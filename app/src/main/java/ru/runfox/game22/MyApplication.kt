package ru.runfox.game22

import android.app.Application

class MyApplication: Application() {
    companion object {
        var helpButtonFiftyUsed: Boolean = false;
        var helpButtonCallUsed: Boolean = false;

        fun initHelpButtons() {
            helpButtonFiftyUsed = false
            helpButtonCallUsed = false
        }
    }
}