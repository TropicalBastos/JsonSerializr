package com.tropicalbastos.jsonserializr;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Objects;
import java.util.List;

public class Serializer {

    public String serialize(Object o) throws JsonSerializeException {
        try {
            Class<?> objectClass = Objects.requireNonNull(o).getClass();
            Map<String, String> jsonElements = new HashMap<>();
            for(Field field: objectClass.getDeclaredFields()){
                field.setAccessible(true);
                if(field.isAnnotationPresent(JsonField.class)){
                    String value;
                    try{
                        Object f = field.get(o);
                        if(Number.class.isAssignableFrom(f.getClass())){
                            value = f.toString();
                        } else {
                            value = (String) field.get(o);
                        }
                    }catch(ClassCastException e){
                        Object fieldObj = field.get(o);
                        if(fieldObj instanceof JsonSerializeable){
                            value = trimStringByString(serialize(fieldObj), "\"");
                        }else{
                            throw new JsonSerializeException(e.getMessage());
                        }
                    }
                    jsonElements.put(getSerializedKey(field), value);
                }
            }

            return toJsonString(jsonElements);

        }catch(IllegalAccessException e){
            throw new JsonSerializeException(e.getMessage());
        }
    }

    public <T> String serialize(List<T> list) throws JsonSerializeException {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < list.size(); i++) {
            try {
                sb.append(serialize(list.get(i)) + ((i == list.size() - 1) ? "" : ","));
            } catch(JsonSerializeException e) {
                throw e;
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String getSerializedKey(Field field){
        String annotatedValue = field.getAnnotation(JsonField.class).value();
        if(annotatedValue.isEmpty()){
            return field.getName();
        }
        return annotatedValue;
    }

    public String toJsonString(Map<String, String> map){
        String elements = map.entrySet()
           .stream()
           .map(entry -> "\"" + entry.getKey() + "\":\"" + (String) entry.getValue() + "\"")
           .collect(Collectors.joining(","));
        return "{" + elements + "}";
    }

    protected String trimStringByString(String text, String trimBy) {
        int beginIndex = 0;
        int endIndex = text.length();
    
        while (text.substring(beginIndex, endIndex).startsWith(trimBy)) {
            beginIndex += trimBy.length();
        } 
    
        while (text.substring(beginIndex, endIndex).endsWith(trimBy)) {
            endIndex -= trimBy.length();
        }
    
        return text.substring(beginIndex, endIndex);
    }

}