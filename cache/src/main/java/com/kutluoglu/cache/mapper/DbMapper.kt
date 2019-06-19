package com.kutluoglu.cache.mapper

/**
 * Created by F.K. on 2019-06-17.
 *
 */

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving and putting of models from data layer
 *
 * @param <D> the model input type
 * @param <C> the cached model return type
 */

interface DbMapper<D, C> {

    fun mapToCached(type: D): C

    fun mapFromCached(type: C): D
}