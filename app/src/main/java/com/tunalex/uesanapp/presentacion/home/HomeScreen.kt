package com.tunalex.uesanapp.presentacion.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.tunalex.uesanapp.data.model.CountryModel

val mockCountries = listOf(
    CountryModel("colombia", 5, "https://flagcdn.com/w320/co.png"),
    CountryModel("argentina", 3, "https://flagcdn.com/w320/ar.png"),
    CountryModel("chile", 1, "https://flagcdn.com/w320/cl.png"),
    CountryModel("peru", 2, "https://flagcdn.com/w320/pe.png"),
    CountryModel("ecuador", 4, "https://flagcdn.com/w320/ec.png"),
    CountryModel("brasil", 6, "https://flagcdn.com/w320/br.png"),
    CountryModel("uruguay", 7, "https://flagcdn.com/w320/uy.png"),
    CountryModel("paraguay", 8, "https://flagcdn.com/w320/py.png"),
    CountryModel("bolivia", 9, "https://flagcdn.com/w320/bo.png"),
    CountryModel("venezuela", 10, "https://flagcdn.com/w320/ve.png")
)

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize().statusBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Ranking fifa 2026", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn{
            items(mockCountries){ country ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                ){
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(country.imageUrl),
                            contentDescription = country.name,
                            modifier = Modifier.size(60.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(country.name.uppercase(), style = MaterialTheme.typography.titleMedium)
                            Text("Ranking FIFA 2026: ${country.ranking}")
                        }
                    }
                }
            }
        }
    }
}