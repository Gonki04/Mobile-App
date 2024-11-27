package com.example.shoppinglist.ui.lists.items

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.TAG
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.example.shoppinglist.models.Item
import com.google.firebase.firestore.FirebaseFirestore

data class ListItemsState(
    val items : List<Item> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ListItemsViewModel : ViewModel(){

    var state = mutableStateOf(ListItemsState())
        private set


    fun getItems(listId : String){

        val db = Firebase.firestore

        db.collection("lists")
            .document(listId)
            .collection("items")
            .addSnapshotListener{ value, error->
                if (error!=null){
                    state.value = state.value.copy(
                        error = error.message
                    )
                    return@addSnapshotListener
                }

                val items = arrayListOf<Item>()
                for (document in value?.documents!!) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val item = document.toObject(Item::class.java)
                    item?.docId = document.id
                    items.add(item!!)
                }
                state.value = state.value.copy(
                    items = items
                )
            }

    }

    fun toggleItemChecked(listId: String, item: Item){
        val db = Firebase.firestore

        item.checked = !item.checked

        db.collection("lists")
            .document(listId)
            .collection("items")
            .document(item.docId!!)
            .set(item)

    }

    fun addItem(listId: String, item: Item) {
        val db = Firebase.firestore
        db.collection("lists")
            .document(listId)
            .collection("items")
            .add(item)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                // Handle error (e.g., update state with error message)
            }
    }

    fun deleteItemByNameAndQtd(listId: String, name: String, qtd: Int) {
        val collectionRef = FirebaseFirestore.getInstance()
            .collection("lists")
            .document(listId)
            .collection("items")
        collectionRef.whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val currentQtd = document.getLong("qtd")?.toInt() ?: 0
                    when {
                        currentQtd > qtd -> {
                            // Atualizar a quantidade restante
                            document.reference.update("qtd", currentQtd - qtd)
                                .addOnSuccessListener {
                                    Log.d("ListItemsViewModel", "Quantidade atualizada com sucesso.")
                                }
                        }
                        currentQtd == qtd -> {
                            // Excluir o documento
                            document.reference.delete()
                                .addOnSuccessListener {
                                    Log.d("ListItemsViewModel", "Item removed with success.")
                                }
                        }
                        else -> {
                            // Quantidade a ser removida é maior que a disponível
                            Log.w("ListItemsViewModel", "Insufficient quantity to remove.")
                        }
                    }
                }
            }
    }
}