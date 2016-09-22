<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista appelli</title>

<style type ="text/css">

		.cerchioR {
					width:5px;
					height:5px;
					border-radius:15px;
					background:red;
		}
		.cerchioO {
					width:15px;
					height:15px;
					border-radius:15px;
					background:orange;
		}
		.cerchioG {
					width:15px;
					height:15px;
					border-radius:15px;
					background:green;
		}
		.orange {
					width:50px;
					height:50px;
					border-radius:25px;
					background:orange;
		}
		.red {
					width:50px;
					height:50px;
					border-radius:25px;
					background:red;
		}
		.green {
					width:50px;
					height:50px;
					border-radius:25px;
					background:green;
		}
		table th {
					background-color:#00BFFF;
					color:#0000CC;
		}
		
	
	
</style>

<script type="text/javascript">
			$(function() {
    		$( "#dataAppello" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today', maxDate: 'today+1Y' });
  			});
		</script>
</head>
<body>
			<% String action = request.getParameter("action");
                System.out.println(action);
            %>

<c:if test= "${action == 'checkCompleted'}">     
   Appello nello stesso giorno: <div class="red"></div>
   Appello ad un giorno di distanza: <div class="orange"></div>
   Appello a distanza da 2 a 4 giorni: <div class="green"></div>
   <br/>
   <br/>
</c:if>
     
	<table border=1>
        <thead>
            <tr>
            	<th>facoltà</th>
            	<th>corso di studio</th>
            	<th>attività didattica</th>
				<th>tipo</th>
				<th>docente</th>
				<th>data appello</th>
				<c:if test= "${action == 'checkCompleted'}">
					<th>conflitto</th>
				</c:if>
            </tr>
        </thead>
        <tbody>          
		  <c:forEach items="${beans}" var="bean">
		    <tr>
				<td><c:out value="${bean.facDes}" /></td>
                <td><c:out value="${bean.completaDesCds}" /></td>
				<td><c:out value="${bean.adGenDes}" /></td>
				<td><c:out value="${bean.tipoIscrDes}" /></td>
				<td><c:out value="${bean.nomeCompleto}" /></td>
				<td><c:out value="${bean.dataAppello}" /></td>
				<c:if test= "${action == 'checkCompleted'}">
				<td class = <c:choose>
				    					<c:when test="${bean.tr == 1}">"cerchioR"</c:when>
				    					<c:when test="${bean.tr == 2}">"cerchioO"</c:when>
				    					<c:when test="${bean.tr == 3}">"cerchioG"</c:when>
				    					<c:otherwise>"regular"</c:otherwise>   
							</c:choose>>
				</td>
				
				</c:if>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<p><a href="<%=request.getContextPath()%>/CalendarioAppController">Home Page</a></p>
</body>
</html>