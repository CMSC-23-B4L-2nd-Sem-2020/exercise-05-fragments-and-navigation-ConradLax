package com.example.lightsout
import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.lightsout.databinding.FragmentGameboardBinding
import com.example.lightsout.databinding.FragmentStartBinding

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : Fragment() {
    var nicknameData:String = "Yo"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentStartBinding>(inflater,
            R.layout.fragment_start,container,false)

        val gameBinding = DataBindingUtil.inflate<FragmentGameboardBinding>(inflater,
            R.layout.fragment_gameboard,container,false)

        binding.doneButton3.setOnClickListener { view : View ->
                if(binding.nicknameEdit3.text.isNotEmpty()){
//                    gameBinding.nicknameText.text = binding.nicknameEdit3.text.toString()       //puts the nickname input of user to the nickname_text textView
                    newInstance(binding.nicknameEdit3.text.toString())
                }
                else{
//                    gameBinding.nicknameText.text = getString(R.string.response)    //puts the error response input to the nickname_text textView if there is no input
                    newInstance(getString(R.string.response))
                }
            view.findNavController().navigate(R.id.action_startFragment_to_gameboardFragment)
        }




        return binding.root
    }
    //way for passing nickname to the Game Board Fragment
    companion object {

//        @JvmStatic
//        fun newInstance(nickname: String) = StartFragment().apply {
//            arguments = Bundle().apply {
//                putString("NICKNAME_VALUE", nickname)
//            }
//        }
        lateinit var nicknameData:String
        fun newInstance(nicknameText:String){
        nicknameData = nicknameText
}

    }
}
