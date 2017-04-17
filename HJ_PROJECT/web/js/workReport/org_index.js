

//核心方法，构造表，id选择器对应的是index 中你表生成所在位置的div的ID
var table = $('#table_form').DataTable({
		// 'dom': 'Bfrtip',      
		//调用pageSerach 取得user表数据
		'ajax' : {
			"url":contextPath + dataUrl, //这块之前要从后端传进来，在productIndex里要定义
			"type": "post",
			"dataType":"json",
			"data":function(data) {
				data.params={
					"time":"",
					"oid":0
				}
			}
		},
		'columns' : [
		  {
			   "title" : "市区/县/营业厅",
               "className":      'details-control',
               //样式加高光
               "orderable":      false,
               "data":          'orgname',
               "defaultContent": ''
        },{
			"title" : "报障总量",
			"data" : 'faultsum'
		},{
			"title" : "已处理总量",
			"data" : 'handledsum'
		},{
			"title" : "未处理总量",
			"data" : 'nohandledsum',
		},{
			"title" : "告警",
			"data" : 'warningnum'
		},{
			"title" : "故障",
			"data" : 'faultnum'
		},{
			"title" : "意外",
			"data" : 'accidentnum'
		},{
			"title" : "灾难",
			"data" : 'disasternum'
		}
		],
		'columnDefs' : [ {
			'targets' : 0,
			'searchable' : false,
			'orderable' : false,
			'width' : '40%',
			'className' : 'dt-body-center',
			'render' : function(data, type, full, meta) {
			return data;
			}
		} ],
		"ordering": false,
		'info' : false,
		"scrollX": false,
		"scrollY": '50vh',
		"processing": true,
        "serverSide": true,
		'paging' : false,
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
		"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
		
		'searching' : false,      //搜索栏，失效
		//'order' : [ [ 1, 'desc' ] ], 排序，服务器模式失效
		
		'rowCallback' : function(row, data, dataIndex) {
			//获取行id
			var rowId = data[0];
			// 行已经在选择行的组里面
			//if ($.inArray(rowId, rows_selected) !== -1) {
			//	$(row).find('input[type="checkbox"]').prop('checked', true);
			//	$(row).addClass('selected');				
			//}
			
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
