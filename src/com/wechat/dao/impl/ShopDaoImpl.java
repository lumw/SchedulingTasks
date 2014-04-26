package com.wechat.dao.impl;

import com.wechat.dao.ShopDao;
import com.wechat.model.ShopInformTypeModel;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 14-4-24 16:40
 * 模    块: 接口
 * 描    述:
 * 备    注:
 * ------------------------------------------------------------
 * 修改历史:
 *
 * 序号    日期          修改人     修改原因
 *  1     14-4-24       鲁梦维     版本创建
 *
 * </pre>
 */
public class ShopDaoImpl implements ShopDao {

    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    private final Logger log = Logger.getLogger(ShopDaoImpl.class);


    public ShopDaoImpl(Connection conn){
        this.conn = conn;
    }


    /**
     * 获取该商铺的订单通知方式
     *
     * @param shopID 商铺IDßß
     * @return ShopInformTypeModel
     */
    public ShopInformTypeModel getShopInformType(String shopID) throws SQLException {

        String sql = "select IfSmsInform, IfWechatInform from shop_info_t where ShopID = " + shopID;
        log.debug("exec sql " + sql);
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        ShopInformTypeModel shopInformTypeModel = new ShopInformTypeModel();
        while (rs.next()){
            shopInformTypeModel.setIfSmsInform(rs.getInt("IfSmsInform"));
            shopInformTypeModel.setIfWechatInform(rs.getInt("IfWechatInform"));
        }

        freeResource();

        return shopInformTypeModel;
    }

    /**
     * 资源释放
     *
     * @param  shopID 商铺ID
     * @throws SQLException
     *
     */
    public void freeResource() throws SQLException{

        if( null != rs ){
            rs.close();
        }
        if( null != stmt ){
            stmt.close();
        }
    }

}
