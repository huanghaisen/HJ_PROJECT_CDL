
/* kxdtreegride
 *
 *
 */
kxdtreegrid = {};

var kxdgride=null;
var treeObj=null;
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
		  			name:"partTypeName"
		  			
		  		}
		  },
		  async: {
				enable: true,
				type: "post",
				url: contextPath+"/soa/parts/type/datas/search",
				autoParam: ["id"],
				dataFilter: ajaxDataFilter
			},
		  callback: {
		    beforeClick: beforeClick,
		    onClick:zTreeOnClick,
		    onAsyncSuccess: onAsyncSuccess
		  }
	},
};

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
      var $wr = $('<span>').addClass('wr');
      var $s1 = $('<span>').addClass('w1').html(treeNode.statusstr);
      var $s2 = $('<span>').addClass('w1').html(treeNode.partnum);
      var $s3 = $('<span>').addClass('w1').html(treeNode.username);
      var $s4 = $('<span>').addClass('w1').html(treeNode.lastModifiedTime);

      $s1.appendTo($wr);
      $s2.appendTo($wr);
      $s3.appendTo($wr);
      $s4.appendTo($wr);
      $wr.prependTo(aObj);
    };
    
    function beforeClick(treeId, treeNode) {
        return true;
      };
      
	function zTreeOnClick(event, treeId, treeNode) {
	  var $edit = $('#edit');
	  var $del = $('#del');
	  var $status = $('#status');
	  $edit.removeAttr('disabled');
	  $del.removeAttr('disabled');
	  $status.removeAttr('disabled');
	};
	
	function onAsyncSuccess(event, treeId, treeNode, msg){
		  var $edit = $('#edit').attr("disabled",true);
		  var $del = $('#del').attr("disabled",true);
		  var $status = $('#status').attr("disabled",true);
	}
	
	function treeRefresh(node){
	
		treeObj.reAsyncChildNodes(null,"refresh",true);
	    
	}
	
	function treeDelRefresh(node){
		if ((node.getParentNode().children.length-1)===0) {
            treeObj.reAsyncChildNodes(node.getParentNode().getParentNode(),"refresh",true);
        }else{
            treeObj.reAsyncChildNodes(node.getParentNode(),"refresh",true);
        }
	}

/* ----------------------------------
 * - Initialize the kxdtreegrid Object -
 * ----------------------------------
 * All kxdtreegrid functions are implemented below.
 */
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
				content : '请选中父节点添加！'
			});
			d.show();
			setTimeout(function() {
			d.close().remove();
		}, 3000); 
	  }else{
		  var partsTypeId=nodes[0].id;
		  var data={id:partsTypeId};
			post("/soa/parts/type/inst","html",data,function(obj){
				var d = dialog({
					title : '编辑配件类别',
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
   if(nodes==""||nodes[0].id!=1){  //不选节点添加限制和首节点添加限制
	   var d = dialog({
			content : '配件类别不含子类别，请选择监控系统专用类别进行添加！'
		});
		d.show();
		setTimeout(function() {
		d.close().remove();
	}, 3000);
	  }else{
		  var data={pid:nodes[0].id};
		  post("/soa/parts/type/inst","html",data,function(obj){
				var d = dialog({
					title : '创建新配件类别',
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

 //是否有效
 $('#status').on('click',function(){
	 treeObj = $.fn.zTree.getZTreeObj("treeGride");
     var nodes = treeObj.getSelectedNodes();
     if(nodes=="" || nodes[0].id==1){
		  var d = dialog({
	            title: '提示',
	            content:'请选择节点进行修改状态！',
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
	  }else if(nodes[0].partnum>0){
		  var d = dialog({
	            title: '提示',
	            content:'该节点下有数据引用，不容许修改状态！',
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
		var data = {"id":partsTypeId}
     	$.ajax({
     		url:contextPath+"/soa/parts/type/datas/status",    
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


 //删除
 $('#del').on('click',function(){
     var nodes = treeObj.getSelectedNodes();
     if(nodes=="" || nodes[0].id==1){
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
	  }else if(nodes[0].partnum>0){
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
	            content:'确认删除['+nodes[0].partTypeName+']',
	            okValue: '确定',
	            ok: function() {
	            	$.ajax({
	             		url:contextPath+"/soa/parts/type/datas/del",    
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
