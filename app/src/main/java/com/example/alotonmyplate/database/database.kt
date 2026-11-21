package com.example.alotonmyplate.database

import androidx.room3.ColumnInfo
import androidx.room3.Dao
import androidx.room3.Database
import androidx.room3.Delete
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import androidx.room3.Query
import androidx.room3.RoomDatabase
import androidx.room3.Update
import androidx.room3.Upsert

@Entity
data class Priority(
        @PrimaryKey(autoGenerate = true)
        val uid: Int = 0,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "location") val location: Int,
        @ColumnInfo(name = "count") val count: Int,
        @ColumnInfo(name = "x") var x: Float,
        @ColumnInfo(name = "y") var y: Float,
)
@Dao
interface PriorityFunctions {
        @Query("SELECT * FROM Priority")
        suspend fun getAll(): List<Priority>

        @Query("SELECT * FROM Priority WHERE location IN (:location)")
        suspend fun getByLocation(location: Int): List<Priority>

        @Upsert
        suspend fun updateDatabase(priorities: List<Priority>)

        @Delete
        suspend fun delete(priorities: List<Priority>)
}

@Database(entities = [Priority::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
        abstract fun PriorityFunctions(): PriorityFunctions
}
