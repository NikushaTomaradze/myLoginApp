package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth




class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextNewPasswordRegister: EditText
    private lateinit var buttonRegister: Button
    private lateinit var editTextNewPasswordConfirm: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        init()
        registerListeners()









        editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text.toString())
                        .matches()
                )
                    buttonRegister.isEnabled = true
                else {
                    buttonRegister.isEnabled = false
                    editTextEmail.setError("Invalid Email")
                }

            }


        })





        editTextNewPasswordConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (editTextNewPasswordConfirm.text.toString()
                        .equals(editTextNewPasswordRegister.text.toString())
                )
                    buttonRegister.isEnabled = true
                else {
                    buttonRegister.isEnabled = false
                    editTextNewPasswordConfirm.setError("Passwords don't match!")
                }
            }

        })


    }

    private fun init() {

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextNewPasswordRegister = findViewById(R.id.editTextNewPasswordRegister)
        buttonRegister = findViewById(R.id.buttonRegister)
        editTextNewPasswordConfirm = findViewById(R.id.editTextPasswordConfirm)


    }


    private fun registerListeners() {
        buttonRegister.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextNewPasswordRegister.text.toString()






                if (!editTextNewPasswordRegister.text.toString().contains(Regex("[0-9]"))) {
                    editTextNewPasswordRegister.error = "Password must contain a number"
                    editTextNewPasswordRegister.requestFocus()
                    return@setOnClickListener
                }
            if (!editTextNewPasswordRegister.text.toString().contains((Regex("[!-*]")))) {
                editTextNewPasswordRegister.error = "Password must contain a symbol !-*"
                editTextNewPasswordRegister.requestFocus()
                return@setOnClickListener
            }










            if (editTextNewPasswordRegister.text.toString().isEmpty()) {
                editTextNewPasswordRegister.error = "Please enter your password"
                editTextNewPasswordRegister.requestFocus()
                return@setOnClickListener

            }

            if (editTextNewPasswordConfirm.text.toString().isEmpty()) {
                editTextNewPasswordConfirm.error = "Please re-enter your password"
                editTextNewPasswordConfirm.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (editTextNewPasswordRegister.text.toString().length < 9 )   {
                Toast.makeText(this, "Password is too short!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else if (password != "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$") {

                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }

                }


        }











        }
    }










