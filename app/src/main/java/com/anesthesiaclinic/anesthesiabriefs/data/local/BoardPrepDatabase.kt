package com.anesthesiaclinic.anesthesiabriefs.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [QuestionEntity::class, UserProgressEntity::class, PackMetadataEntity::class],
    version = 2,
    exportSchema = false
)
abstract class BoardPrepDatabase : RoomDatabase() {

    abstract fun boardPrepDao(): BoardPrepDao

    companion object {
        @Volatile
        private var INSTANCE: BoardPrepDatabase? = null

        fun getDatabase(context: Context): BoardPrepDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BoardPrepDatabase::class.java,
                    "board_prep_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
