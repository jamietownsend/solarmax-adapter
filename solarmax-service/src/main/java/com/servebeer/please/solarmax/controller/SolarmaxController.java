package com.servebeer.please.solarmax.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servebeer.please.solarmax.PowerGenerationDetail;
import com.servebeer.please.solarmax.array.ArrayManager;
import com.servebeer.please.solarmax.connector.exception.SolarmaxException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping(final HttpServletRequest request, final HttpServletResponse response) throws SolarmaxException {

        return ArrayManager.ping();
    }

    /**
     * gets the power generation status from the SolarMax array
     */
    @RequestMapping(value = "/currentlyGeneratedPower", method = RequestMethod.GET)
    public int getCurrentlyGeneratedPower(final HttpServletRequest request, final HttpServletResponse response)
            throws SolarmaxException {

        return ArrayManager.getCurrentlyGeneratedPower();

    }

    /**
     * gets all relevant details from the SolarMax array
     */
    @RequestMapping(value = "/currentPowerGenerationDetails", method = RequestMethod.GET)
    public List<PowerGenerationDetail> getCurrentPowerGenerationDetails(final HttpServletRequest request,
            final HttpServletResponse response) throws SolarmaxException {

        return ArrayManager.getCurrentPowerGenerationDetails();

    }
}
