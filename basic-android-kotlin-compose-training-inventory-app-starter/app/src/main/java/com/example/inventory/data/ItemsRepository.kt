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

interface ItemsRepository {
    /**
     * Fungsi untuk menambahkan item baru ke sumber data.
     * Misalnya, dapat menambahkan item ke database Room.
     */
    suspend fun insertItem(item: Item)

    /**
     * Fungsi untuk memperbarui item yang sudah ada dalam sumber data.
     * Operasi ini dapat digunakan untuk memperbarui data item yang sudah disimpan.
     */
    suspend fun updateItem(item: Item)

    /**
     * Fungsi untuk menghapus item dari sumber data.
     * Berguna untuk menghapus item tertentu berdasarkan id-nya.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Fungsi untuk mengambil daftar semua item dari sumber data.
     * Fungsi ini dapat mengambil semua item dari database untuk ditampilkan di UI.
     */
    suspend fun getAllItems(): List<Item>

    /**
     * Fungsi untuk mengambil satu item berdasarkan ID dari sumber data.
     * Berguna jika aplikasi memerlukan detail dari satu item tertentu.
     */
    suspend fun getItemById(id: Int): Item?
}
