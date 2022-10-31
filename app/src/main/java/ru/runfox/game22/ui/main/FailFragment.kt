package ru.runfox.game22.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.runfox.game22.Navigation
import ru.runfox.game22.Quest
import ru.runfox.game22.databinding.FragmentFailBinding
import com.google.android.material.snackbar.Snackbar

class FailFragment : Fragment() {
    lateinit var binding: FragmentFailBinding

    companion object {
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
        binding = FragmentFailBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionId = requireArguments().getInt("QUESTION_ID")
        val question = Quest.questions.first { question -> question.id == questionId }

        binding.selectedAnswer.text = requireArguments().getString("ANSWER_TITLE")

        binding.buttonRetry.setOnClickListener {
            Navigation.question(parentFragmentManager, question.id)
        }

        binding.buttonHelpHint.setOnClickListener {
            Snackbar.make(
                binding.root,
                question.hint,
                Snackbar.LENGTH_LONG
            ).show()
        }

        binding.buttonRestartByFail.setOnClickListener {
            Navigation.main(parentFragmentManager)
        }
    }
}