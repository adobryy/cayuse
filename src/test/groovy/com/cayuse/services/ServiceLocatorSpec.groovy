package com.cayuse.challenge.services

import com.cayuse.challenge.exceptions.NoDataAvailableException
import com.cayuse.challenge.model.WeatherInfo
import spock.lang.Narrative;
import spock.lang.Specification
import spock.lang.Title


@Title("ServiceLocatorSpec Specification")
@Narrative("The Specification of behaviour for the ServiceLocator.")
class ServiceLocatorSpec extends Specification {



    def "Validate input zip code"() {
        given:
            ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl()

        when: "validating  entered zip codes"

            boolean returnedValue = serviceLocator.validateInput(input)

        then: " validation performed correctly"

            assert returnedValue == result

        where: "zip parameters and expected results"
            input      || result
            "abcx"     ||  false
            "97214"    ||  true
            "111"      ||  false

    }


    def "Validate request for zip code without data"() {
        given:
            ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl()

        when: " entered valid zip code"

            serviceLocator.getAllAvailableInfoFromZip("11111")

        then: " exception should be thrown"
            thrown(NoDataAvailableException)
    }

    def "Validation of WeatherInfo"() {
        given:
            ServiceLocatorImpl serviceLocator = new ServiceLocatorImpl()
            WeatherInfo weatherInfo = getWeatherInfo()
        //print(weatherInfo.toString())
        when: "validating  returned WeatherInfo"

            boolean returnedValue = serviceLocator.validWeatherServiceResult(weatherInfo)

        then: " validation performed correctly"

            assert returnedValue == true


    }


    WeatherInfo getWeatherInfo() {

        WeatherInfo weatherInfo = new WeatherInfo()
        String lat = '45.52'
        String lon = '-122.67'

        weatherInfo.coord= new WeatherInfo.Location()
        weatherInfo.coord.lon = lon
        weatherInfo.coord.lat= lat

        return weatherInfo
    }
}
