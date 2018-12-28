package com.yuricfurusho.tmdbapi

import com.yuricfurusho.GenreUtils
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class GenreTest {

    @Test
    fun WHEN_ID_28_GENRE_IS_ACTION() {
        assertThat(GenreUtils.getGenreById(28), `is`("Action"))
    }

    @Test
    fun WHEN_ID_878_GENRE_IS_SCIENCE_FICTION() {
        assertThat(GenreUtils.getGenreById(878), `is`("Science Fiction"))
    }

    @Test
    fun WHEN_ID_16_GENRE_IS_ANIMATION() {
        assertThat(GenreUtils.getGenreById(16), `is`("Animation"))
    }
}