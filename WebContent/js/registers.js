var flag;
/** Ajax请求验证用户名是否存在 */
function usernameValidate() {
	var username = document.registerForm.username.value;
	if (username == "") {
		document.getElementById("usernameMsg").innerHTML = "用户名不能为空";
	} else {
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.open("GET", "usernameValidate.html?username=" + username);
		req.onreadystatechange = callback;
		req.send(null);
	}
}

/**
 * 回调函数
 */
function callback() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var msg = req.responseXML.getElementsByTagName("msg")[0];

			setMsg(msg.childNodes[0].nodeValue);
		}
	}
}

/**
 * 把提示信息加到字段后面去
 */
function setMsg(msg) {
	if (eval(msg)){
		flag = false;
		document.getElementById("usernameMsg").innerHTML = "用户名存在，请重新输入！";
	}else{
		flag = true;
		document.getElementById("usernameMsg").innerHTML = "<img src=./images/yes.gif>";
	}
}
/** 提示用户注册准则 */
function usernameHint() {
	document.getElementById("usernamemsg").innerHTML = "请输入用户名！";
}
function usernameShopHint() {
	document.getElementById("usernamemsg").innerHTML = "请输入商户户名！";
}
function passwordHint() {
	document.getElementById("passwordmsg").innerHTML = "请输入同时含有数字和字母，且长度要在6-16位之间的密码！";
}
function repasswordHint() {
	if (document.getElementById("password").value == "") {
		document.getElementById("repasswordMsg").innerHTML = "请先输入第一次密码！";
	} else {
		document.getElementById("repasswormsg").innerHTML = "两次密码必须一致！";
	}
}
function phoneHint() {
	document.getElementById("phonemsg").innerHTML = "请输入正确的手机号码";
}
function QQHint() {
	document.getElementById("QQmsg").innerHTML = "请输入正确的QQ号码(可以为空)";
}
function emailHint() {
	document.getElementById("emailmsg").innerHTML = "请输入正确格式的邮箱(可以为空)";
}
function addressHint() {
	document.getElementById("addressmsg").innerHTML = "地址只能有汉字和数字，字母(不能全为数字或字母)";
}
/** 验证用户注册 */
function passwordValidate(password) {
	if (!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,16}$/.test(password)) {
		document.getElementById("passwordMsg").innerHTML = "请输入同时含有数字和字母，且长度要在6-16位之间的密码！";
	} else {
		document.getElementById("passwordMsg").innerHTML = "<img src=./images/yes.gif>";
	}
}
function repasswordValidate(repassword) {
		if (repassword == document.getElementById("password").value
				&& document.getElementById("password").value != "") {
			document.getElementById("repasswordMsg").innerHTML = "<img src=./images/yes.gif>";
		}else if( document.getElementById("password").value == "") {
			document.getElementById("repasswordMsg").innerHTML = "请先输入第一次密码！";
		}else {
			document.getElementById("repasswordMsg").innerHTML = "两次密码必须一致！";
		}
}
function phoneValidate(phone) {
	if (!/^0?1[3|4|5|8][0-9]\d{8}$/.test(phone)) {
		document.getElementById("phoneMsg").innerHTML = "请输入正确的手机号码";
	} else {
		document.getElementById("phoneMsg").innerHTML = "<img src=./images/yes.gif>";
	}
}
function QQValidate(QQ) {
	if (/^[1-9][0-9]{4,11}$/.test(QQ) || QQ == "") {
		document.getElementById("QQMsg").innerHTML = "<img src=./images/yes.gif>";
	} else {
		document.getElementById("QQMsg").innerHTML = "请输入正确的QQ号码(可以为空)";
	}
}
function emailValidate(email) {
	if (/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email)
			|| email == "") {
		document.getElementById("emailMsg").innerHTML = "<img src=./images/yes.gif>";
	} else {
		document.getElementById("emailMsg").innerHTML = "请输入正确格式的邮箱(可以为空)";
	}
}
function addressValidate(address) {
	if (!/^(?=.*?[\u4E00-\u9FA5])[\w\u4E00-\u9FA5]+$/.test(address)) {
		document.getElementById("addressMsg").innerHTML = "地址只能有汉字和数字，字母(不能全为数字或字母)";
	} else {
		document.getElementById("addressMsg").innerHTML = "<img src=./images/yes.gif>";
		
	}
}
/**检测用户注册信息*/
function CheckRegisterForm(f){
	if(f.username.value==""){
		document.getElementById("usernameMsg").innerHTML = "用户名不能为空";
		return false;
	}
	if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,16}$/.test(f.password.value)){
		document.getElementById("passwordMsg").innerHTML = "请输入同时含有数字和字母，且长度要在6-16位之间的密码！";
		return false;
	}if( f.repassword.value != f.password.value&&f.password.value=="") {
		document.getElementById("repasswordMsg").innerHTML = "请先输入第一次密码！";
		return false;
	}if(f.repassword.value != f.password.value&&f.password.value!=""){
		document.getElementById("repasswordMsg").innerHTML = "两次密码必须一致！";
		return false;
	}if (!/^0?1[3|4|5|8][0-9]\d{8}$/.test(f.phone.value)) {
		document.getElementById("phoneMsg").innerHTML = "请输入正确的手机号码";
		return false;
	}if (!/^(?=.*?[\u4E00-\u9FA5])[\w\u4E00-\u9FA5]+$/.test(f.address.value)) {
		document.getElementById("addressMsg").innerHTML = "地址只能有汉字和数字，字母(不能全为数字或字母)";
		return false;
	}
	return flag;
}
/**检测商户注册信息*/
function CheckRegisterForm(f){
	if(f.username.value==""){
		document.getElementById("usernameMsg").innerHTML = "用户名不能为空";
		return false;
	}
	if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,16}$/.test(f.password.value)){
		document.getElementById("passwordMsg").innerHTML = "请输入同时含有数字和字母，且长度要在6-16位之间的密码！";
		return false;
	}if( f.repassword.value != f.password.value&&f.password.value=="") {
		document.getElementById("repasswordMsg").innerHTML = "请先输入第一次密码！";
		return false;
	}if(f.repassword.value != f.password.value&&f.password.value!=""){
		document.getElementById("repasswordMsg").innerHTML = "两次密码必须一致！";
		return false;
	}
	return flag;
}
