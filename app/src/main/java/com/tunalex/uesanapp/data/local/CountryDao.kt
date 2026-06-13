package com.tunalex.uesanapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountryEntity)

    @Delete
    suspend fun delete(country: CountryEntity)

    @Query("SELECT * FROM favorites ORDER BY ranking ASC")
    fun getAllFavorites(): Flow<List<CountryEntity>>
}
