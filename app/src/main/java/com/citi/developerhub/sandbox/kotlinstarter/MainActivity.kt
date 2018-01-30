package com.citi.developerhub.sandbox.kotlinstarter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import khttp.get
import org.jetbrains.anko.UI
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.util.*

class MainActivity : Activity() {

    var c: Context = this@MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mButton = findViewById<Button>(R.id.button)
        val mImageView= findViewById<ImageView>(R.id.imageView)
        Glide.with(this).asGif().load("https://media3.giphy.com/media/xTiTnKFUiRf14KzhQs/giphy.gif").into(mImageView)
        mButton.setOnClickListener {
            doAsync {
                mImageView.setImageURI(null)
                val r = get("https://api.giphy.com/v1/gifs/random?api_key=rDzyKDXKLq4yjfMl4tbe7EXCBM8v110c&tag=&rating=G")
                val data = r.jsonObject.get("data") as JSONObject
                Log.d("App",data.get("image_original_url").toString())
                uiThread {
                    Glide.with(c)
                        .asGif()
                        .load(data.get("image_original_url").toString())
                        .into(mImageView)
                }

            }

        }
    }
}
