<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/jquery.min.js"></script>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
 <body>
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
	<div class="container">
	<div class="row">
	  <div class="col-lg-12" style="margin-top:20px;">
	    <div class="row">
	      <div class="col-lg-6" style="background-color:#9F0; height:500px;"> 
	      <img src="img/img2_thumb.jpg" style="width:540px; height:500px;" />
	      </div>
	      <div class="col-lg-6" style="background-color:#9C0; height:500px;">
	      <form action="${pageContext.request.contextPath}/user?method=login" method="post" 
	      			class="form-horizontal col-lg-9" role="form" style="margin:90px 60px;">
	      	<div class="from-group">
				<font color='red'>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${requestScope["login.message"]}</font>
				<br><br>										
	      	</div>
	        <div class="form-group">
	          <button type="button" class="btn btn-default btn-sm col-sm-2" style="height:35px;"> 
	          	<span class="glyphicon glyphicon-user"></span>User 
	          </button>
	          <div class="col-sm-10">
	            <input class="form-control" type="text" id="firstname" name="username" placeholder="请输入用户名">
	          </div>
	        </div>
	        
	        <div class="form-group">
	          <button type="button" class="btn btn-default btn-sm col-sm-2" style="height:35px;"> 
	          	<span class="glyphicon glyphicon-lock"></span>Lock 
	          </button>
	          <div class="col-sm-10">
	            <input type="password" class="form-control" id="lastname" name="password" placeholder="请输入密码">
	          </div>
	        </div>
	        <button class="btn btn-default btn-lg btn-block" type="submit" style="margin-top:40px;">登录</button>
	      </form>
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
