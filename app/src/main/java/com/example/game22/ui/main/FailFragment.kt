package com.example.game22.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.game22.Navigation
import com.example.game22.R

class FailFragment : Fragment() {
    companion object {
        fun newInstance() = FailFragment()

        fun bundle(answerTitle: String, questionId: Int): Bundle {
            val bundle = Bundle()
            bundle.putString("ANSWER_TITLE", answerTitle)
            bundle.putInt("QUESTION_ID", questionId)

            return bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_fail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedAnswer = requireView().findViewById<TextView>(R.id.selectedAnswer)
        selectedAnswer.text = requireArguments().getString("ANSWER_TITLE")

        val buttonRetry = requireView().findViewById<Button>(R.id.buttonRetry)
        buttonRetry.setOnClickListener {
            Navigation.question(parentFragmentManager, requireArguments().getInt("QUESTION_ID"))
        }

        val buttonRestart = requireView().findViewById<Button>(R.id.buttonRestartByFail)
        buttonRestart.setOnClickListener {
            Navigation.main(parentFragmentManager)
        }
    }
}