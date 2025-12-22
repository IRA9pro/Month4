package com.example.month4.ui.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.month4.databinding.ItemProductBinding
import com.example.month4.domain.models.Product

class ProductAdapter(
    private val onProductClick: (Int) -> Unit,
    private val onAddCartClick: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffUtilCallback()) {

    class ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(product: Product) {
            with(binding) {
                tvImage.load(product.image) {
                    crossfade(true)
                }

                tvTitle.text = product.title
                tvPrice.text = "$${product.price}"
                tvRating.text = "★${product.rating!!.rate}"

                root.setOnClickListener { onProductClick(product.id) }
                tvImage.setOnClickListener { onAddCartClick(product) }
            }
        }
    }
}