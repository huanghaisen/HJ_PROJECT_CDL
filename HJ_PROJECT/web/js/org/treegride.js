
/* kxdtreegride
 *
 *
 */
kxdtreegrid = {};

var kxdgride=null;
var treeObj=null;
var showdialog=null;
var map=null;
/* --------------------
 * 
 * --------------------
 * 
 */

kxdtreegrid.options = {
	curMenu : null, 
	zTree_Menu : null,
	 setting : {
		  view: {
		    showLine: false,
		    showIcon: false,
		    selectedMulti: false,
		    dblClickExpand: false,
		    addDiyDom: addDiyDom
		  },
		  data: {
			  simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentOrgId",
					rootPId: 0
				},
		  		key:{
		  			name:"orgName"
		  			
		  		}
		  },
		  async: {
				enable: true,
				type: "post",
				url: contextPath+"/soa/org/datas/search",
				autoParam: ["id"],
				dataFilter: ajaxDataFilter
			},
		  callback: {
		    beforeClick: beforeClick,
		    onClick:zTreeOnClick,
		    onAsyncSuccess: onAsyncSuccess,
		    beforeAsync: zTreeBeforeAsync
		    
		  },
		  
		
	},
};

function showdialogfn(obj){
	showdialog=dialog({
		okValue : obj,
		ok : function() {
			
		}
  	});	
  	showdialog.show();
}

function hiddialog(){
	showdialog.okValue="";
  	showdialog.close().remove();
  	
  }

function addDiyDom(treeId, treeNode) {
    var spaceWidth = 10;
    var switchObj = $("#" + treeNode.tId + "_switch"),
    icoObj = $("#" + treeNode.tId + "_ico");
    
    switchObj.remove();
    icoObj.before(switchObj);

    if (treeNode.level > 0) {
      var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
      switchObj.before(spaceStr);
    }

    addcon(treeId, treeNode);
    
  };
  
  function addcon(treeId, treeNode){
      aObj = $("#" + treeNode.tId + "_a");
      var $wr = $('<span data-x="'+treeNode.mapX+'" data-y="'+treeNode.mapY+'">').addClass('wr');
      var $s1 = $('<span>').addClass('w1').html(treeNode.orgCode);
      var $s2 = $('<span>').addClass('w1').html(treeNode.orgTypeStr);
      var $s3 = $('<span>').addClass('w1').html(treeNode.leaderName);
      var $s4 = $('<span>').addClass('w1').html(treeNode.modifierNmae);
      var $s5 = $('<span>').addClass('w1').html(treeNode.lastModifiedTime);

      $s1.appendTo($wr);
      $s2.appendTo($wr);
      $s3.appendTo($wr);
      $s4.appendTo($wr);
      $s5.appendTo($wr);
      $wr.prependTo(aObj);
    };
    
    function beforeClick(treeId, treeNode) {
        return true;
      };
      
	function zTreeOnClick(event, treeId, treeNode) {
		if(treeNode.getParentNode()!=null && treeNode.getParentNode().children.length>1){
			
			if(treeNode.getIndex()==0){
				$('#up').attr("disabled",true);
			}else{
				$('#up').removeAttr('disabled');
			}
			
			if((treeNode.getParentNode().children.length-treeNode.getIndex())>1){
				$('#down').removeAttr('disabled');
			}else{
				$('#down').attr("disabled",true);
			}
			
		}else{
			$('#down').attr("disabled",true);
			$('#up').attr("disabled",true);
		}
		
		
	  var $edit = $('#edit');
	  var $del = $('#del');
	  $edit.removeAttr('disabled');
	  $del.removeAttr('disabled');
	};
	
	function onAsyncSuccess(event, treeId, treeNode, msg){
		  var $edit = $('#edit').attr("disabled",true);
		  var $del = $('#del').attr("disabled",true);
		  //hiddialog();
	}
	
	function zTreeBeforeAsync(treeId, treeNode) {
		//showdialogfn("刷新数据中。。。");
	}
	
	function treeRefresh(node){
		
		if ((node.getParentNode().children.length-1)===0) {
			treeObj.reAsyncChildNodes(node.getParentNode().getParentNode(),"refresh",true);
		}else{
			treeObj.reAsyncChildNodes(node.getParentNode(),"refresh",true);
		}
	}
	
	
function _init() {
	var jtreeObj = $("#treeGride");
	kxdgride=$.fn.zTree.init(jtreeObj, kxdtreegrid.options.setting,null);
	treeObj = $.fn.zTree.getZTreeObj("treeGride");
	jtreeObj.hover(function () {
      if (!jtreeObj.hasClass("showIcon")) {
    	  jtreeObj.addClass("showIcon");
      }
    }, function() {
    	jtreeObj.removeClass("showIcon");
    });
}

function ajaxDataFilter(treeId, parentNode, responseData) {
    if (responseData.data.length>0) {
    	responseData=responseData.data;
    }
    return responseData;
};

_init();

