<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上图书商城首页</title>
<link href="css/top.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/leftmenu.css" rel="stylesheet" type="text/css" />
<link href="css/body.css" rel="stylesheet" type="text/css" />
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/userIndex.js"></script>
<style>
* {
	margin: 0 0 0 0;
}

body, td, th {
	font-family: 新宋体;
	font-size: 14px;
}

a {
	text-decoration: none
}

a:link {
	color: #8A807F;
}

a:visited {
	color: #8A807F;
}

a:hover {
	color: red;
	text-decoration: underline
}

a:active {
	color: #f30
}

img {
	border-top-width: 0px;
	border-left-width: 0px;
	border-bottom-width: 0px;
	border-right-width: 0px
}

.box {
	float: left;
	width: 1022px
}

.box .boxpadding {
	padding-right: 0px;
	padding-left: 0px;
	margin-bottom: 0px;
	padding-bottom: 0px;
	padding-top: 0px
}

#Slide {
	clear: both;
	border-right: #fff 0px solid;
	border-top: #fff 0px solid;
	margin-bottom: 0px;
	overflow: hidden;
	border-left: #fff 0px solid;
	width: 1024px;
	border-bottom: #fff 0px solid;
	height: 100px
}

#Slide A {
	color: #000
}

.img {
	border-right: #fff 0px solid;
	background: #fff;
	margin: 0px auto;
	border-left: #fff 0px solid;
	text-align: center
}

.boxpadding {
	border-right: #fff 0px solid;
	border-top: #fff 0px solid;
	background: #f8f8f8;
	margin-bottom: 1px;
	border-left: #fff 0px solid;
	padding-top: 1px;
	border-bottom: #fff 0px solid
}

.thumb_title {
	margin-top: 5px;
	background: #fff;
	filter: alpha(opacity = 0);
	width: 80px;
	text-indent: 10px;
	line-height: 25px;
	position: absolute;
	height: 15px;
	-moz-opacity: 0.3
}

#Slide_Thumb {
	margin-top: 57px;
	margin-left: 0px;
	position: absolute
}

.thumb_on {
	display: inline;
	float: left;
	margin-left: 1px;
	cursor: pointer
}

.thumb_off {
	display: inline;
	float: left;
	margin-left: 1px;
	cursor: pointer
}

.thumb_off {
	filter: alpha(opacity = 50);
	-moz-opacity: 0.5
}

.thumb_on {
	filter: alpha(opacity = 90);
	-moz-opacity: 1
}

.thumb_off IMG {
	border-right: #fff 8px solid;
	border-top: #fff 2px solid;
	margin-top: 20px;
	border-left: #fff 1px solid;
	width: 15px;
	border-bottom: #fff 1px solid;
	height: 16px
}

.thumb_on IMG {
	border-right: #fff 8px solid;
	border-top: #fff 2px solid;
	margin-top: 20px;
	border-left: #fff 1px solid;
	width: 17px;
	border-bottom: #fff 0px solid;
	height: 18px
}

body, p {
	margin: 0;
	padding: 0;
	text-align: center;
	font: normal 14px/180% Tahoma, sans-serif;
}


body, div, ul, li {
	margin: 0;
	padding: 0;
}

ul {
	list-style-type: none;
}

body {
	text-align: center;
	font: 12px/20px Arial;
}

#box {
	position: relative;
	width: 1020px;
	height: 120px;
	background: #fff;
	border-radius: 0px;
	border: 1px dashed #CCC;
	margin: 0px auto;
	cursor: pointer;
}

#box .list {
	position: relative;
	width: 1020px;
	height: 120px;
	overflow: hidden;
}

#box .list ul {
	position: absolute;
	top: 0;
	left: 0;
}

#box .list li {
	width: 1020px;
	height: 120px;
	overflow: hidden;
}

#box .count {
	position: absolute;
	right: 0;
	bottom: 5px;
}

#box .count li {
	color: #fff;
	float: left;
	width: 20px;
	height: 20px;
	cursor: pointer;
	margin-right: 5px;
	overflow: hidden;
	background: #F90;
	opacity: 0.7;
	filter: alpha(opacity = 70);
	border-radius: 20px;
}

#box .count li.current {
	color: #fff;
	opacity: 1;
	filter: alpha(opacity = 100);
	font-weight: 700;
	background: #f60;
}

