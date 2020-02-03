package com.task.ratesapp.core.models.deserializer

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.task.ratesapp.core.models.*
import java.lang.reflect.Type

class ResponseDeserializer : JsonDeserializer<Response> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Response? {

        val banks = ArrayList<Bank>()
        val baseJsonObject = json?.asJsonObject

        baseJsonObject?.keySet()?.forEach { key ->
            val rates = ArrayList<Rate>()
            val title = baseJsonObject.get(key).asJsonObject.get("title").asString

            val listJsonObject = baseJsonObject.get(key).asJsonObject.get("list").asJsonObject

            listJsonObject.keySet()
                .forEach { currency ->

                    val currencyJsonObject = listJsonObject.get(currency)
                        .asJsonObject

                    currencyJsonObject.keySet()
                        .forEach { cashType ->
                            rates.add(
                                Rate(
                                    Currency.valueOf(currency),
                                    Exchange(CashType.values().find { it.type == cashType }
                                        ?: CashType.CASH,
                                        Gson().fromJson(
                                            currencyJsonObject.get(cashType),
                                            ExchangeValues::class.java
                                        ))
                                )
                            )
                        }
                }
            banks.add(Bank(key, title, rates))
        }
        return Response(banks)
    }

}