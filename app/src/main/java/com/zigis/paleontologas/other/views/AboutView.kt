package com.zigis.paleontologas.other.views

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.databinding.ViewAboutBinding

class AboutView(context: Context) : BaseView<ViewAboutBinding>(
    context,
    ViewAboutBinding.inflate(LayoutInflater.from(context))
) {

    override val titleTextResId: Int = R.string.about_app

    override fun initialize() {
        stylizeDescription()
        setContributors()
    }

    fun setApplicationVersion(version: String) {
        viewBinding.applicationVersion.text = context.getString(R.string.version_placeholder, version)
    }

    private fun setContributors() {
        viewBinding.applicationAuthor.photo.setImageResource(R.drawable.photo_edgar_zigis)
        viewBinding.applicationAuthor.name.text = context.getString(R.string.app_contributor_1)
        viewBinding.applicationAuthor.contribution.text = context.getString(R.string.app_contributor_description_1)

        viewBinding.inspirationContributor.photo.setImageResource(R.drawable.photo_andrej_spiridonov)
        viewBinding.inspirationContributor.name.text = context.getString(R.string.app_contributor_2)
        viewBinding.inspirationContributor.contribution.text = context.getString(R.string.app_contributor_description_2)

        viewBinding.englishContributor.photo.setImageResource(R.drawable.photo_matthew_gerke)
        viewBinding.englishContributor.name.text = context.getString(R.string.app_contributor_3)
        viewBinding.englishContributor.contribution.text = context.getString(R.string.app_contributor_description_3)

        viewBinding.lithuanianContributor.photo.setImageResource(R.drawable.photo_aukse_saule)
        viewBinding.lithuanianContributor.name.text = context.getString(R.string.app_contributor_4)
        viewBinding.lithuanianContributor.contribution.text = context.getString(R.string.app_contributor_description_4)

        viewBinding.spanishContributor.photo.setImageResource(R.drawable.photo_gerardo_lo_valvo)
        viewBinding.spanishContributor.name.text = context.getString(R.string.app_contributor_5)
        viewBinding.spanishContributor.contribution.text = context.getString(R.string.app_contributor_description_5)

        viewBinding.germanContributor.photo.setImageResource(R.drawable.photo_christian_klinnert)
        viewBinding.germanContributor.name.text = context.getString(R.string.app_contributor_6)
        viewBinding.germanContributor.contribution.text = context.getString(R.string.app_contributor_description_6)

        viewBinding.italianContributor.photo.setImageResource(R.drawable.photo_juan_carlos_oliva)
        viewBinding.italianContributor.name.text = context.getString(R.string.app_contributor_7)
        viewBinding.italianContributor.contribution.text = context.getString(R.string.app_contributor_description_7)

        viewBinding.frenchContributor.photo.setImageResource(R.drawable.photo_anthony_favier)
        viewBinding.frenchContributor.name.text = context.getString(R.string.app_contributor_8)
        viewBinding.frenchContributor.contribution.text = context.getString(R.string.app_contributor_description_8)

        viewBinding.slovenianContributor.photo.setImageResource(R.drawable.photo_damjan_dovnik)
        viewBinding.slovenianContributor.name.text = context.getString(R.string.app_contributor_9)
        viewBinding.slovenianContributor.contribution.text = context.getString(R.string.app_contributor_description_9)

        viewBinding.brazilianContributor.photo.setImageResource(R.drawable.photo_mariana_leite)
        viewBinding.brazilianContributor.name.text = context.getString(R.string.app_contributor_10)
        viewBinding.brazilianContributor.contribution.text = context.getString(R.string.app_contributor_description_10)
    }

    private fun stylizeDescription() {
        val descriptionText = context.getString(R.string.about_app_text_2)
        val spannableString = SpannableString(descriptionText)

        listOf(
            context.getString(R.string.app_contributor_11)
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

        viewBinding.description3.text = spannableString
    }
}