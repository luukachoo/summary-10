package com.example.summary10.presentation.screen.bottom_sheet_from_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.summary10.databinding.FragmentFromAccountBottomSheetBinding
import com.example.summary10.presentation.base.BaseBottomSheetDialogFragment
import com.example.summary10.presentation.event.from_bottom_sheet.FromBottomSheetEvents
import com.example.summary10.presentation.extension.showSnackbar
import com.example.summary10.presentation.model.Account
import com.example.summary10.presentation.state.from_bottom_sheet.FromSheetViewState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FromAccountBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: FromAccountViewModel by viewModels()
    private lateinit var accountsAdapter: FromAccountRecyclerAdapter
    var itemClickListener: BottomSheetItemClickListener? = null

    private var _binding: FragmentFromAccountBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFromAccountBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        collectState()
    }


    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect {
                    handleBottomSheetState(it)
                }
            }
        }
    }

    private fun setUpRecycler() = with(binding) {
        accountsAdapter = FromAccountRecyclerAdapter()
        accountsAdapter.onClick { account ->
            itemClickListener?.onBottomSheetItemClick(account)
            dismiss()
        }
        rvCards.adapter = accountsAdapter
        viewModel.onEvent(FromBottomSheetEvents.FetchAccounts)
    }

    private fun handleBottomSheetState(state: FromSheetViewState) = with(binding) {
        progressBar.isVisible = state.isLoading

        state.accounts?.let {
            accountsAdapter.submitList(it)
        }

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(FromBottomSheetEvents.ResetErrorMessage)
        }
    }

    interface BottomSheetItemClickListener {
        fun onBottomSheetItemClick(account: Account)
    }
}