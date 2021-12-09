package com.rapysoft.myapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rapysoft.myapplication.R
import com.rapysoft.myapplication.constant.AppConstant
import com.rapysoft.myapplication.constant.AppConstant.constModelMovie
import com.rapysoft.myapplication.models.ModelMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_view_movie.*

class ViewMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)
        loadControls()
    }

    private fun loadControls() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val model = intent.getSerializableExtra(constModelMovie) as ModelMovie


        val url = "${AppConstant.constUrlImage}${model.posterPath}"

        val overview = model.overview

        val modelTitle = model.title


        title = modelTitle

        textViewOverview.text = overview

        Picasso.get()
            .load(url)
            .resize(500, 500)
            .centerInside()
            .into(imageViewMovieSelect)

        println(model.title)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}