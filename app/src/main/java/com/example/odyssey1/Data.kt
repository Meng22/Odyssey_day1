package com.example.odyssey1

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


//神之物品adapter顯示格式
data class GodItem(val name: String, val image: String, var isWorshiped : Boolean = false)

//查看神的物品
data class GodItemResponse(
    val id: Int,
    val item: String,
    val image: String,
    val created_at: String,
    val updated_at: String
)

//進行祭拜
data class WorshipRequest(
    val item : ArrayList<String>,
    val name: String
)


data class WorshipResponse(
//    @Expose
//    @SerializedName("data")
//    val data: MutableList<ItemDetail>
    val item: List<String>, val name: String, val time: String
)

data class Record(val name: String, val item: String, val time: String)

data class ItemDetail(
    @Expose
    @SerializedName("created_at")
    val created_at: String,
    @Expose
    @SerializedName("god")
    val god: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("item")
    val item: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("updated_at")
    val updated_at: String
)