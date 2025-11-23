package com.zigis.paleontologas.features.library.data.stores

interface LifeFormDataStoreInterface {
    suspend fun getLifeForms(periodId: Int): List<LifeFormDataStore.DTO>
}

class LifeFormDataStore : LifeFormDataStoreInterface {

    data class DTO(
        val id: Int,
        val thumbnailId: String,
        val artworkId: String,
        val artworkAuthor: String?,
        val additionalArtworkId: String?,
        val additionalArtworkAuthor: String?,
        val titleSlug: String,
        val descriptionSlug: String,
        val timeScale: String,
        val order: Int = 0
    )

    override suspend fun getLifeForms(periodId: Int): List<DTO> {
        return when (periodId) {
            2 -> getArcheanLifeForms()
            3 -> getProterozoicLifeForms()
            4 -> getCambrianLifeForms()
            5 -> getOrdovicianLifeForms()
            6 -> getSilurianLifeForms()
            7 -> getDevonianLifeForms()
            8 -> getCarboniferousLifeForms()
            9 -> getPermianLifeForms()
            10 -> getTriassicLifeForms()
            11 -> getJurassicLifeForms()
            12 -> getCretaceousLifeForms()
            13 -> getPaleogeneLifeForms()
            14 -> getNeogeneLifeForms()
            15 -> getQuaternaryLifeForms()
            else -> emptyList()
        }
    }

