package com.mobile.fairless.android.utils

import com.mobile.fairless.features.welcome.dto.City

object SearchCity {

    fun filterList(query: String, list: List<City>): List<City> {
        if (query == "") return list

        val filteredItems = mutableListOf<City>()
        list.map { if (!it.name.lowercase().contains(query.lowercase())) filteredItems.add(it) }
        return filteredItems
    }
}
