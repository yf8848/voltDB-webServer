<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ page import="service.imp.*" %>
<%@ page import="domain.Goods" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>毕设</title>
<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">

<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="bootstrap-3.3.5-dist/js/jquery.min.js"></script>
</head>
<body>
	<div style="height:50px; width:1350px; background-color:#CF0">
	<c:if test = "${empty user }">
	 	 <div style="margin-left:600px; float:left;"> <a href="#" style="margin-right:10px;">登录</a> <a href="#">注册</a> </div>
	</c:if> 
	<c:if test="${not empty user }">
		<div style="margin-left:600px; float:left;"> 
	 		<a href="#" style="margin-right:10px;">当前用户:${user.username}</a> 
			<a href='${pageContext.request.contextPath}/user?method=logout'>注册</a> </div>						
	</c:if>
	  	<div style="float:right; margin-right:100px;"> <a href="#">购物车</a> <a href="#">订单</a> </div>
	</div>
	<div class="container" style="float:center">
	<div class="row">
	<div class="col-md-12" style="background-color:#0F0;
	         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444; height:150px;">
	  <form class="b" >
	    <div class="input-group col-lg-5" style="margin-left:570px; margin-top:60px;">
	      <input type="text" class="form-control" />
	      <span class="input-group-btn">
	      <button class="btn btn-default"> 搜索 </button>
	      </span> </div>
	  </form>
	</div>
	<div class="col-md-12" style="background-color:#0C0;
	         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
	  <div class="row">
	    <div class="col-lg-3" style="height:400px; background-color:#CC0">
	      <div style="margin-top:5px;"> 
	      	<a href="#" class="list-group-item" style="line-height:40px;">
	        <h3>计算机类</h3>
	        </a> <a href="#" class="list-group-item" style="line-height:40px;">
	        <h3>经济类</h3>
	        </a> <a href="#" class="list-group-item" style="line-height:40px;">
	        <h3>文学类</h3>
	        </a> <a href="#" class="list-group-item" style="line-height:40px;">
	        <h3>医学类</h3>
	        </a> <a href="#" class="list-group-item" style="line-height:40px;">
	        <h3>教育类</h3>
	        </a> </div>
	    </div>
	    <div class="col-lg-9">
	      
	    </div>
	  	 <% 
			//Goods_main mgoods = new Goods_main();
		  	List<String> lkinds = new ArrayList<String>();
			lkinds.add("计算机");
	  	 	lkinds.add("经济学");
	  	 	lkinds.add("文学");
	  	 	lkinds.add("医学");
	  	 	lkinds.add("教育");
	  	 	pageContext.setAttribute("lkinds", lkinds);
	  	%>
	  	<c:set var="suffix" value=".jpg"></c:set>
	  	<c:set var="basepath" value="/img/books/"></c:set>
	  	<c:forEach items="${lkinds}" var="kind">
	  		<div class="col-lg-12" style="background-color:#F63; height:50px; margin:5px 0px 5px 0px;">
		      <div class="row">
		        <div class="col-md-6">
		          <h3> ${kind}</h3>
		        </div>
		        <div class="col-md-6">
		          <p class="text-right" style="font-size:24px; line-height:50px"><a href="/goods.jsp">查看全部</a></p>
		        </div>
		      </div>
		     </div>
	      <div class="col-md-12" style="background-color:#0F0; 
	      			box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444; height:260px;">
	      			<a href="${pageContext.request.contextPath}/main?method=kindsDisplay&kinds=${kind}">ss</a>
	     	<ul style="float:left" >
	     	 <c:forEach items="${kgoods}" var="good" begin="0" end="4" >
	     	 	<li style="float:left; height:200px;width:170px; background-color:#C03; list-style-type:none; margin:20px;">
	         	<div >
	          		<img alt="${good.mname}${suffix}" src="${pageContext.request.contextPath}${basepath}${good.mname}${suffix}" width="170px" height="200px">
	          		<br>${good.mname}
	          		${good.mprice/100}
	     		</div>
	   	 		</li>
	     	 </c:forEach>
	     	</ul>
	  	</div>
	  	</c:forEach>
	  	</div>
	</div>
	</div>
	</div>
	<div style=" float:left; height:100px; width:1350px; background-color:#FF6"></div>
</body>
</html>