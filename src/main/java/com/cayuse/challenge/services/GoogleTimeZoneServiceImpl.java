package com.cayuse.challenge.services;

import com.cayuse.challenge.model.TimezoneInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleTimeZoneServiceImpl extends BaseService implements GoogleTimeZoneService {

    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Value("${maps.googleapis.com.maps.api.timezone.prefix}")
    private  String urlPrefix;
    @Value("${maps.googleapis.com.maps.api.timezone.suffix}")
    private  String urlSuffix;
    @Value("${maps.googleapis.com.maps.api.key}")
    private  String urlKey;



    @Override
    public String getTimeZone(String longitude, String latitude) {

        String criteria = (new StringBuffer(40).append(latitude).append(",").append(longitude)
                            ).toString();


        TimezoneInfo responseTimezoneInfo =
                    restTemplate.getForEntity(getURL(criteria, urlPrefix, urlSuffix, urlKey), TimezoneInfo.class)
                            .getBody();

        if(logger.isDebugEnabled()) logger.debug(">>>> From googleapi API Timezone:{}",
                                                responseTimezoneInfo.getTimeZoneName());

        return responseTimezoneInfo.getTimeZoneName();
    }

}
