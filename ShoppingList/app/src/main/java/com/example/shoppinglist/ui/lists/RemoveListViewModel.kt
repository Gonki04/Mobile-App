package com.example.shoppinglist.ui.lists

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.TAG
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.example.shoppinglist.models.ListItems

data class RemoveListState(
    val name : String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class RemoveListViewModel : ViewModel(){
    var state = mutableStateOf(RemoveListState())
        private set

    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun deleteList() {
        val db = Firebase.firestore
        val listName = state.value.name

        db.collection("lists")
            .whereEqualTo("name", listName)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    document.reference.delete()
                        .addOnSuccessListener {
                            Log.d(TAG, "List deleted successfully.")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error deleting list", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error finding list", e)
            }
    }
}