package com.example.summary10.presentation.screen.home

import android.annotation.SuppressLint
import com.example.summary10.R
import com.example.summary10.databinding.FragmentHomeBinding
import com.example.summary10.presentation.base.BaseFragment
import com.example.summary10.presentation.extension.loadImagesWithGlide
import com.example.summary10.presentation.model.Account
import com.example.summary10.presentation.screen.bottom_sheet_from_account.FromAccountBottomSheet
import com.example.summary10.presentation.screen.bottom_sheet_to_account.ToAccountBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    FromAccountBottomSheet.BottomSheetItemClickListener,
    ToAccountBottomSheet.ToAccountBottomSheetListener {




    override fun init() {

    }

    override fun listeners() {
        binding.materialButton.setOnClickListener {
            val bottomSheetFragmentFrom = FromAccountBottomSheet().apply {
                itemClickListener = this@HomeFragment
            }
            bottomSheetFragmentFrom.show(childFragmentManager, bottomSheetFragmentFrom.tag)
        }

        binding.materialButtonTo.setOnClickListener {
            val bottomSheetFragmentTo = ToAccountBottomSheet().apply {
                accountDetailsFetchedListener = this@HomeFragment
            }
            bottomSheetFragmentTo.show(childFragmentManager, bottomSheetFragmentTo.tag)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBottomSheetItemClick(account: Account) {
        updateUIWithSelectedAccount(account)
    }

    @SuppressLint("SetTextI18n")
    private fun updateUIWithSelectedAccount(account: Account) {
        binding.apply {
            tvCardBalance.text = "${account.balance}"
            tvAccountNumber.text = "**${account.accountNumber.takeLast(4)}"

            val cardImageResId = when (account.cardType) {
                "VISA" -> R.drawable.ic_visa
                "MASTER_CARD" -> R.drawable.ic_mastercard
                else -> R.drawable.ic_launcher_background
            }

            appCompatImageView.loadImagesWithGlide(cardImageResId)
        }
    }

    override fun onAccountDetailsFetched(account: Account) {
        TODO("Not yet implemented")
    }
}