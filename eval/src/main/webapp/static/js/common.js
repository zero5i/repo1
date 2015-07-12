var myScroll;
var myScroll2;

function isInteger(s){
	var r = /^[0-9]*[1-9][0-9]*$/; 
	return r.test(s);
}

function loaded() {
	myScroll = new IScroll('#main_holder', { mouseWheel: true });
}

function findCity(){
	
	var provinceId = $('#shopPage_provinceId').val();
	
	$.ajax({ 
        type : "GET", 
        dataType: 'json',
        url  : "ajax/findCityList.action",  
        cache : false, 
        data : {provinceId:provinceId, loginToken : loginToken},
        success : function(data, status){
        	$("#shopPage_cityId").empty();
        	$("<option value='0'>市区</option>").appendTo("#shopPage_cityId")//添加下拉框的option
        	var list = data.cityList;
        	for(var i=0; i<list.length; i++){
        		var o = list[i];
        		$("<option value='"+ o.id +"'>" + o.cityName + "</option>").appendTo("#shopPage_cityId")//添加下拉框的option
        	}
        }, 
        error : function(data,status){
        	$("#shopPage_cityId").empty();
        } 
    });
}

function redirectDetailChart(shopId){
	$.ajax({ 
        type : "GET", 
        dataType: 'json',
        url  : "ajax/findEvalList.action",  
        cache : false, 
        data : {shopId:shopId, loginToken : loginToken},
        success : function(data, status){
        	
        	if(!data.evalPageBean){
        		alert("获取评测记录失败，请重新打开微信后再试.");
        		return;
        	}
        	
        	var pageBean = data.evalPageBean;
        	
        	var html2 = template('evalShopChart_template', pageBean);
        	$("#chartScroller").html(html2);
        	
        	$('#detailChart_fromPage').val("#shop_list_page");
        	
        	$.mobile.changePage("#eval_detail_chart", {transition:"slide",reverse:false}, true, true);
        }, 
        error : function(data,status){
        	console.log(data);
        	alert("获取评测记录失败，请重新打开微信后再试.");
        } 
    });
}

/**
 * 取得名下所有店铺列表
 */
function redirectShopList(){
	$.ajax({ 
        type : "GET", 
        dataType: 'json',
        url  : "ajax/findShopList.action",  
        cache : false, 
        data : {loginToken : loginToken},
        success : function(data, status){
        	
        	if(!data && !data.shopList){
        		alert("获取店铺列表失败，请重新打开微信后再试.");
        		return;
        	}
        	
        	if(data.shopList.length == 0){
        		$.mobile.changePage("#insert_edit_shop_page", {transition:"slide",reverse:false}, true, true);
        		return;
        	}
        	
        	/*var html = template('evalShopList_template', data);
        	$("#shopScroller").html(html);
        	*/
        	
        	$.mobile.changePage( "#shop_list_page", { 
    		    transition: "slideup", 
    		    changeHash: true,		    
    		});
        }, 
        error : function(data,status){
        	console.log(data);
        	alert("获取店铺列表失败，请重新打开微信后再试.");
        } 
    });
}

function redirectEidt(shopId){
	//location.href = "detail.action?shopId="+id;
	//$.mobile.changePage("detail.action?shopId="+id, {transition:"slide",reverse:false}, true, true);
	$.ajax({ 
        type : "GET", 
        dataType: 'json',
        url  : "ajax/findShop.action",
        cache : false, 
        data : {shopId:shopId, loginToken:loginToken},
        success : function(data, status){
        	//alert(data);
        	var shop = data.shopEntity;
        	
        	var list = data.cityList;
        	for(var i=0; i<list.length; i++){
        		var o = list[i];
        		$("<option value='"+ o.id +"'>" + o.cityName + "</option>").appendTo("#shopPage_cityId")//添加下拉框的option
        	}        	
        	
        	$("#shopPage_id").val(shop.id);
        	$("#shopPage_provinceId").val(shop.provinceId);
        	$("#shopPage_cityId").val(shop.cityId);
        	$("#shopPage_typeCode").val(shop.typeCode);
        	$('#shopPage_shopName').val(shop.shopName);
        	$('#shopPage_posCnt').val(shop.posCount);
        	$('#shopPage_perPay').val(shop.perPay);
        	$('#shopPage_spaceSize').val(shop.spaceSize);
        	$('#shopPage_foodCount').val(shop.foodCount);
        	
        	$.mobile.changePage("#insert_edit_shop_page", {transition:"slide",reverse:false}, true, true);
        }, 
        error : function(data,status){
        	
        } 
    });
};	

