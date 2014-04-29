package com.wechat.dao.impl;

import com.wechat.dao.OrderDao;
import com.wechat.model.Order;
import com.wechat.model.OrderGoods;
import com.wechat.util.FinalString;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 14-4-23 22:46
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
public class OrderDaoImpl implements OrderDao {

    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    private final Logger log = Logger.getLogger(ShopDaoImpl.class);

    public OrderDaoImpl(Connection conn) {

        this.conn = conn;
    }

    /**
     * 计算出订单沉淀表中未处理的订单数
     *
     * @return Order
     */
    public int getUntreatedOrderCount() throws SQLException {

        String sql = "select count(*) from order_info_base_t where status = " + FinalString.ORDERSTATUS_UNTREATED;
        log.debug("exec sql " + sql);

        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        int UntreatedOrderCount = 0;
        while (rs.next()) {
            UntreatedOrderCount = rs.getInt(1);
        }

        freeResource();
        return UntreatedOrderCount;

    }

    /**
     * 提取出订单表中status=0，并且创建时间最早的第一条数据
     *
     * @return Order
     */
    public Order getOrderInfo() throws SQLException {

        String sql = "select * from order_info_base_t where status = " + FinalString.ORDERSTATUS_UNTREATED + " order by createTime limit 0,1";
        log.debug("exec sql " + sql);

        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        Order order = new Order();
        while (rs.next()) {
            order.setOrderID(rs.getString("orderID"));
            order.setShopID(rs.getString("shopID"));
            order.setWeChatID(rs.getString("weChatID"));
            order.setCreateTime(rs.getTimestamp("createTime"));
            order.setTotalPrice(rs.getInt("totalPrice"));
            order.setActID(rs.getInt("actID"));
            order.setAddress(rs.getString("address"));
            order.setName(rs.getString("name"));
            order.setTelphone(rs.getString("telphone"));
            order.setPayType(rs.getInt("payType"));
            order.setFreight(rs.getFloat("freight"));
            order.setStatus(rs.getInt("status"));
            order.setRemark(rs.getString("remark"));
            order.setReserve1(rs.getString("reserve1"));
            order.setReserve2(rs.getString("reserve2"));
            order.setReserve3(rs.getString("reserve3"));
        }
        freeResource();
        return order;
    }

    /**
     * 修改订单状态
     *
     * @param orderID 订单ID
     * @param status  订单状态
     */
    public void updateOrderStatus(String orderID, int status) throws SQLException {

        String sql = "update order_info_base_t set status = "+ status + " where orderID = " + "'" + orderID.trim() + "'";
        log.debug("exec sql " + sql);

        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        freeResource();
    }

    /**
     * 获取订单中的商品信息
     *
     * @param orderID 订单ID
     * @return List
     */
    public List<OrderGoods> getOrderGoodsInfo(String orderID) throws SQLException {

        String sql = "select * from order_info_goods_t where orderID = " + "'" + orderID.trim() + "'";
        log.debug("exec sql " + sql);

        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        List<OrderGoods> list = new ArrayList<OrderGoods>();
        while (rs.next()) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderID(rs.getString("orderID"));
            orderGoods.setGoodsID(rs.getInt("goodsID"));
            orderGoods.setGoodsName(rs.getString("goodsName"));
            orderGoods.setPrice(rs.getFloat("price"));
            orderGoods.setQuantity(rs.getInt("quantity"));
            list.add(orderGoods);
        }
        freeResource();
        return list;
    }

    /**
     * 资源释放
     *
     * @param shopID 商铺ID
     * @throws SQLException
     */
    public void freeResource() throws SQLException {

        if (null != rs) {
            rs.close();
        }
        if (null != stmt) {
            stmt.close();
        }
    }

}
