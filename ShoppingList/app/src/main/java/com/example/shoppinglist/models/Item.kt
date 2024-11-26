package com.example.shoppinglist.models

class Item (
    var docId : String?,
    var name : String?,
    var qtd : Double?,
    var checked : Boolean = false) {

    constructor(name: String, qtd: Double, checked: Boolean) : this(null,null,null, false)
}