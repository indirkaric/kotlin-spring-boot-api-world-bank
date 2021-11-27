package com.indir.testapp.controller

import com.indir.testapp.dto.RegionDto
import com.indir.testapp.service.RegionService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/regions/")
class RegionController {

    @Autowired
    lateinit var regionService: RegionService

    @GetMapping
    @ApiOperation(value = "Get regions")
    fun getRegions() : ResponseEntity<List<RegionDto>> = ResponseEntity(regionService.getRegions(), HttpStatus.OK)
}