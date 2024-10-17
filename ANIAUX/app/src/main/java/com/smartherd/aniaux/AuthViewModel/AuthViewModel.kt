package com.smartherd.aniaux.AuthViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.util.Log
import com.google.firebase.database.DatabaseReference


class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun login(email:String,password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful)
            {
                onSuccess()
            }
            else{
                onFailure(it.exception?.message ?: "Check your email or password")
            }
        }
    }
    // Function to handle user registration
    fun registerUser(email: String, password: String, fullName: String, role: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = task.result.user!!.uid
                userId?.let {
                    val user = mapOf(
                        "fullName" to fullName,
                        "email" to email,
                        "role" to role
                    )
                    // Save user data in the Realtime Database
                    database.child("users").child(userId).setValue(user).addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            onSuccess()
                        } else {
                            onFailure(dbTask.exception?.message ?: "Failed to save user details")
                        }
                    }
                }
            } else {
                onFailure(task.exception?.message ?: "Registration failed")
            }
        }
    }
}
