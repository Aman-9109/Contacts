package com.example.contacts.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.repository.Repository
import com.example.contacts.room.Contact
import com.example.contacts.state.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {


    private val isSortedByName = MutableStateFlow(true)



    @OptIn(ExperimentalCoroutinesApi::class)
    private val _contact = isSortedByName.flatMapLatest { sortedByName ->
        if (sortedByName) {
            repository.getAllContactsSortedByNameAsc()
        } else {
            repository.getAllContactsSortedByNameDesc()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val contact =_contact

    fun deleteContact(contact: Contact) = viewModelScope.launch {
        repository.deleteContact(contact)
    }

//    private val _state = MutableStateFlow(ContactState())
//    val state = combine(_state, contact) { state, contactList ->
//        state.copy(contacts = contactList)
//    }.flowOn(viewModelScope.coroutineContext)



}
