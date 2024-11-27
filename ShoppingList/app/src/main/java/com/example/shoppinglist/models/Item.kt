package com.example.shoppinglist.models

data class Item(
    var docId: String? = null,
    var name: String? = null,
    var qtd: Int? = null,
    var checked: Boolean = false
)
{
    // Construtor vazio necessário para o Firebase
    constructor() : this(null, null, null, false)
}