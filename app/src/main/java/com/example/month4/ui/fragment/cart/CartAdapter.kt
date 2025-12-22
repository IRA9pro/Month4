package com.example.month4.ui.fragment.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.month4.databinding.ItemCartBinding
import com.example.month4.domain.models.CartItem

class CartAdapter : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffUtilCallback()) {

    class CartDiffUtilCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(
            oldItem: CartItem,
            newItem: CartItem
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CartItem,
            newItem: CartItem
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {
        return CartViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }

    inner class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(cart: CartItem) {
            with(binding) {
                tvImage.load(cart.product.image) {
                    crossfade(true)
                }

                tvTitle.text = cart.product.title
                tvPrice.text = cart.product.price.toString()

                tvNumber.text = "x${cart.quantity}"
            }
        }
    }
}
