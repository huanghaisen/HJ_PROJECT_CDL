 

function addWorkTree(obj,nodes,flag,fn){
     var treeObj = obj;
     var zNodes =  nodes; 
     var flag=flag;
     var setting = {
      view: {
        showLine: false,
        showIcon: false,
        selectedMulti: false,
        dblClickExpand: false,
        addDiyDom: addDiyDom
      },
      data: {
        simpleData: {
          enable: true
        }
      },
      callback: {
        beforeClick: beforeClick,
        onClick: zTreeOnClick
      }
    };
    
    
    $.ajax({
    type:"post",
    data:"json",
    url:contextPath+"/soa/org/datas/search",
    async:false,
    success:function (result){
      //console.log(result.data);
      var result2=result.data;
      var newResult=[];
      for(var i=0;i<result2.length;i++){
        newResult.push({"id":result2[i].id,"pId":result2[i].parentOrgId,"name":result2[i].orgName,"depath":result2[i].depath});
        }
       zNodes=newResult;    
      }
      
    });
 
    function addDiyDom(treeId, treeNode) {
      var spaceWidth = 10;
      var switchObj = $("#" + treeNode.tId + "_switch"),
      icoObj = $("#" + treeNode.tId + "_ico");
      //console.log(treeNode.tId)     
      switchObj.remove();
      icoObj.before(switchObj);

      if (treeNode.level > 0) {
        var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
        switchObj.before(spaceStr);
      } 
      
    }

    function beforeClick(treeId, treeNode) {
      // if (treeNode.level == 0 ) {
      //   var zTree = $.fn.zTree.getZTreeObj("treeDemo");
      //   zTree.expandNode(treeNode);
      //   return false;
      // }
      return true;
    }
    
    function zTreeOnClick(event, treeId, treeNode) {
      //$('#orgTreeInput').val(treeNode.name).attr('data_id',treeNode.id).attr('parent',treeNode.pId).attr('depath',treeNode.depath);
     
      //flag 是开启全机构都可选择开关  1 true是开  0  false是关，关闭时只能选中营业厅
      if(flag!=0){
    	treeObj.parent().parent().find('>input').val(treeNode.name).attr('data_id',treeNode.id).attr('parent',treeNode.pId).attr('depath',treeNode.depath);      
      }else{
      //flag 关了，只能选营业厅的情况
    	if(treeNode.depath==4){
    		 treeObj.parent().parent().find('>input').val(treeNode.name).attr('data_id',treeNode.id).attr('parent',treeNode.pId).attr('depath',treeNode.depath);
    		 
    		 var orgId=treeNode.id;
    			if(orgId!=""){
    				var deviceUrl=contextPath+"/soa/asmp/datas/deviceList";
    				var msDevice="#msDevice";
    					modalDevice(deviceUrl,msDevice,orgId);
    			}
    		 
    	}else{
            dialog({
       	     title:'提示',
                content:'请选中营业厅！',
                okValue: '关闭',
                zIndex:9999999,
                ok:function(){}
             }).show();
         }
       
      }
      
    };
   
    var thewidth = treeObj.parent().parent().find('>input').width();   
    treeObj.parent().css({width:thewidth+'px'});    
    var $zTreeDemo = treeObj.parent();
    //console.log(treeObj.parent().parent())  
    var thetree = $.fn.zTree.init(treeObj, setting, zNodes);

   // zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
    // curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
    // zTree_Menu.selectNode(curMenu);

    treeObj.hover(function () {
      if (!treeObj.hasClass("showIcon")) {
        treeObj.addClass("showIcon");
      }
    }, function() {
      treeObj.removeClass("showIcon");
    });

    treeObj.parent().parent().find('>input').on('mouseover',function(){
      var width = $(this).outerWidth();
      var left = $(this).position().left;
      $zTreeDemo.css({width:width+'px',left:left}).show();
    });   

    treeObj.parent().parent().mouseleave(function(event) {
      treeObj.parent().hide();
    });

  }   
    