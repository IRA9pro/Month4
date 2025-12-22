package com.example.month4.ui.fragment.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.month4.databinding.FragmentCartBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModel()
    private val adapter = CartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCart.adapter = adapter
        observeData()

        binding.btnPlace.setOnClickListener {
            viewModel.checkout()
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.items.collect { items ->
                adapter.submitList(items)
                binding.btnPlace.isEnabled = items.isNotEmpty()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.total.collect { sum ->
                binding.tvPrice.text = "Total: $%.2f".format(sum)
            }
        }
    }

}