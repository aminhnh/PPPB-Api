package com.example.pppbapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pppbapi.databinding.ActivityMainBinding
import com.example.pppbapi.model.Data
import com.example.pppbapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getAllOrganizations()
        var adapterMember : MemberAdapter = MemberAdapter(emptyList()) {}


        response.enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {

                if (response.isSuccessful){

                    val dataList : List<Data>? = response.body()

                    if (dataList != null){
                        adapterMember = MemberAdapter(dataList) {
                            data ->
                            val bottomSheet = BottomSheetFragment.newInstance(name = data.name, type = data.type, role = data.role, description = data.description, image = data.image, twitter = data.twitter,github = data.github, website = data.website)
                            bottomSheet.show(supportFragmentManager, "ModalBottomSheet")
                        }

                        with(binding){
                            rvMembers.apply {
                                adapter = adapterMember
                                layoutManager = LinearLayoutManager(this@MainActivity)
                            }
                        }
                    }

                    Log.e("APIe", "Success - ArraySize: ${dataList!!.size}")
                } else {
                    Log.e("APIe", "Error: ${response.code()} - ${response.message()}")
                }

            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "A connection error occurred", Toast.LENGTH_SHORT).show()
                Log.e("APIe", "Error: ${t.message}")
            }

        })

        with(binding){
//            rvMembers.apply {
//                adapter = adapterMember
//                layoutManager = LinearLayoutManager(this@MainActivity)
//            }
        }

    }
}