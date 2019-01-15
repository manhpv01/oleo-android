package com.framgia.oleo.data.source.remote.api.middleware

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

class IntegerAdapter : TypeAdapter<Int>() {
    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, value: Int?) {
        if (value == null) {
            jsonWriter.nullValue()
            return
        }
        jsonWriter.value(value)
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): Int? {
        val peek = jsonReader.peek()
        when (peek) {
            JsonToken.NULL -> {
                jsonReader.nextNull()
                return null
            }
            JsonToken.NUMBER -> return jsonReader.nextInt()
            JsonToken.BOOLEAN -> return if (jsonReader.nextBoolean()) 1 else 0
            JsonToken.STRING -> {
                return try {
                    Integer.valueOf(jsonReader.nextString())
                } catch (e: NumberFormatException) {
                    null
                }
            }
            else -> return null
        }
    }
}
