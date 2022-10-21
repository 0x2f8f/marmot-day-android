package com.example.game22.ui.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.game22.R

class QuestionFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textQuestion = requireView().findViewById<TextView>(R.id.textQuestion)
        ObjectAnimator
            .ofFloat(
                textQuestion,
                "alpha",
                0F,
            )
            .setDuration(2_000)
            .start()


        val buttonBack = requireView().findViewById<Button>(R.id.buttonBackQuestion)
        buttonBack.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }
}