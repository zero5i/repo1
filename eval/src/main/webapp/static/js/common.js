var myScroll;

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
        data : {provinceId:provinceId},
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
        url  : "ajax/findShop.action",  
        cache : false, 
        data : {shopId:shopId},
        success : function(data, status){
        	
        	$('#detailChart_fromPage').val("#shop_list_page");
        	
        	$.mobile.changePage("#eval_detail_chart", {transition:"slide",reverse:false}, true, true);
        }, 
        error : function(data,status){
        	
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
        data : {shopId:shopId},
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
        	
        	$.mobile.changePage("#insert_edit_shop_page", {transition:"slide",reverse:false}, true, true);
        }, 
        error : function(data,status){
        	
        } 
    });
};	

$(document).on("pageshow","#index_page",function(){ 
	$("#index_page input[type='image']").attr('src','./static/images/first/1_anniu1.png');
});

$(document).on("pageinit","#index_page", function(){
	$("#index_page input[type='image']").on("tap",function(){
		$(this).attr('src','./static/images/first/1_anniu2.png');
		$.mobile.changePage( "#shop_list_page", { 
		    transition: "slideup", 
		    changeHash: true,		    
		});
	});
});


$(document).on("pageshow","#shop_list_page",function(){ // 当进入页面二时
	$("#shop_list_page input[type='image']").attr('src','./static/images/sec/2_anniu1.png');
});

$(document).on("pageinit","#shop_list_page", function(){
	$("#shop_list_page input[type='image']").on("tap",function(){
		$(this).attr('src','./static/images/sec/2_anniu2.png');
		//$.mobile.changePage("detail.action", {transition:"slide",reverse:false}, true, true);
		
		$("#shopPage_id").val("");
    	$("#shopPage_provinceId").val("0");
    	$("#shopPage_cityId").val("");
    	$("#shopPage_typeCode").val("0");
    	$('#shopPage_shopName').val("");
    	$('#shopPage_posCnt').val("");
    	$('#shopPage_perPay').val("");
    	$('#shopPage_spaceSize').val("");
    	
		$.mobile.changePage("#insert_edit_shop_page", {transition:"slide",reverse:false}, true, true);
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
		
		var monthlySales = $('#shopPage_monthlySales').val();
		var monthlyPurchase = $('#shopPage_monthlyPurchase').val();
		var monthlySalary = $('#shopPage_monthlySalary').val();
		var monthlyRent = $('#shopPage_monthlyRent').val();
		var monthlyEnergy = $('#shopPage_monthlyEnergy').val();
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
		        url  : "ajax/evalShop.action",  
		        beforeSend: function(){
		        	
		       	},
		        cache : false, 
		        data : $('#shopForm').serialize(),
		        success : function(data, status){
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
		 $.mobile.changePage("#eval_type_page", {transition:"slide",reverse:true}, true, true);
	}); 
	
	$('#eval_shop_result_list').on("swipeleft",function(){
		$('#detailChart_fromPage').val("#eval_shop_result_list");
		$.mobile.changePage("#eval_detail_chart", {transition:"slide",reverse:false}, true, true);
	});
});

$(document).on("pageinit","#eval_detail_chart", function(){	
	$('#eval_detail_chart').on("swiperight",function(){
		var fromPage = $('#detailChart_fromPage').val();
		$.mobile.changePage(fromPage, {transition:"slide",reverse:true}, true, true);
	}); 
	
	$('#eval_detail_chart').on("swipeleft",function(){
		 $.mobile.changePage("#eval_share_page", {transition:"slide",reverse:false}, true, true);
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
});

$(document).on("pageinit","#advise_page", function(){
	$('#advise_page').on("swiperight",function(){
		 $.mobile.changePage("#eval_shop_page", {transition:"slide",reverse:true}, true, true);
	}); 
});
