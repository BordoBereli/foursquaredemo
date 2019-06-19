package com.kutluoglu.cache.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.kutluoglu.cache.database.db.constant.DbConstants
import kotlinx.android.parcel.Parcelize

/**
 * Created by F.K. on 2019-06-17.
 *
 */

@Parcelize
@Entity(tableName = DbConstants.TABLE_NAME_VENUE,
        primaryKeys = ["id"])

data class VenueEntity (
        @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "venueName") val venueName: String
) : Parcelable