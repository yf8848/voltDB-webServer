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
	<title>订单支付</title>
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
	    <div class="col-lg-12" style="height:450px;">
	      <div class="row" style=" margin-top:40px;">
	        <div class="col-lg-5">
	          <h4>价格：${order.dprice/100 } </h4>
	          <h4>购买时间：${order.createtime }</h4>
	          <h4>订单号：${order.did }</h4>
	          <div class="row">
	          <form method="post" action="${pageContext.request.contextPath}/order?method=payorder&did=${order.did}" >
	            <div class="col-lg-12" style="margin-top:30px;">
	              <div class="form-group">
	                <label for="lastname" class="col-sm-3 control-label text-right" style="font-size:27px;">支付密码:</label>
	                <div class="col-sm-9">
	                  <input type="password" class="form-control" id="lastname" name="payword" style="width:200px;">
	                </div>
	              </div>
	            </div>
	            <div class="col-lg-12"  style="margin-top:30px;">
	              <button class="btn btn-primary btn-lg" type="submit" style="margin-right:60px;">确认付款</button>
	            </div>
	            </form>
	          </div>
	        </div>
	        <div class="col-lg-7">
	          <h1 style="margin:20px;">价格 : ${order.dprice/100 }</h1>
	        </div>
	      </div>
	      <div class="col-lg-12"> </div>
	    </div>
	  </div>
	</div>
	<div style="float:left; height:100px; width:1350px; background-color:#FF6; margin-top:20px;">
		<p class="col-lg-6 col-md-offset-4">&copy; foogle. All rights reserved. Design by foogle.</p>
	</div>
</body>
</html>