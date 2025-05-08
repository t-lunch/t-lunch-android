package ru.tinkoff.lunch.utils.views

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import ru.tinkoff.lunch.R

fun Fragment.showAlertDialog(
    title: String? = null,
    message: String? = null,
) {
    AlertDialog.Builder(requireContext())
        .setTitle(title ?: getString(R.string.default_alert_title))
        .setMessage(message ?: getString(R.string.default_alert_message))
        .setPositiveButton(R.string.default_alert_positive_button_message) { _, _ -> }
        .show()
}
