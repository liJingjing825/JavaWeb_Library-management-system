<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书搜索</title>
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
    background-color: gray;
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
    background-color: #F99;
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
	height: 300px;
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
	background-color:  rgba(255,255,255,0.3);
  border-radius:20px;
   font-family:"微软雅黑";

}

		</style>
</head>
<body background="./image/1.jpg style="background-repeat:no-repeat; background-size:100% 100%; background-attachment:fixed;">

<div align="center" class="div1" >
<font face="微软雅黑" color="#F0F8FF" size="+6">图书搜索</font>
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
            <li style="list-style-type:none;"><a href="bookloanedout.html">图书借阅</a></li>
             <li style="list-style-type:none;"><a href="bookreturn1.html">图书归还</a></li> 
             <li style="list-style-type:none;"><a href="bookserach.html">图书搜索</a></li>
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
		<form action="searchServlet" method="post" name="login" >
<br/><br/><br/>
<table border="1" align="center" class="tr">
<thead>
<tr>

<td>图书编号</td>
<td>书名</td>
<td>价格</td>
<td>数量</td>
<td>出版社</td>
<td>类别</td>
</tr>
<%String select=(String)session.getAttribute("bookName");

%>
<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");

String sql=null;
//out.print(select);
try{Connection con=null;
PreparedStatement ps=null;
ResultSet rs=null;
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/dh?useUnicode=true&&characterEncoding=utf-8";
con=(Connection) DriverManager.getConnection(url, "root", "root");
sql="select id,bookName,price,count,publisherId,typename from books where bookName=?";
ps=con.prepareStatement(sql);
ps.setString(1,select);
rs=ps.executeQuery();



while(rs.next()){
%>
<tr>

<td><%=rs.getString("id")%></td>
<td><%=rs.getString("bookName")%></td>
<td><%=rs.getString("price")%></td>
<td><%=rs.getString("count")%></td>
<td><%=rs.getString("publisherID")%></td>
<td><%=rs.getString("typename")%></td>
</tr>
<%}
rs.close();
ps.close();
con.close();}
catch(Exception e)
{
out.println(e.getMessage());
}
%>
</thead>
</table>
</div>
</body>
</html>