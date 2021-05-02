package com.project.userapp.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Rahul khurana on 2/5/21.
 */
data class UserResponse(var isSuccess : Boolean = false, val users : List<User>?, var message : String ?) {
}