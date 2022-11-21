package ru.runfox.game22.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.runfox.game22.*
import ru.runfox.game22.databinding.FragmentQuestionBinding
import ru.runfox.game22.dialog.TimeoutDialog
import ru.runfox.game22.dialog.VoitingResultsDialog

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
        MyApplication.currentQuestion = question

        binding.textQuestion.text = question.title
        binding.textReward.text =
            "Вопрос ${question.id} на \$${String.format("%,d", question.reward)}"

        val answerButtons = mapOf(
            0 to binding.answer1,
            1 to binding.answer2,
            2 to binding.answer3,
            3 to binding.answer4
        )

        var correctAnswer: Answer? = null
        answerButtons.forEach { i, button ->
            initButton(button = button, answer = question.answers[i])
            if (question.answers[i].questionId !== null) {
                correctAnswer = question.answers[i]
                MyApplication.correctAnswerTitle = question.answers[i].title
            }
        }

        initHelpButtons(
            binding = binding,
            viewContext = view.context,
            answerButtons = answerButtons,
            answers = question.answers,
            correctAnswer = correctAnswer!!,
        )

        initTimer(binding = binding)
    }

    private fun initHelpButtons(
        binding: FragmentQuestionBinding,
        viewContext: Context,
        answerButtons: Map<Int, Button>,
        answers: List<Answer>,
        correctAnswer: Answer
    ) {
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

                val rightAnswerScore: Int

                val scores: ArrayList<Int> = arrayListOf()
                if (!MyApplication.helpButtonFiftyUsed) {
                    rightAnswerScore = when (correctAnswer.questionId) {
                        in 1..5 -> (70..85).random()
                        in 6..10 -> (50..70).random()
                        in 11..13 -> (40..50).random()
                        else -> (30..50).random()
                    }
                    scores.add(0, (0..(100 - rightAnswerScore)).random())
                    scores.add(1, (0..(100 - rightAnswerScore - scores[0])).random())
                    scores.add(2, (0..(100 - rightAnswerScore - scores[0] - scores[1])).random())
                    scores.shuffle()
                } else {
                    rightAnswerScore = when (correctAnswer.questionId) {
                        in 1..5 -> (90..95).random()
                        in 6..10 -> (70..80).random()
                        in 11..13 -> (50..60).random()
                        else -> (30..60).random()
                    }
                    scores.add(0, 100 - rightAnswerScore)
                }

                var i = 0;
                var j = 0;
                val dialogScores: IntArray = intArrayOf(0, 0, 0, 0)
                while (i < 4) {
                    val score: Int
                    if (answers[i].questionId !== null) {
                        score = rightAnswerScore;
                    } else {
                        if (answerButtons[i]!!.visibility == View.GONE) {
                            score = -1
                        } else {
                            score = scores[j++]
                        }
                    }

                    dialogScores[i++] = score
                }

                val dialog = VoitingResultsDialog();
                dialog.setResultVoiting(dialogScores)
                activity?.let { it1 ->
                    dialog.show(
                        it1.supportFragmentManager,
                        "VoitingResultsDialog"
                    )
                };
            }
        }

        binding.fiftyHelpButton.setOnClickListener {
            if (!MyApplication.helpButtonFiftyUsed) {
                MyApplication.helpButtonFiftyUsed = true
                binding.fiftyHelpButton.setImageResource(R.drawable.fifty_small_disable)

                var k = 0
                var i: Int
                while (k < 2) {
                    i = (0..3).random()
                    if (answers[i].questionId == null && answerButtons[i]!!.visibility != View.GONE) {
                        answerButtons[i]!!.visibility = View.GONE
                        k++
                    }
                }
            }
        }

        binding.callHelpButton.setOnClickListener {
            if (!MyApplication.helpButtonCallUsed) {
                MyApplication.helpButtonCallUsed = true
                binding.callHelpButton.setImageResource(R.drawable.help_call_disable)

                val textHint: String
                var answerByFriend = -1
                if (correctAnswer.questionId!! > 12 || correctAnswer.questionId < 0) {
                    var i: Int
                    while (answerByFriend < 0) {
                        i = (0..3).random()
                        if (answerButtons[i]!!.visibility != View.GONE) {
                            answerByFriend = i
                        }
                    }
                    textHint = "Лучший друг:\n-Предполагаю это ${answers[answerByFriend].title}"
                } else {
                    textHint = "Лучший друг:\n-Я думаю это вариант ${correctAnswer.title}"
                }

                Toast.makeText(viewContext, textHint, Toast.LENGTH_LONG).show()
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
        MyApplication.timerRun = false
        if (answer.questionId == null) {
            answerFail(answer, button)
            return
        }

        if (answer.questionId == -1) {
            answerFinish(button)
            return
        }

        answerCorrect(answer, button, 6)
    }

    private fun answerCorrect(answer: Answer, button: Button, tick: Int) {
        button.setBackgroundResource(
            if (tick % 2 == 0) R.drawable.button_answer_success
            else R.drawable.button_answer_pending
        )
        Handler(Looper.getMainLooper()).postDelayed({
            if (tick <= 0) {
                Navigation.question(parentFragmentManager, answer.questionId!!)
            } else {
                answerCorrect(answer, button, tick - 1)
            }
        }, 150)
    }

    private fun answerFinish(button: Button) {
        button.setBackgroundResource(R.drawable.button_answer_success)
        Handler(Looper.getMainLooper()).postDelayed({
            Navigation.finish(parentFragmentManager)
        }, 1000)
    }

    private fun answerFail(answer: Answer, button: Button) {
        button.setBackgroundResource(R.drawable.button_answer_wrong)

        Handler(Looper.getMainLooper()).postDelayed({
            if (!MyApplication.helpButtonChanceUsed) {
                answerHelpButtonChance()
            } else {
                answerGameOver(answer)
            }
        }, 1000)
    }

    private fun answerHelpButtonChance() {
        MyApplication.helpButtonChanceUsed = true
        binding.chanceHelpButton.setImageResource(R.drawable.help_chance_disable)
        buttonPressed = false
        Snackbar.make(
            binding.root,
            "Вы ответили неверно, но вас спас спасательный круг",
            Snackbar.LENGTH_LONG,
        ).show()
        initTimer(binding)
    }

    private fun answerGameOver(answer: Answer) {
        Navigation.fail(
            parentFragmentManager,
            answer.title,
            requireArguments().getInt("QUESTION_ID")
        )
    }

    private fun timeOut(binding: FragmentQuestionBinding) {
        Snackbar.make(
            binding.root,
            "Время истекло",
            Snackbar.LENGTH_LONG,
        ).show()

        val answerButtons = mapOf(
            0 to binding.answer1,
            1 to binding.answer2,
            2 to binding.answer3,
            3 to binding.answer4
        )

        answerButtons.forEach { i, button ->
            if (MyApplication.currentQuestion!!.answers[i].questionId !== null) {
                button.setBackgroundResource(R.drawable.button_answer_success)
            } else {
                button.setBackgroundResource(R.drawable.button_answer_wrong)
            }
        }
    }

    private fun timeOutButUseHelpChance(binding: FragmentQuestionBinding) {
        if (MyApplication.helpButtonChanceUsed) {
            return
        }

        MyApplication.helpButtonChanceUsed = true
        binding.chanceHelpButton.setImageResource(R.drawable.help_chance_disable)
        buttonPressed = false
        Snackbar.make(
            binding.root,
            "Время истекло, но вас спас спасательный круг",
            Snackbar.LENGTH_LONG,
        ).show()
        MyApplication.timerTime = MyApplication.BASE_TIMER_TIME
        MyApplication.timerRun = true
        tickTimer(binding, MyApplication.timerTime--)
    }

    private fun initTimer(binding: FragmentQuestionBinding) {
        MyApplication.timerTime = MyApplication.BASE_TIMER_TIME
        MyApplication.timerRun = true
        tickTimer(binding, MyApplication.timerTime--)
    }

    @SuppressLint("SetTextI18n")
    private fun tickTimer(binding: FragmentQuestionBinding, time: Int) {
        if (time < 0) {
            //val dialog = TimeoutDialog();
            //dialog.setCorrectAnswer(MyApplication.correctAnswerTitle)
            //activity?.let { it1 -> dialog.show(it1.supportFragmentManager, "TimeoutDialog")};
            if (!MyApplication.helpButtonChanceUsed) {
                timeOutButUseHelpChance(binding)
            } else {
                timeOut(binding)
            }
        } else {
            binding.timer.text = "${if (time < 10) "00:0" else "00:"}${time}"
            Handler(Looper.getMainLooper()).postDelayed({
                if (MyApplication.timerRun) {
                    tickTimer(binding, MyApplication.timerTime--)
                }
            }, 1000)
        }
    }
}