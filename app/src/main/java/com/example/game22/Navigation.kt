package com.example.game22

import androidx.fragment.app.FragmentManager
import com.example.game22.ui.main.FinishFragment
import com.example.game22.ui.main.MainFragment
import com.example.game22.ui.main.QuestionFragment

object Navigation {
    fun main(fragmentManager: FragmentManager) {
        fragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
    }

    fun finish(fragmentManager: FragmentManager) {
        fragmentManager
            .beginTransaction()
            .replace(R.id.container, FinishFragment.newInstance())
            .commitNow()
    }

    fun question(fragmentManager: FragmentManager, questionId: Int) {
        fragmentManager
            .beginTransaction()
            .replace(
                R.id.container, QuestionFragment::class.java,
                QuestionFragment.bundle(questionId = questionId)
            )
            .commit()
    }
}