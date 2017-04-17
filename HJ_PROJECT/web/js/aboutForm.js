//将form转为AJAX提交
function ajaxSubmit(frm, fn) {
    var dataPara = getFormJson(frm);
    postjson(frm.action,"json", dataPara, fn);
}

//将form中的值转换为键值对。
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
          if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
}

function delObject(id,url){	
	var id={"id":id};
	//alert("进入delUser  id: "+id);
	$.ajax({
		url:contextPath+url,
		type:"post",
		data:id,
		dataType:"json"
	});
}

//--------------------------------------全局搜索方法----------------------------
function refreshData(url,params){

    table.settings()[0].ajax.url = url;   
    table.settings()[0].ajax.data = {"params":params};
    table.ajax.reload();

  }



//---------------------------------------------------机构下拉框组件(可输入文字搜索)-----------------------------


//var orgUrl=contextPath+"/soa/asmp/datas/org4list";
//var orgTarget="#fSelect";
//orgSelect(orgUrl,orgTarget);

function orgSelect(comboxUrl,SelectTarget){
	$.ajax({
   	url:comboxUrl,    
   	type:"post",
   	dataType:"json",
   	async:false,
   	success: function(result){  		
   		//combxListData=result;
   		//alert(fOrgs);
   		for(var i = 0;i<result.length;i++){    	
   			var $option = $('<option/>').attr('value',result[i].id).html(result[i].orgName);
   			$option.appendTo($(SelectTarget));
   		}

   		$(SelectTarget).select2({
   			placeholder: "请选择",
   			allowClear: false
   		}).change(function(){
   			//alert("callback2222")
   		});
   		    		
   	   } 
     });
}

//独立下拉选项方法
function selectcbx(comboxUrl,SelectTarget){
	var argv = arguments;
	var datas=(argv.length > 2) ? argv[2]:{};
	$.ajax({
   	url:comboxUrl,    
   	type:"post",
   	dataType:"json",
   	data:datas,
   	async:false,
   	success: function(result){  		

   		for(var i = 0;i<result.length;i++){    	
   			var $option = $('<option/>').attr('value',result[i].id).html(result[i].loginName);
   			$option.appendTo($(SelectTarget));
   		}

   		$(SelectTarget).select2({
   			placeholder: "请选择",
   			allowClear: false
   		}).change(function(obj){
   			console.log(obj);
   		});
   		    		
   	} 
     });
}

function showDailog(msg,closetime){
	var d = dialog({
		content : msg,
	});
		d.show();
		setTimeout(function() {
		d.close().remove();
	}, closetime); 

}
