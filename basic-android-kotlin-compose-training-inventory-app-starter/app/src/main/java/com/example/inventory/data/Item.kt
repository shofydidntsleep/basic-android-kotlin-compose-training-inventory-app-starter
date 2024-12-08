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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Kelas `Item` adalah entitas yang merepresentasikan satu baris data dalam tabel `items`
 * pada database Room. Setiap objek `Item` akan disimpan sebagai satu baris data dalam tabel.
 *
 * Komponen-komponen dalam kelas ini dihubungkan dengan kolom dalam tabel, yang diidentifikasi
 * oleh `@Entity`, `@PrimaryKey`, dan `@ColumnInfo`. `Room` akan menggunakan kelas ini untuk
 * mengelola penyimpanan data dan pengambilan data dari database.
 *
 * Properti-properti yang digunakan:
 * - `id`: Primary key yang unik untuk setiap item, digunakan sebagai pengenal unik.
 * - `name`: Nama dari item yang disimpan.
 * - `price`: Harga per unit dari item yang disimpan.
 * - `quantity`: Jumlah item yang tersedia.
 */
@Entity(tableName = "items")
data class Item(
    // `id` adalah primary key untuk setiap item yang diatur secara otomatis oleh Room
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Kolom `name` menyimpan nama dari setiap item
    @ColumnInfo(name = "name") val name: String,

    // Kolom `price` menyimpan harga dari item dalam bentuk double
    @ColumnInfo(name = "price") val price: Double,

    // Kolom `quantity` menyimpan jumlah atau kuantitas item
    @ColumnInfo(name = "quantity") val quantity: Int
)
