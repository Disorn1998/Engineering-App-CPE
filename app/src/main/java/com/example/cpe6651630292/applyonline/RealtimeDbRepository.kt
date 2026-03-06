package com.example.cpe6651630292.applyonline

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class RealtimeDbRepository {
    private val database: DatabaseReference = Firebase.database.reference
    private val usersNode = database.child("users")

    fun saveUserData(user: UserData, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        // Use .push() to generate a unique key (ID) for the new record
        val newRef = usersNode.push()

        newRef.setValue(user)
            .addOnSuccessListener {
                val docId = newRef.key ?: "N/A"
                Log.d("RTDB", "User saved with key: $docId")
                onSuccess(docId)
            }
            .addOnFailureListener { e ->
                Log.e("RTDB", "Error saving user data", e)
                onFailure(e)
            }
    }
}
