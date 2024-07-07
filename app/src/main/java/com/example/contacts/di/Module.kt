package com.example.contacts.di

import android.app.Application
import androidx.room.Room
import com.example.contacts.repository.Repository
import com.example.contacts.repositoryImpl.RepositoryImpl
import com.example.contacts.room.AppDatabase
import com.example.contacts.room.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "contacts.db")
            .build()
    }

    @Provides
    @Singleton
    fun providesContactDao(db: AppDatabase): ContactDao {
        return db.contactDao()
    }

    @Provides
    @Singleton
    fun providesRepository(dao: ContactDao):Repository{
        return RepositoryImpl(dao)

    }




}