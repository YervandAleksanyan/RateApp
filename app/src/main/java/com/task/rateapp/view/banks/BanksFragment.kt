package com.task.rateapp.view.banks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.task.rateapp.R
import com.task.rateapp.databinding.FragmentBanksBinding

class BanksFragment : Fragment() {

    private lateinit var binding: FragmentBanksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_banks, container, false)
        return binding.root
    }
}