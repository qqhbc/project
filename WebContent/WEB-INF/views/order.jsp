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
}
</style>
<script language="javascript">
function senfe(o,a,b,c,d){
	var t=document.getElementById(o).getElementsByTagName("tr");
	for(var i=0;i<t.length;i++){
		t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
		t[i].onclick=function(){
			if(this.x!="1"){
				this.x="1";//本来打算直接用背景色判断，FF获取到的背景是RGB值，不好判断
				this.style.backgroundColor=d;
			}else{
				this.x="0";
				this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
			}
		}
		t[i].onmouseover=function(){
			if(this.x!="1")this.style.backgroundColor=c;
		}
		t[i].onmouseout=function(){
			if(this.x!="1")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
		}
	}
}
</script>
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
							<c:when test="${user.name==null }">游客,欢迎来我网上图书商城！请<a href="javascript:" onClick="bgDiv.style.display='';login.style.display='';">登录</a>
								<a href="register.htm">免费注册</a>
							</c:when>
							<c:otherwise>
								<font size="4" style="color: red"><c:if test="${user.member }">尊贵的会员</c:if>${user.name }</font>,欢迎来我网上图书商城！
								<a href="logout.htm">安全退出</a>&nbsp;<a href="selectByUsername.htm">修改信息</a>
							</c:otherwise>
						</c:choose>
						<a href="searchOrderByusername.htm?username=${user.name}">我的订单</a>&nbsp;<img src="images/cart.jpg"><a href="mycart.htm">购物车${size>0?size:0 }种</a>&nbsp;<a href="index.htm">网站首页</a>
					</div>
				</td>
			</tr>
    <tr>
      <td height="54" valign="top" bgcolor="#FAFBF7">
			&nbsp;</td>
    </tr>
  </table>
</div>

<!-- 内容区 -->
<hr>
<table style="margin:-8px 0 -8px 0;" width="100%" height="300" bgcolor="#D6F7F8">
<tr><td>
<!-- 订单 start -->
	<center>您好 <font color="red">${user.name }</font>！ 您的订单列表如下：</center>
	<center>
	
		
			<table id="senfe" width="60%" border="0" cellpadding="0" cellspacing="0">
			<tr align="center" valign="middle" bgcolor="#6633FF">
				<td><div align="center" class="STYLE1">订单编号</div></td>
				<td><div align="center" class="STYLE1">订单日期</div></td>
				<td><div align="center" class="STYLE1">订单金额</div></td>
				<td><div align="center" class="STYLE1">状态</div></td>
				<td><div align="center" class="STYLE1">操作</div></td>
			</tr>
				<c:choose>
				<c:when test="${empty orders}">
					<tr align="center" valign="middle" bgcolor="#99FF33">
						<td colspan="5">没有数据</td>
					 </tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${orders}" var="o">
					  <tr align="center" valign="middle" bgcolor="#99FF33">
					  
						<td>${o.orderCode }</td>
						<td>${o.odate }</td>
						<td>${totalPrice }</td>
						<td>${o.status?"已发货":"未发货" }</td>
						<td><a href="orderDetail.htm?orderCode=${o.orderCode  }">详细信息</a></td>
					 </tr>
					 </c:forEach>
			 </c:otherwise>
			 </c:choose>
		</table>
	</center>
<!-- 订单 end -->
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
</html>