package com.example.filmssequenia.kotlinapp.ui.activities.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.filmssequenia.R

/**
 * Базовое Activity с навигацией
 */
abstract class BaseNavigationActivity(
    @LayoutRes contentLayoutId: Int = R.layout.activity_main
) : BaseActivity(contentLayoutId) {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigationController()
    }

    protected open fun getNavigationViewId() = R.id.main_controller

    private fun initNavigationController() {
        val navHost =
            supportFragmentManager.findFragmentById(getNavigationViewId()) as NavHostFragment
        navigationController = navHost.navController
    }

}