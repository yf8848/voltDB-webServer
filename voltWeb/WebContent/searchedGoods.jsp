<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>商品查找</title>
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
	<div class="container" style="float:center">
	  <div class="row">
	    <div class="col-md-12" style="background-color:#0F0;
	         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444; height:150px;">
	       <form class="b" method="post" action="${pageContext.request.contextPath}/main" >
			  <input type="hidden" class="form-control" name ="method" value="searchedDispaly" />
			    <div class="input-group col-lg-5" style="margin-left:570px; margin-top:60px;">
			       <input type="text" class="form-control" name="sname" id="sname" value="${sname}" />
			      <span class="input-group-btn">
			      <button class="btn btn-default" type="submit" > 搜索 </button>
			      </span> 
			     </div>
		  </form>
	    </div>
	    <div class="col-lg-12"  style="background-color:#0F0;
	         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444; height:800px;">
	      <div class="panel panel-default" style="margin-top:10px;">
	        <div class="panel-heading">
	          <h3 class="panel-title">" ${sname} " 相关</h3>
	        </div>
	        <div class="panel-body">
	        <c:set var="suffix" value=".jpg"></c:set>
	        <c:set var="basepath" value="/img/books/"></c:set>
	          <ul style="float:left">
	           <c:forEach items="${sgoods}" var="good" >
	            <li style="float:left; height:300px;width:220px; background-color:#C03; list-style-type:none; margin:20px;">
	              <div class="row">
	              	<a href="${pageContext.request.contextPath}/goodsInfo?method=getGoodsInfo&good=${good.mid}">
	              		<img alt="${good.mname}${suffix}" src="${pageContext.request.contextPath}${basepath}${good.mname}${suffix}" 
	              			class="img-rounded center-block col-lg-12" style="height:210px;"/>
	              	</a>
	                <div class="col-lg-12" style="margin-top:10px;">
	                  <div class="row">
	                    <div class="col-lg-12">
	                      <p class="text-left"> ${good.mname} </p>
	                      <p class="text-left"> ${good.mpress} </p>
	                      <p class="text-center"> 价格 ： ${good.mprice/100} </p>
	                      <p class="text-center"> 商家: </p>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </li>
	          </c:forEach>
	          </ul>
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