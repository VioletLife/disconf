package com.baidu.disconf.core.common.constants;

/**
 * 配置类型(配置文件,配置项)
 *
 * @author liaoqiqi
 * @version 2014-5-16
 */
public enum DisConfigTypeEnum {

    FILE(0, "配置文件"), ITEM(1, "配置项");

    private int type = 0;
    private String modelName = null;

    /**
     * 构造器
     * @param type 类型值
     * @param modelName 名称
     */
    private DisConfigTypeEnum(int type, String modelName) {
        this.type = type;
        this.modelName = modelName;
    }

    /**
     * 根据类型值获取 {@link com.baidu.disconf.core.common.constants.DisConfigTypeEnum}
     * @param type 类型值
     * @return
     */
    public static DisConfigTypeEnum getByType(int type) {

        int index = 0;
        for (DisConfigTypeEnum disConfigTypeEnum : DisConfigTypeEnum.values()) {

            if (type == index) {
                return disConfigTypeEnum;
            }

            index++;
        }

        return null;
    }

    /**
     * 类型
     * @return 类型
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型值
     * @param type 类型值
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取名称
     * @return 名称
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * 设置名称
     * @param modelName 名称
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

}
