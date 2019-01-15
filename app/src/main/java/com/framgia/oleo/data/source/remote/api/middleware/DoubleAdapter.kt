package com.framgia.oleo.data.source.remote.api.middleware

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

class DoubleAdapter : TypeAdapter<Double>() {

    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, value: Double?) {
        if (value == null) {
            jsonWriter.nullValue()
            return
        }
        jsonWriter.value(value)
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): Double? {
        val peek = jsonReader.peek()
        return when (peek) {
            JsonToken.NULL -> {
                jsonReader.nextNull()
                null
            }
            JsonToken.NUMBER -> jsonReader.nextDouble()
            JsonToken.STRING -> {
                try {
                    java.lang.Double.valueOf(jsonReader.nextString())
                } catch (e: NumberFormatException) {
                    null
                }
            }
            else -> null
        }
    }
}
