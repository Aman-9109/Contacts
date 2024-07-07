package com.example.contacts.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.MutableStateFlow

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var number: String,
    var email: String?,
    var address: String?,
    val dateOfCreation: Long = System.currentTimeMillis()
)