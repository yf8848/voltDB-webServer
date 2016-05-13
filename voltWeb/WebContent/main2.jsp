<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ page import="service.imp.*" %>
<%@ page import="domain.Goods" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>毕设</title>
<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">

<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script type="text/javascript">
function showCart(user)
{
	if(user == null)
	{
		location.href="${pageContext.request.contextPath}/login.jsp";
	}
	else
	{
		var show = "showCart";
		alert("UID : " + user.uid);
		location.href="${pageContext.request.contextPath}/cart?method="+showCart+"&uid="+user.uid;
	}
}

$(document).ready(function() {
	 $(".imageRotation").each(function(){
	 // 获取有关参数
	 var imageRotation = this,  // 图片轮换容器
	 imageBox = $(imageRotation).children(".imageBox")[0],  // 图片容器
	 titleBox = $(imageRotation).children(".titleBox")[0],  // 标题容器
	 titleArr = $(titleBox).children(),  // 所有标题（数组）
	 icoBox = $(imageRotation).children(".icoBox")[0],  // 图标容器
	 icoArr = $(icoBox).children(),  // 所有图标（数组）
	 imageWidth = $(imageRotation).width(),  // 图片宽度
	 imageNum = $(imageBox).children().size(),  // 图片数量
	 imageReelWidth = imageWidth*imageNum,  // 图片容器宽度
	 activeID = parseInt($($(icoBox).children(".active")[0]).attr("rel")),  // 当前图片ID
	 nextID = 0,  // 下张图片ID
	 setIntervalID,  // setInterval() 函数ID
	 intervalTime = 2000,  // 间隔时间
	 imageSpeed =500,  // 图片动画执行速度
	 titleSpeed =250;  // 标题动画执行速度
	 // 设置 图片容器 的宽度
	 $(imageBox).css({'width' : imageReelWidth + "px"});
	 // 图片轮换函数
	 var rotate=function(clickID){
	 if(clickID){ nextID = clickID; }
	 else{ nextID=activeID<=4 ? activeID+1 : 1; }
	 // 交换图标
	 $(icoArr[activeID-1]).removeClass("active");
	 $(icoArr[nextID-1]).addClass("active");
	 // 交换标题
	 $(titleArr[activeID-1]).animate(
	 {bottom:"-40px"},
	 titleSpeed,
	 function(){
	 $(titleArr[nextID-1]).animate({bottom:"0px"} , titleSpeed);
	 }
	 );
	 // 交换图片
	 $(imageBox).animate({left:"-"+(nextID-1)*imageWidth+"px"} , imageSpeed);
	 // 交换IP
	 activeID = nextID;
	 }
	 setIntervalID=setInterval(rotate,intervalTime);
	 $(imageBox).hover(
	 function(){ clearInterval(setIntervalID); },
	 function(){ setIntervalID=setInterval(rotate,intervalTime); }
	 ); 
	 $(icoArr).click(function(){
	 clearInterval(setIntervalID);
	 var clickID = parseInt($(this).attr("rel"));
	 rotate(clickID);
	 setIntervalID=setInterval(rotate,intervalTime);
	 });
	 });
	});
