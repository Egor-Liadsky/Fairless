package com.mobile.fairless.common.utils

import com.soywiz.klock.DateTimeSpan
import com.soywiz.klock.DateTimeTz

fun DateTimeTz.isToday(): Boolean{
    val today = DateTimeTz.nowLocal()
    return this.dayOfYear == today.dayOfYear && this.yearInt == today.yearInt
}

fun DateTimeTz.isYesterday(): Boolean{
    val yesterday = DateTimeTz.nowLocal().minus(DateTimeSpan(days = 1))
    return this.dayOfYear == yesterday.dayOfYear && this.yearInt == yesterday.yearInt
}
