//template.config("escape", false);
var pageData = {
	param : {templateCode : ai.getQueryString('code') ? ai.getQueryString('code') : '',//模板代码;当不存在mwrite的时候此参数不可缺
			modifyWriteId : ai.getQueryString('mwrite') ? ai.getQueryString('mwrite') : '',//填报记录ID;为空或0代表一般的新增填报,>0代表获取填报数据并提供编辑操作
			callBack : ai.getQueryString('cback') ? ai.getQueryString('cback') : ''},//回调函数,用于父窗口打开填报模板成功提交后的回调处理逻辑实现
			unitId : ai.getQueryString('unit') ? ai.getQueryString('unit') : '',//集团编码;当不存在mwrite的时候此参数不可缺
			productId : ai.getQueryString('product') ? ai.getQueryString('product') : '',//集团产品ID;为空或0代表不考虑集团产品,>0代表需要考虑特定产品模板
			parentWriteId : ai.getQueryString('pwrite') ? ai.getQueryString('pwrite') : '',//填报记录ID;为空或0代表一般填报模板,>0代表关系填报模板(一般指计划后的结果填报)
			archiveId : ai.getQueryString('archive') ? ai.getQueryString('archive') : '',//商机档案ID;为空或0代表不考虑商机档案,>0代表基于档案填报模板
	unit : {},//填报集团对象
	template : {},//填报模板对象
	display : {}//历史填报分页对象
};

$(function(){
	initMainPageLayout();
	loadTemplateItemRecord();
	//loadTemplateWriteUnit();
});

/**
 * 加载模板填报集团
 */
function loadTemplateWriteUnit(){
	if(pageData.param.unitId){
		ai.ajax('../../unit/get',{unitId:pageData.param.unitId},function(data){
			if(data.state){
				pageData.unit = data.info;
				if(pageData.template && pageData.template.templateCode && pageData.unit && pageData.unit.unitId && !pageData.param.modifyWriteId){
					$('#_template_item_footer button:eq(1)').removeClass("hidden");
				}
			}
		});
	}else if(pageData.param.modifyWriteId){
		//存在编辑记录ID时不考虑集团信息,直接启用编辑按钮即可
	}else{
		ai.alert('不合法的请求,因为您没有提供填报必须的参数信息!');
	}
}

/**
 * 加载填报模板条目
 */
function loadTemplateItemRecord(){
	if(pageData.param.templateCode || pageData.param.modifyWriteId){//存在模板编码或编辑记录ID均允许
		ai.openMask();
		var url = "";
		var param = {};
		if(pageData.param.modifyWriteId){
			url = "../../template/getRecord";
			param = {writeId:pageData.param.modifyWriteId};
		}else{
			url = "../../tmpl/getTemplateInfo";
//			param = {code:pageData.param.templateCode,archive:pageData.param.archiveId,product:pageData.param.productId};
//			param = {code:pageData.param.templateCode,product:pageData.param.productId};
			param = {code:pageData.param.templateCode}
		}
		ai.ajax(url,param,function(data){
			if(data.state){
				pageData.template = data.info;
				$('#_template_item_body').html(template('_template_define_template',data));
				$('[data-toggle="tooltip"]').tooltip();
				// pageData.template && pageData.template.templateCode && pageData.unit && pageData.unit.unitId && !pageData.param.modifyWriteId
//				if(pageData.template && pageData.template.templateCode && pageData.unit && pageData.unit.unitId){
				if(pageData.template && pageData.template.templateCode){
					$('#_template_item_footer button:eq(1)').removeClass("hidden");
				}else if(pageData.template && pageData.template.templateCode && pageData.param.modifyWriteId && pageData.param.modifyWriteId > 0){
					$('#_template_item_footer button:eq(2)').removeClass("hidden");
				}
			}
		},function(){},function(){
			ai.closeMask();
		});
	}else{
		ai.alert('不合法的请求,因为您没有提供必须的参数信息!');
	}
}

