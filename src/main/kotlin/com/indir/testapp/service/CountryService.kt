package com.indir.testapp.service;

import com.indir.testapp.annotation.LogExecutionTime
import com.indir.testapp.dto.CountryDto
import com.indir.testapp.entity.Country
import com.indir.testapp.entity.IncomeLevel
import com.indir.testapp.entity.Region
import com.indir.testapp.enum.Indicator
import com.indir.testapp.exception.error.NotFoundException
import com.indir.testapp.exception.error.RestApiError
import com.indir.testapp.mapper.CountryMapper
import com.indir.testapp.repository.CountryRepository
import com.indir.testapp.repository.IncomeLevelRepository
import com.indir.testapp.repository.RegionRepository
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriComponentsBuilder
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.util.*
import kotlin.collections.LinkedHashMap

@Service
class CountryService {

    @Autowired
    lateinit var countryRepository: CountryRepository

    @Autowired
    lateinit var incomeLevelRepository: IncomeLevelRepository

    @Autowired
    lateinit var regionRepository: RegionRepository

    @Autowired
    lateinit var languageService: LanguageService

    val HEADERS :Array<String> = arrayOf("Name", "Code", "Iso2Code",
                                         "longitude","latitude","population",
                                         "gdp","last updated", "Region code",
                                         "Region iso2Code", "Region name", "Income Level code",
                                         "Income Level iso2Code", "Income Level description")

    val BASE_URL = "https://api.worldbank.org/v2/country/all"
    val JSON_FORMAT = "json"

    @LogExecutionTime
    fun loadData() {
        if (countryRepository.count() <= 0) {
            val finalURI = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                            .queryParam("format", JSON_FORMAT)
                            .queryParam("per_page", 400)
                            .build()
                            .encode()
                            .toUri()

            val restTemplate = RestTemplate();
            val objects :Array<Any> = restTemplate.getForObject(finalURI)

            val finalObj = objects[1] as List<Map<String, Any>>

            finalObj.stream().forEach {
                val latitude = (it["latitude"] as String).toDoubleOrNull()
                val longitude = (it["longitude"] as String).toDoubleOrNull()
                @Suppress("UNCHECKED_CAST")
                val regionCode = (it["region"] as LinkedHashMap<String, Any>)["id"].toString()
                val regionIso2Code = (it["region"] as LinkedHashMap<String, Any>)["iso2code"].toString()
                val regionName = (it["region"] as LinkedHashMap<String, Any>)["value"].toString()
                val incomeCode = (it["incomeLevel"] as LinkedHashMap<String, Any>)["id"].toString()
                val incomeIso2Code = (it["incomeLevel"] as LinkedHashMap<String, Any>)["iso2code"].toString()
                val incomeValue = (it["incomeLevel"] as LinkedHashMap<String, Any>)["value"].toString()

                var region = regionRepository.findByCode(regionCode)
                if (region == null) {
                    region = Region(code = regionCode, iso2Code = regionIso2Code, name = regionName)
                    regionRepository.save(region).also { region = it }
                }

                var incomeLevel = incomeLevelRepository.findByCode(incomeCode)
                if (incomeLevel == null) {
                    incomeLevel = IncomeLevel(code = incomeCode, iso2Code = incomeIso2Code, value = incomeValue)
                    incomeLevelRepository.save(incomeLevel).also { incomeLevel = it }
                }

                val country = Country(code = it["id"] as String,
                                      name = it["name"] as String,
                                      iso2Code = it["iso2Code"] as String,
                                      latitude = latitude ,
                                      longitude = longitude,
                                      incomeLevel = incomeLevel!!,
                                      region = region!!
                )
                countryRepository.save(country)

            }
        }

    }

    @Transactional
    @LogExecutionTime
    fun updateGeneralData(indicator: Indicator) {
        if (countryRepository.count() > 0) {
            val activeYear: Int = LocalDate.now().year - 1
            val dateFormat = "$activeYear:$activeYear"
            val FINAL_URL = "$BASE_URL/indicator/${indicator.description}"

            val finalURI = UriComponentsBuilder.fromHttpUrl(FINAL_URL)
                            .queryParam("date", dateFormat)
                            .queryParam("format", JSON_FORMAT)
                            .queryParam("per_page", 400)
                            .build()
                            .encode()
                            .toUri()

            val restTemplate = RestTemplate();
            val objects :Array<Any> = restTemplate.getForObject(finalURI)

            val finalObj = objects[1] as List<Map<String, Any>>
            finalObj.stream().forEach {
                val code = (it["country"] as LinkedHashMap<String, Any?>)["id"].toString()
                val value = it["value"]?.toString()?.toDoubleOrNull()
                var country = findCountryByCode(code)

                when(indicator) {
                    Indicator.POPULATION -> country.population = value
                    Indicator.GDP -> country.gdp = value
                }
                country.lastUpdated = Date()
                countryRepository.save(country)
            }
        }
    }

    fun downloadCsv(fileName: String): ByteArrayResource {
        val countries = countryRepository.findAll()
        val out = FileWriter(fileName)

        val printer = CSVPrinter(out, CSVFormat.DEFAULT
            .withHeader(*HEADERS)
            .withDelimiter(','))
        try {
            for (country in countries)
                printer.printRecord(country.name,
                                    country.code,
                                    country.iso2Code,
                                    country.longitude,
                                    country.latitude,
                                    country.population,
                                    country.gdp,
                                    country.lastUpdated,
                                    country.region.code,
                                    country.region.iso2Code,
                                    country.region.name,
                                    country.incomeLevel.code,
                                    country.incomeLevel.iso2Code,
                                    country.incomeLevel.value)

        } catch (e: IOException) {
            e.printStackTrace();
        }
        out.close()
        printer.close()
        val content = Files.readAllBytes(Path.of(fileName))
        Files.deleteIfExists(Path.of(fileName))
        return ByteArrayResource(content)
    }

    fun getTranslatedCountry(iso2Code: String, languageCode: String): CountryDto? {
        val country = findCountryByCode(iso2Code)
        languageService.findByCode(languageCode)
        val FINAL_URL = "https://api.worldbank.org/v2/${languageCode}/country/${iso2Code.toLowerCase()}"

        val finalURI = UriComponentsBuilder.fromHttpUrl(FINAL_URL)
                        .queryParam("format", JSON_FORMAT)
                        .build()
                        .encode()
                        .toUri()

        val restTemplate = RestTemplate();
        val objects :Array<Any> = restTemplate.getForObject(finalURI)

        val finalObj = objects[1] as List<Map<String, Any>>
        var countryDto: CountryDto? = null
        
        finalObj.stream().forEach {
            val regionName = (it["region"] as LinkedHashMap<String, Any>)["value"].toString()
            val incomeValue = (it["incomeLevel"] as LinkedHashMap<String, Any>)["value"].toString()
            countryDto = CountryMapper.toDto(country)
            countryDto!!.name = it["name"] as String
            countryDto!!.incomeLevel.value = incomeValue
            countryDto!!.region.name = regionName
        }
        return countryDto
    }

    fun getCountries(): List<CountryDto> = countryRepository.findAll().map { CountryMapper.toDto(it) }

    private fun findCountryByCode(code: String): Country =
        countryRepository.findByIso2Code(code) ?: throw NotFoundException(RestApiError.COUNTRY_DOES_NOT_EXISTS)



}
