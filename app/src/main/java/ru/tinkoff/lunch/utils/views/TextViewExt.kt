package ru.tinkoff.lunch.utils.views

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

fun makeTextLink(
    textView: TextView,
    string: String,
    underlined: Boolean = false,
    color: Int = Color.BLUE,
    typeface: Typeface = Typeface.DEFAULT_BOLD,
    action: (() -> Unit)? = null
) {
    val spannableString = SpannableString(textView.text)
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            action?.invoke()
        }
        override fun updateDrawState(drawState: TextPaint) {
            super.updateDrawState(drawState)
            drawState.isUnderlineText = underlined
            drawState.color = color
            drawState.typeface = typeface
        }
    }
    val index = spannableString.indexOf(string)
    spannableString.setSpan(clickableSpan, index, index + string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.apply {
        text = spannableString
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT
    }
}
