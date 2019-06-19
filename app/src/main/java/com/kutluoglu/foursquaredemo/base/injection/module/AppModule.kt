package com.kutluoglu.foursquaredemo.base.injection.module

import android.app.Application
import android.content.Context
import com.kutluoglu.cache.FoursquareCacheImp
import com.kutluoglu.cache.FoursquareSharedPreference
import com.kutluoglu.cache.database.db.FoursquareDemoDb
import com.kutluoglu.cache.mapper.DbUserMapper
import com.kutluoglu.cache.mapper.DbVenueMapper
import com.kutluoglu.data.DataRepository
import com.kutluoglu.data.executor.JobExecutor
import com.kutluoglu.data.mapper.UserMapper
import com.kutluoglu.data.mapper.VenueMapper
import com.kutluoglu.data.repository.Cache
import com.kutluoglu.data.repository.Remote
import com.kutluoglu.data.source.DataStoreFactory
import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.repository.FoursquareDemoRepository
import com.kutluoglu.foursquaredemo.BuildConfig
import com.kutluoglu.foursquaredemo.UiThread
import com.kutluoglu.foursquaredemo.base.injection.scopes.PerApplication
import com.kutluoglu.remote.FoursquareApi
import com.kutluoglu.remote.FoursquareRemoteImp
import com.kutluoglu.remote.ServiceFactory
import com.kutluoglu.remote.mapper.UserEntityMapper
import com.kutluoglu.remote.mapper.VenueEntityMapper
import dagger.Module
import dagger.Provides

/**
 * Created by F.K. on 2019-06-17.
 *
 */

@Module(includes = [ViewModelModule::class, MainActivityModule::class])
class AppModule {
    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor) : ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideSharedPreferences(context: Context): FoursquareSharedPreference {
        return FoursquareSharedPreference(context)
    }


    @Provides
    @PerApplication
    internal fun provideFoursquareRepository(factory: DataStoreFactory,
                                             venueMapper: VenueMapper, userMapper: UserMapper
    ) : FoursquareDemoRepository {
        return DataRepository(factory, venueMapper, userMapper)
    }

    @Provides
    @PerApplication
    internal fun provideFoursquareCache(foursquareDb: FoursquareDemoDb,
                                        fsSharedPreference: FoursquareSharedPreference,
                                        venueMapper: DbVenueMapper, userMapper: DbUserMapper
    ) : Cache {
        return FoursquareCacheImp(foursquareDb, fsSharedPreference, venueMapper, userMapper)
    }

    @Provides
    @PerApplication
    internal fun provideFoursquareService(): FoursquareApi {
        return ServiceFactory.getFoursquareApi(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideFoursquareRemote(service: FoursquareApi,
                                         venueEntityMapper: VenueEntityMapper,
                                         userEntityMapper: UserEntityMapper
    ): Remote {
        return FoursquareRemoteImp(service, venueEntityMapper, userEntityMapper)
    }

    @Provides
    @PerApplication
    internal fun provideFoursquareDatabase(context: Context): FoursquareDemoDb {
        return FoursquareDemoDb.getInsatnce(context)
    }

}