#tmp {
	width: 100px;
	height: 100px;
	background: red;
	position: absolute;
}

#demo {
	background: #FFF;
	overflow: hidden;
	border: 1px dashed #CCC;
	width: 1022px;
}

#demo img {
	border: 3px solid #F2F2F2;
}

#indemo {
	float: left;
	width: 1500%;
}

#demo1 {
	float: left;
}

#demo2 {
	float: left;
}
</style>
<script type="text/javascript"> 
window.onload = function ()
{
	var oBox = document.getElementById("box");
	var oList = oBox.getElementsByTagName("ul")[0];
	var aImg = oBox.getElementsByTagName("img");
	var timer = playTimer = null;
	var index = i = 0;
	var bOrder = true;
	var aTmp = [];
	var aBtn = null;
	
	//生成数字按钮
	for (i = 0; i < aImg.length; i++) aTmp.push("<li>" + (i + 1) + "</li>");
	
	//插入元素
	var oCount = document.createElement("ul");
	oCount.className = "count";
	oCount.innerHTML = aTmp.join("");
	oBox.appendChild(oCount);	
	aBtn = oBox.getElementsByTagName("ul")[1].getElementsByTagName("li");
	
	//初始化状态
	cutover();
	
	//按钮点击切换
	for (i = 0; i < aBtn.length; i++)
	{
		aBtn[i].index = i;
		aBtn[i].onmouseover = function ()
		{
			index = this.index;
			cutover()
		}
	}
	
	function cutover()
	{
		for (i = 0; i < aBtn.length; i++) aBtn[i].className = "";
		aBtn[index].className = "current";			
		startMove(-(index * aImg[0].offsetHeight))
	}
	
	function next()
	{
		bOrder ? index++ : index--;
		index <= 0 && (index = 0, bOrder = true);
		index >= aBtn.length - 1 && (index = aBtn.length - 1, bOrder = false)
		cutover()
	}
	
	playTimer = setInterval(next, 3000);
	
	//鼠标移入展示区停止自动播放
	oBox.onmouseover = function ()
	{
		clearInterval(playTimer)
	};
	
	//鼠标离开展示区开始自动播放
	oBox.onmouseout = function ()
	{
		playTimer = setInterval(next, 3000)
	};
	function startMove(iTarget)
	{
		clearInterval(timer);
		timer = setInterval(function ()
		{
			doMove(iTarget)
		}, 30)	
	}
	function doMove (iTarget)
	{		
		var iSpeed = (iTarget - oList.offsetTop) / 10;
		iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed);		
		oList.offsetTop == iTarget ? clearInterval(timer) : oList.style.top = oList.offsetTop + iSpeed + "px"
	}
};
</script>
</head>
<body>
	<!--top-->
	<div align="center">
		<table width="1024" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="200" rowspan="2" valign="top" bgcolor="#FCFCFC"><img
					src="images/logo.jpg" width="200" height="80" /></td>
				<td width="824" height="27" valign="top" bgcolor="#FAFBF7">
					<div align="right">
						<font color="red">${error }</font>hi
						<c:choose>
							<c:when test="${user.name==null }">游客,欢迎来我网上图书商城！请<a href="login.htm">登录</a>
								<a href="register.htm">免费注册</a>&nbsp;<a href="login.htm">我的订单</a>
							</c:when>
							<c:otherwise>
								<font size="4" style="color: red"><c:if test="${user.member }">尊贵的会员</c:if>${user.name }</font>,欢迎来我网上图书商城！
								<a href="logout.htm">安全退出</a>&nbsp;<a href="searchOrderByusername.htm?username=${user.name}">我的订单</a>&nbsp;<a href="selectByUsername.htm">修改信息</a>
							</c:otherwise>
						</c:choose>
						<img src="images/cart.jpg"><a href="mycart.htm">购物车${size>=0?size:0}种</a>
					</div>
				</td>
			</tr>
			<tr>
				<td height="53" valign="top" bgcolor="#FAFBF7">
					<div align="center">
						<div id="keyword">
						<form action="searchKeyword.htm" method="post" name="keywordform" >
							<font size="4" class="font_name">关键字：</font> <input
								name="keyword"
								style="height: 20px; position: relative; top: -3px; font-size: 12pt; color: rgb(85, 85, 85);"
								onFocus="if(this.value=='请输入您所要查找的商品名称'){this.value='';}"
								onBlur="if(this.value==''){this.value='请输入您所要查找的商品名称';}"
								value="请输入您所要查找的商品名称" size="35" type="text" /> &nbsp; <input
								id="keywordBut" onclick="searchKeyword()" src="images/go.jpg"
								style="position: relative; top: 5px;" border="0" height="25"
								type="image" width="50" />
								</form>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<!--幻灯片图start-->
	<div id="box">
		<div class="list">
			<ul>
				<li><img src="images/p1.jpg" width="1022" height="120" /></li>
				<li><img src="images/p2.jpg" width="1022" height="120" /></li>
				<li><img src="images/p3.jpg" width="1022" height="120" /></li>
				<li><img src="images/p4.jpg" width="1022" height="120" /></li>
			</ul>
		</div>
		<!--幻灯片图end-->
	</div>
	<!--middle_menu-->
	<div align="center">
		<table width="1024" border="0" cellpadding="0" cellspacing="0">
			<!--DWLayoutTable-->
			<tr>
				<td width="1024" height="31" bgcolor="#FAFBF7" valign="top">
					<!-- BEGIN Menu -->
					<div class="menu">
						<ul>
							<c:choose>
								<c:when test="${empty categoryList}">
									<li><a href="#" target="_blank">无类别</a></li>
								</c:when>
								<c:otherwise>
									<c:forEach items="${categoryList}" var="category">
										<c:if test="${category.parentId==0}">
											<li><a href="searchCategory.htm?categoryId=${category.id}" target="_self">${category.text}</a>
												<c:if test="${category.leaf==false }">
													<table>
														<tr>
															<td>
																<ul>
																	<c:set var="pid" value="${category.id}" />
																	<c:forEach items="${categoryList }" var="category">
																		<c:if test="${category.parentId==pid }">
																			<li><a href="searchCategory.htm?categoryId=${category.id}" target="_self">&nbsp;&nbsp;&nbsp;${category.text}</a></li>
																		</c:if>
																	</c:forEach>
																</ul>
															</td>
														</tr>
													</table>
												</c:if></li>
										</c:if>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</ul>
					</div> <!-- END Menu -->
				</td>
			</tr>
		</table>
	</div>
	<!--滚动图片-->
	<div align="center">
		<table width="1024" border="0" cellpadding="0" cellspacing="0"
			bgcolor="#D6F7F8">
			<!--DWLayoutTable-->
			<tr>
				<td valign="top">
					<div id="demo">
						<div id="indemo">
							<div id="demo1">
								<!-- 展示所有的商品图片 -->
								<c:choose>
									<c:when test="${empty productList}">

									</c:when>
									<c:otherwise>
										<c:forEach items="${productList}" var="product">
											<a href="#"><img width="94" height="76"
												src="images/product/${product.photo }" border="0" /></a>
										</c:forEach>
									</c:otherwise>
								</c:choose>

							</div>
							<div id="demo2"></div>
						</div>
					</div> <script>
				
				var speed=30;
				var tab=document.getElementById("demo");
				var tab1=document.getElementById("demo1");
				var tab2=document.getElementById("demo2");
				tab2.innerHTML=tab1.innerHTML;
				function Marquee(){
				if(tab2.offsetWidth-tab.scrollLeft<=0)
				tab.scrollLeft-=tab1.offsetWidth
				else{
				tab.scrollLeft++;
				}
				}
				var MyMar=setInterval(Marquee,speed);
				tab.onmouseover=function() {clearInterval(MyMar)};
				tab.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
				
				</script>
				</td>
			</tr>
		</table>
	</div>


	<!--middle-->
	<div align="center">
		<table width="1024"  border="0" cellpadding="0" cellspacing="0">
			<!--DWLayoutTable-->
			<tr>
				<!--body_left-->
				<td width="200" height="1000" rowspan="2" valign="top" bgcolor="#FAFBF7">
					<table width="200" border="0" cellpadding="0" cellspacing="0">
						<!--body_left_menu-->
						<tr bgcolor="#FFFFFF">
							<td width="200" height="0" valign="top">
								<!--左菜单start--> <!--左菜单end-->
							</td>
						</tr>

						<!--body_left_login -->
						<tr>
							<td width="200" height="128" valign="top">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tbody>
										<tr>
											<td align="center" height="40"><img
												src="images/login.gif" height="39" width="100%"></td>
										</tr>
									</tbody>
								</table>
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td align="center" bgcolor="#FAFBF7">
											<form name="loginForm" action="login.htm" method="post"
												onsubmit="return CheckLoginForm()">
												<table border="0" cellpadding="0" cellspacing="0"
													width="92%">

													<tr>
														<td align="left" height="25">
															<div align="right">用户名：</div>
														</td>
														<td>
															<div align="left">
																<input name="username" size="10"
																	style="font-size: 11px;" placeholder="请输入用户名"
																	type="text" />
															</div>
														</td>
														<td rowspan="2"><input src="images/down.gif"
															border="0" height="45" type="image" /></td>
													</tr>
													<tr>
														<td align="left" height="25" width="83%">
															<div align="right">密 码：</div>
														</td>
														<td>
															<div align="left">
																<input name="password" size="10"
																	style="font-size: 11px;" placeholder="请输入密码"
																	type="password" />
															</div>
														</td>
													</tr>
													<tr>
														<td colspan="3" height="30">
															<p align="center">
																[ <a href="register.htm">新用户注册</a>] &nbsp;[ <a
																	href="passwdview.php.htm"
																	onClick="js_callpage(href);return false">忘记密码</a>]
															</p>
														</td>
													</tr>
												</table>
											</form>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tbody>
										<tr>
											<td bgcolor="#CECECE" height="1"><img
												src="images/line.gif" height="1" width="1"></td>
										</tr>
									</tbody>
								</table> <!--body_left_login_end-->
							</td>
						</tr>
						<!--body_left_message-->
						<tr>
							<td height="128" valign="top">
								<table>
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0"
												width="100%">
												<tr>
													<td colspan="3"><img src="images/jingcai.gif"
														height="32" width="202"></td>
												</tr>
												<tr>
													<td align="center" bgcolor="#FAFBF7">
														<table bgcolor="#FFFFFF" border="0" width="100%">
															<tr>

																<td align="center" bgcolor="#FAFBF7">
																	<table bgcolor="#FFFFFF" border="0" width="100%">
																		<tr>
																			<td bgcolor="#FFFFFF" height="80">
																				<div align="left">
																					<DIV>
																						<MARQUEE style="WIDTH: 202; HEIGHT: 260"
																							scrollAmount=1 scrollDelay=77 direction=up
																							width=200 height=160 onmouseout="this.start()"
																							onmouseover="this.stop()"><center><span style='font-weight:bold'>公告信息</span></center><br>
																							1.会员权益更新及会费调整<br>
																							2.商铺客服电话更新&nbsp;&nbsp;&nbsp;&nbsp;
																							3.新书折扣注销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							4.夏花绚烂，阅享生活
																						</MARQUEE>
																					</DIV>
																				</div>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
													<td rowspan="3" bgcolor="#CECECE" width="1"><img
														src="images/line.gif" height="1" width="1"></td>
												</tr>
												<tr>
													<td bgcolor="#FAFBF7" height="8"><img
														src="images/line.gif" height="1" width="1"></td>
												</tr>
												<tr>
													<td bgcolor="#CECECE" height="1"><img
														src="images/line.gif" height="1" width="1"></td>
												</tr>
											</table>
										</td>
									</tr>
									<!--body_left_message_end-->
								</table>
							</td>
						</tr>
						<!--body_left_shopkeeper-->
						<tr>
							<td height="164" valign="top">
								<table>
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0"
												width="100%">
												<tr>
													<td colspan="3"><img src="images/jingcai.gif"
														height="32" width="100%"></td>
												</tr>
												<tr>
													<td align="center" bgcolor="#FAFBF7">
														<table bgcolor="#FFFFFF" border="0" width="90%">
															<tr>
																<td align="center" bgcolor="#e2e4f0">
																	<table bgcolor="#FFFFFF" border="0" width="100%">
																		<tr>
																			<td bgcolor="#FFFFFF" height="80">
																				<div align="left">
																					店&nbsp;&nbsp;&nbsp;名: 图书商城<br />
																					地&nbsp;&nbsp;&nbsp;址: 益阳湖南城市学院北门蓝色公寓<br />
																					店&nbsp;&nbsp;&nbsp;主: 尹超<br />
																					电&nbsp;&nbsp;&nbsp;话: 0853－4663430<br />
																					Q&nbsp;&nbsp;&nbsp;&nbsp;Q: 1162454662<br />
																					手&nbsp;&nbsp;&nbsp;机: 18230526559<br /> 个人名言:
																					java是世界上最好的语言！<br /> <a href="registerShop.htm"><center>申请本站商铺入驻</center></a><br />
																				</div>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
													<td rowspan="3" bgcolor="#CECECE" width="1"><img
														src="images/line.gif" height="1" width="1"></td>
												</tr>
												<tr>
													<td bgcolor="#FAFBF7" height="8"><img
														src="images/line.gif" height="1" width="1"></td>
												</tr>
												<tr>
													<td bgcolor="#CECECE" height="1"><img
														src="images/line.gif" height="1" width="1"></td>
												</tr>
											</table>
										</td>
									</tr>
									<!---body_left_shopkeeper_end-->
								</table>
							</td>
						</tr>
						<!-- body_left_advertising-->
						<tr>
							<td width="201" height="440" bgcolor="#FAFBF7"><img
								src="images/4.jpg"></td>
							<!-- body_left_advertising_end-->
						</tr>
					</table>
				</td>
				<!--body_right-->
				<td width="824" height="1000" valign="top" bgcolor="#FAFBF7">
					<table>
					<center><font color="red" size=5>${Msg}</font></center>
						<!--展示最后25条商品信息或者按类别查询展示-->
						<c:choose>
							<c:when test="${empty productList25Last}">
							</c:when>
							<c:otherwise>
								<c:forEach items="${productList25Last}" var="product"
									varStatus="status">
									<!-- 商品每5个换一行 -->
									<c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">
										<tr valign="top">
									</c:if>
									<td width="10" height="200">&nbsp;</td>
									<td width="160" height="200" align="left">
											<table>
												<tr>
													<td width="154.8" height="100"><img width="94"
														height="76" src="images/product/${product.photo }" title="${product.description}"/></td>
												</tr>
												<tr>
													<td width="154.8" height="20">${product.name }</td>
												</tr>
												<tr>
													<td width="154.8" height="20"><img
														src="images/qian.gif"><font size="4" color="#FF0000"><c:if test="${user.member }"><fmt:formatNumber value="${product.memberPrice }"
											pattern="##.##" minFractionDigits="2" maxFractionDigits="2"></fmt:formatNumber></c:if>
											<c:if test="${!user.member }"><fmt:formatNumber value="${product.maketPrice }"
											pattern="##.##" minFractionDigits="2" maxFractionDigits="2"></fmt:formatNumber></c:if>
											</font>元/斤<img
														src="images/hot.gif" /></td>
												</tr>
												<tr>
													<td width="154.8" height="20"><a
														href="buy.htm?productId=${product.id }&productName=${product.name}&price=${product.maketPrice }&count=1"><img
															src="images/buy.gif" /></a></td>
												</tr>
											</table>
									</td>
									<c:if test="${status.count % 5 eq 0 || status.count eq 5}">
										</tr>
									</c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
					<div><center><c:if test="${goPage!=1 }">
							<a href="searchKeyword.htm?goPage=${goPage-1}&keyword=请输入您所要查找的商品名称">上一页</a>
						</c:if> <c:if test="${goPage !=page }">
							<a href="searchKeyword.htm?goPage=${goPage+1}&keyword=请输入您所要查找的商品名称">下一页</a></c:if></center></div>
				</td>
			</tr>
		</table>
	</div>
	<!--footer-->
	<div align="center">
		<table width="1024" border="0" cellpadding="0" cellspacing="0"
			bgcolor="#FAFBF7">
			<!--DWLayoutTable-->
			<tr>
				<td width="429" height="35" valign="top">
					<div align="right">
						<img src="images/biaoshi.gif" border="0" height="35" width="28" />
					</div>
				</td>
				<td width="595" align="left" valign="top">
					<div align="left">
						版权所有:益阳湖南城市学院网上图书商城<br /> 服务电话：18230526559 <br /> 运作日期：2018-01-01
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>