package com.example.loveapplication.ui.fragments.history_fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.loveapplication.data.local.HistoryEntity
import com.example.loveapplication.databinding.FragmentHistoryBinding
import com.example.loveapplication.interfaces.OnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment(), OnClick {

    @Inject
    lateinit var historyAdapter: HistoryAdapter

    private val binding by lazy { FragmentHistoryBinding.inflate(layoutInflater) }
    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHistory.adapter = historyAdapter
        viewModel.getHistory().observe(viewLifecycleOwner, Observer { historyEntities ->
            historyAdapter.submitList(historyEntities)
        })
    }

    override fun onLongClick(historyEntity: HistoryEntity) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Do you want to delete it?")
            setPositiveButton("Yes") { dialog, which ->
                viewModel.delete(historyEntity)
            }
            setNegativeButton("Нет") { dialog, which ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    fun providesHistoryAdapter() = HistoryAdapter(this)

}


