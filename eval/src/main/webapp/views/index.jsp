<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>欢迎来到360°自检</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes">

<link href="${ctx}/static/css/jquery.mobile-1.4.5.min.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/style.css" media="all">

<script src="${ctx}/static/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.mobile-1.4.5.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/iscroll.js" type="text/javascript"></script>
<script src="${ctx}/static/js/common.js" type="text/javascript"></script>
<script>
	$(function(){
		loaded();
	});
</script>

</head>

<body>

	<!-- 首页 -->
	<div data-role="page" id="index_page">
		
		<div class="first_biaoti"></div>
		
		<div class="first_canting"></div>
		
		<div class="first_saomiaoxian"></div>

		<div id="footer">
			<input type="image" width="280px" style="border: 0" src="${ctx}/static/images/first/1_anniu1.png"/>
		</div>
	</div>

	<!-- 餐厅列表 -->
	<div data-role="page" id="shop_list_page">
		<div class="sec_biaoti"></div>
		
		<div class="tbl_wapper">
			<table class="tbl">
				<s:iterator value="shopList" id='shop'> 
					<tr>
						<td class="td1">
							<s:if test="#shop.evalCount != null">
								<a href="#" onclick="redirectDetailChart(${shop.id})">
									<s:property value="#shop.evalCount"/>
								</a>
							</s:if>
							<s:else>
								0
							</s:else>
						</td>
						<td class="td2">
							<a href="#" onclick="redirectEidt(${shop.id})"><s:property value="#shop.shopName"/></a>
						</td>
						<td class="td3">
							<div>&nbsp;</div>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div>
			<input type="image" style="width:280px" src="${ctx}/static/images/sec/2_anniu1.png"/>
		</div>
	</div>

	<!-- 添加、编辑门店 -->
	<div data-role="page" id="insert_edit_shop_page">
		<s:form action="editShop" id="shopForm" name="shopForm" onsubmit="return false">
			<div class="third_shang1">
				<s:textfield name="shopEntity.shopName" data-role="none" id="shopPage_shopName" placeholder="请输入店铺名称" maxlength="10"/>
			</div>
			<div id="main_holder" class="main_holder">
				<div class="scroller">
					<s:hidden name="shopEntity.id" id="shopPage_id"/>
					<div class="main_table">
						<div class="td1"></div>
						<div class="td_header">基础信息</div>
						<div class="td_middle">
							<div class="one_3_1">
								<div>1</div>
								<div>所在省份:</div>
								<s:select name="shopEntity.provinceId" id="shopPage_provinceId"  data-role="none" cssStyle="width:110px;" onchange="findCity()"
								    list="#application['provinceList']"   
								    listKey="id" 
								    listValue="cityName" 
								    headerKey="0"   
								    headerValue="省份">  
								</s:select>
							</div>
							<div class="one_3_2">
								<div></div>
								<s:select name="shopEntity.cityId" id="shopPage_cityId"  data-role="none" cssStyle="width:110px;"
								    list="cityList"   
								    listKey="id" 
								    listValue="cityName" 
								    headerKey="0"   
								    headerValue="市区">  
								</s:select>
							</div>
							<div class="one_3_1">
								<div>2</div>
								<div>餐厅类型:</div>
								<s:select name="shopEntity.typeCode" id="shopPage_typeCode"  data-role="none" cssStyle="width:110px;"
								    list="@com.jmh.server.commom.enmu.ShopType@values()"   
								    listKey="value" 
								    listValue="label" 
								    headerKey="0"   
								    headerValue="餐厅类型">  
								</s:select>
							</div>
							<div class="one_3_1">
								<div>3</div>
								<div>餐位总数:</div>
								<s:textfield name="shopEntity.posCount" data-role="none" id="shopPage_posCnt" maxlength="4"/> 
							</div>
							<div class="one_3_1">
								<div>4</div>
								<div>人均消费:</div>
								<s:textfield name="shopEntity.perPay" data-role="none" id="shopPage_perPay" maxlength="4"/> 
							</div>
							<div class="one_3_1" style="margin-bottom:0">
								<div>5</div>
								<div>前厅面积:</div>
								<s:textfield name="shopEntity.spaceSize" data-role="none" id="shopPage_spaceSize" maxlength="8"/>
							</div>
							
						</div>
						<div class="td_last"></div>
					</div>
					<div class="xia"></div>
					<div class="main_table">
						<div class="td1"></div>
						<div class="td_header">收支明细</div>
						<div class="td_middle">
							<div class="one_3_1">
								<div>6</div>
								<div>月销售额:</div>
								<s:textfield name="evaluateEntity.monthlySales" id="shopPage_monthlySales" data-role="none" maxlength="8"/>
							</div>
							<div class="one_3_1">
								<div>7</div>
								<div>月采购额:</div>
								<s:textfield name="evaluateEntity.monthlyPurchase" id="shopPage_monthlyPurchase" data-role="none" maxlength="8"/>
							</div>					
							<div class="one_3_1">
								<div>8</div>
								<div>每月工资:</div>
								<s:textfield name="evaluateEntity.monthlySalary" id="shopPage_monthlySalary" data-role="none" maxlength="8"/> 
							</div>
							<div class="one_3_1">
								<div>9</div>
								<div>每月租金:</div>
								<s:textfield name="evaluateEntity.monthlyRent" id="shopPage_monthlyRent" data-role="none" maxlength="8"/>
							</div>
							<div class="one_3_1">
								<div>10</div>
								<div>每月能耗:</div>
								<s:textfield name="evaluateEntity.monthlyEnergy" id="shopPage_monthlyEnergy" data-role="none" maxlength="8"/> 
							</div>
							<div class="one_3_1" style="margin-bottom:0">
								<div>10</div>
								<div>其它开销:</div>
								<s:textfield name="evaluateEntity.monthlyOtherPay" id="shopPage_monthlyOtherPay" data-role="none" maxlength="8"/> 
							</div>
						</div>
						<div class="td_last"></div>
					</div>
				
					<div class="third_footer">
						<input type="image" id="btn_analysys" width="250px" src="${ctx}/static/images/third/anniu_1.png"/>
						<p>&nbsp;</p>
					</div>
				</div>
			</div>	
		</s:form>
	</div>
	
	<!-- 评测加载页 -->
	<div data-role="page" id="eval_loader">
		<div>
			<img alt="" src="${ctx}/static/images/four/04_1.gif">
		</div>

		<div>
			<img src="${ctx}/static/images/four/fxz.png"/>
		</div>
	</div>
	
	<!-- 评测结果页 -->
	<div data-role="page" id="eval_shop_page">
		<div>
			<img src="${ctx}/static/images/result/bt1.png">
		</div>

		<div>
			<img src="${ctx}/static/images/result/zt1.png">
			<img src="${ctx}/static/images/result/zt2.png">
			<img src="${ctx}/static/images/result/zt3.png">
			<img src="${ctx}/static/images/result/zt4.png">
		</div>

		<div id="footer">
			<input type="image" style="width:280px" src="${ctx}/static/images/result/anniu1.png"/>
			
		</div>
	</div>
	
	<!-- 状况类型 -->
	<div data-role="page" id="eval_type_page">
		<div>
			扩张型
		</div>

		<div class="first_canting"></div>

		<div class="type_saomiaoxian"></div>
		
		<div id="footer">
			<input type="image" style="width:280px" src="${ctx}/static/images/type/anniu.png"/>
		</div>
	</div>
	
	<!-- 项目诊断结果 -->
	<div data-role="page" id="eval_shop_result_list">
		<table class="rest_top_header">
			<tr>
			<td>检查项目</td>
			<td>合理范围</td>
			<td>您的数据</td>
			<td>自检结果</td>
			</tr>
		</table>
		<div class="tbl_wapper">
			<table class="tbl_normal" >
				<tr>
					<td>餐位数</td>
					<td>180-200</td>
					<td>240</td>
					<td>不合理</td>
				</tr>
			</table>
			<table class="tbl_warn" >
				<tr>
					<td>餐位数</td>
					<td>180-200</td>
					<td>240</td>
					<td>不合理</td>
				</tr>
			</table>
			<table class="tbl_error" >
				<tr>
					<td>餐位数</td>
					<td>180-200</td>
					<td>240</td>
					<td>不合理</td>
				</tr>
			</table>
			<table class="tbl_error" >
				<tr>
					<td>餐位数</td>
					<td>180-200</td>
					<td>240</td>
					<td>不合理</td>
				</tr>
			</table>
			<table class="tbl_error" >
				<tr>
					<td>餐位数</td>
					<td>180-200</td>
					<td>240</td>
					<td>不合理</td>
				</tr>
			</table>
			<table class="tbl_error" >
				<tr>
					<td>餐位数</td>
					<td>180-200</td>
					<td>240</td>
					<td>不合理</td>
				</tr>
			</table>
			<table class="tbl_error" >
				<tr>
					<td>餐位数</td>
					<td>180-200</td>
					<td>240</td>
					<td>不合理</td>
				</tr>
			</table>
			<table class="tbl_error" >
				<tr>
					<td>餐位数</td>
					<td>180-200</td>
					<td>240</td>
					<td>不合理</td>
				</tr>
			</table>
			<table class="tbl_error" >
				<tr>
					<td>餐位数</td>
					<td>180-200</td>
					<td>240</td>
					<td>不合理</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 具体项目的分析 -->	
	<div data-role="page" id="eval_result_analasys">
		<div id="header">
			<h1>餐位数不合理</h1>
		</div>

		<div data-role="content">
			您餐厅的数据：
 
			240
			 
			合理数据区间：
			 
			180~200
		</div>
	</div>
	
	<!-- 经营趋势 -->	
	<div data-role="page" id="eval_detail_chart">
		<div id="wrapper">
			<div id="scroller">
				<ul>
					<li class="pop_ctzt1" style="background-position-y: 20%;"></li>
					<li class="pop_ctzt2" style="background-position-y: 50%;"></li>
					<li class="pop_ctzt3" style="background-position-y: 60%;"></li>
					<li class="pop_ctzt4" style="background-position-y: 20%;"></li>
					<li class="pop_ctzt3" style="background-position-y: 70%;"></li>
					<li class="pop_ctzt4" style="background-position-y: 60%;"></li>
					<li class="pop_ctzt2" style="background-position-y: 30%;"></li>
					<li class="pop_ctzt1" style="background-position-y: 20%;"></li>
					<li class="pop_ctzt4" style="background-position-y: 70%;"></li>
					<li class="pop_ctzt4" style="background-position-y: 40%;"></li>
					<li class="pop_ctzt2" style="background-position-y: 20%;"></li>
					<li class="pop_ctzt4" style="background-position-y: 10%;"></li>
					<li class="pop_ctzt1" style="background-position-y: 20%;"></li>
					<li class="pop_ctzt1" style="background-position-y: 30%;"></li>
					<li class="pop_ctzt3" style="background-position-y: 40%;"></li>
					<li class="pop_ctzt4" style="background-position-y: 70%;"></li>
				</ul>
			</div>
		</div>
	</div>
	
	<!-- 分享 -->
	<div data-role="page" id="eval_share_page">
		<div class="total-count">
			86,990
		</div>
		<div class="word-exp">			
		</div>
		<div class="logo-exp">			
		</div>
		<div class="desc-exp">
			Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar tempor.
		</div>
		<div id="footer">
			<input type="image" style="width:280px" src="${ctx}/static/images/twelf/anniu1.png"/>
		</div>
	</div>
	
	<div data-role="page" id="advise_page">
		<div data-role="header">
			<h1>欢迎来到我的主页</h1>
		</div>

		<div data-role="content">
			意见建议详细信息
		</div>

		<div id="footer">
			<a >开始分析</a>
		</div>
	</div>
</body>
</html>
