package com.ssm.framework.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ssm.framework.utils.Encodes;

/**
 * 
 * java 基本序列化
 * JavaSerializer.
 *
 * @author zax
 */
public class JavaSerializer implements Serializer {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(JavaSerializer.class);

    @Override
    public String serialize(Object obj) throws IOException {
        try
        {
            logger.info("JavaSerializer:begin===serialize===");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            byte[] byteArray = bos.toByteArray();
            logger.info("JavaSerializer:end====serialize====");
            return Encodes.encodeBase64(byteArray);
        }
        catch (IOException ioe)
        {
            logger.error("error:" + ioe.getMessage());
            throw new IOException(ioe);
        }
    }

    @Override
    public Object deserialize(String str) throws IOException {
        Assert.notNull(str, "deserialize str not null");
        try
        {
            logger.info("JavaSerializer:begin===deserialize===");
            byte[] decodeBase64 = Encodes.decodeBase64(str);
            ByteArrayInputStream bis = new ByteArrayInputStream(decodeBase64);
            ObjectInputStream ois = new ObjectInputStream(bis);
            logger.info("JavaSerializer:end===deserialize===");
            return ois.readObject();
        }
        catch (IOException | ClassNotFoundException ioe)
        {
            logger.error("error:" + ioe.getMessage());
            throw new IOException(ioe);
        }
    }
}
