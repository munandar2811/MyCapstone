package com.example.core.data.remote.respon

import com.google.gson.annotations.SerializedName

data class LatesCoinRespon(

    @field:SerializedName("data")
    val data: List<CoinItemRespon>? = null,

    @field:SerializedName("status")
    val status: Status? = null
)

data class Quote(
    @field:SerializedName("USD")
    val uSD: USD? = null
)

data class USD(

    @field:SerializedName("percent_change_30d")
    val percentChange30d: Double? = null,

    @field:SerializedName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double? = null,

    @field:SerializedName("percent_change_1h")
    val percentChange1h: Double? = null,

    @field:SerializedName("last_updated")
    val lastUpdated: String? = null,

    @field:SerializedName("percent_change_24h")
    val percentChange24h: Double? = null,

    @field:SerializedName("market_cap")
    val marketCap: Double? = null,

    @field:SerializedName("volume_change_24h")
    val volumeChange24h: Double? = null,

    @field:SerializedName("price")
    val price: Double? = null,

    @field:SerializedName("volume_24h")
    val volume24h: Double? = null,

    @field:SerializedName("market_cap_dominance")
    val marketCapDominance: Double? = null,

    @field:SerializedName("percent_change_7d")
    val percentChange7d: Double? = null
)

data class CoinItemRespon(

    @field:SerializedName("symbol")
    val symbol: String? = null,

    @field:SerializedName("circulating_supply")
    val circulatingSupply: Double? = null,

    @field:SerializedName("last_updated")
    val lastUpdated: String? = null,

    @field:SerializedName("is_active")
    val isActive: Any? = null,

    @field:SerializedName("total_supply")
    val totalSupply: Double? = null,

    @field:SerializedName("cmc_rank")
    val cmcRank: Int? = null,

    @field:SerializedName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: Double? = null,

    @field:SerializedName("platform")
    val platform: Any? = null,

    @field:SerializedName("tags")
    val tags: List<String?>? = null,

    @field:SerializedName("date_added")
    val dateAdded: String? = null,

    @field:SerializedName("quote")
    val quote: Quote? = null,

    @field:SerializedName("num_market_pairs")
    val numMarketPairs: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("max_supply")
    val maxSupply: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("self_reported_market_cap")
    val selfReportedMarketCap: Double? = null,

    @field:SerializedName("is_fiat")
    val isFiat: Any? = null,

    @field:SerializedName("slug")
    val slug: String? = null
)

