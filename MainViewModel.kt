package com.example.datingapp.viewmodel

import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.datingapp.model.Repository
import com.example.datingapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase


class LoginRegisterViewModel(application: Application) :
    AndroidViewModel(application) {

    private val authAppRepository: Repository by lazy { Repository(application) }

    val userLiveData: MutableLiveData<FirebaseUser> = authAppRepository.getUserLiveData()
    var logOutLiveData: MutableLiveData<Boolean> = authAppRepository.getLoggedOutLiveData()
    val currenUserLiveData:MutableLiveData<User> = authAppRepository.getUser()
    var listOfUsers: MutableLiveData<ArrayList<User>> = authAppRepository.getListsUsers()

    var selectedUser:MutableLiveData<ArrayList<User>> = authAppRepository.getpassUser()

    @RequiresApi(Build.VERSION_CODES.P)
    fun login(email: String?, password: String?) {
        authAppRepository.login(email, password)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun register(name: String?, email: String?, password: String?, sex:String?) {
        authAppRepository.register(name, email, password, sex)

    }
    fun logout(){
        authAppRepository.logOut()

    }
    fun updateUserInfo(mgUr: String?, name: String?, sex: String?, age: String?, bio: String? ){
        authAppRepository.updateUserInfo(mgUr!!, name!!, sex!!, age!!, bio!!)

    }
    fun getImageUploaded(file: Uri) {
        authAppRepository.uploadImage(file)

    }
    fun userLocation(){
        authAppRepository.userLocation()
    }
    fun updateFlag():Boolean{
        return authAppRepository.updateFlag()
    }
    fun passUser(user:User){
        authAppRepository.passUser(user)
    }




}