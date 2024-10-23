package trpl.nim234311056.trofimonitor

// data class bernama Club untuk menyimpan informasi Club sepak bola dan perolehan trofi mereka.
data class Club(
    val name: String,
    val premierLeague: Int?,
    val faCup: Int?,
    val eflCup: Int?,
    val championsLeague: Int?,
    val europaLeague: Int?
) {
    // Untuk menghitung total trofi dari semua kompetisi yang telah dimenangkan oleh Club
    val totalTrophies: Int
        get() = (premierLeague ?: 0) + (faCup ?: 0) + (eflCup ?: 0) + (championsLeague ?: 0) + (europaLeague ?: 0)
}