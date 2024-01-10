package com.example.finalprojectpam_confessify.repository

import android.content.Context
import com.example.finalprojectpam_confessify.data.DatabaseConfess

interface AppContainer {
    val repositoryConfess: RepositoryConfess
}

class ContainerDataApp(private val context: Context): AppContainer{
    override val repositoryConfess: RepositoryConfess by lazy {
        OfflineRepo(DatabaseConfess.getDatabase(context).confessDao())
    }
}