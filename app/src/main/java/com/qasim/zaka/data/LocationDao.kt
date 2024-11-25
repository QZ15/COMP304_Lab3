package com.qasim.zaka.data

import androidx.room.*

@Dao
interface LocationDao {
    // Insert a new location
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(location: Location)

    // Retrieve all saved locations
    @Query("SELECT * FROM locations")
    fun getAllLocations(): List<Location>

    // Delete a specific location
    @Delete
    fun delete(location: Location)
}
