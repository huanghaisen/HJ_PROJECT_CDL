
   var showTip = function(row, data, dataIndex) {
       //黑框详细信息
      $(row).mouseenter(function(event) {

         var $this = $(this);
         var $rowTip = $('<div />').addClass('showTip');
         var $rowTipcon = $('<div />').addClass('showTipcon').html('<p>' +'故障等级： '+ data.workGradeStr	 + '</p>' + '<p>' + '设备编号： '+data.deviceCode + '</p>' + '<p>' + '电话号码： '+data.telphone + '</p>');
         var x = event.pageX - 220;
         var y = event.pageY - 200;

         $rowTip.css({
            position: 'absolute',
            left: x + 'px',
            top: y + 'px'
         });

         $rowTipcon.appendTo($rowTip);
         $rowTip.appendTo($this);

      });

      $(row).mouseleave(function(event) {
         var $this = $(this);
         $(row).find('.showTip').remove();

      });

   }

 //初始表格
   var data={};
   
   var d1={params:{status:2}};
   var table = $('#table_form').DataTable({
		// 'dom': 'Bfrtip',      
		//调用pageSerach 取得user表数据
		'ajax' : {
			"url":contextPath + orginUrl,
			"type": "post",
			"dataType":"json",
			"data":function(d){
                d=d1
			}
		},
		
		
		'columns' : [
		  {
			"title" : '<input name="select_all" type="checkbox">',
			data : function(e) {//这里给最后一列返回一个操作列表
				return '<input value="'+e.id+'" type="checkbox">';
			}
		}, {

			//child row 
			   "title" : "工单编号",
              "className":      'details-control',
              //样式加高光
              "orderable":      false,
              "data":          'workCode',
              "defaultContent": ''
       },{
			"title" : "报障来源",
			"data" : 'workSourceStr'
		},{
			"title" : "维保厂商",
			"data" : 'manufName'
				
		},{
			"title" : "区县",
			"data" : 'districtStr'
				
		},{
			"title" : "营业厅",
			"data" : 'orgName'
				
		},{
			"title" : "报障人",
			"data" : 'creatorName'
				
		},{
			"title" : "联系电话",
			"data" : 'creatorTel'
				
		},{
			"title" : "状态",
			"data" : 'statusStr'
				
		},{
			"title" : "报障时间",
			"data" : 'createTime'
		}

		],
		'columnDefs' : [ {
			'targets' : 0,
			'searchable' : false,
			'orderable' : false,
			'width' : '1%',
			'className' : 'dt-body-center',
			'render' : function(data, type, full, meta) {
			return data;
			}
		} ],
		"ordering": false,
		'info' : false,
		"scrollX": false,
		"scrollY": '50vh',
		"processing": false,
       "serverSide": true,
		'paging' : true,
		'pageLength' : 8,
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
		"pagingType": "full_numbers",//number simple simple_numbers full 默认full_numbers
		'lengthChange' : false,   //可变分页组件,下行可规定显示页数，左边是定义，右边是页面显示的效果（如-1就是搜全部，但页面显示的选项是ALL）
		//"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		
		'searching' : false,      //搜索栏，失效
		//'order' : [ [ 1, 'desc' ] ], 排序，服务器模式失效
//		"dom": 'T<"clear">lfrtip',
//       "tableTools": {
//           "sSwfPath": "/swf/copy_csv_xls_pdf.swf"
//       },
		
		'rowCallback' : function(row, data, dataIndex) {
			//获取行id
			var rowId = data[0];
			showTip(row, data, dataIndex);
			
		}
	
	});

 
//-------------------------------------------以下为复选框相关js-----------------------------------------
   
	// 表格行选择数组
	var rows_selected = [];
   //更新表格 单选 全选状态
   function updateDataTableSelectAllCtrl(table) {
   	var $table = table.table().node();
   	var $chkbox_all = $('tbody input[type="checkbox"]', $table);
   	var $chkbox_checked = $('tbody input[type="checkbox"]:checked', $table);
   	var chkbox_select_all = $('thead input[name="select_all"]', $table).get(0);

   	// 没有选中
   	if ($chkbox_checked.length === 0) {
   		chkbox_select_all.checked = false;
   		if ('indeterminate' in chkbox_select_all) {
   			chkbox_select_all.indeterminate = false;
   		}

   		// 全部选中
   	} else if ($chkbox_checked.length === $chkbox_all.length) {
   		chkbox_select_all.checked = true;
   		if ('indeterminate' in chkbox_select_all) {
   			chkbox_select_all.indeterminate = false;
   		}

   		// 部分选中
   	} else {
   		chkbox_select_all.checked = true;
   		if ('indeterminate' in chkbox_select_all) {
   			chkbox_select_all.indeterminate = true;
   		}
   	}
   }

