package com.example.core.data.remote.respon

import com.google.gson.annotations.SerializedName

data class ResponseDetail(

    @field:SerializedName("data")
    val data: Map<String, CoinItemDetailRespon>? = null,

    @field:SerializedName("status")
    val status: Status? = null
)

data class PlatformResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("token_address")
    val tokenAddress: String
)

data class CoinItemDetailRespon(

    @field:SerializedName("symbol")
    val symbol: String? = null,

    @field:SerializedName("circulating_supply")
    val circulatingSupply: Double? = null,

    @field:SerializedName("last_updated")
    val lastUpdated: String? = null,

    @field:SerializedName("is_active")
    val isActive: Int? = null,

    @field:SerializedName("total_supply")
    val totalSupply: Double? = null,

    @field:SerializedName("tvl_ratio")
    val tvlRatio: Double? = null,

    @field:SerializedName("cmc_rank")
    val cmcRank: Int? = null,

    @field:SerializedName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: Double? = null,

    @field:SerializedName("platform")
    val platform: PlatformResponse? = null,

    @field:SerializedName("tags")
    val tags: List<TagsItem>? = null,

    @field:SerializedName("date_added")
    val dateAdded: String? = null,

    @field:SerializedName("quote")
    val quote: Quote? = null,

    @field:SerializedName("num_market_pairs")
    val numMarketPairs: Int? = null,

    @field:SerializedName("infinite_supply")
    val infiniteSupply: Boolean? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("max_supply")
    val maxSupply: Double? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("self_reported_market_cap")
    val selfReportedMarketCap: Double? = null,

    @field:SerializedName("is_fiat")
    val isFiat: Int? = null,

    @field:SerializedName("slug")
    val slug: String? = null
)


data class TagsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null
)

