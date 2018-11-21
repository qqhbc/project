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
								<a href="logout.htm">安全退出</a>&nbsp;<a href="selectByUsername.htm">修改信息</a>
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
	<font color="red">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户在网上图书商城发布信息（包括注册用户名）时，必须遵守国家有关法律规定，并承担一切因自己发布信息不当导致的民事、行政和/或刑事法律责任。用户在网上图书商城所发布的信息（包括注册用户名、发表评论以及其他内容），不得含有以下任何内容：<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1) 含有法律、行政法规禁止的其他内容的。违反宪法确定的基本原则的；危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；以非法民间组织名义活动的；<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2) 损害国家荣誉和利益，攻击党和政府的；煽动民族仇恨、民族歧视，破坏民族团结的；破坏国家、地区间友好关系的；煽动、组织、教唆恐怖活动、非法集会、结社、游行、示威、聚众扰乱社会秩序的；<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3) 违背中华民族传统美德、社会公德、论理道德、以及社会主义精神文明的；破坏国家宗教政策，宣扬邪教和封建迷信的；散布谣言或不实消息，扰乱社会秩序，破坏社会稳定的；<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4) 散布淫秽、色情、赌博、暴力、恐怖或者教唆犯罪的；侮辱或诽谤他人，侵害他人合法权益的；<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5) 不得利用本网站从事洗钱、窃取商业秘密、窃取个人信息等违法犯罪活动；<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6) 不得干扰本网站的正常运转，不得侵入本网站及国家计算机信息系统；<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7) 不得利用在本网站注册的账户进行牟利性经营活动（如批发、炒货、转卖）；<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8) 侵犯他人著作权等合法权益的；使用漫骂、辱骂、中伤、恐吓、诅咒等不文明语言的；侵犯他人肖像权、姓名权、名誉权、隐私权或其他人身权利的；<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;若您未遵守以上规定的，网上图书商城有权做出独立判断并采取暂停会员权限或注销您的会员账户等措施，并且您须对自己的言论和行为承担法律责任。
</font>
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