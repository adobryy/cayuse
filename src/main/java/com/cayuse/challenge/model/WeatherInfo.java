package com.cayuse.challenge.model;

import lombok.Data;


@Data
public class WeatherInfo {

    private  String name;
    private  Temperature  main;
    private  Location coord;

    @Data
    public static class Temperature {
        private String temp;
        private String temp_min;
        private String temp_max;
    }

    @Data
    public static class Location {
        private String lat;
        private String lon;
    }

}
