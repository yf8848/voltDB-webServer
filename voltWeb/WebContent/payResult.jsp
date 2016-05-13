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
	<title>支付结果</title>
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
	<div class="container" style="height:415px;">
	  <div class="row">
	  	<div style="margin-top:50px;">
	  		<label class="col-lg-2 control-label">结果: ${message } </label>
	  	</div>
	  	<c:set var="suffix" value=".jpg"></c:set>
	  	<c:set var="path" value="/img/"></c:set>
	  	<div style="margin-top:50px;" > 
          	<img src="${pageContext.request.contextPath}${path}${name}${suffix}" 
          		style="width:550px; height:235px;"  /> 
        </div>
        <div>
        	<c:if test="${fn:containsIgnoreCase('success', name) }">
        		<a href="${pageContext.request.contextPath}/main2.jsp" class="btn btn-primary btn-lg" role="button" style="margin-right:60px;" >返回首页</a>
        	</c:if>
        	<c:if test="${fn:containsIgnoreCase('fail', name) }">
        		<a href="${pageContext.request.contextPath}/order?method=repay&did=${did}" class="btn btn-primary btn-lg" role="button" style="margin-right:60px;" >重新支付</a>
        	</c:if>
        </div>
	  </div>
	</div>
	<div style="float:left; height:100px; width:1350px; background-color:#FF6; margin-top:20px;">
		<p class="col-lg-6 col-md-offset-4">&copy; foogle. All rights reserved. Design by foogle.</p>
	</div>
</body>
</html>