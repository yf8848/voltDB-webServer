<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>购物车</title>
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<script src="bootstrap-3.3.5-dist/js/jquery.min.js"></script>
	<script type="text/javascript">
	function numbText(e) {
	
			//考虑浏览器兼容问题
			if (e && e.stopPropagation) { //e存在
				code = e.which; //firefox获取键码
			} else {
				//不存在 --对应ie浏览器
				code = window.event.keyCode;  //id浏览器中获取键码值
			}
	
			//alert(code);
			//48--57代表的是数字0-9    8与46代表的是delete 与backspace
			if (!(code >= 48 && code <= 57 || code == 8 || code == 46)) {
				//阻止事务的默认行为执行.
	
				if (e && e.stopPropagation) { //e存在
					e.preventDefault(); //firefox阻止默认事务执行			
	
				} else {
					//不存在 --对应ie浏览器
					window.event.returnValue = false; //ie浏览器阻止默认事件执行.
	
				}
	
			}
		}
	function changeCount(id, count, mid) {
			//alert("uid : " + id+ " count :  " +count +"mid : " + mid);
			if (parseInt(count) < 0) {
				count = 1;
			}
			else if(parseInt(count)==0)
			{
				var flag=window.confirm("确认删除商品吗");
				if(!flag){
					return ;
				}
			}
	
			//向一个servlet发送请求
			var u = "update";
			location.href = "${pageContext.request.contextPath}/cart?method=" + u +"&uid=" + id
					+ "&count=" + count + "&mid=" + mid;
	
		}
		
		function delConfirm(e){
			var flag=window.confirm("确认删除商品吗");
			
			if(!flag){
				//不删除商品		
				//要想不删除商品，要阻止事件的默认行为执行.
				if(e&&e.preventDefault){
					// e对象存在，preventDefault方法存在 ---- 火狐浏览器
					e.preventDefault();
				}else{
					// 不支持e对象，或者没有preventDefault方法 ---- IE
					window.event.returnValue = false;
				}
	
			}
			
		}
		
		function gotoorder(){
			
			location.href="${pageContext.request.contextPath}/order.jsp";	
		}
		
		var sum=0;
		var smid = new String();
		function ban(obj, mid)
		{
			if(obj.checked)
			{
				sum+=parseInt(obj.value);
				if(smid.search(/mid/) == -1)
				{
					if(smid.length == 0)
					{
						smid +=mid;
					}
					else
					{
						smid +=" "+mid;	
					}
				}
			}else if(!obj.checked){
				sum-=parseInt(obj.value);
				var pos = smid.search(mid);
				//alert(pos);
				if(pos != -1)
				{
					var temp = smid.substring(0,pos-1);
					temp += smid.substring(pos+mid.length);
					smid = temp;
					//alert(temp);
				}
			}
			//alert(smid);
			var p=document.getElementById('price');
			p.innerText=sum;
		}
		
		function suborder(spname, uid)
		{
			//alert("提交订单 : "+spname + smid + sum +uid);
			var u = "suborder";
			location.href = "${pageContext.request.contextPath}/order?method=" + u +"&mnum="+sum+"&uid=" + uid + "&spname=" + spname + "&smid=" + smid;
			
		}
	</script>
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
	    <div class="col-lg-12" style="height:70px; background-color:#FC3"> </div>
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
	                    <th class="text-center" style="background-color:#099; width:75px;"></th>
	                    <th class="text-center" style="background-color:#C33; width:200px;">商品</th>
	                    <th class="text-center" style="background-color:#CF0; width:200px;">价格</th>
	                    <th class="text-center" style="background-color:#FF6; width:200px;">数量</th>
	                    <th class="text-center" style="background-color:#CF0; width:200px;">小计</th>
	                    <th class="text-center" style="background-color:#FF6;">操作</th>
	                  </tr>
	                </table>
	              </li>
	            </ul>
	          </li>
	        </ul>
	        <c:set var="spname" value=""></c:set>
	        <c:forEach items="${sspname}" var="sp">
	        <c:set var="spname" value="${spname } ${sp }"></c:set>
	       
	        <ul>
	          <li style="margin-top:30px; list-style:none">
	            <p style="margin-left:35px;">${sp } </p>
	            <hr />
	            <ul>
	            <c:forEach items="${mCarts.get(sp) }" var="cart">
	              <li style="margin-top:20px; list-style:none">
	                <table class="table">
	                  <tr>
	                  	<c:set var="suffix" value=".jpg"></c:set>
	  					<c:set var="basepath" value="/img/books/"></c:set>
	  					<c:set var="mid" value="${cart.mid }"></c:set>
	  					<td style="width:20px;"><input type="checkbox" onclick="ban(this, '${cart.mid }')" value="${cart.cnum*cart.mprice/100}"></td>
	                    <td style="background-color:#099; width:60px; height:70px;">
	                    <a href="${pageContext.request.contextPath}/goodsInfo?method=getGoodsInfo&good=${cart.mid}">
	                    	<img src="${pageContext.request.contextPath}${basepath}${cart.mname}${suffix}" style="width:60px; height:70px; background-color:#9F0" />
	                    </a>
	                    </td>
	                    <td class="text-center" style="background-color:#C33; width:200px;">${cart.mname}</td>
	                    <td class="text-center" style="background-color:#CF0; width:200px;">${cart.mprice/100 }</td>
	                    <td class="text-center" style="background-color:#CF0; width:200px;">
	                      <input type="button" value="-"
								onclick="changeCount('${cart.uid }','${cart.cnum-1}','${mid }')">
	                      <input type="text" value="${cart.cnum }"
								style="text-align:center; width:100px" onkeydown="numbText(event);"
								onblur="changeCount('${cart.uid }',this.value,'${mid }')">
	                      <input type="button" value='+'
								onclick="changeCount('${cart.uid }','${cart.cnum+1}','${mid }')"></td>
						<td class="text-center" style="background-color:#FF6; width:200px;">${cart.cnum*cart.mprice/100}</td>
	                    <td class="text-center" style="background-color:#FF6;">
	                    	<a href="${pageContext.request.contextPath}/cart?method=remove&uid=${user.uid}&mid=${mid }"
								onclick="delConfirm(event)">删除</a>
						</td>
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
	            <td colspan="5" align="right">总价:￥<span id="price">0</span>元</td>
	          </tr>
	          <tr>
	            <td colspan="5" align="right"><button class="btn btn-default" onclick="suborder('${spname}', '${user.uid }')" >结算</button></td>
	          </tr>
	        </table>
	      </div>
	    </div>
	  </div>
	</div>
	<div style="float:left; height:100px; width:1350px; background-color:#FF6; margin-top:20px;">
		<p class="col-lg-6 col-md-offset-4">&copy; foogle. All rights reserved. Design by foogle.</p>
	</div>
</body>
</html>