$(document).on("pageshow","#index_page",function(){ 
	
});

$(document).on("pageinit","#index_page", function(){
	$("#index_page input[type='image']").on("tap",function(){
		var currObj = $(this);
		currObj.attr('src','./static/images/first/1_anniu2.png');
		setTimeout(function(){
			currObj.attr('src','./static/images/first/1_anniu1.png');
			redirectShopList();
		}, 200);
	});
});


$(document).on("pageshow","#shop_list_page",function(){ // 当进入页面二时
	
});

$(document).on("pageinit","#shop_list_page", function(){
	$("#shop_list_page input[type='image']").on("tap",function(){
		
		var currObj = $(this);
		
		currObj.attr('src','./static/images/sec/2_anniu2.png');
		
		setTimeout(function(){
			currObj.attr('src','./static/images/sec/2_anniu1.png');
			
			$("#shopPage_id").val("");
	    	$("#shopPage_provinceId").val("0");
	    	$("#shopPage_cityId").val("");
	    	$("#shopPage_typeCode").val("0");
	    	$('#shopPage_shopName').val("");
	    	$('#shopPage_posCnt').val("");
	    	$('#shopPage_perPay').val("");
	    	$('#shopPage_spaceSize').val("");
	    	$('#shopPage_foodCount').val("");
	    	
			$.mobile.changePage("#insert_edit_shop_page", {transition:"slide",reverse:false}, true, true);
		}, 200);
	});
}); 

