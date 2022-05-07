package com.example.game

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var width = 0
    private var height = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        width = resources.displayMetrics.widthPixels - 100
        height = resources.displayMetrics.heightPixels - 250

        textView.text = "$width $height"

        imageButtonUp.setOnTouchListener { v, event ->
            when {
                isBoom() -> boomBomb()
                imageViewAndroid.y > 0 -> imageViewAndroid.y-=10
            }
            false
        }

        imageButtonDown.setOnTouchListener { v, event ->
            when {
                isBoom() -> boomBomb()
                imageViewAndroid.y < height -> imageViewAndroid.y+=10
            }
            false
        }

        imageButtonLeft.setOnTouchListener { v, event ->
            when {
                isBoom() -> boomBomb()
                imageViewAndroid.x > 0 -> imageViewAndroid.x-=10
            }
            false
        }

        imageButtonRight.setOnTouchListener { v, event ->
            when {
                isBoom() -> boomBomb()
                imageViewAndroid.x < width -> imageViewAndroid.x+=10
            }
            false
        }

        buttonBomb.setOnClickListener {
            imageViewBomb.scaleX = 1F
            imageViewBomb.scaleY = 1F
            imageViewBomb.setImageResource(R.drawable.bomb)
            imageViewBomb.x = Random.nextInt(width).toFloat()
            imageViewBomb.y = Random.nextInt(height).toFloat()
        }
    }

    private fun isBoom(): Boolean {
        return imageViewAndroid.x in imageViewBomb.x-70..imageViewBomb.x+70
                && imageViewAndroid.y in imageViewBomb.y-70..imageViewBomb.y+70
    }

    private fun boomBomb() {
        imageViewBomb.setImageResource(R.drawable.boom)
        imageViewBomb.scaleX = 6F
        imageViewBomb.scaleY = 6F
    }
}