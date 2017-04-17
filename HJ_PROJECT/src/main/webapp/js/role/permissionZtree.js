var menuId;
var rmId;
var pZtreeNodes;
var setting = {
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
	  			name:"funcName",
	  			funcSign:"funcSign",
	  			funcCode:"funcCode",
	  			funcIcon:"funcIcon"
	  		}
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		}
	};
function initPermissionTree(){
    	      	$.ajax({
    	      		url:contextPath+"/soa/func/list",    
    	      		type:"post",
    	      		dataType:"json",
    	      		async:false,
    	      		success: function(result){
    	      			var array =result;
    	      			pZtreeNodes=[];
    	      			for(var i =0;i<array.length;i++){
    	      				//pZtreeNodes.push({id:array[i].id,name:array[i].funcName});
    	      				pZtreeNodes.push(array[i]);
    	      			 }
    	      		    } 
    	      		});
    		    console.log(pZtreeNodes);
    	      	zTree=$.fn.zTree.init($("#funTree"), setting, pZtreeNodes);
    	    
    		}
    		
    		function initMenuPermission(menuId){	
    			console.log("-----------"+roldId);
    			var id={"menuId" : menuId,"roleId":roldId};
    	      	$.ajax({
    	      		url:contextPath+"/soa/permission/getAllPmByRMId",
    	      		data:id,
    	      		type:"post",
    	      		dataType:"json",
    	      		async:false,
    	      		success: function(result){
    	      			var flag = result.success;
    	      			rmId=result.msg;
    	      			if(flag==0){
    	      				 var array=result.data;
        	      			 console.log(array);
        	      			for(var i=0;i<array.length;i++){
        	      				var zTree = $.fn.zTree.getZTreeObj("funTree");
        	      				console.log(array[i].funcId);
        	      				var node = zTree.getNodeByParam("id",array[i].funcId);
        	      				zTree.checkNode(node, true, true);
        	      			} 
    	      			  }
    	      		    } 
    	      		});
    		    	    
    		}
    		
    		 function updateMenuPermission(){	
    			var zTree = $.fn.zTree.getZTreeObj("funTree");
    			var nodes = zTree.getCheckedNodes(true);
    			var roleIds =[];
    			for (var i=0, l=nodes.length; i<l; i++) {
    				roleIds[i]=nodes[i].id;
    			} 
    			console.log("rmId="+rmId+",funIds="+roleIds);
    	      	$.ajax({
    	      		url:contextPath+"/soa/permission/roleUpdatePermission",
    	      		data:"rmId="+rmId+"&funIds="+roleIds,
    	      		type:"post",
    	      		dataType:"json",
    	      		async:false,
    	      		success: function(result){
    	      			
    	      			$('#myFuntree').modal('hide');
    	      		}
    	      	});    	      	    
    		} 
    		
function showRoleMenu(id){
        	var data={"id":id};
        	roldId=id;
        	var dataMenu_table = $('#dataMenu_table').DataTable({
 			   //	data:data,
 			   	ajax: {
 		            "url": contextPath+"/soa/role/editRoleMenu",
 		            "type": "POST",
 		       		"data":data
 		        } ,
 		       pageLength : 8,

 	            "language": {
 	                "sProcessing": "正在加载中......",
 	                "sLengthMenu": "每页显示 _MENU_ 条记录",
 	                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
 	                "sZeroRecords": "对不起，查询不到相关数据！",
 	                "sEmptyTable": "表中无数据存在！",
 	                "search": "查询:",
 	                "paginate": {
 	                   "previous": "上一页",
 	                   "next": "下一页",
 	                   "first": "第一页",
 	                   "last": "最后一页"
 	                }
 	             },
		        paging: true,
		        lengthChange: false,
		        searching: false,
		        ordering: true,
		        search: false, //显示搜索框
		        info: true,
		        autoWidth: true,
 			    columns: [
 					{ 
 						title: '序号',
 						width:"5%",
 						data: function (e) {//这里给最后一列返回一个操作列表
                        		return '<input type="checkbox" value="'+e.id+'">';
                     	}
 						
 					},
 					{ 
 						title: '名称',
 						width:"15%",
 						data: 'menuName',
 						align: 'center',
 	                    valign: 'middle'
 						
 					},
 					{ 
 						title: '场景',
 						width:"15%",
 						data: 'menuSence',
 						align: 'center',
 	                    valign: 'middle'
 					}, 
 					{
 						title: '功能',
 						width:"20%",
 						data: function (e) {//这里给最后一列返回一个操作列表
 							//var a = e.roleSence;
                        		return '<a class="btn btn-default btn-xs show-detail-json" onclick=updatePermission('+e.id+') data-whatever='+e.id+'><i class="icon-edit"></i>显示详细</a>';
                     	}
 					}
 			    ]
 			});
        	
        	//dataMenu_table.destroy();        	
        	$('#myModalMenu').modal('show');        	
        	$('#myModalMenu').on('hidden.bs.modal', function (e) {
       			dataMenu_table.destroy();
       		});
        }

//分配菜单事件
function updatePermission(id){
	$('#myModalMenu').modal('hide');
	initPermissionTree();
	initMenuPermission(id);
	$('#myFuntree').modal('show').find('.modal-dialog').removeAttr('style');
}