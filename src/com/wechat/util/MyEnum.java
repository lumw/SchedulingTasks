package com.wechat.util;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 14-4-24 16:45
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
public class MyEnum {


    /*订单状态*/
    public static enum OrderStatu{

        UNTREATED(0),      //0 未处理
        INFORMED(1);       //1 已通知商家

        private int nCode;

        private OrderStatu(int _nCode) {
            this.nCode = _nCode;
        }

        public String toString(){
            return String.valueOf(this.nCode);
        }
    }

    /*订单推送类型*/
    public static enum OrderPushType{
        SMS,
        WECHAT;
    }
}
