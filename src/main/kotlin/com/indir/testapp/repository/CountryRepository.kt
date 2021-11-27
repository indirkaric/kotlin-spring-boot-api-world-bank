package com.indir.testapp.repository

import com.indir.testapp.entity.Country
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository: JpaRepository<Country, Long> {
    fun findByIso2Code(code: String): Country?
}