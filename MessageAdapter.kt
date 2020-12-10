package com.example.datingapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datingapp.R
import com.example.datingapp.model.MessageObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*


class MessageAdapter(private var messageList: ArrayList<MessageObject>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>()
{
    
    var fUser: FirebaseUser? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val rcv: MessageViewHolder
        if (viewType == MSG_TYPE_RIGHT) {
            val layoutView: View =
                LayoutInflater.from(parent.context).inflate(R.layout.message_right, null, false)
            val lp = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutView.layoutParams = lp
            rcv = MessageViewHolder(layoutView)
        }
        else
        {
            val layoutView: View =
                LayoutInflater.from(parent.context).inflate(R.layout.message_left, null, false)
            val lp = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

            layoutView.layoutParams = lp
            rcv = MessageViewHolder(layoutView)
        }
        return rcv
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.mMessage.text = messageList[position].message

    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    inner class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        var mMessage: TextView = view.findViewById(R.id.message)

    }

    override fun getItemViewType(position: Int): Int {
        fUser = FirebaseAuth.getInstance().currentUser
        return if (messageList[position].receiver == fUser!!.uid)
                    MSG_TYPE_RIGHT
        else
            MSG_TYPE_LEFT
    }

    companion object {
        const val MSG_TYPE_RIGHT = 1
        const val MSG_TYPE_LEFT = 0
    }

}