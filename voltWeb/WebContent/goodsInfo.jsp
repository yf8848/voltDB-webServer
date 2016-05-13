<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/jquery.min.js"></script>
	<title>商品详情</title>
	
	<script type="text/javascript">
		function numbText(e) 
		{
			//考虑浏览器兼容问题
			if (e && e.stopPropagation) { //e存在
				code = e.which; //firefox获取键码
			} else {
				//不存在 --对应ie浏览器
				code = window.event.keyCode;  //id浏览器中获取键码值
			}
			//alert(code);
			//48--57代表的是数字0-9    8与46代表的是delete 与backspace
			if (!(code >= 48 && code <= 57 || code == 8 || code == 46))
			{
				//阻止事务的默认行为执行.
				if (e && e.stopPropagation) { //e存在
					e.preventDefault(); //firefox阻止默认事务执行
				} else {
					//不存在 --对应ie浏览器
					window.event.returnValue = false; //ie浏览器阻止默认事件执行.
				}
			}
		}
		
		function addNum()
		{
			var count = document.getElementById("text").value
			document.getElementById("text").value=parseInt(count)+1;
		}
		
		function decNum()
		{
			var count = document.getElementById("text").value
			if(parseInt(count) <= 0)
			{
				document.getElementById("text").value=0;
			}
			else
			{
				document.getElementById("text").value=parseInt(count)-1;
			}
		}
		
		function chNum(num)
		{
			if(parseInt(num) >= 0)
			{
				document.getElementById("text").value=parseInt(num);
			}
		}
		
		function addCart(mid, uid)
		{
			//test(uid);
			if(typeof(uid) == undefined || !uid)
			{
				//alert(uid);
				gotoLogin();
			}
			var count=parseInt(document.getElementById("text").value);
			//alert("mid : " + mid + "\t count : " + count+ "\t uid : " + uid);
			var meth = "addCart";
			alert("添加成功")
			location.href = "${pageContext.request.contextPath}/cart?method="+meth+"&mid=" + mid + "&count=" + count+"&uid="+uid;
			
		}
		
		function oneBuy(mid, uid)
		{
			if(typeof(uid) == undefined || !uid)
			{
				//alert(uid);
				gotoLogin();
			}
			var count=parseInt(document.getElementById("text").value);
			var meth = "oneKeyBuy";
			alert("确定购买");
			location.href = "${pageContext.request.contextPath}/order?method="+meth+"&mid=" + mid + "&count=" + count+"&uid=" + uid;
		}
		
		function gotoLogin()
		{
			//alert("!!");
			location.href="${pageContext.request.contextPath}/login.jsp";	
		}
		
		function test(mid)
		{
			alert("onclick 测试 : "+ mid);
		}
		
	</script>
</head>
<body>
	<c:set var="suffix" value=".jpg"></c:set>
	<c:set var="basepath" value="/img/books/"></c:set>
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
			<div style="margin-left:500px; float:left;"> 
		 		<a style="margin-right:10px;">当前用户:${user.uname}</a> 
				<a href="${pageContext.request.contextPath}/user?method=logout">退出</a> 
			</div>	
			<div style="float:right; margin-right:100px;"> 
			  	<a href="${pageContext.request.contextPath}/cart?method=showCart&user=${user }">购物车</a> 
			  	<a href="${pageContext.request.contextPath}/order?method=showOrder">订单</a> 
		  	</div>					
		</c:if>
	</div>
	<div class="container" style="float:center">
		<div class="row">
		<div class="col-md-12" style="background-color:#0F0;
		         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444; height:150px;">
		  <form class="b" method="post" action="${pageContext.request.contextPath}/goodsInfo" >
		  <input type="hidden" class="form-control" name ="method" value="searchedDispaly" />
		    <div class="input-group col-lg-5" style="margin-left:570px; margin-top:60px;">
		       <input type="text" class="form-control" name="sname" id="sname" value="" />
		      <span class="input-group-btn">
		      <button class="btn btn-default" type="submit" > 搜索 </button>
		      </span> 
		     </div>
		  </form>
		</div>
	    <div class="col-lg-12" style="margin-top:10px;">
	      <div class="row">
	        <div class="col-lg-4" style="background-color:#3FF; height:500px;"> 
	        	<img class="img-responsive" style="width:350px; height:400px; margin-left:10px;margin-top:15px;"
	        	alt="${goodsName}${suffix}" src="${pageContext.request.contextPath}${basepath}${goodsName}${suffix}" /> 
	        </div>
	        <div class="col-lg-8" style="background-color:#FF3; height:500px;">
	          <div class="row" style="width:500px; margin-left:40px;">
	           <form action="" method="post" class="form-horizontal" >
	            <div class="col-lg-12" style="margin-top:70px; width:500px;   background-color:#CF3">
	                <h2>${goodsName}</h2>
	                <h4>作者 : ${goodsAuthor}</h4>
	                <h4>出版社 : ${goodsPress }</h4>
	                <h4>出版时间 : ${goodsPubTime }</h4>
	                <h4>单价 : ${goodsPrice/100 } </h4>
	                <h4>商家 ： ${goodsSp }</h4>
	            	<h4>数量 ： 
						<input type="button" value="-"
							onclick="decNum()">
						<input type='text' value="1" id="text" name="num"
							style="text-align:center" onkeydown="numbText(event);"
							onblur="chNum(this.value)">

						<input type="button" value='+'
							onclick="addNum()">
					</h4>
				</div>
				<div class="col-lg-12">
	              <div>
		              <input type="submit" style="font-size:30px; margin:15px" value="加入购物车" onclick="addCart('${goodsMid}','${user.uid }')">
	     			  <input type="button" style="font-size:30px; margin:15px" value="一键购买" onclick="oneBuy('${goodsMid}','${user.uid }')">
	              </div>
	            </div>
	            </form>
	            </div>
	          </div>
	        </div>
	      </div>
	     </div>
	    </div>
	<div style="float:left; height:100px; width:1350px; background-color:#FF6; margin-top:20px;">
		<p class="col-lg-6 col-md-offset-4">&copy; foogle. All rights reserved. Design by foogle.</p>
	</div>
</body>
</html>