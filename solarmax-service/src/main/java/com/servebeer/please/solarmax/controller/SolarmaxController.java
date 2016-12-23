package com.servebeer.please.solarmax.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servebeer.please.solarmax.array.ArrayManager;
import com.servebeer.please.solarmax.connector.exception.SolarmaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller to talk to a SolarMax MT Power Inverter
 *
 * @author Jamie Townsend
 * @since 1.0, 2016.
 */
@RestController
@RequestMapping(value = "/solarmaxArray")
class SolarmaxController {

    private static final Logger logger = LoggerFactory.getLogger(SolarmaxController.class);

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping(final HttpServletRequest request, final HttpServletResponse response) throws SolarmaxException {

        return ArrayManager.ping();
    }

    /**
     * gets the power generation status from the SolarMax device addressable under host:port
     */
    @RequestMapping(value = "/currentlyGeneratedPower", method = RequestMethod.GET)
    public int getCurrentlyGeneratedPower(final HttpServletRequest request, final HttpServletResponse response) throws SolarmaxException {

        return ArrayManager.getCurrentlyGeneratedPower();

    }
}
