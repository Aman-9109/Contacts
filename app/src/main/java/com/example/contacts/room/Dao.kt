package com.example.contacts.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert()
    suspend fun upsert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contact")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contact WHERE id = :id")
    suspend fun getContactById(id: Int): Contact?

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAllContactsSortedByNameAsc(): Flow<List<Contact>>

    // Get all contacts sorted by name in descending order
    @Query("SELECT * FROM contact ORDER BY name DESC")
    fun getAllContactsSortedByNameDesc(): Flow<List<Contact>>

}
