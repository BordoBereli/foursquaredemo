package com.kutluoglu.cache

import com.kutluoglu.cache.database.db.FoursquareDemoDb
import com.kutluoglu.cache.database.entity.VenueEntity
import com.kutluoglu.cache.mapper.DbUserMapper
import com.kutluoglu.cache.mapper.DbVenueMapper
import com.kutluoglu.data.model.DataUser
import com.kutluoglu.data.model.DataVenue
import com.kutluoglu.data.repository.Cache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */

class FoursquareCacheImp @Inject constructor(
        private val foursquareDemoDb: FoursquareDemoDb,
        private val foursquareSharedPreference: FoursquareSharedPreference,
        private val venueMapper: DbVenueMapper,
        private val userMapper: DbUserMapper
) : Cache {
    override fun saveVenues(venues: List<DataVenue>): Completable {
        return Completable.defer {
            val venueEntities = mutableListOf<VenueEntity>()
            venues.map {
                 venueEntities.add(venueMapper.mapToCached(it))
            }
            if (isExpired()) {
                foursquareDemoDb.venueDao().insertAllVenues(venueEntities)
                setLastCacheTime()
            }
            Completable.complete()
        }
    }

    override fun getVenues(): Flowable<List<DataVenue>> {
        return foursquareDemoDb.venueDao().getAllVenues()
                .map { venues ->
                    val dataVenues = mutableListOf<DataVenue>()
                    venues.map {
                        dataVenues.add(venueMapper.mapFromCached(it))
                    }

                    dataVenues
                }
    }

    override fun getVenueByID(venueID: String): Single<DataVenue> {
        return Single.defer {
            foursquareDemoDb.venueDao().getVenueByID(venueID)
                    .map {
                        venueMapper.mapFromCached(it)
                    }
        }
    }

    override fun addUser(user: DataUser): Completable {
        return Completable.defer {
            foursquareDemoDb.userDao().insertUser(userMapper.mapToCached(user))
            Completable.complete()
        }
    }

    override fun getUser(userID: String?): Single<DataUser> {
        return if(userID != null) {
            Single.defer {
                foursquareDemoDb.userDao().getUsers().map {
                    if(it != null) userMapper.mapFromCached(it)
                    else DataUser("0", "")
                }
                }
            } else {
                Single.just(DataUser("0", ""))
            }
    }

    override fun removeUser(): Single<Boolean> {
        return Single.defer {
            val count = foursquareDemoDb.userDao().deleteAll()
            Single.just(count > 0)
        }
    }

    override fun isCached(): Single<Boolean> {
        return Single.defer {
            val venueEntities = foursquareDemoDb.venueDao().getAllVenues().firstOrError().blockingGet()
            Single.just(venueEntities.isNotEmpty())
        }
    }

    override fun setLastCacheTime() {
        foursquareSharedPreference.lastCacheTime = System.currentTimeMillis()
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = foursquareSharedPreference.lastCacheTime
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    override fun isLogin(): Single<Boolean> {
        return Single.defer {
            try {
                val user = foursquareDemoDb.userDao().getUsers().blockingGet()
                val login = user != null
                Single.just(login)
            } catch (ex: Exception) {
                Single.just(false)
            }

        }
    }

    companion object {
        private const val EXPIRATION_TIME = (5 * 60 * 1000).toLong() // 5 Minutes
    }
}