package ru.runfox.game22.ui.main

//import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.runfox.game22.Answer
import ru.runfox.game22.Navigation
import ru.runfox.game22.Quest
import ru.runfox.game22.databinding.FragmentQuestionBinding
import com.google.android.material.snackbar.Snackbar

class QuestionFragment : Fragment() {
    lateinit var binding: FragmentQuestionBinding

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
        binding.textReward.text = "Вопрос ${question.id} на \$${String.format("%,d", question.reward)}"

        listOf(
            binding.answer1,
            binding.answer2,
            binding.answer3,
            binding.answer4
        ).forEachIndexed { i, button ->
            initButton(button = button, answer = question.answers[i])
        }

        binding.buttonHint.setOnClickListener {
            Snackbar.make(
                binding.root,
                question.hint,
                Snackbar.LENGTH_LONG,
            ).show()
        }
    }

    private fun initButton(button: Button, answer: Answer) {
        button.text = answer.title
        button.setOnClickListener { processAnswer(answer) }
    }

    private fun processAnswer(answer: Answer) {
        if (answer.questionId == null) {
            Navigation.fail(
                parentFragmentManager,
                answer.title,
                requireArguments().getInt("QUESTION_ID")
            )

            return
        }

        if (answer.questionId == -1) {
            Navigation.finish(parentFragmentManager)

            return
        }

        Navigation.question(parentFragmentManager, answer.questionId)
    }
}