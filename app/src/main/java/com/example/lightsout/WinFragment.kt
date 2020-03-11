package com.example.lightsout

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.lightsout.databinding.FragmentGameboardBinding
import com.example.lightsout.databinding.FragmentWinBinding

/**
 * A simple [Fragment] subclass.
 */
class WinFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentWinBinding>(inflater,
            R.layout.fragment_win,container,false)
        val gameBinding = DataBindingUtil.inflate<FragmentGameboardBinding>(inflater,
            R.layout.fragment_gameboard,container,false)

        binding.countText2.text = gameBinding.countText.text

        binding.retryButton3.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_winFragment_to_startFragment)
        }
        return binding.root
    }

}
