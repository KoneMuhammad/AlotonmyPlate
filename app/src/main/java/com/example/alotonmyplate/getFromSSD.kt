package com.example.alotonmyplate

import androidx.room3.ColumnInfo
import androidx.room3.Dao
import androidx.room3.Database
import androidx.room3.Delete
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import androidx.room3.Query
import androidx.room3.RoomDatabase
import androidx.room3.Update

@Entity
data class Priority(
        @PrimaryKey val uid: Int,
        @ColumnInfo(name = "level") val level: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "location") val location: Int
)

@Dao
interface PriorityFunctions {
        @Query("SELECT * FROM Priority")
        suspend fun getAll(): List<Priority>

        @Query("SELECT * FROM Priority WHERE location IN (:location)")
        suspend fun getByLocation(location: Int): List<Priority>

        @Update
        suspend fun updateAll(priorities: List<Priority>)

        @Delete
        suspend fun delete(priorities: List<Priority>)
}

@Database(entities = [Priority::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
        abstract fun PriorityFunctions(): PriorityFunctions
}
