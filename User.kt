package com.example.datingapp.model

data class User(

    var name: String="NOT DEFINED",
    var bio:String ="NOT DEFINED",
    var sex: String="NOT DEFINED",
    var age:String ="NOT DEFINED",
    var imageUrl: String ="NOT DEFINED",
    var uid:String ="NOT DEFINED",
    var latitude:Double =0.0,
    var longtitude:Double =0.0,
    var password:String = "NOT DEFINED",
    var email:String = "NOT DEFINED"


)

data class ChatObject(
    val chatId: String,
    val chatName: String,
    val image:String,
   val  usersList: ArrayList<User>

)

class MessageObject(
    var sender: String= "",
    var receiver: String ="",
    var message: String=""

)


