package com.ssm.framework.serializer;

import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

public class JSONSerializer implements Serializer {

    @Override
    public String serialize(Object obj) {
            return JSON.toJSONString(obj);
    }

    @Override
    public Object deserialize(String str) {
        Assert.notNull(str, "String must be not null");
        return JSON.toJSON(str);
    }
}
