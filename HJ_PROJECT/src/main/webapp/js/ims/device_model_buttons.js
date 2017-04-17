//按钮工具栏组件需要的相关属性以及样式属性

//来源2.后台请求控制器查库获取
var toolbuttonsdata = $.ajax({
	   url: contextPath+"/soa/device/model/datas/getButtonsData",
	   async: false
	  }).responseText;

//这里我们选用控制器模拟取库的数据赋给按钮组件
toolbuttonsdata = eval(toolbuttonsdata);
//alert(toolbuttonsdata);

//工具按钮事件表
var buttonsAction = {
		'inst' : function(e, dt, node, conf){
			post("/soa/device/model/inst","html","",function(obj){
				//console.log(obj);
				var d = dialog({
					title : '新增设备型号',
					content :obj.msg,					
					width : 550,
					height : 500,
					okValue: '保存',
					zIndex:999999,
					ok : 
						function() {
						var window = this;
						this.title('正在提交..');
						ajaxSubmit($('#form_part')[0], function(data){
				        });
						
						setTimeout(function() {
							DTrefresh();
							window.close().remove();
						}, 100);
						return false;
					 cancel: false
					},
			     cancelValue:'取消',
				 cancel: function() {
				  }
				});
				d.showModal();
			});
			},
		
	'edit' : function(e, dt, node, conf){
		
		var $table = table.table().node();
		var $chkbox_all = $('tbody input[type="checkbox"]', $table);
		var $chkbox_checked = $('tbody input[type="checkbox"]:checked', $table);
		if($chkbox_checked.length<1){
			var d = dialog({
				title:'提示',
				content : '请勾选要修改的记录！'
			});
			d.show();
			setTimeout(function() {
				d.close().remove();
			}, 2000);
		}else
		if($chkbox_checked.length>1){
			var d = dialog({
				title:'提示',
				content : '请不要勾选多条记录修改！'
			});
			d.show();
			setTimeout(function() {
				d.close().remove();
			}, 2000);
		}else{
			var data={"id":$chkbox_checked.val()};
			var id=$chkbox_checked.val();
			post("/soa/parts/type/edit","html",data,function(obj){
				//console.log(obj);
				var d = dialog({
					title : '修改设备信息',
					content :obj.msg,					
					width : 550,
					height : 500,
					okValue: '保存',
					zIndex:999999,
					ok : 
						function() {
						var that = this;
						this.title('正在提交..');
						ajaxSubmit($('#form_part')[0], function(data){
				            //alert(data);
				        });
						
						
						 //updateUser(id);
						setTimeout(function() {
							DTrefresh();
							//table.ajax.reload(null,false);//刷新datatable页面  table对应建表的var 命名
							that.close().remove();
						}, 100);
						return false;
					 cancel: false
					},
			     cancelValue:'取消',
				 cancel: function() {
					 
				  }
				});
				d.showModal();
			});
		}
		
		},
		
		'del' : function(e, dt, node, conf) {
			var $table = table.table().node();
			var $chkbox_all = $('tbody input[type="checkbox"]', $table);
			var $chkbox_checked = $('tbody input[type="checkbox"]:checked', $table);
			
			var data={"id":$chkbox_checked.val()};
			var id=$chkbox_checked.val();
			
			if($chkbox_checked.length<1){
			var t = dialog({
				title:'提示',
				content : '请勾选要删除的记录！'
			});
			t.show();
			setTimeout(function() {
				t.close().remove();
			}, 2000);
		    }else if($chkbox_checked.length>1){
				var t = dialog({
					title:'提示',
					content : '暂无删除多条记录权限'
				});
				t.show();
				setTimeout(function() {
					t.close().remove();
				}, 2000);
			    }else{
				//alert(id);//测试可拿到id
				var d = dialog({
	                 title:'提示',
	                 content:'确认删除id为：'+id+' 的这条记录？',
	                 okValue:'确认',
	                 ok:function(){
	                	 var that=this;
	                	 var url='/soa/device/info/datas/del';
	                	 delObject(id,url);
	                	setTimeout(function() {
							table.ajax.reload(null,false);
							that.close().remove();
						}, 100);
	                	
	                 },
	                 cancelValue:'取消',
	                 cancel:function(){
	                	 
	                 }
					
				});
				d.showModal();	
		    }
		}
}

//按钮属性事件 数据
var buttonsActiondata = madeToolbuttons(toolbuttonsdata, buttonsAction);



