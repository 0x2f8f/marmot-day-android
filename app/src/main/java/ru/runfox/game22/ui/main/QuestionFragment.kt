package ru.runfox.game22.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.runfox.game22.*
import ru.runfox.game22.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {
    lateinit var binding: FragmentQuestionBinding
    var buttonPressed: Boolean = false

    companion object {
        fun bundle(questionId: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt("QUESTION_ID", questionId)

            return bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionId = requireArguments().getInt("QUESTION_ID")
        val question = Quest.questions.first { question -> question.id == questionId }

        binding.textQuestion.text = question.title
        binding.textReward.text =
            "Вопрос ${question.id} на \$${String.format("%,d", question.reward)}"

        listOf(
            binding.answer1,
            binding.answer2,
            binding.answer3,
            binding.answer4
        ).forEachIndexed { i, button ->
            initButton(button = button, answer = question.answers[i])
        }

        initHelpButtons(binding)
    }

    private fun initHelpButtons(binding: FragmentQuestionBinding) {
        if (MyApplication.helpButtonFiftyUsed) {
            binding.fiftyHelpButton.setImageResource(R.drawable.fifty_small_disable)
        }

        binding.chanceHelpButton.setOnClickListener {
            Snackbar.make(
                binding.root,
                binding.chanceHelpButton.contentDescription,
                Snackbar.LENGTH_LONG,
            ).show()
        }

        binding.fiftyHelpButton.setOnClickListener {
            if (!MyApplication.helpButtonFiftyUsed) {
                MyApplication.helpButtonFiftyUsed = true
                binding.fiftyHelpButton.setImageResource(R.drawable.fifty_small_disable)
            }
        }
    }

    private fun initButton(button: Button, answer: Answer) {
        button.text = answer.title
        button.setOnClickListener {
            if (!buttonPressed) {
                buttonPressed = true
                button.isEnabled = false
                button.isSelected = true
                Handler(Looper.getMainLooper()).postDelayed({
                    processAnswer(answer, button)
                }, 1000)
            }
        }
    }

    private fun processAnswer(answer: Answer, button: Button) {
        if (answer.questionId == null) {
            button.setBackgroundResource(R.drawable.button_answer_wrong)

            Handler(Looper.getMainLooper()).postDelayed({
                Navigation.fail(
                    parentFragmentManager,
                    answer.title,
                    requireArguments().getInt("QUESTION_ID")
                )
            }, 1000)

            return
        }

        if (answer.questionId == -1) {
            button.setBackgroundResource(R.drawable.button_answer_success)
            Handler(Looper.getMainLooper()).postDelayed({
                Navigation.finish(parentFragmentManager)
            }, 1000)

            return
        }

        button.setBackgroundResource(R.drawable.button_answer_success)

        Handler(Looper.getMainLooper()).postDelayed({
            Navigation.question(
                parentFragmentManager,
                answer.questionId
            )
        }, 1000)

    }
}