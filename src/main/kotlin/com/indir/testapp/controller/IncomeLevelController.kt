package com.indir.testapp.controller

import com.indir.testapp.dto.IncomeLevelDto
import com.indir.testapp.service.IncomeLevelService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/income-levels/")
class IncomeLevelController {

    @Autowired
    lateinit var incomeLevelService: IncomeLevelService

    @GetMapping
    @ApiOperation(value = "Get income levels")
    fun getIncomeLevels(): ResponseEntity<List<IncomeLevelDto>>  = ResponseEntity(incomeLevelService.getIncomeLevels(), HttpStatus.OK)
}