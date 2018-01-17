package co.jco.weatherdemo.countries;

import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import io.reactivex.Observable;
import io.reactivex.Single;

interface CountriesService {

    /**
     * @return the name of given Country in Capital letters
     */
    Single<String> countryNameInCapitals(Country country);

    /**
     * @return the number of countries in the list
     */
    Single<Integer> countCountries(List<Country> countries);

    /**
     * @return the population of each country in the list
     */
    Observable<Long> listPopulationOfEachCountry(List<Country> countries);

    /**
     * @return the name of each country in the list
     */
    Observable<String> listNameOfEachCountry(List<Country> countries);

    /**
     * @return the 3rd and 4th countries in the list
     */
    Observable<Country> listOnly3rdAnd4thCountry(List<Country> countries);

    /**
     * @return true if the population of each country is more than 1M inhabitants
     */
    Single<Boolean> isAllCountriesPopulationMoreThanOneMillion(List<Country> countries);

    /**
     * @return each country which population is more than 1M inhabitants
     */
    Observable<Country> listPopulationMoreThanOneMillion(List<Country> countries);

    /**
     * @param countriesFromNetwork an async task which is sometimes very very slow
     * @return the filtered values from the {@link FutureTask} or an {@link Observable#empty()} if there are no values within 1 second
     */
    Observable<Country> listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(FutureTask<List<Country>> countriesFromNetwork);

    /**
     * @return the currency used by given country if found, or "USD"
     */
    Observable<String> getCurrencyUsdIfNotFound(String countryName, List<Country> countries);

    /**
     * @return the sum of all countries populations
     */
    Observable<Long> sumPopulationOfCountries(List<Country> countries);

    /**
     * @return the sum of all countries populations, from the two given Observable
     */
    Observable<Long> sumPopulationOfCountries(Observable<Country> countryObservable1,
                                              Observable<Country> countryObservable2);

    /**
     * @return a Map containing Country name -> Country population entries
     */
    Single<Map<String, Long>> mapCountriesToNamePopulation(List<Country> countries);

    /**
     * @return true if the sequences emitted by these two Observables are equal
     */
    Single<Boolean> areEmittingSameSequences(Observable<Country> countryObservable1,
                                             Observable<Country> countryObservable2);
}