    private fun getArcheanLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 1,
                thumbnailId = "item_archean_banded_iron_formation_thumb",
                artworkId = "item_archean_banded_iron_formation",
                artworkAuthor = null,
                additionalArtworkId = "item_archean_banded_iron_formation_info",
                additionalArtworkAuthor = null,
                titleSlug = "archean_banded_iron_formation",
                descriptionSlug = "archean_banded_iron_formation_description",
                timeScale = "3850.0–0",
                order = 1
            ),
            DTO(
                id = 2,
                thumbnailId = "item_archean_stromatolites_thumb",
                artworkId = "item_archean_stromatolites",
                artworkAuthor = null,
                additionalArtworkId = "item_archean_stromatolites_info",
                additionalArtworkAuthor = null,
                titleSlug = "archean_stromatolites",
                descriptionSlug = "archean_stromatolites_description",
                timeScale = "3700.0-0",
                order = 2
            ),
            DTO(
                id = 3,
                thumbnailId = "item_archean_primaevifilum_amoenum_thumb",
                artworkId = "item_archean_primaevifilum_amoenum",
                artworkAuthor = null,
                additionalArtworkId = "item_archean_primaevifilum_amoenum_info",
                additionalArtworkAuthor = null,
                titleSlug = "archean_primaevifilum_amoenum",
                descriptionSlug = "archean_primaevifilum_amoenum_description",
                timeScale = "3465.0–2000.0",
                order = 3
            ),
            DTO(
                id = 4,
                thumbnailId = "item_archean_acritarch_thumb",
                artworkId = "item_archean_acritarch",
                artworkAuthor = null,
                additionalArtworkId = "item_archean_acritarch_info",
                additionalArtworkAuthor = null,
                titleSlug = "archean_acritarcha",
                descriptionSlug = "archean_acritarcha_description",
                timeScale = "3200.0–0",
                order = 4
            )
        )
    }

    private fun getProterozoicLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 135,
                thumbnailId = "item_proterozoic_grypania_spiralis_thumb",
                artworkId = "item_proterozoic_grypania_spiralis",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_grypania_spiralis",
                descriptionSlug = "proterozoic_grypania_spiralis_description",
                timeScale = "1870.0-600.0",
                order = 1
            ),
            DTO(
                id = 123,
                thumbnailId = "item_proterozoic_bangiomorpha_pubescens_thumb",
                artworkId = "item_proterozoic_bangiomorpha_pubescens",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_bangiomorpha_pubescens",
                descriptionSlug = "proterozoic_bangiomorpha_pubescens_description",
                timeScale = "1200.0-?",
                order = 2
            ),
            DTO(
                id = 126,
                thumbnailId = "item_proterozoic_dickinsonia_thumb",
                artworkId = "item_proterozoic_dickinsonia",
                artworkAuthor = "John Sibbick",
                additionalArtworkId = "item_proterozoic_dickinsonia_info",
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_dickinsonia",
                descriptionSlug = "proterozoic_dickinsonia_description",
                timeScale = "635.0-542.0",
                order = 3
            ),
            DTO(
                id = 124,
                thumbnailId = "item_proterozoic_nemiana_simplex_thumb",
                artworkId = "item_proterozoic_nemiana_simplex",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_nemiana_simplex",
                descriptionSlug = "proterozoic_nemiana_simplex_description",
                timeScale = "635.0-542.0",
                order = 4
            ),
            DTO(
                id = 125,
                thumbnailId = "item_proterozoic_tribrachidium_heraldicum_thumb",
                artworkId = "item_proterozoic_tribrachidium_heraldicum",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_tribrachidium_heraldicum",
                descriptionSlug = "proterozoic_tribrachidium_heraldicum_description",
                timeScale = "635.0-542.0",
                order = 5
            ),
            DTO(
                id = 127,
                thumbnailId = "item_proterozoic_cloudina_thumb",
                artworkId = "item_proterozoic_cloudina",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_cloudina",
                descriptionSlug = "proterozoic_cloudina_description",
                timeScale = "635.0-505.0",
                order = 6
            ),
            DTO(
                id = 128,
                thumbnailId = "item_proterozoic_charnia_thumb",
                artworkId = "item_proterozoic_charnia",
                artworkAuthor = "Marko Georgievski",
                additionalArtworkId = "item_proterozoic_charnia_info",
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_charnia",
                descriptionSlug = "proterozoic_charnia_description",
                timeScale = "580.0-542.0",
                order = 7
            ),
            DTO(
                id = 136,
                thumbnailId = "item_proterozoic_spriggina_thumb",
                artworkId = "item_proterozoic_spriggina",
                artworkAuthor = "Paleozoo",
                additionalArtworkId = "item_proterozoic_spriggina_info",
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_spriggina",
                descriptionSlug = "proterozoic_spriggina_description",
                timeScale = "555.0-550.0",
                order = 8
            ),
            DTO(
                id = 137,
                thumbnailId = "item_proterozoic_kimberella_thumb",
                artworkId = "item_proterozoic_kimberella",
                artworkAuthor = "Oleg Kuznetsov",
                additionalArtworkId = "item_proterozoic_kimberella_info",
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_kimberella",
                descriptionSlug = "proterozoic_kimberella_description",
                timeScale = "555.0-550.0",
                order = 9
            ),
            DTO(
                id = 138,
                thumbnailId = "item_proterozoic_rangea_thumb",
                artworkId = "item_proterozoic_rangea",
                artworkAuthor = null,
                additionalArtworkId = "item_proterozoic_rangea_info",
                additionalArtworkAuthor = null,
                titleSlug = "proterozoic_rangea",
                descriptionSlug = "proterozoic_rangea_description",
                timeScale = "550.0-542.0",
                order = 10
            )
        )
    }

    private fun getCambrianLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 5,
                thumbnailId = "item_cambrian_treptichnus_pedum_thumb",
                artworkId = "item_cambrian_treptichnus_pedum",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_treptichnus_pedum",
                descriptionSlug = "cambrian_treptichnus_pedum_description",
                timeScale = "538.0-24.0",
                order = 1
            ),
            DTO(
                id = 7,
                thumbnailId = "item_cambrian_foraminifera_thumb",
                artworkId = "item_cambrian_foraminifera",
                artworkAuthor = "Howard Spero",
                additionalArtworkId = "item_cambrian_foraminifera_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_foraminifera",
                descriptionSlug = "cambrian_foraminifera_description",
                timeScale = "538.0-0",
                order = 2
            ),
            DTO(
                id = 9,
                thumbnailId = "item_cambrian_conodonta_thumb",
                artworkId = "item_cambrian_conodonta",
                artworkAuthor = null,
                additionalArtworkId = "item_cambrian_conodonta_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_conodonta",
                descriptionSlug = "cambrian_conodonta_description",
                timeScale = "530.0-171.6",
                order = 3
            ),
            DTO(
                id = 8,
                thumbnailId = "item_cambrian_archaeocyatha_thumb",
                artworkId = "item_cambrian_archaeocyatha",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_archaeocyatha",
                descriptionSlug = "cambrian_archaeocyatha_description",
                timeScale = "525.0-505.0",
                order = 4
            ),
            DTO(
                id = 132,
                thumbnailId = "item_cambrian_omnidens_thumb",
                artworkId = "item_cambrian_omnidens",
                artworkAuthor = "José Antonio PA",
                additionalArtworkId = "item_cambrian_omnidens_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_omnidens",
                descriptionSlug = "cambrian_omnidens_description",
                timeScale = "521.0-509.0",
                order = 5
            ),
            DTO(
                id = 10,
                thumbnailId = "item_cambrian_trilobita_thumb",
                artworkId = "item_cambrian_trilobita",
                artworkAuthor = null,
                additionalArtworkId = "item_cambrian_trilobita_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_trilobita",
                descriptionSlug = "cambrian_trilobita_description",
                timeScale = "521.0-252.6",
                order = 6
            ),
            DTO(
                id = 11,
                thumbnailId = "item_cambrian_myllokunmingia_thumb",
                artworkId = "item_cambrian_myllokunmingia",
                artworkAuthor = null,
                additionalArtworkId = "item_cambrian_myllokunmingia_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_myllokunmingia",
                descriptionSlug = "cambrian_myllokunmingia_description",
                timeScale = "520.0-516.0",
                order = 7
            ),
            DTO(
                id = 12,
                thumbnailId = "item_cambrian_wiwaxia_thumb",
                artworkId = "item_cambrian_wiwaxia",
                artworkAuthor = "Tiko",
                additionalArtworkId = "item_cambrian_wiwaxia_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_wiwaxia",
                descriptionSlug = "cambrian_wiwaxia_description",
                timeScale = "520.0-505.0",
                order = 8
            ),
            DTO(
                id = 13,
                thumbnailId = "item_cambrian_anomalocaris_thumb",
                artworkId = "item_cambrian_anomalocaris",
                artworkAuthor = "Quade Paul",
                additionalArtworkId = "item_cambrian_anomalocaris_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_anomalocaris",
                descriptionSlug = "cambrian_anomalocaris_description",
                timeScale = "520.0-499.0",
                order = 9
            ),
            DTO(
                id = 6,
                thumbnailId = "item_cambrian_hallucigenia_thumb",
                artworkId = "item_cambrian_hallucigenia",
                artworkAuthor = null,
                additionalArtworkId = "item_cambrian_hallucigenia_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_hallucigenia",
                descriptionSlug = "cambrian_hallucigenia_description",
                timeScale = "516.0-505.0",
                order = 10
            ),
            DTO(
                id = 14,
                thumbnailId = "item_cambrian_pikaia_thumb",
                artworkId = "item_cambrian_pikaia",
                artworkAuthor = null,
                additionalArtworkId = "item_cambrian_pikaia_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_pikaia",
                descriptionSlug = "cambrian_pikaia_description",
                timeScale = "513.0-505.0",
                order = 11
            ),
            DTO(
                id = 15,
                thumbnailId = "item_cambrian_opabinia_thumb",
                artworkId = "item_cambrian_opabinia",
                artworkAuthor = null,
                additionalArtworkId = "item_cambrian_opabinia_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_opabinia",
                descriptionSlug = "cambrian_opabinia_description",
                timeScale = "513.0-505.0",
                order = 12
            ),
            DTO(
                id = 16,
                thumbnailId = "item_cambrian_marrella_thumb",
                artworkId = "item_cambrian_marrella",
                artworkAuthor = "Tiko",
                additionalArtworkId = "item_cambrian_marrella_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_marrella",
                descriptionSlug = "cambrian_marrella_description",
                timeScale = "513.0-505.0",
                order = 13
            ),
            DTO(
                id = 131,
                thumbnailId = "item_cambrian_nectocaris_thumb",
                artworkId = "item_cambrian_nectocaris",
                artworkAuthor = "Marianne Collins",
                additionalArtworkId = "item_cambrian_nectocaris_info",
                additionalArtworkAuthor = null,
                titleSlug = "cambrian_nectocaris",
                descriptionSlug = "cambrian_nectocaris_description",
                timeScale = "508.0-497.0",
                order = 14
            )
        )
    }

    private fun getOrdovicianLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 21,
                thumbnailId = "item_ordovician_rugosa_thumb",
                artworkId = "item_ordovician_rugosa",
                artworkAuthor = null,
                additionalArtworkId = "item_ordovician_rugosa_info",
                additionalArtworkAuthor = null,
                titleSlug = "ordovician_rugosa",
                descriptionSlug = "ordovician_rugosa_description",
                timeScale = "486.0-251.902",
                order = 1
            ),
            DTO(
                id = 22,
                thumbnailId = "item_ordovician_tabulata_thumb",
                artworkId = "item_ordovician_tabulata",
                artworkAuthor = null,
                additionalArtworkId = "item_ordovician_tabulata_info",
                additionalArtworkAuthor = null,
                titleSlug = "ordovician_tabulata",
                descriptionSlug = "ordovician_tabulata_description",
                timeScale = "486.0-251.902",
                order = 2
            ),
            DTO(
                id = 133,
                thumbnailId = "item_ordovician_aegirocassis_thumb",
                artworkId = "item_ordovician_aegirocassis",
                artworkAuthor = "Gabuded",
                additionalArtworkId = "item_ordovician_aegirocassis_info",
                additionalArtworkAuthor = "The Natural Canvas",
                titleSlug = "ordovician_aegirocassis",
                descriptionSlug = "ordovician_aegirocassis_description",
                timeScale = "480.0-470.0",
                order = 3
            ),
            DTO(
                id = 17,
                thumbnailId = "item_ordovician_orthoceras_thumb",
                artworkId = "item_ordovician_orthoceras",
                artworkAuthor = "Felipe Arias",
                additionalArtworkId = "item_ordovician_orthoceras_info",
                additionalArtworkAuthor = null,
                titleSlug = "ordovician_orthoceras",
                descriptionSlug = "ordovician_orthoceras_description",
                timeScale = "471.8-458.0",
                order = 4
            ),
            DTO(
                id = 134,
                thumbnailId = "item_ordovician_cameroceras_thumb",
                artworkId = "item_ordovician_cameroceras",
                artworkAuthor = "Gojira Studios",
                additionalArtworkId = "item_ordovician_cameroceras_info",
                additionalArtworkAuthor = null,
                titleSlug = "ordovician_cameroceras",
                descriptionSlug = "ordovician_cameroceras_description",
                timeScale = "470.0-460.0",
                order = 5
            ),
            DTO(
                id = 18,
                thumbnailId = "item_ordovician_eurypterida_thumb",
                artworkId = "item_ordovician_eurypterida",
                artworkAuthor = "Patrick Lynch",
                additionalArtworkId = "item_ordovician_eurypterida_info",
                additionalArtworkAuthor = null,
                titleSlug = "ordovician_pentecopterus",
                descriptionSlug = "ordovician_pentecopterus_description",
                timeScale = "467.3-458.4",
                order = 6
            ),
            DTO(
                id = 20,
                thumbnailId = "item_ordovician_astraspis_thumb",
                artworkId = "item_ordovician_astraspis",
                artworkAuthor = "Nobu Tamura",
                additionalArtworkId = "item_ordovician_astraspis_info",
                additionalArtworkAuthor = null,
                titleSlug = "ordovician_astraspis",
                descriptionSlug = "ordovician_astraspis_description",
                timeScale = "467.0-443.7",
                order = 7
            ),
            DTO(
                id = 19,
                thumbnailId = "item_ordovician_sacabambaspis_janvieri_thumb",
                artworkId = "item_ordovician_sacabambaspis_janvieri",
                artworkAuthor = "Nobu Tamura",
                additionalArtworkId = "item_ordovician_sacabambaspis_janvieri_info",
                additionalArtworkAuthor = null,
                titleSlug = "ordovician_sacabambaspis",
                descriptionSlug = "ordovician_sacabambaspis_description",
                timeScale = "466.0-449.5",
                order = 8
            )
        )
    }

    private fun getSilurianLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 23,
                thumbnailId = "item_silurian_eurypterus_thumb",
                artworkId = "item_silurian_eurypterus",
                artworkAuthor = null,
                additionalArtworkId = "item_silurian_eurypterus_info",
                additionalArtworkAuthor = null,
                titleSlug = "silurian_eurypterus",
                descriptionSlug = "silurian_eurypterus_description",
                timeScale = "432.0-418.1",
                order = 1
            ),
            DTO(
                id = 24,
                thumbnailId = "item_silurian_pneumodesmus_thumb",
                artworkId = "item_silurian_pneumodesmus",
                artworkAuthor = null,
                additionalArtworkId = "item_silurian_pneumodesmus_info",
                additionalArtworkAuthor = null,
                titleSlug = "silurian_pneumodesmus",
                descriptionSlug = "silurian_pneumodesmus_description",
                timeScale = "428.2-412.3",
                order = 2
            ),
            DTO(
                id = 179,
                thumbnailId = "item_silurian_pterygotus_thumb",
                artworkId = "item_silurian_pterygotus",
                artworkAuthor = "Paleobiome",
                additionalArtworkId = "item_silurian_pterygotus_info",
                additionalArtworkAuthor = null,
                titleSlug = "silurian_pterygotus",
                descriptionSlug = "silurian_pterygotus_description",
                timeScale = "428.0–372.2",
                order = 3
            ),
            DTO(
                id = 29,
                thumbnailId = "item_devonian_prototaxites_thumb",
                artworkId = "item_devonian_prototaxites",
                artworkAuthor = null,
                additionalArtworkId = "item_devonian_prototaxites_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_prototaxites",
                descriptionSlug = "devonian_prototaxites_description",
                timeScale = "426.2-360.7",
                order = 4
            ),
            DTO(
                id = 28,
                thumbnailId = "item_silurian_entelognathus_thumb",
                artworkId = "item_silurian_entelognathus",
                artworkAuthor = "Julio Lacerda",
                additionalArtworkId = "item_silurian_entelognathus_info",
                additionalArtworkAuthor = null,
                titleSlug = "silurian_entelognathus",
                descriptionSlug = "silurian_entelognathus_description",
                timeScale = "424.0-419.0",
                order = 5
            ),
            DTO(
                id = 26,
                thumbnailId = "item_silurian_baragwanathia_thumb",
                artworkId = "item_silurian_baragwanathia",
                artworkAuthor = null,
                additionalArtworkId = "item_silurian_baragwanathia_info",
                additionalArtworkAuthor = null,
                titleSlug = "silurian_baragwanathia",
                descriptionSlug = "silurian_baragwanathia_description",
                timeScale = "422.9-391.9",
                order = 6
            ),
            DTO(
                id = 25,
                thumbnailId = "item_silurian_cooksonia_thumb",
                artworkId = "item_silurian_cooksonia",
                artworkAuthor = "Simona Warmer",
                additionalArtworkId = "item_silurian_cooksonia_info",
                additionalArtworkAuthor = null,
                titleSlug = "silurian_cooksonia",
                descriptionSlug = "silurian_cooksonia_description",
                timeScale = "422.9-383.7",
                order = 7
            ),
            DTO(
                id = 27,
                thumbnailId = "item_silurian_acanthodii_thumb",
                artworkId = "item_silurian_acanthodii",
                artworkAuthor = "Mikhail Tichonov",
                additionalArtworkId = "item_silurian_acanthodii_info",
                additionalArtworkAuthor = null,
                titleSlug = "silurian_acanthodii",
                descriptionSlug = "silurian_acanthodii_description",
                timeScale = "422.9-298.9",
                order = 8
            )
        )
    }

    private fun getDevonianLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 176,
                thumbnailId = "item_devonian_brontoscorpio_thumb",
                artworkId = "item_devonian_brontoscorpio",
                artworkAuthor = "Masatto Hattori",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "devonian_brontoscorpio",
                descriptionSlug = "devonian_brontoscorpio_description",
                timeScale = "416.0-412.0",
                order = 1
            ),
            DTO(
                id = 139,
                thumbnailId = "item_devonian_jaekelopterus_thumb",
                artworkId = "item_devonian_jaekelopterus",
                artworkAuthor = "Dani Navarro",
                additionalArtworkId = "item_devonian_jaekelopterus_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_jaekelopterus",
                descriptionSlug = "devonian_jaekelopterus_description",
                timeScale = "410.8-402.5",
                order = 2
            ),
            DTO(
                id = 175,
                thumbnailId = "item_devonian_rhynia_thumb",
                artworkId = "item_devonian_rhynia",
                artworkAuthor = "KnorkerDrawings",
                additionalArtworkId = "item_devonian_rhynia_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_rhynia",
                descriptionSlug = "devonian_rhynia_description",
                timeScale = "410.0-400.0",
                order = 3
            ),
            DTO(
                id = 30,
                thumbnailId = "item_devonian_wattieza_thumb",
                artworkId = "item_devonian_wattieza",
                artworkAuthor = "Science Photo Library",
                additionalArtworkId = "item_devonian_wattieza_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_wattieza",
                descriptionSlug = "devonian_wattieza_description",
                timeScale = "388.1-383.7",
                order = 4
            ),
            DTO(
                id = 36,
                thumbnailId = "item_devonian_bothriolepis_thumb",
                artworkId = "item_devonian_bothriolepis",
                artworkAuthor = "I. Béchard",
                additionalArtworkId = "item_devonian_bothriolepis_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_bothriolepis",
                descriptionSlug = "devonian_bothriolepis_description",
                timeScale = "387.0-360.7",
                order = 5
            ),
            DTO(
                id = 180,
                thumbnailId = "item_devonian_panderichthys_thumb",
                artworkId = "item_devonian_panderichthys",
                artworkAuthor = "Mario Lanzas",
                additionalArtworkId = "item_devonian_panderichthys_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_panderichthys",
                descriptionSlug = "devonian_panderichthys_description",
                timeScale = "385.0–380.0",
                order = 6
            ),
            DTO(
                id = 141,
                thumbnailId = "item_devonian_calamophyton_thumb",
                artworkId = "item_devonian_calamophyton",
                artworkAuthor = "Peter Giesen and Christopher M. Berry",
                additionalArtworkId = "item_devonian_calamophyton_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_calamophyton",
                descriptionSlug = "devonian_calamophyton_description",
                timeScale = "385.0-375.0",
                order = 7
            ),
            DTO(
                id = 142,
                thumbnailId = "item_devonian_elkinsia_thumb",
                artworkId = "item_devonian_elkinsia",
                artworkAuthor = "Paleobiome",
                additionalArtworkId = "item_devonian_elkinsia_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_elkinsia",
                descriptionSlug = "devonian_elkinsia_description",
                timeScale = "385.0-375.0",
                order = 8
            ),
            DTO(
                id = 32,
                thumbnailId = "item_devonian_tiktaalik_thumb",
                artworkId = "item_devonian_tiktaalik",
                artworkAuthor = "Tyler Keillor & Beth Rooney",
                additionalArtworkId = "item_devonian_tiktaalik_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_tiktaalik",
                descriptionSlug = "devonian_tiktaalik_description",
                timeScale = "383.7-376.1",
                order = 9
            ),
            DTO(
                id = 31,
                thumbnailId = "item_devonian_archaeopteris_thumb",
                artworkId = "item_devonian_archaeopteris",
                artworkAuthor = null,
                additionalArtworkId = "item_devonian_archaeopteris_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_archaeopteris",
                descriptionSlug = "devonian_archaeopteris_description",
                timeScale = "383.7-318.1",
                order = 10
            ),
            DTO(
                id = 38,
                thumbnailId = "item_devonian_dunkleosteus_thumb",
                artworkId = "item_devonian_dunkleosteus",
                artworkAuthor = null,
                additionalArtworkId = "item_devonian_dunkleosteus_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_dunkleosteus",
                descriptionSlug = "devonian_dunkleosteus_description",
                timeScale = "382.0-358.0",
                order = 11
            ),
            DTO(
                id = 35,
                thumbnailId = "item_devonian_materpiscis_thumb",
                artworkId = "item_devonian_materpiscis",
                artworkAuthor = "Julio Lacerda",
                additionalArtworkId = "item_devonian_materpiscis_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_materpiscis",
                descriptionSlug = "devonian_materpiscis_description",
                timeScale = "380.0-?",
                order = 12
            ),
            DTO(
                id = 37,
                thumbnailId = "item_devonian_cladoselache_thumb",
                artworkId = "item_devonian_cladoselache",
                artworkAuthor = "Julio Lacerda",
                additionalArtworkId = "item_devonian_cladoselache_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_cladoselache",
                descriptionSlug = "devonian_cladoselache_description",
                timeScale = "376.1-360.7",
                order = 13
            ),
            DTO(
                id = 34,
                thumbnailId = "item_devonian_acanthostega_thumb",
                artworkId = "item_devonian_acanthostega",
                artworkAuthor = "Masato Hattori",
                additionalArtworkId = "item_devonian_acanthostega_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_acanthostega",
                descriptionSlug = "devonian_acanthostega_description",
                timeScale = "376.1-360.7",
                order = 14
            ),
            DTO(
                id = 33,
                thumbnailId = "item_devonian_ichthyostega_thumb",
                artworkId = "item_devonian_ichthyostega",
                artworkAuthor = "Alain Beneteau",
                additionalArtworkId = "item_devonian_ichthyostega_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_ichthyostega",
                descriptionSlug = "devonian_ichthyostega_description",
                timeScale = "365.0-360.7",
                order = 15
            ),
            DTO(
                id = 140,
                thumbnailId = "item_devonian_xenacanthus_thumb",
                artworkId = "item_devonian_xenacanthus",
                artworkAuthor = "Warpaint",
                additionalArtworkId = "item_devonian_xenacanthus_info",
                additionalArtworkAuthor = null,
                titleSlug = "devonian_xenacanthus",
                descriptionSlug = "devonian_xenacanthus_description",
                timeScale = "360.0-208.0",
                order = 16
            )
        )
    }

    private fun getCarboniferousLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 41,
                thumbnailId = "item_carboniferous_calamites_thumb",
                artworkId = "item_carboniferous_calamites",
                artworkAuthor = "Walter Myers",
                additionalArtworkId = "item_carboniferous_calamites_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_calamites",
                descriptionSlug = "carboniferous_calamites_description",
                timeScale = "358.8-299.0",
                order = 1
            ),
            DTO(
                id = 39,
                thumbnailId = "item_carboniferous_lepidodendron_thumb",
                artworkId = "item_carboniferous_lepidodendron",
                artworkAuthor = null,
                additionalArtworkId = "item_carboniferous_lepidodendron_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_lepidodendron",
                descriptionSlug = "carboniferous_lepidodendron_description",
                timeScale = "358.8-251.9",
                order = 2
            ),
            DTO(
                id = 44,
                thumbnailId = "item_carboniferous_pederpes_thumb",
                artworkId = "item_carboniferous_pederpes",
                artworkAuthor = null,
                additionalArtworkId = "item_carboniferous_pederpes_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_pederpes",
                descriptionSlug = "carboniferous_pederpes_description",
                timeScale = "348.0–347.6",
                order = 3
            ),
            DTO(
                id = 186,
                thumbnailId = "item_carboniferous_crassigyrinus_thumb",
                artworkId = "item_carboniferous_crassigyrinus",
                artworkAuthor = "Mario Lanzas",
                additionalArtworkId = "item_carboniferous_crassigyrinus_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_crassigyrinus",
                descriptionSlug = "carboniferous_crassigyrinus_description",
                timeScale = "345.0–329.0",
                order = 4
            ),
            DTO(
                id = 49,
                thumbnailId = "item_carboniferous_arthropleura_thumb",
                artworkId = "item_carboniferous_arthropleura",
                artworkAuthor = "Paleopete",
                additionalArtworkId = "item_carboniferous_arthropleura_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_arthropleura",
                descriptionSlug = "carboniferous_arthropleura_description",
                timeScale = "344.0–292.0",
                order = 5
            ),
            DTO(
                id = 48,
                thumbnailId = "item_carboniferous_pulmonoscorpius_thumb",
                artworkId = "item_carboniferous_pulmonoscorpius",
                artworkAuthor = "Prehistorica CM",
                additionalArtworkId = "item_carboniferous_pulmonoscorpius_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_pulmonoscorpius",
                descriptionSlug = "carboniferous_pulmonoscorpius_description",
                timeScale = "335.9–330.3",
                order = 6
            ),
            DTO(
                id = 45,
                thumbnailId = "item_carboniferous_pterogyrinus_thumb",
                artworkId = "item_carboniferous_pterogyrinus",
                artworkAuthor = "Nobu Tamura",
                additionalArtworkId = "item_carboniferous_proterogyrinus_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_pterogyrinus",
                descriptionSlug = "carboniferous_pterogyrinus_description",
                timeScale = "331.0–323.0",
                order = 7
            ),
            DTO(
                id = 177,
                thumbnailId = "item_carboniferous_anthracosaurus_thumb",
                artworkId = "item_carboniferous_anthracosaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_carboniferous_anthracosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_anthracosaurus",
                descriptionSlug = "carboniferous_anthracosaurus_description",
                timeScale = "328.0-418.0",
                order = 8
            ),
            DTO(
                id = 40,
                thumbnailId = "item_carboniferous_sigillaria_thumb",
                artworkId = "item_carboniferous_sigillaria",
                artworkAuthor = null,
                additionalArtworkId = "item_carboniferous_sigillaria_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_sigillaria",
                descriptionSlug = "carboniferous_sigillaria_description",
                timeScale = "323.2-254.0",
                order = 9
            ),
            DTO(
                id = 42,
                thumbnailId = "item_carboniferous_akmonistion_thumb",
                artworkId = "item_carboniferous_akmonistion",
                artworkAuthor = "Masato Hattori",
                additionalArtworkId = "item_carboniferous_akmonistion_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_akmonistion",
                descriptionSlug = "carboniferous_akmonistion_description",
                timeScale = "318.0-315.0",
                order = 10
            ),
            DTO(
                id = 46,
                thumbnailId = "item_carboniferous_hylonomus_thumb",
                artworkId = "item_carboniferous_hylonomus",
                artworkAuthor = null,
                additionalArtworkId = "item_carboniferous_hylonomus_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_hylonomus",
                descriptionSlug = "carboniferous_hylonomus_description",
                timeScale = "318.1-314.6",
                order = 11
            ),
            DTO(
                id = 144,
                thumbnailId = "item_permian_diplocaulus_thumb",
                artworkId = "item_permian_diplocaulus",
                artworkAuthor = null,
                additionalArtworkId = "item_permian_diplocaulus_info",
                additionalArtworkAuthor = null,
                titleSlug = "permian_diplocaulus",
                descriptionSlug = "permian_diplocaulus_description",
                timeScale = "307.0-257.0",
                order = 12
            ),
            DTO(
                id = 47,
                thumbnailId = "item_carboniferous_archaeothyris_thumb",
                artworkId = "item_carboniferous_archaeothyris",
                artworkAuthor = "Arthur Weasley",
                additionalArtworkId = "item_carboniferous_archaeothyris_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_archaeothyris",
                descriptionSlug = "carboniferous_archaeothyris_description",
                timeScale = "306.0–302.0",
                order = 13
            ),
            DTO(
                id = 50,
                thumbnailId = "item_carboniferous_meganeura_thumb",
                artworkId = "item_carboniferous_meganeura",
                artworkAuthor = null,
                additionalArtworkId = "item_carboniferous_meganeura_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_meganeura",
                descriptionSlug = "carboniferous_meganeura_description",
                timeScale = "303.4-298.9",
                order = 14
            ),
            DTO(
                id = 129,
                thumbnailId = "item_carboniferous_megarachne_thumb",
                artworkId = "item_carboniferous_megarachne",
                artworkAuthor = "Nicholls",
                additionalArtworkId = "item_carboniferous_megarachne_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_megarachne",
                descriptionSlug = "carboniferous_megarachne_description",
                timeScale = "303.7-298.9",
                order = 15
            ),
            DTO(
                id = 130,
                thumbnailId = "item_carboniferous_edestus_thumb",
                artworkId = "item_carboniferous_edestus",
                artworkAuthor = "Mateusz Horbowiec",
                additionalArtworkId = "item_carboniferous_edestus_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_edestus",
                descriptionSlug = "carboniferous_edestus_description",
                timeScale = "303.7-298.9",
                order = 16
            ),
            DTO(
                id = 178,
                thumbnailId = "item_carboniferous_edaphosaurus_thumb",
                artworkId = "item_carboniferous_edaphosaurus",
                artworkAuthor = "Gabriel Ugueto",
                additionalArtworkId = "item_carboniferous_edaphosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_edaphosaurus",
                descriptionSlug = "carboniferous_edaphosaurus_description",
                timeScale = "303.4–272.5",
                order = 17
            ),
            DTO(
                id = 56,
                thumbnailId = "item_carboniferous_orthacanthus_thumb",
                artworkId = "item_carboniferous_orthacanthus",
                artworkAuthor = "Alain Beneteau",
                additionalArtworkId = "item_carboniferous_orthacanthus_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_orthacanthus",
                descriptionSlug = "carboniferous_orthacanthus_description",
                timeScale = "300.0–260.0",
                order = 18
            )
        )
    }

    private fun getPermianLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 51,
                thumbnailId = "item_permian_glossopteris_thumb",
                artworkId = "item_permian_glossopteris",
                artworkAuthor = "Rose Prevec",
                additionalArtworkId = "item_permian_glossopteris_info",
                additionalArtworkAuthor = null,
                titleSlug = "permian_glossopteris",
                descriptionSlug = "permian_glossopteris_description",
                timeScale = "298.9-251.9",
                order = 1
            ),
            DTO(
                id = 57,
                thumbnailId = "item_triassic_bennettitales_thumb",
                artworkId = "item_triassic_bennettitales",
                artworkAuthor = "Jonathan Hudhes",
                additionalArtworkId = "item_triassic_bennetitales_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_bennettitales",
                descriptionSlug = "triassic_bennettitales_description",
                timeScale = "298.9-28.4",
                order = 2
            ),
            DTO(
                id = 143,
                thumbnailId = "item_permian_eryops_thumb",
                artworkId = "item_permian_eryops",
                artworkAuthor = "Vladislav Egorov",
                additionalArtworkId = "item_permian_eryops_info",
                additionalArtworkAuthor = null,
                titleSlug = "permian_eryops",
                descriptionSlug = "permian_eryops_description",
                timeScale = "298.9-273.0",
                order = 3
            ),
            DTO(
                id = 52,
                thumbnailId = "item_permian_dimetrodon_thumb",
                artworkId = "item_permian_dimetrodon",
                artworkAuthor = "Jerry Lofaro",
                additionalArtworkId = "item_permian_dimetrodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "permian_dimetrodon",
                descriptionSlug = "permian_dimetrodon_description",
                timeScale = "295.0-270.0",
                order = 4
            ),
            DTO(
                id = 43,
                thumbnailId = "item_carboniferous_helicoprion_thumb",
                artworkId = "item_carboniferous_helicoprion",
                artworkAuthor = null,
                additionalArtworkId = "item_carboniferous_helicoprion_info",
                additionalArtworkAuthor = null,
                titleSlug = "carboniferous_helicoprion",
                descriptionSlug = "carboniferous_helicoprion_description",
                timeScale = "290.1-270.0",
                order = 5
            ),
            DTO(
                id = 53,
                thumbnailId = "item_permian_titanophoneus_thumb",
                artworkId = "item_permian_titanophoneus",
                artworkAuthor = "Nobu Tamura",
                additionalArtworkId = "item_permian_titanophoneus_info",
                additionalArtworkAuthor = null,
                titleSlug = "permian_titanophoneus",
                descriptionSlug = "permian_titanophoneus_description",
                timeScale = "268.0-265.0",
                order = 6
            ),
            DTO(
                id = 54,
                thumbnailId = "item_permian_estemmenosuchus_thumb",
                artworkId = "item_permian_estemmenosuchus",
                artworkAuthor = "Dinoraul",
                additionalArtworkId = "item_permian_estemmenosuchus_info",
                additionalArtworkAuthor = null,
                titleSlug = "permian_estemmenosuchus",
                descriptionSlug = "permian_estemmenosuchus_description",
                timeScale = "267.0-260.0",
                order = 7
            ),
            DTO(
                id = 185,
                thumbnailId = "item_permian_eunotosaurus_thumb",
                artworkId = "item_permian_eunotosaurus",
                artworkAuthor = "Andrey Atuchin",
                additionalArtworkId = "item_permian_eunotosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "permian_eunotosaurus",
                descriptionSlug = "permian_eunotosaurus_description",
                timeScale = "265.8–259.0",
                order = 8
            ),
            DTO(
                id = 181,
                thumbnailId = "item_permian_moschops_thumb",
                artworkId = "item_permian_moschops",
                artworkAuthor = null,
                additionalArtworkId = "item_permian_moschops_info",
                additionalArtworkAuthor = null,
                titleSlug = "permian_moschops",
                descriptionSlug = "permian_moschops_description",
                timeScale = "265.0–260.0",
                order = 9
            ),
            DTO(
                id = 55,
                thumbnailId = "item_permian_gorgonops_thumb",
                artworkId = "item_permian_gorgonops",
                artworkAuthor = null,
                additionalArtworkId = "item_permian_gorgonops_info",
                additionalArtworkAuthor = null,
                titleSlug = "permian_gorgonops",
                descriptionSlug = "permian_gorgonops_description",
                timeScale = "260.0-254.0",
                order = 10
            ),
            DTO(
                id = 59,
                thumbnailId = "item_triassic_lystrosaurus_thumb",
                artworkId = "item_triassic_lystrosaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_triassic_lystrosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_lystrosaurus",
                descriptionSlug = "triassic_lystrosaurus_description",
                timeScale = "255.0-248.0",
                order = 11
            ),
            DTO(
                id = 170,
                thumbnailId = "item_triassic_thrinaxodon_thumb",
                artworkId = "item_triassic_thrinaxodon",
                artworkAuthor = "April I. Neander",
                additionalArtworkId = "item_triassic_thrinaxodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_thrinaxodon",
                descriptionSlug = "triassic_thrinaxodon_description",
                timeScale = "252.0-247.0",
                order = 12
            )
        )
    }

    private fun getTriassicLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 62,
                thumbnailId = "item_triassic_ichthyosauria_thumb",
                artworkId = "item_triassic_ichthyosauria",
                artworkAuthor = "Todd Marshall",
                additionalArtworkId = "item_triassic_ichthyosauria_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_ichthyosauria",
                descriptionSlug = "triassic_ichthyosauria_description",
                timeScale = "248.0–90.0",
                order = 1
            ),
            DTO(
                id = 67,
                thumbnailId = "item_triassic_tanystropheus_thumb",
                artworkId = "item_triassic_tanystropheus",
                artworkAuthor = "Mark Witton",
                additionalArtworkId = "item_triassic_tanystropheus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_tanystropheus",
                descriptionSlug = "triassic_tanystropheus_description",
                timeScale = "247.2-208.5",
                order = 2
            ),
            DTO(
                id = 154,
                thumbnailId = "item_triassic_mixosaurus_thumb",
                artworkId = "item_triassic_mixosaurus",
                artworkAuthor = "Sante Mazzei",
                additionalArtworkId = "item_triassic_mixosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_mixosaurus",
                descriptionSlug = "triassic_mixosaurus_description",
                timeScale = "247.0-242.0",
                order = 3
            ),
            DTO(
                id = 145,
                thumbnailId = "item_triassic_atopodentatus_thumb",
                artworkId = "item_triassic_atopodentatus",
                artworkAuthor = "Carlos Eulefi",
                additionalArtworkId = "item_triassic_atopodentatus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_atopodentatus",
                descriptionSlug = "triassic_atopodentatus_description",
                timeScale = "247.0-240.0",
                order = 4
            ),
            DTO(
                id = 153,
                thumbnailId = "item_triassic_nothosaurus_thumb",
                artworkId = "item_triassic_nothosaurus",
                artworkAuthor = "Corey Ford",
                additionalArtworkId = "item_triassic_nothosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_nothosaurus",
                descriptionSlug = "triassic_nothosaurus_description",
                timeScale = "240.0-210.0",
                order = 5
            ),
            DTO(
                id = 147,
                thumbnailId = "item_triassic_postosuchus_thumb",
                artworkId = "item_triassic_postosuchus",
                artworkAuthor = "The Dino Sirs",
                additionalArtworkId = "item_triassic_postosuchus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_postosuchus",
                descriptionSlug = "triassic_postosuchus_description",
                timeScale = "237.0-201.4",
                order = 6
            ),
            DTO(
                id = 65,
                thumbnailId = "item_triassic_longisquama_thumb",
                artworkId = "item_triassic_longisquama",
                artworkAuthor = "Julio Lacerda",
                additionalArtworkId = "item_triassic_longisquama_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_longisquama",
                descriptionSlug = "triassic_longisquama_description",
                timeScale = "235.0-221.5",
                order = 7
            ),
            DTO(
                id = 183,
                thumbnailId = "item_triassic_staurikosaurus_thumb",
                artworkId = "item_triassic_staurikosaurus",
                artworkAuthor = "Márcio L. Castro",
                additionalArtworkId = "item_triassic_staurikosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_staurikosaurus",
                descriptionSlug = "triassic_staurikosaurus_description",
                timeScale = "233.23-228.0",
                order = 8
            ),
            DTO(
                id = 148,
                thumbnailId = "item_triassic_shonisaurus_thumb",
                artworkId = "item_triassic_shonisaurus",
                artworkAuthor = "Scott Reid",
                additionalArtworkId = "item_triassic_shonisaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_shonisaurus",
                descriptionSlug = "triassic_shonisaurus_description",
                timeScale = "232.0-212.0",
                order = 9
            ),
            DTO(
                id = 146,
                thumbnailId = "item_triassic_herrerasaurus_thumb",
                artworkId = "item_triassic_herrerasaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_triassic_herrerasaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_herrerasaurus",
                descriptionSlug = "triassic_herrerasaurus_description",
                timeScale = "231.4–228.91",
                order = 10
            ),
            DTO(
                id = 167,
                thumbnailId = "item_triassic_hyperodapedon_thumb",
                artworkId = "item_triassic_hyperodapedon",
                artworkAuthor = "Liam Elward",
                additionalArtworkId = "item_triassic_hyperodapedon_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_hyperodapedon",
                descriptionSlug = "triassic_hyperodapedon_description",
                timeScale = "231.0-227.0",
                order = 11
            ),
            DTO(
                id = 168,
                thumbnailId = "item_triassic_placerias_thumb",
                artworkId = "item_triassic_placerias",
                artworkAuthor = "Nobu Tamura",
                additionalArtworkId = "item_triassic_placerias_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_placerias",
                descriptionSlug = "triassic_placerias_description",
                timeScale = "230.0-215.0",
                order = 12
            ),
            DTO(
                id = 60,
                thumbnailId = "item_triassic_pterosauria_thumb",
                artworkId = "item_triassic_pterosauria",
                artworkAuthor = "Hombre Hojalata",
                additionalArtworkId = "item_triassic_pterosauria_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_pterosauria",
                descriptionSlug = "triassic_pterosauria_description",
                timeScale = "228.1–66.0",
                order = 13
            ),
            DTO(
                id = 63,
                thumbnailId = "item_triassic_mystriosuchus_thumb",
                artworkId = "item_triassic_mystriosuchus",
                artworkAuthor = "Julio Lacerda",
                additionalArtworkId = "item_triassic_mystriosuchus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_mystriosuchus",
                descriptionSlug = "triassic_mystriosuchus_description",
                timeScale = "228.0-212.0",
                order = 14
            ),
            DTO(
                id = 64,
                thumbnailId = "item_triassic_desmatosuchus_thumb",
                artworkId = "item_triassic_desmatosuchus",
                artworkAuthor = null,
                additionalArtworkId = "item_triassic_desmatosuchus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_desmatosuchus",
                descriptionSlug = "triassic_desmatosuchus_description",
                timeScale = "228.0-210.0",
                order = 15
            ),
            DTO(
                id = 68,
                thumbnailId = "item_triassic_plateosaurus_thumb",
                artworkId = "item_triassic_plateosaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_triassic_plateosaurus_info",
                additionalArtworkAuthor = "Darren Pepper",
                titleSlug = "triassic_plateosaurus",
                descriptionSlug = "triassic_plateosaurus_description",
                timeScale = "227.0-204.0",
                order = 16
            ),
            DTO(
                id = 66,
                thumbnailId = "item_triassic_sharovipteryx_thumb",
                artworkId = "item_triassic_sharovipteryx",
                artworkAuthor = "Julio Lacerda",
                additionalArtworkId = "item_triassic_sharovipteryx_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_sharovipteryx",
                descriptionSlug = "triassic_sharovipteryx_description",
                timeScale = "225.0-221.5",
                order = 17
            ),
            DTO(
                id = 169,
                thumbnailId = "item_triassic_stagonolepis_thumb",
                artworkId = "item_triassic_stagonolepis",
                artworkAuthor = "Joanna Kobierska",
                additionalArtworkId = "item_triassic_stagonolepis_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_stagonolepis",
                descriptionSlug = "triassic_stagonolepis_description",
                timeScale = "221.0-206.0",
                order = 18
            ),
            DTO(
                id = 182,
                thumbnailId = "item_triassic_fasolasuchus_thumb",
                artworkId = "item_triassic_fasolasuchus",
                artworkAuthor = null,
                additionalArtworkId = "item_triassic_fasolasuchus_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_fasolasuchus",
                descriptionSlug = "triassic_fasolasuchus_description",
                timeScale = "220.0–213.0",
                order = 19
            ),
            DTO(
                id = 184,
                thumbnailId = "item_triassic_coelophysis_thumb",
                artworkId = "item_triassic_coelophysis",
                artworkAuthor = "Roberto Akeiron",
                additionalArtworkId = "item_triassic_coelophysis_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_coelophysis",
                descriptionSlug = "triassic_coelophysis_description",
                timeScale = "215.0–201.4",
                order = 20
            ),
            DTO(
                id = 58,
                thumbnailId = "item_triassic_morganucodon_thumb",
                artworkId = "item_triassic_morganucodon",
                artworkAuthor = "Michael B.H.",
                additionalArtworkId = "item_triassic_morganucodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_morganucodon",
                descriptionSlug = "triassic_morganucodon_description",
                timeScale = "205.6-164.7",
                order = 21
            ),
            DTO(
                id = 61,
                thumbnailId = "item_triassic_plesiosauria_thumb",
                artworkId = "item_triassic_plesiosauria",
                artworkAuthor = "Daniel Loxton",
                additionalArtworkId = "item_triassic_plesiosauria_info",
                additionalArtworkAuthor = null,
                titleSlug = "triassic_plesiosauria",
                descriptionSlug = "triassic_plesiosauria_description",
                timeScale = "203.6–66.0",
                order = 22
            )
        )
    }

    private fun getJurassicLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 69,
                thumbnailId = "item_jurassic_archaeopteryx_thumb",
                artworkId = "item_jurassic_archaeopteryx",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_archaeopteryx",
                descriptionSlug = "jurassic_archaeopteryx_description",
                timeScale = "150.8-125.45"
            ),
            DTO(
                id = 70,
                thumbnailId = "item_jurassic_allosaurus_thumb",
                artworkId = "item_jurassic_allosaurus",
                artworkAuthor = "Franco Tempesta",
                additionalArtworkId = "item_jurassic_allosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_allosaurus",
                descriptionSlug = "jurassic_allosaurus_description",
                timeScale = "150.8-89.3"
            ),
            DTO(
                id = 71,
                thumbnailId = "item_jurassic_mamenchisaurus_thumb",
                artworkId = "item_jurassic_mamenchisaurus",
                artworkAuthor = "Walter Myers",
                additionalArtworkId = "item_jurassic_mamenchisaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_mamenchisaurus",
                descriptionSlug = "jurassic_mamenchisaurus_description",
                timeScale = "161.2-145.5"
            ),
            DTO(
                id = 72,
                thumbnailId = "item_jurassic_liopleurodon_thumb",
                artworkId = "item_jurassic_liopleurodon",
                artworkAuthor = "Rainer Harf",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_liopleurodon",
                descriptionSlug = "jurassic_liopleurodon_description",
                timeScale = "161.2-140.2"
            ),
            DTO(
                id = 73,
                thumbnailId = "item_jurassic_stegosaurus_thumb",
                artworkId = "item_jurassic_stegosaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_jurassic_stegosaurus_info",
                additionalArtworkAuthor = "Darren Pepper",
                titleSlug = "jurassic_stegosaurus",
                descriptionSlug = "jurassic_stegosaurus_description",
                timeScale = "150.8-99.7"
            ),
            DTO(
                id = 74,
                thumbnailId = "item_jurassic_kentrosaurus_thumb",
                artworkId = "item_jurassic_kentrosaurus",
                artworkAuthor = "Federico",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_kentrosaurus",
                descriptionSlug = "jurassic_kentrosaurus_description",
                timeScale = "155.7-145.5"
            ),
            DTO(
                id = 75,
                thumbnailId = "item_jurassic_epidexipteryx_thumb",
                artworkId = "item_jurassic_epidexipteryx",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_epidexipteryx",
                descriptionSlug = "jurassic_epidexipteryx_description",
                timeScale = "164.7-161.2"
            ),
            DTO(
                id = 76,
                thumbnailId = "item_jurassic_epidendrosaurus_thumb",
                artworkId = "item_jurassic_epidendrosaurus",
                artworkAuthor = "Will Brennan",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_epidendrosaurus",
                descriptionSlug = "jurassic_epidendrosaurus_description",
                timeScale = "164.7-161.2"
            ),
            DTO(
                id = 77,
                thumbnailId = "item_jurassic_dimorphodon_thumb",
                artworkId = "item_jurassic_dimorphodon",
                artworkAuthor = "Romn Garca",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_dimorphodon",
                descriptionSlug = "jurassic_dimorphodon_description",
                timeScale = "201.3-189.6"
            ),
            DTO(
                id = 78,
                thumbnailId = "item_jurassic_dilophosaurus_thumb",
                artworkId = "item_jurassic_dilophosaurus",
                artworkAuthor = "Heather Luterman",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_dilophosaurus",
                descriptionSlug = "jurassic_dilophosaurus_description",
                timeScale = "196.5-189.6"
            ),
            DTO(
                id = 155,
                thumbnailId = "item_jurassic_metriacanthosaurus_thumb",
                artworkId = "item_jurassic_metriacanthosaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_jurassic_metriacanthosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_metriacanthosaurus",
                descriptionSlug = "jurassic_metriacanthosaurus_description",
                timeScale = "160.0-155.0"
            ),
            DTO(
                id = 156,
                thumbnailId = "item_jurassic_diplodocus_thumb",
                artworkId = "item_jurassic_diplodocus",
                artworkAuthor = "Daniel Eskridge",
                additionalArtworkId = "item_jurassic_diplodocus_info",
                additionalArtworkAuthor = null,
                titleSlug = "jurassic_diplodocus",
                descriptionSlug = "jurassic_diplodocus_description",
                timeScale = "152.07-149.1"
            )
        )
    }

    private fun getCretaceousLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 79,
                thumbnailId = "item_cretaceous_tyrannosaurus_thumb",
                artworkId = "item_cretaceous_tyrannosaurus",
                artworkAuthor = "Vlad Konstantinov",
                additionalArtworkId = "item_cretaceous_tyrannosaurus_info",
                additionalArtworkAuthor = "Danielle Dufault",
                titleSlug = "cretaceous_tyrannosaurus",
                descriptionSlug = "cretaceous_tyrannosaurus_description",
                timeScale = "70.6-66.043"
            ),
            DTO(
                id = 80,
                thumbnailId = "item_cretaceous_giganotosaurus_thumb",
                artworkId = "item_cretaceous_giganotosaurus",
                artworkAuthor = "Damir",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_giganotosaurus",
                descriptionSlug = "cretaceous_giganotosaurus_description",
                timeScale = "99.7-94.3"
            ),
            DTO(
                id = 81,
                thumbnailId = "item_cretaceous_linhenykus_thumb",
                artworkId = "item_cretaceous_linhenykus",
                artworkAuthor = "Julius Csotonyi",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_linhenykus",
                descriptionSlug = "cretaceous_linhenykus_description",
                timeScale = "84.9-70.6"
            ),
            DTO(
                id = 82,
                thumbnailId = "item_cretaceous_velociraptor_thumb",
                artworkId = "item_cretaceous_velociraptor",
                artworkAuthor = "Zhao Chuang",
                additionalArtworkId = "item_cretaceous_velociraptor_info",
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_velociraptor",
                descriptionSlug = "cretaceous_velociraptor_description",
                timeScale = "84.9-70.6"
            ),
            DTO(
                id = 83,
                thumbnailId = "item_cretaceous_spinosaurus_thumb",
                artworkId = "item_cretaceous_spinosaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_cretaceous_spinosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_spinosaurus",
                descriptionSlug = "cretaceous_spinosaurus_description",
                timeScale = "112.6-70.6"
            ),
            DTO(
                id = 84,
                thumbnailId = "item_cretaceous_pachycephalosaurus_thumb",
                artworkId = "item_cretaceous_pachycephalosaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_cretaceous_pachycephalosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_pachycephalosaurus",
                descriptionSlug = "cretaceous_pachycephalosaurus_description",
                timeScale = "70.6-66.043"
            ),
            DTO(
                id = 85,
                thumbnailId = "item_cretaceous_triceratops_thumb",
                artworkId = "item_cretaceous_triceratops",
                artworkAuthor = "Vlad Konstantinov",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_triceratops",
                descriptionSlug = "cretaceous_triceratops_description",
                timeScale = "70.6-66.043"
            ),
            DTO(
                id = 86,
                thumbnailId = "item_cretaceous_ankylosaurus_thumb",
                artworkId = "item_cretaceous_ankylosaurus",
                artworkAuthor = "Karl Lindberg",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_ankylosaurus",
                descriptionSlug = "cretaceous_ankylosaurus_description",
                timeScale = "70.6-66.043"
            ),
            DTO(
                id = 87,
                thumbnailId = "item_cretaceous_therizinosaurus_thumb",
                artworkId = "item_cretaceous_therizinosaurus",
                artworkAuthor = "Damir",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_therizinosaurus",
                descriptionSlug = "cretaceous_therizinosaurus_description",
                timeScale = "70.6-66.043"
            ),
            DTO(
                id = 88,
                thumbnailId = "item_cretaceous_tsintaosaurus_thumb",
                artworkId = "item_cretaceous_tsintaosaurus",
                artworkAuthor = "James Kuether",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_tsintaosaurus",
                descriptionSlug = "cretaceous_tsintaosaurus_description",
                timeScale = "84.9-70.6"
            ),
            DTO(
                id = 89,
                thumbnailId = "item_cretaceous_deinocherus_thumb",
                artworkId = "item_cretaceous_deinocherus",
                artworkAuthor = "Olorotitan",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_deinocherus",
                descriptionSlug = "cretaceous_deinocherus_description",
                timeScale = "99.7-66.043"
            ),
            DTO(
                id = 90,
                thumbnailId = "item_cretaceous_sarcosuchus_thumb",
                artworkId = "item_cretaceous_sarcosuchus",
                artworkAuthor = "Raul Martin",
                additionalArtworkId = "item_cretaceous_sarcosuchus_info",
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_sarcosuchus",
                descriptionSlug = "cretaceous_sarcosuchus_description",
                timeScale = "112.6-94.3"
            ),
            DTO(
                id = 91,
                thumbnailId = "item_cretaceous_quetzalcoatlus_thumb",
                artworkId = "item_cretaceous_quetzalcoatlus",
                artworkAuthor = "Vlad Konstantinov",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_quetzalcoatlus",
                descriptionSlug = "cretaceous_quetzalcoatlus_description",
                timeScale = "70.6-66.043"
            ),
            DTO(
                id = 92,
                thumbnailId = "item_cretaceous_microraptor_thumb",
                artworkId = "item_cretaceous_microraptor",
                artworkAuthor = "Sergey Krasovskiy",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_microraptor",
                descriptionSlug = "cretaceous_microraptor_description",
                timeScale = "125.45-112.6"
            ),
            DTO(
                id = 93,
                thumbnailId = "item_cretaceous_mosasaurus_thumb",
                artworkId = "item_cretaceous_mosasaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_cretaceous_mosasaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_mosasaurus",
                descriptionSlug = "cretaceous_mosasaurus_description",
                timeScale = "94.3-66.043"
            ),
            DTO(
                id = 94,
                thumbnailId = "item_cretaceous_ammonite_thumb",
                artworkId = "item_cretaceous_ammonite",
                artworkAuthor = "Simon Stalenhag",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_ammonite",
                descriptionSlug = "cretaceous_ammonite_description",
                timeScale = "400.0-66.043"
            ),
            DTO(
                id = 149,
                thumbnailId = "item_cretaceous_pteranodon_thumb",
                artworkId = "item_cretaceous_pteranodon",
                artworkAuthor = "Mohamad Haghani",
                additionalArtworkId = "item_cretaceous_pteranodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_pteranodon",
                descriptionSlug = "cretaceous_pteranodon_description",
                timeScale = "86.0-78.25"
            ),
            DTO(
                id = 150,
                thumbnailId = "item_cretaceous_carnotaurus_thumb",
                artworkId = "item_cretaceous_carnotaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_cretaceous_carnotaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_carnotaurus",
                descriptionSlug = "cretaceous_carnotaurus_description",
                timeScale = "72.0-69.9"
            ),
            DTO(
                id = 151,
                thumbnailId = "item_cretaceous_saltasaurus_thumb",
                artworkId = "item_cretaceous_saltasaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_cretaceous_saltasaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_saltasaurus",
                descriptionSlug = "cretaceous_saltasaurus_description",
                timeScale = "70.0-68.0"
            ),
            DTO(
                id = 152,
                thumbnailId = "item_cretaceous_nigersaurus_thumb",
                artworkId = "item_cretaceous_nigersaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_cretaceous_nigersaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "cretaceous_nigersaurus",
                descriptionSlug = "cretaceous_nigersaurus_description",
                timeScale = "115.0-105.0"
            )
        )
    }

    private fun getPaleogeneLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 95,
                thumbnailId = "item_paleogene_titanoboa_thumb",
                artworkId = "item_paleogene_titanoboa",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_titanoboa_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_titanoboa",
                descriptionSlug = "paleogene_titanoboa_description",
                timeScale = "61.7-58.7"
            ),
            DTO(
                id = 96,
                thumbnailId = "item_paleogene_gastornis_thumb",
                artworkId = "item_paleogene_gastornis",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_gastornis_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_gastornis",
                descriptionSlug = "paleogene_gastornis_description",
                timeScale = "58.7-48.6"
            ),
            DTO(
                id = 97,
                thumbnailId = "item_paleogene_basilosaurus_thumb",
                artworkId = "item_paleogene_basilosaurus",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_basilosaurus_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_basilosaurus",
                descriptionSlug = "paleogene_basilosaurus_description",
                timeScale = "41.3–33.9"
            ),
            DTO(
                id = 98,
                thumbnailId = "item_paleogene_paraceratherium_thumb",
                artworkId = "item_paleogene_paraceratherium",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_paraceratherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_paraceratherium",
                descriptionSlug = "paleogene_paraceratherium_description",
                timeScale = "33.9-23.03"
            ),
            DTO(
                id = 99,
                thumbnailId = "item_paleogene_daeodon_thumb",
                artworkId = "item_paleogene_daeodon",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_daeodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_daeodon",
                descriptionSlug = "paleogene_daeodon_description",
                timeScale = "29.0-15.97"
            ),
            DTO(
                id = 100,
                thumbnailId = "item_paleogene_archaeotherium_thumb",
                artworkId = "item_paleogene_archaeotherium",
                artworkAuthor = "Julius Csotonyi",
                additionalArtworkId = "item_paleogene_archaeotherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_archaeotherium",
                descriptionSlug = "paleogene_archaeotherium_description",
                timeScale = "35.0-28.0"
            ),
            DTO(
                id = 101,
                thumbnailId = "item_paleogene_arsinoitherium_thumb",
                artworkId = "item_paleogene_arsinoitherium",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_arsinoitherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_arsinoitherium",
                descriptionSlug = "paleogene_arsinoitherium_description",
                timeScale = "36.0-27.0"
            ),
            DTO(
                id = 102,
                thumbnailId = "item_paleogene_pyrotherium_thumb",
                artworkId = "item_paleogene_pyrotherium",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_pyrotherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_pyrotherium",
                descriptionSlug = "paleogene_pyrotherium_description",
                timeScale = "28.4-23.03"
            ),
            DTO(
                id = 157,
                thumbnailId = "item_paleogene_brontotherium_thumb",
                artworkId = "item_paleogene_brontotherium",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_brontotherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_brontotherium",
                descriptionSlug = "paleogene_brontotherium_description",
                timeScale = "38.0-33.9"
            ),
            DTO(
                id = 158,
                thumbnailId = "item_paleogene_uintatherium_thumb",
                artworkId = "item_paleogene_uintatherium",
                artworkAuthor = "Vadim Anikeev",
                additionalArtworkId = "item_paleogene_uintatherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_uintatherium",
                descriptionSlug = "paleogene_uintatherium_description",
                timeScale = "50.5-37.0"
            ),
            DTO(
                id = 159,
                thumbnailId = "item_paleogene_hyaenodon_thumb",
                artworkId = "item_paleogene_hyaenodon",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_hyaenodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_hyaenodon",
                descriptionSlug = "paleogene_hyaenodon_description",
                timeScale = "38.0-16.9"
            ),
            DTO(
                id = 160,
                thumbnailId = "item_paleogene_dorudon_thumb",
                artworkId = "item_paleogene_dorudon",
                artworkAuthor = "Lucas Mateus",
                additionalArtworkId = "item_paleogene_dorudon_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_dorudon",
                descriptionSlug = "paleogene_dorudon_description",
                timeScale = "41.03-33.9"
            ),
            DTO(
                id = 171,
                thumbnailId = "item_paleogene_protocetus_thumb",
                artworkId = "item_paleogene_protocetus",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_protocetus_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_protocetus",
                descriptionSlug = "paleogene_protocetus_description",
                timeScale = "45.0-43.5"
            ),
            DTO(
                id = 172,
                thumbnailId = "item_paleogene_hyrachyus_thumb",
                artworkId = "item_paleogene_hyrachyus",
                artworkAuthor = "Leogon",
                additionalArtworkId = "item_paleogene_hyrachyus_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_hyrachyus",
                descriptionSlug = "paleogene_hyrachyus_description",
                timeScale = "56.0-40.0"
            ),
            DTO(
                id = 173,
                thumbnailId = "item_paleogene_otodus_thumb",
                artworkId = "item_paleogene_otodus",
                artworkAuthor = null,
                additionalArtworkId = "item_paleogene_otodus_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_otodus",
                descriptionSlug = "paleogene_otodus_description",
                timeScale = "65.0-3.6"
            ),
            DTO(
                id = 174,
                thumbnailId = "item_paleogene_pelagornis_thumb",
                artworkId = "item_paleogene_pelagornis",
                artworkAuthor = "James Gurney",
                additionalArtworkId = "item_paleogene_pelagornis_info",
                additionalArtworkAuthor = null,
                titleSlug = "paleogene_pelagornis",
                descriptionSlug = "paleogene_pelagornis_description",
                timeScale = "25.0-2.5"
            )
        )
    }

    private fun getNeogeneLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 103,
                thumbnailId = "item_neogene_argentavis_thumb",
                artworkId = "item_neogene_argentavis",
                artworkAuthor = null,
                additionalArtworkId = "item_neogene_argentavis_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_argentavis",
                descriptionSlug = "neogene_argentavis_description",
                timeScale = "9.0-6.8"
            ),
            DTO(
                id = 104,
                thumbnailId = "item_neogene_titanis_thumb",
                artworkId = "item_neogene_titanis",
                artworkAuthor = "Gary Wright",
                additionalArtworkId = "item_neogene_titanis_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_titanis",
                descriptionSlug = "neogene_titanis_description",
                timeScale = "4.9-1.8"
            ),
            DTO(
                id = 105,
                thumbnailId = "item_neogene_deinotherium_thumb",
                artworkId = "item_neogene_deinotherium",
                artworkAuthor = null,
                additionalArtworkId = "item_neogene_deinotherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_deinotherium",
                descriptionSlug = "neogene_deinotherium_description",
                timeScale = "13.0-0.781"
            ),
            DTO(
                id = 106,
                thumbnailId = "item_neogene_stegotetrabelodon_thumb",
                artworkId = "item_neogene_stegotetrabelodon",
                artworkAuthor = "Mauricio Antón",
                additionalArtworkId = "item_neogene_stegotetrabelodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_stegotetrabelodon",
                descriptionSlug = "neogene_stegotetrabelodon_description",
                timeScale = "8.0-4.2"
            ),
            DTO(
                id = 107,
                thumbnailId = "item_neogene_platybelodon_thumb",
                artworkId = "item_neogene_platybelodon",
                artworkAuthor = null,
                additionalArtworkId = "item_neogene_platybelodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_platybelodon",
                descriptionSlug = "neogene_platybelodon_description",
                timeScale = "15.97-10.0"
            ),
            DTO(
                id = 108,
                thumbnailId = "item_neogene_ardipithecus_thumb",
                artworkId = "item_neogene_ardipithecus",
                artworkAuthor = null,
                additionalArtworkId = "item_neogene_ardipithecus_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_ardipithecus",
                descriptionSlug = "neogene_ardipithecus_description",
                timeScale = "5.77-4.4"
            ),
            DTO(
                id = 109,
                thumbnailId = "item_neogene_megalodon_thumb",
                artworkId = "item_neogene_megalodon",
                artworkAuthor = "Alberto Gennari",
                additionalArtworkId = "item_neogene_megalodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_megalodon",
                descriptionSlug = "neogene_megalodon_description",
                timeScale = "23.0–3.6"
            ),
            DTO(
                id = 110,
                thumbnailId = "item_neogene_livyatan_thumb",
                artworkId = "item_neogene_livyatan",
                artworkAuthor = "A. Gennari",
                additionalArtworkId = "item_neogene_livyatan_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_levyatan",
                descriptionSlug = "neogene_levyatan_description",
                timeScale = "9.9-5.0"
            ),
            DTO(
                id = 161,
                thumbnailId = "item_neogene_gomphotherium_thumb",
                artworkId = "item_neogene_gomphotherium",
                artworkAuthor = null,
                additionalArtworkId = "item_neogene_gomphotherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_gomphotherium",
                descriptionSlug = "neogene_gomphotherium_description",
                timeScale = "19.5-5.0"
            ),
            DTO(
                id = 162,
                thumbnailId = "item_neogene_hipparion_thumb",
                artworkId = "item_neogene_hipparion",
                artworkAuthor = null,
                additionalArtworkId = "item_neogene_hipparion_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_hipparion",
                descriptionSlug = "neogene_hipparion_description",
                timeScale = "9.9-5.3"
            ),
            DTO(
                id = 163,
                thumbnailId = "item_neogene_sivatherium_thumb",
                artworkId = "item_neogene_sivatherium",
                artworkAuthor = null,
                additionalArtworkId = "item_neogene_sivatherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_sivatherium",
                descriptionSlug = "neogene_sivatherium_description",
                timeScale = "7.0-0.8"
            ),
            DTO(
                id = 164,
                thumbnailId = "item_neogene_machairodus_thumb",
                artworkId = "item_neogene_machairodus",
                artworkAuthor = "GreatCakeMods",
                additionalArtworkId = "item_neogene_machairodus_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_machairodus",
                descriptionSlug = "neogene_machairodus_description",
                timeScale = "12.5-8.7"
            ),
            DTO(
                id = 165,
                thumbnailId = "item_neogene_thalassocnus_thumb",
                artworkId = "item_neogene_thalassocnus",
                artworkAuthor = "Science Photo Library",
                additionalArtworkId = "item_neogene_thalassocnus_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_thalassocnus",
                descriptionSlug = "neogene_thalassocnus_description",
                timeScale = "7.0-3.0"
            ),
            DTO(
                id = 166,
                thumbnailId = "item_neogene_toxodon_thumb",
                artworkId = "item_neogene_toxodon",
                artworkAuthor = null,
                additionalArtworkId = "item_neogene_toxodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "neogene_toxodon",
                descriptionSlug = "neogene_toxodon_description",
                timeScale = "2.6-0.011"
            )
        )
    }

    private fun getQuaternaryLifeForms(): List<DTO> {
        return listOf(
            DTO(
                id = 111,
                thumbnailId = "item_quaternary_mammuthus_thumb",
                artworkId = "item_quaternary_mammuthus",
                artworkAuthor = null,
                additionalArtworkId = "item_quaternary_mammuthus_info",
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_mammutus",
                descriptionSlug = "quaternary_mammutus_description",
                timeScale = "5.332-0.0037"
            ),
            DTO(
                id = 112,
                thumbnailId = "item_quaternary_coelodonta_thumb",
                artworkId = "item_quaternary_coelodonta",
                artworkAuthor = "Mihin89",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_coelodonta",
                descriptionSlug = "quaternary_coelodonta_description",
                timeScale = "4.2-0.012"
            ),
            DTO(
                id = 113,
                thumbnailId = "item_quaternary_megatherium_thumb",
                artworkId = "item_quaternary_megatherium",
                artworkAuthor = null,
                additionalArtworkId = "item_quaternary_megatherium_info",
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_megatherium",
                descriptionSlug = "quaternary_megatherium_description",
                timeScale = "17.5-0.01"
            ),
            DTO(
                id = 114,
                thumbnailId = "item_quaternary_smilodon_thumb",
                artworkId = "item_quaternary_smilodon",
                artworkAuthor = "Delphi",
                additionalArtworkId = "item_quaternary_smilodon_info",
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_smilodon",
                descriptionSlug = "quaternary_smilodon_description",
                timeScale = "2.5–0.01"
            ),
            DTO(
                id = 115,
                thumbnailId = "item_quaternary_coelacanth_thumb",
                artworkId = "item_quaternary_coelacanth",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_coelacanth",
                descriptionSlug = "quaternary_coelacanth_description",
                timeScale = "409.0-0.0"
            ),
            DTO(
                id = 116,
                thumbnailId = "item_quaternary_xiphosura_thumb",
                artworkId = "item_quaternary_xiphosura",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_xiphosura",
                descriptionSlug = "quaternary_xiphosura_description",
                timeScale = "450.0-0.0"
            ),
            DTO(
                id = 117,
                thumbnailId = "item_quaternary_ginkgo_thumb",
                artworkId = "item_quaternary_ginkgo",
                artworkAuthor = null,
                additionalArtworkId = "item_quaternary_ginkgo_info",
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_ginkgo",
                descriptionSlug = "quaternary_ginkgo_description",
                timeScale = "270.0-0.0"
            ),
            DTO(
                id = 118,
                thumbnailId = "item_quaternary_welwitschia_thumb",
                artworkId = "item_quaternary_welwitschia",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_welwitschia",
                descriptionSlug = "quaternary_welwitschia_description",
                timeScale = "242.0-0.0"
            ),
            DTO(
                id = 119,
                thumbnailId = "item_quaternary_megalania_thumb",
                artworkId = "item_quaternary_megalania",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_megalania",
                descriptionSlug = "quaternary_megalania_description",
                timeScale = "1.5-0.04"
            ),
            DTO(
                id = 120,
                thumbnailId = "item_quaternary_tuatara_thumb",
                artworkId = "item_quaternary_tuatara",
                artworkAuthor = null,
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_tuatara",
                descriptionSlug = "quaternary_tuatara_description",
                timeScale = "240.0-0.0"
            ),
            DTO(
                id = 121,
                thumbnailId = "item_quaternary_dodo_thumb",
                artworkId = "item_quaternary_dodo",
                artworkAuthor = "Paul Roget",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_dodo",
                descriptionSlug = "quaternary_dodo_description",
                timeScale = "0.012-0.0004"
            ),
            DTO(
                id = 122,
                thumbnailId = "item_quaternary_homo_thumb",
                artworkId = "item_quaternary_homo",
                artworkAuthor = "Andipantz",
                additionalArtworkId = null,
                additionalArtworkAuthor = null,
                titleSlug = "quaternary_human",
                descriptionSlug = "quaternary_human_description",
                timeScale = "0.126-0.0"
            )
        )
    }
}