package com.mobile.fairless.common.utils

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.parse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DateTimeTzSerializer : KSerializer<DateTimeTz> {
    override val descriptor = PrimitiveSerialDescriptor("DateTime", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: DateTimeTz) =
        encoder.encodeString(value.toString(DateFormat("yyyy-MM-dd'T'HH:mm:ss")))

    override fun deserialize(decoder: Decoder): DateTimeTz {
        val str = decoder.decodeString().removeSuffix("Z")
        return DateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(str.substringBefore("."))
    }
}
