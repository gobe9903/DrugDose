package com.example.drugdose

data class DrugData(
    val principi_attivi: List<PrincipioAttivo>
)

data class PrincipioAttivo(
    val id: String,
    val nome: String,
    val descrizione: String,
    val categoria_icona: String,
    val regole_dosaggio: List<RegolaDosaggio>,
    val farmaci_commerciali: List<FarmacoCommerciale>
)

data class RegolaDosaggio(
    val id: String,
    val titolo: String,
    val indicazione: String,
    val logica_calcolo: String,
    val formula_placeholder: String,
    val valore_dosaggio: Double?,
    val unita_dosaggio: String,
    val frequenza_ore: Int?,
    val numero_somministrazioni_max_giornaliere: Int?,
    val input_richiesti: List<String>,
    val limiti: Limiti,
    val alert: List<String>,
    val note: List<String>,
    val fonte: Fonte
)

data class Limiti(
    val eta_min_anni: Int?,
    val eta_max_anni: Int?,
    val peso_min_kg: Double?,
    val peso_max_kg: Double?,
    val dose_massima_mg: Double?
)

data class Fonte(
    val tipo: String,
    val titolo: String,
    val url: String,
    val note: String
)

data class FarmacoCommerciale(
    val id: String,
    val nome: String,
    val formato: String,
    val icona: String,
    val concentrazione: Double?,
    val unita_concentrazione: String,
    val forma_somministrazione: String,
    val unita_output_preferita: String,
    val conversione_placeholder: String,
    val note_formato: List<String>
)
