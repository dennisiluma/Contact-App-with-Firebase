package com.decagon.android.sq007
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.decagon.android.sq007.firebasecontact.FirebaseContactActivity
import com.decagon.android.sq007.phonecontact.PhoneContactActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonPhoneContactTap()
        buttonCustomContactTap()
    }

    private fun buttonPhoneContactTap() {
        findViewById<Button>(R.id.btPhoneContact).setOnClickListener {
            val phonecontactIntent = Intent(this, PhoneContactActivity::class.java)
            startActivity(phonecontactIntent)
        }
    }

    fun buttonCustomContactTap() {
        findViewById<Button>(R.id.btCustomContact).setOnClickListener {
            val customContactIntent = Intent(this, FirebaseContactActivity::class.java)
            startActivity(customContactIntent)
        }
    }
}
