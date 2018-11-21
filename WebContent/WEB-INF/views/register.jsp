<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script type="text/javascript" src="js/registers.js"></script>
<script type="text/javascript" src="js/userIndex.js"></script>
<style>
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
<div align="center" style="margin:-8px 0 -8px 0;">
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
								<a href="register.htm">免费注册</a>
							</c:when>
							<c:otherwise>
								<font size="4" style="color: red"><c:if test="${user.member }">尊贵的会员</c:if>${user.name }</font>,欢迎来我网上图书商城！
								<a href="logout.htm">安全退出</a>
							</c:otherwise>
						</c:choose>
						<a href="login.htm">我的订单</a>&nbsp;<img src="images/cart.jpg"><a href="mycart.htm">购物车${size>0?size:0 }种</a>&nbsp;<a href="index.htm">网站首页</a>
					</div>
				</td>
			</tr>
    <tr>
      <td height="54" valign="top" bgcolor="#FAFBF7">
			&nbsp;</td>
    </tr>
  </table>
</div>

<center><h1>用户注册</h1></center>

<hr>
<!-- 幻灯片end -->
	<table style="margin:-8px 0 -8px 0;" width="100%" height="300" bgcolor="#D6F7F8">
	<tr><td>
	<div><font color="red">${msg }</font></div>
	<div align="center"><font color="red">请先注册以后再进行购物，注意：注册的信息将是我们送货的信息，希望您填写正确详细的信息，带星号的必填。谢谢合作！</font></div>
	<form  name="registerForm" action="user_register.htm" method="post" onSubmit="return CheckRegisterForm(this)" >
	<table bgcolor="#D6F7F8" align="center" border="0">
		
		<tr>
			<td>用户名:</td>
			<td><input type="text" size="25" maxlength="20" name="username" id="username" onblur="usernameValidate()" onfocus="usernameHint()"/>(<font color="red">*</font>)</td>
			<td><div style="color:red;" id="usernameMsg"><div style="color:green" id="usernamemsg"></div></div></td>
		</tr>
		<tr>
			<td>密码:</td>
			<td><input type="password" size="25" maxlength="20" name="password" id="password" onblur="passwordValidate(this.value)" onfocus="passwordHint()">(<font color="red">*</font>)</td>
			<td><div style="color:red;" id="passwordMsg"><div style="color:green" id="passwordmsg"></div></div></td>
		</tr>
		<tr>
			<td>确认密码:</td>
			<td><input type="password" size="25" maxlength="20" name="repassword" onblur="repasswordValidate(this.value)" onfocus="repasswordHint()">(<font color="red">*</font>)</td>
			<td><div style="color:red;" id="repasswordMsg"><div style="color:green" id="repasswordmsg"></div></div></td>
		</tr>
		<tr>
			<td>电话:</td>
			<td><input type="text" size="25" maxlength="20" name="phone" onblur="phoneValidate(this.value)" onfocus="phoneHint()">(<font color="red">*</font>)</td>
			<td><div style="color:red;" id="phoneMsg"><div style="color:green" id="phonemsg"></div></div></td>
		</tr>
		<tr>
			<td>性别:</td>
			<td><input type="radio" name="sex" checked="checked" value="true" />男
    		    <input type="radio" name="sex" value="false" />女</td>
		</tr>
		<tr>
			<td>QQ号码:</td>
			<td><input type="text" size="25" maxlength="10" name="QQ" onblur="QQValidate(this.value)" onfocus="QQHint()"></td>
			<td><div style="color:red;" id="QQMsg"><div style="color:green" id="QQmsg"></div></div></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" size="25" maxlength="20" name="email" onblur="emailValidate(this.value)" onfocus="emailHint()"></td>
			<td><div style="color:red;" id="emailMsg"><div style="color:green" id="emailmsg"></div></div></td>
		</tr>
		<tr>
			<td>地址:</td>
			<td><input type="text" size="25	" maxlength="20	" name="address" onblur="addressValidate(this.value)" onfocus="addressHint()">(<font color="red">*</font>)</td>
			<td><div style="color:red;" id="addressMsg"><div style="color:green" id="addressmsg"></div></div></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="同意协议并注册"><input type="reset" value="重填"></td>
		</tr>
		<tr>
			<td></td>
			<td><a href="agreement.htm">《网上图书商城服务协议》</a></td>
		</tr>
	</table>
	</form>
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
						版权所有:益阳湖南城市学院网上图书商城<br /> 服务电话：18230526559 <br /> 运作日期：2018-01-01
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>