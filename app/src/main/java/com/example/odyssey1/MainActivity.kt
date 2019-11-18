package com.example.odyssey1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val recordAdapter = RecordAdapter()
    private val recordList: MutableList<Record> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_apollo.setOnClickListener {
            val intent = Intent(this, WorshipActivity::class.java)
            startActivityForResult(intent, 1)
        }
        API.apiInterface.toShowWorship().enqueue(object: Callback<MutableList<ItemDetail>>{
            override fun onFailure(call: Call<MutableList<ItemDetail>>, t: Throwable) {
                println("=============$t")
            }

            override fun onResponse(call: Call<MutableList<ItemDetail>>, response: Response<MutableList<ItemDetail>>) {
                if (response.isSuccessful){
                    val responsebody = response.body()

                    for(i in 0 until responsebody!!.size){
                        recordList.add(Record(responsebody[i].name, responsebody[i].item, responsebody[i].created_at))
                    }
                    rv_record.layoutManager = LinearLayoutManager(this@MainActivity)
                    rv_record.adapter = recordAdapter
                    recordAdapter.update(recordList)
                }
            }
        })
    }
}
