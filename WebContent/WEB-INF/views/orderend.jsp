<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

</style>
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
						hi
						<c:choose>
							<c:when test="${user.name==null }">游客,欢迎来我网上图书商城！请<font
									style="color: red;">登录</font>
								<a href="#">免费注册</a>
							</c:when>
							<c:otherwise>
								<font size="4" style="color: red"><c:if test="${user.member }">尊贵的会员</c:if>${user.name }</font>,欢迎来我网上图书商城！
								<a href="logout.htm">安全退出</a>&nbsp;<a href="selectByUsername.htm">修改信息</a>
							</c:otherwise>
						</c:choose>
						<a href="searchOrderByusername.htm?username=${user.name}">我的订单</a>&nbsp;<img src="images/cart.jpg"><a href="mycart.htm">购物车${size>0?size:0}种</a>&nbsp;<a href="index.htm">网站首页</a>
					</div>
				</td>
			</tr>		
			<tr>
				<td height="54" valign="top"  bgcolor="#FAFBF7">&nbsp;</td>
			</tr>
		</table>
	</div>

<hr>

<center>
<table height="70%" bgcolor="#D6F7F8">
<tr style="margin:50% auto;">
	<td>
	<center><h1>谢谢您在本站购物!欢迎<a href="./index.htm"><font color="green">继续</font></a>。</h1></center>
	</td>
</tr>
</table>
</center>

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
						版权所有:益阳湖南城市学院网上图书商城<br /> 服务电话：18230526559 <br /> 运作日期：2018-01-01
					</div>
				</td>
			</tr>
		</table>
	</div>	
</body>
</html>