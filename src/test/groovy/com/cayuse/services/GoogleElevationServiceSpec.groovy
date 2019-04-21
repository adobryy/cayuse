package com.cayuse.services

import com.cayuse.challenge.model.WeatherInfo
import com.cayuse.challenge.services.GoogleElevationService
import com.cayuse.challenge.services.GoogleElevationServiceImpl
import com.cayuse.challenge.services.OpenWeatherService
import com.cayuse.challenge.services.OpenWeatherServiceImpl
import org.springframework.web.client.RestTemplate
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("GoogleElevationService Specification")
@Narrative("The Specification of behaviour for the GoogleElevationService.")
class GoogleElevationServiceSpec extends Specification{


    protected RestTemplate restTemplate = new RestTemplate()

    String prefix="https://maps.googleapis.com/maps/api/elevation/json?locations="
    String suffix="&key="
    String key="AIzaSyCvRZspGXjHWhQ2SfK27yzK-iux9pbCvbM"



    def "Validate method call"() {
        given: "Maps Google Elevation API Service"

            GoogleElevationService elevationService = new GoogleElevationServiceImpl()
            elevationService.setRestTemplate(restTemplate)
            elevationService.urlPrefix=prefix
            elevationService.urlSuffix=suffix
            elevationService.urlKey = key


        when: " entered valid coordinates"

            String result = elevationService.getElevation(longtitude, latitude)

        then: " result should be returned and no Exceptions thrown"
            result != null
            result == elevation

        where: "coordinates and Elevation are given "
        longtitude | latitude   || elevation
        '-122.67'  | '45.52'    || '3'        // Portland OR
        '-105.39'  | '40.12'    || '2234'     // Boulder, CO

    }
}
