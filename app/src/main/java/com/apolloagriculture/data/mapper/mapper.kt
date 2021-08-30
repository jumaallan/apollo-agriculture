package com.apolloagriculture.data.mapper

import com.apolloagriculture.data.database.entity.Weather

internal fun Weather.toWeatherUIModel(): com.apolloagriculture.data.model.Weather =
    com.apolloagriculture.data.model.Weather(
        lowTemp = this.lowTemp,
        highTemp = this.highTemp,
        icon = this.icon,
        description = this.description,
    )