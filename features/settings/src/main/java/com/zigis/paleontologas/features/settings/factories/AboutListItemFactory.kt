package com.zigis.paleontologas.features.settings.factories

import com.zigis.paleontologas.features.settings.R
import com.zigis.paleontologas.features.settings.stories.about.list.AboutListItem

class AboutListItemFactory {

    fun getItems(): List<AboutListItem> {
        return listOf(
            AboutListItem(
                photoResId = R.drawable.photo_edgar_zigis,
                fullNameResId = R.string.app_contributor_1,
                contributionResId = R.string.app_contributor_description_1
            ),
            AboutListItem(
                photoResId = R.drawable.photo_andrej_spiridonov,
                fullNameResId = R.string.app_contributor_2,
                contributionResId = R.string.app_contributor_description_2
            ),
            AboutListItem(
                photoResId = R.drawable.photo_matthew_gerke,
                fullNameResId = R.string.app_contributor_3,
                contributionResId = R.string.app_contributor_description_3
            ),
            AboutListItem(
                photoResId = R.drawable.photo_aukse_saule,
                fullNameResId = R.string.app_contributor_4,
                contributionResId = R.string.app_contributor_description_4
            ),
            AboutListItem(
                photoResId = R.drawable.photo_gerardo_lo_valvo,
                fullNameResId = R.string.app_contributor_5,
                contributionResId = R.string.app_contributor_description_5
            ),
            AboutListItem(
                photoResId = R.drawable.photo_christian_klinnert,
                fullNameResId = R.string.app_contributor_6,
                contributionResId = R.string.app_contributor_description_6
            ),
            AboutListItem(
                photoResId = R.drawable.photo_juan_carlos_oliva,
                fullNameResId = R.string.app_contributor_7,
                contributionResId = R.string.app_contributor_description_7
            ),
            AboutListItem(
                photoResId = R.drawable.photo_anthony_favier,
                fullNameResId = R.string.app_contributor_8,
                contributionResId = R.string.app_contributor_description_8
            ),
            AboutListItem(
                photoResId = R.drawable.photo_damjan_dovnik,
                fullNameResId = R.string.app_contributor_9,
                contributionResId = R.string.app_contributor_description_9
            ),
            AboutListItem(
                photoResId = R.drawable.photo_mariana_leite,
                fullNameResId = R.string.app_contributor_10,
                contributionResId = R.string.app_contributor_description_10
            ),
            AboutListItem(
                photoResId = R.drawable.photo_elena_ozernova,
                fullNameResId = R.string.app_contributor_11,
                contributionResId = R.string.app_contributor_description_11
            )
        )
    }
}