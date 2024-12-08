/*
 * MainActivity adalah titik masuk utama aplikasi yang menggunakan Jetpack Compose untuk mendefinisikan UI.
 * Kelas ini menggunakan `setContent` untuk merender komponen-komponen UI dan menerapkan tema aplikasi.
 *
 * Walaupun file ini berfokus pada UI, fungsi Room atau data sering kali terhubung melalui elemen-elemen
 * yang dideklarasikan di sini, seperti `InventoryApp()`. Komponen ini dapat menggunakan ViewModel
 * atau state yang didukung oleh database Room untuk menyajikan data kepada pengguna.
 */

package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.inventory.ui.theme.InventoryTheme

class MainActivity : ComponentActivity() {

    /*
     * Fungsi `onCreate` adalah metode lifecycle yang dipanggil ketika aktivitas pertama kali dibuat.
     * Dalam konteks Room, data biasanya dimuat atau diinisialisasi dalam ViewModel yang terkait
     * dengan UI yang dirender di dalam `setContent`.
     *
     * Dalam implementasi ini, `InventoryApp()` dapat memuat data dari database Room, misalnya
     * melalui ViewModel yang mengakses DAO. Hal ini memastikan data yang ditampilkan selalu sinkron
     * dengan penyimpanan lokal aplikasi.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        /*
         * `enableEdgeToEdge()` digunakan untuk memungkinkan UI menggunakan area layar penuh,
         * yang dapat meningkatkan estetika antarmuka.
         */
        enableEdgeToEdge()

        // Memanggil metode onCreate superclass untuk melanjutkan proses inisialisasi Android.
        super.onCreate(savedInstanceState)

        /*
         * `setContent` adalah tempat utama untuk merender UI menggunakan Jetpack Compose.
         * Di dalam lambda ini, InventoryTheme diterapkan untuk konsistensi desain.
         * Semua komponen UI di dalamnya, termasuk `InventoryApp`, dapat menggunakan data dari Room
         * melalui ViewModel. Data yang ditampilkan dapat diperbarui secara real-time dengan memanfaatkan
         * fitur LiveData atau State.
         */
        setContent {
            InventoryTheme {
                /*
                 * `Surface` adalah kontainer dasar yang menggunakan warna latar belakang dari tema
                 * untuk membuat UI lebih terorganisir. Dalam hal ini, `InventoryApp` adalah komponen
                 * utama yang dapat menampilkan data dari database Room, seperti daftar inventaris.
                 */
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*
                     * `InventoryApp` adalah fungsi utama yang biasanya digunakan untuk mengatur
                     * navigasi aplikasi dan menyediakan konteks data. Komponen ini dapat menerima ViewModel
                     * atau state untuk menampilkan data dari database Room, seperti tabel inventaris
                     * atau data pengguna.
                     */
                    InventoryApp()
                }
            }
        }
    }
}
