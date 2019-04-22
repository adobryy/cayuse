package com.cayuse.challenge.controllers;

import com.cayuse.challenge.exceptions.InvalidInputException;
import com.cayuse.challenge.exceptions.NoDataAvailableException;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@NoArgsConstructor
public abstract class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(InvalidInputException.class)
    @ResponseBody
    @ResponseStatus(NOT_ACCEPTABLE)
    public String handleInvalidInpuException(InvalidInputException ex) {
        return logStackTraceAndReturnMessage(ex);
    }

    @ExceptionHandler(NoDataAvailableException.class)
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    public String handleNoDataAvailableException(NoDataAvailableException ex) {

        return logStackTraceAndReturnMessage(ex);
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public String handleGeneralException(Exception ex) {

        return logStackTraceAndReturnMessage(ex);
    }

    private String logStackTraceAndReturnMessage(Exception ex) {
        logger.error("Exception caught in controller: {}", ExceptionUtils.getStackTrace(ex));
        return ex.getMessage();
    }

}
