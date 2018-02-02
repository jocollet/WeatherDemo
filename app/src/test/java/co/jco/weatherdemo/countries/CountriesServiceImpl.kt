package co.jco.weatherdemo.countries

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit

internal class CountriesServiceImpl : CountriesService {

    override fun countryNameInCapitals(country: Country): Single<String> {
        return Single.just(country.name.toUpperCase())
    }

    override fun countCountries(countries: List<Country>): Single<Int> {
        return Single.just(countries.size)
    }

    override fun listPopulationOfEachCountry(countries: List<Country>): Observable<Long> {
        return Observable
                .fromIterable(countries)
                .map{it.population}
    }

    override fun listNameOfEachCountry(countries: List<Country>): Observable<String> {
        return Observable
                .fromIterable(countries)
                .map{it.name}
    }

    override fun listOnly3rdAnd4thCountry(countries: List<Country>): Observable<Country> {
        return Observable
                .fromIterable(countries)
                .skip(2)
                .take(2)
    }

    override fun isAllCountriesPopulationMoreThanOneMillion(countries: List<Country>): Single<Boolean> {
        return Observable
                .fromIterable(countries)
                .all { it.hasMoreThanOneMillionInhabitants() }
    }

    override fun listPopulationMoreThanOneMillion(countries: List<Country>): Observable<Country> {
        return Observable
                .fromIterable(countries)
                .filter { it.hasMoreThanOneMillionInhabitants() }
                .switchIfEmpty { Observable.empty<Country>() }
    }

    override fun listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(countriesFromNetwork: FutureTask<List<Country>>): Observable<Country> {
        return Observable.fromFuture(countriesFromNetwork, Schedulers.io())
                .flatMapIterable{ country -> country }
                .filter { it.hasMoreThanOneMillionInhabitants() }
                .timeout(1, TimeUnit.SECONDS, Observable.empty<Country>())
    }

    override fun getCurrencyUsdIfNotFound(countryName: String, countries: List<Country>): Observable<String> {
        return Observable
                .fromIterable(countries)
                .filter { it.name == countryName }
                .map{it.currency }
                .first("USD")
                .toObservable()
    }

    override fun sumPopulationOfCountries(countries: List<Country>): Observable<Long> {
        return Observable
                .fromIterable(countries)
                .map{ it.population }
                .reduce { x, sum -> x + sum }
                .toObservable()
    }

    override fun mapCountriesToNamePopulation(countries: List<Country>): Single<Map<String, Long>> {
        return Observable
                .fromIterable(countries)
                .toMap({it.name}, {it.population})
    }

    override fun sumPopulationOfCountries(countryObservable1: Observable<Country>,
                                          countryObservable2: Observable<Country>): Observable<Long> {
        return countryObservable1
                .concatWith(countryObservable2)
                .map{ it.population }
                .reduce { x, sum -> x + sum }
                .toObservable()
    }

    override fun areEmittingSameSequences(countryObservable1: Observable<Country>,
                                          countryObservable2: Observable<Country>): Single<Boolean> {
        return Observable.sequenceEqual(countryObservable1, countryObservable2)
    }

    private fun Country.hasMoreThanOneMillionInhabitants(): Boolean {
        return population > 1_000_000
    }

}
