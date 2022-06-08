package com.example.filmssequenia.kotlinapp.data.wrappers

/**
 * Extension функции для фильтрации входящих "null"
 * и приведению к заданному строковому значению
 */
fun Int?.avoidNullToString(string: String): String {
    return this?.toString() ?: string
}

/**
 * double так же приводится к формату #,#
 */
fun Double?.avoidNullToString(string: String): String {
    return if (this == null || this == -1.0) string else "%.1f".format(this)
}

fun String?.avoidNullToString(string: String): String {
    return if (this.isNullOrEmpty()) string else return this
}