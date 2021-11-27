package com.indir.testapp.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "countries")
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var code: String,

    @Column(nullable = false)
    var iso2Code: String,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var lastUpdated: Date = Date(),

    @ManyToOne
    @JoinColumn(name = "income_level_id", nullable = false)
    var incomeLevel: IncomeLevel,

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    var region: Region,

    var longitude: Double? = null,
    var latitude: Double? = null,
    var population: Double? = null,
    var gdp: Double? = null
)