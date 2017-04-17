package com.enums;

public enum ErrorType {
	
	/** 未授权  0 */
	NONORG {
		@Override
		public String toString() {
			return "未授权";
		}
	}, /** 账号异常  1 */
	ACCOUNT  {
		@Override
		public String toString() {
			return "账号异常 ";
		}
	},
	/** 请求路径异常  2 */
	PATH {
		@Override
		public String toString() {
			return "请求服务错误";
		}
	},
	/** 其他  3 */
	OTHER {
		@Override
		public String toString() {
			return "其他异常";
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
	public static ErrorType valueOf(Integer value){
		ErrorType[] ErrorTypes=ErrorType.values();
		if(value==null){
			return ErrorTypes[0];
		}
		for (ErrorType orgType : ErrorTypes) {
			if (value.equals(orgType.getValue())) {
				return orgType;
			}
		}
		return ErrorTypes[0];
	}
}
