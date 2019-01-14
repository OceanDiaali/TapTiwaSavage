package com.example.ocean.taptiwa

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*


class Game : AppCompatActivity() {

    var imageArray: ArrayList<ImageView> = ArrayList<ImageView>()
    var scoreCount: Int = 0
    var hiScore: Int = 0

    var handler: Handler = Handler()
    var runnable: Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        imageArray = arrayListOf( tiwa1, tiwa2, tiwa3, tiwa4, tiwa5, tiwa6, tiwa7, tiwa8, tiwa9)

        for (image in imageArray) {
            image.setOnClickListener { playerScore() }
        }

        stopWatch()
        hideImage()


    } // end of oncreate

    fun hideImage() {

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }



                var rnd = (0..8).random()
                imageArray[rnd].visibility = View.VISIBLE

                handler.postDelayed(runnable, 2000)
            }
        }
            handler.post(runnable)
    }

    fun stopWatch() {
        object : CountDownTimer(21000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timer_view.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                timer_view.text = "Time!"
                if (scoreCount > hiScore) {
                    hiScore = scoreCount
                    hiscore_view.text = "hiScore:$hiScore"
                }
                handler.removeCallbacks(runnable) // to stop taps ability
                // remove images, so no more tapping
                timer_view.text = "Game Over!"
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                } //for loop
            }
        }.start()
    } // end of stop watch

    fun playerScore() {
        scoreCount++
        score_view.text = "score:$scoreCount"
    }

} // end of class
