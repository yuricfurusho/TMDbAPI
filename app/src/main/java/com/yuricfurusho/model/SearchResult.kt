package com.yuricfurusho.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Yuri Furusho on 27/12/18.
 */
data class SearchResult(
    val page: Int = 0,
    val results: List<MovieResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)