$(document).on("pageinit","#insert_edit_shop_page",function(){ // 当进入页面三时
	$("#insert_edit_shop_page input[type='image']").on("tap",function(){
		
		var shopName = $('#shopPage_shopName').val();
		var cityId = $('#shopPage_cityId').val();
		var typeCode = $('#shopPage_typeCode').val();
		var posCnt = $('#shopPage_posCnt').val();
		var perPay = $('#shopPage_perPay').val();
		var spaceSize = $('#shopPage_spaceSize').val();
		var foodCount = $('#shopPage_foodCount').val();
		
		var monthlySales = $('#shopPage_monthlySales').val();
		var monthlyPurchase = $('#shopPage_monthlyPurchase').val();
		var monthlySalary = $('#shopPage_monthlySalary').val();
		var monthlyRent = $('#shopPage_monthlyRent').val();
		var monthlyEnergy = $('#shopPage_monthlyEnergy').val();
		var monthlyGroupBuy = $('#shopPage_monthlyGroupBuy').val();
		var monthlyOtherPay = $('#shopPage_monthlyOtherPay').val();
				
		if(!shopName){
			alert("店铺名称不能为空.");
			return;
		}
		
		if(!cityId){
			alert("请选择城市.");
			return;
		}
		
		if(!typeCode){
			alert("请选择店铺类型.");
			return;
		}
		
		if(!posCnt || !isInteger(posCnt)){
			alert("请输入店合的餐位数.");
			return;
		}

		if(!perPay || !isInteger(perPay)){
			alert("请输入合法的人均消费.");
			return;
		}
		
		if(!spaceSize || !isInteger(spaceSize)){
			alert("请输入合法的店铺面积.");
			return;
		}
		
		if(!foodCount || !isInteger(foodCount)){
			alert("请输入合法的菜品数量.");
			return;
		}
		
		if(!monthlySales || !isInteger(monthlySales)){
			alert("请输入合法的月销售额.");
			return;
		}
		
		if(!monthlyPurchase || !isInteger(monthlyPurchase)){
			alert("请输入合法的月采购额.");
			return;
		}
		
		if(!monthlySalary || !isInteger(monthlySalary)){
			alert("请输入合法的每月工资.");
			return;
		}

		if(!monthlyRent || !isInteger(monthlyRent)){
			alert("请输入合法的每月租金.");
			return;
		}
		
		if(!monthlyEnergy || !isInteger(monthlyEnergy)){
			alert("请输入合法的每月能耗.");
			return;
		}
		
		if(!monthlyGroupBuy){
			monthlyGroupBuy = 0;
		}else{
			if(!isInteger(monthlyGroupBuy)){
				alert("请输入合法的团购收入.");
				return;
			}
		}
		
		if(!monthlyOtherPay){
			monthlyOtherPay = 0;
		}else{
			if(!isInteger(monthlyOtherPay)){
				alert("请输入合法的每月其他开销.");
				return;
			}
		}
		
		$(this).attr('src','./static/images/third/anniu_2.png');
		$.mobile.changePage( "#eval_loader", {
  			transition: "pop",
  			reverse: false,
  			changeHash: false
  		});
				
		setTimeout(function(){
			$.ajax({ 
		        type : "POST", 
		        dataType: 'json',
		        async : false,
		        url  : "ajax/evalShop.action?loginToken=" + loginToken,  
		        beforeSend: function(){
		        	
		       	},
		        cache : false, 
		        data : $('#shopForm').serialize(),
		        success : function(data, status){
		        	
		        	if(!data || !data.evalPageBean){
		        		alert("评测失败，请重新打开微信后再试.");
			        	$.mobile.changePage('#insert_edit_shop_page',"pop",false,false);
			        	return;
		        	}
		        	
		        	var pageBean = data.evalPageBean;
		        	
		        	var val = pageBean.evaluateValue;
		        	
		        	$('#evalShopPage_zt1').hide();
		        	$('#evalShopPage_zt2').hide();
		        	$('#evalShopPage_zt3').hide();
		        	$('#evalShopPage_zt4').hide();
		        	
		        	var imgSrc = ""
		        	if(val > 0){
			        	// 盈利
		        		imgSrc = "./static/images/result/bt1.png";
		        		$('#evalShopPage_zt1').show();
		        	}else if(val == 0){
		        		// 平衡
		        		imgSrc = "./static/images/result/bt2.png";
		        		$('#evalShopPage_zt2').show();
			        	$('#evalShopPage_zt3').show();
			        	$('#evalShopPage_zt4').show();
		        	}else if(val < 0){
		        		// 亏本
		        		imgSrc = "./static/images/result/bt3.png";
		        		$('#evalShopPage_zt2').show();
			        	$('#evalShopPage_zt3').show();
		        	}
		        	
		        	$('#evalShopPage_topImage').attr('src', imgSrc);
		        	
		        	// 状况类型设定
		        	$('#evalType_header').html(pageBean.evalType);
		        	
		        	var html1 = template('evalShopResult_template', pageBean);
		        	$("#evalShopResultList_wapper").html(html1);
		        	
		        	var html2 = template('evalShopChart_template', pageBean);
		        	$("#chartScroller").html(html2);
		        	
		    		$.mobile.changePage('#eval_shop_page',"pop",false,false);
		        }, 
		        error : function(data, status){
		        	alert("评测失败，请稍候再试.");
		        	$.mobile.changePage('#insert_edit_shop_page',"pop",false,false);
		        } 
		    });
		}, 3000);
	});
});

