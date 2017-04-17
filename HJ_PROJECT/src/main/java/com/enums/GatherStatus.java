package com.enums;

public enum GatherStatus {
	
	OFFLINE {
		@Override
		public String toString() {
			return "离线";
		}
	},NORMAL {
		@Override
		public String toString() {
			return "正常";
		}
	},CHANCE {
		@Override
		public String toString() {
			return "意外";
		}
	},ALARM {
		@Override
		public String toString() {
			return "告警";
		}
	},FAILURE {
		@Override
		public String toString() {
			return "故障";
		}
	},DISASTER {
		@Override
		public String toString() {
			return "灾难";
		}
	},
	/**设备不纳入统计范围，也不计入监控*/
	IGNORE {
		@Override
		public String toString() {
			return "忽略";
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
	public static GatherStatus valueOf(Integer value){
		GatherStatus[] GatherStatuses=GatherStatus.values();
		if(value==null){
			return GatherStatuses[0];
		}
		for (GatherStatus gatherStatus : GatherStatuses) {
			if (value.equals(gatherStatus.getValue())) {
				return gatherStatus;
			}
		}
		return GatherStatuses[0];
	}
}
