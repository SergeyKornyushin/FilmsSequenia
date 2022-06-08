package com.example.filmssequenia.kotlinapp.ui.utils

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.filmssequenia.R
import com.example.filmssequenia.utils.ResourcesUtils

/**
 * Держатель сообщений разных типов
 */
class MessagesHolder(
    lifecycleOwner: LifecycleOwner,
    view: View
) {

    private var snackBarHolder =
        DefaultSnackBarHolder(lifecycleOwner, view)
    private var infoSnackBarHolder = DefaultSnackBarHolder(lifecycleOwner, view)

    fun showNetworkError(message: String) {
        snackBarHolder.showLongDurationMessage(message)
    }

    fun showUnhiddenNetworkError(message: String, listener: () -> Unit) {
        snackBarHolder.showIndefiniteDurationMessage(
            message,
            ResourcesUtils.getString(R.string.repeat_snackbar),
            object : SnackBarActionListener {
                override fun onActionClick() {
                    listener()
                }
            }
        )
    }

    fun showMessage(message: String) {
        snackBarHolder.showLongDurationMessage(message, "OK")
    }

    fun showInfoMessage(message: String) {
        infoSnackBarHolder.showShortDurationMessage(message, "OK")
    }
}