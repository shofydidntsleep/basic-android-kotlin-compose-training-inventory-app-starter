/*
 * InventoryApplication adalah subclass dari Application yang bertindak sebagai titik awal untuk mengatur
 * dependensi aplikasi. Kelas ini menyediakan instance `AppContainer`, yang bertugas untuk menyediakan
 * dependensi yang dibutuhkan oleh komponen aplikasi lainnya, termasuk data terkait Room.
 *
 * Pada Android, pendekatan seperti ini digunakan untuk Dependency Injection, memastikan bahwa dependensi
 * disediakan dalam satu tempat yang terpusat, sehingga memudahkan pengelolaan, pengujian, dan skalabilitas.
 */

package com.example.inventory

import android.app.Application
import com.example.inventory.data.AppContainer
import com.example.inventory.data.AppDataContainer

class InventoryApplication : Application() {

    /*
     * Properti `container` adalah instance dari `AppContainer`, yang bertugas untuk menyimpan dan
     * menyediakan objek-objek yang dibutuhkan di dalam aplikasi. Contohnya, objek-objek ini mungkin mencakup:
     * - Repository: Abstraksi untuk sumber data, seperti database Room atau API.
     * - DAO (Data Access Object): Interface untuk berinteraksi langsung dengan tabel dalam Room.
     * - ViewModel Factory: Membantu membuat ViewModel dengan dependensi yang telah disediakan.
     *
     * Dengan menggunakan AppContainer, kita memisahkan logika pembuatan objek dari komponen aplikasi lainnya,
     * sehingga memudahkan pengelolaan dependensi.
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        /*
         * Saat aplikasi dibuat, `container` diinisialisasi dengan `AppDataContainer`. Kelas
         * `AppDataContainer` adalah implementasi dari `AppContainer` yang menyediakan data
         * terkait konfigurasi atau objek-objek yang mengelola Room Database.
         *
         * `this` dalam konstruktor `AppDataContainer` mengacu pada context aplikasi, yang
         * dibutuhkan untuk inisialisasi database Room.
         *
         * Dengan mengatur dependensi di sini, kita memastikan bahwa seluruh aplikasi menggunakan
         * dependensi yang konsisten dan terpusat.
         */
        container = AppDataContainer(this)
    }
}