function dispResultList(shopId){
	$.ajax({ 
        type : "GET", 
        dataType: 'json',
        url  : "ajax/findEvalList.action",  
        cache : false, 
        data : {shopId:shopId, loginToken : loginToken},
        success : function(data, status){
        	
        	if(!data.evalPageBean){
        		alert("获取评测记录失败，请重新打开微信后再试.");
        		return;
        	}
        	
        	var pageBean = data.evalPageBean;

        	var html1 = template('evalShopResult_template', pageBean);
        	$("#evalShopResultList_wapper").html(html1);
        	
        	$('#shopResultList_fromPage').val("#shop_list_page");
        	
        	$.mobile.changePage("#eval_shop_result_list", {transition:"slide",reverse:false}, true, true);
        }, 
        error : function(data,status){
        	console.log(data);
        	alert("获取评测记录失败，请重新打开微信后再试.");
        } 
    });
}

/**
 * 显示评测结果详细
 * @param idx
 */
function dispResultDetail(idx, labelName, scope, normalRange, statusLabel){
	$.ajax({ 
        type : "GET", 
        dataType: 'json',
        url  : "ajax/findEvalValidate.action",
        cache : false, 
        data : {validateTypeValue:idx, loginToken: loginToken},
        success : function(data, status){
        	//alert(data);
        	data.labelName = labelName;
        	data.scope = scope;
        	data.normalRange = normalRange;
        	data.statusLabel = statusLabel;
        	
        	var html1 = template('resultAnalasys_template', data);
        	$("#eval_result_analasys").html(html1);
        	
        	$.mobile.changePage("#eval_result_analasys", {transition:"slide",reverse:false}, true, true);
        }, 
        error : function(data,status){
        	
        } 
    });
}

$(document).on("pageshow","#insert_edit_shop_page",function(){ 
	loaded();
	$("#insert_edit_shop_page input[type='image']").attr('src','./static/images/third/anniu_1.png');
});


$(document).on("pageinit","#eval_shop_page", function(){
	$("#eval_shop_page input[type='image']").on("tap",function(){
		$(this).attr('src','./static/images/result/anniu2.png');
		
		$.mobile.changePage("#advise_page", {transition:"slide",reverse:false}, true, true);
	});	
	
	$('#eval_shop_page').on("swiperight",function(){
		 $.mobile.changePage("#shop_list_page", {transition:"slide",reverse:true}, true, true);
	}); 
	
	$('#eval_shop_page').on("swipeleft",function(){
		 $.mobile.changePage("#eval_type_page", {transition:"slide",reverse:false}, true, true);
	});  
});
$(document).on("pageshow","#eval_shop_page", function(){
	$("#eval_shop_page input[type='image']").attr('src','./static/images/result/anniu1.png');
});

$(document).on("pageinit","#eval_type_page", function(){
	
	$("#eval_type_page input[type='image']").on("tap",function(){
		
		$(".type_saomiaoxian").css({"background-image":"url(./static/images/type/saomiaoxian.png)"});
		$(".type_saomiaoxian").css({"-webkit-animation-name":"myType"});
		//$('.type_saomiaoxian').css("backgroundImage", "url(./static/images/type/saomiaoxian.png)");
		setTimeout(function(){
			$(".type_saomiaoxian").css({"-webkit-animation-name":""});
			$(".type_saomiaoxian").css({"background-image":""});
		}, 2000);
		
		setTimeout(function(){
			$('#shopResultList_fromPage').val('#eval_type_page');
			$.mobile.changePage("#eval_shop_result_list", {transition:"slide",reverse:false}, true, true);
		}, 2300);
		
	});	
	
	$('#eval_type_page').on("swiperight",function(){
		 $.mobile.changePage("#eval_shop_page", {transition:"slide",reverse:true}, true, true);
	}); 
	
	/* $('#eval_type_page').on("swipeleft",function(){
		 $.mobile.changePage("#eval_shop_result_list", {transition:"slide",reverse:false}, true, true);
	});  */
});

