package com.yuricfurusho.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Yuri Furusho on 27/12/18.
 */
data class MovieDetail(
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection = BelongsToCollection(),
    val budget: Int = 0,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    @SerializedName("imdb_id")
    val imdbId: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany> = listOf(),
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry> = listOf(),
    @SerializedName("release_date")
    val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)

data class BelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    val id: Int = 0,
    val name: String = "",
    @SerializedName("poster_path")
    val posterPath: String = ""
)

data class ProductionCompany(
    val id: Int = 0,
    @SerializedName("logo_path")
    val logoPath: String = "",
    val name: String = "",
    @SerializedName("origin_country")
    val originCountry: String = ""
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String = "",
    val name: String = ""
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)

data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String = "",
    val name: String = ""
)