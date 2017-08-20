package com.chen.upms.client.shiro.util;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>Tiltle: com.chen.upms.client.shiro.util </p>
 * <p>Description: session序列化工具 </p>
 *
 * @Author chen
 * @data: 2017-08-20
 * @version: 1.0
 */
public class SerializableUtil {
    public static String serialize(Session session) {
        if (null == session) {
            return null;
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(session);
            return Base64.encodeToString(bos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        }
    }

    public static Session deserialize(String sessionStr) {
        if (StringUtils.isBlank(sessionStr)) {
            return null;
        }
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Session) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }
}
