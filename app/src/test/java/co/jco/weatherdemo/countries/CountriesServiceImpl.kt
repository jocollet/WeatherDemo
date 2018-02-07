package co.jco.weatherdemo.countries

import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.FutureTask

internal class CountriesServiceImpl : CountriesService {

    override fun countryNameInCapitals(country: Country): Single<String> {
        throw NotImplementedError()
    }

    override fun countCountries(countries: List<Country>): Single<Int> {
        throw NotImplementedError()
    }

    override fun listPopulationOfEachCountry(countries: List<Country>): Observable<Long> {
        throw NotImplementedError()
    }

    override fun listNameOfEachCountry(countries: List<Country>): Observable<String> {
        throw NotImplementedError()
    }

    override fun listOnly3rdAnd4thCountry(countries: List<Country>): Observable<Country> {
        throw NotImplementedError()
    }

    override fun isAllCountriesPopulationMoreThanOneMillion(countries: List<Country>): Single<Boolean> {
        throw NotImplementedError()
    }

    override fun listPopulationMoreThanOneMillion(countries: List<Country>): Observable<Country> {
        throw NotImplementedError()
    }

    override fun listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(countriesFromNetwork: FutureTask<List<Country>>): Observable<Country> {
        throw NotImplementedError()
    }

    override fun getCurrencyUsdIfNotFound(countryName: String, countries: List<Country>): Observable<String> {
        throw NotImplementedError()
    }

    override fun sumPopulationOfCountries(countries: List<Country>): Observable<Long> {
        throw NotImplementedError()
    }

    override fun mapCountriesToNamePopulation(countries: List<Country>): Single<Map<String, Long>> {
        throw NotImplementedError()
    }

    override fun sumPopulationOfCountries(countryObservable1: Observable<Country>,
                                          countryObservable2: Observable<Country>): Observable<Long> {
        throw NotImplementedError()
    }

    override fun areEmittingSameSequences(countryObservable1: Observable<Country>,
                                          countryObservable2: Observable<Country>): Single<Boolean> {
        throw NotImplementedError()
    }

}
