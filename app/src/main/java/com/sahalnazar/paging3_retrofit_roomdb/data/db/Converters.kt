package com.sahalnazar.paging3_retrofit_roomdb.data.db

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {

    class GenreIdsModel {
        @TypeConverter
        fun fromString(value: String): List<Int?>? {
            return Json.decodeFromString(value)
        }

        @TypeConverter
        fun toString(value: List<Int?>?): String {
            return Json.encodeToString(value)
        }
    }

}