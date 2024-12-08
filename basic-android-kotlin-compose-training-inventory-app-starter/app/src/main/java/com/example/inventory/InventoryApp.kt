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

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.inventory

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.R.string
import com.example.inventory.ui.navigation.InventoryNavHost

/**
 * Fungsi `InventoryApp` adalah fungsi utama pada aplikasi yang
 * mengatur navigasi antar layar (screen) dalam aplikasi inventaris ini.
 * Fungsi ini menggunakan `NavHostController` untuk mengelola navigasi.
 * `InventoryNavHost` dipanggil untuk mendefinisikan struktur navigasi.
 *
 * Fungsi ini tidak langsung berhubungan dengan `Room`, tetapi membantu
 * pengguna menavigasi antarlayar yang mungkin menampilkan data dari database `Room`.
 */
@Composable
fun InventoryApp(navController: NavHostController = rememberNavController()) {
    // Memanggil `InventoryNavHost` untuk menginisialisasi dan menampilkan navigasi aplikasi
    InventoryNavHost(navController = navController)
}

/**
 * `InventoryTopAppBar` adalah komponen `AppBar` untuk menampilkan judul aplikasi dan
 * ikon navigasi kembali (back) jika diperlukan. Fitur ini berfungsi untuk memberikan
 * pengalaman pengguna yang konsisten di berbagai layar aplikasi.
 *
 * `canNavigateBack` adalah parameter yang mengontrol apakah tombol kembali ditampilkan.
 * Ini berguna saat aplikasi mengizinkan pengguna bernavigasi di antara layar-layar yang
 * menampilkan data yang dikelola `Room`, misalnya detail item inventaris.
 *
 * @param title Judul dari layar yang sedang ditampilkan.
 * @param canNavigateBack Menentukan apakah ikon navigasi kembali (back) perlu ditampilkan.
 * @param modifier Modifier untuk menyesuaikan tampilan komponen `AppBar`.
 * @param scrollBehavior Menyediakan perilaku untuk pengguliran (scrolling).
 * @param navigateUp Fungsi yang akan dijalankan ketika tombol kembali ditekan.
 */
@Composable
fun InventoryTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Filled.ArrowBack,
                        contentDescription = stringResource(string.back_button)
                    )
                }
            }
        }
    )
}

/**
 * Catatan: Meskipun `InventoryTopAppBar` dan `InventoryApp` tidak berinteraksi
 * langsung dengan `Room` atau pengelolaan data, kedua komponen ini memainkan
 * peran penting dalam pengaturan navigasi dan tata letak aplikasi.
 * Dalam konteks aplikasi yang menggunakan `Room`, layar yang diatur oleh `InventoryApp`
 * dapat memuat data dari database `Room`, dan `AppBar` akan berfungsi sebagai
 * elemen tata letak untuk menavigasi antar layar yang berisi data.
 */
