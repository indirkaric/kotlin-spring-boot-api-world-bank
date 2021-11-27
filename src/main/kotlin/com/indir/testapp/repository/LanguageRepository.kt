package com.indir.testapp.repository

import com.indir.testapp.entity.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LanguageRepository: JpaRepository<Language, Long> {
    fun findByCode(code: String) :Language?
}