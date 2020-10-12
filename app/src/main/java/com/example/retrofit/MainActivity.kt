package com.example.retrofit

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tvResult = findViewById(R.id.text_view_result) as TextView
        //Creating retrofit Instance
        val retrofit:Retrofit=Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Casting or converting interface to retrofit object
        var apiLisner:APIListner=retrofit.create(APIListner::class.java)
        //Calling the reference of the API
        var call: Call<List<User>> =apiLisner.getPosts()

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val posts: List<User>? = response.body()
                for (post in posts!!) {
                    var content = ""
                    content += """
                        ID: ${post.id.toString()}
                        
                        """.trimIndent()
                    content += """
                        User ID: ${post.userID.toString()}
                        
                        """.trimIndent()
//                    content += """
//                        Text: ${post.text.toString()}
//
//                        """.trimIndent()
                    content += """
                        Title: ${post.title.toString()}
                        
                        """.trimIndent()
                    content += """
                       }
                        
                        
                        """.trimIndent()
                    tvResult.append(content)
                }

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
            }

        })



    }
}