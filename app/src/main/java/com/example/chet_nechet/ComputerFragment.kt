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

class ComputerFragment : Fragment() {
    private lateinit var binding: FragmentComputerBinding
    private val dataModel: DataModel by activityViewModels()
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComputerBinding.inflate(inflater)

        var trueCounter = 0

        rnd()
        dataModel.messageForComputerFragment.observe(activity as LifecycleOwner){
            val btnClick = it.toString().toBoolean()
            if (btnClick)
            {
                binding.linearLayout.isVisible = true
                binding.imageView.isVisible = true
            }
        }
        dataModel.messageForMainActivityFromComputerFragmentTrueCounter.observe(activity as LifecycleOwner) {
            trueCounter = it.toString().toInt()
        }


        when ((0..1).random()){
            0 -> {binding.radioButton3.isChecked = true
                binding.radioButton3.isEnabled = false
                binding.radioButton4.isEnabled = false
                if (binding.textViewSum.text.toString().toInt() % 2 == 0 && binding.radioButton3.isChecked)
                {
                    binding.imageView.setImageResource(R.drawable.resource_true)
                    trueCounter++
                    dataModel.messageForMainActivityFromComputerFragmentTrueCounter.value = trueCounter.toString()
                }
                else
                {
                    binding.imageView.setImageResource(R.drawable.resource_false)
                    dataModel.messageForMainActivityFromComputerFragmentTrueCounter.value = trueCounter.toString()
                }
            }
            1 -> {
                binding.radioButton4.isChecked = true
                binding.radioButton3.isEnabled = false
                binding.radioButton4.isEnabled = false
                if (binding.textViewSum.text.toString().toInt() % 2 == 1 && binding.radioButton4.isChecked)
                {
                    binding.imageView.setImageResource(R.drawable.resource_true)
                    trueCounter++
                    dataModel.messageForMainActivityFromComputerFragmentTrueCounter.value = trueCounter.toString()
                }
                else
                {
                    binding.imageView.setImageResource(R.drawable.resource_false)
                    dataModel.messageForMainActivityFromComputerFragmentTrueCounter.value = trueCounter.toString()
                }
            }
        }

        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() = ComputerFragment()
    }
    @SuppressLint("SetTextI18n")
    fun rnd()
    {
        binding.textView1.text = (1..6).random().toString()
        binding.textView2.text = (1..6).random().toString()
        binding.textView3.text = (1..6).random().toString()
        binding.textViewSum.text = (binding.textView1.text.toString().toInt() +
                binding.textView2.text.toString().toInt() +
                binding.textView3.text.toString().toInt()).toString()
    }
}