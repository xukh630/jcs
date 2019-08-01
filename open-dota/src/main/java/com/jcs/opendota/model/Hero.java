package com.jcs.opendota.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Hero implements Serializable {
    /**
     * 主键标识
     */
    private String uuid;

    /**
     * 英雄中文名称
     */
    private String heroCnName;

    /**
     * 英雄英文名称
     */
    private String heroEnName;

    /**
     * 英雄类型 1.力量 2.敏捷 3.智力
     */
    private Integer heroType;

    /**
     * 力量
     */
    private BigDecimal power;

    /**
     * 敏捷
     */
    private BigDecimal agile;

    /**
     * 智力
     */
    private BigDecimal intellectual;

    /**
     * 力量成长
     */
    private BigDecimal powerGrow;

    /**
     * 敏捷成长
     */
    private BigDecimal agileGrow;

    /**
     * 智力成长
     */
    private BigDecimal intellectualGrow;

    /**
     * 移动速度
     */
    private Integer moveingSpeed;

    /**
     * 攻击上限
     */
    private Integer attackIntervalCap;

    /**
     * 攻击下限
     */
    private Integer attackIntervalLower;

    /**
     * 护甲
     */
    private Integer armor;

    /**
     * 物理抗性
     */
    private Integer materialResistance;

    /**
     * 魔法抗性
     */
    private Integer magicResistance;

    /**
     * 血量
     */
    private Integer bloodVolume;

    /**
     * 蓝量
     */
    private Integer magicVolume;

    private static final long serialVersionUID = 1L;


}