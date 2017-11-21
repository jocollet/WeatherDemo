package co.jco.weatherdemo.data;

/**
 * Data class representing the current weather of a city
 */
public class WeatherCity {

    private String cityName;
    /**
     * defined in °C by convention
     */
    private double temperature;
    /**
     * weather description
     */
    private String description;
    /**
     * weather code, see http://openweathermap.org/weather-conditions
     */
    private int weatherCode;

    /**
     * Instanciates a new #WeatherCity
     */
    public WeatherCity() {
    }

    /**
     * Instanciates a new #WeatherCity
     *
     * @param cityName    city name
     * @param temperature current temperature in °C
     * @param description current weather description
     * @param weatherCode current weather code, see http://openweathermap.org/weather-conditions
     */
    public WeatherCity(String cityName, float temperature, String description, int weatherCode) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.description = description;
        this.weatherCode = weatherCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }
}
