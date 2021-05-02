package com.project.userapp.repository

import com.project.userapp.api.NetworkProvider
import com.project.userapp.api.UserService
import com.project.userapp.data.UserResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserService,
    private var networkProvider: NetworkProvider
) {

    suspend fun fetchUserResult() = if (networkProvider.isNetworkConnected()) {
        var users = service.fetchUser()
        var userResponse = UserResponse(true, users, "Success");
        userResponse.isSuccess = true
        userResponse
    } else {
        UserResponse(false, null, "Please check network connection")
    }
}