package trpl.nim234311056.trofimonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Deklarasi variabel Nama dan NIM
        val studentName = "Widya Wulandari"
        val studentId = "234311056"

        // Deklarasi variabel data Club dan jumlah Trofinya
        val initialClubs = listOf(
            Club("Liverpool", 19, 8, 10, 6, 3),
            Club("Manchester United", 20, 12, 6, 3, 1),
            Club("Arsenal", 13, 14, 2, null, null),
            Club("Manchester City", 9, 7, 8, 1, null),
            Club("Chelsea", 6, 8, 5, 2, 2),
            Club("Tottenham Hotspur", 2, 8, 4, null, null),
        )

        setContent {
            // Variabel untuk menyimpan data Club yang akan ditampilkan
            var clubs by remember { mutableStateOf(initialClubs) }
            // Variabel untuk mengatur apakah form tambah club akan ditampilkan
            var showAddClubForm by remember { mutableStateOf(false) }

            // Layout utama yang menampilkan info mahasiswa dan daftar club
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StudentInfoScreen(studentName, studentId)

                // Menampilkan form untuk menambah club baru jika showAddClubForm bernilai true
                if (showAddClubForm) {
                    AddClubScreen(onClubAdded = { newClub ->
                        clubs = clubs + newClub
                        showAddClubForm = false
                    })
                } else {
                    ClubListScreen(clubs)

                    // Button untuk membuka form tambah club
                    Button(
                        onClick = { showAddClubForm = true },
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text("Tambah Data Club")
                    }

                    // Menampilkan Club yang memiliki total trofi lebih dari 30
                    Text(
                        text = "Club yang memiliki lebih dari 30 trofi : ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(top = 6.dp)
                            .padding(start = 16.dp, top = 6.dp)
                            .fillMaxWidth()
                    )
                    clubs.filter { it.totalTrophies > 30 }.forEach { club ->
                        Text(
                            text = "${club.name}: ${club.totalTrophies} trofi",
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .padding(start = 16.dp, top = 4.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

// Menampilkan informasi nama dan NIM mahasiswa yang sudah dideklarasikan
@Composable
fun StudentInfoScreen(studentName: String, studentId: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Nama        : $studentName",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = "NIM           : $studentId",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

// Menampilkan daftar Club
@Composable
fun ClubListScreen(clubs: List<Club>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(clubs) { club -> // Mengiterasi setiap item club dalam list clubs
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Menampilkan logo club sesuai nama club
                ClubLogoImage(clubName = club.name)

                Spacer(modifier = Modifier.width(16.dp))

                // Menampilkan nama club dan jumlah trofi yang dimiliki
                Column {
                    Text(
                        text = club.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Total Trofi: ${club.totalTrophies}",
                        fontSize = 14.sp,
                    )

                    // Menampilkan pesan jika club belum memenangkan Champions League
                    if (club.championsLeague ?: 0 > 0)
                    else {
                        Text(
                            text = "${club.name} belum pernah memenangkan trofi internasional.",
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClubLogoImage(clubName: String) {
    // Resource gambar sesuai dengan nama club
    val imageResource = when (clubName) {
        "Liverpool" -> R.drawable.liverpool
        "Manchester United" -> R.drawable.manchester_united
        "Arsenal" -> R.drawable.arsenal
        "Chelsea" -> R.drawable.chelsea
        "Manchester City" -> R.drawable.manchester_city
        "Tottenham Hotspur" -> R.drawable.tottenham_hotspur
        else -> R.drawable.resource_default
    }

    // Menampilkan gambar logo club
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "$clubName Logo",
        modifier = Modifier.size(65.dp)
    )
}

// Preview tampilan list club
@Preview(showBackground = true)
@Composable
fun PreviewClubList() {
    val clubs = listOf(
        Club("Liverpool", 19, 8, 10, 6, 3),
        Club("Manchester United", 20, 12, 6, 3,1),
        Club("Arsenal", 13, 14, 2, null, null),
        Club("Chelsea", 6, 8, 5, 2, 2),
        Club("Manchester City", 9, 7, 8, 1, null),
        Club("Tottenham Hotspur", 2, 8, 4, null, null),
    )
    ClubListScreen(clubs)
}