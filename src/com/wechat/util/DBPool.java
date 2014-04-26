package com.wechat.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 13-12-9 下午8:54
 * 模    块: 接口
 * 描    述:
 * 备    注:
 * ------------------------------------------------------------
 * 修改历史:
 *
 * 序号    日期          修改人     修改原因
 *  1     13-12-9       鲁梦维     版本创建
 *
 * </pre>
 */
public class DBPool {

    private static DataSource pool;

    static {
        Context env = null;

        try {
            env = (Context) new InitialContext().lookup("java:comp/env");
            pool = (DataSource) env.lookup("jdbc/wechat");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getPool(){
        return pool;
    }
}
