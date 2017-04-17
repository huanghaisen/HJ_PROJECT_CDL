
/**
 * 设备监控使用
 */
$(function(){
	var mapurl="/soa/echarts/datas/screen?cmd=map";
	var lineurl="/soa/echarts/datas/screen?cmd=line";
	var onlineurl="/soa/echarts/datas/screen?cmd=pie&p=online";
	var faulturl="/soa/echarts/datas/screen?cmd=pie&p=fault";
	var manufurl="/soa/echarts/datas/screen?cmd=pie&p=manuf";
	var partsurl="/soa/echarts/datas/screen?cmd=pie&p=parts";
	var tableurl="/soa/echarts/datas/screen?cmd=table";
	var piecolor=['#61ECB9','#FF0000','#E3F855','#FFE58F','#2BC9FD','#F07BA5','#F99471','#D7B0FD','#ACBCAC'];

	  function layout(w,h){
	    $("#main").css({width:w+"px",height:h+"px"});
	  }

	  function setHeight(o,h){
	    o.css({height:h+'px'});
	  }

	  function resizeWin(){

	    var winWidth = window.innerWidth;
	    var winHeight = window.innerHeight; 

	    var rowH1 = winHeight*35/100;
	    var rowH2 = winHeight*14/100;
	    var rowH3 = winHeight*38/100; 

	    layout(winWidth,winHeight);

	    setHeight($('.h-row-1'),rowH1);
	    setHeight($('.h-row-2'),rowH2);
	    setHeight($('.h-row-3'),rowH3);
	  };
	  resizeWin();

	  //-------------------------地图------------s
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
		//颜色配置{"较好":'#b4d3a4',"正常":'#bbb9ee',"注意":'#e7de63',"异常":'#f0bf7a',"失常":'#fd842b',"严重":'#ef5757'}
		kxdmapinfo.color=['#b4d3a4','#bbb9ee','#e7de63','#f0bf7a','#fd842b','#ef5757'];
		kxdmapinfo.piecolor=['#61ECB9','#FF0000','#E3F855','#FFE58F','#2BC9FD','#F07BA5','#F99471','#D7B0FD','#ACBCAC'];
		
	  var mChart = {};
	  var mapTypeName = 'qinghai';
	  var maplevel = 1;
	  var geoJson = null;
	  var thetitle = '';
	  var statusCacheData = [];
	  var mapdata={};
	  var chartData={};
	  var mapgeoJson={};
	  var pieData={};

	  //var mychartdata = {};
	  
	  var data={};
	  //初始
	  function initFlash(){
		  dataLoad(212);
		  lineinit();
		  pieLoad1();
		  pieLoad2();
		  pieLoad3();
		  pieLoad4();
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
		  
		  	chartData.level=obj.data.level;
			sysChartMsgShow($('#sysChartMsg'),obj.data.msdata);
			mapdata[obj.data.level]=obj.data.mapdata;
			mapoption.series[0].mapType=mapTypeName;
			mapoption.series[0].data=colorpush(mapdata[obj.data.level]);
			mapChart.setOption(mapoption,true);
			mapChart.hideLoading();
			showVdataTip($('#themap'));
	  }
	  
	  function dataLoad(oid){
		  mapChart.showLoading();
		  myChartbingtu1.showLoading();
		  myChartbingtu2.showLoading();
		  myChartbingtu3.showLoading();
		  myChartbingtu4.showLoading();
		  data.oid=oid;
		  post(
			mapurl,
			'json',
			data,
			function(obj){
				if (obj.success===0) {
					mapload(obj,0);
				}
			});
	  }
	  
	  function flash(oid){
		  mapChart.showLoading();
		  myChartbingtu1.showLoading();
		  myChartbingtu2.showLoading();
		  myChartbingtu3.showLoading();
		  myChartbingtu4.showLoading();
		  data.oid=oid;
		  post(
			mapurl,
			'json',
			data,
			function(obj){
				if (obj.success===0) {
					mapload(obj,1);
				}
			});
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
	  
	  function sysChartMsgShow(o,msdata){

		    var table = '<table class="table">';
		    var td ='';
		    td= '<td>营业网点<span class="fc1">'+msdata.siteNumber+'个</span></td>'+
			    '<td>在线数<span class="fc1">'+msdata.onlineNumber+'台</span></td>'+
			    '<td>离线数<span class="fc1">'+msdata.offineNumber+'台</span></td>'+
			    '<td>正常<span class="fc1">'+msdata.normalNumber+'台</span></td>'+
			    '<td>设备总数<span class="fc1">'+msdata.deviceNumber+'台</span></td>';
		    table +='<tr>'+td+'<tr>';
		    o.html(table);
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
		        	  o.f =(o.o/o.z)*100+'%';
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
		        mapType: 'qinghai',
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
	  //-------------------------地图-------------e
		  
	//-------------------------线图-------------s
	var timelinedata = [];//分割时间小时
	var timelinetemp=[];//点击事件分割
	var maindata = [];
	var barname=[];
	var barvalue=[];
	var data={};
	  var charts={
				check:0
		};
			function lineinit(){
				lineLoad(null);
			}
		
			function lineLoad(tlong){
				lineChart.showLoading();
				data.time=tlong;
				post(
					lineurl,
					'JSON',
					data,
					function(obj){
						if (obj.success===0) {
							var datas=obj.data.lineList;
							timelinedata.length = 0;
							timelinetemp.length = 0;
							maindata.length = 0;
							for (var i = 0; i < datas.length; i++) {
								timelinedata.push(tlong===null?getHourlinedata(datas[i].tlong):getTimelineMindata(obj.data.tlong,datas[i].tlong));
								timelinetemp.push(datas[i].tlong);
								maindata.push({content:datas[i].content,value:datas[i].value});
							}
							option = optionline;
						    if (option && typeof option === "object") {
						        lineChart.setOption(option, true);
						    }
						    $("#time").html("("+obj.data.tlong+":"+obj.data.mlong+")");
						    setTimeslider(timelinetemp);
						    setToolbtn();
						    lineChart.hideLoading();
						}
					});
			}
			
			var thewidth = $("#theline").width();
			$('#theline').css({width:thewidth+'px'});
		    var theline = document.getElementById("theline");
		    var lineChart = echarts.init(theline);
		    var nowdate = new Date();
		    var thedate = moment().format('YYYY-MM-DD');
		    
		    //时间的小时分割
		    function getHourlinedata(tlong){
		    	var t = tlong < 10 ? ('0' +tlong +':00') : (tlong + ':00'); 
		        return t;
		    }
		    
		  //一时间的5分钟分割 t当前时刻
		   function getTimelineMindata(t,m){
	    	 var d='';
	    	 var h = t < 10 ? ('0' +t) : (t); 
	    	 
	            if(m===0){
	              d = h+":00";      
	            }
	            if(m<10){
	                d = h+':0'+m;
	            }else{
	            	d = h+':'+m;
	            }
	            return d;
		    }

		    var option = null;
		    var optionline = {//线图
		        title: {
		            text: '全省故障率',
		            subtext: thedate,
		            left: '10%',
		            textStyle: {
		                color: '#fff',
		                fontWeight: 'normal',
		                fontSize: 18
		            }
		        },
		        tooltip: {
		            formatter: function(params) {
		                var o = {},
		                    text1 = '故障数',
		                    text2 = '总数';
		                o.zx = params.data.value;
		                o.zs = params.data.content;
		                o.f = ((o.zx / o.zs) * 100).toFixed() + '%';

		                var str = '<p style="color:#fff600;text-align:center;font-size:16px;">' + o.f + '</p>';
		                str += text1 + '：<span style="color:#8aff00;">' + o.zx + '</span><br/>';
		                str += text2 + '：<span style="color:#8aff00;">' + o.zs + '</span>';
		                return str;

		            }
		        },
		        itemStyle: {
		            normal: {
		                color: '#55bc75'
		            }
		        },
		        lineStyle: {
		            normal: {
		                color: '#4fbd66'
		            }
		        },
		        areaStyle: {
		            normal: {
		                color: '#55bc75'
		            }
		        },
		        grid: {
		            left: '40px',
		            right: '40px',
		            bottom: '40px',
		            containLabel: false
		        },
		        xAxis: [{
		            name: '',
		            type: 'category',
		            boundaryGap: false,
		            splitLine: {
		                show: false
		            },
		            data: timelinedata
		        }],
		        yAxis: [{
		            name: '%',
		            type: 'value'
		        }],
		        series: [{
		            name: '故障状态',
		            type: 'line',
		            stack: '%',
		            areaStyle: {
		                normal: {}
		            },
		            data: maindata
		        }]
		    };
		    
		      //时间分割线点击事件
			  $('#slider').delegate('a', 'click', function(event) {
				  if (charts.check===0) {
					  $(this).addClass('cur').siblings().removeClass('cur');
				      var time = parseInt($(this).attr('data-ajax'));
				      lineLoad(time);
				      charts.check=1;
				      $(this).parent().hide();
				  }else{
					  $(this).addClass('cur').siblings().removeClass('cur');
				      var time = parseInt($(this).attr('data-ajax'));
				      lineLoad(null);
				      charts.check=0;
				      $(this).parent().hide();
				  }
			     
			  });
			  
			// 折线图图标 柱状图图标点击事件
			    $('#mtool').delegate('.btn', 'click', function(event) {
			      var title = $(this).attr('title');
			      if(title==="line"){
			        option = optionline;        
			        lineChart.setOption(option, true);
			        $('#slider').show();

			      }

			      if(title==="bar"){
			        option = optionbar;
			        lineChart.setOption(option, true);
			        $('#slider').hide();
			      }

			      if(!$(this).hasClass('btn-info')){
			        $(this).addClass('btn-info').removeClass('btn-default').siblings().removeClass('btn-info');
			      }else{
			        $(this).removeClass('btn-default');
			      }
			    });
			    
			    function setTimeslider(data){
			        var width = $('#theline').width()-80;
			        var a ='';
			        w=width/data.length;
			        
			        $.each(data,function(index,el){
			             a+='<a href="javascript:;" data-ajax=' + el + ' style="width:'+w+'px">'+el+'</a>';
			        });

			        $('#slider').width(width).html(a).show();

			    }
			    
			  //设置图标工具按钮
			    function setToolbtn(){
			       var btn = '<button type="button" class="btn btn-info btn-sm" title="line" ><i class="fa fa-line-chart"></i></button>'
			          +'<button type="button" class="btn btn-default btn-sm" title="bar"><i class="fa fa-bar-chart"></i></button>';

			       $('#mtool').html(btn);   
			    }	  
	
	//-------------------------线图---------------e
			    
			    
	//-------------------------table-------------s
			    var table_index=1;
			    var table = $('#thetable').DataTable({
			       // 'dom': 'Bfrtip',
			       "ajax": {
			             "url": contextPath+tableurl,
			             "type":"POST",
			             "dataType":"json",
			             "dataSrc":"data.tableList",
			              "data": function ( d ) {
			                 d.time = null;
			                 d.mtime = null;
			                 d.oid = oid;
			             }} ,  
			      'columns': [
			             { "data": function(e) {return table_index++;},"title":"序号"},
			             { "data": "name","title":"机构名称"},
			             { "data": function(e){
			            	 return '<div class="progress progress-xs progress-striped active">'+
		                     '<div class="progress-bar progress-bar-success" style="width: '+e.rate+'%"></div>'+
		                     '</div><span class="badge bg-green">'+e.rate+'%</span>';

			             	},"title":"故障率"},
			             { "data": "status","title":"状态"},
			             { "data": "fullNumber","title":"总数"},
			             { "data": "onlineNumber","title":"故障数"}
			         ],
			       'columnDefs': [ {
						'targets' : 0,
						'searchable' : false,
						'orderable' : false,
						'width' : '8%',
						'className' : 'dt-body-center',
						'render' : function(data, type, full, meta) {
						return data;
						}
					},{
						'targets' : 1,
						'searchable' : false,
						'orderable' : false,
						'width' : '20%',
						'className' : 'dt-body-center',
						'render' : function(data, type, full, meta) {
						return data;
						}
					},{
						'targets' : 2,
						'searchable' : false,
						'orderable' : false,
						'width' : '35%',
						'className' : 'dt-body-center',
						'render' : function(data, type, full, meta) {
						return data;
						}
					},{
						'targets' : 3,
						'searchable' : false,
						'orderable' : false,
						'width' : '8%',
						'className' : 'dt-body-center',
						'render' : function(data, type, full, meta) {
						return data;
						}
					},{
						'targets' : 4,
						'searchable' : false,
						'orderable' : false,
						'width' : '10%',
						'className' : 'dt-body-center',
						'render' : function(data, type, full, meta) {
						return data;
						}
					},{
						'targets' : 5,
						'searchable' : false,
						'orderable' : false,
						'width' : '10%',
						'className' : 'dt-body-center',
						'render' : function(data, type, full, meta) {
						return data;
						}
					}],    
			                
			       'paging':false,
			       'info': false,
			       'lengthChange': false, 
			       'showRowNumber':true,
			       'bFilter':false,
			       'ordering':false,
			       'rowCallback': function(row, data, dataIndex){
			          //获取行id
			          
			          //var rowId = data.tid;

			          //console.log(data);
			          
			          $(row).find('.btn').on('click',function(){
			             alert(data['机构名称']);
			          })

			         //$(row).find('input[type="checkbox"]').val(rowId);

			          

			       }
			    });
			    
	//-------------------------table-------------e
			    
	//-------------------------pie-------------s
		//------1
	    function pieLoad1(){
	    	myChartbingtu1.showLoading();
			post(
				onlineurl,
				'JSON',
				data,
				function(obj){
					if (obj.success===0) {
						var datas=obj.data.piedata;
						btoption1.series[0].data=pie1(datas);
						myChartbingtu1.setOption(btoption1, true);
						myChartbingtu1.hideLoading();
					}
				});
		}
	    var bingtu1 = document.getElementById("bingbu1");
		var myChartbingtu1 = echarts.init(bingbu1);
		var btoption1 = {
		    title : {
		    	text: '在线状态',
		        subtext: '',
		        x:'70%',
		        y:'30%',
		        textStyle: {
		          color: '#fff',
		          fontWeight:'normal',
		          fontSize:16
		        }
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    series : [
		        {
		            name: '在线状态',
		            type: 'pie',
		            radius: ['30%', '70%'], 
		            center:['30%','50%'],           
		            data:[],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		
		function pie1(datas){
		  	  var data=[{
			              value: datas.onlineNumber,
			              name: '在线',
			              itemStyle: {
			                normal: {
			                  color: '#55bc75'
			                }
			              }
			            },
			            {
			              value: datas.offineNumber,
			              name: '离线',
			              itemStyle: {
			                normal: {
			                  color: '#54b5ff'                      
			                }
			              }
			            }
			        ];
		  	  return data;
		    }
		
		
		//------2
		function pieLoad2(){
			myChartbingtu2.showLoading();
			post(
				faulturl,
				'JSON',
				data,
				function(obj){
					if (obj.success===0) {
						var datas=obj.data.piedata;
						btoption2.series[0].data=pie2(datas);
						myChartbingtu2.setOption(btoption2, true);
						myChartbingtu2.hideLoading();
					}
				});
		}
		
		var bingtu2 = document.getElementById("bingbu2");
	    var myChartbingtu2 = echarts.init(bingbu2);
	    var btoption2 = {
	      title: {
	        text: '故障状态',
	        subtext: '',
	        x: '70%',
	        y:'30%',
	        textStyle: {
	          color: '#fff',
	          fontWeight:'normal',
	          fontSize:16
	        }
	      },
	      tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	      },
	      series: [{
	        name: '故障状态',
	        type: 'pie',
	        radius: ['30%', '70%'], 
	        center:['30%','50%'],         
	        data: [],
	        itemStyle: {
	          emphasis: {
	            shadowBlur: 10,
	            shadowOffsetX: 0,
	            shadowColor: 'rgba(0, 0, 0, 0.5)'
	          }
	        }
	      }]
	    };
		
		function pie2(datas){
		  	  var data=[{
			              value: datas.onlineNumber,
			              name: '正常',
			              itemStyle: {
			                normal: {
			                  color: '#93BE48'
			                }
			              }
			            },
			            {
			              value: datas.warningNumber,
			              name: '告警',
			              itemStyle: {
			                normal: {
			                  color: '#FFAE4F'                      
			                }
			              }
			            },
			            {
			              value: datas.faultNumber,
			              name: '故障',
			              itemStyle: {
			                normal: {
			                  color: '#FFFF38'                      
			                }
			              }
			            }
			        ];
		  	  return data;
		    }
		
		//------3
		function pieLoad3(){
	    	myChartbingtu3.showLoading();
			post(
				manufurl,
				'JSON',
				data,
				function(obj){
					if (obj.success===0) {
						var datas=obj.data.piedata;
						btoption3.series[0].data=pie3(datas);
						myChartbingtu3.setOption(btoption3, true);
						myChartbingtu3.hideLoading();
					}
				});
		}
		
		var zxbingtu = document.getElementById("bingbu3");
	    var myChartbingtu3 = echarts.init(zxbingtu);
	    var btoption3 = {
	  	      title: {
	  	        text: '厂商故障状态',
	  	        subtext: '',
		        x: '60%',
		        y:'30%',
	  	        textStyle: {
	  	          color: '#fff',
	  	          fontWeight:'normal',
	  	          fontSize:16
	  	        }
	  	      },
	  	      tooltip: {
	  	        trigger: 'item',
	  	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	  	      },
	  	      series: [{
	  	        name: '维保厂商',
	  	        type: 'pie',
	  	        radius: ['30%', '70%'], 
	  	        center:['30%','50%'],         
	  	        data: [],
	  	        itemStyle: {
	  	          emphasis: {
	  	            shadowBlur: 10,
	  	            shadowOffsetX: 0,
	  	            shadowColor: 'rgba(0, 0, 0, 0.5)'
	  	          }
	  	        }
	  	      }]
	  	    };
	    
	    function pie3(datas){
	  	 var data=new Array();
	  	 for (var i = 0; i < datas.length; i++) {
	  		 data.push({
	  	          value: datas[i].faultNumber,
	  	          total: datas[i].totalNumber,
	  	          name: datas[i].name,
	  	          itemStyle: {
	  	            normal: {
	  	              color: piecolor[i]
	  	            }
	  	          }
	  		 });
	  	}
	  	  return data;
	    }
	    
	    //------4
	    function pieLoad4(){
	    	myChartbingtu4.showLoading();
			post(
				partsurl,
				'JSON',
				data,
				function(obj){
					if (obj.success===0) {
						var datas=obj.data.piedata;
						btoption4.series[0].data=pie4(datas);
						myChartbingtu4.setOption(btoption4, true);
						myChartbingtu4.hideLoading();
					}
				});
		}
	    
	    var bingtu4 = document.getElementById("bingbu4");
	    var myChartbingtu4 = echarts.init(bingbu4);
	    var btoption4 = {
	      title: {
	        text: '模块故障状态',
	        subtext: '',
	        x: '70%',
	        y:'30%',
	        textStyle: {
	          color: '#fff',
	          fontWeight:'normal',
	          fontSize:16
	        }
	      },
	      tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	      },
	      series: [{
	        name: '模块故障状态',
	        type: 'pie',
	        radius: ['30%', '70%'],    
	        center:['30%','50%'],      
	        data: [],
	        itemStyle: {
	          emphasis: {
	            shadowBlur: 10,
	            shadowOffsetX: 0,
	            shadowColor: 'rgba(0, 0, 0, 0.5)'
	          }
	        }
	      }]
	    };
	    
	    function pie4(datas){
		  	 var data=new Array();
		  	 for (var i = 0; i < datas.length; i++) {
		  		 data.push({
		  	          value: datas[i].faultNumber,
		  	          total: datas[i].totalNumber,
		  	          name: datas[i].name,
		  	          itemStyle: {
		  	            normal: {
		  	              color: piecolor[i]
		  	            }
		  	          }
		  		 });
		  	}
		  	  return data;
		    }
			    
	//-------------------------pie-------------e
	    
	  mapChart.showLoading({
	      text: '数据正在加载中...',
	      color: 'rgba(255, 255, 255, 0)',
	      textColor: '#000',      
	      maskColor: 'rgba(255, 255, 255, 0.8)',
	      zlevel: 0
	  });

	//加载青海地图
	  $.get(contextPath+'/lib/echarts/map/json/province/qinghai.json', function (Json) {
			geoJson = Json;
			mapgeoJson=Json;
		    mapChart.hideLoading();
		    echarts.registerMap('qinghai', geoJson);
		    cutDown(5,initFlash());
	  });
	  
	 //-------------auto-----s
	  
	  function cutDown(m,fn){
		    var m = m;//分
		    var s = 60;//秒

		    function showtime(){
		        
		        if(m>0){
		            $('#cuttime').html('<span>'+(m-1) +"分"+ (s-1)+"秒"+ "后自动刷新！" + '</span>'); 
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
		    var settime = setInterval(function(){
		        showtime();
		    },1000);

		}

		var timer = setInterval(function(){
		        cutDown(5,initFlash());
		    },60000*5);

	 //-------------auto-----e
});


