package com.mobile.fairless.features.additional.faq.state

data class FaqState(
    val common_info1: Boolean = false,
    val common_info2: Boolean = false,
    val publish_info1: Boolean = false,
    val publish_info2: Boolean = false,
    val conf_info1: Boolean = false,
    val conf_info2: Boolean = false,
    val conf_info3: Boolean = false,
    val search_info1: Boolean = false,
    val search_info2: Boolean = false,
    val search_info3: Boolean = false,
    val participation_info1: Boolean = false,
    val participation_info2: Boolean = false,
    val mobile_info: Boolean = false,
    val throwable_info1: Boolean = false,
    val throwable_info2: Boolean = false,
    val soc_info1: Boolean = false,
    val soc_info2: Boolean = false,
){
    companion object {
        fun getInstance() = FaqState()
    }
}
