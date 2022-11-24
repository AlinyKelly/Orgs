package br.com.alinykelly.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    //Converter valor que vem do banco SQLITE para BigDecimal
    @TypeConverter
    fun deDouble(valor: Double?) : BigDecimal {
        return valor?.let { BigDecimal(valor.toString()) } ?: BigDecimal.ZERO
    }

    //Converter o que está no aplicado para a informação que o BD entende
    @TypeConverter
    fun bigDecimalparaDouble(valor: BigDecimal?) : Double? {
        return valor?.let { valor.toDouble() }
    }
}