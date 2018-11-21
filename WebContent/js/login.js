/** 提示用户登录准则 */
function usernameHint() {
	document.getElementById("usernamemsg").innerHTML = "请输入用户名！";
}
function passwordHint() {
	document.getElementById("passwordmsg").innerHTML = "请输入密码！";
}
/** 验证用户登录准则*/
function usernameValidate(username){
	if(username==""){
		document.getElementById("usernameMsg").innerHTML = "用户名不能为空";
	}
}
function passwordValidate(password){
	if(password==""){
		document.getElementById("passwordMsg").innerHTML = "密码不能为空";
	}
}
/**验证用户登录信息*/
function CheckLoginForm(f){
	if(f.username.value==""){
		document.getElementById("usernameMsg").innerHTML = "用户名不能为空";
		f.username.focus();
		return false;
	}
	if(f.password.value==""){
		document.getElementById("passwordMsg").innerHTML = "密码不能为空";
		f.password.focus();
		return false;
	}
	return true;
}