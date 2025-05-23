package ru.tinkoff.lunch.utils.views

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showAlertSnackbar(message: String?) {
    Snackbar.make(this.requireView(), message ?: "Alert message", Snackbar.LENGTH_SHORT).show()
}
