package com.indir.testapp.mapper

import com.indir.testapp.dto.CountryDto
import com.indir.testapp.entity.Country

class CountryMapper {
    companion object {
        fun toDto(country: Country): CountryDto =
            CountryDto(
                id = country.id,
                name = country.name,
                code = country.code,
                iso2Code = country.iso2Code,
                population = country.population,
                gdp = country.gdp,
                latitude = country.latitude,
                longitude = country.longitude,
                lastUpdated = country.lastUpdated.toInstant().toString(),
                incomeLevel = IncomeLevelMapper.toDto(country.incomeLevel),
                region = RegionMapper.toDto(country.region)
            )
    }
}