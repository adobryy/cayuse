package com.cayuse.services

import com.cayuse.challenge.model.WeatherInfo
import com.cayuse.challenge.services.OpenWeatherService
import com.cayuse.challenge.services.OpenWeatherServiceImpl
import org.springframework.web.client.RestTemplate
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("ServiceLocator Specification")
@Narrative("The Specification of behaviour for the OpenWeatherService.")
class OpenWeatherServiceSpec extends Specification{


    protected RestTemplate restTemplate = new RestTemplate()

    String prefix="http://api.openweathermap.org/data/2.5/weather?zip="
    String suffix=",us&units=metric&APPID="
    String key="4db7eb169d06cba48afe164b77f2c74a"

    def "Validate method call"() {
        given: "OpenWeather API Service"

            OpenWeatherService openWeatherService = new OpenWeatherServiceImpl()
            openWeatherService.setRestTemplate(restTemplate)
            openWeatherService.urlPrefix=prefix
            openWeatherService.urlSuffix=suffix
            openWeatherService.urlKey = key
            String zip = "97214"


        when: " entered valid zip code"

            WeatherInfo result = openWeatherService.getTemperature(zip)

        then: " result should be returned and no Exceptions thrown"
            result != null
            result.main !=null
            result.coord!=null
            result.coord.lat == '45.52'
            result.coord.lon == '-122.67'

    }
}
