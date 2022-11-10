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

        val answerButtons = mapOf(
            0 to binding.answer1,
            1 to binding.answer2,
            2 to binding.answer3,
            3 to binding.answer4
        )

        answerButtons.forEach { i, button ->
            initButton(button = button, answer = question.answers[i])
        }

        initHelpButtons(binding, answerButtons, question.answers)
    }

    private fun initHelpButtons(binding: FragmentQuestionBinding, answerButtons: Map<Int, Button>, answers: List<Answer>) {
        if (MyApplication.helpButtonPeopleUsed) {
            binding.peopleHelpButton.setImageResource(R.drawable.help_people_disable)
        }
        if (MyApplication.helpButtonFiftyUsed) {
            binding.fiftyHelpButton.setImageResource(R.drawable.fifty_small_disable)
        }
        if (MyApplication.helpButtonCallUsed) {
            binding.callHelpButton.setImageResource(R.drawable.help_call_disable)
        }
        if (MyApplication.helpButtonChanceUsed) {
            binding.chanceHelpButton.setImageResource(R.drawable.help_chance_disable)
        }

        binding.chanceHelpButton.setOnClickListener {
            Snackbar.make(
                binding.root,
                binding.chanceHelpButton.contentDescription,
                Snackbar.LENGTH_LONG,
            ).show()
        }

        binding.peopleHelpButton.setOnClickListener {
            if (!MyApplication.helpButtonPeopleUsed) {
                MyApplication.helpButtonPeopleUsed = true
                binding.peopleHelpButton.setImageResource(R.drawable.help_people_disable)
            }
        }

        binding.fiftyHelpButton.setOnClickListener {
            if (!MyApplication.helpButtonFiftyUsed) {
                MyApplication.helpButtonFiftyUsed = true
                binding.fiftyHelpButton.setImageResource(R.drawable.fifty_small_disable)

                var i = 0
                var j = 0
                while(i<4) {
                    if (answers[i].questionId == null && j<2) {
                        answerButtons[i]!!.visibility = View.GONE
                        j++
                    }
                    i++
                }
            }
        }

        binding.callHelpButton.setOnClickListener {
            if (!MyApplication.helpButtonCallUsed) {
                MyApplication.helpButtonCallUsed = true
                binding.callHelpButton.setImageResource(R.drawable.help_call_disable)
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