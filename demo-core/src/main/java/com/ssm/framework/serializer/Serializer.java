package com.ssm.framework.serializer;

import java.io.IOException;

/**
 * 序列化对象
 * Serializer.
 *
 * @author zax
 */
public interface Serializer {
    
    public String serialize(final Object obj) throws IOException;

    public Object deserialize(final String str) throws IOException;
}
