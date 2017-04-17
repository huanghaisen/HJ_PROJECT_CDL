package com.enums;

public enum OrgType {
	
	/** 非营业厅机构  0 */
	NONORG {
		@Override
		public String toString() {
			return "非营业厅机构";
		}
	}, /** 自建厅  1 */
	SELFORG {
		@Override
		public String toString() {
			return "自建厅";
		}
	},
	/** 合作厅  2 */
	COOORG {
		@Override
		public String toString() {
			return "合作厅";
		}
	},
	/** 特约代办点  3 */
	SPEORG {
		@Override
		public String toString() {
			return "特约代办点";
		}
	},
	/** 手机卖场  4 */
	PHONEORG {
		@Override
		public String toString() {
			return "手机卖场";
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
	public static OrgType valueOf(Integer value){
		OrgType[] OrgTypes=OrgType.values();
		if(value==null){
			return OrgTypes[0];
		}
		for (OrgType orgType : OrgTypes) {
			if (value.equals(orgType.getValue())) {
				return orgType;
			}
		}
		return OrgTypes[0];
	}
}
