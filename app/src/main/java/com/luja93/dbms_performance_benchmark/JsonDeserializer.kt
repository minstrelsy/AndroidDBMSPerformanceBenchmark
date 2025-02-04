package com.luja93.dbms_performance_benchmark

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ListFloatDeserializer: JsonDeserializer<List<Float>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Float> {
        val floatArray = context?.deserialize<FloatArray>(json, FloatArray::class.java)
        return floatArray!!.asList()
    }
}