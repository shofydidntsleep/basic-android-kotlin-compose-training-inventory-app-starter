/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import android.content.Context

/**
 * Interface `AppContainer` berfungsi sebagai container atau wadah yang digunakan untuk
 * dependency injection (penyuntikan ketergantungan) di dalam aplikasi.
 * Interface ini memungkinkan aplikasi mendapatkan instance dari objek `ItemsRepository`
 * dengan cara yang konsisten dan modular.
 *
 * Dependency injection membantu aplikasi menjadi lebih fleksibel dan mudah diuji
 * karena komponen-komponennya dapat diganti atau diubah saat pengujian.
 */
interface AppContainer {
    /**
     * Mendefinisikan properti `itemsRepository`, yang merupakan instance dari
     * `ItemsRepository`. Properti ini menyediakan akses ke repository item yang
     * digunakan dalam aplikasi untuk menangani data barang (item).
     */
    val itemsRepository: ItemsRepository
}

/**
 * Kelas `AppDataContainer` adalah implementasi konkret dari `AppContainer`.
 * Kelas ini bertanggung jawab untuk menyediakan instance dari repository yang
 * akan digunakan oleh aplikasi. Implementasi ini menggunakan `OfflineItemsRepository`
 * sebagai sumber data lokal yang tidak memerlukan koneksi jaringan.
 *
 * `AppDataContainer` diinisialisasi dengan `context`, yaitu parameter `Context`
 * dari Android yang memungkinkan kelas ini mengakses berbagai sumber daya aplikasi.
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * `itemsRepository` adalah properti yang diimplementasikan menggunakan `lazy`,
     * yang berarti instance `OfflineItemsRepository` hanya akan dibuat satu kali
     * saat pertama kali diakses. Ini membantu mengoptimalkan penggunaan memori
     * dan memastikan bahwa repository hanya dibuat saat dibutuhkan.
     *
     * `OfflineItemsRepository` adalah kelas yang bertindak sebagai repository
     * lokal. Di dalam implementasi yang lebih kompleks, `OfflineItemsRepository`
     * akan berisi logika untuk berinteraksi dengan `Room Database`, yaitu pustaka
     * dari Jetpack untuk pengelolaan basis data SQLite dalam aplikasi Android.
     *
     * Room mempermudah penyimpanan dan pengambilan data di dalam aplikasi secara
     * offline. Dalam konteks ini, `itemsRepository` menyediakan antarmuka yang
     * memungkinkan aplikasi mengelola data item dari database Room.
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository()
    }
}

/**
 * Interface `ItemsRepository` mendefinisikan kontrak untuk pengelolaan data item.
 * Interface ini dapat memiliki berbagai implementasi, baik yang menggunakan
 * penyimpanan lokal (offline) maupun akses jaringan (online).
 * Dalam aplikasi ini, `ItemsRepository` digunakan untuk memisahkan logika bisnis
 * dari cara penyimpanan datanya, sehingga memudahkan pengembangan dan pengujian.
 */
interface ItemsRepository {
    // Definisikan fungsi-fungsi untuk berinteraksi dengan data item
    fun getItems(): List<Item>
    fun addItem(item: Item)
    fun deleteItem(item: Item)
}

/**
 * `OfflineItemsRepository` adalah implementasi dari `ItemsRepository` yang digunakan
 * untuk mengelola data secara offline. Pada aplikasi ini, `OfflineItemsRepository`
 * kemungkinan besar menggunakan `Room Database` untuk menyimpan data secara lokal.
 * Dengan adanya `OfflineItemsRepository`, data item dapat diakses dan dimanipulasi
 * tanpa koneksi internet.
 */
class OfflineItemsRepository : ItemsRepository {
    // List untuk menyimpan data item sementara. Dalam implementasi penuh, ini
    // akan digantikan oleh interaksi dengan `Room Database`.
    private val itemList = mutableListOf<Item>()

    /**
     * Fungsi `getItems()` digunakan untuk mengambil seluruh item yang disimpan.
     * Dalam implementasi yang menggunakan `Room`, ini akan memanggil `Dao` untuk
     * mengambil data dari tabel di database.
     */
    override fun getItems(): List<Item> = itemList

    /**
     * Fungsi `addItem()` digunakan untuk menambahkan item baru ke dalam database.
     * Dalam skenario yang menggunakan `Room`, ini akan memanggil `Dao` untuk
     * menyimpan data ke dalam tabel.
     */
    override fun addItem(item: Item) {
        itemList.add(item)
    }

    /**
     * Fungsi `deleteItem()` digunakan untuk menghapus item dari database.
     * Dalam implementasi `Room`, ini akan menghapus item berdasarkan ID dari
     * tabel data item.
     */
    override fun deleteItem(item: Item) {
        itemList.remove(item)
    }
}

/**
 * Kelas data `Item` mewakili entitas item yang dikelola oleh `ItemsRepository`.
 * Dalam konteks `Room`, `Item` akan dideklarasikan sebagai entitas basis data,
 * dan setiap properti yang ada di kelas ini akan menjadi kolom dalam tabel.
 */
data class Item(
    val id: Int,
    val name: String,
    val description: String
)
