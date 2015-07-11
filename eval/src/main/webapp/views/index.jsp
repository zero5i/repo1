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

<script>
	var loginToken = "<s:property value="loginToken"/>";
</script>
<script src="${ctx}/static/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.mobile-1.4.5.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/iscroll.js" type="text/javascript"></script>
<script src="${ctx}/static/js/template.js" type="text/javascript"></script>
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

		<div class="footer">
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
								<s:textfield type="number" name="shopEntity.posCount" data-role="none" id="shopPage_posCnt" maxlength="4"/> 
							</div>
							<div class="one_3_1">
								<div>4</div>
								<div>人均消费:</div>
								<s:textfield type="number" name="shopEntity.perPay" data-role="none" id="shopPage_perPay" maxlength="4"/> 
							</div>
							<div class="one_3_1" style="margin-bottom:0">
								<div>5</div>
								<div>前厅面积:</div>
								<s:textfield type="number" name="shopEntity.spaceSize" data-role="none" id="shopPage_spaceSize" maxlength="8"/>
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
								<s:textfield type="number" name="evaluateEntity.monthlySales" id="shopPage_monthlySales" data-role="none" maxlength="8"/>
							</div>
							<div class="one_3_1">
								<div>7</div>
								<div>月采购额:</div>
								<s:textfield type="number" name="evaluateEntity.monthlyPurchase" id="shopPage_monthlyPurchase" data-role="none" maxlength="8"/>
							</div>					
							<div class="one_3_1">
								<div>8</div>
								<div>每月工资:</div>
								<s:textfield type="number" name="evaluateEntity.monthlySalary" id="shopPage_monthlySalary" data-role="none" maxlength="8"/> 
							</div>
							<div class="one_3_1">
								<div>9</div>
								<div>每月租金:</div>
								<s:textfield type="number" name="evaluateEntity.monthlyRent" id="shopPage_monthlyRent" data-role="none" maxlength="8"/>
							</div>
							<div class="one_3_1">
								<div>10</div>
								<div>每月能耗:</div>
								<s:textfield type="number" name="evaluateEntity.monthlyEnergy" id="shopPage_monthlyEnergy" data-role="none" maxlength="8"/> 
							</div>
							<div class="one_3_1" style="margin-bottom:0">
								<div>10</div>
								<div>其它开销:</div>
								<s:textfield type="number" name="evaluateEntity.monthlyOtherPay" id="shopPage_monthlyOtherPay" data-role="none" maxlength="8"/> 
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
			<img id="evalShopPage_topImage" src="">
		</div>

		<div>
			<img id="evalShopPage_zt1" src="${ctx}/static/images/result/zt1.png">
			<img id="evalShopPage_zt2" src="${ctx}/static/images/result/zt2.png">
			<img id="evalShopPage_zt3" src="${ctx}/static/images/result/zt3.png">
			<img id="evalShopPage_zt4" src="${ctx}/static/images/result/zt4.png">
		</div>

		<div class="footer">
			<input type="image" style="width:280px" src="${ctx}/static/images/result/anniu1.png"/>
		</div>
	</div>
	
	<!-- 状况类型 -->
	<div data-role="page" id="eval_type_page">
		<div id="evalType_header"></div>
		<div class="first_canting"></div>
		<div class="type_saomiaoxian"></div>
		<div class="footer">
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
		<div class="tbl_wapper" id="evalShopResultList_wapper">
		</div>
	</div>

	<!-- 具体项目的分析 -->	
	<div data-role="page" id="eval_result_analasys">
		<div class="header">
			<div class="header-text">餐位数不合理</div>
			<div class="normal-range">餐位数合理范围:10%~20%</div>
			<div class="scope">您的数据是:10%</div>
		</div>
		<div class="resultAnalasys_wapper">
			<div>是否可以调整目前桌椅排列？</div>
			<div>是否可以调整公共空间利间？</div>
			<div>是否可以调整桌椅规格大小？</div>
		</div>
	</div>
	
	<!-- 经营趋势 -->	
	<div data-role="page" id="eval_detail_chart">
		<input type="hidden" id="detailChart_fromPage"> 
		<div id="wrapper">
			<div id="chartScroller">
				<ul>
					<li class="pop_ctzt_1"><div>扩展性:2015-01-21</div></li>
					<li class="pop_ctzt_2"><div>健康型:2015-02-21</div></li>
					<li class="pop_ctzt_3"><div>警惕性:2015-03-21</div></li>
					<li class="pop_ctzt_4"><div>危机型:2015-04-21</div></li>
				</ul>
			</div>
		</div>
		<div class="footer">
			<div style="float:left;width:80px;margin-left:10px;">
				<input type="button" value="返回" id="btn_back_result_list"/>
			</div>
			<div style="float:right;width:80px;margin-right:10px;">
				<input type="button"  value="分享" id="btn_go_share"/>
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
		<div class="footer">
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

		<div class="footer">
			<a >开始分析</a>
		</div>
	</div>
	
	<script id="evalShopResult_template" type="text/html">
	{{each validateBeanList as value i}}
		<table class="tbl_{{ value.color }}" onclick="dispResultDetail({{value.idx}}, '{{value.labelName}}', '{{value.scope}}', '{{value.normalRange}}','{{ value.statusLabel }}');">
			<tr>
				<td>{{ value.labelName }}</td>
				<td>{{ value.normalRange }}</td>
				<td>{{ value.scope }}</td>
				<td>{{ value.statusLabel }}</td>
			</tr>
		</table>
	{{/each}}
	</script>
	
	<script id="evalShopChart_template" type="text/html">
	{{each chartBeanList as value i}}
		<li class="pop_ctzt_{{ value.statusTypeCode }}"><div>{{ value.statusTypeLabel }}&nbsp;{{ value.evaluateDate }}</div></li>
	{{/each}}
	</script>
	
	<script id="resultAnalasys_template" type="text/html">
		<div class="header">
			<div class="header-text">{{ labelName }} {{ statusLabel }}</div>
			<div class="normal-range">{{ labelName }}合理范围:{{ normalRange }}</div>
			<div class="scope">您的数据是:{{ scope }}</div>
		</div>
		<div class="resultAnalasys_wapper">
		{{each evalTipsList as value i}}
			<div>{{i+1}}.{{ value }}</div>
		{{/each}}
		</div>
	</script>
</body>
</html>