$(document).on("pageinit","#eval_shop_result_list", function(){	
	$('#eval_shop_result_list').on("swiperight",function(){
		var fromPage = $('#shopResultList_fromPage').val();
		if(!fromPage){
			frontPage = '#eval_type_page';
		}
		$.mobile.changePage(fromPage, {transition:"slide",reverse:true}, true, true);
	}); 
	
	$('#eval_shop_result_list').on("swipeleft",function(){
		$('#detailChart_fromPage').val("#eval_shop_result_list");
		$.mobile.changePage("#eval_detail_chart", {transition:"slide",reverse:false}, true, true);
	});
});

$(document).on("pageinit","#eval_detail_chart", function(){
	/*$('#eval_detail_chart').on("swiperight",function(){
		var fromPage = $('#detailChart_fromPage').val();
		$.mobile.changePage(fromPage, {transition:"slide",reverse:true}, true, true);
	}); */
	
	/*$('#eval_detail_chart').on("swipeleft",function(){
		 $.mobile.changePage("#eval_share_page", {transition:"slide",reverse:false}, true, true);
	});*/
	$('#btn_back_result_list').bind("click", function(){
		var fromPage = $('#detailChart_fromPage').val();
		$.mobile.changePage(fromPage, {transition:"slide",reverse:true}, true, true);
	});
	
	/*$('#btn_go_share').on("tap", function(){
		$.mobile.changePage("#eval_share_page", {transition:"slide",reverse:false}, true, true);
	});*/
	$('#btn_go_share').bind('click', function(){
		$.mobile.changePage("#eval_share_page", {transition:"slide",reverse:false}, true, true);
	});
});

$(document).on("pageshow","#eval_detail_chart", function(){
	myScroll2 = new IScroll('#wrapper', { 
		//eventPassthrough: true, 
		scrollX: true, 
		scrollY: false, 
		//preventDefault: true,
		onScrollMove: function(){
			start = this.x - this.maxScrollX;
			console.log("scrollMove " + start);
		}
	});	
	var start = 0;
	var end = 0;
/*	myScroll2.on('scrollMove', function(){
		start = this.x - this.maxScrollX;
		console.log("scrollMove " + start);
	});*/
	
	myScroll2.on('scrollStart', function(){
		start = this.x - this.maxScrollX;
		console.log(start);
	});
	myScroll2.on('scrollEnd', function(){
		end = this.x - this.maxScrollX;
		console.log(this.maxScrollX + " -- " + end);
		
		if(start == end && end == 0 ){
			
		}
	});
});

$(document).on("pageinit","#eval_result_analasys", function(){
	$('#eval_result_analasys').on("swiperight",function(){
		 $.mobile.changePage("#eval_shop_result_list", {transition:"slide",reverse:true}, true, true);
	}); 
});

$(document).on("pageinit","#eval_share_page", function(){
	$('#eval_share_page').on("swiperight",function(){
		 $.mobile.changePage("#eval_detail_chart", {transition:"slide",reverse:true}, true, true);
	}); 
	
	$("#eval_share_page input[type='image']").on("tap",function(){
		var currObj = $(this); 
		currObj.attr('src','./static/images/twelf/anniu2.png');
		setTimeout(function(){
			currObj.attr('src','./static/images/twelf/anniu1.png');
		}, 300);
		
		var url = "http://mp.weixin.qq.com/s?__biz=MjM5Nzk1ODcxMw==&mid=206019690&idx=1&sn=21efcebc08577fd3f712aa4b80c29239#rd";
		location.href = url;
		/*$.mobile.changePage( url, {
  			transition: "pop",
  			reverse: false,
  			changeHash: false
  		});*/
	});
});

$(document).on("pageinit","#advise_page", function(){
	$('#advise_page').on("swiperight",function(){
		 $.mobile.changePage("#eval_shop_page", {transition:"slide",reverse:true}, true, true);
	}); 
});
