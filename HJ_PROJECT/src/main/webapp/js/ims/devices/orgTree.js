var curMenu = null, zTree_Menu = null;
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
    onClick:zTreeOnClick
  }
};

//Ztree的核心属性  zNodes_org
var zNodes_org =[];

//ajax要同步取值赋给zNodes_org,async要给fasle,否则默认异步就空了
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
		   zNodes_org=newResult;	  
		  }
		  
});

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
    
  }

  function beforeClick(treeId, treeNode) {
    // if (treeNode.level == 0 ) {
    //   var zTree = $.fn.zTree.getZTreeObj("orgTreeUl");
    //   zTree.expandNode(treeNode);
    //   return false;
    // }
    return true;
  }
  
  //点击下拉树事件，给目标input框赋值  
  function zTreeOnClick(event, treeId, treeNode) {
	$('#orgTreeInput').val(treeNode.name).attr('data_id',treeNode.id).attr('parent',treeNode.pId).attr('depath',treeNode.depath);
   // console.log(treeNode);
    
  };
 
  var thewidth = $('#orgTreeInput').width();

  $('#orgTreeDiv').css({width:thewidth+'px'});

 //调用ztree的init方法生成下拉树（属性详情参考官网api）
  var treeObj = $("#orgTreeUl");
  $.fn.zTree.init(treeObj, setting, zNodes_org);
  zTree_Menu = $.fn.zTree.getZTreeObj("orgTreeUl");
  // curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
  // zTree_Menu.selectNode(curMenu);

  treeObj.hover(function () {
    if (!treeObj.hasClass("showIcon")) {
      treeObj.addClass("showIcon");
    }
  }, function() {
    treeObj.removeClass("showIcon");
  });


  $('#orgTreeInput').on('mouseover',function(){

    var width = $(this).outerWidth();
    var left = $(this).position().left;

    $('#orgTreeDiv').css({width:width+'px',left:left}).show();

  });

  $('#orgTreeInput').parent().on('mouseleave',function(){

      $('#orgTreeDiv').hide();

  });
  

  $('#orgTreeDiv').mouseleave(function(event) {
    $(this).hide();
  });;