package com.example.carrot_market.core.util;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LogUtil {

    // pretty log4j2 logger
    public static void logPretty(String message) {
        log.info("--------------------------------------------------");
        log.info(message);
        log.info("--------------------------------------------------");
    }

}
