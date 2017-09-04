var pageData = {
	param : {code:ai.getQueryString("code"),pageNum : 1,pageSize : 15},//查询参数
	first : true,//是否首次加载页面
	grid : {}//缓存查询结果数据
};

$(function(){
	loadDataInfo();
});

/**
 * 点击查询按钮
 */
function clickDGridQueryBtn(){
	pageData.param.pageNum = 1;
	$.each($('#_dgrid_query_from input'),function(i,n){
		if($.trim($(n).val())){
			pageData.param[$(n).attr('name')] = $.trim($(n).val());
		}else{
			delete pageData.param[$(n).attr('name')];
		}
	});
	loadDataInfo();
}

/**
 * 加载数据信息
 */
function loadDataInfo(){
	ai.openMask();
	ai.ajax('../../grid/getDataInfo',pageData.param,function(data){
		if(data.state){
			if(pageData.first){
				$('#_dgrid_query_from').html(template('_dgrid_query_from_template',data.info));
				pageData.first = false;
			}
			$('#_dgrid_query_table').html(template('_dgrid_query_table_template',data.info));
			$('#_dgrid_query_pagination').html(template('_dgrid_query_pagination_template',data.info));
			$('[data-toggle="tooltip"]').tooltip();
			pageData.grid = data.info;
			initMainPageLayout();
		}
	},function(){
		
	},function(){
		ai.closeMask();
	});
}

/**
 * 点击改变分页控件页面
 * @param pageNum
 */
function clickChangePageNumBtn(pageNum){
	pageData.param.pageNum = pageNum;
	loadDataInfo();
}

/**
 * 点击数据行中的操作类按钮
 * @param rowIndex
 * @param btnIndex
 */
function clickRowBtnEvent(rowIndex,btnIndex){
	var row = pageData.grid.data.list[rowIndex];
	var btn = pageData.grid.define.buttons[btnIndex];
	if(btn.confirm){
		ai.confirm('确认','您确定要进行'+btn.text+'操作吗？',function(){
			handleRowBtnEvent(row,btn);
		});
	}else{
		handleRowBtnEvent(row,btn);
	}
}

/**
 * 处理数据行中的操作按钮点击事件
 * @param rowObj
 * @param btnObj
 */
function handleRowBtnEvent(row,btn){
//	alert(JSON.stringify(row));
//	alert(JSON.stringify(btn));
	var param = '';
	if(btn.params){
		var spts = [];
		for(var i = 0;i < btn.params.length;i++){
			spts = btn.params[i].split('>');
			if(spts[0] && row[spts[0]] && spts[1]){
				param += ((i>0?'&':'')+spts[1]+'='+row[spts[0]]);
			}
		}
	}
	alert(param);
	if(btn.type){//Dialog,Window,Redirect,Ajax
		switch (btn.type.toUpperCase()) {
		case 'WINDOW':
			window.open(btn.url+'?'+param);
			break;
		case 'REDIRECT':
			document.location.href=btn.url+'?'+param;
			break;
		case 'DIALOG':
			_openGridCommonModalDialog(btn.url+'?'+param);
			break;
		case 'AJAX':
			ai.alert('尚未实现Ajax请求.');
			break;
		default:
			break;
		}
	}else{
		ai.alert('该按钮未指定处理方法.');
	}
}

/**
 * 操作按钮点击事件后的回调函数
 */
function modalCallback(msg){
	$('#_grid_iframe_modal_dialog').modal('hide');
	loadDataInfo();
	ai.alert(msg);
}

/**
 * 加载页面Layout布局
 */
function initMainPageLayout(){
	$('body').layout({
		defaults:{
			spacing_open:0,
			spacing_closed:0
		},
		north:{
//			size:45,
			paneClass:'layout-north-panel'
		},
		center:{
			paneClass:'layout-center-panel'
		},
		south:{
//			size:45,
			paneClass:'layout-south-panel'
		}
	});
}

/**
 * 根据iframe的src启用模态窗口
 * @param src
 */
function _openGridCommonModalDialog(src,title){
	$('#_grid_iframe_modal_dialog iframe').css({"height":($(window).height()-100)+"px"});//-100:顶部及底部预留空间和
	$('#_grid_iframe_modal_dialog').on('shown.bs.modal', function (e) {
		$('#_grid_iframe_modal_dialog iframe').attr('src', src);
	}).on('hidden.bs.modal', function (e){
		$('#_grid_iframe_modal_dialog iframe').removeAttr('src');
	});
//	$('#_grid_title_modal_label').html(title);
//	$('#_grid_iframe_modal_dialog').modal({backdrop:'static',keyboard:true});
	$('#_grid_iframe_modal_dialog').modal('show');
}