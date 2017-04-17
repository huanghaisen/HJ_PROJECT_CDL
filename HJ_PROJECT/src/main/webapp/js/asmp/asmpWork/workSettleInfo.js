
  var treenum=0;

  //面板处理 打回切换
  $('#radiogroup').delegate('input', 'click', function(event) {
      
      var thevalue = $(this).val();

      if(thevalue == 1){

        $('#jddetail').show();
        
      }else{

        $('#jddetail').hide();

      }

  });

  //增加故障行
  $('#addgz').on('click',function(){

    var selectvalue = false;

    if($('#gztable').find('input').length>0){

      $('#gztable').find('input').each(function(index, el) {
        
        if($(this).val()=='请选择'){
          selectvalue = false; 
        }else{

          selectvalue = true; 
        }

        

      });
    }else{

      var selectvalue = true;
    }
    

    if(selectvalue==true){

      addgzRow($('#gztable'));
   
      var $treeId1 = $('#'+'ztree'+treenum+'1');
      var $treeId2 = $('#'+'ztree'+treenum+'2');
     
      addTree($treeId1,zNodes1);
      addTree($treeId2,zNodes2);

      treenum++ ; 
      
    }else{

      //alert('请选择')
    }


  });

  //删除故障当前行
  $('#gztable').delegate('button', 'click', function(event) {

      var $this = $(this);
      
      $this.parent().parent().remove();

  });  

  //增加故障行html
  function addgzRow(obj){

   var n1 = 'ztree'+treenum+'1';
   var n2 = 'ztree'+treenum+'2'; 
   var tr = '<tr>'
            +'<td width="40%">'
            +'<div class="wb-col pre">'  
                +'<input type="text" class="form-control" value="请选择" >'
                +'<div class="zTreeDemo">'
                 +'<ul class="ztree" id='+n1+'></ul>'
                +'</div>' 
              +'</div>'
            +'</td>'
            +'<td width="40%">'
              +'<div class="wb-col pre">'  
                +'<input type="text" class="form-control" value="请选择">'
                +'<div class="zTreeDemo">'
                  +'<ul class="ztree" id='+n2+'></ul>'
                +'</div>' 
              +'</div>'
            +'</td>'
            +'<td width="20%">'
                +'<button type="button" class="btn btn-danger"><i class="fa fa-times"></i> 删除</button>'                
            +'</td>'
            +'</tr>'
            ;

    $(tr).appendTo(obj); 
        
  }  

  var zNodes1 =[
    { id:1, pId:0, name:"文件夹"},
    { id:11, pId:1, name:"收件箱"},
    { id:111, pId:11, name:"收件箱1"},
    { id:112, pId:111, name:"收件箱2更感人的广告覆盖的覆盖的"},
    { id:113, pId:112, name:"收件箱3"},
    { id:114, pId:113, name:"收件箱4放松放松的实打的方式发送"},
    { id:12, pId:1, name:"垃圾邮件"},
    { id:13, pId:1, name:"草稿"},
    { id:14, pId:1, name:"已发送邮件"},
    { id:15, pId:1, name:"已删除邮件"},
    { id:3, pId:0, name:"快速视图范德萨发生的发生大幅度"},
    { id:31, pId:3, name:"文档"},
    { id:32, pId:3, name:"照片"},
  ];

  var zNodes2 =[
    { id:1, pId:0, name:"文件夹"},
    { id:11, pId:1, name:"收件箱"},
    { id:111, pId:11, name:"收件箱1"},
    { id:112, pId:111, name:"收件箱2更感人的广告覆盖的覆盖的非官方"},
    { id:113, pId:112, name:"收件箱3"},
    { id:114, pId:113, name:"收件箱4放松放松的实打的方式发送到发"},
    { id:12, pId:1, name:"垃圾邮件"},
    { id:13, pId:1, name:"草稿"},
    { id:14, pId:1, name:"已发送邮件"},
    { id:15, pId:1, name:"已删除邮件"},
    { id:3, pId:0, name:"快速视图范德萨发生的发生大幅度"},
    { id:31, pId:3, name:"文档"},
    { id:32, pId:3, name:"照片"},
  ];

  //实例树
  function addTree(obj,nodes){
     var treeObj = obj;
     var zNodes =  nodes;  
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
    

    function addDiyDom(treeId, treeNode) {
      var spaceWidth = 10;
      var switchObj = $("#" + treeNode.tId + "_switch"),
      icoObj = $("#" + treeNode.tId + "_ico");

      console.log(treeNode.tId)
      
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
      treeObj.parent().parent().find('>input').val(treeNode.name);
      
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
    

//------------------------------------------分割线---------------------------------------------------------

 var selectnum=0;
 var selectHtml = '<option></option>'
                  +'<option value="0">头部配件</option>'
                  +'<option value="1">中部配件</option>'
                  +'<option value="2">尾部配件</option>'
                  ;

  //增加配件行
  $('#addpj').on('click',function(){

    var selectvalue = false;
    var inputvalue = false;

    if($('#pjtable').find('input').length>0){
      

      $('#pjtable').find('input.inw').each(function(index, el) {
        
        if($(this).val()===''){
          inputvalue = false; 
        }else{

          inputvalue = true; 
        }        

      });

      var selectId = '#select'+(selectnum-1);
      if($(selectId).val()===''){
        selectvalue = false;
      }else{

        selectvalue = true;
      };

    }else{

      selectvalue = true;
      inputvalue = true; 
   }
    

    if(selectvalue===true&&inputvalue ===true){


      addpjRow($('#pjtable'));

      var selectId = '#select'+selectnum;
     
      $(selectId).html(selectHtml).select2();


      selectnum++ ; 
      
    }else{

      //alert('请选择')
    }


  });

  //删除配件当前行
  $('#pjtable').delegate('button', 'click', function(event) {

      var $this = $(this);
      
      $this.parent().parent().remove();

  });  
  
  //增加配件行html
  function addpjRow(obj){
   var sid = 'select'+selectnum;
   var tr = '<tr>'
            +'<td width="40%">'
            +'<div class="wb-col pre">'  
                +'<select class="form-control select2" id='+sid+' data-placeholder="请选择" style="width: 100%;"></select>'
              +'</div>'
            +'</td>'
            +'<td width="40%">'
              +'<div class="wb-col pre">'  
                +'<input type="text" class="form-control inw"  value="1">'                
              +'</div>'
            +'</td>'
            +'<td width="20%">'
                +'<button type="button" class="btn btn-danger"><i class="fa fa-times"></i> 删除</button>'                
            +'</td>'
            +'</tr>'
            ;

    $(tr).appendTo(obj); 
        
  }  







