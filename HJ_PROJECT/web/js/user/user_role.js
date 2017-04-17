//---------------------------------------用户角色模块（权限permission）---------------------------------------------
	//树形显示
	var setting = {
			check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}
		};
	var zTree;
	var zNodes;
	var userId;
		function initTree(sence){
	      	$.ajax({
	      		url:contextPath+"/soa/role/datas/search",
	      		data:{"sence":sence},
	      		type:"post",
	      		dataType:"json",
	      		async:false,
	      		success: function(result){
	      			var array=result.data;
	      			zNodes=[];
	      			for(var i=0;i<array.length;i++){
	      				zNodes.push({id:array[i].id,pid:0,name:array[i].roleDesc});
	      			}
	      		    } 
	      		});
	      	
		    //console.log(zNodes);
	      	zTree=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	    
		}
		function initUserRole(id){	
			var id={"id" : id};
	      	$.ajax({
	      		url:contextPath+"/soa/userRole/datas/show",
	      		data:id,
	      		type:"post",
	      		dataType:"json",
	      		async:false,
	      		success: function(result){
	      			var array=result.data;
	      			for(var i=0;i<array.length;i++){
	      				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	      				var node = zTree.getNodeByParam("id",array[i].roleId);
	      				if(node!=null){
	      					console.log("---------"+node.name);
	      					zTree.checkNode(node, true, true);
	      				}
	      			}
	      		    } 
	      		});
		    	    
		}
		
		 function updateUserRole(){	
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = zTree.getCheckedNodes(true);
			var roleIds =[];
			for (var i=0, l=nodes.length; i<l; i++) {
				roleIds[i]=nodes[i].id;
			} 
	      	$.ajax({
	      		url:contextPath+"/soa/userRole/datas/update",
	      		data:"userId="+userId+"&roleIds="+roleIds,
	      		type:"post",
	      		dataType:"json",
	      		async:false,
	      		success: function(result){
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

		function showMenu(id) {
			zTree=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree.refresh();
			userId=id;
			initUserRole(id);
			$('#myModaltree').modal('show');
			
		}