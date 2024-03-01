package com.example.summary10.presentation.screen.bottom_sheet_from_account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.summary10.R
import com.example.summary10.databinding.ItemCardBinding
import com.example.summary10.presentation.extension.loadImagesWithGlide
import com.example.summary10.presentation.model.Account
import com.example.summary10.presentation.model.CardType

class FromAccountRecyclerAdapter :
    ListAdapter<Account, FromAccountRecyclerAdapter.AccountViewHolder>(AccountDiffCallback) {

    private var onClick: ((Account) -> Unit)? = null

    inner class AccountViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) = with(binding) {
            tvAccountName.text = account.accountName
            tvAccountNumber.text = account.accountNumber

            when(account.cardType) {
                CardType.VISA.toString() -> ivCardType.loadImagesWithGlide(R.drawable.ic_visa)
                CardType.MASTERCARD.toString() -> ivCardType.loadImagesWithGlide(R.drawable.ic_mastercard)
            }

            itemView.setOnClickListener {
                onClick?.invoke(account)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AccountViewHolder(
        ItemCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun onClick(click: (Account) -> Unit) {
        this.onClick = click
    }


    private object AccountDiffCallback : DiffUtil.ItemCallback<Account>() {
        override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean =
            oldItem == newItem

    }
}