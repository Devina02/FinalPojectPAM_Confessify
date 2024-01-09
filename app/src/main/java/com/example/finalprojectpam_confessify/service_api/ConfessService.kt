package com.example.finalprojectpam_confessify.service_api

import com.example.finalprojectpam_confessify.data.ConfessData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ConfessService {
    @Headers(
        "Accept: application/json"
    )

    @GET("/Confess")
    suspend fun getConfess(): List<ConfessData>

    @POST("Confess")
    suspend fun insertConfess(@Body Confess: ConfessData)

    @PUT("Confess/{id}")
    suspend fun updateConfess(@Query("id") @Body Confess: ConfessData)
}