//处理 checkbox 点击事件
$('#table_form tbody').on('click', 'input[type="checkbox"]', function(e) {
	var $row = $(this).closest('tr');

	// 获取行数据
	var data = table.row($row).data();

	// 获取行id
	var rowId = data[0];

	// 
	var index = $.inArray(rowId, rows_selected);

	// If checkbox is checked and row ID is not in list of selected row IDs
	if (this.checked && index === -1) {
		rows_selected.push(rowId);

		// Otherwise, if checkbox is not checked and row ID is in list of selected row IDs
	} else if (!this.checked && index !== -1) {
		rows_selected.splice(index, 1);
	}

	if (this.checked) {
		$row.addClass('selected');
	} else {
		$row.removeClass('selected');
	}

	//更新表格选择状态
	updateDataTableSelectAllCtrl(table);

	e.stopPropagation();
});

// 处理表格单元格单击复选框 如果需要...
$('#table_form').on('click', 'tbody td, thead th:first-child', function(e) {
	$(this).parent().find('input[type="checkbox"]').trigger('click');
});

// 表格全选
$('thead input[name="select_all"]', table.table().container()).on(
		'click',
		function(e) {
			if (this.checked) {				
				$('#table_form tbody input[type="checkbox"]:not(:checked)')
						.trigger('click');
			} else {
				$('#table_form tbody input[type="checkbox"]:checked').trigger(
						'click');
			}

			e.stopPropagation();
		});

// on是开启监听--作用要问兴斌
//table.on('draw', function() {
//     updateDataTableSelectAllCtrl(table);
//});



//-------------------------------------------以上为复选框相关js-----------------------------------------

//刷新 重新载入分页数据
//刷新用户表到初始
	function DTrefresh(){
		var url=contextPath + orginUrl;
		 var params = {};
         params.orgName = "";
         params.deviceCode= "";
		table.settings()[0].ajax.url = url; 
		table.settings()[0].ajax.data = {"params":params};
		 setTimeout(function() {
				table.ajax.reload();			
			}, 200);
	}
	


//-----------------------------------child row-----------------------------
function format (e) {
    // `d` is the original data object for the row

	return '<table class="exTable" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	    '<div>'+
            '<div>感谢您使用售后服务报障平台，我们会尽快安排技术人员上门处理故障问题.</div>'+
            '<div class="pub-wrap">'+
    		'<ul class="pubprocess process-flex">'+
    			'<li class="active">'+
    				'<span class="ball"> <em>已报障</em>'+
    				'</span>'+
    			'</li>'+
    			'<li>'+
    			'	<span class="ball"> <em>已受理</em>'+
    				'</span>'+
    			'</li>'+
    			'<li>'+
    				'<span class="ball">'+
    					'<em>已结单</em>'+
    				'</span>'+
    			'</li>'+
    			'<li>'+
    				'<span class="ball">'+
    					'<em>已验收</em>'+
    				'</span>'+
    			'</li>'+
    		'</ul>'+
    	'</div>'+

       '</div>'+
	    '<tr>'+
            '<td>工单编号：</td>'+
            '<td>'+e.workCode+'</td>'+
            '<td>工单状态：</td>'+
            '<td>'+e.statusStr+'</td>'+
            '<td>营业厅：</td>'+
            '<td>'+e.orgName+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>报障人：</td>'+
            '<td>'+e.creatorName+'</td>'+
            '<td>手机号码：</td>'+
            '<td>'+e.telphone+'</td>'+
            '<td>报障时间：</td>'+
            '<td>'+e.createTime+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>设备编号：</td>'+
            '<td>'+e.deviceCode+'</td>'+
            '<td>维保厂商：</td>'+
            '<td>'+e.manufName+'</td>'+
            '<td>维保人员：</td>'+
            '<td>'+e.maintainerName+'</td>'+

        '</tr>'+
        '<tr>'+
            '<td>故障描述：</td>'+
            '<td>'+e.modelFaultRemark+'</td>'+
            '<td>附件：</td>'+
            '<td>'+'<a>'+e.attachList+'</a>'+'</td>'+
       '</tr>'+
       '<tr>'+
            '<td>故障现象：</td>'+
            '<td>'+e.modelFaultNameList+'</td>'+

       '</tr>'+
    '</table>';
}

function format1(e) {
    // `d` is the original data object for the row
	
    return 
}

//child row 样式事件
function setStep(o,i){
	var $o = o;
	var len = $o.find('li').length;

	if(i
		<=0){
		i=0;
	};
	if(0<i && i<len){
		i = i-1;		
	};
	if(i >= len){
		i=len-1;
	};

	$o.find('li').removeAttr('class');
	
	$o.find('li:lt('+i+')').addClass('done').end().find('li').eq(i).addClass('active');
}	

$('#table_form').on('click', 'td.details-control', function () {
    var tr = $(this).closest('tr');
    var row = table.row( tr );

    if ( row.child.isShown() ) {
        // This row is already open - close it
        row.child.hide();
        tr.removeClass('shown');
    }
    else {
        // Open this row
        row.child( format(row.data())).show();
        var num =1;
        if(row.data().status==1||row.data().status==2||row.data().status==8){
        	num=1;
        }else if(row.data().status==3){
        	num=2;
        }else if(row.data().status==9){
        	num=3;
        }else if(row.data().status==10){
        	num=4;
        }
    	
        setStep($(this).parent().next().find('.pubprocess'),num);
    	//alert(row.data().status+"num:"+num);
    	//console.log($(this).parent().next().html());
        tr.addClass('shown');
    }
} );

 
