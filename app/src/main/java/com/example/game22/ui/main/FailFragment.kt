package com.example.game22.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.game22.Navigation
import com.example.game22.R

class FailFragment: Fragment() {
    companion object {
        fun newInstance() = FailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_fail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonRestart = requireView().findViewById<Button>(R.id.buttonRestartByFail)
        buttonRestart.setOnClickListener {
            Navigation.main(parentFragmentManager)
        }
    }
}