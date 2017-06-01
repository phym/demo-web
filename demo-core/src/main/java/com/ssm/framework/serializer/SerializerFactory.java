package com.ssm.framework.serializer;

/**
 * 序列化对象工厂
 * 
 * SerializerFactory.
 *
 * @author zax
 */
public class SerializerFactory {
    public static Serializer getSerializer(SerializerType serializerType){
        Serializer serializer = null;
        if(serializerType.name().equals("JAVA")){
            serializer = new JavaSerializer();
        }else if(serializerType.name().equals("JSON")){
            serializer = new JSONSerializer();
        }
        return serializer;
    }
}
