<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/jquery.min.js"></script>
	<title>注册</title>
</head>
<body style="background-color:#E2FCF8">
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
	    <div class="col-lg-12" style="background-color:#FFC; margin-top:10px">
	      <div class="row">
	      <div class="col-lg-12">
	         <h2 class="text-center" style="background-color:#33F">欢迎注册</h2>
	         <hr style="border-bottom-style:dotted"/>
	      </div>
	        <div class="col-lg-6 col-md-offset-3">
	            <div>
	              <form action="${pageContext.request.contextPath}/user?method=register" method="post" class="form-horizontal" role="form" style="height:450px; margin-top:15px;">
	                <div class="form-group" style="margin:20px;">
	                  <label class="col-sm-2 control-label">用户名</label>
	                  <div class="col-sm-10">
	                    <input type="text" class="form-control" id="inputPassword" name="userName" placeholder="请输入用户名">
	                  </div>
	                </div>
	                <div class="form-group" style="margin:20px;">
	                  <label for="inputPassword" class="col-sm-2 control-label">密码</label>
	                  <div class="col-sm-10">
	                    <input type="password" class="form-control" id="inputPassword" name="password"
	         placeholder="请输入密码">
	                  </div>
	                </div>
	                <div class="form-group" style="margin:20px;">
	                  <label for="inputPassword" class="col-sm-2 control-label">邮箱</label>
	                  <div class="col-sm-10">
	                    <input type="email" class="form-control" id="inputPassword" name="umail"
	         placeholder="请输入邮箱号">
	                  </div>
	                </div>
	                <div class="form-group" style="margin:20px;">
	                  <label for="inputPassword" class="col-sm-2 control-label">手机号</label>
	                  <div class="col-sm-10">
	                    <input type="tel" class="form-control" id="inputPassword" name="uphone"
	         placeholder="请输入手机号">
	                  </div>
	                </div>
	                <div class="form-group" style="margin:20px;">
	                  <label for="name" class="col-sm-2 control-label">性别</label>
	                  <div class="col-sm-10">
	                    <select class="form-control" name="gender" >
	                      <option>男</option>
	                      <option>女</option>
	                    </select>
	                  </div>
	                </div>
	                <div class="form-group" style="margin:20px;">
	                  <label for="inputPassword" class="col-sm-2 control-label">年龄</label>
	                  <div class="col-sm-10">
	                    <input type="text" class="form-control" id="inputPassword" name="age"
	         placeholder="请输入你的年龄">
	                  </div>
	                </div>
	                <div class="col-lg-6 col-md-offset-4">
	                  <input class="btn btn-primary btn-lg" style="margin-right:20px;" type="submit" value="注册">
	                  <input class="btn btn-primary btn-lg" type="reset" value="重置">
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

