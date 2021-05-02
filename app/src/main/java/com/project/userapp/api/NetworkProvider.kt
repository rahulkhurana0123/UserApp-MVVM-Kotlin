package com.project.userapp.api

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext


/**
 * Created by Rahul khurana on 2/5/21.
 */
 open class NetworkProvider constructor(@ApplicationContext var appContext: Context) {

     fun isNetworkConnected(): Boolean {
        val cm =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm?.activeNetworkInfo?:null != null && cm.activeNetworkInfo?.isConnected?:false
    }
}