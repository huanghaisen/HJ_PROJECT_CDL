var zTree;
var zNodes;
var releaseId;
//树形显示开始
    	var settingrelease = {
    			check: {
    				enable: true,
    				chkboxType: {"Y":"p", "N":"ps"}
    			},
    			view: {
    				dblClickExpand: false
    			},
    			    			
    			data: {
    				simpleData: {
    					enable: true,
    					idKey: "id",
    					pIdKey: "parentOrgId",
    					rootPId: 0
    				},
    		  		key:{
    		  			name:"orgBriefName",
    		  			status:"status",
    		  			orgType:"orgType"
    		  		}
    			},
    			callback: {
    				beforeClick: beforeClick,
    				onCheck: onCheck
    			}
    		};
    	
    		function initOrgTree(id){
    			releaseId=id;
    	      	$.ajax({
    	      		url:contextPath+"/soa/org/datas/search",    
    	      		data:"",
    	      		type:"post",
    	      		dataType:"json",
    	      		async:false,
    	      		success: function(result){
    	      			zNodes=result.data;
    	      		    } 
    	      		});
    	      	zTree=$.fn.zTree.init($("#treeDemo"), settingrelease, zNodes);
    	    
    		}
    		 function updateReleaseArea(){	
    			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    			var nodes = zTree.getCheckedNodes(true);
    			var orgIds =[];
    			var num=0;
    			for (var i=0, l=nodes.length; i<l; i++) {
    				if(nodes[i].orgType>0){
    					orgIds[num]=nodes[i].id;
    					num++;
    				}
    			} 
    	      	$.ajax({
    	      		url:contextPath+"/soa/driver/version/datas/saveReleaseArea",
    	      		data:"releaseId="+releaseId+"&orgIds="+orgIds,
    	      		type:"post",
    	      		dataType:"json",
    	      		async:false,
    	      		success: function(result){
    	      			table.ajax.reload();
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
    	//树形显示结束
		
