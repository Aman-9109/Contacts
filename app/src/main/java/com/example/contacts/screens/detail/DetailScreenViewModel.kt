package com.example.contacts.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.repository.Repository
import com.example.contacts.room.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private var _contact = MutableStateFlow(Contact(0, "", "", "", ""))
    val contact = _contact.asStateFlow()


    fun updateContact(
        id: Int,
        name: String,
        email: String,
        number: String,
        address: String
    ) {
        viewModelScope.launch {
            repository.addOrUpdateContact(
                Contact(
                    id = id,
                    name = name,
                    email = email,
                    number = number,
                    address = address
                )
            )

        }

    }

    fun getContactById(id: Int): Any = viewModelScope.launch {


        _contact.value = repository.getContactById(id)!!
        Log.d("TAG", "getContactById: ${_contact.value}")


    }

}