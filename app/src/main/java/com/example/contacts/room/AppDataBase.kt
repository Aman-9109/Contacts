package com.example.contacts.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}