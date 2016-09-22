<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%-- <%@ include file="/includes/cache.jsp"%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<head>
<title><decorator:title default="booking" /></title>
<%@ include file="/includes/style.jsp"%>
<decorator:head/>
</head>

<%-- <body bgcolor="#FFFFFF"	background="<%=request.getContextPath()%>/images/bg.gif"> --%>
<body bgcolor="#FFFFFF">
	<%@ include file="/includes/header.jsp"%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20" nowrap></td>
		</tr>
		<tr>
			<td width="1%" nowrap></td>
			<td width="16%" valign="top" nowrap>
 <%-- 				<%@ include file="/includes/navigation.jsp"%> --%>
			</td>
			<td width="2%" nowrap></td>
			<td valign="top"><br>
				<div class="docBody">
					<decorator:body />
				</div>
			</td>
			<td width="1%" nowrap></td>
		</tr>
	</table>
	<br>
	<%@ include file="/includes/footer.jsp"%> 
	<%@ include file="/includes/copyright.jsp"%>
</body>
</html>
