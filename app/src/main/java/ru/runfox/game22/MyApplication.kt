package ru.runfox.game22

import android.app.Application

class MyApplication: Application() {
    companion object {
        const val BASE_TIMER_TIME = 59

        var helpButtonFiftyUsed: Boolean = false;
        var helpButtonCallUsed: Boolean = false;
        var helpButtonPeopleUsed: Boolean = false;
        var helpButtonChanceUsed: Boolean = false;

        var timerRun = false
        var timerTime = BASE_TIMER_TIME

        var correctAnswerTitle: String = ""
        var currentQuestion: Question? = null

        fun initHelpButtons() {
            helpButtonFiftyUsed = false
            helpButtonCallUsed = false
            helpButtonPeopleUsed = false
            helpButtonChanceUsed = false
        }
    }
}