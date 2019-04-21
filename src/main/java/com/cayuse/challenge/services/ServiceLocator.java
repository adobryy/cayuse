package com.cayuse.challenge.services;

import com.cayuse.challenge.exceptions.InvalidInpuException;
import com.cayuse.challenge.exceptions.NoDataAvailableException;

public interface ServiceLocator {

    String getAllAvailableInfoFromZip(String zip) throws InvalidInpuException, NoDataAvailableException;
}
