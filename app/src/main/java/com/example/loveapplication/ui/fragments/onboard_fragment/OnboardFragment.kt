package com.example.loveapplication.ui.fragments.onboard_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.loveapplication.R
import com.example.loveapplication.data.local.SharedPreferencesHelper
import com.example.loveapplication.databinding.FragmentOnboardBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardFragment : Fragment() {

    @Inject lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private val binding by lazy {
        FragmentOnboardBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun setupListener() {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 3) {
                    binding.tvStart.visibility = View.VISIBLE
                    binding.tvSkip.visibility = View.INVISIBLE
                } else {
                    binding.tvStart.visibility = View.INVISIBLE
                    binding.tvSkip.visibility = View.VISIBLE
                }
            }
        })

        binding.tvStart.setOnClickListener {
            if (binding.viewPager2.currentItem < 4){
                binding.viewPager2.setCurrentItem(binding.viewPager2.currentItem + 3, true)
            }
        }

        binding.tvStart.setOnClickListener {
            sharedPreferencesHelper.setOnboardingShown()
            findNavController().navigate(R.id.action_onboardFragment_to_calculateFragment)
        }

        binding.tvSkip.setOnClickListener {
            if (binding.viewPager2.currentItem < 3) {
                binding.viewPager2.currentItem += 1
            }
        }
    }

    private fun initialize() {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this@OnboardFragment)
        TabLayoutMediator(binding.dotsTabLayout, binding.viewPager2) { _, _ ->
        }.attach()
    }
}
