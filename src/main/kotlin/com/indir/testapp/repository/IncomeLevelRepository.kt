package com.indir.testapp.repository

import com.indir.testapp.entity.IncomeLevel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IncomeLevelRepository: JpaRepository<IncomeLevel, Long> {
    fun findByCode(code: String): IncomeLevel?
}