package com.chen.upms.client.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

/**
 * <p>Tiltle: com.chen.upms.client.shiro.session </p>
 * <p>Description: 基于redis的sessionDao，缓存共享session </p>
 *
 * @Author chen
 * @data: 2017-08-20
 * @version: 1.0
 */
public class UpmsSessionDao extends CachingSessionDAO {
    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }

    @Override
    protected Serializable doCreate(Session session) {
        return null;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }
}
