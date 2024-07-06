package com.example.loveapplication.ui.fragments.result_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.loveapplication.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoveResultFragment : Fragment() {

    private val binding by lazy {
        FragmentResultBinding.inflate(layoutInflater)
    }
    private val args: LoveResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvFName.text = args.firstName
            tvSName.text = args.secondName
            tvScore.text = args.percentage
            tvMessage.text = args.result
        }

        binding.btnTryAgain.setOnClickListener {
            findNavController().navigate(LoveResultFragmentDirections.actionResultFragmentToCalculateFragment())
        }
    }


}