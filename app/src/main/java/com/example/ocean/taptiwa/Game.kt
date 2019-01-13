package com.example.ocean.taptiwa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_game.*


class Game : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        stopWatch()
    } // end of oncreate

    fun stopWatch() {
        object : CountDownTimer(21000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timer_view.setText((millisUntilFinished / 1000).toString())
            }

            override fun onFinish() {
                timer_view.setText("Time Up!")
            }
        }.start()
    }
} // end of class
