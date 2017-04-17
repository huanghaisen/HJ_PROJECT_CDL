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

//Ztree的核心属性  zNodes
var zNodes =[];

//ajax要同步取值赋给zNodes,async要给fasle,否则默认异步就空了
$.ajax({
	  type:"post",
	  data:"json",
	  url:contextPath+"/product/datas/typeTreeCombox",
	  async:false,
	  success:function (result){
		  var newResult=[];
		  for(var i=0;i<result.length;i++){
			  newResult.push({"id":result[i].id,"pId":result[i].parent,"name":result[i].text,"code":result[i].code});
			  }
		   zNodes=newResult;	  
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
    //   var zTree = $.fn.zTree.getZTreeObj("treeUl");
    //   zTree.expandNode(treeNode);
    //   return false;
    // }
    return true;
  }
  
  //点击下拉树事件，给目标input框赋值  
  function zTreeOnClick(event, treeId, treeNode) {
	$('#typeInputTree').val(treeNode.name).attr('data_id',treeNode.id).attr('parent',treeNode.pId).attr('code',treeNode.code);
    console.log(treeNode);
    
  };
 
  var thewidth = $('#typeInputTree').width();

  $('#zTreeDiv').css({width:thewidth+'px'});

 //调用ztree的init方法生成下拉树（属性详情参考官网api）
  var treeObj = $("#treeUl");
  $.fn.zTree.init(treeObj, setting, zNodes);
  zTree_Menu = $.fn.zTree.getZTreeObj("treeUl");
  // curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
  // zTree_Menu.selectNode(curMenu);

  treeObj.hover(function () {
    if (!treeObj.hasClass("showIcon")) {
      treeObj.addClass("showIcon");
    }
  }, function() {
    treeObj.removeClass("showIcon");
  });


  $('#typeInputTree').on('mouseover',function(){

    var width = $(this).outerWidth();
    var left = $(this).position().left;

    $('#zTreeDiv').css({width:width+'px',left:left}).show();

  });

  $('#typeInputTree').parent().on('mouseleave',function(){

      $('#zTreeDiv').hide();

  });
  

  $('#zTreeDiv').mouseleave(function(event) {
    $(this).hide();
  });;