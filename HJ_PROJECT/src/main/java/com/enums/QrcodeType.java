package com.enums;

public enum QrcodeType {
	
	/** 外部网页地址  0 */
	OUTADDR {
		@Override
		public String toString() {
			return "外部网页地址二维码";
		}
	}, /** 内部网页地址  1 */
	INSIDEADDR {
		@Override
		public String toString() {
			return "内部网页地址二维码";
		}
	},
	/** 设备  2 */
	DEVICE {
		@Override
		public String toString() {
			return "设备使用二维码";
		}
	},
	/** 机构 3 */
	ORG {
		@Override
		public String toString() {
			return "机构定位二维码";
		}
	},
	/** 厂商 4 */
	MANUF {
		@Override
		public String toString() {
			return "厂商使用二维码";
		}
	},
	/** 文件 5 */
	FILE {
		@Override
		public String toString() {
			return "文件使用二维码";
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
	public static QrcodeType valueOf(Integer value){
		QrcodeType[] QrcodeTypes=QrcodeType.values();
		if(value==null){
			return QrcodeTypes[0];
		}
		for (QrcodeType qrcodeType : QrcodeTypes) {
			if (value.equals(qrcodeType.getValue())) {
				return qrcodeType;
			}
		}
		return QrcodeTypes[0];
	}
}
