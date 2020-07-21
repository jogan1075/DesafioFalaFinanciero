package com.jmc.tecnicaltestfifjmc.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var nombre: String,
    var apellido: String,
    var correo: String,
    var password: String
)