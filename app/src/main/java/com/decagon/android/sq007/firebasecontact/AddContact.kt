package com.decagon.android.sq007.firebasecontact

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.decagon.android.sq007.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class AddContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        phonenumberInputChecker()
        btAddToList()
    }

    // btAddtoList function that collects inputs starts
    fun btAddToList() {
        val btAddToList = findViewById<Button>(R.id.btAddToList)
        btAddToList.setOnClickListener {
            val firstNameInput =
                findViewById<EditText>(R.id.etAddContactFirstNameValue).text.toString().trim()
            val lastNameinput =
                findViewById<EditText>(R.id.etAddContactLastNameValue).text.toString().trim()
            val phoneNumberInput =
                findViewById<EditText>(R.id.etAddContacPhoneValue).text.toString().trim()
            val fieldVerificationChecker = validateRegistrationInput(
                firstName = firstNameInput,
                lastName = lastNameinput,
                number = phoneNumberInput
            )
            if (fieldVerificationChecker) {
                // create an instance of the data class and pass data to it
                val contact = Contact(
                    firstName = firstNameInput,
                    lastName = lastNameinput,
                    phoneNumber = phoneNumberInput
                )
                // bundle the data and send to ContactViewModel contact parameter in addContact function
                val viewModel = ContactViewModel()
                viewModel.addContact(contact)
                Toast.makeText(this, "Your Contact has been Succesfully added", Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(this, FirebaseContactActivity::class.java))
            } else {
                Toast.makeText(this, "Please, Check Your Input", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // btAddToList function that collects inputs ends

    // regex method for phone number validation starts here
    fun phoneNumberValidator(text: String): Boolean {
        // regular expresion code to validate nigeria phone number
        var pattern: Pattern = Pattern.compile("^(0|234)([789][01])([0-9]){8}$")
        var matcher: Matcher = pattern.matcher(text)
        return matcher.matches()
    }

    // function for validating user input starts here

    // section that handles phone validation before submission starts here
    fun phonenumberInputChecker() {
        val etAddContacPhoneValue = findViewById<EditText>(R.id.etAddContactFirstNameValue)

        etAddContacPhoneValue.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // null
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (phoneNumberValidator(etAddContacPhoneValue.text.toString())) {
                } else {
                    etAddContacPhoneValue.error = "Invalid Mobile"
                }
            }
        })
    }
    // section that handles phone validatiom before sumbmission ends here

    // a function to check the validity of the overall input as described starts here
    fun validateRegistrationInput(firstName: String, lastName: String, number: String): Boolean {
        if ((firstName.isBlank() || firstName.length > 15) || (lastName.isBlank() || lastName.length > 15) || !phoneNumberValidator(
                number
            )
        ) {
            return false
        }
        return true
    }
    // a function to check the validity of the overall input as described ends here
}
