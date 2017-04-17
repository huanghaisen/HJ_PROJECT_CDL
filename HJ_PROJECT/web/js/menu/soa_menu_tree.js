var setting = {
      view: {
    	 addDiyDom: FirstIco,
         addHoverDom: addHoverDom,
         removeHoverDom: removeHoverDom,
         selectedMulti: false
      },
      edit: {
         enable: true,
         editNameSelectAll: true
      },
      data: {
         simpleData: {
            enable: true
         }
      },
      callback: {
         beforeDrag: beforeDrag,
         beforeEditName: beforeEditName,
         beforeRemove: beforeRemove
      }
   };
           
   var className = "dark";
   
   function beforeDrag(treeId, treeNodes) {
      return false;
   }
      
   
   //增加菜单 鼠标上去 的自定义按钮 和按钮事件
   
   //---------------添加事件-----------------//
  function addHoverDom(treeId, treeNode) {
	   beforeAddNode(treeId,treeNode);//添加
     sortUpBtnfn(treeId, treeNode);//升序
     sortDownBtnfn(treeId, treeNode);// 降序 
  };
  
  //菜单屋子图标
  function FirstIco(){
	  $("#treeDemo_1_ico").css({background:"url("+contextPath+"/lib/plugins/zTree3/css/zTreeStyle/img/diy/1_open.png) 0 0 no-repeat"});
  }
   
  
   //---------------修改菜单-----------------//
   function beforeEditName(treeId, treeNode) {
      className = (className === "dark" ? "":"dark");
      var zTree = $.fn.zTree.getZTreeObj("treeDemo");
      zTree.selectNode(treeNode);
      
      var data={"id":treeNode.id};
		post("/soa/menu/edit","html",data,function(obj){
			var d = dialog({
				title : '修改菜单',
				content :obj,					
				width : 550,
				height : 500,
				okValue: '保存',
				zIndex:1050,
				ok:function() {
					this.title('正在提交..');
					ajaxSubmit($('#form_menu')[0], function(data){
						if(data.status==0){
							var d = dialog({
		    					content : data.msg
		    				});
		    					d.show();
		    					setTimeout(function() {
		    					d.close().remove();
		    				}, 4000); 
		    				Refresh();//刷新缓存
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
			 	cancel: function(){
			  	}
			});
			d.showModal();
		});
      
      return false;
      
   }
   
   
   
  //---------------删除菜单-----------------//
   function beforeRemove(treeId, treeNode) {
      className = (className === "dark" ? "":"dark");
      var zTree = $.fn.zTree.getZTreeObj("treeDemo");
      zTree.selectNode(treeNode); 
      var pid;
      var sNodes = zTree.getSelectedNodes();
      if (sNodes.length > 0) {
      	var node = sNodes[0].getParentNode();
      	if(node==null){
      		pid=0;
      	}else{
      		pid=node.id;
      	}      	
      }
      var d = dialog({
          title:'提示',
          content:'确认删除id为：'+treeNode.id+' 的这条记录？',
          okValue:'确认',
          ok:function(){
        	var stat=false;
          	$.ajax({
          		async: false,
          		url:contextPath+"/soa/menu/datas/del",
          		data: {id:treeNode.id,pid:pid,order:treeNode.seq,tier:treeNode.tier,code:treeNode.code,sence:$("#menuType").val()},
          		type:"post",
          		dataType:"json",
          		success: function(result){
          			if(result.status==0){
          				var d = dialog({
              				content : result.msg
              			});
              			d.show();
              			setTimeout(function() {
              				d.close().remove();
              			}, 4000);
              			stat=true;
          			}else{
          				var d = dialog({
              				content : result.msg
              			});
              			d.show();
              			setTimeout(function() {
              				d.close().remove();
              			}, 4000);
              			stat=false;
          			}
          		},
          		});
          	
          	if(stat == true){
          		zTree.removeNode(treeNode);
          	}
          	       	
          },
          cancelValue:'取消',
          cancel:function(){}
			
		});
		d.showModal(); 
		return false;
   }
  
 	//---------------添加菜单-----------------//
   function beforeAddNode(treeId,treeNode){
	   var sObj = $("#" + treeNode.tId + "_span");
	      if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	      //第一个不能添加
	      if(treeNode.tId!="treeDemo_1"){
	    	  //第三级菜单不能再添加子菜单
	    	 if(parseInt(treeNode.tier) != 3){
	    		  var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
	              + "' title='add node' onfocus='this.blur();'></span>";   
	           sObj.after(addStr);
	           var btn = $("#addBtn_"+treeNode.tId);
	           if (btn) btn.bind("click", function(){
	              var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	              zTree.selectNode(treeNode);
	              
	              var data={"parentId":treeNode.id,"tier":treeNode.tier,"code":treeNode.code,"sence":$("#menuType").val()};
	              post("/soa/menu/inst","html",data,function(obj){
	      			var d = dialog({
	      				title : '添加菜单',
	      				content :obj,					
	      				width : 550,
	      				height : 500,
	      				okValue: '保存',
	      				zIndex:1050,
	      				ok:function() {
	      					this.title('正在提交..');
	      					ajaxSubmit($('#form_menu')[0], function(data){
	      						if(data.status==0){
	     							var d = dialog({
	     	    						content : data.msg
	     	    					});
	     	    						d.show();
	     	    						setTimeout(function() {
	     	    						d.close().remove();
	     	    					}, 4000); 
	     	    					Refresh();//刷新缓存
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
	      			 	cancel: function(){
	      			  	}
	      			});
	      			d.showModal();
	      		});
	              
	              
	              return false;
	           });
	      	}	    	 
	      }
   }

 	
   //升序
    //---------------菜单升序-----------------//
   function sortUpBtnfn(treeId, treeNode) {

      var $sObj = $("#" + treeNode.tId + "_span");

      if ($("#sortUpBtn_"+treeNode.tId).length>0) return; 

      if(!treeNode.isFirstNode){
    	  if(treeNode.tId!="treeDemo_2"){
      var sortUpStr = "<span class='button sortUp' id='sortUpBtn_" + treeNode.tId
         + "' title='sort up' onfocus='this.blur();'></span>";

        $(sortUpStr).appendTo($sObj.parent());       
         
         var upbtn = $("#sortUpBtn_"+treeNode.tId);

         if (upbtn) upbtn.bind("click", function(){
        	//获取父节点
        	 var zTree = $.fn.zTree.getZTreeObj("treeDemo");
             zTree.selectNode(treeNode); 
        	 
        	
             var pid;
             var sNodes = zTree.getSelectedNodes();
             if (sNodes.length > 0) {
             	var node = sNodes[0].getParentNode();
             	if(node==null){
             		pid=0;
             	}else{
             		pid=node.id;
             	}
             	
             }
             
             $.ajax({
           		async: false,
           		url:contextPath+"/soa/menu/asc",
           		data: {id:treeNode.id,parent_id:pid,order:treeNode.seq,sence:$("#menuType").val()},
           		type:"post",
           		dataType:"json",
           		success: function(result){
           			if(result.status==0){
           				var d = dialog({
               				content : result.msg
               			});
               			d.show();
               			setTimeout(function() {
               				d.close().remove();
               			}, 4000);
               			Refresh();//刷新缓存
           			}else{
           				var d = dialog({
               				content : result.msg
               			});
               			d.show();
               			setTimeout(function() {
               				d.close().remove();
               			}, 4000);
           			}
           			
           		},
           		error:function(){
           		} 
           		});
            return false;
                    
         });

      }   }

   }

   
   
   
   //降序
   //---------------菜单降序-----------------//
   function sortDownBtnfn(treeId, treeNode) {

      var $sObj = $("#" + treeNode.tId + "_span");

      if ($("#sortDownBtn_"+treeNode.tId).length>0) return; 

      if(!treeNode.isLastNode){
    	  if(treeNode.tId!="treeDemo_1"){
         var sortDownStr = "<span class='button sortDown' id='sortDownBtn_" + treeNode.tId
            + "' title='sort up' onfocus='this.blur();'></span>";

         $(sortDownStr).appendTo($sObj.parent());       
         
         var downbtn = $("#sortDownBtn_"+treeNode.tId);

         if (downbtn) downbtn.bind("click", function(){
        	
        	//获取父节点
        	 var zTree = $.fn.zTree.getZTreeObj("treeDemo");
             zTree.selectNode(treeNode); 
        	 
        	
             var pid;
             var sNodes = zTree.getSelectedNodes();
             if (sNodes.length > 0) {
              	var node = sNodes[0].getParentNode();
              	if(node==null){
              		pid=0;
              	}else{
              		pid=node.id;
              	}
              	
              }
             
             $.ajax({
            		async: false,
            		url:contextPath+"/soa/menu/desc",
            		data: {id:treeNode.id,parent_id:pid,order:treeNode.seq,sence:$("#menuType").val()},
            		type:"post",
            		dataType:"json",
            		success: function(result){
            			if(result.status==0){
               				var d = dialog({
                   				content : result.msg
                   			});
                   			d.show();
                   			setTimeout(function() {
                   				d.close().remove();
                   			}, 4000);
                   			Refresh();//刷新缓存
               			}else{
               				var d = dialog({
                   				content : result.msg
                   			});
                   			d.show();
                   			setTimeout(function() {
                   				d.close().remove();
                   			}, 4000);
               			}
            		},
            		error:function(){
            		} 
            		});
            return false; 

         });
      }
      }

   }

   
 //---------------添加父菜单-----------------//
/* 
 function addParentMenu(){
	 var data={"parentId":0,"tier":0,"code":100000,"sence":$("#menuType").val()};
     post("/soa/menu/inst","html",data,function(obj){
			var d = dialog({
				title : '添加菜单',
				content :obj,					
				width : 550,
				height : 500,
				okValue: '保存',
				zIndex:1050,
				ok:function() {
					this.title('正在提交..');
					ajaxSubmit($('#form_menu')[0], function(data){
						if(data.status==0){
						var d = dialog({
    						content : '添加成功！'
    					});
    						d.show();
    						setTimeout(function() {
    						d.close().remove();
    					}, 2000); 
    					Refresh();//刷新缓存
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
				 	cancel: false
				},
		     	cancelValue:'取消',
			 	cancel: function(){
			  	}
			});
			d.showModal();
		});
 }
*/
 
   function removeHoverDom(treeId, treeNode) {
      $("#addBtn_"+treeNode.tId).unbind().remove();
      $("#sortUpBtn_"+treeNode.tId).unbind().remove();
      $("#sortDownBtn_"+treeNode.tId).unbind().remove();
   };

   
   function selectAll() {
      var zTree = $.fn.zTree.getZTreeObj("treeDemo");
      zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
   }
   
   
   
   
   
   function refresh() {
	   var sence = $("#menuType").val();
		
		$.ajax({
      		url:contextPath+"/soa/manage/datas/menurefresh",
      		data:{"sence":sence},
      		type:"post",
      		dataType:"json",
      		success: function(result){
      			initZtree(sence);
      		    } 
      		});
	} 
   
   
   //加载树
   function initZtree(sence){
	   var zNodes="";
	   
		$.ajaxSetup({ 
  			async:false
  		});
      	$.ajax({
      		url:contextPath+"/soa/menu/datas/search",
      		data:{"sence":sence},
      		type:"post",
      		dataType:"json",
      		success: function(result){
      			zNodes=result.data;
      		    } 
      		});
      	$.ajaxSetup({ 
  			async:true
  		});
	    
     $.fn.zTree.init($("#treeDemo"), setting, zNodes);
     $("#selectAll").bind("click", selectAll);
   }
   
   
   