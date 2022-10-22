package com.example.game22

class Question(
    val id: Int,
    val title: String,
    val hint: String,
    val answers: List<Answer>
)

class Answer(
    val title: String,
    val questionId: Int?
)
