package com.decagon.android.sq007.firebasecontact

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase

class ContactViewModel : ViewModel() {
    // connect to our firebase data
//     val database = FirebaseDatabase.getInstance(NODE_CONTACT).reference
    // keep an eye on our data and firebase
    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    // keep an eye on our data displayed from firebase
    private val _contact = MutableLiveData<Contact?>()
    val contact: LiveData<Contact?> get() = _contact

    // add data to firebase
    fun addContact(contact: Contact) {
        // generate an id
        val database = FirebaseDatabase.getInstance().getReference("contact")
        contact.id = database.push().key
        // save values to firebase and generate an id
        database.child(contact.id!!).setValue(contact).addOnCompleteListener {
            if (it.isSuccessful) {
                _result.value = null
                Log.i("tag", "succes")
            } else {
                _result.value = it.exception
                Log.i("tag", "Faliure")
            }
        }
    }
    // event listener to know if a contact has been added or not
//    private val childEventListener = object :ChildEventListener{
//        override fun onCancelled(error: DatabaseError) {}
//
//        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
//
//        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
//
//        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//
//            val contact = snapshot.getValue(Contact::class.java) //get a snapshot of a value from firebase and store it as contact
//            contact?.id = snapshot.key
//            _contact.value = contact!!
//        }
//
//        override fun onChildRemoved(snapshot: DataSnapshot) {}
//
//    }

//    fun  getRealTimeUpdate(){
//        database.addChildEventListener(childEventListener)
//    }

//    override fun onCleared() {
//        super.onCleared()
//        database.removeEventListener(childEventListener)
//    }
}
