<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<style type="text/css">
  table.header {
    width: 100%;
    border=0; 
    cellspacing=0;
    cellpadding=0;
/*     overflow: hidden; */
/*     display: inline-block; */
    white-space: nowrap;
/*     text-align: center; */
    }
  table.header th {
    width: 30px;
/*     overflow: hidden; */
    display: inline-block;
    white-space: nowrap;
    text-align: center;
    }
  table.header td {
    width: 30px;
/*     overflow: hidden; */
    display: inline-block;
    white-space: nowrap;
    text-align: center;
}

	hr { color: #00008B;
		 background: #00008B; 
		 border: 0; 
		 height: 1px; 
		 }

	/*body { 
			background-image: url('images/back.jpg'); 
		width:      80%;
		height:     80%; 
		position:   relative;
		background-size:         100% auto; 
		-moz-background-size:	 100% auto;
		-webkit-background-size: 100% auto;
		-ms-background-size: 	 100% auto;
		-o-background-size: 	 100% auto;
		margin:     10px auto 0;
		} */
		
/* Background sfumato!*/
/*	body{
		background: url('images/back.jpg'); 
		width:      70%;
		height:     70%; 
		color:      #DAA520;
		position:   relative;
		background-size:         auto auto; 
		-moz-background-size:	 auto auto;
		-webkit-background-size: auto auto;
		-ms-background-size: 	 auto auto;
		-o-background-size: 	 auto auto;
		margin:     10px auto 0;
		background-image:  -webkit-linear-gradient(top left, rgba(178,34,34,  0.1),  rgba(75, 107, 175, 1)), url('images/back.jpg'); 
		background-image:     -moz-linear-gradient(top left, rgba(178,34,34,  0.1),  rgba(75, 107, 175, 1)), url('images/back.jpg');
		background-image:     -ms-linear-gradient(top left, rgba(178,34,34,  0.1),  rgba(75, 107, 175, 1)), url('images/back.jpg');
		background-image:     -o-linear-gradient(top left, rgba(178,34,34,  0.1),  rgba(75, 107, 175, 1)), url('images/back.jpg');
		background-image:     	   linear-gradient(top left, rgba(178,34,34,  0.1),  rgba(75, 107, 175, 1)), url('images/back.jpg');
	}*/

#locale A { padding:10px 0 1px 0; margin-left: 2px; display:block; float:left; }
#locale A.current { padding:2px 0 1px 0; }
</style>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="page"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="it.mf.i18n.Message" var="lang"/>
		
<table class="header">
		<tr>
			<td>
				<div id="examManagementLogo" align="left" >
				<a href="CalendarioAppController?action=edit">
					<img src="<%=request.getContextPath()%>/images/home_page.jpg" alt="examManagement"/>
				</a>
				</div>
				
			<td>
				<div id="checkout" align="right">
					<a href="<%=request.getContextPath()%>/LogoutServlet">
					<img src="<%=request.getContextPath()%>/images/shutdown.png" alt="Uscita"/>
					</a>
				</div>
			</td>

		</tr>
</table>
