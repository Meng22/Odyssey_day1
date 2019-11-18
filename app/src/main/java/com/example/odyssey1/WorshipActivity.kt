package com.example.odyssey1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_worship.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorshipActivity : AppCompatActivity() {
    private val apolloAdapter = ApolloAdapter()
    private val godItemList: MutableList<GodItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worship)

        rv_apollo.layoutManager = LinearLayoutManager(this)
        rv_apollo.adapter = apolloAdapter

        API.apiInterface.showGodItem().enqueue(object : Callback<MutableList<GodItemResponse>>{
            override fun onFailure(call: Call<MutableList<GodItemResponse>>, t: Throwable) {
                println("================$t")
            }
            override fun onResponse(
                call: Call<MutableList<GodItemResponse>>, response: Response<MutableList<GodItemResponse>>) {
                val responseList = response.body()
                println("================$responseList")
                for (i in 0 until responseList!!.size){
                    godItemList.add(GodItem(responseList[i].item, responseList[i].image, false))
                }
                println("================$godItemList")

                apolloAdapter.update(godItemList)
                apolloAdapter.setToClick(object : ApolloAdapter.ItemClickListener{
                    override fun toClick(item: GodItem) {
                        item.isWorshiped = !item.isWorshiped
                    }
                })

            }

        })


        btn_worship.setOnClickListener {
            if (ed_username.text.isNullOrEmpty()){
                Toast.makeText(this@WorshipActivity, "請輸入祭拜者姓名", Toast.LENGTH_SHORT).show()
            }
            else{
                val selectedList = godItemList.filter { it.isWorshiped == true }
                val toWorshipList: ArrayList<String> = arrayListOf()
                for (i in 0 until selectedList.size){
                    toWorshipList.add(selectedList[i].name)
                }
                println("===============$toWorshipList")

                val name = ed_username.text.toString()
                API.apiInterface.toWorship(WorshipRequest(toWorshipList, name)).enqueue(object : Callback<WorshipResponse>{
                    override fun onFailure(call: Call<WorshipResponse>, t: Throwable) {
                        println("================$t")
                    }
                    override fun onResponse(call: Call<WorshipResponse>, response: Response<WorshipResponse>) {
                        val responsebody = response.body()
                        println("================$responsebody")
                    }
                })

            }
        }


    }
}
