package com.cayuse.services

import com.cayuse.challenge.services.GoogleTimeZoneService
import com.cayuse.challenge.services.GoogleTimeZoneServiceImpl
import org.springframework.web.client.RestTemplate
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("ServiceLocator Specification")
@Narrative("The Specification of behaviour for OpenWeatherService.")
class GoogleTimeZoneServiceSpec extends Specification{

    protected RestTemplate restTemplate = new RestTemplate()

    String prefix="https://maps.googleapis.com/maps/api/timezone/json?location="
    String suffix="&timestamp=1458000000&key="
    String key="AIzaSyCvRZspGXjHWhQ2SfK27yzK-iux9pbCvbM"


    def "Validate method call"() {
        given: "Maps Google Elevation API Service"

        GoogleTimeZoneService timeZoneService = new GoogleTimeZoneServiceImpl()
        timeZoneService.setRestTemplate(restTemplate)
        timeZoneService.urlPrefix=prefix
        timeZoneService.urlSuffix=suffix
        timeZoneService.urlKey = key


        when: " entered valid zip coordinates"

        String result = timeZoneService.getTimeZone(longtitude, latitude)

        then: " result should be returned and no Exceptions thrown"
        result != null
        result == timeZone

        where: "coordinates and Elevation are given "
        longtitude | latitude   || timeZone
        '-122.67'  | '45.52'    || 'Pacific Daylight Time'        // Portland OR
        '-105.39'  | '40.12'    || 'Mountain Daylight Time'     // Boulder, CO

    }

}
