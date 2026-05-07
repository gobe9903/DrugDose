package com.example.drugdose

import com.google.gson.annotations.SerializedName

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
    val valore_dosaggio: Double? = null,
    val valore_dosaggio_min: Double? = null,
    val valore_dosaggio_max: Double? = null,
    val unita_dosaggio: String,
    val frequenza_ore: Int? = null,
    val frequenza_ore_max: Int? = null,
    val numero_somministrazioni_max_giornaliere: Int? = null,
    val numero_somministrazioni_possibili: List<Int>? = null,
    val input_richiesti: List<String>,
    val limiti: Limiti,
    val alert: List<String>,
    val note: List<String>,
    val fonte: Fonte,
    
    // Campi per logiche avanzate (fasce, tabelle, ecc.)
    // Usiamo Map per flessibilità nelle diverse strutture di fasce
    val fasce_peso: List<Map<String, Any>>? = null,
    val fasce_eta: List<Map<String, Any>>? = null,
    val schemi_posologici: List<Map<String, Any>>? = null,
    val tabella_compresse: List<Map<String, Any>>? = null,
    val dose_singola: Map<String, Any>? = null,
    val mg_per_goccia: Double? = null,
    val dose_massima_per_somministrazione_mg: Double? = null,
    val durata_giorni_standard: Int? = null
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
    val note_formato: List<String>,
    val mg_per_goccia: Double? = null,
    val percentuale: Double? = null
)
