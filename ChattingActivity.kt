package com.example.datingapp.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.datingapp.R
import com.example.datingapp.model.MessageObject
import com.example.datingapp.model.User
import com.example.datingapp.viewmodel.ChattingViewModel
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView

class ChattingActivity : AppCompatActivity() {

    lateinit var profile: CircleImageView
    lateinit var username: TextView

    lateinit var imagebtn: ImageButton
    lateinit var messageText: EditText

    lateinit var chattingViewModel: ChattingViewModel

    lateinit var recyclerView: RecyclerView


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chattingViewModel = ViewModelProviders.of(this).get(ChattingViewModel::class.java)
        recyclerView = findViewById(R.id.rv_chat_container)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this)




        var toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.tollbar)
        profile = findViewById(R.id.tb_profil_image)
        username = findViewById(R.id.user_name)
        imagebtn = findViewById(R.id.btn_send_message)
        messageText = findViewById(R.id.it_input_message)

        supportActionBar?.title = ""
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()

        }
        var intent = intent
        var userId: String = intent.getStringExtra("The_user")!!


        print("#####################"+userId)


        var text: String = messageText.text.toString()
        var currentUser = FirebaseAuth.getInstance().currentUser



        imagebtn.setOnClickListener {
            if (text != " ") {
                chattingViewModel.sendMessage(currentUser?.uid.toString(), userId, messageText.text.toString())

            } else {
                Toast.makeText(applicationContext, "The message Cannot be empty", Toast.LENGTH_LONG).show()
            }
            messageText.text.clear()


        }
        chattingViewModel.displayMessages(currentUser?.uid!!, userId).observe(this@ChattingActivity,
                Observer<ArrayList<MessageObject>> {

                    recyclerView.adapter = MessageAdapter(it)
                    (recyclerView.adapter as MessageAdapter).notifyDataSetChanged()
                })





    }
}