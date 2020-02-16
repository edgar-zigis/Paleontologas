package com.zigis.paleontologas.other.views

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.view_about.view.*

class AboutView(context: Context) : BaseView(context, R.layout.view_about) {

    override fun initialize() {
        title.text = context.getString(R.string.about_app)
        stylizeDescription()
    }

    fun setApplicationVersion(version: String) {
        applicationVersion.text = context.getString(R.string.version_placeholder, version)
    }

    private fun stylizeDescription() {
        val descriptionText = context.getString(R.string.about_app_text)
        val spannableString = SpannableString(descriptionText)

        listOf(
            context.getString(R.string.app_contributor_1),
            context.getString(R.string.app_contributor_2),
            context.getString(R.string.app_contributor_3),
            context.getString(R.string.app_contributor_4),
            context.getString(R.string.app_contributor_5),
            context.getString(R.string.app_contributor_6)
        ).forEach {
            spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                descriptionText.indexOf(it),
                descriptionText.indexOf(it) + it.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        val email = context.getString(R.string.contact_email)
        spannableString.setSpan(
            StyleSpan(Typeface.ITALIC),
            descriptionText.indexOf(email),
            descriptionText.indexOf(email) + email.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        description.text = spannableString
    }
}