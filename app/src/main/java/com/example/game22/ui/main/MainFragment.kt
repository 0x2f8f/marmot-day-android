package com.example.game22.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.game22.Quest
import com.example.game22.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

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

        val mainMessage = requireView().findViewById<TextView>(R.id.mainMessage)
        mainMessage.text = Quest.title

        val buttonQuestion = requireView().findViewById<Button>(R.id.buttonStart)
        buttonQuestion.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, QuestionFragment.newInstance())
                .commit()
        }

        val buttonMarmot = requireView().findViewById<Button>(R.id.buttonMarmot)
        buttonMarmot.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, ImageFragment.newInstance())
                .commit()
        }
    }

}