package com.kutluoglu.cache.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kutluoglu.cache.database.dao.UserDao
import com.kutluoglu.cache.database.dao.VenueDao
import com.kutluoglu.cache.database.db.constant.DbConstants
import com.kutluoglu.cache.database.entity.UserEntity
import com.kutluoglu.cache.database.entity.VenueEntity
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

@Database(entities = [VenueEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class FoursquareDemoDb @Inject constructor() : RoomDatabase() {
    abstract fun venueDao() : VenueDao
    abstract fun userDao() : UserDao

    companion object {
        @Volatile private var INSTANCE: FoursquareDemoDb? = null
        fun getInsatnce(context: Context) : FoursquareDemoDb =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        FoursquareDemoDb::class.java, DbConstants.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
    }

}