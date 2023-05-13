package com.example.chet_nechet

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.chet_nechet.databinding.FragmentComputerBinding
import com.example.chet_nechet.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    lateinit var binding: FragmentUserBinding
    private val dataModel: DataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater)

        var trueCounter = 0
        val rbClick = true
        rnd()
        dataModel.messageForUserFragment.observe(activity as LifecycleOwner){
            val btnClick = it.toString().toBoolean()
            if (btnClick)
            {
                binding.linearLayout2.isVisible = true
                binding.imageViewU.isVisible = true
            }
        }
        dataModel.messageForMainActivityFromUserFragmentTrueCounter.observe(activity as LifecycleOwner) {
            trueCounter = it.toString().toInt()
        }

        binding.radioButton3U.setOnClickListener{
            dataModel.messageForMainActivityFromUserFragment.value = rbClick.toString()
            binding.radioButton3U.isEnabled = false
            binding.radioButton4U.isEnabled = false
            if (binding.textViewSumU.text.toString().toInt() % 2 == 0 && binding.radioButton3U.isChecked)
            {
                binding.imageViewU.setImageResource(R.drawable.resource_true)
                trueCounter++
                dataModel.messageForMainActivityFromUserFragmentTrueCounter.value = trueCounter.toString()
            }
            else
            {
                binding.imageViewU.setImageResource(R.drawable.resource_false)
                dataModel.messageForMainActivityFromUserFragmentTrueCounter.value = trueCounter.toString()
            }
        }


        binding.radioButton4U.setOnClickListener{
            dataModel.messageForMainActivityFromUserFragment.value = rbClick.toString()
            binding.radioButton3U.isEnabled = false
            binding.radioButton4U.isEnabled = false
            if (binding.textViewSumU.text.toString().toInt() % 2 == 1 && binding.radioButton4U.isChecked)
            {
                binding.imageViewU.setImageResource(R.drawable.resource_true)
                trueCounter++
                dataModel.messageForMainActivityFromUserFragmentTrueCounter.value = trueCounter.toString()
            }
            else
            {
                binding.imageViewU.setImageResource(R.drawable.resource_false)
                dataModel.messageForMainActivityFromUserFragmentTrueCounter.value = trueCounter.toString()
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()
    }
    @SuppressLint("SetTextI18n")
    fun rnd()
    {
        binding.textView1U.text = (1..6).random().toString()
        binding.textView2U.text = (1..6).random().toString()
        binding.textView3U.text = (1..6).random().toString()
        binding.textViewSumU.text = (binding.textView1U.text.toString().toInt() +
                binding.textView2U.text.toString().toInt() +
                binding.textView3U.text.toString().toInt()).toString()
    }
}