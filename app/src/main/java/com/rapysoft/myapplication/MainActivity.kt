package com.rapysoft.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rapysoft.myapplication.activities.ViewMovieActivity
import com.rapysoft.myapplication.adapters.AdapterMovie
import com.rapysoft.myapplication.adapters.ItemClickListener
import com.rapysoft.myapplication.api.ApiAdapter
import com.rapysoft.myapplication.constant.AppConstant
import com.rapysoft.myapplication.constant.AppConstant.constModelMovie
import com.rapysoft.myapplication.models.ModelMovie
import com.rapysoft.myapplication.models.ModelResponseMovie
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var recyclerAdapter: AdapterMovie? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Populares"
        loadControls()
    }

    private fun loadControls() {

        val gridLayoutManager = GridLayoutManager(this, 1)
        recyclerViewMovie.setHasFixedSize(true)
        recyclerViewMovie.layoutManager = gridLayoutManager

        getApiMovies()
    }

    private fun getApiMovies() {
        try {

            val call = ApiAdapter.apiService?.getMoviePopular(
                AppConstant.constApiKey, "es-ES", "1"
            )
            call?.enqueue(object : Callback<ModelResponseMovie> {
                override fun onResponse(
                    call: Call<ModelResponseMovie>?,
                    response: Response<ModelResponseMovie>?
                ) {
                    val isSuccessful = response?.isSuccessful ?: false

                    if (isSuccessful) {

                        val data = response?.body()?.results

                        if (data != null) {
                            recyclerAdapter = AdapterMovie(data, object : ItemClickListener {
                                override fun onClick(view: View, i: Int) {
                                    onClickMovie(data[i])
                                }
                            })

                            recyclerViewMovie.adapter = recyclerAdapter
                        }
                    }
                }

                override fun onFailure(call: Call<ModelResponseMovie>?, t: Throwable?) {
                    println("Failed")

                    t?.printStackTrace()
                }
            })

        } catch (ex: Exception) {
            println(ex.message)
            ex.printStackTrace()
        }
    }


    private fun onClickMovie(modelMovie: ModelMovie) {

        val intent=Intent().setClass(this,ViewMovieActivity::class.java)
       intent.putExtra(constModelMovie,modelMovie)
        startActivity(intent)

    }
}