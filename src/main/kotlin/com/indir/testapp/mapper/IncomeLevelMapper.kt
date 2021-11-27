package com.indir.testapp.mapper

import com.indir.testapp.dto.IncomeLevelDto
import com.indir.testapp.entity.IncomeLevel

class IncomeLevelMapper {
    companion object {
        fun toDto(incomeLevel: IncomeLevel): IncomeLevelDto =
            IncomeLevelDto(
                id = incomeLevel.id,
                code = incomeLevel.code,
                iso2Code = incomeLevel.iso2Code,
                value = incomeLevel.value
            )

    }
}