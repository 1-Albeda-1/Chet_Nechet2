package com.example.chet_nechet

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.chet_nechet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFrag(ComputerFragment.newInstance(), R.id.FrameComputer)
        openFrag(UserFragment.newInstance(), R.id.FrameUser)

        dataModel.messageForMainActivityFromUserFragment.observe(this) {
            val rbClick = it.toString().toBoolean()
            binding.btnStart.isEnabled = rbClick
        }

        binding.FrameUser.isVisible = true
        binding.FrameComputer.isVisible = true


        binding.btnStart.setOnClickListener {
            binding.btnStart.isVisible = false
            binding.btnRepeat.isVisible = true
            val btnCl = true
            binding.txtCompCounter.isVisible = true
            binding.txtUserCounter.isVisible = true
            dataModel.messageForComputerFragment.value = btnCl.toString()
            dataModel.messageForUserFragment.value = btnCl.toString()
            binding.btnClose.isVisible = true
            dataModel.messageForMainActivityFromComputerFragmentTrueCounter.observe(this) {
                val trueCounterComp = it
                binding.txtCompCounter.text = trueCounterComp
            }
            dataModel.messageForMainActivityFromUserFragmentTrueCounter.observe(this) {
                val trueCounterUser = it
                binding.txtUserCounter.text = trueCounterUser
            }
        }
        binding.btnRepeat.setOnClickListener{

            binding.btnRepeat.isVisible = false
            binding.btnClose.isVisible = false
            binding.btnStart.isVisible = true
            binding.btnStart.isEnabled = false
            dataModel.messageForMainActivityFromComputerFragmentTrueCounter.value = binding.txtCompCounter.text.toString()
            dataModel.messageForMainActivityFromUserFragmentTrueCounter.value = binding.txtUserCounter.text.toString()
            val btnClk = false
            binding.txtCompCounter.isVisible = false
            binding.txtUserCounter.isVisible = false

            dataModel.messageForComputerFragment.value = btnClk.toString()
            dataModel.messageForUserFragment.value = btnClk.toString()
            openFrag(ComputerFragment.newInstance(), R.id.FrameComputer)
            openFrag(UserFragment.newInstance(), R.id.FrameUser)
        }

        binding.btnClose.setOnClickListener{
            finish()
        }
    }
    private fun openFrag(f: Fragment, idHolder: Int)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .commit()
    }
}