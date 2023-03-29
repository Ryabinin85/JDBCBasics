package model;

public class City {

    private final Integer id;
    private final String cityName;

    public City(Integer id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public Integer getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
