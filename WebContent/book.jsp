<%@page import="java.sql.ResultSet"%>
<%@page import="com.dh.db.DBUtil"%><%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书查看</title>
<script type="text/javascript">
			//一开始将所有菜单子模块隐藏
			var hide=true;
				//通过title的变化定位到不同的元素
				function showOrHide(title){
					var d = document.getElementById("d"+title);
					//如果其属性是隐藏的，将其变为显示
					if(hide){
						d.style.display = "block";
						//hide变为false
						hide = false;
						//反之，将其属性变为隐藏
					}else{
						d.style.display = "none";
						//hide变为true
						hide = true;
					}
				}
	
	

				function change() {
				    var secondDiv=this.parentNode.getElementsByTagName("ul")[0];
				    //选择li的ul
				    if (secondDiv.className=="myhide")    
				        secondDiv.className="myshow";
				    else
				        secondDiv.className="myhide";
				}
				window.onload=function(){
				    var ul=document.getElementById("listul");
				    var ali=ul.childNodes;
				    var A;
				    for(var i=0;i<ali.length;i++)
				    {
				        if (ali[i].tagName=="LI" && ali[i].getElementsByTagName("ul").length)  //tagName的标签名要大写
				        //有li 且li里面有ul子菜单
				        {
				            A=ali[i].firstChild;
				            A.onclick=change; 
				        }
				    }
				}
	
		</script>
		<style>
			.container{
				width: 100px;
				text-align: center;
			}
			.item1{
				
				
            	color:black;
            	padding:2px 15px;
            	cursor:pointer;
			}
			.item2{
				display: none;
				color: black;
				
			}
						
#navigation{
    width: 200px;
    font-family: arial;
}
#navigation>ul>li{
    border-bottom: 1px solid #ad9f9f; /*添加下划线
    子元素选择器*/
}
#navigation>ul>li>a{
    display: block;
    padding: 5px 5px 5px 0.5em;
    text-decoration: none;
    border-left: 12px solid #711111; /*左边的粗边*/
}
#navigation>ul>li>a:link,#navigation>ul>li>a:visited{
    background-color:gray;
    color: #FFFFFF;
}

/*二级菜单的样式*/
#navigation ul li ul{
    margin: 0px;padding: 0px;
}
#navigation ul li ul li{
    border-top: 1px solid #ed9f9f;
}
#navigation ul li ul li a{
    display: block; padding: 3px 3px 3px 0.5em;
    text-decoration: none;
    border-left: 28px solid #a71f1f;
    border-right: 1px solid #711515;
}
#navigation ul li ul li a:link,#navigation ul li ul li a:visited{
    background-color:#F99;
    color: #FFFFFF;
}
#navigation ul li ul li a:hover{
    background-color: #c2425d;
    
}
#navigation ul li ul.myhide{
    display: none;     /*隐藏元素*/
}
#navigation ul li ul.myshow{
    display: block;   /*显示元素*/
}
.tr{
position:absolute;
			top:140px;
			left:360px;

}
table{
margin-left: auto;
	margin-right: auto;
	width: 643px;
	height: 400px;
	padding: 30px;
	border: 1px solid   rgba(0,0,0,.2);
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	-moz-background-clip: padding;
	-webkit-background-clip: padding-box;
	background-clip: padding-box;
	-moz-box-shadow: 0 0 13px 3px rgba(0,0,0,.5);
	-webkit-box-shadow: 0 0 13px 3px rgba(0,0,0,.5);
	box-shadow: 0 0 13px 3px rgba(0,0,0,.5);
	overflow: hidden;
	background-color:  rgba(255,255,255,0.5);
  border-radius:20px;
   font-family:"微软雅黑";

}
		</style>

</head>
<body background="./image/1.jpg" style="background-repeat:no-repeat; background-size:100% 100%; background-attachment:fixed;">

<form action="bookdeleteServlet" method="post" name="login" >
<div align="center" class="div1" >
<font face="微软雅黑" color="#F0F8FF" size="+6">图书查看</font>
</div>
<div style="width: 100%;height: 600px;">

	<div style="float:left;width: 300px;height: 100%;"align="center">
<div id="navigation">
<br/><br/><br/><br/>
    <ul id="listul">
        <li style="list-style-type:none;"><a href="#">图书信息管理</a>
        <ul class="myhide">
            <li style="list-style-type:none;"><a href="addbook.html">添加图书</a></li>
             <li style="list-style-type:none;"><a href="bookmodify.html">图书信息修改</a></li> 
             <li style="list-style-type:none;"><a href="bookdelete.html">图书删除</a></li>
             <li style="list-style-type:none;"><a href="book.jsp">图书信息查看</a></li>
        </ul>
        </li>
        <li style="list-style-type:none;"><a href="#">图书类别管理</a>
        <ul class="myhide">
            <li style="list-style-type:none;"><a href="booktypeadd.html">图书类别添加</a></li>
             <li style="list-style-type:none;"><a href="booktypemodify.html">图书类别修改</a></li> 
        </ul>
        </li>
         <li style="list-style-type:none;"><a href="#">读者信息管理</a>
        <ul class="myhide">
            <li style="list-style-type:none;"><a href="readeradd.html">添加读者</a></li>
             <li style="list-style-type:none;"><a href="readermodify.html">读者信息修改</a></li> 
             <li style="list-style-type:none;"><a href="readerdelete.html">读者删除</a></li>
             
        </ul>
        </li>
        <li style="list-style-type:none;"><a href="#">借阅管理</a>
        <ul class="myhide">
            <li style="list-style-type:none;" ><a href="bookloanedout.html">图书借阅</a></li>
             <li style="list-style-type:none;"><a href="bookreturn1.html">图书归还</a></li> 
             <li style="list-style-type:none;"><a href="booksearch.html">图书搜索</a></li>
        </ul>
        </li>
        <li style="list-style-type:none;"><a href="#"> 管理员信息管理</a>
        <ul class="myhide">
            <li style="list-style-type:none;"><a href="adminadd.html">管理员添加</a></li>
             <li style="list-style-type:none;"><a href="deleteadmin.html">管理员删除</a></li> 
             <li style="list-style-type:none;"><a href="adminmodify.html">管理员信息修改</a></li>
        </ul>
        
        <li style="list-style-type:none;"><a href="adminLogin.jsp">退出</a></li>
        
    </ul>
<script type="text/javascript" src="../js/caidan.js"></script>  
</div>
	</div>	
<div style="width:1020px;height: 100%;float:right;">




<%
DBUtil db=new DBUtil();
db.getconection("dh", "root", "root");
String sql="select * from books";
ResultSet rs=db.dbQuery(sql, null);//查询的书放在记录集，记录集一行代表表格一行
%>
<br/><br/><br/>
<table border="1" align="center" class="tr">
<thead>
<tr>

<td>图书编号</td>
<td align="center">书名</td>
<td>价格</td>
<td>数量</td>
<td align="center">出版社</td>
</tr>
<%

while(rs.next())
{%>
<tr>

<td align="center"><%=rs.getString("id")%></td>
<td><%=rs.getString("bookName")%></td>
<td><%=rs.getString("price")%></td>
<td><%=rs.getString("count")%></td>
<td><%=rs.getString("publisherID")%></td>
</tr>
<%}%>
</thead>

</table>
</div>
</body>
</html>