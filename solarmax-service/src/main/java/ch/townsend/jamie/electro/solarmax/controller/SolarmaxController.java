package ch.townsend.jamie.electro.solarmax.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.townsend.jamie.electro.solarmax.SolarmaxResponse;
import ch.townsend.jamie.electro.solarmax.SolarmaxConnector;
import ch.townsend.jamie.electro.solarmax.exception.SolarMaxException;

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
public class SolarmaxController {

    private static final Logger logger = LoggerFactory.getLogger(SolarmaxController.class);

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public SolarmaxResponse ping(final HttpServletRequest request, final HttpServletResponse response, @RequestParam("host") final String host, @RequestParam(value = "port", required = false, defaultValue = "12345") final int port) throws SolarMaxException {

        logger.debug("Trying to connect to host '" + host + "' on port '" + port + "'");

        final boolean status = SolarmaxConnector.connectionTest(host, port);
        SolarmaxResponse scs = new SolarmaxResponse(host, port, status);
        logger.debug("Connection status: '" + scs.getConnectionStatus() + "'");

        return scs;
    }

}
