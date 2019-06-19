package com.kutluoglu.data

import com.kutluoglu.data.mapper.UserMapper
import com.kutluoglu.data.mapper.VenueMapper
import com.kutluoglu.data.model.DataUser
import com.kutluoglu.data.model.DataVenue
import com.kutluoglu.data.source.DataStoreFactory
import com.kutluoglu.domain.model.DomainVenue
import com.kutluoglu.domain.model.User
import com.kutluoglu.domain.repository.FoursquareDemoRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-17.
 *
 */
class DataRepository @Inject constructor(
        private val factory: DataStoreFactory,
        private val venueMapper: VenueMapper,
        private val userMapper: UserMapper
) : FoursquareDemoRepository {
    override fun getNearVenuesForSahibinden(): Flowable<List<DomainVenue>> {
        return factory.retreiveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retreiveDataStore(it).getVenues()
                }
                .flatMap {
                    Flowable.just(it.map { dataVenue ->
                        venueMapper.mapFromEntityToDomainModel(dataVenue)})
                }
                .flatMap {
                    saveVenues(it).toSingle { it }.toFlowable()
                }
    }

    override fun getVenueByID(venueID: String): Single<DomainVenue> {
        return factory.retreiveCacheDataStore().getVenueByID(venueID).map {
            venueMapper.mapFromEntityToDomainModel(it)
        }
    }

    override fun getUser(userID: String?): Single<User> {
        var user: User? = null
        factory.retreiveCacheDataStore().getUser(userID).map {
            user = userMapper.mapFromEntityToDomainModel(it)
        }

        return if (user != null && user?.userID != "0") Single.just(user)
            else factory.retreiveRemoteDataStore().getUser(userID).map {
                saveUser(it)
                userMapper.mapFromEntityToDomainModel(it)
            }
    }

    override fun removeUser() : Single<Boolean> {
        return factory.retreiveCacheDataStore().removeUser()

    }

    override fun isLogin(): Single<Boolean> {
        return factory.retreiveCacheDataStore().isLogin()
    }

    private fun saveUser(it: DataUser) {
        factory.retreiveCacheDataStore().addUser(it).subscribe()
    }

    private fun saveVenues(domainVenues: List<DomainVenue>): Completable {
        val dataVenues = mutableListOf<DataVenue>()
        domainVenues.map {
            dataVenues.add(venueMapper.mapToEntityFromDomainModel(it))
        }

        return factory.retreiveCacheDataStore().saveVenues(dataVenues)
    }

}