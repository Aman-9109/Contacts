package com.example.contacts.navigation

import kotlinx.serialization.Serializable

class Routes {
     @Serializable
     object Home


    @Serializable
    data class Detail(val contactId: Int)

    @Serializable
    object AddContact

}