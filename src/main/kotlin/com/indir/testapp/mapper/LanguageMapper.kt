package com.indir.testapp.mapper

import com.indir.testapp.dto.LanguageDto
import com.indir.testapp.entity.Language

class LanguageMapper {
    companion object {
        fun toDto(language: Language): LanguageDto =
            LanguageDto(id = language.id,
                        code = language.code,
                        name = language.name,
                        nativeForm = language.nativeForm)
    }
}