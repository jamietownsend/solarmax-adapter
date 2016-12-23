package com.servebeer.please.solarmax.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
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
class SolarmaxStubController {

    private static final Logger logger = LoggerFactory.getLogger(SolarmaxStubController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String post(@RequestBody String input) {

        logger.info("Received: '" + input + "'");

        return "ok";
    }

}
