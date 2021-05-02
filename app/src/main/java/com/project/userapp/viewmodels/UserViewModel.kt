package com.project.userapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.userapp.data.User
import com.project.userapp.data.UserResponse
import com.project.userapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private var userLiveData = MutableLiveData<UserResponse>()

    init{
        viewModelScope.launch {
            try{
                userLiveData.value = repository.fetchUserResult()
            }
            catch (e : Exception){
               userLiveData.value = UserResponse(users = null,message = e.message?:"Something went wrong");
            }
        }
    }

    fun getUserLiveData() = userLiveData

}