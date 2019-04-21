package com.cayuse.challenge.services;

import com.cayuse.challenge.model.WeatherInfo;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;


@Service
public class OpenWeatherServiceImpl extends BaseService implements OpenWeatherService {
    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherServiceImpl.class);

    @Value("${api.openweathermap.org.url.prefix}")
    private  String urlPrefix;
    @Value("${api.openweathermap.org.url.suffix}")
    private  String urlSuffix;
    @Value("${api.openweathermap.org.url.key}")
    private  String urlKey;

    @Override
    public WeatherInfo getTemperature(String zip) {

        String url = super.getURL(zip, urlPrefix, urlSuffix, urlKey);
        if(logger.isDebugEnabled()) logger.debug(">>>> getTemperature with template {} and  URL:{} ", Objects.nonNull(restTemplate),url);

        WeatherInfo responseWeatherInfo= restTemplate.getForEntity(url, WeatherInfo.class)
                                                                        .getBody();

        if(logger.isDebugEnabled()) logger.debug(">>>> From OpenWeather API city:{} and Temperature:{}C",
                                                responseWeatherInfo.getName(),
                                                responseWeatherInfo.getMain().getTemp());

        return responseWeatherInfo;
    }



}
