package com.example.drugdose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drugdose.ui.theme.DrugDoseTheme

// Activity principale dell'applicazione
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Abilitazione visualizzazione edge-to-edge
        enableEdgeToEdge()
        
        // Inizializzazione repository e ViewModel per la gestione dei dati
        val repository = DrugRepository(this)
        val viewModel = DrugViewModel(repository)

        // Configurazione del contenuto UI e del tema applicativo
        setContent {
            DrugDoseTheme {
                HomeScreen(viewModel)
            }
        }
    }
}

// Schermata principale: gestione dello stato tramite ViewModel
@Composable
fun HomeScreen(viewModel: DrugViewModel) {
    // Monitoraggio dello stato della lista filtrata e della query di ricerca
    val drugs by viewModel.filteredDrugs.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    // Rendering del layout principale
    HomeScreenContent(
        drugs = drugs,
        searchQuery = searchQuery,
        onSearchQueryChange = { viewModel.onSearchQueryChange(it) }
    )
}

// Struttura del layout: sfondo, header, ricerca e lista farmaci
@Composable
fun HomeScreenContent(
    drugs: List<PrincipioAttivo>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                // Sfondo con gradiente verticale
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFBFEDF3), 
                        Color(0xFF94D3DA)  
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding() // Gestione del padding per la barra di stato
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Sezione Header: Titolo e icona applicativa
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DrugDose",
                    fontSize = 34.sp,
                    color = Color(0xFF00796B),
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = R.drawable.medical_cross_ico),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }

            // Campo di ricerca con stile elevato e forma circolare
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .shadow(elevation = 12.dp, shape = CircleShape),
                shape = CircleShape,
                color = Color.White
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Cerca farmaco o principio attivo", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color(0xFF00796B)
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Seleziona un principio attivo",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF546E7A),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Lista farmaci a scorrimento verticale
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 40.dp)
            ) {
                items(drugs) { drug ->
                    DrugCard(drug)
                }
            }
        }
    }
}

// Componente Card per la visualizzazione del singolo farmaco
@Composable
fun DrugCard(drug: PrincipioAttivo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Azione di navigazione ai dettagli */ },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Contenitore circolare per l'icona della categoria
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFB4DAD7)),
                contentAlignment = Alignment.Center
            ) {
                // Selezione dell'icona basata sulla categoria definita nel database
                val iconRes = when (drug.categoria_icona) {
                    "bag_ico" -> R.drawable.bag_ico
                    "bug_ico" -> R.drawable.bug_ico
                    "cream_ico" -> R.drawable.cream_ico
                    "efervescent_ico" -> R.drawable.efervescent_ico
                    "flat_pill_ico" -> R.drawable.flat_pill_ico
                    "flower_ico" -> R.drawable.flower_ico
                    "heartrate_ico" -> R.drawable.heartrate_ico
                    "inhaler_ico" -> R.drawable.inhaler_ico
                    "medical_cross_ico" -> R.drawable.medical_cross_ico
                    "nasal_spray_ico" -> R.drawable.nasal_spray_ico
                    "pill_ico" -> R.drawable.pill_ico
                    "stomac_ico" -> R.drawable.stomac_ico
                    "syringe_ico" -> R.drawable.syringe_ico
                    "teardrops_ico" -> R.drawable.teardrops_ico
                    "thermometer_ico" -> R.drawable.thermometer_ico
                    "virus_ico" -> R.drawable.virus_ico
                    else -> R.drawable.pill_ico
                }

                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(62.dp)
                )
            }

            // Dettagli farmaco: Nome e descrizione
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = drug.nome,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF263238)
                )
                Text(
                    text = drug.descrizione,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }

            // Indicatore di interazione
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFF00796B),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

// Dati di esempio per l'anteprima statica
private val sampleDataList = listOf(
    PrincipioAttivo("paracetamolo", "Paracetamolo", "Febbre e dolore", "pill_ico", emptyList(), emptyList()),
    PrincipioAttivo("ibuprofene", "Ibuprofene", "Dolore e infiammazione", "flat_pill_ico", emptyList(), emptyList()),
    PrincipioAttivo("amoxicillina", "Amoxicillina", "Infezioni batteriche", "efervescent_ico", emptyList(), emptyList()),
    PrincipioAttivo("ivermectina", "Ivermectina", "Trattamento antiparassitario", "bug_ico", emptyList(), emptyList()),
    PrincipioAttivo("cetirizina", "Cetirizina", "Allergie", "flower_ico", emptyList(), emptyList()),
    PrincipioAttivo("aciclovir", "Aciclovir", "Infezioni virali", "virus_ico", emptyList(), emptyList())
)

// Anteprima del layout per l'ambiente di sviluppo
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DrugDoseTheme {
        HomeScreenContent(
            drugs = sampleDataList,
            searchQuery = "",
            onSearchQueryChange = {}
        )
    }
}
