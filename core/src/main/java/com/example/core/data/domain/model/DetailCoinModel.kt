package com.example.core.data.domain.model


data class QuoteModel(
    val USD: USDModel? = null
)

data class USDModel(
    val price: Double? = null,
    val market_cap: Double? = null,
    val market_cap_dominance: Double? = null,
    val percent_change_1h: Double? = null,
    val percent_change_24h: Double? = null,
    val percent_change_30d: Double? = null,
    val percent_change_7d: Double? = null,
    val volume_change_24h: Double? = null,
    val volume_24h: Double? = null,
    val fully_diluted_market_cap: Double? = null,
    val last_updated: String? = null

)

data class TagsItemModel(

    val name: String? = null,

    val category: String? = null,

    val slug: String? = null
)

data class DetailCoinModel(
    val symbol: String? = null,
    val circulating_supply: Double? = null,
    val last_updated: String? = null,
    val is_active: Any? = null,
    val total_supply: Double? = null,
    val cmc_rank: Int? = null,
    val self_reported_circulating_supply: Double? = null,
    val platform: PlatformModel? = null,
    val tags: List<TagsItemModel>? = null,
    val date_added: String? = null,
    val quote: QuoteModel? = null,
    val num_market_pairs: Int? = null,
    val name: String? = null,
    val max_supply: Double? = null,
    val id: Int? = null,
    val self_reported_market_cap: Double? = null,
    val is_fiat: Any? = null,
    val slug: String? = null
)


data class PlatformModel(

    val id: Int? = null,

    val name: String? = null,

    val symbol: String? = null,

    val slug: String? = null,

    val tokenAddress: String? = null
)



