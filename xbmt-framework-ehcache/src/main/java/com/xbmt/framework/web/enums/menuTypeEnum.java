package com.xbmt.framework.web.enums;

import java.util.HashMap;

import javax.faces.model.SelectItem;

import com.xbmt.common.enums.EnumUtils;
import com.xbmt.common.enums.base.CoreBaseEnum;
/**
 * 自定义菜单事件推送枚举
 * @author mengxuejiao
 *
 */
public enum menuTypeEnum implements CoreBaseEnum<menuTypeEnum, String> {
	CLICK_EVENT("click", "点击菜单拉取消息时的事件推送"), VIEW_EVENT("view", "点击菜单跳转链接时的事件推送"), SCANCODE_PUSH("scancode_push", "扫码推事件的事件推送"),
	SCANCODE_WAITMSG("scancode_waitmsg", "扫码推事件且弹出“消息接收中”提示框的事件推送"), PIC_SYSPHOTO("pic_sysphoto", "弹出系统拍照发图的事件推送"), PIC_PHOTO_OR_ALBUM("pic_photo_or_album", "弹出拍照或者相册发图的事件推送"),
	PIC_WEIXIN("pic_weixin", "弹出微信相册发图器的事件推送"), LOCATION_SELECT("location_select", " 弹出地理位置选择器的事件推送");

	/** 真实值 **/
	private String value;
	/** 显示值 **/
	private String alias;
	
	/**
	 * 构造函数<p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private menuTypeEnum(String value, String alias) {
		this.value = value;
		this.alias = alias;
	}

	
	@Override
	public String getValue() {
		return value;
	} 

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public Enum<menuTypeEnum>[] getEnums() {
		return values();
	}

	@Override
	public Enum<menuTypeEnum> getEnum(String value) {
		return (menuTypeEnum) EnumUtils.getValueEnum(this, value);
	}

	@Override
	public SelectItem[] getEnumSelectItem() {
		return EnumUtils.getSelectItemList(this);
	}

	@Override
	public HashMap<String, String> getHashMap() {
		return EnumUtils.getHashMapFromEnums(this);
	}

}
