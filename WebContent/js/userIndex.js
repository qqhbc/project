/** 登录表单验证 */
function CheckLoginForm() {
	if (document.loginForm.username.value.length == 0) {
		alert("请输入用户名!");
		document.loginForm.username.focus();
		return false;
	}
	if (document.loginForm.password.value.length == 0) {
		alert("请输入密码！");
		document.loginForm.password.focus();
		return false;
	}
	return true;
}
