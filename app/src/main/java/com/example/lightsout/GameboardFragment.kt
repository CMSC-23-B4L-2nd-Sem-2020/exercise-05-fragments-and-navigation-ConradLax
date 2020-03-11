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
class GameboardFragment : Fragment() {

    lateinit var binding: FragmentGameboardBinding
    lateinit var winBinding: FragmentWinBinding
    var clickCounter=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentGameboardBinding>(inflater,
            R.layout.fragment_gameboard,container,false)

        winBinding = DataBindingUtil.inflate<FragmentWinBinding>(inflater,
            R.layout.fragment_win,container,false)


        //lets user edit nickname
        binding.nicknameText.setOnClickListener { view : View ->

            view.visibility = View.GONE             //the textView for the nickname is now invisible because the user is editing the nickname

            //the app now focuses on the editing function of the user
            binding.nicknameEdit.requestFocus()
        }
        //function for done button - to add the new name input
        binding.doneButton.setOnClickListener { view : View ->
            binding.nicknameText.visibility = View.VISIBLE
            if(binding.nicknameEdit.text.isNotEmpty()){
                binding.nicknameText.text = binding.nicknameEdit.text       //puts the nickname input of user to the nickname_text textView
            }
            else{
                binding.nicknameText.text = getString(R.string.response)      //puts the error response input to the nickname_text textView if there is no input
            }
        }

        binding.retryButton.setOnClickListener {
            clickCounter=0                          //resets counter
            var testData:TextView
            for(item in (0..24)){               //makes each boxes reset
                testData = getId(item)
                testData.visibility = View.VISIBLE     //makes the removed boxes when the user won visible again
                testData.setBackgroundColor(Color.LTGRAY)
                testData.text="0"
            }
        }

        setListeners()
        return binding.root
    }






    //FUNCTION SECTION
    private fun getId(int: Int): TextView {
        val list: List<TextView> = listOf(
            binding.boxOne,
            binding.boxTwo,
            binding.boxThree,
            binding.boxFour,
            binding.boxFive,
            binding.boxSix,
            binding.boxSeven,
            binding.boxEight,
            binding.boxNine,
            binding.boxTen,
            binding.boxEleven,
            binding.boxTwelve,
            binding.boxThirteen,
            binding.boxFourteen,
            binding.boxFifteen,
            binding.boxSixteen,
            binding.boxSeventeen,
            binding.boxEighteen,
            binding.boxNineteen,
            binding.boxTwenty,
            binding.boxTwentyOne,
            binding.boxTwentyTwo,
            binding.boxTwentyThree,
            binding.boxTwentyFour,
            binding.boxTwentyFive
        )

        return list[int]
    }

    private fun didUserWin(){
        var counter = 0
        var testBoxData:TextView


        for(item in (0..24)){                   //counts the lighted boxes
            testBoxData = getId(item)
            if(testBoxData.text=="1") counter+=1
        }
        if(counter==25){                             //checks if counter reached 25, which is what is needed to win
            view?.findNavController()?.navigate(R.id.action_gameboardFragment_to_winFragment)
        }

    }

    //action to be done to check if adjacent boxes and the clicked box should turn off or on
    private fun decideColor(textView: TextView){
        if(textView.text=="0"){
            textView.setBackgroundColor(Color.DKGRAY)
            textView.text="1"
        }
        else{
            textView.setBackgroundColor(Color.LTGRAY)
            textView.text="0"
        }
    }

    private fun countClicks(){
        clickCounter+=1
        binding.countText.text = getString(R.string.count_start).plus(clickCounter.toString())
        winBinding.countText2.text = getString(R.string.count_start).plus(clickCounter.toString())
    }

    //colors the textbox
    private fun makeColored(view: View){

        for (item in (0..24)){
            if(getId(item).id == view.id){     //uses the values of each box to represent boolean values (if turned on(1) or off(0))
                if(getId(item).text == "0"){
                    view.setBackgroundColor(Color.DKGRAY)
                    getId(item).text = "1"
                    if(item==0){
                        var next_box_data = getId(item+1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                    }
                    else if(item==4){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                    }
                    else if(item==20){
                        var next_box_data = getId(item+1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                    }
                    else if(item==24){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                    }
                    else if(item>0 && item<4){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                    }
                    else if(item>20 && item <24){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                    }
                    else if(item%5==0){
                        var next_box_data = getId(item-5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+1)
                        decideColor(next_box_data)
                    }
                    else if(item==9 || item==14 || item==19){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                    }
                    else{
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+1)
                        decideColor(next_box_data)
                    }
                }
                else if(getId(item).text == "1"){
                    view.setBackgroundColor(Color.LTGRAY)
                    getId(item).text = "0"

                    if(item==0){
                        var next_box_data = getId(item+1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                    }
                    else if(item==4){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                    }
                    else if(item==20){
                        var next_box_data = getId(item+1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                    }
                    else if(item==24){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                    }
                    else if(item>0 && item<4){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                    }
                    else if(item>20 && item <24){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item+1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                    }
                    else if(item%5==0){
                        var next_box_data = getId(item-5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+1)
                        decideColor(next_box_data)
                    }
                    else if(item==9 || item==14 || item==19){
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                    }
                    else{
                        var next_box_data = getId(item-1)
                        decideColor(next_box_data)
                        next_box_data = getId(item-5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+5)
                        decideColor(next_box_data)
                        next_box_data = getId(item+1)
                        decideColor(next_box_data)
                    }
                }

            }
        }
    }

    //function that adds an action when a box is pressed
    private fun setListeners(){
        for(item in (0..24)){
            getId(item).setOnClickListener{
                makeColored(it)
                countClicks()
                didUserWin()
            }
        }

    }
}
