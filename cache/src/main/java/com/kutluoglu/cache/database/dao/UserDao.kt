package com.kutluoglu.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kutluoglu.cache.database.db.constant.DbConstants
import com.kutluoglu.cache.database.entity.UserEntity
import io.reactivex.Single

/**
 * Created by F.K. on 2019-06-17.
 *
 */

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity) : Long

    @Query(DbConstants.QUERY_DELETE_USERS)
    fun deleteAll() : Int

    @Query(DbConstants.QUERY_GET_USERS)
    fun getUsers() : Single<UserEntity>
}