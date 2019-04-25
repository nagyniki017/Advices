package bme.aut.nikoletn.advices

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bme.aut.nikoletn.advices.dao.AdviceDAO
import bme.aut.nikoletn.advices.model.Advice

@Database(entities = arrayOf(Advice::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun adviceDao(): AdviceDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "advice.db"
                ).build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}