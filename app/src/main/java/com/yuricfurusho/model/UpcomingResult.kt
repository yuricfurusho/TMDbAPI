package com.yuricfurusho.model
import com.google.gson.annotations.SerializedName



/**
 * Created by Yuri Furusho on 26/12/18.
 */
data class UpcomingResult(
    val dates: Dates = Dates(),
    val page: Int = 0,
    val results: List<MovieResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)

data class Dates(
    val maximum: String = "",
    val minimum: String = ""
)