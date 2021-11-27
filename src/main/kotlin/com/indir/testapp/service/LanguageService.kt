package com.indir.testapp.service

import com.indir.testapp.dto.LanguageDto
import com.indir.testapp.entity.Language
import com.indir.testapp.exception.error.NotFoundException
import com.indir.testapp.exception.error.RestApiError
import com.indir.testapp.mapper.LanguageMapper
import com.indir.testapp.repository.LanguageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriComponentsBuilder

@Service
class LanguageService {

    val BASE_URL = "http://api.worldbank.org/v2/languages";
    val JSON_FORMAT = "json"

    @Autowired
    lateinit var languageRepository: LanguageRepository

    fun loadLanguages() {
        if (languageRepository.count() <= 0) {
            val finalURI = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("format", JSON_FORMAT)
                .build()
                .encode()
                .toUri()

            val restTemplate = RestTemplate();
            val objects :Array<Any> = restTemplate.getForObject(finalURI)

            val finalObj = objects[1] as List<Map<String, Any>>

            finalObj.stream().forEach {
                val language = Language(code = it["code"] as String,
                                        name = it["name"] as String,
                                        nativeForm = it["nativeForm"] as String)
                languageRepository.save(language)
            }
        }
    }

    fun getLanguages(): List<LanguageDto> = languageRepository.findAll().map { LanguageMapper.toDto(it) }

    fun findByCode(code: String): Language =
        languageRepository.findByCode(code) ?: throw NotFoundException(RestApiError.LANGUAGE_DOES_NOT_EXISTS)

}