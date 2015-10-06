package fr.sewatech.formation.spring.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PingController {

    private static final Logger logger = LoggerFactory.getLogger(PingController.class);

    @RequestMapping(value = "/ping")
    Answer ping() {
        logger.info("Ping called from user " + SecurityContextHolder.getContext().getAuthentication().getName());
        return new Answer();
    }

    @RequestMapping(value = "/ping", produces = "text/plain")
    String simplePing() {
        return ping().value;
    }

    @JacksonXmlRootElement(localName = "answer")
    public static class Answer {
        @JacksonXmlText
        @JsonProperty("answer")
        public String value = "OK";
    }

}
