package com.example.loveapplication.ui.fragments.onboard_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieCompositionFactory
import com.example.loveapplication.R
import com.example.loveapplication.databinding.FragmentOnboardViewPagerBinding

class OnboardViewPagerFragment : Fragment() {

    companion object {
        const val ARG_ONBOARD_POSITION = "onboard"
    }

    private val binding by lazy {
        FragmentOnboardViewPagerBinding.inflate(layoutInflater)
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
    }

    private fun initialize() {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                LottieCompositionFactory.fromRawRes(context, R.raw.lottie1)
            }

            1 -> {
                LottieCompositionFactory.fromRawRes(context, R.raw.lottie_love1)
            }

            2 -> {
                LottieCompositionFactory.fromRawRes(context, R.raw.lottie_love2)
            }

            3 -> {
                LottieCompositionFactory.fromRawRes(context, R.raw.lottie_love3)
            }
        }
    }

}