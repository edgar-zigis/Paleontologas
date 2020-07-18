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
import kotlinx.android.synthetic.main.view_about_contributor.view.*

class AboutView(context: Context) : BaseView(context, R.layout.view_about) {

    override fun initialize() {
        title.text = context.getString(R.string.about_app)
        stylizeDescription()
        setContributors()
    }

    fun setApplicationVersion(version: String) {
        applicationVersion.text = context.getString(R.string.version_placeholder, version)
    }

    private fun setContributors() {
        applicationAuthor.photo.setImageResource(R.drawable.photo_edgar_zigis)
        applicationAuthor.name.text = context.getString(R.string.app_contributor_1)
        applicationAuthor.contribution.text = context.getString(R.string.app_contributor_description_1)

        inspirationContributor.photo.setImageResource(R.drawable.photo_andrej_spiridonov)
        inspirationContributor.name.text = context.getString(R.string.app_contributor_2)
        inspirationContributor.contribution.text = context.getString(R.string.app_contributor_description_2)

        englishContributor.photo.setImageResource(R.drawable.photo_matthew_gerke)
        englishContributor.name.text = context.getString(R.string.app_contributor_3)
        englishContributor.contribution.text = context.getString(R.string.app_contributor_description_3)

        lithuanianContributor.photo.setImageResource(R.drawable.photo_aukse_saule)
        lithuanianContributor.name.text = context.getString(R.string.app_contributor_4)
        lithuanianContributor.contribution.text = context.getString(R.string.app_contributor_description_4)

        spanishContributor.photo.setImageResource(R.drawable.photo_gerardo_lo_valvo)
        spanishContributor.name.text = context.getString(R.string.app_contributor_5)
        spanishContributor.contribution.text = context.getString(R.string.app_contributor_description_5)

        germanContributor.photo.setImageResource(R.drawable.photo_christian_klinnert)
        germanContributor.name.text = context.getString(R.string.app_contributor_6)
        germanContributor.contribution.text = context.getString(R.string.app_contributor_description_6)
    }

    private fun stylizeDescription() {
        val descriptionText = context.getString(R.string.about_app_text_2)
        val spannableString = SpannableString(descriptionText)

        listOf(
            context.getString(R.string.app_contributor_7)
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

        description3.text = spannableString
    }
}