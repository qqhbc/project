<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>你的订单信息</title>
<style type="text/css">
#senfe {
	width: 60%;
	border-top: #000 1px solid;
	border-left: #000 1px solid;
}

#senfe td {
	border-right: #000 1px solid;
	border-bottom: #000 1px solid;
}

.STYLE1 {
	font-size: 20px;
	font-weight: bold;
	a
	{
	text-decoration
	:
	none
}

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
	/**检测订单信息*/
	function CheckConfirm(f){
		if (!/^0?1[3|4|5|8][0-9]\d{8}$/.test(f.phone.value)) {
			document.getElementById("phoneMsg").innerHTML = "请输入正确的手机号码";
			return false;
		}if (!/^(?=.*?[\u4E00-\u9FA5])[\w\u4E00-\u9FA5]+$/.test(f.address.value)) {
			document.getElementById("addressMsg").innerHTML = "地址只能有汉字和数字，字母(不能全为数字或字母)";
			return false;
		}
		return true;
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
						<a href="searchOrderByusername.htm?username=${user.name}">我的订单</a>&nbsp;<img src="images/cart.jpg"><a
							href=mycart.htm>购物车${size }种</a>&nbsp;<a href="index.htm">网站首页</a>
					</div>
				</td>
			</tr>
			<tr>
				<td height="54" valign="top" bgcolor="#FAFBF7">&nbsp;</td>
			</tr>
		</table>
	</div>


	<!-- 内容区 -->
	<!-- 修改回馈信息显示 -->
	<center>您一共购买了这么多的商品:${size}种</center>

	<hr>
	<table style="margin: -8px 0 -8px 0;" width="100%" height="300"
		bgcolor="#D6F7F8">
		<tr>
			<td>
				<!-- 定单 start -->
				<center>
					<h3>${ordermsg }</h3>
				</center>
				<center>
					<div style="color:red;" id="phoneMsg"></div>
					<div style="color:red;" id="addressMsg"></div>
				</center>
				<center>
					<h2>
						您好 <font color="red">${user.name }</font>!请核对您的订单内容,核对好请点击<font
							color="blue">"确定订单"</font>按钮
					</h2>
				</center>
				<center>
					<form action="confirm_order.htm" method="post" onsubmit="return CheckConfirm(this)">
						<table width="60%">
							<tr>
								<td>客户名称：<input size="8" type=text name="username"
									value="${user.name }" readonly></td>
								<td>订单编号：<input size="30" type=text name="orderCode"
									value="${orderCode }" readonly></td>
								<td>日期：<input size="18" type=text name="orderDate"
									value="${orderDate }" readonly></td>
							</tr>
							<tr>
								<td colspan="1">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<input
									type=text name="phone"
									onFocus="if(this.value=='${user.phone}'){this.value='';}"
									onBlur="if(this.value==''){this.value='${user.phone }';}"
									value="${user.phone }" size="10"></td>
								<td colspan="2">&nbsp;E&nbsp;m&nbsp;a&nbsp;
									i&nbsp;&nbsp;l&nbsp;：<input type=text name="email"
									onFocus="if(this.value=='${user.email}'){this.value='';}"
									onBlur="if(this.value==''){this.value='${user.email }';}"
									value="${user.email }" size="54%">
								</td>
							</tr>
							<tr>
								<td colspan="1">QQ&nbsp;&nbsp;号码：<input type=text name="qq"
									onFocus="if(this.value=='${user.QQ}'){this.value='';}"
									onBlur="if(this.value==''){this.value='${user.QQ }';}"
									value="${user.QQ }" size="10"></td>
								<td colspan="2">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：<input
									type=text name="remark" value="" size="54%"></td>
							</tr>
							<tr>
								<td colspan="3">送货地址：<input type=text name="address"
									onFocus="if(this.value=='${user.address}'){this.value='';}"
									onBlur="if(this.value==''){this.value='${user.address }';}"
									value="${user.address }" size="75"></td>
							</tr>
						</table>
						<table id="senfe" width="60%" border="0" cellpadding="0"
							cellspacing="0">
							<tr align="center" valign="middle" bgcolor="#6633FF">
								<td><div align="center" class="STYLE1">商品ID</div></td>
								<td><div align="center" class="STYLE1">商品名称</div></td>
								<td><div align="center" class="STYLE1">商品价格</div></td>
								<td><div align="center" class="STYLE1">购买数量</div></td>
								<td><div align="center" class="STYLE1">金额</div></td>
							</tr>
							<%
								int i = 0;
							%>
							<c:forEach items="${cartList}" var="c">
								<tr align="center" valign="middle" bgcolor="#99FF33">
									<td>${c.productId }</td>
									<td>${c.productName }</td>
									<td>${c.price }</td>
									<td>${c.count }</td>
									<td><fmt:formatNumber value="${c.price*c.count }"
											pattern="##.##" minFractionDigits="2" maxFractionDigits="2"></fmt:formatNumber></td>
								</tr>
								<tr>
									<td><input type="hidden"
										name="saleitems[<%=i %>].productId" value="${c.productId }"></td>
									<td><input type="hidden"
										name="saleitems[<%=i %>].productName"
										value="${c.productName }"></td>
									<td><input type="hidden" name="saleitems[<%=i %>].price"
										value="${c.price }"></td>
									<td><input type="hidden" name="saleitems[<%=i %>].count"
										value="${c.count }"></td>
									<td><input type="hidden"
										name="saleitems[<%=i %>].totalPrice"
										value="${c.price*c.count }"></td>
								</tr>
								<%
									i++;
								%>
							</c:forEach>
						</table>

						共
						<fmt:formatNumber value="${totalPrice }" pattern="##.##"
							minFractionDigits="2" maxFractionDigits="2"></fmt:formatNumber>
						元 <input type="submit" value="确定订单">
					</form>
				</center> <!-- 定单 end -->
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