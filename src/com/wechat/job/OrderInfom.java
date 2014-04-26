package com.wechat.job;

import com.wechat.bo.OrderPickup;
import org.apache.log4j.Logger;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 14-4-24 0:29
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
public class OrderInfom {

    private final Logger log = Logger.getLogger(OrderInfom.class);


    public void execute() {


        OrderPickup op = new OrderPickup();
        op.a();

    }
}
