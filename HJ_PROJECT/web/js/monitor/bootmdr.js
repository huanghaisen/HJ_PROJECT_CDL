
/**
 * 设备监控使用
 */
$(function(){
	
	var lineurl="/soa/echarts/datas/boot?cmd=line";
	var barurl="/soa/echarts/datas/boot?cmd=bar";
	var pieurl="/soa/echarts/datas/boot?cmd=pie";
	var tableurl="/soa/echarts/datas/boot?cmd=table";
	var timelinedata = [];//分割时间小时
	var timelinetemp=[];//点击事件分割
	var maindata = [];
	var barname=[];
	var barvalue=[];
	var data={};
	var auto_time=5;
	var table;
	//---------线图---------statr
	var charts={
			check:0
	};
		function lineinit(){
			cutDown(auto_time,flash(null));
		    init_timer();
		}
		
		function flash(time){
			lineLoad(time);
			barLoad(time);
			pieLoad(time);
			if(table==undefined || table==''){
				tables();
			}else{
				table.ajax.reload();
			}
			charts.check=0;//点击标记
		}
	
		function lineLoad(tlong){
			lineChart.showLoading();
			data.time=tlong;
			data.oid=oid;
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
							timelinedata.push(datas[i].dlong);
							timelinetemp.push(datas[i].dlong);
							maindata.push({content:datas[i].content,value:datas[i].value});
							$("#time").html("("+reduday()+")");
						}
						option = optionline;
					    if (option && typeof option === "object") {
					        lineChart.setOption(option, true);
					    }
					    setTimeslider(timelinetemp);
					    setToolbtn();
					    lineChart.hideLoading();
					}
				});
		}
	 
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
	            text: '全省终端监控',
	            //subtext: thedate,
	            left: '10%',
	            textStyle: {
	                color: '#000',
	                fontWeight: 'normal',
	                fontSize: 18
	            }
	        },
	        tooltip: {
	            formatter: function(params) {
	                var o = {},
	                    text1 = '实际开机',
	                    text2 = '额定开机';
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
	            name: '在线状态',
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

	    //---------线图---------end
		//---------柱图---------statr
	    function barLoad(time){
	    	data.oid=oid;
	    	data.time=time;
			post(
				barurl,
				'JSON',
				data,
				function(obj){
					if (obj.success===0) {
						var datas=obj.data.barList;
						barname.length = 0;
						barvalue.length = 0;
						for (var i = 0; i < datas.length; i++) {
							var name=datas[i].name;
							barname.push(name.length>3?name.substring(0,2):name);
							barvalue.push({content:datas[i].content,value:datas[i].value,name:datas[i].name});
						}
					}
				});
		}

	    var optionbar = {
	        title: {
	            text: '全省终端监控',
	            //subtext: thedate ,
	            left: '10%',
	            textStyle: {
	                color: '#000',
	                fontWeight: 'normal',
	                fontSize: 18,
	            }
	        },
	        tooltip: {
	            formatter: function(params) {
	                var o = {},
	                    text1 = '实际开机',
	                    text2 = '额定开机';
	                o.zx = params.data.value;
	                o.zs = params.data.content;
	                if(o.zs!=0){
	                	o.f = ((o.zx / o.zs) * 100).toFixed() + '%';
	            	}else{
	            		o.f ='0%';
	            	}
	                var str = '<p style="color:#fff600;text-align:center;font-size:16px;">' +o.f+ '</p>';
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
	        grid: {
	            left: '40px',
	            right: '40px',
	            bottom: '40px',
	            containLabel: false
	        },
	        xAxis: [{
	            type: 'category',
	            data:barname
	        }],
	        yAxis: [{
	            type: 'value',
	            name: '%',
	            axisLabel: {
	                formatter: '{value}'
	            }
	        }],
	        series: [
	            {
	                name: '分数',
	                type: 'bar',
	                data:barvalue
	            }
	        ]
	    };
		//---------柱图---------end
	    //---------饼图---------start
	    function pieLoad(time){
	    	data.oid=oid;
	    	data.time=time;
	    	myChartbingtu.showLoading();
			post(
				pieurl,
				'JSON',
				data,
				function(obj){
					if (obj.success===0) {
						var datas=obj.data.piedata;
						btoption.series[0].data=pie(datas);
						myChartbingtu.setOption(btoption, true);
						myChartbingtu.hideLoading();
					}
				});
		}
	  //在线状态饼图    
	    var zxbingtu = document.getElementById("bingbu1");
	    var myChartbingtu = echarts.init(zxbingtu);
	    var btoption = {      
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
	                data: [],
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
	    
	    function pie(datas){
	      $('#online').html(datas.valueNumber);
	  	  $('#offline').html(datas.offineNumber);
			$('#txt_znum').html(datas.deviceNumber);
			$('#txt_zx').html(datas.valueNumber);
			$('#txt_lx').html(datas.offineNumber);
			$('#txt_aver').html(datas.deviceave+"%");
	  	
	  	  var data=[{
		              value: datas.valueNumber,
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
	    
	    //---------饼图---------end
	    //---------表格---------start
	    // 初始化表格
	    //获取月份天数
	    function getMonthNum(month, year) {           
            var LeapYear = ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ? true: false;
            var monthNum;
            switch (parseInt(month)) {            
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 11:
                monthNum = 31;
                break;
            case 4:
            case 6:
            case 9:            
            case 10:
            case 12:
                monthNum = 30;
                break;
            case 2:
                monthNum = LeapYear ? 29: 28;
            }
            return monthNum;
        }
	    //获取当前时间的前一天
	    function reduday(){
			var year = new Date().getFullYear();
			var month = new Date().getMonth() + 1;
			var day = new Date().getDate();
	
			var monthnum = 12;
	
			if (day > 1) {
				day = day - 1;
			} else {
	
				if (month > 1) {
					month -= 1;
				} else {
					month = monthnum;
					year -= 1
				}
				day = getMonthNum(month, year);
			}
	
			month = month >= 10 ? month : '0' + month;
			day = day >= 10 ? day : '0' + day;
	
			var val = year + '-' + month + '-' + day;
			return val;
	    }
	   function tables(){
		  table = $('#thetable').DataTable({
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
		             { "data": function(e) {return e.id;},"title":"序号"},
		             { "data": "name","title":"机构名称"},
		             { "data": function(e){
		            	 return '<div class="progress progress-xs progress-striped active">'+
	                     '<div class="progress-bar progress-bar-success" style="width: '+e.rate+'%"></div>'+
	                     '</div><span class="badge bg-green">'+e.rate+'%</span>';

		             	},"title":"开机率"},
		             { "data": "status","title":"状态"},
		             { "data": "fullNumber","title":"额定开机(H)"},
		             { "data": "valueNumber","title":"实际开机(H)"},
		             {"field": null, "title": '操作', "isShow": true,"width": 50, "isSort": false,
				          "data": null,
				          "render": function (data, type, row) {
				              return '<a href="javaScript:void(0);" name="search_a"><i class="fa fa-search" title="查看"></i> 查看</a>';
				          }
				      }
		             
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
					'width' : '25%',
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
					'width' : '15%',
					'className' : 'dt-body-center',
					'render' : function(data, type, full, meta) {
					return data;
					}
				},{
					'targets' : 5,
					'searchable' : false,
					'orderable' : false,
					'width' : '15%',
					'className' : 'dt-body-center',
					'render' : function(data, type, full, meta) {
					return data;
					}
				},{
					'targets' : 6,
					'searchable' : false,
					'orderable' : false,
					'width' : '20%',
					'className' : 'dt-body-center',
					'render' : function(data, type, full, meta) {
					return data;
					}
				} ],    
		                
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
	   }
	    //---------表格---------end
	    //---------初始---------statr
	    lineinit();//线图初始
	    //---------初始---------end
	    
	    
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
	  	        cutDown(auto_time,flash(null));
	  	    },60000*auto_time);
	    }
	   //-------------auto-----e
	    var initMonth=''; //默认选中当前月份
	    $("#thetable").delegate("a[name='search_a']", "click", function(event) {
	    	var html=$(this).parent().parent().children().html();
	    	oid=html;
	    	flash(null);
	    	$('#theMonth').find('select').val(initMonth);
	    });
	    function initSelectMonth(){
	    	var year = new Date().getFullYear();
	    	var month = new Date().getMonth()+1;
	    	var $select = $('<select>');
	    	var op='';

	    	var theyear = year;
	    	initMonth=year+'-'+month;
	    	for(var i=0;i<6;i++){  
	    	  month--;
	    	  if(month<0){
	    	    month = 12+month;
	    	    theyear=year-1;
	    	  }  
	    	  op+='<option value="'+theyear+'-'+(month+1)+'">'+theyear+'年'+(month+1)+'月</option>';
	    	}
	    	$(op).appendTo($select);
	    	$select.appendTo($('#theMonth'));
	    }
	    initSelectMonth();
	    $('#theMonth').find('select').change(function(){
	    	flash($(this).val());
	    });
	    
	    //导出
	    $('#bootExport').on('click',function(){
	    	location.href =contextPath+'/soa/echarts/boot/export?oid='+oid;
	    });
	    
});




