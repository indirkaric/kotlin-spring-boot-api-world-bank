package com.indir.testapp.mapper

import com.indir.testapp.dto.RegionDto
import com.indir.testapp.entity.Region

class RegionMapper {
    companion object {
        fun toDto(region: Region): RegionDto =
            RegionDto(
                id = region.id,
                name = region.name,
                code = region.code,
                iso2Code = region.iso2Code
            )
    }
}