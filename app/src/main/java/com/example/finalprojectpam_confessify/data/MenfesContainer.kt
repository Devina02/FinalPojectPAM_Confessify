//package com.example.finalprojectpam_confessify.data
//
//import com.google.firebase.firestore.FirebaseFirestore
//
//interface AppContainer {
//    val menfessRepository: MenfessRepository
//}
//
//class MenfesContainer : AppContainer{
//    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
//
//    override val menfessRepository: MenfessRepository by lazy {
//        MenfessRepositoryImpl(firestore)
//    }
//}