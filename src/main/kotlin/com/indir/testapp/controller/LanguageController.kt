package com.indir.testapp.controller

import com.indir.testapp.dto.LanguageDto
import com.indir.testapp.service.LanguageService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/languages/")
class LanguageController {
    @Autowired
    lateinit var languageService: LanguageService

    @GetMapping
    @ApiOperation("Get all languages")
    fun getLanguages(): ResponseEntity<List<LanguageDto>> = ResponseEntity(languageService.getLanguages(), HttpStatus.OK)
}