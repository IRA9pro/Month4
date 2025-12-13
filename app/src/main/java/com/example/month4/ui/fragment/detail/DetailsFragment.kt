package com.example.month4.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil3.load
import coil3.request.crossfade
import com.example.month4.data.api.RetrofitService
import com.example.month4.data.model.ProductDto
import com.example.month4.data.repository.ProductRepository
import com.example.month4.databinding.FragmentDetailsBinding
import com.example.month4.ui.models.UIState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val navArgument: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadItemProduct(navArgument.productId)

        loadProduct()
    }

    fun loadProduct() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is UIState.Loading -> {}

                        is UIState.Success -> {
                            val item = state.data

                            with(binding) {
                                tvDescription.text = item.description
                                tvPrice.text = "$${item.price}"
                                tvRating.text =
                                    "★${item.rating!!.rate}  \uD83D\uDC64${item.rating!!.count}"
                                tvTitle.text = item.title
                                tvCategory.text = item.category

                                tvImage.load(item.image) {
                                    crossfade(true)
                                }
                            }
                        }

                        is UIState.Error -> Toast.makeText(
                            requireContext(),
                            state.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}