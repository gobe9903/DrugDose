package com.example.drugdose

data class DrugData(
    val principi_attivi: List<PrincipioAttivo>
)

data class PrincipioAttivo(
    val id: String,
    val nome: String,
    val descrizione: String,
    val categoria_icona: String,
    val farmaci_commerciali: List<FarmacoCommerciale>
)

data class FarmacoCommerciale(
    val id: String,
    val nome: String,
    val formato: String,
    val icona: String,
    val concentrazione: Double,
    val unita_concentrazione: String,
    val logica_calcolo: String,
    val valore_dosaggio: Double? = null,
    val unita_dosaggio: String? = null,
    val frequenza_ore: Int,
    val dose_max_die: Double? = null,
    val unita_dose_max: String? = null,
    val alert: String,
    val fonte: String,
    val valore_fisso: Double? = null,
    val unita_valore: String? = null,
    val fasce: List<Fascia>? = null
)

data class Fascia(
    val min_kg: Double,
    val max_kg: Double,
    val dose: Double,
    val unita: String
)
