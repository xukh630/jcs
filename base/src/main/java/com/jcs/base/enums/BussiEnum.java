package com.jcs.base.enums;

/**
 * @auther xukh
 * @date 2019/8/13 20:29
 */
public enum BussiEnum {
    SCUESS(200, "成功"),
    ERROR(500, "失败");

    private Integer code;
    private String msg;

    private BussiEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static BussiEnum getEnumIndex(Integer code) {
        BussiEnum[] bussiEnums = BussiEnum.values();
        for (BussiEnum bussiEnum : bussiEnums) {
            if (bussiEnum.code.equals(code)){
                return bussiEnum;
            }
        }
        return null;
    }

}
