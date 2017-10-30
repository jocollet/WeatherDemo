package co.jco.weatherdemo.data;


public class WeatherCity {

    private String cityName;
    private float temperature;
    private String description;
    //http://openweathermap.org/weather-conditions
    private int weatherCode;

    public WeatherCity() {
    }

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

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
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
