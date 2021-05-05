package com.decagon.android.sq007.phonecontact
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import androidx.core.app.ActivityCompat
import com.decagon.android.sq007.MainActivity
import com.decagon.android.sq007.R

class PhoneContactActivity : AppCompatActivity() {
    // decler varibles that holds data colum in form of a row
    var cols = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_contact)

        checkAndRequestForPermission()
        toHome()


    }

    //check if permission is already granted if not, request for permission starts
    fun checkAndRequestForPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                Array(1) { android.Manifest.permission.READ_CONTACTS },
                113
            )
        } else readContact()
    }
    //check if permission is already granted if not, request for permission ends

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 113 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            readContact()
    }

    private fun readContact() {
        val from = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        ).toTypedArray()
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)

        var resultSet = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            cols, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

        var myAdapter =
            SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, resultSet, from, to, 0)

        findViewById<ListView>(R.id.lvPhoneContact).adapter = myAdapter

        //implement searchView query starts
        findViewById<SearchView>(R.id.searchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                resultSet =
                    contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        cols,
                        "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} LIKE ?",
                        Array(1) { "%$newText%" },
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    )
                myAdapter.changeCursor(resultSet)
                return false
            }

        })
        //implementsearchview query ends

    }

    fun toHome() {
        findViewById<Button>(R.id.btHome).setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}