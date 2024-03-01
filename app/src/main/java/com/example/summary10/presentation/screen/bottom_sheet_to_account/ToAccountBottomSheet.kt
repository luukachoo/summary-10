package com.example.summary10.presentation.screen.bottom_sheet_to_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.summary10.databinding.FragmentToAccountBottomSheetBinding
import com.example.summary10.presentation.event.from_bottom_sheet.FromBottomSheetEvents
import com.example.summary10.presentation.event.to_bottom_sheet.ToFragmentEvents
import com.example.summary10.presentation.extension.showSnackbar
import com.example.summary10.presentation.model.Account
import com.example.summary10.presentation.state.from_bottom_sheet.FromSheetViewState
import com.example.summary10.presentation.state.to_bottom_sheet.ToFragmentState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToAccountBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: ToAccountViewModel by viewModels()
    var accountDetailsFetchedListener: ToAccountBottomSheetListener? = null

    private var _binding: FragmentToAccountBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToAccountBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun handleBottomSheetState(state: ToFragmentState) = with(binding) {
        state.account?.let {
            accountDetailsFetchedListener?.onAccountDetailsFetched(it)
            dismiss()
        }

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(ToFragmentEvents.ResetErrorMessage)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface ToAccountBottomSheetListener {
        fun onAccountDetailsFetched(account: Account)
    }
}