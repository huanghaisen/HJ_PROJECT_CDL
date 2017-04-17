         //初始表格
		   var data={};
		  
		   var table = $('#table_form').DataTable({
				// 'dom': 'Bfrtip',      
				//调用pageSerach 取得user表数据
				'ajax' : {
					"url":contextPath + "/soa/asmp/modelFault/datas/search",
					"type": "post",
					"dataType":"json",
					"data":function(d){
						
					}
				},				
				
				'columns' : [
				  {
					"title" : '<input name="select_all" type="checkbox">',
					data : function(e) {//这里给最后一列返回一个操作列表
						return '<input value="'+e.id+'" type="checkbox">';
					}
				}, {
					   "title" : "故障现象",
		               "data": 'modelFaultName',
		        },{
					"title" : "所属型号",
					"data" : 'deviceModelStr'
				},{
					"title" : "故障等级",
					"data" : 'workGradeStr'
				},{
					"title" : "有效状态",
					"data" : 'status',
					"render": function (val, type, row) {
		                return val == 0 ? "有效" : "无效";
		            }
						
				},{
					"title" : "更新人员",
					"data" : 'modifierName'
				},{
					"title" : "最后更新时间",
					"data" : 'lastModifiedTime'
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
				'pageLength' : 8,
				"pagingType": "full_numbers",//number simple simple_numbers full 默认full_numbers
				'lengthChange' : false,   //可变分页组件,下行可规定显示页数，左边是定义，右边是页面显示的效果（如-1就是搜全部，但页面显示的选项是ALL）
				//"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				
				'searching' : false,      //搜索栏，失效
				//'order' : [ [ 1, 'desc' ] ], 排序，服务器模式失效
//				"dom": 'T<"clear">lfrtip',
//		       "tableTools": {
//		           "sSwfPath": "/swf/copy_csv_xls_pdf.swf"
//		       },			
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
//		table.on('draw', function() {
//			updateDataTableSelectAllCtrl(table);
//		});
//
//		setTimeout(function(){
//			
//			table.search('adn').draw();
//		},1000)

		//-------------------------------------------以上为复选框相关js-----------------------------------------

		//刷新 重新载入分页数据
		//刷新用户表到初始
			function DTrefresh(){
				var url=contextPath + "/soa/asmp/modelFault/datas/search";
				 var data = {};
//		         data.nickName = "";
//		         data.loginName= "";
//		         data.email    = ""; 
//		         data.phone    = ""; 
				table.settings()[0].ajax.url = url; 
				table.settings()[0].ajax.data = {"params":data};
				 setTimeout(function() {
						table.ajax.reload();			
					}, 200);
			}
			



		
		