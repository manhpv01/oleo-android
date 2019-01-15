package com.framgia.oleo.data.source.remote.api.middleware

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

class BooleanAdapter : TypeAdapter<Boolean>() {
    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, value: Boolean?) {
        if (value == null) {
            jsonWriter.nullValue()
            return
        }
        jsonWriter.value(value)
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): Boolean? {
        val peek = jsonReader.peek()
        return when (peek) {
            JsonToken.NULL -> {
                jsonReader.nextNull()
                null
            }
            JsonToken.BOOLEAN -> jsonReader.nextBoolean()
            JsonToken.NUMBER -> jsonReader.nextInt() != 0
            JsonToken.STRING -> java.lang.Boolean.valueOf(jsonReader.nextString())
            else -> null
        }
    }
}