/**
 * 点击重置模板填报表单
 */
function clickResetTemplateWriteBtn(){
	$('#_template_item_form')[0].reset();
	$('#_template_item_form [data-toggle="tooltip"]').removeClass('text-danger').tooltip('hide').parent().removeClass('text-danger');
}

/**
 * 单击提交模板填报表单
 */
function clickSubmitTemplateWriteBtn(){
	if(_validTemplateItemForm()){
		ai.confirm('确认','您确定要提交吗?',function(){
			ai.openMask();
			var url = "";
			// pageData.param.modifyWriteId && pageData.param.modifyWriteId > 0
			if(pageData.param.modifyWriteId){//编辑填报记录信息
				url = "../../template/modifyRecord";
			}else{
				url = "../../template/writeRecord";
			}
			var writeRecord = _generateWriteRecord();
			ai.json(url,JSON.stringify(writeRecord),function(data){
				if(data.state){
					var resultMsg = pageData.template.templateName+' 成功!';
					if(pageData.param.modifyWriteId){//编辑填报记录信息
						resultMsg = "编辑 "+resultMsg;
					}else{
						$('#_template_item_form')[0].reset();
					}
					if(window.parent && pageData.param.callBack){
						var funCallBack = eval('window.parent.'+pageData.param.callBack);
						new funCallBack(resultMsg);
					}
	//				else if(typeof window.parent.writeCallback === 'function'){
	//					window.parent.writeCallback(resultMsg);
	//				}
					else{
						ai.alert(resultMsg);
					}
				}
			},function(){},function(){
				ai.closeMask();
			});
		});
	}
}

/**
 * 单击添加模板分组克隆表单
 * @param groupId
 * @param groupCode
 */
function clickAppendTemplateGroupButton(groupId,groupCode){
	ai.openMask();
	ai.ajax('../../template/getGroup',{groupId:groupId},function(data){
		if(data.state){
			data.info.groupIndex = new Date().getTime();//缓存groupIndex用于后期剔除的依据
			$.each(data.info.templateItem,function(i,n){
				n.groupIndex = data.info.groupIndex;//缓存groupIndex用于后期取值使用
			});
			pageData.template.templateGroup.push(data.info);
			$('#_'+groupCode+'_buttons').after(template('_template_group_template',data.info));
			$('[data-toggle="tooltip"]').tooltip();
		}
	},function(){},function(){
		ai.closeMask();
	});
}

/**
 * 单击移除模板分组克隆表单
 * @param groupIndex
 * @param groupId
 * @param groupCode
 */
function clickRemoveTemplateGroupButton(groupIndex,groupId,groupCode){
	var a_index = -1;
	$.each(pageData.template.templateGroup,function(g,group){
		if(!group.groupIndex){group.groupIndex = 0};
		if(groupId == group.groupId && groupIndex == group.groupIndex){
			$('#_'+group.groupCode+'_'+groupIndex).remove();
			a_index = g;
		}
	});
	pageData.template.templateGroup.splice(a_index,1);
}

/**
 * 点击查看已经填报的分组克隆表单
 * @param groupId
 * @param groupCode
 */
function clickDisplayTemplateGroupButton(groupId,groupCode){
	ai.openMask();
	pageData.display = {unitId:pageData.param.unitId,groupId:groupId,pageNum:1,pageSize:2};
	ai.ajax('../../template/getWriteByGroup',pageData.display,function(data){
		if(data.state){
			$('#_group_write_modal_body').html(template('_group_write_record_template',data.info));
			$('#_group_write_modal_dialog').modal('show');
		}
	},function(){},function(){
		ai.closeMask();
	});
}

/**
 * 分页查看已经填报的分组克隆表单
 */
function clickChangeWritePageNumBtn(pageNum,writeType){
	ai.openMask();
	pageData.display.pageNum = pageNum;
	ai.ajax('../../template/getWriteByGroup',pageData.display,function(data){
		if(data.state){
			$('#_group_write_modal_body').html(template('_group_write_record_template',data.info));
		}
	},function(){},function(){
		ai.closeMask();
	});
}

