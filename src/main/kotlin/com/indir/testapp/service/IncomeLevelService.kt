package com.indir.testapp.service

import com.indir.testapp.dto.IncomeLevelDto
import com.indir.testapp.mapper.IncomeLevelMapper
import com.indir.testapp.repository.IncomeLevelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IncomeLevelService {

    @Autowired
    lateinit var incomeLevelRepository: IncomeLevelRepository

    fun getIncomeLevels(): List<IncomeLevelDto> = incomeLevelRepository.findAll().map { IncomeLevelMapper.toDto(it) }
}