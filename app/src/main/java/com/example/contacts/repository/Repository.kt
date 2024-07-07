package com.example.contacts.repository

import com.example.contacts.room.Contact
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addOrUpdateContact(contact: Contact)


    suspend fun deleteContact(contact: Contact)


    fun getAllContacts(): Flow<List<Contact>>

    suspend fun getContactById(id: Int): Contact?

    fun getAllContactsSortedByNameAsc(): Flow<List<Contact>>

    // Get all contacts sorted by name in descending order
    fun getAllContactsSortedByNameDesc(): Flow<List<Contact>>

}