var selectHtml2='<option></option>';
$.ajax({
   	url:contextPath+"/soa/parts/datas/partsBox",    
   	type:"post",
   	dataType:"json",
   	async:false,
   	success: function(result){  		
          
   		for(var i = 0;i<result.length;i++){    	
   			selectHtml2 +='<option value="'+result[i].id+'">'+result[i].devicePartName+'</option>'
   		}
   		$("#select3").html("");
   		$("#select3").html(selectHtml2).select2();
   	   }
     });


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

var setting2 = {
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
		    onClick:zTreeOnClick2
		  }
		};



//处理结单问题下拉树数据
var zNodes1 =[];
var zNodes2 =[];
$.ajax({
	  type:"post",
	  data:"json",
	  url:contextPath+"/soa/asmp/commonFault/datas/simpleBox",
	  async:false,
	  success:function (result){
		  var newResult=[];
		  for(var i=0;i<result.length;i++){
			  newResult.push({"id":result[i].id,"pId":result[i].parentId,"name":result[i].faultName});
			  }
		  zNodes1=newResult;
		  }
		  
});

//处理结单处理方法下拉树数据
var zNodes2 =[];
$.ajax({
	  type:"post",
	  data:"json",
	  url:contextPath+"/soa/asmp/commonMethod/datas/simpleBox",
	  async:false,
	  success:function (result){
		  var newResult=[];
		  for(var i=0;i<result.length;i++){
			  newResult.push({"id":result[i].id,"pId":result[i].parentId,"name":result[i].commonMethodName});
			  }
		  zNodes2=newResult;
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
   //   var zTree = $.fn.zTree.getZTreeObj("treeDemo");
   //   zTree.expandNode(treeNode);
   //   return false;
   // }
   return true;
 }
 
 //点击下拉树事件，给目标input框赋值  
 function zTreeOnClick(event, treeId, treeNode1) {
   $('#inputTree').val(treeNode1.name).attr('data_id',treeNode1.id).attr('pId',treeNode1.pId);
   //console.log(treeNode1);
   $('#zTreeDemo').hide();
  
 };
 //解决方法的点击事件
 function zTreeOnClick2(event, treeId, treeNode2) {
	   $('#inputTree2').val(treeNode2.name).attr('data_id',treeNode2.id).attr('pId',treeNode2.pId);
	   //console.log(treeNode2);
	   
	   var fault = $("#inputTree").val();
	   
	   //console.log("1:"+fault);
	   var faultId=$("#inputTree").attr('data_id');
	  // console.log("2:"+faultId);
       var method = $('#inputTree2').val();
      // console.log("3:"+method);
       var methodId= $('#inputTree2').attr('data_id');
      // console.log("4:"+methodId);
       var data = {};
       data.fault = fault;
       data.method = method;
       data.faultId = faultId;
       data.methodId = methodId;
       
       if(faultId==null||faultId==""){
    	   alert("请先选择问题再选处理方法")
       }else{
    	   addc2($('#addcl'),data);
       }      
	   $('#zTreeDemo2').hide();
	  
	 };

 var thewidth = $('#inputTree').width();

 $('#zTreeDemo').css({width:thewidth+'px'});

//调用ztree的init方法生成下拉树（属性详情参考官网api）
 var treeObj = $("#treeDemo");
 $.fn.zTree.init(treeObj, setting, zNodes1);
 //zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
 
 var treeObj2 = $("#treeDemo2");
 $.fn.zTree.init(treeObj2, setting2, zNodes2);
 zTree_Menu2 = $.fn.zTree.getZTreeObj("treeDemo2");
 // curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
 // zTree_Menu.selectNode(curMenu);

 treeObj.hover(function () {
   if (!treeObj.hasClass("showIcon")) {
     treeObj.addClass("showIcon");
   }
 }, function() {
   treeObj.removeClass("showIcon");
 });
 
 treeObj2.hover(function () {
	   if (!treeObj.hasClass("showIcon")) {
	     treeObj.addClass("showIcon");
	   }
	 }, function() {
	   treeObj.removeClass("showIcon");
	 });


 $('#inputTree').on('click',function(){

   var width = $(this).outerWidth();
   var left = $(this).position().left;

   $('#zTreeDemo').css({width:width+'px',left:left}).show();

 });
 
 $('#inputTree2').on('click',function(){

	   var width = $(this).outerWidth();
	   var left = $(this).position().left;

	   $('#zTreeDemo2').css({width:width+'px',left:left}).show();

	 });

 function addc2(obj,data){
     var html = '<div class="row">'
           +'<div class="col-xs-10" faultId="'+data.faultId+'" methodId="'+data.methodId+'">'
             +'<p>故障描述：'+data.fault+'</p>'
             +'<p>处理方法：'+data.method+'</p>'
           +'</div>'
           +'<div class="col-xs-2">'
             +'<i class="fa fa-fw fa-trash-o mt10 pull-right"></i>'
           +'</div>'
         +'</div>'
         ;
     $(html).appendTo(obj);    
  }

app.currentCompleted = true;

  
  $(function(){
       //打回结单
        var isshouli = false;
        $('#ipt1').on('click',function(){

          if(isshouli==true){
            isshouli = false;
            $(this).attr('checked',true);
            $(this).siblings().html('是');
            $('#isshouli').show().siblings('#unshouli').hide();

          }else{
            isshouli = true;
            $(this).attr('checked',false);
            $(this).siblings().html('否');
            $('#unshouli').show().siblings('#isshouli').hide();
          }

        });

       $("#select3").select2({placeholder:'请选择物料',minimumResultsForSearch: -1});

       $("#select3").change(function(){
         
          var id = $(this).val();
          var name = $(".select2 option:selected").html();
          var data = {};
          data.id = id;
          data.name = name; 

          addwl($('#addwl'),data);

          
       });
      /*
       function addcl(obj,data){

          var html = '<div class="row">'
                +'<div class="col-xs-10" faultId="'+data.faultId+'">'
                  +'<p>故障描述：'+data.fault+'</p>'
                  +'<p>处理方法：'+data.method+'</p>'
                +'</div>'
                +'<div class="col-xs-2">'
                  +'<i class="fa fa-fw fa-trash-o mt10 pull-right"></i>'
                +'</div>'
              +'</div>'
              ;
          $(html).appendTo(obj);    
       }*/

       function addwl(obj,data){

          var html = '<div class="row">'
                +'<div class="col-xs-10" dataId="'+data.id+'">'
                  +'<p>物料名称：'+data.name+'</p>'                  
                +'</div>'
                +'<div class="col-xs-2">'
                  +'<i class="fa fa-fw fa-trash-o pull-right"></i>'
                +'</div>'
              +'</div>'
              ;
          $(html).appendTo(obj);    
       }

       $('#addcl').delegate('.fa-trash-o','click',function(){
          $(this).parent().parent().remove();
       });
       $('#addwl').delegate('.fa-trash-o','click',function(){
          $(this).parent().parent().remove();
       })
      
  });