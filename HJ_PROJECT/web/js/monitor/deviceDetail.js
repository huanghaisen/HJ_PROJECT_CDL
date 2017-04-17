

//核心方法，构造表，id选择器对应的是index 中你表生成所在位置的div的ID
var table = $('#table_form').DataTable({
		// 'dom': 'Bfrtip',      
		//调用pageSerach 取得user表数据
		'ajax' : {
			"url":contextPath + dataUrl, //这块之前要从后端传进来，在productIndex里要定义
			"type": "post",
			"dataType":"json",
			"data":function(data) {
				data={
					"did":0,
					"mid":0,
					"state":0,
					"oid":0,
					"code":""
				}
			}
		},
		'columns' : [

		{
			"title" : "",
		    "className":      'details-control',
		    //样式加高光
		    "orderable":      false,
		    "data":          'status',
		    "render":function(data,type,full,meta){
		    	//0离线,1正常,2意外,3告警,4故障,5灾难
				var value = "";
				if(data===0){
					value = "<small class='label label-default' title='离线'><i class='fa fa-plug'></i></small>";
				}else if(data===1){
					value = "<small class='label label-success' title='正常'><i class='fa fa-gears'></i></small>";
				}else if(data===2 || data===3){
					value = "<small class='label label-warning' title='告警'><i class='fa fa-info-circle'></i></small>";
				}else if(data===4 || data===5){
					value = "<small class='label label-danger' title='故障'><i class='fa fa-exclamation-triangle'></i></small>";
				}
				return value;
			}
		},{
		   "title" : "地市",
           "data":   'cityname',
           "render":function(data,type,full,meta){
        	   return strSub(data,4);
           }
        },{
			"title" : "区县",
			"data" : 'countyname',
			"render":function(data,type,full,meta){
	    	   return strSub(data,4);
			}
		},{
			"title" : "营业厅",
			"data" : 'orgname',
			"render":function(data,type,full,meta){
	    	   return strSub(data,5);
			}
		},{
			"title" : "终端用途",
			"data" : 'purposename',
		},{
			"title" : "编号",
			"data" : 'code',
			"render":function(data,type,full,meta){
				var value = "<a href='javaScript:void(0);' onclick='showView("+full.id+")' >"+data+"</a>";
				return value;
			}
		},{
			"title" : "在线状态",
			"data" : 'online',
			"render":function(data,type,full,meta){
				var value = "";
				if(data===0){
					value = "离线";
				}else{
					value = "在线";
				}
				return value;
			}
		},{
			"title" : "厂商",
			"data" : 'manufname',
			"render":function(data,type,full,meta){
	    	   return strSub(data,4);
			}
		},{
			"title" : "型号",
			"data" : 'modelname'
		},{
			"title" : "",
			"className":      'details-control',
			"data" : 'id',
			"render":function(data,type,full,meta){
				var value = "<a href='javaScript:void(0);' onclick='sendCommand("+data+",\"RESTART\")' ><i class='glyphicon glyphicon-repeat' title='重启'></i></a> <a href='javaScript:void(0);' onclick='sendCommand("+data+",\"SHUTDOWN\")' ><i class='glyphicon glyphicon-off'  title='关机'></i></a>";
				return value;
			}
		}
		],
		'columnDefs' : [ {
			'targets' : 0,
			'searchable' : false,
			'orderable' : false,
			'width' : '6%',
			'className' : 'dt-body-center',
			'render' : function(data, type, full, meta) {
			return data;
			}
		},{
			'targets' : 9,
			'width' : '6%'
		} ],
		"ordering": false,
		'info' : false,
		"scrollX": false,
		"scrollY": '50vh',
		"processing": false,
        "serverSide": false,
		'paging' : true,
		'pageLength' : 10,
		"language": {
                "sProcessing": "正在加载中......",
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
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
		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
		
		'searching' : false,      //搜索栏，失效
		//'order' : [ [ 1, 'desc' ] ], 排序，服务器模式失效
		
		'rowCallback' : function(row, data, dataIndex) {
			//获取行id
//			var rowId = data[0];
			if(data.online===0){
				$(row).find('td').eq(9).html('');
			}
			// 行已经在选择行的组里面
			//if ($.inArray(rowId, rows_selected) !== -1) {
			//	$(row).find('input[type="checkbox"]').prop('checked', true);
			//	$(row).addClass('selected');				
			//}
			
		},"fnFooterCallback": function(nFoot, aData, iStart, iEnd, aiDisplay) {
			$("#shownum").html("共<em style='color: #fc6500;'>"+aData.length+"</em>台设备");
		}
	
	});
	

//----------------------------------------------以下为首列复选框相关Js------------------------------------------------------
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

//更新表格 单选 全选状态
function updateDataTableSelectAllCtrl(table) {
	var $table = table.table().node();
	var $chkbox_all = $('tbody input[type="checkbox"]', $table);
	var $chkbox_checked = $('tbody input[type="checkbox"]:checked', $table);
	var chkbox_select_all = $('thead input[name="select_all"]', $table)
			.get(0);

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


//----------------------------------------------以上为首列复选框相关Js------------------------------------------------------


// 处理表格单元格单击复选框 如果需要...（暂时不知道用上了没，兴斌预留）
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


//------------------------------------------刷新 重新载入分页数据（必须要有）
//刷新用户表到初始
	function DTrefresh(){
		var url=contextPath + dataUrl;
		 var data = {};
         data.name = "";
         data.loginName= "";
         data.state    = ""; 
		table.settings()[0].ajax.url = url; 
		table.settings()[0].ajax.data = {"params":data};
		table.ajax.reload();			
			
	}

//child row 事件
$('#table_form').on('click', 'td.details-control', function () {
    var tr = $(this).closest('tr');
    var row = table.row( tr );

    if ( row.child.isShown() ) {
        // This row is already open - close it
        row.child.hide();
        tr.removeClass('shown');
    }
    else {
        tr.addClass('shown');
    }
} );

//-----------------------------------以上为child row---作用：单击拼接显示详情-------------------------- 
