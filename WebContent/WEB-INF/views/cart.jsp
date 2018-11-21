<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>你的购物车</title>
<script type="text/javascript" src="js/userIndex.js"></script>
<style type="text/css">
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
#senfe {
	width: 333px;
	border-top: #000 1px solid;
	border-left: #000 1px solid;
}

#senfe td {
	border-right: #000 1px solid;
	border-bottom: #000 1px solid;
}
</style>
<script language="javascript">
	function senfe(o, a, b, c, d) {
		var t = document.getElementById(o).getElementsByTagName("tr");
		for (var i = 0; i < t.length; i++) {
			t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a
					: b;
			t[i].onclick = function() {
				if (this.x != "1") {
					this.x = "1";//本来打算直接用背景色判断，FF获取到的背景是RGB值，不好判断
					this.style.backgroundColor = d;
				} else {
					this.x = "0";
					this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a
							: b;
				}
			}
			t[i].onmouseover = function() {
				if (this.x != "1")
					this.style.backgroundColor = c;
			}
			t[i].onmouseout = function() {
				if (this.x != "1")
					this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a
							: b;
			}
		}
	}

</script>

</head>
<body>
	<div align="center" style="margin: -8px 0 -8px 0;">
		<table width="1024" border="0" cellpadding="0" cellspacing="0">
			<!--DWLayoutTable-->
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
								<a href="index.htm?flag=logout">安全退出</a>&nbsp;<a href="searchOrderByusername.htm?username=${user.name}">我的订单</a>&nbsp;<a href="selectByUsername.htm">修改信息</a>
							</c:otherwise>
						</c:choose>
						<img src="images/cart.jpg"><a href="mycart.htm">购物车${size }种</a>&nbsp;<a href="index.htm">网站首页</a>
					</div>
				</td>
			</tr>		
			<tr>
				<td height="54" valign="top"  bgcolor="#FAFBF7">&nbsp;</td>
			</tr>
		</table>
	</div>

	<!-- 内容区 -->
	<!-- 修改回馈信息显示 -->
	<center>
		<font color="red">${msg }</font>
	</center>
	<center>您一共购买了这么多的商品:${size}种</center>

	<hr>
	
	<table style="margin: -8px 0 -8px 0;" width="100%" height="300"
		bgcolor="#D6F7F8">
		<tr>
			<td>
			<c:choose>
			<c:when test="${cartList!=null}">
				<!-- 购物车 start -->
				<form action="buy_update.htm" method="post" >
					<table id="senfe" border="0" cellpadding="0" cellspacing="0"
						align="center">
						<tr>
							<td>商品ID</td>
							<td>商品名称</td>
							<td>商品价格</td>
							<td>购买数量</td>
							<td>金额</td>
							<td>删除</td>
						</tr>
						<c:forEach items="${cartList}" var="c">
							<tr>
								<td>${c.productId }</td>
								<td>${c.productName }</td>
								<td>${c.price }</td>
								<td><input type="text" size=4 name="p${c.productId }" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"  
									value="${c.count }"></td>
								<td><fmt:formatNumber value="${c.price*c.count}"
										pattern="##.##" minFractionDigits="2" maxFractionDigits="2"></fmt:formatNumber></td>
								<td><center><a href="buy_delete.htm?productId=${c.productId}"><img src="images/delete.gif"/></a></center></td>
							</tr>
						</c:forEach>
					</table>
					<script language="javascript">
						//senfe("表格名称","奇数行背景","偶数行背景","鼠标经过背景","点击后背景");
						senfe("senfe", "#fff", "#ccc", "#cfc", "#f00");
						
					</script>


					<center>
						共<fmt:formatNumber value="${totalPrice }"
										pattern="##.##" minFractionDigits="2" maxFractionDigits="2"></fmt:formatNumber>元 <input type="submit"  value="修改数量" /> <input
							type="button" value="去结算"
							onclick="document.location.href='buy_update.htm?confirm=xiadingdan'">
					</center>
					<center>
						<a href="index.htm"><font size=5>继续购买</font></a>
					</center>
				</form> <!-- 购物车end -->
				</c:when>
				<c:otherwise>
					<center><font size=8 color="red">购物车为空!!</font></center>
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	<hr>
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
						版权所有:益阳湖南城市学院网上商城<br /> 服务电话：18230526559 <br /> 运作日期：2018-01-01
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>