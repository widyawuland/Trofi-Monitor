package trpl.nim234311056.trofimonitor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddClubScreen(onClubAdded: (Club) -> Unit) {
    var name by remember { mutableStateOf("") } // Menyimpan nama Club
    var premierLeague by remember { mutableStateOf(0) } // Menyimpan jumlah trofi Premier League
    var faCup by remember { mutableStateOf(0) } // Menyimpan jumlah trofi FA Cup
    var eflCup by remember { mutableStateOf(0) } // Menyimpan jumlah trofi EFL Cup
    var championsLeague by remember { mutableStateOf(0) } // Menyimpan jumlah trofi Champions League
    var europaLeague by remember { mutableStateOf(0) } // Menyimpan jumlah trofi Europa League


    Column(modifier = Modifier.padding(50.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Club Name") }) // Input untuk memasukkan nama Club
        TextField(value = premierLeague.toString(), onValueChange = { premierLeague = it.toInt() }, label = { Text("Premier League Wins") }) // Input untuk memasukkan jumlah kemenangan Premier League
        TextField(value = faCup.toString(), onValueChange = { faCup = it.toInt() }, label = { Text("FA Cup Wins") }) // Input untuk memasukkan jumlah kemenangan FA Cup
        TextField(value = eflCup.toString(), onValueChange = { eflCup = it.toInt() }, label = { Text("EFL Cup Wins") })  // Input untuk memasukkan jumlah kemenangan EFL Cup
        TextField(value = championsLeague.toString(), onValueChange = { championsLeague = it.toInt() }, label = { Text("Champions League Wins") }) // Input untuk memasukkan jumlah kemenangan Champions League
        TextField(value = europaLeague.toString(), onValueChange = { europaLeague = it.toInt() }, label = { Text("Europa League Wins") }) // Input untuk memasukkan jumlah kemenangan Europa League

        // Button untuk menambahkan data Club baru
        Button(
            onClick = {
                onClubAdded(Club(name, premierLeague, faCup, eflCup, championsLeague, europaLeague))
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Add Club")
        }
    }
}

