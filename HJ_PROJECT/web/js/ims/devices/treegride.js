
/* kxdtreegride
 *
 *
 */
kxdtreegrid = {};

var kxdgride=null;
var treeObj=null;
var showdialog=null;
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
					pIdKey: "parentId",
					rootPId: 0
				},
		  		key:{
		  			name:"deviceModelName"
		  			
		  		}
		  },
		  async: {
				enable: true,
				type: "post",
				url: contextPath+"/soa/device/model/datas/search",
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
	  var statusStr=" ";
	  var partnum = " ";
	  var devicenum = "";
	  if(!treeNode.isParent){
		  statusStr = treeNode.statusStr;
		  partnum = treeNode.partnum;
		  devicenum = treeNode.devicenum;
	  }
      aObj = $("#" + treeNode.tId + "_a");
      var $wr = $('<span>').addClass('wr');
      var $s1 = $('<span>').addClass('w1').html(treeNode.typename);
      var $s3 = $('<span>').addClass('w1').html(statusStr);
      var $s4 = $('<span>').addClass('w1').html(partnum);
      var $s5 = $('<span>').addClass('w1').html(devicenum);

      $s1.appendTo($wr);
      $s3.appendTo($wr);
      $s4.appendTo($wr);
      $s5.appendTo($wr);
      $wr.prependTo(aObj);
    };
    
    function beforeClick(treeId, treeNode) {
        return true;
      };
      
	function zTreeOnClick(event, treeId, treeNode) {
	  var $edit = $('#edit');
	  var $del = $('#del');
	  var $status = $('#status');
	  var $addParts=$('#addParts');
	  var $partsInfo=$('#partsInfo'); 
	  $edit.removeAttr('disabled');
	  $del.removeAttr('disabled');
	  $status.removeAttr('disabled');
	  $addParts.removeAttr('disabled');
	  $partsInfo.removeAttr('disabled');
	};
	
	function onAsyncSuccess(event, treeId, treeNode, msg){
		  var $edit = $('#edit').attr("disabled",true);
		  var $del = $('#del').attr("disabled",true);
		  var $status = $('#status').attr("disabled",true);
		  hiddialog();
	}
	
	function zTreeBeforeAsync(treeId, treeNode) {
		showdialogfn("刷新数据中。。。");
	}
	
	function treeRefresh(node){
		//alert(node.getParentNode().tId);
		if(node.getParentNode()==null || node.getParentNode()==''){
			treeObj.reAsyncChildNodes(node,"refresh",true);
		}else{
			//alert((node.getParentNode().children.length-1)===0);
			if ((node.getParentNode().children.length-1)===0) {
				treeObj.reAsyncChildNodes(node.getParentNode().getParentNode(),"refresh",true);
			}else{
				treeObj.reAsyncChildNodes(node.getParentNode(),"refresh",true);
			}
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
				content : '选择正确的节点编辑！'
			});
			d.show();
			setTimeout(function() {
			d.close().remove();
		}, 3000); 
	  }else{
		  var id=nodes[0].id;
		  var data={id:id};
			post("/soa/device/model/inst","html",data,function(obj){
				var d = dialog({
					title : '编辑设备型号',
					content :obj,					
					width : 450,
					okValue: '保存',
					zIndex:1040,
					ok :function() {
						this.title('正在提交..');
						ajaxSubmit($('#form_part')[0], function(data){
							if(data.status==0){
								treeRefresh(nodes[0]);
								var d = dialog({
		    						content : data.msg
		    					});
		    						d.show();
		    						setTimeout(function() {
		    						d.close().remove();
		    					}, 4000); 
							}else{
								var d = dialog({
		    						content : data.msg
		    					});
		    						d.show();
		    						setTimeout(function() {
		    						d.close().remove();
		    					}, 4000);
							} 
						});
					 	cancel: false
					},
			     cancelValue:'取消',
				 cancel: function() {
				  }
				});
				d.showModal();
			});
	  }
});


 //添加
 $('#add').on('click',function(){
   treeObj = $.fn.zTree.getZTreeObj("treeGride");
   var nodes = treeObj.getSelectedNodes();
   if(nodes=="" || nodes[0].depath===3){
	   var d = dialog({
			content : '选择正确的节点添加！'
		});
		d.show();
		setTimeout(function() {
		d.close().remove();
	}, 3000);
	  }else{
		  var data={pid:nodes[0].id};
		  post("/soa/device/model/inst","html",data,function(obj){
				var d = dialog({
					title : '新增设备型号',
					content :obj,
					width : 450,
					okValue: '保存',
					zIndex:1040,
					ok :function() {
 					this.title('正在提交..');
 					ajaxSubmit($('#form_part')[0], function(data){
 						if(data.status==0){
 							treeRefresh(nodes[0]);
							var d = dialog({
	    						content : '添加成功！'
	    					});
	    						d.show();
	    						setTimeout(function() {
	    						d.close().remove();
	    					}, 2000); 
	    					//table.ajax.reload(null,false);//刷新数据
						}else{
							var d = dialog({
	    						content : '添加失败！'
	    					});
	    						d.show();
	    						setTimeout(function() {
	    						d.close().remove();
	    					}, 2000);
						} 
 					});
 				 	cancel: false;
 				},
			     cancelValue:'取消',
				 cancel: function() {
				  }
				});
				d.showModal();
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
	  }else if(nodes[0].partnum>0 || nodes[0].devicenum>0){
			 var d = dialog({
				 title: '提示',
				 content:'该节点下有数据引用，不容许删除！',
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
	            content:'确认删除['+nodes[0].deviceModelName+']',
	            okValue: '确定',
	            ok: function() {
	            	$.ajax({
	             		url:contextPath+"/soa/device/model/datas/del",    
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
 //是否有效
 $('#status').on('click',function(){
	 var nodes = treeObj.getSelectedNodes();
	 if(nodes==""){
		 var d = dialog({
			 title: '提示',
			 content:'请选择节点！',
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
	 }else if(nodes[0].partnum>0 || nodes[0].devicenum>0 || nodes[0].statusStr==''){
		 var d = dialog({
			 title: '提示',
			 content:'该节点下有数据引用，不容许失效！',
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
		 var id = nodes[0].id;
		 var status=nodes[0].statusStr;
		 var data = {"status":status,"id":id};
		 var d = dialog({
			 title: '提示',
			 content:'确认修改['+nodes[0].deviceModelName+']的有效性',
			 okValue: '确定',
			 ok: function() {
				 $.ajax({
					 url:contextPath+"/soa/device/model/datas/updateStatue",    
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
			 },
			 cancelValue: '取消',
			 cancel: function() {}
		 });
		 d.show();
	 }
 });
 
 
 
 
 
 
 
