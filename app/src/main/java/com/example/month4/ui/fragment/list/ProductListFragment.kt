package com.example.month4.ui.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.month4.R
import com.example.month4.databinding.FragmentProductListBinding
import com.example.month4.domain.models.Product
import com.example.month4.ui.models.UIState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var adapter: ProductAdapter
    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ProductAdapter(::onProductClick, ::onAddCartClick)
        binding.rvProductList.adapter = adapter
        observeState()

        binding.btnCart.setOnClickListener {
            findNavController()
                .navigate(R.id.action_productListFragment_to_cartFragment)
        }

    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is UIState.Loading -> {
                            binding.rvProductList.isVisible = false
                        }

                        is UIState.Success -> {
                            binding.rvProductList.isVisible = true
                            adapter.submitList(state.data)
                        }

                        is UIState.Error -> {
                            binding.rvProductList.isVisible = false
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun onProductClick(id: Int) {
        val action = ProductListFragmentDirections.Companion.actionProductListFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }

    private fun onAddCartClick(product: Product) {
        viewModel.addToCart(product)
        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
    }
}