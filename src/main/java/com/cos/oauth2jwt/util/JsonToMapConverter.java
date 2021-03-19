package com.cos.oauth2jwt.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class JsonToMapConverter implements AttributeConverter<Map<String, Object>, String> {

	@Override
	public String convertToDatabaseColumn(Map<String, Object> attribute) {
		try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(attribute);
        }catch (JsonProcessingException e){
        	e.printStackTrace();
            return null;
        }
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(String dbData) {
		if (dbData == null) {
	           return new HashMap<>();
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(dbData, HashMap.class);
        }catch (IOException e) {
        	e.printStackTrace();
        }
        return new HashMap<>();
	}

	
}
