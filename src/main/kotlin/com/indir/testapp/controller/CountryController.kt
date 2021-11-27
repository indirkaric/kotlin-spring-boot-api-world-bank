package com.indir.testapp.controller

import com.indir.testapp.common.FileConstants
import com.indir.testapp.dto.CountryDto
import com.indir.testapp.service.CountryService
import com.indir.testapp.util.HeaderUtil
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/countries/")
class CountryController {

    @Autowired
    lateinit var countryService: CountryService

    @GetMapping
    @ApiOperation(value = "Get countries")
    fun getCountries(): ResponseEntity<List<CountryDto>> =  ResponseEntity(countryService.getCountries(), HttpStatus.OK)

    @GetMapping("{languageCode}/{iso2Code}")
    @ApiOperation(value = "Get country translation")
    fun getCountry(@PathVariable iso2Code: String, @PathVariable languageCode: String): ResponseEntity<CountryDto?> =
        ResponseEntity(countryService.getTranslatedCountry(iso2Code, languageCode), HttpStatus.OK)


    @GetMapping("csv/{fileName}")
    @ApiOperation(" Download CSV file")
    fun getCSV(@PathVariable fileName: String): ResponseEntity<ByteArrayResource> {
        val resource = countryService.downloadCsv(fileName)
        if (resource.contentLength() > 0)
            return ResponseEntity.ok()
                .headers(HeaderUtil.getFileHeader(fileName, FileConstants.CSV_EXTENSION))
                .contentLength(resource.contentLength())
                .body(resource)
        return ResponseEntity.noContent().build()
    }

}