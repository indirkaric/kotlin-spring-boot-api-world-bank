package com.indir.testapp.entity

import javax.persistence.*

@Entity
@Table(name = "languages")
data class Language(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    var name: String,

    @Column(nullable = false, unique = true)
    var code: String,

    @Column(nullable = false)
    var nativeForm: String
)