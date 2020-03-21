package com.example.shopitee.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shopitee.models.ItemCartModel

@Dao
interface CartDao {
    @Query("SELECT * from cart_table ORDER BY created_at DESC")
    fun getEntireCart(): List<ItemCartModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ItemCartModel)

    @Query("DELETE FROM cart_table")
    suspend fun deleteAll()

    @Query("DELETE FROM cart_table WHERE id = :id")
    suspend fun deleteItem(id: String)
}