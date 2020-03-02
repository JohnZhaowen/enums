package com.example.demo.serializer;

import com.example.demo.enums.BaseEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JsonEnumDeerializer extends JsonDeserializer<BaseEnum> {

    @Override
    public BaseEnum deserialize(JsonParser p, DeserializationContext ctcx) throws IOException {
        //前端输入的值
        String inputParameter = p.getText();
        if (StringUtils.isEmpty(inputParameter)) {
            return null;
        }
        //获取对应的枚举类
        Class enumClass = getEnumClass(p);

        return getEnum(inputParameter, enumClass);
    }

    private Class getEnumClass(JsonParser p) {
        JsonStreamContext parsingContext = p.getParsingContext();
        String currentName = parsingContext.getCurrentName();//字段名
        Object currentValue = parsingContext.getCurrentValue();//前端注入的对象(ResDTO)
        Field field = ReflectionUtils.findField(currentValue.getClass(), currentName); // 通过对象和属性名获取属性的类型
        return field.getType();
    }

    private BaseEnum getEnum(String inputParameter, Class enumClass)  {
        BaseEnum baseEnum = null;
        try {
            Method valuesMethod = enumClass.getDeclaredMethod("values");

            BaseEnum[] values = (BaseEnum[]) valuesMethod.invoke(null);
            baseEnum = Arrays.stream(values).filter(e -> (e.getCode()+"").equals(inputParameter)).findFirst().orElse(null);

        } catch (Exception e) {
            System.out.println("异常");
        }

        //如果都拿不到,那就直接抛出异常了
        if (baseEnum == null) {
            throw new RuntimeException("输入参数不符合预期");
        }
        return baseEnum;

    }

}
