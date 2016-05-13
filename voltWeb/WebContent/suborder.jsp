<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>订单结算页</title>
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
    <div class="col-lg-12" style="background:#FFF">
    <c:set var="all" value="0"></c:set>
      <form class="form-horizontal" style="margin-top:60px;"
      		method="post" action="${pageContext.request.contextPath}/order?method=createorder"  >
        <div class="row">
          <div class="col-lg-6">
            <div class="form-group">
              <label class="col-lg-2 control-label">姓名</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" id="name" name="recName" placeholder="请输入收货人姓名" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-2 control-label">手机号</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" id="phone" name="recPhone" placeholder="请输入收货人联系方式" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-2 control-label">收货地址</label>
              <div class="col-lg-10">
                <textarea class="form-control" name="recAddress" rows="3" placeholder="请输入收货地址" ></textarea>
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-2 control-label">邮编</label>
              <div class="col-lg-10">
                <input type="text" class="form-control" name="passCode" placeholder="请输入邮编" />
              </div>
            </div>
          </div>
          <div class="col-lg-6"> 
          	<img src="${pageContext.request.contextPath}/img/suborder.gif" 
          		style="width:550px; height:235px;"  /> 
          </div>
        </div>
        <hr style="margin-top:80px;" />
        <div style="margin-top:50px;"></div>
        <h4 style="font-weight:bold">送货清单</h4>
         <hr />
	      <div class="col-lg-12">
	        <ul>
	          <li style="list-style:none">
	            <ul>
	              <li style="list-style:none">
	                <table class="table">
	                  <tr style="text-align:center">
	                    <th></th>
            			<th class="text-center">商品</th>
           				<th class="text-center">单价</th>
            			<th class="text-center">数量</th>
            			<th class="text-center">小计</th>
	                  </tr>
	                </table>
	              </li>
	            </ul>
	          </li>
	        </ul>
	        <c:set var="suffix" value=".jpg"></c:set>
	  	  	<c:set var="basepath" value="/img/books/"></c:set>
	        <c:forEach items="${spname}" var="sp">
	        <ul>
	          <li style="margin-top:30px; list-style:none">
	            <p style="margin-left:35px;">${sp } </p>
	            <hr />
	            <ul>
	            <c:forEach items="${morder.get(sp) }" var="cart">
	              <li style="margin-top:20px; list-style:none">
	                <table class="table">
	                  <tr>
						<c:set var="p" value="${cart.mprice*cart.cnum/100 }"></c:set>
			            <td style="background-color:#099; width:60px; height:70px;">
			            	<img src="${pageContext.request.contextPath}${basepath}${cart.mname}${suffix}" style="width:60px; height:70px; background-color:#9F0" />
			            </td>
			            <td style="background-color:#C33; width:180px;" class="text-center">${cart.mname }</td>
			            <td style="background-color:#CF0; width:180px;" class="text-center">${cart.mprice/100 }</td>
			            <td style="background-color:#FF6; width:180px;" class="text-center">${cart.cnum }</td>
			            <td style="background-color:#CF0; width:180px;" class="text-center">￥ ${p }</td>
						<c:set var="all" value="${all+p }"></c:set>
	                  </tr>
	                </table>
	              </li>
	              </c:forEach>
	            </ul>
	          </li>
	          </ul>
	          </c:forEach>
	        <table class="table" style="border:none">
	          <tr>
	            <td colspan="5" align="right">总价:￥ ${all } 元</td>
	          </tr>
	          <input type="hidden" class="form-control" id="name" name="mnum" value="${all }" />
	          <tr>
	            <td colspan="5" align="right">
	            	<button class="btn btn-primary btn-lg" type="submit" style="margin-right:60px;">提交订单</button>
	            </td>
	          </tr>
	        </table>
	      </div>
      </form>
    </div>
  </div>
</div>
<div style="float:left; height:100px; width:1350px; background-color:#FF6; margin-top:20px;">
		<p class="col-lg-6 col-md-offset-4">&copy; foogle. All rights reserved. Design by foogle.</p>
	</div>
</body>
</html>