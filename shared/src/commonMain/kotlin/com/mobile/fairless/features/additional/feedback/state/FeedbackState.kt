package com.mobile.fairless.features.additional.feedback.state

data class FeedbackState(
    val feedbackText: String = "",
    val files: MutableList<ImageFile>? = mutableListOf()
)

data class ImageFile(
    val uri: String,
    val name: String
)
