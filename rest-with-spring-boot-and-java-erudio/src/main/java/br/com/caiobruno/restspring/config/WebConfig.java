package br.com.caiobruno.restspring.config;

import br.com.caiobruno.restspring.serializationConverter.YamlJackson2HttpMesageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    private static MediaType MEDIA_TYPE_APLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Value("${cors.originPatterns:default}")
    private String corsoriginPatterns ="";

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add( new YamlJackson2HttpMesageConverter());

    }

    @Override
    public void addCorsMappings(CorsRegistry registry){

        var allowedOrigins= corsoriginPatterns.split(",");
       registry.addMapping("/**")
               //.allowedMethods("GET", "POST",'PUT');
               .allowedMethods("*")
               .allowedOrigins(allowedOrigins)
               .allowCredentials(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //via query paran http://localhost:8081/api/person/v1?mediaType=xml

        /*  configurer.favorParameter(true)
              .parameterName("mediaType").ignoreAcceptHeader(true)
              .useRegisteredExtensionsOnly(false)
              .defaultContentType(MediaType.APPLICATION_JSON)
              .mediaType("json", MediaType.APPLICATION_JSON)
              .mediaType("xml", MediaType.APPLICATION_XML);*/

        //via  HEADER PARAM http://localhost:8081/api/person/v1

        configurer.favorParameter(true)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yamç",MEDIA_TYPE_APLICATION_YML);

    }


}
