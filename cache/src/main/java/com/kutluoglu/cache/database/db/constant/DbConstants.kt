package com.kutluoglu.cache.database.db.constant

/**
 * Created by F.K. on 2019-06-17.
 */
object DbConstants {
    const val DATABASE_NAME = "FoursquareDemo.db"
    /**
     * Venue Item Table Info
     */
    const val TABLE_NAME_VENUE = "VenueList"
    const val QUERY_GET_VENUES = "SELECT * FROM $TABLE_NAME_VENUE"
    const val QUERY_GET_CATALOGS_BY_ID = "SELECT * FROM $TABLE_NAME_VENUE WHERE id = :venueId"

    /**
     * User Table Info
     */
    const val TABLE_NAME_USER = "User"
    const val QUERY_GET_USERS = "SELECT * FROM $TABLE_NAME_USER"
    const val QUERY_DELETE_USERS = "DELETE FROM $TABLE_NAME_USER"
}