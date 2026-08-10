package com.example.tourismapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tourismapp.Model.TouristPlaces

@Database(entities = [TouristPlaces::class], version = 2)
abstract class TouristDatabase : RoomDatabase(){
    abstract fun touristDao() : TouristDao

    companion object{
        val migration_1_2 = object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    ALTER TABLE tourism ADD COLUMN longitude TEXT NOT NULL DEFAULT ''
                """.trimIndent())
                db.execSQL("""
                    ALTER TABLE tourism ADD COLUMN latitude TEXT NOT NULL DEFAULT ''
                """.trimIndent())
            }
        }
        @Volatile
        private var INSTANCE : TouristDatabase? = null

        fun getDatabase(context : Context) : TouristDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context, TouristDatabase::class.java,"touristDb").addMigrations(migration_1_2).build();
                }
            }
            return INSTANCE!!;
        }
    }
}