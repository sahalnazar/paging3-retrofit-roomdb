package com.sahalnazar.paging3_retrofit_roomdb.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sahalnazar.paging3_retrofit_roomdb.data.model.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: RemoteKeys)

    @Query("SELECT * FROM remote_keys")
    suspend fun getKeys(): List<RemoteKeys>

}