</script>
<style type="text/css">
.imageRotation {
	background-color: #CF3;
	height: 400px;
	width: 860px;
	overflow: hidden;  /*--超出容器的所有元素都不可见--*/
	position: relative;  /*--相对定位--*/
}
/*-------------图片容器---------------*/
.imageBox {
	position: absolute;  /*--固定定位--*/
	height: 400px;
	width: 860px;
	top: 0px;
	left: 0px;
	overflow: hidden;
}
.imageBox img {
	display: block;
	height: 400px;
	width: 860px;
	float: left;
	border: none;
}
/*-------------标题容器---------------*/
.titleBox {
	position: absolute;
	bottom: 0px;
	width: 860px;
	height: 40px;
	overflow: hidden;
}
.titleBox p {
	position: absolute;
	bottom: -40px;
	width: 860px;
	height: 40px;
	margin: 0px;
	padding: 0px 10px;
	line-height: 40px;
	z-index: 1;
	border-top: 1px solid #000;
	background-color: #000;
	color: #fff;
	font-family: "微软雅黑", "yahei";
	opacity: 0.5;
	-moz-opacity: 0.5;
	-webkit-opacity: 0.5;
	filter: alpha(opacity=50);
}
.titleBox p span {
	opacity: 1;
	-moz-opacity: 1;
	-webkit-opacity: 1;
	filter: alpha(opacity=100);
}
.titleBox p.active {
	bottom: 0px;
}
/*-------------图标容器---------------*/
.icoBox {
	position: absolute;  /*--固定定位--*/
	bottom: 14px;
	right: 15px;
	width: 76px;
	height: 12px;
	text-align: center;
	line-height: 40px;
	z-index: 2;
}
.icoBox span {
	display: block;
	float: left;
	height: 12px;
	width: 12px;
	margin-left: 3px;
	overflow: hidden;
	background: url("images/ico.png") 0px 0px no-repeat;
	cursor: pointer;
}
.icoBox span.active {
	background-position: 0px -12px;
	cursor: default;
}
</style>
</head>
<body>
<jsp:useBean id="Goods_main" class="service.imp.GoodsMainService" scope="session"></jsp:useBean>
<% 
	String basePath=request.getContextPath();
	List<String> lkinds = new ArrayList<String>();
	lkinds.add("计算机");
	lkinds.add("经济学");
	lkinds.add("文学");
	lkinds.add("医学");
	lkinds.add("教育");	
	pageContext.setAttribute("kinds", lkinds);
