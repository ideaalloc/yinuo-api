package com.yinuo.api.controller;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public abstract class ApiController {
    private static final String API_PATH = "/v1";

    public static final String AUTHENTICATE_URL = API_PATH + "/authenticate";
    public static final String APP_AUTHENTICATE_URL = API_PATH + "/connect";
    public static final String STUFF_URL = API_PATH + "/stuff";

    // Spring Boot Actuator services
    public static final String AUTOCONFIG_ENDPOINT = "/autoconfig";
    public static final String BEANS_ENDPOINT = "/beans";
    public static final String CONFIGPROPS_ENDPOINT = "/configprops";
    public static final String ENV_ENDPOINT = "/env";
    public static final String MAPPINGS_ENDPOINT = "/mappings";
    public static final String METRICS_ENDPOINT = "/metrics";
    public static final String SHUTDOWN_ENDPOINT = "/shutdown";
}
