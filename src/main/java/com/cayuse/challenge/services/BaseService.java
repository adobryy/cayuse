package com.cayuse.challenge.services;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class BaseService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleElevationServiceImpl.class);

    @Autowired
    protected @Setter RestTemplate restTemplate;


    //-------------------------------
    // Util methods
    //-------------------------------

    protected String getURL(final String criteria, // final String longitude, final String latitude,
                            final String urlPrefix, final String urlSuffix,
                            final String urlKey) {


        if(logger.isDebugEnabled()) logger.debug(">>>> From properties urlPrefix:{} and urlSuffix:{}C",
                                                urlPrefix, urlSuffix);

        StringBuffer bf = new  StringBuffer(300);
        String targetURL = bf.append(urlPrefix)
                             .append(criteria)
                             .append(urlSuffix)
                             .append(urlKey)
                             .toString();

        if(logger.isDebugEnabled()) logger.debug(">>>> targetURL={}", targetURL);

        return targetURL;
    }


}
