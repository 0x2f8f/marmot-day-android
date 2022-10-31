package ru.runfox.game22

object Quest {
    const val title = "Кто хочет стать миллионером?"

    val startQuestion = 1
    val finish = -1
    val questions: List<Question> = listOf(
        Question(
            id = 1,
            reward = 500,
            title = "Что растёт в огороде?",
            hint = "Это должно быть что то съедобное",
            answers = listOf(
                Answer("A: Лук", 2),
                Answer("B: Пистолет"),
                Answer("C: Пулемёт"),
                Answer("D: Ракета СС-20"),
            )
        ),
        Question(
            id = 2,
            reward = 1_000,
            title = "Как называют микроавтобусы, совершающие поездки по определённым маршрутам?",
            hint = "Почти как маршрут-квитанция",
            answers = listOf(
                Answer("A: Рейсовка"),
                Answer("B: Путёвка"),
                Answer("C: Курсовка"),
                Answer("D: Маршрутка",3),
            )
        ),
        Question(
            id = 3,
            reward = 2_000,
            title = "О чём писал Грибоедов, отмечая, что он «нам сладок и приятен» ?",
            hint = "Должно быть что то про отца",
            answers = listOf(
                Answer("A: Дым Отечества", 4),
                Answer("B: Дух купечества"),
                Answer("C: Дар пророчества"),
                Answer("D: Пыл девичества"),
            )
        ),
        Question(
            id = 4,
            reward = 3_000,
            title = "Какого персонажа нет в известной считалке «На золотом крыльце сидели» ?",
            hint = "Он умеет ковать",
            answers = listOf(
                Answer("A: Сапожника"),
                Answer("B: Кузнеца", 5),
                Answer("C: Короля"),
                Answer("D: Портного"),
            )
        ),
        Question(
            id = 5,
            reward = 5_000,
            title = "Какой специалист занимается изучением неопознанных летающих объектов?",
            hint = "Житель столицы Башкортостана",
            answers = listOf(
                Answer("A: Кинолог"),
                Answer("B: Уфолог", 6),
                Answer("C: Сексопатолог"),
                Answer("D: Психиатр"),
            )
        ),
        Question(
            id = 6,
            reward = 10_000,
            title = "Как называется разновидность воды, в которой атом водорода замещён его изотопом дейтерием?",
            hint = "Как музыка РОК",
            answers = listOf(
                Answer("A: Лёгкая"),
                Answer("B: Средняя"),
                Answer("C: Тяжёлая", 7),
                Answer("D: Невесомая"),
            )
        ),
        Question(
            id = 7,
            reward = 15_000,
            title = "Что такое десница?",
            hint = "Есть левая, а есть правая",
            answers = listOf(
                Answer("A: Бровь"),
                Answer("B: Глаз"),
                Answer("C: Шея"),
                Answer("D: Рука", 8),
            )
        ),
        Question(
            id = 8,
            reward = 25_000,
            title = "В какое море впадает река Урал?",
            hint = "Доброе привидение",
            answers = listOf(
                Answer("A: Азовское"),
                Answer("B: Чёрное"),
                Answer("C: Средиземное"),
                Answer("D: Каспийское", 9),
            )
        ),
        Question(
            id = 9,
            reward = 50_000,
            title = "На что кладут руку члены английского общества лысых во время присяги?",
            hint = "Что то долго живущее",
            answers = listOf(
                Answer("A: Баскетбольный мяч"),
                Answer("B: Бильярдный шар", 10),
                Answer("C: Апельсин"),
                Answer("D: Кокосовый орех"),
            )
        ),
        Question(
            id = 10,
            reward = 100_000,
            title = "Как назывался каменный монолит, на котором установлен памятник Петру I в Санкт-Петербурге?",
            hint = "В Питере всегда дождливо",
            answers = listOf(
                Answer("A: Дом-камень"),
                Answer("B: Гром-камень", 11),
                Answer("C: Гора-камень"),
                Answer("D: Разрыв-камень"),
            )
        ),
        Question(
            id = 11,
            reward = 200_000,
            title = "Как Пётр Ильич Чайковский назвал свою серенаду для скрипки с оркестром си-бемоль минор?",
            hint = "Очень тревожная серенада",
            answers = listOf(
                Answer("A: Флегматическая"),
                Answer("B: Меланхолическая", 12),
                Answer("C: Холерическая"),
                Answer("D: Сангвиническая"),
            )
        ),
        Question(
            id = 12,
            reward = 400_000,
            title = "Какого ордена не было у первого советского космонавта Юрия Гагарина?",
            hint = "Гагарин хоть и летал высоко, но под небесами не был",
            answers = listOf(
                Answer("A: «Ожерелье Нила» (Египет)"),
                Answer("B: «Крест Грюнвальда» (Польша)"),
                Answer("C: «Плайя Хирон» (Куба)"),
                Answer("D: «Орден двойного дракона» (Китай)", 13),
            )
        ),
        Question(
            id = 13,
            reward = 800_000,
            title = "Какое животное имеет второе название — кугуар?",
            hint = "Прям как мои кроссовки",
            answers = listOf(
                Answer("A: Оцелот"),
                Answer("B: Леопард"),
                Answer("C: Пума", 14),
                Answer("D: Пантера"),
            )
        ),
        Question(
            id = 14,
            reward = 1_500_000,
            title = "Что в России 19 века делали в дортуаре?",
            hint = "Дортуар - общая комната для учащихся в закрытых учебных заведениях",
            answers = listOf(
                Answer("A: Ели"),
                Answer("B: Спали", 15),
                Answer("C: Ездили верхом"),
                Answer("D: Купались"),
            )
        ),
        Question(
            id = 15,
            reward = 3_000_000,
            title = "Какой химический элемент назван в честь злого подземного гнома?",
            hint = "Ещё есть такая модель Chevrolet",
            answers = listOf(
                Answer("A: Гафний"),
                Answer("B: Кобальт", finish),
                Answer("C: Бериллий"),
                Answer("D: Теллур"),
            )
        ),
    )
}