
/**
 * 地图控件使用
 */
$(function(){
	var kxdmapinfo={};
	kxdmapinfo.cityMap = {
		    "北京市": "110100",
		    "天津市": "120100",
		    "上海市": "310100",
		    "重庆市": "500100",
		    "崇明县": "310200",
		    "湖北省直辖县市": "429000",
		    "铜仁市": "522200",
		    "毕节市": "522400",
		    "石家庄市": "130100",
		    "唐山市": "130200",
		    "秦皇岛市": "130300",
		    "邯郸市": "130400",
		    "邢台市": "130500",
		    "保定市": "130600",
		    "张家口市": "130700",
		    "承德市": "130800",
		    "沧州市": "130900",
		    "廊坊市": "131000",
		    "衡水市": "131100",
		    "太原市": "140100",
		    "大同市": "140200",
		    "阳泉市": "140300",
		    "长治市": "140400",
		    "晋城市": "140500",
		    "朔州市": "140600",
		    "晋中市": "140700",
		    "运城市": "140800",
		    "忻州市": "140900",
		    "临汾市": "141000",
		    "吕梁市": "141100",
		    "呼和浩特市": "150100",
		    "包头市": "150200",
		    "乌海市": "150300",
		    "赤峰市": "150400",
		    "通辽市": "150500",
		    "鄂尔多斯市": "150600",
		    "呼伦贝尔市": "150700",
		    "巴彦淖尔市": "150800",
		    "乌兰察布市": "150900",
		    "兴安盟": "152200",
		    "锡林郭勒盟": "152500",
		    "阿拉善盟": "152900",
		    "沈阳市": "210100",
		    "大连市": "210200",
		    "鞍山市": "210300",
		    "抚顺市": "210400",
		    "本溪市": "210500",
		    "丹东市": "210600",
		    "锦州市": "210700",
		    "营口市": "210800",
		    "阜新市": "210900",
		    "辽阳市": "211000",
		    "盘锦市": "211100",
		    "铁岭市": "211200",
		    "朝阳市": "211300",
		    "葫芦岛市": "211400",
		    "长春市": "220100",
		    "吉林市": "220200",
		    "四平市": "220300",
		    "辽源市": "220400",
		    "通化市": "220500",
		    "白山市": "220600",
		    "松原市": "220700",
		    "白城市": "220800",
		    "延边朝鲜族自治州": "222400",
		    "哈尔滨市": "230100",
		    "齐齐哈尔市": "230200",
		    "鸡西市": "230300",
		    "鹤岗市": "230400",
		    "双鸭山市": "230500",
		    "大庆市": "230600",
		    "伊春市": "230700",
		    "佳木斯市": "230800",
		    "七台河市": "230900",
		    "牡丹江市": "231000",
		    "黑河市": "231100",
		    "绥化市": "231200",
		    "大兴安岭地区": "232700",
		    "南京市": "320100",
		    "无锡市": "320200",
		    "徐州市": "320300",
		    "常州市": "320400",
		    "苏州市": "320500",
		    "南通市": "320600",
		    "连云港市": "320700",
		    "淮安市": "320800",
		    "盐城市": "320900",
		    "扬州市": "321000",
		    "镇江市": "321100",
		    "泰州市": "321200",
		    "宿迁市": "321300",
		    "杭州市": "330100",
		    "宁波市": "330200",
		    "温州市": "330300",
		    "嘉兴市": "330400",
		    "湖州市": "330500",
		    "绍兴市": "330600",
		    "金华市": "330700",
		    "衢州市": "330800",
		    "舟山市": "330900",
		    "台州市": "331000",
		    "丽水市": "331100",
		    "合肥市": "340100",
		    "芜湖市": "340200",
		    "蚌埠市": "340300",
		    "淮南市": "340400",
		    "马鞍山市": "340500",
		    "淮北市": "340600",
		    "铜陵市": "340700",
		    "安庆市": "340800",
		    "黄山市": "341000",
		    "滁州市": "341100",
		    "阜阳市": "341200",
		    "宿州市": "341300",
		    "六安市": "341500",
		    "亳州市": "341600",
		    "池州市": "341700",
		    "宣城市": "341800",
		    "福州市": "350100",
		    "厦门市": "350200",
		    "莆田市": "350300",
		    "三明市": "350400",
		    "泉州市": "350500",
		    "漳州市": "350600",
		    "南平市": "350700",
		    "龙岩市": "350800",
		    "宁德市": "350900",
		    "南昌市": "360100",
		    "景德镇市": "360200",
		    "萍乡市": "360300",
		    "九江市": "360400",
		    "新余市": "360500",
		    "鹰潭市": "360600",
		    "赣州市": "360700",
		    "吉安市": "360800",
		    "宜春市": "360900",
		    "抚州市": "361000",
		    "上饶市": "361100",
		    "济南市": "370100",
		    "青岛市": "370200",
		    "淄博市": "370300",
		    "枣庄市": "370400",
		    "东营市": "370500",
		    "烟台市": "370600",
		    "潍坊市": "370700",
		    "济宁市": "370800",
		    "泰安市": "370900",
		    "威海市": "371000",
		    "日照市": "371100",
		    "莱芜市": "371200",
		    "临沂市": "371300",
		    "德州市": "371400",
		    "聊城市": "371500",
		    "滨州市": "371600",
		    "菏泽市": "371700",
		    "郑州市": "410100",
		    "开封市": "410200",
		    "洛阳市": "410300",
		    "平顶山市": "410400",
		    "安阳市": "410500",
		    "鹤壁市": "410600",
		    "新乡市": "410700",
		    "焦作市": "410800",
		    "濮阳市": "410900",
		    "许昌市": "411000",
		    "漯河市": "411100",
		    "三门峡市": "411200",
		    "南阳市": "411300",
		    "商丘市": "411400",
		    "信阳市": "411500",
		    "周口市": "411600",
		    "驻马店市": "411700",
		    "省直辖县级行政区划": "469000",
		    "武汉市": "420100",
		    "黄石市": "420200",
		    "十堰市": "420300",
		    "宜昌市": "420500",
		    "襄樊市": "420600",
		    "鄂州市": "420700",
		    "荆门市": "420800",
		    "孝感市": "420900",
		    "荆州市": "421000",
		    "黄冈市": "421100",
		    "咸宁市": "421200",
		    "随州市": "421300",
		    "恩施土家族苗族自治州": "422800",
		    "长沙市": "430100",
		    "株洲市": "430200",
		    "湘潭市": "430300",
		    "衡阳市": "430400",
		    "邵阳市": "430500",
		    "岳阳市": "430600",
		    "常德市": "430700",
		    "张家界市": "430800",
		    "益阳市": "430900",
		    "郴州市": "431000",
		    "永州市": "431100",
		    "怀化市": "431200",
		    "娄底市": "431300",
		    "湘西土家族苗族自治州": "433100",
		    "广州市": "440100",
		    "韶关市": "440200",
		    "深圳市": "440300",
		    "珠海市": "440400",
		    "汕头市": "440500",
		    "佛山市": "440600",
		    "江门市": "440700",
		    "湛江市": "440800",
		    "茂名市": "440900",
		    "肇庆市": "441200",
		    "惠州市": "441300",
		    "梅州市": "441400",
		    "汕尾市": "441500",
		    "河源市": "441600",
		    "阳江市": "441700",
		    "清远市": "441800",
		    "东莞市": "441900",
		    "中山市": "442000",
		    "潮州市": "445100",
		    "揭阳市": "445200",
		    "云浮市": "445300",
		    "南宁市": "450100",
		    "柳州市": "450200",
		    "桂林市": "450300",
		    "梧州市": "450400",
		    "北海市": "450500",
		    "防城港市": "450600",
		    "钦州市": "450700",
		    "贵港市": "450800",
		    "玉林市": "450900",
		    "百色市": "451000",
		    "贺州市": "451100",
		    "河池市": "451200",
		    "来宾市": "451300",
		    "崇左市": "451400",
		    "海口市": "460100",
		    "三亚市": "460200",
		    "三沙市": "460300",
		    "成都市": "510100",
		    "自贡市": "510300",
		    "攀枝花市": "510400",
		    "泸州市": "510500",
		    "德阳市": "510600",
		    "绵阳市": "510700",
		    "广元市": "510800",
		    "遂宁市": "510900",
		    "内江市": "511000",
		    "乐山市": "511100",
		    "南充市": "511300",
		    "眉山市": "511400",
		    "宜宾市": "511500",
		    "广安市": "511600",
		    "达州市": "511700",
		    "雅安市": "511800",
		    "巴中市": "511900",
		    "资阳市": "512000",
		    "阿坝藏族羌族自治州": "513200",
		    "甘孜藏族自治州": "513300",
		    "凉山彝族自治州": "513400",
		    "贵阳市": "520100",
		    "六盘水市": "520200",
		    "遵义市": "520300",
		    "安顺市": "520400",
		    "黔西南布依族苗族自治州": "522300",
		    "黔东南苗族侗族自治州": "522600",
		    "黔南布依族苗族自治州": "522700",
		    "昆明市": "530100",
		    "曲靖市": "530300",
		    "玉溪市": "530400",
		    "保山市": "530500",
		    "昭通市": "530600",
		    "丽江市": "530700",
		    "普洱市": "530800",
		    "临沧市": "530900",
		    "楚雄彝族自治州": "532300",
		    "红河哈尼族彝族自治州": "532500",
		    "文山壮族苗族自治州": "532600",
		    "西双版纳傣族自治州": "532800",
		    "大理白族自治州": "532900",
		    "德宏傣族景颇族自治州": "533100",
		    "怒江傈僳族自治州": "533300",
		    "迪庆藏族自治州": "533400",
		    "拉萨市": "540100",
		    "昌都地区": "542100",
		    "山南地区": "542200",
		    "日喀则地区": "542300",
		    "那曲地区": "542400",
		    "阿里地区": "542500",
		    "林芝地区": "542600",
		    "西安市": "610100",
		    "铜川市": "610200",
		    "宝鸡市": "610300",
		    "咸阳市": "610400",
		    "渭南市": "610500",
		    "延安市": "610600",
		    "汉中市": "610700",
		    "榆林市": "610800",
		    "安康市": "610900",
		    "商洛市": "611000",
		    "兰州市": "620100",
		    "嘉峪关市": "620200",
		    "金昌市": "620300",
		    "白银市": "620400",
		    "天水市": "620500",
		    "武威市": "620600",
		    "张掖市": "620700",
		    "平凉市": "620800",
		    "酒泉市": "620900",
		    "庆阳市": "621000",
		    "定西市": "621100",
		    "陇南市": "621200",
		    "临夏回族自治州": "622900",
		    "甘南藏族自治州": "623000",
		    "西宁市": "630100",
		    "海东市": "632100",
		    "海北藏族自治州": "632200",
		    "黄南藏族自治州": "632300",
		    "海南藏族自治州": "632500",
		    "果洛藏族自治州": "632600",
		    "玉树藏族自治州": "632700",
		    "海西蒙古族藏族自治州": "632800",
		    "银川市": "640100",
		    "石嘴山市": "640200",
		    "吴忠市": "640300",
		    "固原市": "640400",
		    "中卫市": "640500",
		    "乌鲁木齐市": "650100",
		    "克拉玛依市": "650200",
		    "吐鲁番地区": "652100",
		    "哈密地区": "652200",
		    "昌吉回族自治州": "652300",
		    "博尔塔拉蒙古自治州": "652700",
		    "巴音郭楞蒙古自治州": "652800",
		    "阿克苏地区": "652900",
		    "克孜勒苏柯尔克孜自治州": "653000",
		    "喀什地区": "653100",
		    "和田地区": "653200",
		    "伊犁哈萨克自治州": "654000",
		    "塔城地区": "654200",
		    "阿勒泰地区": "654300",
		    "自治区直辖县级行政区划": "659000",
		    "台湾省": "710000",
		    "香港特别行政区": "810100",
		    "澳门特别行政区": "820000"
		  };
	
	
	kxdmapinfo.mapType = [
	               'china',
	               // 23个省
	               '广东', '青海', '四川', '海南', '陕西', '甘肃', '云南', '湖南', '湖北', '黑龙江', '贵州', '山东', '江西', '河南', '河北',
	               '山西', '安徽', '福建', '浙江', '江苏', '吉林', '辽宁', '台湾',
	               // 5个自治区
	               '新疆', '广西', '宁夏', '内蒙古', '西藏',
	               // 4个直辖市
	               '北京', '天津', '上海', '重庆',
	               // 2个特别行政区
	               '香港', '澳门'
	             ];
	
	kxdmapinfo.provinces = ['shanghai', 'hebei', 'shanxi', 'neimenggu', 'liaoning', 'jilin', 'heilongjiang', 'jiangsu', 'zhejiang', 'anhui', 'fujian', 'jiangxi', 'shandong', 'henan', 'hubei', 'hunan', 'guangdong', 'guangxi', 'hainan', 'sichuan', 'guizhou', 'yunnan', 'xizang', 'shanxi1', 'gansu', 'qinghai', 'ningxia', 'xinjiang', 'beijing', 'tianjin', 'chongqing', 'xianggang', 'aomen'];
	
	kxdmapinfo.provincesText = ['上海', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新疆', '北京', '天津', '重庆', '香港', '澳门'];
	//颜色配置{"较好":'#b4d3a4',"优秀":'#bbb9ee',"注意":'#e7de63',"异常":'#f0bf7a',"失常":'#fd842b',"严重":'#ef5757'}
	kxdmapinfo.color=['#b4d3a4','#bbb9ee','#e7de63','#f0bf7a','#fd842b','#ef5757'];
	kxdmapinfo.piecolor=['#61ECB9','#FF0000','#E3F855','#FFE58F','#2BC9FD','#F07BA5','#F99471','#D7B0FD','#ACBCAC'];
	
  var mChart = {};
  var mapTypeName = 'gansu';
  var maplevel = 1;
  var geoJson = null;
  var thetitle = '';
  var statusCacheData = [];
  var mapdata={};
  var chartData={};
  var mapgeoJson={};
  var pieData={};
  var id=orgid;
  var url="/soa/echarts/datas/map";
  var maptype=0;
  var auto_time=5;
  //var mychartdata = {};
  
  var kxddata={};
  //初始
  function initFlash(){
	  dataLoad(id,"map",maptype);
  }
  
  function getColor(v){
	  var color = ['#b4d3a4','#bbb9ee','#e7de63','#f0bf7a','#fd842b','#ef5757'];
	  var thecolor=null;
	  if(v>=95){
		  thecolor=color[0];
	  }else if(v>=90 && v<95){
		  thecolor=color[1];
	  }else if(v>=85 && v<90){
		  thecolor=color[2];
	  }else if(v>=80 && v<85){
		  thecolor=color[3];
	  }else if(v>=60 && v<80){
		  thecolor=color[4];
	  }else if(v<60){
		  thecolor=color[5];
	  }
	  
	  return thecolor;
 
  }
  
  
  function colorpush(data){
	  var arr = [];
	  for(var i=0;i<data.length;i++){
		  var v=0;
		  var max=data[i].maxValue;
		  var value=data[i].value;
		  if(data[i].maxValue===0){
			 data[i].itemStyle={"normal":{"areaColor":"#cccccc"}};
		  }else{
			  v=(value/max).toFixed(2)*100;
			  data[i].itemStyle={"normal":{"areaColor":getColor(v)}};
		  }
	  }
	  arr=data;
	  return arr;
  }
  
  function mapload(obj,state){
	  if (state===0) {
		  echarts.registerMap(mapTypeName, geoJson);
	  }else{
		  echarts.registerMap(mapTypeName, mapgeoJson);
	  }
	  	
	  	$('#smapArea').html('').hide();
	   
	    if(obj.data.level<3){
	    	chartData.level=obj.data.level;
	    	mapdata[obj.data.level]=obj.data.mapdata;
			mapoption.series[0].mapType=mapTypeName;
			mapoption.series[0].data=colorpush(mapdata[obj.data.level]);
			mapChart.setOption(mapoption,true);
			mapChart.hideLoading();
	    }else{
	    	mapChart.setOption(mapoption,true);
	    	mapChart.hideLoading();
	    	//处理高德数据
	    }
		showVdataTip($('#themap'));
		showmianMapdataNav($('#themap'));
  }
  
  function postdata(cmd,type){
	  var d = dialog({
			content : "数据加载中..."
		});
		d.show();
		setTimeout(function() {
			d.close().remove();
		}, 3000);
	  post(
		url,
		'json',
		kxddata,
		function(obj){
			if (obj.success===0) {
				 if (cmd==="map") {
					 mapload(obj,type);
					 dataLoad(kxddata.oid,"table",0);
					 dataLoad(kxddata.oid,"fault",0);
					 dataLoad(kxddata.oid,"manuf",0);
					 dataLoad(kxddata.oid,"parts",0);
				  }else if(cmd==="table"){
					  pieLoad(1,obj.data.msdata);//饼图1
					  sysChartMsgShow($('#sysChartMsg'),obj.data.msdata);
				  }else if(cmd==="fault"){
					  pieLoad(2,obj.data.statView);//饼图2
				  }else if(cmd==="manuf"){
					  pieLoad(3,obj.data.manufView);//饼图3
				  }else if(cmd==="parts"){
					  pieLoad(4,obj.data.partView);//饼图4
				  }else if(cmd==="search"){
					  mapload(obj,type);
				  }
				d.close().remove();
			}else{
				 if (cmd==="map") {
					 mapChart.hideLoading();
				  }else if(cmd==="table"){
					  myChartbingtu1.hideLoading();
				  }else if(cmd==="fault"){
					  myChartbingtu2.hideLoading();
				  }else if(cmd==="manuf"){
					  myChartbingtu3.hideLoading();
				  }else if(cmd==="parts"){
					  myChartbingtu4.hideLoading();
				  }
			}
		});
  }
  
  function dataLoad(oid,cmd,type){
	  kxddata.oid=oid;
	  kxddata.cmd=cmd;
	  if (cmd==="map") {
		  mapChart.showLoading();
		  postdata("map",type);
	  }else if(cmd==="table"){
		  myChartbingtu1.showLoading();
		  postdata("table",type);
	  }else if(cmd==="fault"){
		  myChartbingtu2.showLoading();
		  postdata("fault",type);
	  }else if(cmd==="manuf"){
		  myChartbingtu3.showLoading();
		  postdata("manuf",type);
	  }else if(cmd==="parts"){
		  myChartbingtu4.showLoading();
		  postdata("parts",type);
	  }
  }
  
  function dataLoadsearch(oid,cmd,type){
	  kxddata.oid=oid;
	  kxddata.cmd="map";
	  mapChart.showLoading();
	  postdata(cmd,type);
  }
  
	mChart.getGeoJson = function(c) { //获取扩展地图geo
		var geoJsonName = kxdmapinfo.cityMap[c];
		var thedata = null;
	
		if(geoJsonName !== undefined) {
		  $.ajax({
			type: "GET",
			url: contextPath+'/lib/echarts/map/geoJson/china-main-city/' + geoJsonName + '.json',
			async: false,
			dataType: "JSON",
			success: function(data) {
			  thedata = data;
			}
		  });
		}
	
		return thedata;
	
	  }

  function setTitle(o,t){
    o.html(t);
  }

  function loadStatus(o,t){
    var h = o.height();
    var p = '<p style="line-height:'+h+';font-size:14px;color:#666;">'+t+'</p>';
    o.html(p);
  }

  function showVdataTip(obj){
    var html = '<ul class="vdatatip" id="vdatatip">'
        +'<li><span>优秀</span><em class="bc1">&nbsp;</em></li>'
        +'<li><span>良好</span><em class="bc2">&nbsp;</em></li>'
        +'<li><span>较好</span><em class="bc3">&nbsp;</em></li>'
        +'<li><span>达标</span><em class="bc4">&nbsp;</em></li>'
        /*+'<li><span>失常</span><em class="bc5">&nbsp;</em></li>'*/
        +'<li><span>失常</span><em class="bc6">&nbsp;</em></li>'
      +'</ul>'
      ;
    if(!obj.find('#vdatatip').length>0){
        $(html).appendTo(obj);
    }  
  }
  
  function showmianMapdataNav(obj){
	    var html = '<ul class="mianmapdatanav" id="mianmapdatanav">'
	      	+'<li class="hover"><em id="0">&nbsp;</em><span>所有设备</span></li>'
	      	+'<li><em id="1">&nbsp;</em><span>缴费终端</span></li>'
	      	+'<li><em id="2">&nbsp;</em><span>营业PC</span></li>'
	      	+'<li><em id="4">&nbsp;</em><span>排队机</span></li>'      	
	      +'</ul>'
	      ;
	    if(!obj.find('#mianmapdatanav').length>0){
	        $(html).appendTo(obj);
	    }
  }
	   
	  $('#themap').delegate('#mianmapdatanav li','click',function(){
	    var ckid = $(this).find('em').attr("id");  
	    $(this).addClass('hover').siblings().removeClass('hover');
	    kxddata.deviceType=parseInt(ckid);
	    kxddata.onlineStatus=0;
		kxddata.faultStatus=0;
		kxddata.manufId=0;
		kxddata.partId=0
	    dataLoadsearch(id,"search",maptype);
	  });


  //..{'营业网点':1,'在线数':1,'离线数':2,'优秀':2,'设备总数':6}
//  var msdata = {'营业网点':1,'在线数':1,'离线数':2,'优秀':2,'设备总数':6};
  function sysChartMsgShow(o,msdata){
	 if(msdata!=null){
		 var table = '<table class="table">';
		    var td ='';
		    td= '<td>营业网点<span class="fc1">'+msdata.siteNumber+'个</span></td>'+
			    '<td>在线数<span class="fc1">'+msdata.onlineNumber+'台</span></td>'+
			    '<td>离线数<span class="fc1">'+msdata.offineNumber+'台</span></td>'+
			    '<td>优秀<span class="fc1">'+msdata.normalNumber+'台</span></td>'+
			    '<td>设备总数<span class="fc1">'+msdata.deviceNumber+'台</span></td>';
		    table +='<tr>'+td+'<tr>';
		    o.html(table);
	 }
  }

  var mapChart = echarts.init(document.getElementById('themap'),'shine');

  //地图option配置 
  var mapoption = {
      title: {
        text: '',
        subtext: ''        
      },
      tooltip: {
        trigger: 'item',
        formatter: function(params){
          var o = {},text = '在线数';
          if (!isNaN(params.value)) {
        	  o.o = params.value;
          }else{
        	  o.o = 0;
          }
          if (!isNaN(params.data.maxValue) || !params.data.maxValue == undefined) {
              o.z = params.data.maxValue;
          }else{
        	  o.z = 0;
          }
          if(o.z===0){
        	  var str = '<p style="color:#fff600;text-align:center;font-size:16px;padding:5px 0;">'+params.name+'</p>'+
        		'<p style="color:#8aff00;text-align:center;font-size:18px;padding:5px 0;">暂无数据..</p>';
		        return str;
          }else{
        	  var ofavg=(o.o/o.z)*100;
        	  o.f = ofavg.toFixed(2)+'%';
        	  var str = '<p style="color:#fff600;text-align:center;font-size:16px;padding:5px 0;">'+params.name+'</p>'+
        		'<p style="color:#8aff00;text-align:center;font-size:18px;padding:5px 0;">'+o.f+'</p>';
		        str += text + '：<span style="color:#8aff00;">'+o.o + '</span><br/>';
		        str += '\u603b\u6570：<span style="color:#8aff00;">'+ o.z + '</span>';
		        return str;
          }
        }
      },      
      series: [{
        name: '在线',
        type: 'map',
        mapType: 'gansu',
        roam: false,
        label: {
          normal: {
            show: true
          },
          emphasis: {
            show: true
          }
        },
        showLegendSymbol:false, 
        selectedMode: 'multiple',     
        data: mapdata
      }]
    };  
  
  //onload 首次加载...
  mapChart.showLoading({
      text: '数据正在加载中...',
      color: 'rgba(255, 255, 255, 0)',
      textColor: '#000',      
      maskColor: 'rgba(255, 255, 255, 0.8)',
      zlevel: 0
  });

  //加载青海地图
  $.get(contextPath+'/lib/echarts/map/json/province/gansu.json', function (Json) {
		geoJson = Json;
		mapgeoJson=Json;
	    mapChart.hideLoading();
	    echarts.registerMap('gansu', geoJson);
	    cutDown(auto_time,initFlash());
	    init_timer();
  });

  //缓存地图初始配置数据 json格式
  var statusmapoption1 = JSON.stringify(mapoption);  
  statusCacheData.push(statusmapoption1);

  //地图事件 mapselectchanged
  mapChart.on('mapselectchanged', function (params) {

	var mapTypeNametemp=params.name;
    var ischildren;
    for(var i=0;i<(mapdata[chartData.level]==undefined?0:mapdata[chartData.level].length);i++){
      if(mapTypeNametemp===mapdata[chartData.level][i].name){
    	  id=mapdata[chartData.level][i].id;
    	  ischildren=mapdata[chartData.level][i].ischildren;
    	  break;
      }
    }
    if (chartData.level<2) {
    	mapTypeName =  mapTypeNametemp;
	}
    if(ischildren===1){
    	if(chartData.level>=2){
    		chartData.level=3;
    		$('#smapArea').show();
    		//这里显示高德地图入口
    		var options = {mainChartDiv:'smapArea',city:mapTypeNametemp,orgid:id,url:'/soa/org/datas/findChildrenById'};
    		var kxdamap = new Kxdamap(options);
    		kxdamap.onLoad();
    	}else{
    		geoJson = mChart.getGeoJson(mapTypeName);
    		maptype=0;
        	dataLoad(id,"map",maptype);
        	$('#backtomap').show();
    	}
    }else{
    	/*var options = {mainChartDiv:'themap',city:'青海',orgid:id,url:'/soa/org/datas/findChildrenById'};
		var kxdamap = new Kxdamap(options);
		kxdamap.onLoad();*/
    	alert("没有需要的数据");
    	return;
    }
    
    
  });


  //返回按钮事件
  $('#backtomap').on('click',function(){
      if(chartData.level>2){
         $('#smapArea').html('').hide();
         chartData.level=2;
      }else{
    	  mapTypeName = 'gansu';
    	  maptype=1;
          dataLoad(oid,"map",maptype);
          id=oid;
          $('#backtomap').hide();
      }
     
  });

  //刷新按钮事件
  $('#mflush').on('click',function(){
      //初始化 地图 饼图;
	  if(chartData.level<3){
		  dataLoad(id,"map",maptype);
	  }
  });
  

//-------------------饼图开始---------------
  
  function pieLoad(num,datas){
	  if(datas!=null){
		  if(num===1){
				option1.series[0].data=pie1(datas);
				myChartbingtu1.setOption(option1, true);//饼图1
				myChartbingtu1.hideLoading();
			}else if (num===2) {
				option2.series[0].data=pie2(datas.statedata);
				myChartbingtu2.setOption(option2, true);//饼图2
				myChartbingtu2.hideLoading();
			}else if (num===3) {
				option3.series[0].data=pie3(datas);
				myChartbingtu3.setOption(option3, true);//饼图3
				setchartData($('#pie3'), datas, "维保厂商达标状态", "维保厂商达标状态");
				myChartbingtu3.hideLoading();
			}else if(num===4){
				option4.series[0].data=pie4(datas);
				myChartbingtu4.setOption(option4, true);//饼图4
				setchartData($('#pie4'), datas, "模块达标状态", "模块达标状态");
				myChartbingtu4.hideLoading();
			} 
	  }
  }
  
  //在线状态饼图    
  var bingtu1 = document.getElementById("bingbu1");
  var myChartbingtu1 = echarts.init(bingbu1);
  var option1 = {      
      tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },      
      series : [
          {
              name: '在线状态',
              type: 'pie',
              radius: ['30%', '70%'], 
              center:['50%','50%'],
              avoidLabelOverlap: false,
              selectedMode: 'multiple', 
              label: {
                normal: {
                    show: true,
                    position: 'inner',
                    formatter: '{d}%',
                    textStyle:{color:'#333'}
                }
              },                        
              data: pieData[1],
              itemStyle: {
                  emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
              },
          }
      ]
  };
  
  //pie--------------1---------
  function pie1(datas){
	  if(datas!=null){
		  $('#online').html(datas.onlineNumber);
		  $('#offline').html(datas.offineNumber);
		  var data=[{
	          value: datas.onlineNumber,
	          name: '在线',
	          onlineStatus: 1,
	          itemStyle: {
	            normal: {
	              color: '#E3F855'
	            }
	          }
	        },
	        {
	          value: datas.offineNumber,
	          name: '离线',
	          onlineStatus: 2,
	          itemStyle: {
	            normal: {
	              color: '#2BC9FD'                      
	            }
	          }
	        }
	    ];
	  }
	  
	  return data;
  }
//在线状态饼图点击事件
  //myChartbingtu1.on('pieselectchanged', function (params) {
  myChartbingtu1.on('click', function (params) {
   if(params.data.selected){
	   kxddata.onlineStatus=params.data.onlineStatus;
   }else{
	   kxddata.onlineStatus=0; 
   }
   kxddata.faultStatus=0;
   kxddata.manufId=0;
   kxddata.partId=0;
   //刷新当前地图
   dataLoadsearch(id,"search",maptype);
  });
//在线状态点击事件
  $('#pie1').on('click','li',function(){
	  var onlineStatus=$(this).attr('data-device');
	  kxddata.onlineStatus=onlineStatus;
	  kxddata.faultStatus=0;
	  kxddata.manufId=0;
	  kxddata.partId=0;
	  //刷新当前地图
	  dataLoadsearch(id,"search",maptype);
  });
//达标状态饼图    
  var bingtu2 = document.getElementById("bingbu2");
  var myChartbingtu2 = echarts.init(bingbu2);
  var option2 = {      
      tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },      
      series : [
          {
              name: '故障状态',
              type: 'pie',
              radius: ['30%', '70%'], 
              center:['50%','50%'],
              avoidLabelOverlap: false,
              selectedMode: 'multiple', 
              label: {
                normal: {
                    show: true,
                    position: 'inner',
                    formatter: '{d}%',
                    textStyle:{color:'#333'}
                }
              },                        
              data: pieData[2],
              itemStyle: {
                  emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
              },
          }
      ]
  };
  
//pie--------------2---------
  function pie2(datas){
	  if(datas!=null){
		  $('#normal').html(datas.normal);
		  $('#chance').html(datas.chance);
		  $('#alarm').html(datas.alarm);
		  $('#failure').html(datas.failure);
		  $('#disaster').html(datas.disaster);
	  var data=[{
          value:datas.normal,
          name: '正常',
          faultStatus: 1,
          itemStyle: {
            normal: {
              color: '#93BE48'
            }
          }
        },
        {
          value:datas.chance,
          name: '意外',
          faultStatus: 2,
          itemStyle: {
            normal: {
              color: '#93BEDF'                      
            }
          }
        },
        {
          value:datas.alarm,
          name: '告警',
          faultStatus: 3,
          itemStyle: {
            normal: {
              color: '#FFAE4F'                      
            }
          }
        },
        {
          value:datas.failure,
          name: '故障',
          faultStatus: 4,
          itemStyle: {
            normal: {
              color: '#FFFF38'                      
            }
          }
        },
        {
          value:datas.disaster,
          name: '灾难',
          faultStatus: 5,
          itemStyle: {
            normal: {
              color: '#FF4F38'                      
            }
          }
        }
    ];
	  }
	  return data;
  }
//在线状态饼图点击事件
  //myChartbingtu2.on('pieselectchanged', function (params) {
  myChartbingtu2.on('click', function (params) {
	   if(params.data.selected){
		   kxddata.faultStatus=params.data.faultStatus;
	   }else{
		   kxddata.faultStatus=0;
	   }
	   kxddata.onlineStatus=0;
	   kxddata.manufId=0;
	   kxddata.partId=0;
	 //刷新当前地图
	   dataLoadsearch(id,"search",maptype);
	  }); 
//故障状态点击事件
  $('#pie2').on('click','li',function(){
	  var faultStatus=$(this).attr('data-fault');
	  kxddata.onlineStatus=0;
	  kxddata.faultStatus=faultStatus;
	  kxddata.manufId=0;
	  kxddata.partId=0;
	  //刷新当前地图
	  dataLoadsearch(id,"search",maptype);
  });
//维保厂商达标状态    
  var bingtu3 = document.getElementById("bingbu3");
  var myChartbingtu3 = echarts.init(bingbu3);
  var option3 = {      
      tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },      
      series : [
          {
              name: '维保厂商达标状态',
              type: 'pie',
              radius: ['30%', '70%'], 
              center:['50%','50%'],
              avoidLabelOverlap: false,
              selectedMode: 'multiple', 
              label: {
                normal: {
                    show: true,
                    position: 'inner',
                    formatter: '{d}%',
                    textStyle:{color:'#333'}
                }
              },                        
              data: pieData[3],
              itemStyle: {
                  emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
              },
          }
      ]
  };
  
  //pie--------------3---------
  function pie3(datas){
	 var data=new Array();
	 for (var i = 0; i < datas.length; i++) {
		 data.push({
	          value: datas[i].value,
	          name: datas[i].name,
	          id:datas[i].id,
	          itemStyle: {
	            normal: {
	              color: kxdmapinfo.piecolor[i]
	            }
	          }
		 });
	}
	  return data;
  }
