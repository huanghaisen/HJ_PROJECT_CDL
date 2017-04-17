
// icoclass:fa-share,fa-dashboard,fa-files-o,fa-th,fa-laptop,fa-edit,fa-book,fa-table,fa-envelope,fa-pie-chart;fa-circle-o text-red,text-yellow,text-aqua

//子菜单
function createChildMenu(data) {
	var $themenu = $('<ul />');
	var len = data.length;

	for (var i = 0; i < len; i++) {

		if (data[i].children.length > 0) {

			var $li = $('<li />');
			var $a = $('<a>');
			var menuname = '<i class="fa fa-circle-o"></i>' + data[i].name + '<i class="fa fa-angle-left pull-right"></i>';
			
			var $ul = createChildMenu(data[i].children);

			var action = data[i].action;
			var isopen = data[i].isopen;

			if (action !== "") {
				$a.attr('href', action);
				$a.attr('value', isopen);
			} else {
				$a.attr('href', '#');
			}
			
			$a.attr('data-navtitle',data[i].navtitle);

			$a.html(menuname).appendTo($li);
			$ul.appendTo($li);
			$li.appendTo($themenu);

		} else {

			var $li = $('<li />');
			var $a = $('<a>');
			var menuname = '<i class="fa fa-circle-o"></i>' + data[i].name;
			var action = data[i].action;
			var isopen = data[i].isopen;

			if (action !== "") {
				$a.attr('href', action);
				$a.attr('value', isopen);
			} else {
				$a.attr('href', '#');
			}

			$a.attr('data-navtitle',data[i].navtitle);
			$a.html(menuname).appendTo($li);
			$li.appendTo($themenu);

		}

	}

	$themenu.addClass('treeview-menu');

	return $themenu;
}

//一级菜单
function createMenu(data) {
	var $menucontent = $('<ul>');
	var len = data.length;

	for (var i = 0; i < len; i++) {

		if (data[i].children.length > 0) {

			var $li = $('<li />');
			var $a = $('<a>');			
			var $iL = $('<i>').addClass('fa');
			var $iR = $('<i>').addClass('fa fa-angle-left pull-right');
			var $span = $('<span>').html(data[i].name);
			var childlen = data[i].children.length;

			var $ul = createChildMenu(data[i].children);
			var action = data[i].action;
			var isopen = data[i].isopen;

			if (action !== "") {
				$a.attr('href', action);
				$a.attr('value', isopen);
			} else {
				$a.attr('href', '#');
			}
			$a.attr('data-navtitle',data[i].navtitle);

			$iL.addClass(data[i].icoclass);
			$iL.appendTo($a);
			$span.appendTo($a);
			$iR.appendTo($a);
			$a.appendTo($li);
			$ul.appendTo($li);

			if (data[i].type === 'normal') {

				if (data[i].status === 'active') {
					$li.addClass('active');
				}

			}

			$li.addClass('treeview').appendTo($menucontent);

		} else {			

			if (data[i].type === 'header') {
				var $li = $('<li />');

				$li.addClass('header').html(data[i].name);
				$li.appendTo($menucontent);

			}

			if (data[i].type === 'only') {
				var $li = $('<li />');
				var $a = $('<a>');
				var $i = $('<i>').addClass('fa');
				var $span = $('<span>').html(data[i].name);
				
				var action = data[i].action;
				var isopen = data[i].isopen;

				if(action!==""){
					$a.attr('href', action);
					$a.attr('value', isopen);
				}else{
					$a.attr('href','#');
				}				
				
				$i.addClass(data[i].icoclass);
				$i.appendTo($a);
				$span.appendTo($a);
				$a.appendTo($li);
				$li.appendTo($menucontent);

			}

		}

	}

	$menucontent.addClass('sidebar-menu');

	return $menucontent;

}

function initMenu(menu){
	var $sidebar = $('.sidebar');

	if ($sidebar.find('.sidebar-menu').length > 0) {		
		$sidebar.find('.sidebar-menu').remove();
		
		// setTimeout(function() {
		 	menu.appendTo($sidebar);
		// }, 10);

	}else{	
		menu.appendTo($sidebar);
	}
}

function getMenu() {
	ajax("/soa/manage/datas/menu","json",function(json) {
		if (json) {
			var data = json.data;
			var $menu = createMenu(data);
			setTimeout(function() {
				initMenu($menu);
			}, 10);

		} else {

			alert("数据出错!");
		}
	});	
}

function menuRefresh() {
	ajax("/soa/manage/datas/menurefresh","json",function(obj){
		if (obj.success==0) {
			getMenu();
		}else{
			alert(obj.msg);
		}
	});
}


//创建 当前活动导航
function createActivenav(data){	
	
	var $ol = $('<ol />');
	var home = '<li><a href="index.html"><i class="fa fa-dashboard"></i> 首页</a></li>';
	var navtitle = '<li class="active">'+data[0]+'</li>';	

	$ol.addClass('breadcrumb');

	$(home).appendTo($ol); 
	$(navtitle).appendTo($ol);

	return $ol;	

}

//创建一级导航
function createWebnav(data){

	var len = data.length;
	var $h1= $('<h1 />');
	var htitle ='';

	for(var i=0;i<len;i++){

		if(0==i){
			htitle = data[i];

		}else{
			htitle += '<small>'+' &gt;'+data[i]+'</small>';
		}

	}

	$h1.html(htitle);

	return $h1;
	
}

//设置 一级导航
function setWebnav(data){

	var $contentheader = $('#content-header');

	if($contentheader.find('>h1').length>0){
		$contentheader.find('>h1').remove();

		var $createWebnav = createWebnav(data);
		$createWebnav.appendTo($contentheader);

	}else{

		var $createWebnav = createWebnav(data);
		$createWebnav.appendTo($contentheader);

	}

}

//设置 当前活动导航
function setActivenav(data){

	var $contentheader = $('#content-header');

	if($contentheader.find('.breadcrumb').length>0){
		$contentheader.find('.breadcrumb').remove();

		var $createActivenav = createActivenav(data);
		$createActivenav.appendTo($contentheader);

	}else{

		var $createActivenav = createActivenav(data);
		$createActivenav.appendTo($contentheader);

	}

}

//菜单 事件路由表
var menutodo = {
	'gohome': function() {
		alert('gohome');
	},
	'todo': function() {
		alert('todo');
	}
}

function menuAction(todo){

	var $sidebar = $('.sidebar');
	
	if (todo) {

		$sidebar.delegate('.sidebar-menu li a', 'click', function(event) {

			var $this = $(this);
			var navtitle = $this.attr('data-navtitle').split('_');
			var isopen = parseInt($this.attr('value'));


			if (isopen === 0) {
				var action = $this.attr('href');
				ajax(action,"html",function(obj){
					$(".content").html(obj);
				});
				//触发 导航功能
				setWebnav(navtitle);
				setActivenav(navtitle);
				event.preventDefault();
				event.stopPropagation();
			}
			
			if (isopen === 1) {

				//触发菜单 路由表事件	

				var action = $this.attr('href');
				action = action.split('#')[1];

				for (var fn in todo) {

					if (fn == action) {

						if (typeof(todo[fn]) == 'function') {
							todo[fn]();
						}


					}
				}
				//触发 导航功能
				setWebnav(navtitle);
				setActivenav(navtitle);
				event.preventDefault();
				event.stopPropagation();
			}
			
			if (isopen === 2) {

				$this.attr('target', '_blank');


			}

		});

	}else{

		return 
	}


};


function init(){
	getMenu();
	$('#menurefresh').on('click',function(){
		menuRefresh();
	});
	menuAction(menutodo);
};


init();











