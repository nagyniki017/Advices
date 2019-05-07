package bme.aut.nikoletn.advices.mock

import android.content.Context
import bme.aut.nikoletn.advices.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockDatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase {
        return MockAppDatabase.getInstance(context)
    }
}