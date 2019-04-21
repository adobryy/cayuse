package com.cayuse.challenge.services;

import com.cayuse.challenge.model.WeatherInfo;

public interface OpenWeatherService {

    WeatherInfo getTemperature (String zip);
}
