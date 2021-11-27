package com.indir.testapp.repository

import com.indir.testapp.entity.Region
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegionRepository: JpaRepository<Region, Long> {
    fun findByCode(code: String): Region?
}