package com.example.odyssey1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    //查看祭品
    @GET("/api/item")
    fun showGodItem(): Call<MutableList<GodItemResponse>>

    //進行祭拜
    @POST("/api/worship")
    fun toWorship(@Body worshipRequest: WorshipRequest): Call<WorshipResponse>

    @GET("/api/worship")
    fun toShowWorship(): Call<MutableList<ItemDetail>>
}