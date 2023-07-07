package com.example.xchat.presentation.fragment.onBoarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.xchat.presentation.utils.SingleLiveEvent
import com.example.xchat.presentation.utils.XchatDataClass.GenericActions

class OnBoardingViewModel : ViewModel() {

    private val _action = SingleLiveEvent<GenericActions>()
    val action: LiveData<GenericActions> = _action

    fun toSignUp() {
        _action.value = GenericActions.Navigate(OnBoardingFragmentDirections.toSignUpFragment())
    }

    fun toSignIn() {
        _action.value = GenericActions.Navigate(OnBoardingFragmentDirections.toSignInFragment())
    }
}