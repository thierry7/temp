package com.example.datingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datingapp.model.ChattingRepository
import com.example.datingapp.model.MessageObject
import com.example.datingapp.model.Repository
import com.example.datingapp.model.User

class ChattingViewModel( application: Application): AndroidViewModel(application)
{

    private val authAppRepository: ChattingRepository by lazy { ChattingRepository(application) }


    fun getUserSender(uid: String): MutableLiveData<User>{
        return authAppRepository.getSender(uid)
    }

    fun sendMessage(sender: String, receiver: String, message: String){
        authAppRepository.sendMessage(sender, receiver, message)
    }

    fun displayMessages(senderUid:String, receiverUid: String): MutableLiveData<ArrayList<MessageObject>>{
        return authAppRepository.readMessages(senderUid, receiverUid)
    }

}