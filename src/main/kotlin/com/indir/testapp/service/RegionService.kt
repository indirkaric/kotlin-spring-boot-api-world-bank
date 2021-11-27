package com.indir.testapp.service

import com.indir.testapp.dto.RegionDto
import com.indir.testapp.mapper.RegionMapper
import com.indir.testapp.repository.RegionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegionService {

    @Autowired
    lateinit var regionRepository: RegionRepository

    fun getRegions() : List<RegionDto> = regionRepository.findAll().map { RegionMapper.toDto(it) }
}