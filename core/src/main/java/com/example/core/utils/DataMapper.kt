package com.example.core.utils

import com.example.core.data.domain.model.*
import com.example.core.data.local.entity.CoinFavoriteEntity
import com.example.core.data.remote.respon.*

object DataMapper {

    fun coinEntityToModel(coins: List<CoinFavoriteEntity>): List<CoinModel> {
        val coinsModels = ArrayList<CoinModel>()

        coins.map {
            val coinModel = CoinModel(
                id = it.id,
                rank = it.rank,
                name = it.name,
                symbol = it.symbol,
                logoUrl = it.logoUrl,
                price = it.price,
                percentChange24h = it.percentChange24h
            )

            coinsModels.add(coinModel)
        }

        return coinsModels
    }

    fun coinDetailModelToEntity(detailCoinModel: DetailCoinModel): CoinFavoriteEntity {

        val usd = detailCoinModel.quote?.USD!!
        return CoinFavoriteEntity(
            id = detailCoinModel.id,
            rank = detailCoinModel.cmc_rank,
            symbol = detailCoinModel.symbol,
            name = detailCoinModel.name,
            logoUrl = ItemCoin.getUrl(id = detailCoinModel.id),
            price = usd.price!!,
            percentChange24h = usd.percent_change_24h!!
        )
    }

    fun coinResponToModel(coins: List<CoinItemRespon>?): List<CoinModel> {
        val coinsModels = ArrayList<CoinModel>()

        coins?.map {
            val coinModel = CoinModel(
                id = it.id,
                rank = it.cmcRank,
                name = it.name,
                symbol = it.symbol,
                logoUrl = ItemCoin.getUrl(it.id),
                price = ItemCoin.getPrice(it.quote?.uSD),
                percentChange24h = ItemCoin.getPercentage(it.quote?.uSD)
            )

            coinsModels.add(coinModel)
        }

        return coinsModels
    }

    private fun platformResponToModel(platformResponse: PlatformResponse?): PlatformModel {
        return PlatformModel(
            id = platformResponse?.id,
            name = platformResponse?.name,
            symbol = platformResponse?.symbol,
            slug = platformResponse?.slug,
            tokenAddress = platformResponse?.tokenAddress
        )
    }


    fun detailResponToModel(id: String, detailCoinRespon: ResponseDetail): DetailCoinModel {
        val data = detailCoinRespon.data?.get(id)

        return DetailCoinModel(
            symbol = data?.symbol,
            circulating_supply = data?.circulatingSupply,
            last_updated = data?.lastUpdated,
            is_active = data?.isActive,
            total_supply = data?.totalSupply,
            cmc_rank = data?.cmcRank,
            self_reported_circulating_supply = data?.selfReportedCirculatingSupply,
            platform = platformResponToModel(data?.platform),
            tags = tagsResponToModel(data?.tags),
            date_added = data?.dateAdded,
            quote = quoteResponToModel(data?.quote),
            num_market_pairs = data?.numMarketPairs,
            name = data?.name,
            max_supply = data?.maxSupply,
            id = data?.id,
            self_reported_market_cap = data?.selfReportedMarketCap,
            is_fiat = data?.isFiat,
            slug = data?.slug,
        )
    }


    private fun tagsResponToModel(tagsItems: List<TagsItem>?): List<TagsItemModel> {
        val tagsItemsModel = ArrayList<TagsItemModel>()

        tagsItems?.map { tagsItem ->
            val tagItemModel = TagsItemModel(
                name = tagsItem.name,
                category = tagsItem.category,
                slug = tagsItem.slug
            )
            tagsItemsModel.add(tagItemModel)
        }
        return tagsItemsModel
    }


    private fun quoteResponToModel(quote: Quote?): QuoteModel {
        return QuoteModel(
            USD = usdResponToModel(quote?.uSD)
        )
    }

    private fun usdResponToModel(usd: USD?): USDModel {
        return USDModel(
            percent_change_30d = usd?.percentChange30d,
            percent_change_24h = usd?.percentChange24h,
            percent_change_1h = usd?.percentChange1h,
            fully_diluted_market_cap = usd?.fullyDilutedMarketCap,
            last_updated = usd?.lastUpdated,
            market_cap = usd?.marketCap,
            volume_24h = usd?.volume24h,
            volume_change_24h = usd?.volumeChange24h,
            price = usd?.price,
            market_cap_dominance = usd?.marketCapDominance,
            percent_change_7d = usd?.percentChange7d
        )
    }


}


object ItemCoin {
    fun getUrl(id: Int?) = "https://s2.coinmarketcap.com/static/img/coins/64x64/$id.png"
    fun getPrice(usd: USD?): Double {
        return usd?.price as Double
    }

    fun getPercentage(usd: USD?): Double {
        return usd?.percentChange24h as Double
    }
}