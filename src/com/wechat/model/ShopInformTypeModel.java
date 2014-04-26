package com.wechat.model;

/**
 * <pre>
 * Insert Brief Description Here!
 * 日    期: 14-4-24 17:11
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
public class ShopInformTypeModel {

    private int ifSmsInform;        //是否短信通知
    private int ifWechatInform;     //是否微信通知

    public int getIfSmsInform() {
        return ifSmsInform;
    }

    public void setIfSmsInform(int ifSmsInform) {
        this.ifSmsInform = ifSmsInform;
    }

    public int getIfWechatInform() {
        return ifWechatInform;
    }

    public void setIfWechatInform(int ifWechatInform) {
        this.ifWechatInform = ifWechatInform;
    }
}
