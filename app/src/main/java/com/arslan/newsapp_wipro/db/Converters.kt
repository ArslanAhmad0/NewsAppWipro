package com.arslan.newsapp_wipro.db

import androidx.room.TypeConverter
import com.arslan.newsapp_wipro.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) = source.name

    @TypeConverter
    fun toSource(name: String) = Source(name, name)
}