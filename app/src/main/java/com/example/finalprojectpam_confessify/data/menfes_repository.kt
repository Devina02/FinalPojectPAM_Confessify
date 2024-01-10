//package com.example.finalprojectpam_confessify.data
//
//import android.content.ContentValues
//import android.util.Log
//import com.example.finalprojectpam_confessify.model.Menfess
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.Query
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOn
//import kotlinx.coroutines.tasks.await
//
//interface MenfessRepository {
//    fun getAll(): Flow<List<Menfess>>
//    suspend fun save(menfess: Menfess): String
//    suspend fun update(menfess: Menfess)
//    suspend fun delete(menfessId: String)
//    fun getMenfessById(menfessId: String): Flow<Menfess>
//}
//
//class MenfessRepositoryImpl(private val firestore: FirebaseFirestore) : MenfessRepository {
//    override fun getAll(): Flow<List<Menfess>> = flow {
//        val snapshot = firestore.collection("Menfess")
//            .orderBy("nama", Query.Direction.ASCENDING)
//            .get()
//            .await()
//        val menfess = snapshot.toObjects(Menfess::class.java)
//        emit(menfess)
//    }.flowOn(Dispatchers.IO)
//
//
//    override suspend fun save(menfess: Menfess): String {
//        return try {
//            val documentReference = firestore.collection("Menfess").add(menfess).await()
//            // Update the Menfess with the Firestore-generated DocumentReference
//            firestore.collection("Menfess").document(documentReference.id)
//                .set(menfess.copy(id = documentReference.id))
//            "Berhasil + ${documentReference.id}"
//        } catch (e: Exception) {
//            Log.w(ContentValues.TAG, "Error adding document", e)
//            "Gagal $e"
//        }
//    }
//
//    override suspend fun update(menfess: Menfess) {
//        firestore.collection("Menfess").document(menfess.id).set(menfess).await()
//    }
//
//    override suspend fun delete(menfessId: String) {
//        firestore.collection("Menfess").document(menfessId).delete().await()
//    }
//
//    override fun getMenfessById(menfessId: String): Flow<Menfess> {
//        return flow {
//            val snapshot = firestore.collection("Menfess").document(menfessId).get().await()
//            val menfess = snapshot.toObject(Menfess::class.java)
//            emit(menfess!!)
//        }.flowOn(Dispatchers.IO)
//    }
//
//}
