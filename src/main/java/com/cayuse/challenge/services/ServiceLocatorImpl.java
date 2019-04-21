package com.cayuse.challenge.services;


import com.cayuse.challenge.exceptions.InvalidInpuException;
import com.cayuse.challenge.exceptions.NoDataAvailableException;
import com.cayuse.challenge.model.WeatherInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ServiceLocatorImpl implements ServiceLocator {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLocatorImpl.class);

    @Autowired
    OpenWeatherService openWeatherService;
    @Autowired
    GoogleElevationService googleElevationService;
    @Autowired
    GoogleTimeZoneService googleTimeZoneService;


    @Override
    public String getAllAvailableInfoFromZip(String zip)  throws InvalidInpuException, NoDataAvailableException {

        if(!validateInput(zip)) {
            throw new InvalidInpuException(String.format("Invalid zip code:%s", zip));
        }
        try {
                WeatherInfo weatherInfo = openWeatherService.getTemperature(zip);

                if(logger.isDebugEnabled()) logger.debug(">>>> WeatherInfo From openWeatherService:{}", weatherInfo.toString());
                //  Validation
                if(!validWeatherServiceResult(weatherInfo))
                    throw new NoDataAvailableException(String.format("No data is available for zip code:%s", zip));

                String longitude = weatherInfo.getCoord().getLon();
                String latitude = weatherInfo.getCoord().getLat();

                String timeZoneStr = googleTimeZoneService.getTimeZone(longitude, latitude);
                String elevationStr = googleElevationService.getElevation(longitude, latitude);

                return createResult(weatherInfo, timeZoneStr, elevationStr);

        } catch (NoDataAvailableException e){
            throw e;
        }
        catch (Exception ex) {
            logger.error("Exception caught getAllAvailableInfoFromZip: {}", ExceptionUtils.getStackTrace(ex));
            throw new NoDataAvailableException(String.format("No data is available for zip code:%s", zip), ex.getCause());
        }
    }

    //-------------------------------
    // Util methods
    //-------------------------------
    protected boolean validWeatherServiceResult(WeatherInfo weatherInfo) {

        return (Objects.nonNull(weatherInfo.getCoord().getLon()) && Objects.nonNull(weatherInfo.getCoord().getLat()));

    }

    protected boolean validateInput(String zip) {
        return StringUtils.isNotEmpty(zip) && zip.length()==5 && zip.chars().allMatch(Character::isDigit);
    }

    // It should look like:
    // "At the location $CITY_NAME, the temperature is $TEMPERATURE, the timezone is $TIMEZONE, and the elevation is $ELEVATION"
    protected String createResult(WeatherInfo weatherInfo, String timeZoneStr, String elevationStr) {
        StringBuffer resultBf = new StringBuffer(150);
        resultBf.append("At the location ")
                .append(weatherInfo.getName())
                .append(", the temperature is ")
                .append(weatherInfo.getMain().getTemp()).append(" C, the timezone is ").append(timeZoneStr)
                .append(", and the elevation is ").append(elevationStr).append(" meter(s).");
        return resultBf.toString();
    }

}