/**
 * 点击编辑历史填报记录的按钮
 */
function clickModifyWriteHistoryButton(writeId){
	ai.confirm('确认','将暂时跳离当前填报页面,您确定要继续进行编辑操作吗?',function(){
		document.location.href= 'index.html?unit='+pageData.param.unitId+'&mwrite='+writeId;
	});
}

/**
 * 填报模板表单验证
 * @returns {Boolean}
 */
function _validTemplateItemForm(){
	var submit = true;//验证结果是否合法标记
	$.each(pageData.template.templateGroup,function(g,group){
		$.each(group.templateItem,function(i,item){
			var bol = _validItemValueByType(item,submit);
			if(!bol){
				submit = bol;
			}
		});
	});
	return submit;
}

/**
 * 生成表单填报结果对象
 * @returns {___anonymous4759_4858}
 */
function _generateWriteRecord(){
	var writeRecord = {templateId:pageData.template.templateId,templateCode:pageData.template.templateCode,writeDetail:[]};
	if(pageData.param.unitId && pageData.param.unitId > 0){
		writeRecord.unitId = pageData.param.unitId;
	}
	if(pageData.param.parentWriteId && pageData.param.parentWriteId > 0){//填报时需建立填报关系的父模板
		writeRecord.parentWriteId = pageData.param.parentWriteId;
	}
	if(pageData.param.productId && 'BUILD_ARCHIVE' == pageData.param.templateCode){//创建商机档案
		writeRecord.writeArchive = {unitId:pageData.param.unitId,productId:pageData.param.productId};
	}else if(pageData.param.archiveId && pageData.param.archiveId > 0){//基于商机档案填报的模板
		writeRecord.writeArchive = {archiveId:pageData.param.archiveId};
	}
	$.each(pageData.template.templateGroup,function(g,group){
		$.each(group.templateItem,function(i,item){
			writeRecord.writeDetail.push({
				groupId:group.groupId,
				groupCode:group.groupCode,
				groupIndex:(group.groupIndex ? group.groupIndex : 0),
				itemId:item.itemId,
				itemCode:item.itemCode,
				itemType:item.itemType,
				itemValue:_getItemValueByType(item)
			});
		});
	});
	if(pageData.param.modifyWriteId){//编辑填报记录信息
		writeRecord.writeId = pageData.param.modifyWriteId;
	}
	return writeRecord;
}

/**
 * 根据控件类型获取输入值
 */
function _getItemValueByType(item){
	if(8 == item.itemType){//单选框
		return $('#_'+item.itemCode+'_'+(item.groupIndex ? item.groupIndex : '0')+' :checked').size() > 0 ? $('#_'+item.itemCode+'_'+(item.groupIndex ? item.groupIndex : '0')+' :checked').val() : '';
	}else{
		return $('#_'+item.itemCode+'_'+(item.groupIndex ? item.groupIndex : '0')).val();
	}
}

/**
 * 根据控件正则验证输入值
 * @param item
 * @returns {Boolean}
 */
function _validItemValueByType(item,submit){
	if(item.itemRegex && !$.trim(_getItemValueByType(item).match(eval(item.itemRegex)))){
		$('#_'+item.itemCode+'_'+(item.groupIndex ? item.groupIndex : '0')+'_tooltip').addClass('text-danger').tooltip(submit?'show':'hide').parent().addClass('text-danger');
		submit = false;
	}else{
		$('#_'+item.itemCode+'_'+(item.groupIndex ? item.groupIndex : '0')+'_tooltip').removeClass('text-danger').tooltip('hide').parent().removeClass('text-danger');
	}
	return submit;
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
		center:{
			paneClass:'layout-center-panel'
		},
		south:{
			paneClass:'layout-south-panel'
		}
	});
}