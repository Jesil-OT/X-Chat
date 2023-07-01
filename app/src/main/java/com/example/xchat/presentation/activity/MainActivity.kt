package com.example.xchat.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.xchat.R
import com.example.xchat.databinding.ActivityMainBinding
import com.example.xchat.presentation.utils.hideView
import com.example.xchat.presentation.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.headerSection.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.chat_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.onBoardingFragment)
        )
        binding.apply {
            headerSection.root.setupWithNavController(navController, appBarConfiguration)
            chatBottomNavigation.setupWithNavController(navController)
            chatBottomNavigation.setOnItemReselectedListener { }
            // ChatBottomNavigation.getOrCreateBadge(R.id.chat).clearNumber()
        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.onBoardingFragment -> {
                    hideBottomNavigation()
                    hideToolbar()
                }

                else -> showBottomNavigation()
            }
        }
    }

    private fun showBottomNavigation() = with(binding) {
        chatBottomNavigation.showView()
    }

    private fun hideBottomNavigation() = with(binding) {
        TransitionManager.beginDelayedTransition(
            binding.root,
            Slide(Gravity.BOTTOM).excludeTarget(R.id.chat_nav_host_fragment, true)
        )
        chatBottomNavigation.hideView()
    }

    private fun hideToolbar() = with(binding) {
        headerSection.root.hideView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}