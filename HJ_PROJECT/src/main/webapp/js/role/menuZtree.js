var zTree;
var zNodes;
var roldId;
//树形显示开始
    	var settingmenu = {
    			check: {
    				enable: true,
    				chkboxType: {"Y":"p", "N":"p"}
    			},
    			view: {
    				dblClickExpand: false
    			},
    			    			
    			data: {
    				simpleData: {
    					enable: true,
    					idKey: "id",
    					pIdKey: "parentId",
    					rootPId: 0
    				},
    		  		key:{
    		  			name:"menuName",
    		  			menuUrl:"menuUrl",
    		  			isopen:"menuIsOpen"
    		  		}
    			},
    			callback: {
    				beforeClick: beforeClick,
    				onCheck: onCheck
    			}
    		};
    	
    		function initMenuTree(id){
    			var roleSence = {"sence":id}
    	      	$.ajax({
    	      		url:contextPath+"/soa/menu/datas/search2",    
    	      		data:roleSence,
    	      		type:"post",
    	      		dataType:"json",
    	      		async:false,
    	      		success: function(result){
    	      			zNodes=result.data;
    	      		    } 
    	      		});
    	      	zTree=$.fn.zTree.init($("#treeDemo"), settingmenu, zNodes);
    	    
    		}
    		
    		function initRolePermission(id){	
    			var id={"roleId" : id};
    	      	$.ajax({
    	      		url:contextPath+"/soa/role/datas/roleFindMenu",
    	      		data:id,
    	      		type:"post",
    	      		dataType:"json",
    	      		async:false,
    	      		success: function(result){
    	      			 var array=result.data;
    	      			for(var i=0;i<array.length;i++){
    	      				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    	      				var node = zTree.getNodeByParam("id",array[i].menuId);
    	      				if (node!=null) {
    	      					zTree.checkNode(node, true, true);
							}
    	      			} 
    	      		    } 
    	      		});
    		    	    
    		}
    		
    		 function updateRolePermission(){	
    			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    			var nodes = zTree.getCheckedNodes(true);
    			var roleIds =[];
    			for (var i=0, l=nodes.length; i<l; i++) {
    				roleIds[i]=nodes[i].id;
    			} 
    	      	$.ajax({
    	      		url:contextPath+"/soa/role/datas/roleUpdateMenu",
    	      		data:"roleId="+roldId+"&menuIds="+roleIds,
    	      		type:"post",
    	      		dataType:"json",
    	      		async:false,
    	      		success: function(result){
    	      			menuRefresh();
    	      			$('#myModaltree').modal('hide');
    	      		}
    	      		});
    		    	    
    		} 
    		

    		function beforeClick(treeId, treeNode) {
    			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    			zTree.checkNode(treeNode, !treeNode.checked, null, true);
    			return false;
    		}
    		
    		function onCheck(e, treeId, treeNode) {
    			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    			nodes = zTree.getCheckedNodes(true);
    		}
    		$( "#myModaltree" ).find('.modal-dialog').draggable();
    		function showMenu(id,roleSence) {
    			/* zTree=$.fn.zTree.init($("#treeDemo"), settingmenu, zNodes);
    			zTree.refresh(); */
    			initMenuTree(roleSence);
    			roldId=id;
    			initRolePermission(id);
    			$('#myModaltree').modal('show').find('.modal-dialog').removeAttr('style');
    			
    		}
    	//树形显示结束
		
