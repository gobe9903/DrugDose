package com.example.drugdose

import android.content.Context
import com.google.gson.Gson
import java.io.InputStreamReader

class DrugRepository(private val context: Context) {
    fun getDrugs(): List<PrincipioAttivo> {
        return try {
            val inputStream = context.assets.open("drugs.json")
            val reader = InputStreamReader(inputStream)
            val drugData = Gson().fromJson(reader, DrugData::class.java)
            drugData.principi_attivi
        } catch (e: Exception) {
            emptyList()
        }
    }
}
