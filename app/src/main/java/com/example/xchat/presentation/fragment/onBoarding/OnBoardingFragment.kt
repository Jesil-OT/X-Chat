package com.example.xchat.presentation.fragment.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.xchat.R
import com.example.xchat.databinding.FragmentOnboardingBinding
import com.example.xchat.presentation.utils.XchatDataClass.GenericActions
import com.example.xchat.presentation.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_onboarding) {

    private val binding by viewBinding(FragmentOnboardingBinding::bind)
    private val viewModel: OnBoardingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        setupClickListeners()
    }

    private fun setupClickListeners() = with(binding) {
        signInBt.setOnClickListener { viewModel.toSignUp() }
        loginTv.setOnClickListener { viewModel.toSignIn() }
    }

    private fun setUpObserver() {
        viewModel.action.observe(viewLifecycleOwner) {
            when (it) {
                is GenericActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }
}