package com.example.contacts.screens.addContact

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.repository.Repository
import com.example.contacts.room.Contact
import com.example.contacts.state.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    fun addContact(
        name:String,
        email: String?,
        number: String,
        address: String?
    ) = viewModelScope.launch {

        val contact = Contact(name = name, email = email, address = address, number = number)
        repository.addOrUpdateContact(contact)
    }

}