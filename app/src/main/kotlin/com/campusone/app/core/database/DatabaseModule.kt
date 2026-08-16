package com.campusone.app.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Placeholder for actual Room Database class
    // @Provides
    // @Singleton
    // fun provideDatabase(@ApplicationContext context: Context): CampusOneDatabase {
    //     return Room.databaseBuilder(
    //         context,
    //         CampusOneDatabase::class.java,
    //         "campusone_db"
    //     ).build()
    // }
}
