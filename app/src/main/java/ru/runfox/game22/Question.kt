package ru.runfox.game22

class Question(
    val id: Int,
    val title: String,
    val reward: Int,
    val hint: String,
    val answers: List<Answer>
)

class Answer(
    val title: String,
    val questionId: Int? = null
)
