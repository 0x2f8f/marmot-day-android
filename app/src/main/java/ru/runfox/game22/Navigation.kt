package ru.runfox.game22

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.runfox.game22.ui.main.FailFragment
import ru.runfox.game22.ui.main.FinishFragment
import ru.runfox.game22.ui.main.MainFragment
import ru.runfox.game22.ui.main.QuestionFragment

object Navigation {
    fun main(fragmentManager: FragmentManager) {
        MyApplication.initHelpButtons()
        replace(
            fragmentManager = fragmentManager,
            fragmentClass = MainFragment::class.java
        )
    }

    fun finish(fragmentManager: FragmentManager) = replace(
        fragmentManager = fragmentManager,
        fragmentClass = FinishFragment::class.java
    )

    fun question(fragmentManager: FragmentManager, questionId: Int) = replace(
        fragmentManager = fragmentManager,
        fragmentClass = QuestionFragment::class.java,
        args = QuestionFragment.bundle(questionId = questionId)
    )

    fun fail(fragmentManager: FragmentManager, answerTitle: String, questionId: Int) = replace(
        fragmentManager = fragmentManager,
        fragmentClass = FailFragment::class.java,
        args = FailFragment.bundle(answerTitle = answerTitle, questionId = questionId)
    )

    private fun replace(
        fragmentManager: FragmentManager,
        fragmentClass: Class<out Fragment?>,
        args: Bundle? = null,
    ) {
        fragmentManager
            .beginTransaction()
            .replace(R.id.container, fragmentClass, args)
            .commit()
    }
}