package com.enums;

public enum UserType {
	
	/** 机构用户 */
	ORG {
		@Override
		public String toString() {
			return "营业厅用户";
		}
	}, /** 厂商用户 */
	MANUF {
		@Override
		public String toString() {
			return "厂商用户";
		}
	},
	/** 管理员用户 */
	ADMIN {
		@Override
		public String toString() {
			return "管理员用户";
		}
	};
	
	@Override
	public String toString(){
		return this.toString();
	}
	
	public Integer getValue(){
		return ordinal();
	}
	
	/**
	 * 获取对应枚举数据如果没有就默认第一个
	 * @param value 
	 * @return
	 */
	public static UserType valueOf(Integer value){
		UserType[] UserTypes=UserType.values();
		if(value==null){
			return UserTypes[0];
		}
		for (UserType userType : UserTypes) {
			if (value.equals(userType.getValue())) {
				return userType;
			}
		}
		return UserTypes[0];
	}
}
