package com.example.booksdonationapp.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.ActivityMainBinding
import com.example.booksdonationapp.presentation.commun.BaseActivity
import com.example.booksdonationapp.presentation.ui.feeds.FeedsFragment
import com.example.booksdonationapp.presentation.utils.*

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val isConnected = false
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun getLayoutId(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun initView() {
        setUpNav()


        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {

                when (f) {
                    is FeedsFragment -> {
                        showNavigation()
                    }
                    else -> {
                        hideNavigation()
                    }
                }
            }
        }, true)


        if (isConnected) {
            navHostFragment.findNavController().setGraph(R.navigation.home_graph)

        } else {
            navHostFragment.findNavController().setGraph(R.navigation.start_up_graph)
        }
        binding.bottomNavigationView.background = null
    }

    override fun initData() {

    }


    fun hideNavigation() {
        binding.bottomNavigationView.gone()
        binding.bottomAppBar.gone()
        binding.fab.gone()
        hideActionBar()
    }

    private fun hideActionBar() {
        binding.activityMainToolBar.gone()
    }

    fun showNavigation() {
        binding.bottomNavigationView.visible()
        binding.bottomAppBar.visible()
        binding.fab.visible()
        showActionBar()
    }

    private fun showActionBar() {
        binding.activityMainToolBar.visible()
    }

    private fun setUpNav() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navController
        )

        ToolbarShared.getInstance().toolbarTitle.reObserve(this, {
            updateToolbarTitle(it)
        })
        navController.addOnDestinationChangedListener { _, destination, _ ->
            onNavigate(destination)
        }
        // Setting Navigation Controller with the BottomNavigationView
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun updateToolbarTitle(title: String) {

        binding.toolbarTitle.text = title
    }

    private fun onNavigate(dest: NavDestination) {
        val title = dest.label as? String?

        title?.let {
            updateToolbarTitle(it)
        }
        hideKeyBoard()

    }
}