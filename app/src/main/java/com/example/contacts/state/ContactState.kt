package com.example.contacts.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.contacts.room.Contact


data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val name: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String> ?= mutableStateOf(""),
    val number: MutableState<String> = mutableStateOf(""),
    val address: MutableState<String>? = mutableStateOf(""),
    val dateOfCreation: MutableState<Double> = mutableDoubleStateOf(0.0),


    )