package com.example.game22

object Quest {
    const val title = "День сурка"
    val startQuestion = 1
    val finish = -1
    val questions: List<Question> = listOf(
        Question(
            id = 1,
            title = "Я проснсулся, ничего не помню...",
            hint = "Если ничего не делать, ничего не изменится",
            answers = listOf(
                Answer("Встать с кровати и умыться", 2),
                Answer("Попытаться заснуть", null),
                Answer("Пролежать весь день с телефоном", null),
            )
        ),
        Question(
            id = 2,
            title = "Как взбодриться?",
            hint = "Может что-нибудь почитать?",
            answers = listOf(
                Answer("Кофе", null),
                Answer("Газета", -1),
                Answer("Red bull", null),
            )
        )
    )
}