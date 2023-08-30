package com.luja93.dbms_performance_benchmark

import com.google.gson.annotations.SerializedName

class Vector {
    @SerializedName("name")
    var name: String = ""
    @SerializedName("vector")
    var vector: ArrayList<Float> = ArrayList()
}