<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>我的订单</title>
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/jquery.min.js"></script>
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
	<div class="col-lg-12" style="height:70px; background-color:#FC3"></div>
	<div class="col-lg-12" style="background-color:#9C0">
	<h2>全部商品</h2>
	<hr />
	<div class="col-lg-12">
	<ul>
	  <li style="list-style:none">
	    <ul>
	      <li style="list-style:none">
	        <table class="table">
	          <tr style="text-align:center">
	            <th class="text-center" style="width:75px;">图片</th>
	            <th class="text-center" style="background-color:#C33; width:170px;">商品</th>
	            <th class="text-center" style="background-color:#CF0; width:170px;">数量</th>
	            <th class="text-center" style="background-color:#FF6; width:170px;">金额</th>
	            <th class="text-center" style="background-color:#CF0; width:170px;">状态</th>
	            <th class="text-center" style="background-color:#CF0; width:170px;">交易时间</th>
	            <th class="text-center" style="background-color:#FF6;">操作</th>
	          </tr>
	        </table>
	      </li>
	    </ul>
	  </li>
	</ul>
	<c:set var="suffix" value=".jpg"></c:set>
	<c:set var="path" value="/img/books/"></c:set>
	<form action="" method="post">
	<c:forEach items="${didSet}" var="did">
	<ul>
	  <li style="margin-top:30px; list-style:none">
	    <div class="row">
	    <c:set var="order" value="${mapOrder.get(did) }"></c:set>
	    <c:set var="dstate" value="交易成功"></c:set>
	       <div class="col-lg-6">
	         <p >订单号 : ${order.did }</p>
	       </div>
	       <div class="col-lg-6">
	       <c:if test="${order.dstate != 2 }">
				<p class="text-right"><a href="${pageContext.request.contextPath}/order?method=repay&did=${order.did}" >重新支付</a></p>
				<c:set var="dstate" value="支付失败"></c:set>
				<c:if test="${order.dstate == 0 }">
					<c:set var="dstate" value="待支付"></c:set>
				</c:if>
			</c:if>
	       </div>
	    </div>
	    <hr style="margin:0 0 0 0;"/>
	    <ul>
	    <c:forEach items="${mCarts.get(did) }" var="cart">
	      <li style="margin-top:10px; list-style:none">
	        <table class="table">
	          <tr>
	            <td style="background-color:#099; width:60px; height:70px;">
		            <img  style="width:60px; height:70px; background-color:#9F0" 
		           		alt="${cart.mname}${suffix}" src="${pageContext.request.contextPath}${path}${cart.mname}${suffix}" />
	            </td>
	            <td class="text-center" style="background-color:#C33; width:170px;">${cart.mname }</td>
	            <td class="text-center" style="background-color:#CF0; width:170px;">${cart.cnum }</td>
	            <td class="text-center" style="background-color:#F60; width:170px;">${cart.cnum*cart.mprice/100 }</td>
	            <td class="text-center" style="background-color:#CF0; width:170px;">${dstate }</td>
	            <td class="text-center" style="background-color:#CF0; width:170px;">${cart.paytime }</td>
	            <td class="text-center" style="background-color:#FF6;">
	            	<a href="${pageContext.request.contextPath}/order?method=remove&cid=${cart.cid }"
								onclick="delConfirm(event)">删除</a>
	          </tr>
	        </table>
	      </li>
	      </c:forEach>
	    </ul>
	  </li>
	</ul>
	</c:forEach>
	</form>
	</div>
	</div>
	</div>
	</div>
	<div style="float:left; height:100px; width:1350px; background-color:#FF6; margin-top:20px;">
		<p class="col-lg-6 col-md-offset-4">&copy; foogle. All rights reserved. Design by foogle.</p>
	</div>
</body>
</html>