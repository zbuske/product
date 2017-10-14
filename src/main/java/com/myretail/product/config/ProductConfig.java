package com.myretail.product.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external")
public class ProductConfig {
    private String redSkyApi;
    private String redSkyExclude;

    public String getRedSkyApi() {
        return redSkyApi;
    }

    public void setRedSkyApi(String redSkyApi) {
        this.redSkyApi = redSkyApi;
    }

    public String getRedSkyExclude() {
        return redSkyExclude;
    }

    public void setRedSkyExclude(String redSkyExclude) {
        this.redSkyExclude = redSkyExclude;
    }
}
