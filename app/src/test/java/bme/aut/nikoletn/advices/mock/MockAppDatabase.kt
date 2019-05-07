package bme.aut.nikoletn.advices.mock

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import bme.aut.nikoletn.advices.AppDatabase
import bme.aut.nikoletn.advices.model.Advice

@Database(entities = arrayOf(Advice::class), version = 1)
abstract class MockAppDatabase : AppDatabase() {
    abstract override fun adviceDao(): MockAdviceDao

    companion object {
        private var INSTANCE: MockAppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MockAppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .inMemoryDatabaseBuilder(context.applicationContext, MockAppDatabase::class.java)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

    }
}