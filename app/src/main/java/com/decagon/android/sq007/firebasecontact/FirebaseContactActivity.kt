package com.decagon.android.sq007.firebasecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.phonecontact.PhoneContactActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class FirebaseContactActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayListContact: ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_contact)
        addContactButtonTap()

        recyclerView = findViewById(R.id.rvRecyclerViewFireBaseContacts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        arrayListContact = arrayListOf()
        getUserdata()

        findViewById<RecyclerView>(R.id.rvRecyclerViewFireBaseContacts).adapter = ContactAdapter(arrayListContact)

    }

    private fun addContactButtonTap() {
        findViewById<FloatingActionButton>(R.id.fbFloatingActionButton).setOnClickListener {
            val addContactIntent = Intent(this, AddContact::class.java)
            startActivity(addContactIntent)

        }

    }

    fun getUserdata() {
        databaseReference = FirebaseDatabase.getInstance().getReference("contact")
        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (contactSnapshot in snapshot.children) {

                        // get the object of the Contact and store as snap
                        val snap = contactSnapshot.getValue(Contact::class.java)
                        arrayListContact.add(snap!!)

                    }
                    recyclerView.adapter = ContactAdapter(arrayListContact)
                }
                findViewById<RecyclerView>(R.id.rvRecyclerViewFireBaseContacts).adapter = ContactAdapter(arrayListContact)
            }

        })
    }


}