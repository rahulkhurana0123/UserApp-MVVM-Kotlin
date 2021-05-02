
package com.google.samples.apps.sunflower.di

import android.content.Context
import com.project.userapp.api.NetworkProvider
import com.project.userapp.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideUserService(): UserService {
        return UserService.create()
    }

    @Singleton
    @Provides
    fun provideNetWorkProvider(@ApplicationContext applicationContext: Context) = NetworkProvider(appContext = applicationContext)

}
