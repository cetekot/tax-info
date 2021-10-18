package com.cetekot.service.tax.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright:    Copyright (c) 2020-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Configuration
@EnableFeignClients( basePackages = { "com.cetekot.service.auth" } )
public class CloudConfig {

}
