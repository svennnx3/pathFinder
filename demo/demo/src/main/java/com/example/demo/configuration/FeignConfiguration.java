package com.example.demo.configuration;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;
import java.util.List;

@Configuration
public class FeignConfiguration {
    @Bean
    public Decoder feignDecoder() {
        var converter = new FeignClientMessageConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(Collections.singleton(converter));
        return new SpringDecoder(objectFactory);
    }

    private static class FeignClientMessageConverter extends MappingJackson2HttpMessageConverter {
        public FeignClientMessageConverter() {
            setSupportedMediaTypes(List.of(MediaType.TEXT_PLAIN));
        }
    }
}
