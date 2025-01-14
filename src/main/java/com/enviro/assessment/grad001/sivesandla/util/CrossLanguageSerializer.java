package com.enviro.assessment.grad001.sivesandla.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class CrossLanguageSerializer {

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    static {
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    
    /**
     * Serializes an object to a JSON string.
     *
     * @param object The object to serialize.
     * @return A string containing the serialized JSON representation of the object.
     * @throws Exception If any serialization error occurs.
     */
    public static String toJson(Object object) throws Exception {
        return jsonMapper.writeValueAsString(object);
    }

    /**
     * Deserializes a JSON string into an object of the specified class.
     *
     * @param json The JSON string to deserialize.
     * @param clazz The class of the object to deserialize into.
     * @param <T> The type of the object.
     * @return An object of the specified class.
     * @throws Exception If any deserialization error occurs.
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        return jsonMapper.readValue(json, clazz);
    }
    
    /**
     * Serializes an object to an XML string.
     *
     * @param object The object to serialize.
     * @return A string containing the serialized XML representation of the object.
     * @throws Exception If any serialization error occurs.
     */
    public static String toXml(Object object) throws Exception {
        return xmlMapper.writeValueAsString(object);
    }
    
    /**
     * Deserializes an XML string into an object of the specified class.
     *
     * @param xml The XML string to deserialize.
     * @param clazz The class of the object to deserialize into.
     * @param <T> The type of the object.
     * @return An object of the specified class.
     * @throws Exception If any deserialization error occurs.
     */
    public static <T> T fromXml(String xml, Class<T> clazz) throws Exception {
        return xmlMapper.readValue(xml, clazz);
    }

}
