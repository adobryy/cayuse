package com.cayuse.challenge.controllers;

import com.cayuse.challenge.services.ServiceLocatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ZipRestController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ZipRestController.class);

    @Autowired
    ServiceLocatorImpl serviceLocator;

    //http://localhost:8080/api?zip=97214
    @GetMapping
    public String getZipRelatedInformation (
                            @RequestParam(name = "zip") String zipStr ) {

        logger.info("getZipRelatedInformation - zip:{}", zipStr);

        return serviceLocator.getAllAvailableInfoFromZip(zipStr);
    }


}
