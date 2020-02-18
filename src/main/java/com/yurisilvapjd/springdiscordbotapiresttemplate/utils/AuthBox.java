package com.yurisilvapjd.springdiscordbotapiresttemplate.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Getter
@Setter
@Service
public class AuthBox {

    @Value("${JDA.TOKEN}")
    private String JDA_TOKEN;
    @Value("${HAPPIDEV.APIKEY}")
    private String HAPPIDEV_APIKEY;

}

