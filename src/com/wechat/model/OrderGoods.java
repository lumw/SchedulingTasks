package com.wechat.model;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 2014/3/25 20:36
 * 模    块: 接口
 * 描    述: 订单中的商品信息
 * 备    注:
 * ------------------------------------------------------------
 * 修改历史:
 *
 * 序号    日期          修改人     修改原因
 *  1     2014/3/25       鲁梦维     版本创建
 *
 * </pre>
 */
public class OrderGoods {

    private String orderID;             /*订单ID*/
    private int goodsID;                /*商品类型编码*/
    private String goodsName;           /*商品名称*/
    private float price;                /*商品价格*/
    private int quantity;               /*商品数量*/

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(int goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
