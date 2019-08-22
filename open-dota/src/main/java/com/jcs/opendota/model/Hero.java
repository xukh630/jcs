package com.jcs.opendota.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHeroCnName() {
        return heroCnName;
    }

    public void setHeroCnName(String heroCnName) {
        this.heroCnName = heroCnName;
    }

    public String getHeroEnName() {
        return heroEnName;
    }

    public void setHeroEnName(String heroEnName) {
        this.heroEnName = heroEnName;
    }

    public Integer getHeroType() {
        return heroType;
    }

    public void setHeroType(Integer heroType) {
        this.heroType = heroType;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public BigDecimal getAgile() {
        return agile;
    }

    public void setAgile(BigDecimal agile) {
        this.agile = agile;
    }

    public BigDecimal getIntellectual() {
        return intellectual;
    }

    public void setIntellectual(BigDecimal intellectual) {
        this.intellectual = intellectual;
    }

    public BigDecimal getPowerGrow() {
        return powerGrow;
    }

    public void setPowerGrow(BigDecimal powerGrow) {
        this.powerGrow = powerGrow;
    }

    public BigDecimal getAgileGrow() {
        return agileGrow;
    }

    public void setAgileGrow(BigDecimal agileGrow) {
        this.agileGrow = agileGrow;
    }

    public BigDecimal getIntellectualGrow() {
        return intellectualGrow;
    }

    public void setIntellectualGrow(BigDecimal intellectualGrow) {
        this.intellectualGrow = intellectualGrow;
    }

    public Integer getMoveingSpeed() {
        return moveingSpeed;
    }

    public void setMoveingSpeed(Integer moveingSpeed) {
        this.moveingSpeed = moveingSpeed;
    }

    public Integer getAttackIntervalCap() {
        return attackIntervalCap;
    }

    public void setAttackIntervalCap(Integer attackIntervalCap) {
        this.attackIntervalCap = attackIntervalCap;
    }

    public Integer getAttackIntervalLower() {
        return attackIntervalLower;
    }

    public void setAttackIntervalLower(Integer attackIntervalLower) {
        this.attackIntervalLower = attackIntervalLower;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getMaterialResistance() {
        return materialResistance;
    }

    public void setMaterialResistance(Integer materialResistance) {
        this.materialResistance = materialResistance;
    }

    public Integer getMagicResistance() {
        return magicResistance;
    }

    public void setMagicResistance(Integer magicResistance) {
        this.magicResistance = magicResistance;
    }

    public Integer getBloodVolume() {
        return bloodVolume;
    }

    public void setBloodVolume(Integer bloodVolume) {
        this.bloodVolume = bloodVolume;
    }

    public Integer getMagicVolume() {
        return magicVolume;
    }

    public void setMagicVolume(Integer magicVolume) {
        this.magicVolume = magicVolume;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}