//在线状态饼图点击事件
  myChartbingtu3.on('click', function (params) {
	   if(params.data.selected){
		   kxddata.manufId=params.data.id;
	   }else{
		   kxddata.manufId=0;
	   }
	   kxddata.onlineStatus=0;
	   kxddata.faultStatus=0;
	   kxddata.partId=0;
	 //刷新当前地图
	   dataLoadsearch(id,"search",maptype);
	  }); 
//维保厂商达标状态点击事件
  $('#pie3').on('click','li',function(){
	 var manufId=$(this).attr('data-value');
	  kxddata.onlineStatus=0;
	  kxddata.faultStatus=0;
	  kxddata.manufId=manufId;
	  kxddata.partId=0;
	  //刷新当前地图
	  dataLoadsearch(id,"search",maptype);
  });
  //模块达标状态    
  var bingtu4 = document.getElementById("bingbu4");
  var myChartbingtu4 = echarts.init(bingbu4);
  var option4 = {      
      tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },      
      series : [
          {
              name: '模块达标状态',
              type: 'pie',
              radius: ['30%', '70%'], 
              center:['50%','50%'],
              avoidLabelOverlap: false,
              selectedMode: 'multiple', 
              label: {
                normal: {
                    show: true,
                    position: 'inner',
                    formatter: '{d}%',
                    textStyle:{color:'#333'}
                }
              },                        
              data: pieData[4],
              itemStyle: {
                  emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
              },
          }
      ]
  };
  
