package com.cayuse.challenge.services;

import com.cayuse.challenge.model.ElevationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleElevationServiceImpl extends BaseService implements GoogleElevationService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleElevationServiceImpl.class);

    @Value("${maps.googleapis.com.maps.api.elevation.prefix}")
    private  String urlPrefix;
    @Value("${maps.googleapis.com.maps.api.elevation.suffix}")
    private  String urlSuffix;
    @Value("${maps.googleapis.com.maps.api.key}")
    private  String urlKey;



    @Override
    public String getElevation( String longitude, String latitude) {

        String criteria = (new StringBuffer(40).append(latitude).append(",").append(longitude)
                            ).toString();

        ElevationInfo responseElevationInfo =
                            restTemplate.getForEntity(getURL(criteria, urlPrefix, urlSuffix, urlKey), ElevationInfo.class)
                            .getBody();

        if(logger.isDebugEnabled()) logger.debug(">>>> From googleapi API Elevation:{}",
                                                responseElevationInfo.getResults().get(0).getElevation());

        return responseElevationInfo.getResults().get(0).getElevation().toString();
    }


}
