package com.saftynetalert.saftynetalert.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ResponseMessage
{
    public static class Message{
        public String Item;
        public String Contain;

        public Message(String item, String contain) {
            Item = item;
            Contain = contain;
        }
    }

    public static void Response(Message[] messages, HttpServletResponse response) throws IOException, ServletException {
        Map<String,String> resp = new HashMap<>();
        for (Message m : messages) {
            resp.put(m.Item , m.Contain);
        }
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream() , resp);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {
        public NotFoundException() {
            super();
        }
        public NotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
        public NotFoundException(String message) {
            super(message);
        }
        public NotFoundException(Throwable cause) {
            super(cause);
        }
    }
}
