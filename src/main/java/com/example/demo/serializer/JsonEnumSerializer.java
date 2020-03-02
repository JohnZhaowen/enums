package com.example.demo.serializer;

import com.example.demo.enums.BaseEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JsonEnumSerializer extends JsonSerializer<BaseEnum> {

    @Override
    public void serialize(BaseEnum baseEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {

            Map<String, Object> jsonMap = new HashMap<>();

            Method[] m = baseEnum.getClass().getDeclaredMethods();
            for (int i = 0; i < m.length; i++) {
                if (m[i].getName().startsWith("get")) {
                    String fieldName = m[i].getName().substring(3);
                    jsonMap.put(fieldName, m[i].invoke(baseEnum));
                }
            }

            serializerProvider.defaultSerializeValue(jsonMap, jsonGenerator);
        } catch (Exception e) {
            //  logger.error("JsonEnumSerializer serialize error: " + Throwables.getStackTraceAsString(e));
            System.out.println("JsonEnumSerializer serialize error: " + e);
            throw new RuntimeException(e);
        }

    }
}
