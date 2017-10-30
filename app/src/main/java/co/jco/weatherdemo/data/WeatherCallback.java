package co.jco.weatherdemo.data;

/**
 * Callback used by all async methods of the repository and datasources
 *
 * @param <T> the type of the expected result
 */
public interface WeatherCallback<T> {

    /**
     * Callback method called when the call had a response, you must check success to ensure data
     * are available
     *
     * @param t       the expected result type
     * @param success true if the call succeeded
     */
    void onResponse(T t, boolean success);

    /**
     * Callback method called when the call failed
     *
     * @param t a Throwable explaining why the call failed
     */
    void onFailure(Throwable t);
}
