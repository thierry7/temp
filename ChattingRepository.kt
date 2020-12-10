package com.example.datingapp.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class ChattingRepository(val  application: Application)
{
    var fUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    var ref: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")

    var chatRef :DatabaseReference = FirebaseDatabase.getInstance().reference.child("chats")

    val mMessages : MutableLiveData<ArrayList<MessageObject>> = MutableLiveData()
    val mSender: MutableLiveData<User> = MutableLiveData()



    fun getSender(uid: String): MutableLiveData<User>{

        ref.child(uid).addValueEventListener( object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot?) {
                var user = User()
                user = p0?.getValue(User::class.java)!!
                mSender.postValue(user)

            }

            override fun onCancelled(p0: DatabaseError?) {
                TODO("Not yet implemented")
            }

        })

        return mSender
    }

    fun sendMessage(sender: String, receiver: String, message: String)
    {
        val reference: DatabaseReference = FirebaseDatabase.getInstance().reference

        val map = hashMapOf<String, String>()

        map.put("sender", sender)
        map.put("receiver", receiver)
        map.put("message", message)


        reference.child("chats").push().setValue(map)
    }


    fun readMessages(senderUid: String, receiverUid: String): MutableLiveData<ArrayList<MessageObject>>
    {

        var array: ArrayList<MessageObject> = ArrayList()

        chatRef.addValueEventListener(object : ValueEventListener{


            override fun onDataChange(p0: DataSnapshot?) {
                array.clear()
                var messages: MessageObject

                for (snap: DataSnapshot in p0!!.children) {

                       messages = snap.getValue(MessageObject::class.java)!!

                       if(messages.receiver == receiverUid && messages.sender == senderUid
                               || messages.receiver == senderUid && messages.sender == receiverUid){

                           array.add(messages)
                           mMessages.postValue(array)


                       }
                   }

            }

            override fun onCancelled(p0: DatabaseError?) {
                TODO("Not yet implemented")
            }

        })
        return mMessages
    }
}