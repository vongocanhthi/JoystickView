package com.at.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), JoystickView.OnMoveListener {
    private var TAG: String = "zzz"
    private var isMove: Boolean = false
    private var index: Int = 0
//    private var mThread: Thread? = Thread(this)


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        joystick.setOnMoveListener(this, 80)
    }

    @SuppressLint("SetTextI18n")
    private fun showText(text: String) {

        index++
        txtDirection.text = "$text $index"

//        try {
//            Thread.sleep(1000)
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
    }

    override fun onMove(angle: Int, strength: Int) {

        Log.d(TAG, "angle: " + angle)
        Log.d(TAG, "strength: " + strength)

        if (strength == -1) {
//            index = 0
//            txtDirection.text = "center"
        }
        if (isMove && strength > 15) {

            when (angle) {
                in 0..44,
                in 316..360 -> {
                    Log.d(TAG, "right: ")
                    showText("right")

                }
                in 45..135 -> {
                    Log.d(TAG, "top: ")
                    showText("top")

                }
                in 136..225 -> {
                    Log.d(TAG, "left: ")
                    showText("left")
                }
                in 226..315 -> {
                    Log.d(TAG, "bottom: ")
                    showText("bottom")
                }
            }
        }
    }

    override fun onActionMove(angle: Int, strength: Int) {
        if (strength > 15) {

            Log.d(TAG, "onActionMove: ")
            isMove = true


        }
    }

    override fun onActionDown() {

        isMove = false

        Log.d(TAG, "onActionDown: ")
    }

    override fun onActionUp() {
        if (!isMove) {
            Log.d(TAG, "onActionUp: ")
            joystick.alpha = 0.5f
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    joystick.alpha = 1f
                },
                100
            )
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
        } else {
            isMove = false
            Log.d(TAG, "onActionUp: " + isMove)
        }

    }


}