//pie--------------4---------
  function pie4(datas){
	 var data=new Array();
	 for (var i = 0; i < datas.length; i++) {
		 data.push({
	          value: datas[i].value,
	          name: datas[i].name,
	          id:datas[i].id,
	          maxValue:datas[i].maxValue,
	          itemStyle: {
	            normal: {
	              color: kxdmapinfo.piecolor[i]
	            }
	          }
		 });
	}
	  return data;
  }
//在线状态饼图点击事件
  myChartbingtu4.on('click', function (params) {
	   if(params.data.selected){
		   kxddata.partId=params.data.id;
	   }else{
		   kxddata.partId=0;
	   }
	   kxddata.onlineStatus=0;
	   kxddata.faultStatus=0;
	   kxddata.manufId=0;
	 //刷新当前地图
	   dataLoadsearch(id,"search",maptype);
	  }); 
//维保厂商达标状态点击事件
  $('#pie4').on('click','li',function(){
	  var partId=$(this).attr('data-value');
	  kxddata.onlineStatus=0;
	  kxddata.faultStatus=0;
	  kxddata.manufId=0;
	  kxddata.partId=partId;
	  //刷新当前地图
	  dataLoadsearch(id,"search",maptype);
  });
//-------------------饼图结束---------------
  
//饼图右边列表 数据 模板
  function setchartData(o, data,title,tips) {
      var data = data;
      var ul = '<ul class="chartData chartIndex">';
      var uld = '</ul>';
      var html = '';
      var li = '';
      var title = '<h5><a href="javascript:;" title="点击查看更多»">' + title + '</a></h5>';

      if (data.length > 2) {
          $.each(data, function(index, el) {
              li += '<li class="fx" data-value='+el.id+' title=' + el.name+':'+((el.value/el.maxValue).toFixed(2)*100) + '% data-ajax=' + tips + ' data-name=' + el.name + '><em class="c1" style="background-color:' + kxdmapinfo.piecolor[index] + ';"></em>' + el.name + '</li>';
          });

      } else {
          $.each(data, function(index, el) {
        	  
              li += '<li class="fx" data-value='+el.id+' title=' + el.name+':'+((el.value/el.maxValue).toFixed(2)*100) + '% data-ajax=' + tips + ' data-name=' + el.name + '><em class="c1" style="background-color:' + kxdmapinfo.piecolor[index] + ';"></em>' + el.name + '</li>';
          });
      }
      html = ul +title+ li + uld;
      o.html(html);

  }
  
//-------------auto-----s
  function cutDown(m,fn){
	    var m = m;//分
	    var s = 60;//秒

	    function showtime(){
	        
	        if(m>0){
	            $('.cuttime').html('<span>'+(m-1) +"分"+ (s-1)+"秒"+ "后自动刷新！" + '</span>'); 
	        }  
	        s = s-1;   
	        if(s==0){
	            m = m -1;
	            s = 60
	        }
	        if(m==0){            
	            fn();
	            clearInterval(settime);
	        }

	    }

	    clearInterval(settime);
	    settime = setInterval(function(){
	        showtime();
	    },1000);

	}

  function init_timer(){
	$('.cuttime').html('---');
	if(timer!=null){
    	clearInterval(timer);
    }
	timer = setInterval(function(){
	        cutDown(auto_time,dataLoad(id,"map",maptype));
	    },60000*auto_time);
  }
 //-------------auto-----e
});


