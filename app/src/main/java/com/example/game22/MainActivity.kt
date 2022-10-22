package com.example.game22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.game22.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            Navigation.main(supportFragmentManager)
        }
    }
}