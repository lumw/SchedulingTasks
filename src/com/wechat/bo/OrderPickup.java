package com.wechat.bo;

import com.wechat.dao.impl.OrderDaoImpl;
import com.wechat.dao.impl.ShopDaoImpl;
import com.wechat.model.Order;
import com.wechat.model.OrderGoods;
import com.wechat.model.ShopInformTypeModel;
import com.wechat.util.DBPool;
import com.wechat.util.FinalString;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 14-4-24 16:24
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
public class OrderPickup {

    private final Logger log = Logger.getLogger(OrderPickup.class);
    Connection conn;

    public void a()  {

        try {
            conn = DBPool.getPool().getConnection();
            conn.setAutoCommit(false);

            OrderDaoImpl orderDaoImpl = new OrderDaoImpl(conn);

            /*没有要处理的订单*/
            if (orderDaoImpl.getUntreatedOrderCount() == 0) {
                log.info("没有找到要处理的订单...");
                return;
            }

            /*获取订单基本信息*/
            Order order = orderDaoImpl.getOrderInfo();

            /*获取订单里的商品信息*/
            List<OrderGoods> list = orderDaoImpl.getOrderGoodsInfo(order.getOrderID());


            ShopDaoImpl shopDaoImpl = new ShopDaoImpl(conn);
            /*获取通知方式*/
            ShopInformTypeModel shopInformTypeModel = shopDaoImpl.getShopInformType(order.getShopID());


            if (shopInformTypeModel.getIfSmsInform() == 1) {

                pushOrderBySms(FinalString.ORDERSTATUS_INFORMING);
                orderDaoImpl.updateOrderStatus(order.getOrderID(), FinalString.ORDERSTATUS_INFORMING);
            }

            if (shopInformTypeModel.getIfWechatInform() == 1) {

                psushOrderByWechat(FinalString.ORDERSTATUS_INFORMING);
                orderDaoImpl.updateOrderStatus(order.getOrderID(), FinalString.ORDERSTATUS_INFORMING);
            }
            log.info("开始处理订单: " + OrderInfoToString(order, list));
            conn.commit();
        } catch (SQLException e) {
            log.error("订单处理过程中出错 错误信息 " + e.getLocalizedMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /*通过短信发送订单信息*/
    public void pushOrderBySms(int informType){

        getContactsForPushOrder(informType);



    }

    /*通过微信发送订单信息*/
    public void psushOrderByWechat(int informType){

        getContactsForPushOrder(informType);

    }

    /*根据订单推送方式获取对应的联系人信息*/
    public void getContactsForPushOrder(int informType){

    }

    /**/
    public String OrderInfoToString(Order order, List<OrderGoods> list){

        StringBuffer sb = new StringBuffer();

        sb.append("订单ID " + order.getOrderID());
        sb.append(" 订单总价 " + order.getTotalPrice());
        sb.append(" 配送费 " + order.getFreight());
        sb.append(" 联系人姓名 " + order.getName());
        sb.append(" 送货地址 " + order.getAddress());
        sb.append(" 联系人电话 " + order.getTelphone());
        sb.append(" 其他信息 " + order.getRemark() + "\n");
        sb.append(" 订单商品信息：" + "\n");

        for(Object o : list){
            sb.append("商品名称" + ((OrderGoods)o).getGoodsName() + "商品单价" + ((OrderGoods) o).getPrice() + "商品数量" + ((OrderGoods) o).getQuantity() + "\n");
        }


        return sb.toString();
    }
}