%>
	<div style="height:50px; width:1350px; background-color:#CF0">
		<div style="float:left; margin-right:100px;">
			<a href="${pageContext.request.contextPath}/main2.jsp">主页</a> 
		</div>
		 <c:if test = "${empty user }">
		 	 <div style="margin-left:600px; float:left;"> 
			 	 <a href="${pageContext.request.contextPath}/login.jsp" style="margin-right:10px;">登录</a> 
			 	 <a href="${pageContext.request.contextPath}/register.jsp">注册</a> 
		 	 </div>
		</c:if> 
		<c:if test="${not empty user }">
			<div style="margin-left:600px; float:left;"> 
		 		<a style="margin-right:10px;">当前用户:${user.uname}</a> 
				<a href="${pageContext.request.contextPath}/user?method=logout">退出</a> 
			</div>						
		</c:if>
		  	<div style="float:right; margin-right:100px;"> 
			  	<a href="${pageContext.request.contextPath}/cart?method=showCart&user=${user }">购物车</a> 
			  	<a href="${pageContext.request.contextPath}/order?method=showOrder">订单</a> 
		  	</div>
	</div>
	<div class="container" style="float:center">
	<div class="row">
	<div class="col-md-12" style="background-color:#0F0;
	         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444; height:150px;">
	  <form class="b" method="post" action="${pageContext.request.contextPath}/main" >
	  <input type="hidden" class="form-control" name ="method" value="searchedDispaly" />
	    <div class="input-group col-lg-5" style="margin-left:570px; margin-top:60px;">
	       <input type="text" class="form-control" name="sname" id="sname" value="" />
	      <span class="input-group-btn">
	      <button class="btn btn-default" type="submit" > 搜索 </button>
	      </span> 
	     </div>
	  </form>
	</div>
	<div class="col-md-12" style="background-color:#0C0;
	         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
	  <div class="row">
	    <div class="col-lg-3" style="height:400px; background-color:#CC0">
	      <div style="margin-top:5px;"> 
	      	<a href="${pageContext.request.contextPath}/main?method=kindsDisplay&kinds=${kinds.get(0)}" class="list-group-item" style="line-height:40px;">
	       	 <h3>${kinds.get(0)}类</h3>
	        </a> 
	        <a href="${pageContext.request.contextPath}/main?method=kindsDisplay&kinds=${kinds.get(1)}" class="list-group-item" style="line-height:40px;">
	        <h3>${kinds.get(1)}类</h3>
	        </a> <a href="${pageContext.request.contextPath}/main?method=kindsDisplay&kinds=${kinds.get(2)}" class="list-group-item" style="line-height:40px;">
	        <h3>${kinds.get(2)}类</h3>
	        </a> <a href="${pageContext.request.contextPath}/main?method=kindsDisplay&kinds=${kinds.get(3)}" class="list-group-item" style="line-height:40px;">
	        <h3>${kinds.get(3)}类</h3>
	        </a> <a href="${pageContext.request.contextPath}/main?method=kindsDisplay&kinds=${kinds.get(4)}" class="list-group-item" style="line-height:40px;">
	        <h3>${kinds.get(4)}类</h3>
	        </a> </div>
	    </div>
	    <div class="col-lg-9" style="background-color:#F96">
          <div class="imageRotation">
            <div class="imageBox"> 
            <img src="img/img2.jpg"/> 
            <img src="img/img3.jpg"/> 
            <img src="img/img4.jpg"/> 
            <img src="img/img5.jpg"/> 
            <img src="img/img6.jpg"/> 
            </div>
            <div class="titleBox">
              <p class="active"><span>第一张图片标题</span></p>
              <p>第二张图片标题</p>
              <p>第三张图片标</p>
              <p>第四张图片标题</p>
              <p>第五张图片标题</p>
            </div>
            <div class="icoBox"> <span class="active" rel="1">1</span> <span rel="2">2</span> <span rel="3">3</span> <span rel="4">4</span> <span rel="5">5</span> </div>
          </div>
        </div>
	  	 <% 
			//Goods_main mgoods = new Goods_main();
			for(int i=0;i<lkinds.size();++i)
			{
				Set<Goods> lgoods = Goods_main.getKindsGoods(lkinds.get(i));
				System.out.println(lkinds.get(i)+" : " + i);
				request.setAttribute("kind", lkinds.get(i));
	  	%>
	  	<div class="col-lg-12" style="background-color:#F63; height:50px; margin:5px 0px 5px 0px;">
	      <div class="row">
	        <div class="col-md-6">
	          <h3> ${kind}</h3>
	        </div>
	        <div class="col-md-6">
	          <p class="text-right" style="font-size:24px; line-height:50px">
	          		<a href="${pageContext.request.contextPath}/main?method=kindsDisplay&kinds=${kind}">查看全部</a></p>
	        </div>
	      </div>
	     </div>
	      <div class="col-md-12" style="background-color:#0F0;
	       	 box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444; height:260px;">
	     	 <ul style="float:left">
	    <% 
	    	int num = 0;
	    	Iterator<Goods> iterator = lgoods.iterator();
			while(iterator.hasNext())
			{
				num ++;
				if(num > 5 )
				{
					break;
				}
				Goods good = iterator.next();
				String ppath = good.getMname();
				//ppath = request.getContextPath()+"/WebContent/img/books/"+ppath+".jpg";
				ppath+=".jpg";
				ppath = "img/books/"+ppath;
				System.out.println(ppath);
				pageContext.setAttribute("good",good);
				pageContext.setAttribute("imgpath", ppath);
		%>	
		
	        <li style="float:left; height:200px;width:170px; background-color:#C03; list-style-type:none; margin:20px;">
	          <div >
	          <a href="${pageContext.request.contextPath}/goodsInfo?method=getGoodsInfo&good=${good.mid}">
	        	  <img alt="${imgpath}" src="${pageContext.request.contextPath}/${imgpath}" width="170px" height="200px" >
	          </a>
		<% 
			
			System.out.println(good);
			String ms="<br>";
			ms+=good.getMname()+"</br>";
			ms+=good.getMprice()/100+"</br></br>";
			System.out.println(ms);
			out.write(ms);
			ms="";
		%>
		
		</div>
	    </li>
	    <%} %>
	    </ul>
	  </div>
	  <%} %>
	</div>
	</div>
	</div>
</div>
	<div style=" float:left; height:100px; width:1450px; background-color:#FF6"></div>
</body>
</html>