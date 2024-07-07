package com.example.loveapplication.ui.fragments.calculate_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.loveapplication.R
import com.example.loveapplication.data.network.LoveResult
import com.example.loveapplication.databinding.FragmentCalculateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoveCalcFragment : Fragment() {

    private val binding by lazy {
        FragmentCalculateBinding.inflate(layoutInflater)
    }

    private val viewModel: CalculateViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCalc.setOnClickListener {
            setupListener()
            setupObserver()
        }
    }

    private fun setupObserver() {
        viewModel.loveResultData.observe(viewLifecycleOwner) { result ->
            navigateToResultFragment(result)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupListener() {
        binding.btnCalc.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val secondName = binding.etSecondName.text.toString()

            if (firstName.isBlank() || secondName.isBlank()) {
                Toast.makeText(context, "Please enter the names", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.getPercentage(firstName, secondName)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
    private fun navigateToResultFragment(loveResult: LoveResult) {
        val bundle = Bundle().apply {
            putString("firstName", loveResult.firstName)
            putString("secondName", loveResult.secondName)
            putString("percentage", loveResult.percentage)
            putString("result", loveResult.result)
        }
        findNavController().navigate(R.id.action_calculateFragment_to_resultFragment, bundle)
    }
}
