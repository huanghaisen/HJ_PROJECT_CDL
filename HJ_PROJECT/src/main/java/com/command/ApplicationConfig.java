package com.command;

public class ApplicationConfig {
	public static boolean SYSTEMBOOT=false;//系统启动标识
	public static final String MENUCACHE="cache_sys_menu";//缓存菜单
//	public static final String MENUCACHENEW="cache_sys_menu_new";//缓存菜单(新)
	public static final String SYSCOMPONENT="cache_sys_component";//系统组件
	public static final String ORGCACHE="cache_org";//机构缓存
	public static final String FILETYPT="file_type";//文件上传格式缓存
	public static final String TERM_ORG="term_org";//终端启动注册缓存
	public static final String GATHER_TEMP="gather_tmp";//采集数据临时缓存
	public static final String GATHER_BEACON="gather_beacon";//采集缓存标记
	public static final String GATHER_BATCH="gather_batch";//采集数据批次缓存(每天分时间12小时每小时一套缓存)
//	public static final String GATHER_BATCH
//	public static final String GATHER_INTEGRA="gather_integra";//采集数据最新一套缓存
	public static final String GATHER_SUM="gather_sum";//采集数据汇总缓存
	public static final String GATHER_TREAM="gather_tream";//返回终端信息堆栈
	public static final String FAULT_POOL="fault_pool";//故障池
	public static final String CLEANED_HIGH_GATHER="cleaned_high_gather";//最新的一批清洗过的高频数据
	public static final String GATHER_TERM_ID_MAP="gather_term_id_map";//采集过的终端id标记缓存
	public static final String AUTO_WORK="auto_work";//自动报对象
	public static final String USER_SESSION="user_session";//用户信息缓存
	public static final String BOOT_LOG="boot_log";//开机监控日记缓存
	public static final String USER_LOG="user_log";//用户登录日志
	public static final String USER_ACTION_LOG="user_action_log";//用户操作日志
	public static final String USER_SMS="user_sms";//用户短信验证码缓存
	public static final String TSTATS_BUSINESS="tstats_business";//欣网业务缓存
}
