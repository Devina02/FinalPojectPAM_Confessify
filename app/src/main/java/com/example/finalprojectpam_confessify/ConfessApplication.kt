package com.example.finalprojectpam_confessify

import android.app.Application
import com.example.finalprojectpam_confessify.repository.AppContainer
import com.example.finalprojectpam_confessify.repository.ContainerDataApp

class ConfessApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this
        )
    }
}