package com.cos.oauth2jwt.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class JsonToListStringConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
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
	public List<String> convertToEntityAttribute(String dbData) {
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
