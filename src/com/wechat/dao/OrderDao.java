package com.wechat.dao;

import com.wechat.model.Order;
import com.wechat.model.OrderGoods;

import java.sql.SQLException;
import java.util.List;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 14-4-23 22:41
 * 模    块: 接口
 * 描    述:
 * 备    注:
 * ------------------------------------------------------------
 * 修改历史:
 *
 * 序号    日期          修改人     修改原因
 *  1     14-4-23       鲁梦维     版本创建
 *
 * </pre>
 */
public interface OrderDao {

    public int getUntreatedOrderCount() throws SQLException;

    public Order getOrderInfo() throws SQLException;

    public void updateOrderStatus(String orderID, int status) throws SQLException;

    public List<OrderGoods> getOrderGoodsInfo(String orderID) throws SQLException;
}
