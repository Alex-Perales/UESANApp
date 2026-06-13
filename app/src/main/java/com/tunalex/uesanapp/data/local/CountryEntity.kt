package com.tunalex.uesanapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class CountryEntity(
    @PrimaryKey val name: String,
    val ranking: Int,
    val imageUrl: String
)
