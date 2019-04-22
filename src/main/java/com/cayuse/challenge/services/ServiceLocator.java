package com.cayuse.challenge.services;

import com.cayuse.challenge.exceptions.InvalidInputException;
import com.cayuse.challenge.exceptions.NoDataAvailableException;

public interface ServiceLocator {

    String getAllAvailableInfoFromZip(String zip) throws InvalidInputException, NoDataAvailableException;
}
