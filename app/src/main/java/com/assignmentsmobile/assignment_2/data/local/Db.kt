package com.assignmentsmobile.assignment_2.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Update
import com.assignmentsmobile.assignment_2.data.Film
import com.assignmentsmobile.assignment_2.data.Section

@Dao
interface SectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSections(section_table: List<Section>)

    @Query("SELECT * FROM section_table")
    suspend fun getSections(): List<Section>

    @Update
    suspend fun updateSection(section: Section)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: Film)

    @Query("DELETE FROM section_table")
    suspend fun clearAllSections()
}

@Database(entities = [Section::class, Film::class], version = 1)
@TypeConverters(FilmListConverter::class, CountryListConverter::class, GenreListConverter::class)
abstract class SectionDatabase : RoomDatabase() {
    abstract fun sectionDao(): SectionDao

    companion object {
        @Volatile
        private var INSTANCE: SectionDatabase? = null

        fun getDatabase(context: Context): SectionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SectionDatabase::class.java,
                    "section_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

