package ru.runfox.game22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = Quest.title
        if (savedInstanceState == null) {
            Navigation.main(supportFragmentManager)
        }

    }
}