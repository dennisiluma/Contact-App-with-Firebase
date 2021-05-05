package com.decagon.android.sq007.firebasecontact

import com.google.firebase.database.Exclude

data class Contact(
    @get: Exclude
    var id: String? = null, //it is ignored by the @get:Exclude
    var firstName: String? = null,
    var lastName: String? = null,
    var phoneNumber: String? = null
)

