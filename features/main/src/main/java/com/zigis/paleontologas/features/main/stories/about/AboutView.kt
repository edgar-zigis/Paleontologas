package com.zigis.paleontologas.features.main.stories.about

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.features.main.R
import com.zigis.paleontologas.features.main.databinding.FragmentAboutBinding

class AboutView(
    context: Context
) : BaseView<AboutViewState, FragmentAboutBinding>(context) {

    var delegate: AboutViewDelegate? = null

    override var binding: FragmentAboutBinding? = FragmentAboutBinding.inflate(layoutInflater)

    init {
        with(requireBinding()) {
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            stylizeDescription()
            setContributors()
        }
        addView(requireBinding().root)
    }

    override fun render(state: AboutViewState) = with(requireBinding()) {
        applicationVersion.text = context.getString(
            R.string.version_placeholder,
            state.applicationVersion
        )
    }

    private fun stylizeDescription() = with(requireBinding()) {
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

        description3.text = spannableString
    }

    private fun setContributors() = with(requireBinding()) {
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

        italianContributor.photo.setImageResource(R.drawable.photo_juan_carlos_oliva)
        italianContributor.name.text = context.getString(R.string.app_contributor_7)
        italianContributor.contribution.text = context.getString(R.string.app_contributor_description_7)

        frenchContributor.photo.setImageResource(R.drawable.photo_anthony_favier)
        frenchContributor.name.text = context.getString(R.string.app_contributor_8)
        frenchContributor.contribution.text = context.getString(R.string.app_contributor_description_8)

        slovenianContributor.photo.setImageResource(R.drawable.photo_damjan_dovnik)
        slovenianContributor.name.text = context.getString(R.string.app_contributor_9)
        slovenianContributor.contribution.text = context.getString(R.string.app_contributor_description_9)

        brazilianContributor.photo.setImageResource(R.drawable.photo_mariana_leite)
        brazilianContributor.name.text = context.getString(R.string.app_contributor_10)
        brazilianContributor.contribution.text = context.getString(R.string.app_contributor_description_10)
    }
}