typeof template == 'function' && typeof moment == 'function' && template.helper("moment", moment);
var lmask = null;
var ai = {};//封装常用的公共方法

/**
 * Ajax请求封装
 */
ai.ajax = function(addr, params, onSuccess, onError, onComplete){
	_ai_ajax_common(addr, params, onSuccess, onError, onComplete,"application/x-www-form-urlencoded");
}

ai.json = function(addr, params, onSuccess, onError, onComplete){
	_ai_ajax_common(addr, params, onSuccess, onError, onComplete,"application/json");
}

ai.alert = function(message){
	layer.alert(message);
}

ai.confirm = function (title,message,onOKBtn,onCalBtn){
	var onOKBtn = arguments[2]?arguments[2]:function(){};//点击确定按钮时
	var onCalBtn = arguments[3]?arguments[3]:function(){};//点击取消按钮时
	layer.confirm(message, {icon:3, title:title}, function(index){
		layer.close(index);
		onOKBtn();
	});
}

/**
 * 获取HTML页面请求参数方法
 */
ai.getQueryString = function(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}

/**
 * 打开加载提示蒙版
 */
ai.openMask = function(){
	if(!lmask){
		lmask = layer.load(1,{shade: [0.3, '#393D49']});
	}
}

/**
 * 关闭加载提示蒙版
 */
ai.closeMask = function(){
	if(lmask){
		layer.close(lmask);
		lmask = null;
	}
}

function _ai_ajax_common(addr, params, onSuccess, onError, onComplete,contentType){
	var onSuccess = arguments[2]?arguments[2]:function(){};//成功时
	var onError = arguments[3]?arguments[3]:function(){};//异常时
	var onComplete = arguments[4]?arguments[4]:function(){};//完成时
	var contentType = arguments[5]?arguments[5]:"application/x-www-form-urlencoded";//请求数据类型
	$.ajax({
		type : "POST",
		url : addr,
		async : true,
		cache : false,
		dataType : "json",
		contentType: contentType,
		data : params,
//		timeout:10000,
//		headers:{'AI-Requested-Way':'WEB'},
		success : function(data, textStatus, jqXHR) {
			if(!data.state && data.code == "JAVA_EXCEPTION"){
				ai.closeMask();
				layer.alert('服务端请求处理发生问题,请联系系统管理员.',{icon:5});
			}else if(!data.state && data.code == "NOT_LOGINED"){
				layer.alert('您尚未登陆或账号在其他终端上登陆导致本设备踢出.',{icon:5},function(index){
					document.location.href="../base/login.html";//页面转向到登陆页面
				});
			}else if(!data.state && data.code == "CHARACTER_WRONGFUL"){
				ai.closeMask();
				layer.alert('您提交的数据中含有非法字符,请调整后继续.',{icon:5});
			}else if(!data.state && data.code == "SHOW_MSG"){
				ai.closeMask();
				layer.alert(data.info,{icon:5});
			}else{
				onSuccess(data);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("AIERROR: "+(errorThrown ? errorThrown : "")+(textStatus ? textStatus : ""));//通常 textStatus和 errorThrown之中只有一个会包含信息
			ai.closeMask();
			onError();
		},
		complete : function(XMLHttpRequest, textStatus) {
			onComplete();
		}
	});
}
