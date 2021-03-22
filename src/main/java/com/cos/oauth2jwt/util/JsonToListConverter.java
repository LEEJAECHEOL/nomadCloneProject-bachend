package com.cos.oauth2jwt.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class JsonToListConverter implements AttributeConverter<List<Map<String, Object>>, String> {

	@Override
	public String convertToDatabaseColumn(List<Map<String, Object>> attribute) {
		try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(attribute);
        }
        catch (JsonProcessingException e)
        {
        	e.printStackTrace();
            return null;
        }
	}

	@Override
	public List<Map<String, Object>> convertToEntityAttribute(String dbData) {
		if (dbData == null) {
	           return new ArrayList<>();
        }
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(dbData, ArrayList.class);
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
        return new ArrayList<>();
	}

	
}
