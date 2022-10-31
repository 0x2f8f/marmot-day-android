package ru.runfox.game22.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ru.runfox.game22.Navigation
import ru.runfox.game22.Quest
import ru.runfox.game22.R

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainMessage = requireView().findViewById<TextView>(R.id.gameTitle)
        mainMessage.text = Quest.title

        val buttonQuestion = requireView().findViewById<Button>(R.id.buttonStart)
        buttonQuestion.setOnClickListener {
            Navigation.question(parentFragmentManager, Quest.startQuestion)
        }
    }

}