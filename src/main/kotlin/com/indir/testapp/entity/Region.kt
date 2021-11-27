package com.indir.testapp.entity

import javax.persistence.*

@Entity
@Table(name = "regions")
data class Region(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    var code: String,

    @Column(nullable = false, unique = true)
    var iso2Code: String,

    @Column(nullable = false, unique = true)
    var name: String
)
