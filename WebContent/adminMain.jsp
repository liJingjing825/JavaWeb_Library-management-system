
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>


 <title>图书馆管理系统</title>
    <link rel="stylesheet" type="text/css" href="../css/caidan.css">


<script type="text/javascript">
function change() {
    var secondDiv=this.parentNode.getElementsByTagName("ul")[0];// 返回的是ul标签,返回的是数组的第一个ul标签
    //选择li的ul
    if (secondDiv.className=="myhide")    
        secondDiv.className="myshow";
    else
        secondDiv.className="myhide";
}
window.onload=function(){//网页加载完后执行的一个方法,onload是window窗口对象的事件属性，
	//语句是把匿名函数赋值给window的onload事件属性，当window加载完成时会触发onload事件,
	//也就触发了匿名函数,执行函数体内的语句
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
		</style>
</head>
<body background="./image/1.jpg" style="background-repeat:no-repeat; background-size:100% 100%; background-attachment:fixed;">

<div align="center" class="div1" >
<font face="微软雅黑" color="#F0F8FF" size="+6"><strong>图书馆管理</strong></font>
</div>
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


</body>
</html>
