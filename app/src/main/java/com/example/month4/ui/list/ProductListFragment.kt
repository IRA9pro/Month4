package com.example.month4.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.month4.data.api.RetrofitService
import com.example.month4.databinding.FragmentProductListBinding
import kotlinx.coroutines.launch

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRecycler()
    }

    private fun loadRecycler() {
        adapter = ProductAdapter(::onProductClick)
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val products = RetrofitService.api.getAllProducts()
                binding.rvProductList.adapter = adapter
                adapter.submitList(products)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onProductClick(id: Int) {
        val action = ProductListFragmentDirections.actionProductListFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }
}