package com.indir.testapp.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Column(unique = true)
    var username: String,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createdAt: Date = Date()
)