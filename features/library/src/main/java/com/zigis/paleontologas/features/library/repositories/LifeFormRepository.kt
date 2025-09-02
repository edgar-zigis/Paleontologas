package com.zigis.paleontologas.features.library.repositories

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.sqlite.db.SimpleSQLiteQuery
import com.zigis.paleontologas.core.architecture.BaseRepository
import com.zigis.paleontologas.features.library.R
import com.zigis.paleontologas.features.library.data.LifeFormDao
import com.zigis.paleontologas.features.library.data.LifeForm

class LifeFormRepository(
    private val context: Context,
    private val lifeFormDao: LifeFormDao
) : BaseRepository<LifeForm>(lifeFormDao, tableName = "life_forms") {

    @WorkerThread
    override suspend fun initialize() {
        val list = mutableListOf<LifeForm>()
        with(list) {
            insertArchean(this)
            insertProterozoic(this)
            insertCambrian(this)
            insertOrdovician(this)
            insertSilurian(this)
            insertDevonian(this)
            insertCarboniferous(this)
            insertPermian(this)
            insertTriassic(this)
            insertJurassic(this)
            insertCretaceous(this)
            insertPaleogene(this)
            insertNeogene(this)
            insertQuaternary(this)
        }
        deleteAll()
        lifeFormDao.insertAll(list)
    }

    private fun insertArchean(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.archean_titles)
        val timeScales = context.resources.getStringArray(R.array.archean_time_scales)
        val descriptions = context.resources.getStringArray(R.array.archean_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.archean_thumbs)
        val additionalImages = context.resources.getStringArray(R.array.archean_additional_images)
        val images = context.resources.getStringArray(R.array.archean_images)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 2,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = "",
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertProterozoic(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.proterozoic_titles)
        val timeScales = context.resources.getStringArray(R.array.proterozoic_time_scales)
        val descriptions = context.resources.getStringArray(R.array.proterozoic_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.proterozoic_thumbs)
        val additionalImages = context.resources.getStringArray(R.array.proterozoic_additional_images)
        val images = context.resources.getStringArray(R.array.proterozoic_images)
        val artworkAuthors = context.resources.getStringArray(R.array.proterozoic_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 3,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertCambrian(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.cambrian_titles)
        val timeScales = context.resources.getStringArray(R.array.cambrian_time_scales)
        val descriptions = context.resources.getStringArray(R.array.cambrian_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.cambrian_thumbs)
        val additionalImages = context.resources.getStringArray(R.array.cambrian_additional_images)
        val images = context.resources.getStringArray(R.array.cambrian_images)
        val artworkAuthors = context.resources.getStringArray(R.array.cambrian_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 4,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertOrdovician(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.ordovician_titles)
        val timeScales = context.resources.getStringArray(R.array.ordovician_time_scales)
        val descriptions = context.resources.getStringArray(R.array.ordovician_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.ordovician_thumbs)
        val additionalImages =
            context.resources.getStringArray(R.array.ordovician_additional_images)
        val images = context.resources.getStringArray(R.array.ordovician_images)
        val artworkAuthors = context.resources.getStringArray(R.array.ordovician_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 5,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertSilurian(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.silurian_titles)
        val timeScales = context.resources.getStringArray(R.array.silurian_time_scales)
        val descriptions = context.resources.getStringArray(R.array.silurian_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.silurian_thumbs)
        val additionalImages = context.resources.getStringArray(R.array.silurian_additional_images)
        val images = context.resources.getStringArray(R.array.silurian_images)
        val artworkAuthors = context.resources.getStringArray(R.array.silurian_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 6,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertDevonian(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.devonian_titles)
        val timeScales = context.resources.getStringArray(R.array.devonian_time_scales)
        val descriptions = context.resources.getStringArray(R.array.devonian_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.devonian_thumbs)
        val additionalImages = context.resources.getStringArray(R.array.devonian_additional_images)
        val images = context.resources.getStringArray(R.array.devonian_images)
        val artworkAuthors = context.resources.getStringArray(R.array.devonian_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 7,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertCarboniferous(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.carboniferous_titles)
        val timeScales = context.resources.getStringArray(R.array.carboniferous_time_scales)
        val descriptions = context.resources.getStringArray(R.array.carboniferous_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.carboniferous_thumbs)
        val additionalImages =
            context.resources.getStringArray(R.array.carboniferous_additional_images)
        val images = context.resources.getStringArray(R.array.carboniferous_images)
        val artworkAuthors = context.resources.getStringArray(R.array.carboniferous_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 8,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertPermian(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.permian_titles)
        val timeScales = context.resources.getStringArray(R.array.permian_time_scales)
        val descriptions = context.resources.getStringArray(R.array.permian_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.permian_thumbs)
        val additionalImages =
            context.resources.getStringArray(R.array.permian_additional_images)
        val images = context.resources.getStringArray(R.array.permian_images)
        val artworkAuthors = context.resources.getStringArray(R.array.permian_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 9,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertTriassic(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.triassic_titles)
        val timeScales = context.resources.getStringArray(R.array.triassic_time_scales)
        val descriptions = context.resources.getStringArray(R.array.triassic_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.triassic_thumbs)
        val additionalImages = context.resources.getStringArray(R.array.triassic_additional_images)
        val images = context.resources.getStringArray(R.array.triassic_images)
        val artworkAuthors = context.resources.getStringArray(R.array.triassic_photo_authors)
        val additionalArtworkAuthors =
            context.resources.getStringArray(R.array.triassic_additional_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 10,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = additionalArtworkAuthors[index]
                )
            )
        }
    }

    private fun insertJurassic(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.jurassic_titles)
        val timeScales = context.resources.getStringArray(R.array.jurassic_time_scales)
        val descriptions = context.resources.getStringArray(R.array.jurassic_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.jurassic_thumbs)
        val additionalImages = context.resources.getStringArray(R.array.jurassic_additional_images)
        val images = context.resources.getStringArray(R.array.jurassic_images)
        val artworkAuthors = context.resources.getStringArray(R.array.jurassic_photo_authors)
        val additionalArtworkAuthors =
            context.resources.getStringArray(R.array.jurassic_additional_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 11,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = additionalArtworkAuthors[index]
                )
            )
        }
    }

    private fun insertCretaceous(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.cretaceous_titles)
        val timeScales = context.resources.getStringArray(R.array.cretaceous_time_scales)
        val descriptions = context.resources.getStringArray(R.array.cretaceous_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.cretaceous_thumbs)
        val additionalImages =
            context.resources.getStringArray(R.array.cretaceous_additional_images)
        val images = context.resources.getStringArray(R.array.cretaceous_images)
        val artworkAuthors = context.resources.getStringArray(R.array.cretaceous_photo_authors)
        val additionalArtworkAuthors =
            context.resources.getStringArray(R.array.cretaceous_additional_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 12,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = additionalArtworkAuthors[index]
                )
            )
        }
    }

    private fun insertPaleogene(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.paleogene_titles)
        val timeScales = context.resources.getStringArray(R.array.paleogene_time_scales)
        val descriptions = context.resources.getStringArray(R.array.paleogene_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.paleogene_thumbs)
        val additionalImages = context.resources.getStringArray(R.array.paleogene_additional_images)
        val images = context.resources.getStringArray(R.array.paleogene_images)
        val artworkAuthors = context.resources.getStringArray(R.array.paleogene_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 13,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertNeogene(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.neogene_titles)
        val timeScales = context.resources.getStringArray(R.array.neogene_time_scales)
        val descriptions = context.resources.getStringArray(R.array.neogene_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.neogene_thumbs)
        val additionalImages = context.resources.getStringArray(R.array.neogene_additional_images)
        val images = context.resources.getStringArray(R.array.neogene_images)
        val artworkAuthors = context.resources.getStringArray(R.array.neogene_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 14,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    private fun insertQuaternary(list: MutableList<LifeForm>) {
        val titles = context.resources.getStringArray(R.array.quaternary_titles)
        val timeScales = context.resources.getStringArray(R.array.quaternary_time_scales)
        val descriptions = context.resources.getStringArray(R.array.quaternary_descriptions)
        val thumbnails = context.resources.getStringArray(R.array.quaternary_thumbs)
        val additionalImages =
            context.resources.getStringArray(R.array.quaternary_additional_images)
        val images = context.resources.getStringArray(R.array.quaternary_images)
        val artworkAuthors = context.resources.getStringArray(R.array.quaternary_photo_authors)
        titles.forEachIndexed { index, title ->
            list.add(
                LifeForm(
                    id = list.size + 1,
                    periodId = 15,
                    title = title,
                    description = descriptions[index],
                    timeScale = timeScales[index],
                    thumbnail = thumbnails[index],
                    artwork = images[index],
                    additionalArtwork = additionalImages[index],
                    artworkAuthor = artworkAuthors[index],
                    additionalArtworkAuthor = ""
                )
            )
        }
    }

    suspend fun findAll(periodId: Int): List<LifeForm> {
        val query = SimpleSQLiteQuery("SELECT * FROM life_forms WHERE periodId=${periodId}")
        return findAll(query)
    }
}