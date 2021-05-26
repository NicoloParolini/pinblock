package com.livingcode.test.pinblock.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.livingcode.test.pinblock.R
import com.livingcode.test.pinblock.databinding.BlockFragmentBinding

class BlockFragment : Fragment() {

    companion object {
        fun newInstance() = BlockFragment()
    }

    private lateinit var viewModel: BlockViewModel
    private lateinit var binding: BlockFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BlockFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BlockViewModel::class.java)

        // Technically, this could be by databinding as well.
        // Setting it here allows for more flexibility in error handling.
        viewModel.error.observe(this, { error ->
            if (error) {
                showError()
            } else {
                hideError()
            }
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun showError() {
        binding.blockfragmentError.setText(R.string.blockError)
        binding.blockfragmentError.visibility = View.VISIBLE
        binding.blockfragmentBlock.visibility = View.GONE
    }

    private fun hideError() {
        binding.blockfragmentError.visibility = View.GONE
        binding.blockfragmentBlock.visibility = View.VISIBLE
    }

}