package com.wechat.dao;

import com.wechat.model.ShopInformTypeModel;

import java.sql.SQLException;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 14-4-24 16:25
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
public interface ShopDao {

    public ShopInformTypeModel getShopInformType(String shopID) throws SQLException;
}
