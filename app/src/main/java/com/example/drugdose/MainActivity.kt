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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val repository = DrugRepository(this)
        val viewModel = DrugViewModel(repository)

        setContent {
            DrugDoseTheme {
                HomeScreen(viewModel)
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: DrugViewModel) {
    val drugs by viewModel.filteredDrugs.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    HomeScreenContent(
        drugs = drugs,
        searchQuery = searchQuery,
        onSearchQueryChange = { viewModel.onSearchQueryChange(it) }
    )
}

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
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFBFEDF3), // 3) Chiaro sopra
                        Color(0xFF94D3DA)  // 3) Azzurro sotto
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            // 2) Più spazio in alto
            Spacer(modifier = Modifier.height(32.dp))

            // Header: Titolo e 4) medical_cross_ico.png
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

                // Usiamo Image per caricare la risorsa senza forzare il tint di Icon
                Image(
                    painter = painterResource(id = R.drawable.medical_cross_ico),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }

            // Barra di ricerca
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

            // 2) Distribuzione spazio
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Seleziona un principio attivo",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF546E7A),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Lista card
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

@Composable
fun DrugCard(drug: PrincipioAttivo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
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
            // Icona con cerchio azzurrino
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFB4DAD7)),
                contentAlignment = Alignment.Center
            ) {
                val iconRes = when (drug.categoria_icona) {
                    "pill_ico" -> R.drawable.pill_ico
                    "flat_pill_ico" -> R.drawable.flat_pill_ico
                    "medical_cross_ico" -> R.drawable.medical_cross_ico
                    "bug_ico" -> R.drawable.bug_ico
                    "flower_ico" -> R.drawable.flower_ico
                    "virus_ico" -> R.drawable.virus_ico
                    "teardrops_ico" -> R.drawable.teardrops_ico
                    "syringe_ico" -> R.drawable.syringe_ico
                    else -> R.drawable.pill_ico
                }

                // 1) Icone molto più grandi (52dp)
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(62.dp)
                )
            }

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

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFF00796B),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

private val sampleDataList = listOf(
    PrincipioAttivo("paracetamolo", "Paracetamolo", "Febbre e dolore", "pill_ico", emptyList()),
    PrincipioAttivo("ibuprofene", "Ibuprofene", "Dolore e infiammazione", "flat_pill_ico", emptyList()),
    PrincipioAttivo("amoxicillina", "Amoxicillina", "Infezioni batteriche", "medical_cross_ico", emptyList()),
    PrincipioAttivo("ivermectina", "Ivermectina", "Trattamento antiparassitario", "bug_ico", emptyList()),
    PrincipioAttivo("cetirizina", "Cetirizina", "Allergie", "flower_ico", emptyList()),
    PrincipioAttivo("aciclovir", "Aciclovir", "Infezioni virali", "virus_ico", emptyList())
)

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
