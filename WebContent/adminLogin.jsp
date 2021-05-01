<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>管理员登录</title>
<script type="text/javascript">
function loginClick(){
	var user=login.username.value;
	var pass=login.password.value;
	if(user==null||user==""){
		alert("请填写登录名");
		login.username.value.focus();
	}else if(pass==null||pass==""){
		alert("请填写密码");
		login.password.value.focus();
	}else
		//alert("请填写121");
		login.submit();
	}
	function res(){
		login.username.value="";
		login.password.value="";
	}
	function changevalidcode(){
		document.getElementById("validCode").src="ValidCodeServlet?rand="+Math.random();
	}
	
</script>
<style type="text/css">
.tb{
 font-family:"微软雅黑";	
 font-size:18px;
	
}
.div2{
background:;
 width:60%;
  height:50;
}
.mian{
width:50%;
height:600px;
border:0px solid;
margin:5%;
}
.rr{
text-align:center;
font-family:Verdana, Geneva, sans-serif;
color:#FFFFFF;
font-size:60px;
font-color:red;
}
.main1{margin-left:5%;}
.login{
		width:450px;/* 宽度 */
		height:380px;/*高度*/
		margin:150px auto;
		
		/* border:1px solid red; */
		border-radius:6px;/* ;边框圆角 */
		background:rgba(255,255,255,0.25);
		box-shadow: 0 50px 50px rgba(21,62,78,1);
		position:relative;	
			}
.normal-title{
	text-align:center;
}
.box {
	margin-left:67px;
}
.image{
	margin-left:15px;
	width:88px;
}
</style>


</head>

<body background="./image/bk.jpg" style="background-repeat:no-repeat;
background-size:100% 100%;background-attachment:fixed;size:100%;">

  <div class="rr" >
   <p><span style="color:#FFCC33;text-align:center">图书馆管理系统</span></p>
  </div>
  <div class="main1">
  <form action="AdminLoginServlet" method="post" name="login">


<div class="login">
<table cellpadding="0" cellspacing="0">
</br>
</br>
  <div class="normal-title" style="font-size:50px;font-family: "华文楷体";">
登 录
</br>
     </div>
  <tr>
<td>
    <input type="text"   class="box" id="username" name="username" placeholder="  管理员名"style="width:260px; height:50px; border-radius:5px; "class="tb"/>
    
  </div></td>
</tr>

<tr>
<td>
    <input type="password"   class="box" id="password" name="password"placeholder="  密码"style="width:260px; height:50px;border-radius:5px; "class="tb"/>
    
    
  </div></td>
</tr>

<tr>
<td>
  <div align="center">
  <input type="yanzhengma"  class="box" id="yanzhengma" name="yanzhengma"placeholder="  验证码"style="width:260px; height:50px;border-radius:5px; "class="tb"/>
  <br/>
  <br/>
  <br/>
    
  <br/>
  </div></td>

<td>
<img src="ValidCodeServlet" class="image" alt="" id="validCode"onclick="changevalidcode()"/>
<br/>
<br/>
<br/>
</td>
</tr>

<tr>
<td>
<div style="width:60px display:block;font-size:0px;">
  <div align="right">
  <input name="按钮" type="button" id="重置" style="width:130px;height:40px;font-size:18px"onclick="res()" value="重    置""/>
    
  <input type="button" value="登  录" id="登录" onclick="loginClick()" style="width:130px;height:40px;font-size:18px " />
  </div> 
</div>
</td>
</tr>
</table>
</div>
<br/>
<br/>
</div>
</form>
  </div>
  </div>
</div>
<br/>



</body>
</html>


