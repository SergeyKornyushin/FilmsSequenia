package com.example.filmssequenia.kotlinapp.ui.fragments.base

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

/**
 * Базовый Fragment с навигацией
 */
open class BaseNavigationFragment(
    @androidx.annotation.LayoutRes contentLayoutId: Int
) : BaseFragment(contentLayoutId) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigationController()
    }

    override fun onBackPressed(shouldHideKeyboard: Boolean) {
        super.onBackPressed(shouldHideKeyboard)

        val isPopped = navController.popBackStack()

        if (isPopped) {
            return
        }

        activity?.finish()
    }

    protected fun navigate(actionId: Int) {
        navController.navigate(actionId)
    }

    protected fun navigate(action: NavDirections) {
        navController.navigate(action)
    }

    private fun initNavigationController() {
        navController = findNavController()
    }
}