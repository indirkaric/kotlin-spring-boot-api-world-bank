package com.indir.testapp.dto

import java.util.*

data class CountryDto (
    var id: Long,
    var name: String,
    var code: String,
    var iso2Code: String,
    var lastUpdated: String,
    var longitude: Double?,
    var latitude: Double?,
    var population: Double?,
    var gdp: Double?,
    var incomeLevel: IncomeLevelDto,
    var region: RegionDto
)