//修改
$('#edit').on('click',function(){
   treeObj = $.fn.zTree.getZTreeObj("treeGride");
   var nodes = treeObj.getSelectedNodes();
   if(parent==null){
		   var d = dialog({
				content : '选择正确的节点添加！'
			});
			d.show();
			setTimeout(function() {
			d.close().remove();
		}, 3000); 
	  }else{
		  var id=nodes[0].id;
		  var data={id:id};
			post("/soa/org/inst","html",data,function(obj){
				var d = dialog({
					title : '编辑机构',
					content :obj,					
					width : 450,
					okValue: '保存',
					zIndex:1040,
					ok :function() {
						$('#form_action').bootstrapValidator('validate');//弹窗动作在edit页面
						return false;
					},
			     cancelValue:'取消',
				 cancel: function() {
				  }
				});
				d.showModal();
				dialogEdit=d;
				nodeEdit=nodes[0];
			});
	  }
});

var dialogEdit;
var nodeEdit ;
 //添加
 $('#add').on('click',function(){
   treeObj = $.fn.zTree.getZTreeObj("treeGride");
   var nodes = treeObj.getSelectedNodes();
   if(nodes==""){
	   var d = dialog({
			content : '选择正确的节点添加！'
		});
		d.show();
		setTimeout(function() {
		d.close().remove();
	}, 3000);
	  }else{
		  var data={pid:nodes[0].id};
		  post("/soa/org/inst","html",data,function(obj){
				var d = dialog({
					title : '创建新机构',
					content :obj,					
					width : 450,
					okValue: '保存',
					zIndex:1040,
					ok :function() {
						$('#form_action').bootstrapValidator('validate');//弹窗动作在edit页面
						return false;					
 				},
			     cancelValue:'取消',
				 cancel: function() {
				  }
				});
				d.showModal();
				dialogEdit=d;
				nodeEdit=nodes[0];
			});
	  }
});

 //删除
 $('#del').on('click',function(){
     var nodes = treeObj.getSelectedNodes();
     if(nodes==""){
		  var d = dialog({
	            title: '提示',
	            content:'请选择节点删除！',
	            okValue: '确定',
	            ok: function() {
	            	setTimeout(function() {
  						d.close().remove();
  					}, 4000); 
	            },
	            cancelValue: '取消',
	            cancel: function() {}
	         });
	      d.show();
	  }else{
		var partsTypeId=nodes[0].id;
		var data = {"id":partsTypeId};
		 var d = dialog({
	            title: '提示',
	            content:'确认删除['+nodes[0].orgName+']',
	            okValue: '确定',
	            ok: function() {
	            	$.ajax({
	             		url:contextPath+"/soa/org/datas/del",    
	             		data:data,
	             		type:"post",
	             		dataType:"json",
	             		async:false,
	             		success: function(result){
	             			treeRefresh(nodes[0]);
	             			if(result.status==0){
	             				var d = dialog({
	         						content : result.msg
	         					});
	         						d.show();
	         						setTimeout(function() {
	         						d.close().remove();
	         					}, 2000); 
	        					}else{
	        						var d = dialog({
	         						content : result.msg
	         					});
	         						d.show();
	         						setTimeout(function() {
	         						d.close().remove();
	         					}, 2000);
	        					} 
	             		} 
	             	});
	            },
	            cancelValue: '取消',
	            cancel: function() {}
	         });
	      d.show();
	  }
 });
 
 $('#up').on('click',function position(){
	 treeObj = $.fn.zTree.getZTreeObj("treeGride");
     var nodes = treeObj.getSelectedNodes();
     if(nodes==""){
		  var d = dialog({
	            title: '提示',
	            content:'请选择节点进行修改！',
	            okValue: '确定',
	            ok: function() {
	            	setTimeout(function() {
  						d.close().remove();
  					}, 2000); 
	            },
	            cancelValue: '取消',
	            cancel: function() {}
	         });
	      d.show();
	  }else{
		  var id=nodes[0].id;
		  var nid=nodes[0].getPreNode().id;
		var data = {
				"cmd":0,
				"id":id,
				"nid":nid
			};
     	$.ajax({
     		url:contextPath+"/soa/org/datas/position",    
     		data:data,
     		type:"post",
     		dataType:"json",
     		async:false,
     		success: function(result){
     			if(result.status==0){
     				treeRefresh(nodes[0]);
     				var d = dialog({
 						content : result.msg
 					});
 						d.show();
 						setTimeout(function() {
 						d.close().remove();
 					}, 2000); 
					}else{
						var d = dialog({
 						content : result.msg
 					});
 						d.show();
 						setTimeout(function() {
 						d.close().remove();
 					}, 2000);
					} 
     		} 
     	});
	  }
  });
 
 
 $('#down').on('click',function position(){
	 treeObj = $.fn.zTree.getZTreeObj("treeGride");
     var nodes = treeObj.getSelectedNodes();
     if(nodes==""){
		  var d = dialog({
	            title: '提示',
	            content:'请选择节点进行修改！',
	            okValue: '确定',
	            ok: function() {
	            	setTimeout(function() {
  						d.close().remove();
  					}, 2000); 
	            },
	            cancelValue: '取消',
	            cancel: function() {}
	         });
	      d.show();
	  }else{
		  var id=nodes[0].id;
		  var nid=nodes[0].getNextNode().id;
		
		var data = {
				"cmd":1,
				"id":id,
				"nid":nid
			};
     	$.ajax({
     		url:contextPath+"/soa/org/datas/position",    
     		data:data,
     		type:"post",
     		dataType:"json",
     		async:false,
     		success: function(result){
     			if(result.status==0){
     				treeRefresh(nodes[0]);
     				var d = dialog({
 						content : result.msg
 					});
 						d.show();
 						setTimeout(function() {
 						d.close().remove();
 					}, 2000); 
					}else{
						var d = dialog({
 						content : result.msg
 					});
 						d.show();
 						setTimeout(function() {
 						d.close().remove();
 					}, 2000);
					} 
     		} 
     	});
	  }
  });
 $("#map").click(function(){
	 var nodes = treeObj.getSelectedNodes();
     if(nodes==""){
		  var d = dialog({
	            title: '提示',
	            content:'请选择节点标志！',
	            okValue: '确定',
	            ok: function() {
	            	setTimeout(function() {
  						d.close().remove();
  					}, 4000); 
	            },
	            cancelValue: '取消',
	            cancel: function() {}
	         });
	      d.show();
	  }else{
		  var d = dialog({
			  title: '提示',
			  width:1000,
		  	  height:400,
			  content:'<div id="orgMap" style="width:100%;height:100%;">要展示的地图<div id="editpanel" style="width: 260px; height: 220px; position: absolute; border: 1px solid rgb(204, 204, 204); background-color: rgb(255, 255, 255); border-radius: 6px; z-index: 2000; top: 2px; left: 2px;">'
			  +'  <form class="form-horizontal"  method="post" name="form_action" id="form_action" action="">'
			  +' <input type="hidden" value="" id="orgid" name="orgid">' 
			  +'	<div class="form-group" style="margin-left: 20px; margin-top:10px;  height: 30px;  line-height: 30px;  padding-bottom: 10px;  color: #666;">'
				+'			<button type="button" id="markbtn_add" onclick="map.addMarker()" style="display: none;" class="btn btn-success btn-xs">添加坐标</button>'
				+'			<button type="button" id="markbtn_edit" onclick="map.editMarker()" style="display: none;" class="btn btn-success btn-xs">编辑坐标</button>'
				+'			<button type="button" id="markbtn_save" onclick="map.saveMarker()" style="display: inline-block;" class="btn btn-success btn-xs">确认坐标</button>'
				+'			<button type="button" id="markbtn_rest" onclick="map.restMarker()" class="btn btn-success btn-xs">还原坐标</button>'
				+'	</div>'
			  +'   <div class="form-group">'
			  +'    <label for="inputPassword3" class="col-sm-4 control-label">机构名称</label>'
			  +'    <div class="col-sm-7">'
			  +'   <input type="text" class="form-control" id="orgName" name="orgName" value="">'
			  +'  </div>'
			  +'  </div>    '  
			  +'  <div class="form-group">'
			  +'	<label for="inputPassword3" class="col-sm-4 control-label">经度</label>'
			  +'    <div class="col-sm-7">'
			  +'    <input type="text" class="form-control" id="mapX" name="mapX" value="">'
			  +' </div>'
			  +' </div>    ' 
			  +'  <div class="form-group">'
			  +' <label for="inputPassword3" class="col-sm-4 control-label">纬度</label>'
			  +'   <div class="col-sm-7">'
			  +'   <input type="text" class="form-control" id="mapY" name="mapY" value="">'
			  +'  </div> '     
			  +'  </div>  '
			  +' </form>'
			  +' </div>',
			  okValue: '确定',
			  zIndex:1050,
			  drag : true,
			  ok: function() {
				  var x=$("#mapX").val();
				  var y=$("#mapY").val();
				  var data={orgId:nodes[0].id,mapX:x,mapY:y};
				  var url = '/soa/org/datas/updateById';
				  $.ajax({
			           	url:contextPath+url,  
			           	data:data,
			           	type:"post",
			           	dataType:"json",
			           	async:true,
			           	success: function(result){ 
			           	 var msg = dialog({
			           		 title: '提示',
			           		 content:result.msg
			           	 });
			           	 msg.show();
			           	treeRefresh(nodes[0]);
			           	setTimeout(function() {
			           		msg.close().remove();
							d.close().remove();
						  }, 4000); 
			           	} 
			        });
			  },
			  cancelValue: '取消',
			  cancel: function() {}
		  });
		  d.show();
		var partsTypeId=nodes[0].id;
		var orgName=nodes[0].orgName;
		$('#orgid').val(partsTypeId);
		$('#orgName').val(orgName);
		var mapX=$('#'+nodes[0].tId+'').find('span').attr('data-x');
		var mapY=$('#'+nodes[0].tId+'').find('span').attr('data-y');
		var options = {mainChartDiv:'orgMap',city:orgName,orgid:partsTypeId,url:'/soa/org/datas/updateById',center:[mapY,mapX],orgtitle:orgName};
		map = new Kxdamap(options);
		map.onLoad();
		//kxdamap.mapClickListener();
	  }
 });
 
 
 
 
 
