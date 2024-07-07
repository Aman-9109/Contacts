package com.example.contacts.repositoryImpl

import com.example.contacts.repository.Repository
import com.example.contacts.room.Contact
import com.example.contacts.room.ContactDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dao: ContactDao) : Repository {
    override suspend fun addOrUpdateContact(contact: Contact) {
        dao.upsert(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        dao.delete(contact)
    }

    override fun getAllContacts(): Flow<List<Contact>> {
        return dao.getAllContacts()
    }

    override suspend fun getContactById(id: Int): Contact? {
        return dao.getContactById(id)
    }

    override fun getAllContactsSortedByNameAsc(): Flow<List<Contact>> {
        return dao.getAllContactsSortedByNameAsc()
    }

    override fun getAllContactsSortedByNameDesc(): Flow<List<Contact>> {
        return dao.getAllContactsSortedByNameDesc()
    }
}