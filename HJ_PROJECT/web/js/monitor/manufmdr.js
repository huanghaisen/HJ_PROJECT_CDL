
/**
 * 设备监控使用
 */
$(function(){
	
	var lineurl=dataurl+"?cmd=line";
	var barurl=dataurl+"?cmd=bar";
	var pieurl=dataurl+"?cmd=pie";
	var tableurl=dataurl+"?cmd=table";
	var piecolor=['#61ECB9','#FF0000','#E3F855','#FFE58F','#2BC9FD','#F07BA5','#F99471','#D7B0FD','#ACBCAC'];
	var timelinedata = [];//分割时间小时
	var timelinetemp=[];//点击事件分割
	var maindata = [];
	var timelinedata=[];
	var legenddata=[];
	var barvalue=[];
	var data={};
	var auto_time=5;
	var table;
	//---------线图---------statr
	var charts={
			check:0
	};
		function lineinit(){
			cutDown(auto_time,flash());
		    init_timer();
		}
		
		function flash(){
			lineLoad(null);
			//barLoad();
			pieLoad();
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
						var lineView=datas[0].lineView;
						for (var i = 0; i < lineView.length; i++) {
							timelinedata.push(tlong===null?getHourlinedata(lineView[i].tlong):getTimelineMindata(obj.data.tlong,lineView[i].tlong));
							timelinetemp.push(lineView[i].tlong);
						}
						
						for (var i = 0; i < datas.length; i++) {
							var manufView=datas[i];
							legenddata.push(manufView.name);
							maindata.push({name:manufView.name,type:"line",data:manufView.lineView});
						}
						option = optionline;
					    if (option && typeof option === "object") {
					        lineChart.setOption(option, true);
					    }
					    $("#time").html("("+obj.data.tlong+":"+obj.data.mlong+")");
//					    setTimeslider(timelinetemp);
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
	            text: '',
	            subtext: thedate,
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
	                    text1 = '故障数',
	                    text2 = '总数';
	                o.zx = params.data.value;
	                o.zs = params.data.content;
	                if(o.zs!=0){
	                	 o.f = ((o.zx / o.zs) * 100).toFixed() + '%';
	                }else{
	                	 o.f = '0%';
	                }
	                var str = '<p style="color:#fff600;text-align:center;font-size:16px;">' + o.f + '</p>';
	                str += text1 + '：<span style="color:#8aff00;">' + o.zx + '</span><br/>';
	                str += text2 + '：<span style="color:#8aff00;">' + o.zs + '</span>';
	                return str;

	            }
	        },
	        legend: {
	            left:"60px",
	            bottom:"30px",
	            data:legenddata
	        },
	        grid: {
	            left: '40px',
	            right: '40px',
	            bottom: '80px',
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
	        series: maindata
	    };
	    
	      //时间分割线点击事件
//		  $('#slider').delegate('a', 'click', function(event) {
//			  if (charts.check===0) {
//				  $(this).addClass('cur').siblings().removeClass('cur');
//			      var time = parseInt($(this).attr('data-ajax'));
//			      lineLoad(time);
//			      charts.check=1;
//			      $(this).parent().hide();
//			  }else{
//				  $(this).addClass('cur').siblings().removeClass('cur');
//			      var time = parseInt($(this).attr('data-ajax'));
//			      lineLoad(null);
//			      charts.check=0;
//			      $(this).parent().hide();
//			  }
//		     
//		  });
		  
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
		          +'<button type="button" disabled=true class="btn btn-default btn-sm" title="bar"><i class="fa fa-bar-chart"></i></button>';

		       $('#mtool').html(btn);   
		    }

	    //---------线图---------end
		//---------柱图---------statr
		    
	    function barLoad(){
			post(
				barurl,
				'JSON',
				data,
				function(obj){
					if (obj.success===0) {
						var datas=obj.data.barList;
						timelinedata.length = 0;
						barvalue.length = 0;
						for (var i = 0; i < datas.length; i++) {
							var name=datas[i].name;
							timelinedata.push(name.length>3?name.substring(0,2):name);
							barvalue.push({content:datas[i].content,value:datas[i].value,name:datas[i].name});
						}
					}
				});
		}

	    var optionbar = {
    		title: {
                text: '厂商监控',
                subtext: thedate,
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
	                    text1 = '故障数',
	                    text2 = '总数';
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
	            name: '',
	            type: 'category',
	            boundaryGap: false,
	            splitLine: {
	                show: false
	            },
	            data: timelinedata
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
	    function pieLoad(){
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
						setchartData($('#pie1'), datas, "厂商故障状态", "厂商故障状态");
						settextfn($('#pie_title'), datas);
						myChartbingtu.hideLoading();
					}
				});
		}
	  //故障状态饼图    
	    var zxbingtu = document.getElementById("bingbu1");
	    var myChartbingtu = echarts.init(zxbingtu);
	    var btoption = {      
	    		tooltip: {
		            formatter: function(params) {
		                var o = {},
	                    text1 = '故障数',
	                    text2 = '设备数';
		                o.name=params.data.name;
		                o.zx = params.data.value;
		                o.zs = params.data.total;
		                if(o.zs!=0){
		                	 o.f = ((o.zx / o.zs) * 100).toFixed() + '%';
		                }else{
		                	 o.f = '0%';
		                }
		                var str = '<p style="color:#fff600;text-align:center;font-size:16px;">' + o.f + '</p>';
		                str += text1 + '：<span style="color:#8aff00;">' + o.zx + '</span><br/>';
		                str += text2 + '：<span style="color:#8aff00;">' + o.zs + '</span>';
		                return str;

		            }
		        },      
	        series : [
	            {
	                name: '厂商故障状态',
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
	    
	    //---------饼图---------end
	    //---------表格---------start
	    // 初始化表格
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
	 	             { "data": "name","title":"厂商名称"},
	 	             { "data": function(e){
	 	            	 return '<div class="progress progress-xs progress-striped active">'+
	                      '<div class="progress-bar progress-bar-success" style="width: '+e.rate+'%"></div>'+
	                      '</div><span class="badge bg-green">'+e.rate+'%</span>';

	 	             	},"title":"故障率"},
	 	             { "data": "totalNumber","title":"总数"},
	 	             { "data": "faultNumber","title":"故障数"}
	 	             
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
	 				'width' : '10%',
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
	 	       }
	 	    });
	    }
	    //---------表格---------end
	    //---------初始---------statr
	    lineinit();//线图初始
	    //---------初始---------end
	    
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
	            	var sumave=el.totalNumber>0?((el.faultNumber/el.totalNumber).toFixed(2)*100):0;
	                li += '<li class="fx" title=' + el.name+':'+sumave+ '% data-ajax=' + tips + ' data-name=' + el.name + '><em class="c1" style="background-color:' + piecolor[index] + ';"></em>' + el.name + '</li>';
	            });

	        } else {
	            $.each(data, function(index, el) {
	            	var sumave=el.totalNumber>0?((el.faultNumber/el.totalNumber).toFixed(2)*100):0;
	                li += '<li class="fx" title=' + el.name+':'+sumave + '% data-ajax=' + tips + ' data-name=' + el.name + '><em class="c1" style="background-color:' + piecolor[index] + ';"></em>' + el.name + '</li>';
	            });
	        }
	        html = ul +title+ li + uld;
	        o.html(html);
	    }
	    
	    function settextfn(o, data){
	    	var data = data;
	    	var ul='<em class="r"><!--圆点--></em>';
	    	var html = '';
	    	var title='当前全省厂商共计<font color="red">'+data.length+'</font>个, ';
	    	var li='';
	    	$.each(data, function(index, el) {
            	var sumave=el.totalNumber>0?((el.faultNumber/el.totalNumber).toFixed(2)*100):0;
                li += el.name+'故障数:'+el.faultNumber + ',故障率:' + sumave + '%;';
            });
	    	html=ul+title+li;
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
	  	        cutDown(auto_time,flash());
	  	    },60000*auto_time);
	    }
	   //-------------auto-----e
	    
	    //导出
	    $('#maintenanceExport').on('click',function(){
	    	location.href =contextPath+exportUrl+'?oid='+oid;
	    });
});


