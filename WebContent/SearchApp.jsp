<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="it.mf.i18n.Message" var="lang" />

<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="Filter.Para.Title" bundle="${lang}"/></title>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
		<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

		<script type="text/javascript">
			$(function() {
    			$( "#dataAppello" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today', maxDate: 'today+1Y' });
    			$( "#aDataAppello" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today', maxDate: 'today+1Y' });
  
    		/*Elimina i 'required' quando viene premuto il pulsante "Controllo di sovrapposizione"*/
    		$("#submit").click(function() {
    		    //$("#cdsId").removeProp('required');
    		    $("#doceId").removeProp('required');
    		    $("#dataAppello").removeProp('required');
    		});
			});
    		
		</script>
		
		<script type="text/javascript">
		$(document).ready(function(){
			$(".descrizione").hide();
			
    		$("#hide").click(function(){
        	$(".descrizione").hide();
    	});
    		$("#show").click(function(){
       		$(".descrizione").show();
    	});
		});
		</script>

		
</head>
<body>

		<form method="POST" action='CalendarioAppController' name="frmSearchApp">
			
			<div><label for="facoId"><fmt:message key="app.label.facoltaId" bundle="${lang}" />:</label> 
			<br/>
			<select name="facId" id="facId">
					<option value=""></option>
	    		<c:forEach var="item" items="${facList}">
	        		<option value="${item.facId}">${item.facDes}</option>
	    		</c:forEach>
			</select>
			</div>
			
			<div><label for="cdsId"><fmt:message key="app.label.cdsId" bundle="${lang}" />:</label> 
			<select name="cdsId" id="cdsId" >
					<option value=""></option>
	    		<c:forEach var="item" items="${cdsList}">
	        		<option value="${item.cdsId}">${item.completaDesCds}</option>
	    		</c:forEach>
			</select>
			</div>
			
			<div><label for="adId"><fmt:message key="app.label.adId" bundle="${lang}" />:</label> 
			<select name="adId" id="adId">
					<option value="0"></option>
	    		<c:forEach var="item" items="${adList}">
	        		<option value="${item.adId}">${item.completaDesAd}</option>
	    		</c:forEach>
			</select>
			</div>
			
			<div><label for="doceId"><fmt:message key="app.label.doceMatricola" bundle="${lang}" />:</label> 
			<br/>
			<select name="doceId" id="doceId" required >
					<option value=""></option>
	    		<c:forEach var="item" items="${doceList}">
	        		<option value="${item.doceId}">${item.nomeCompleto}</option>
	    		</c:forEach>
			</select>
			</div>
			
			<div><label for="ruoloCod"><fmt:message key="app.label.ruoloCod" bundle="${lang}" />:</label> 
			<br/>
			<select name="ruoloCod" id="ruoloCod" >
					<option value=""></option>
	    		<c:forEach var="item" items="${ruoloList}">
	        		<option value="${item.ruoloCod}">${item.ruoloDes}</option>
	    		</c:forEach>
			</select>
			</div>
			
		<div><label for="dataAppello"><fmt:message key="app.label.dataAppello" bundle="${lang}" />:</label> <input type="text" name="dataAppello" id="dataAppello" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.nome}" />"  required> </div>
		<div><label for="aDataAppello"><fmt:message key="app.label.aDataAppello" bundle="${lang}" />:</label> <input type="text" name="aDataAppello" id="aDataAppello" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.nome}" />"> </div>

		<br/>
			<div class="descrizione"><label for="facIdVs"><fmt:message key="app.label.facoltaIdVs" bundle="${lang}" />:</label> 
				<br/>
				<select name="facIdVs" id="facIdVs">
						<option value=""></option>
	    			<c:forEach var="item" items="${facList}">
	        			<option value="${item.facId}">${item.facDes}</option>
	    			</c:forEach>
				</select>
			</div>
			
			<div class="descrizione"><label for="cdsIdVs"><fmt:message key="app.label.cdsIdVs" bundle="${lang}" />:</label> 
			<select name="cdsIdVs" id="cdsIdVs">
					<option value=""></option>
	    		<c:forEach var="item" items="${cdsList}">
	        		<option value="${item.cdsId}">${item.completaDesCds}</option>
	    		</c:forEach>
			</select>
			</div>
			
			<div class="descrizione"><label for="adIdVs"><fmt:message key="app.label.adIdVs" bundle="${lang}" />:</label> 
			<select name="adIdVs" id="adIdVs">
					<option value="0"></option>
	    		<c:forEach var="item" items="${adList}">
	        		<option value="${item.adId}">${item.completaDesAd}</option>
	    		</c:forEach>
			</select>
			</div>
		<div class="descrizione"><label for="doceIdVs"><fmt:message key="app.label.doceMatricolaVs" bundle="${lang}" />:</label> 
			<br/>
			<select name="doceIdVs" id="doceIdVs"  multiple required>
					<option value=""></option>
	    		<c:forEach var="item" items="${doceList}">
	        		<option value="${item.doceId}">${item.nomeCompleto}</option>
	    		</c:forEach>
			</select>
		</div>
		<div class="descrizione"><label for="ruoloCodVs"><fmt:message key="app.label.ruoloCodVs" bundle="${lang}" />:</label> 
			<br/>
			<select name="ruoloCodVs" id="ruoloCodVs" >
					<option value=""></option>
	    		<c:forEach var="item" items="${ruoloList}">
	        		<option value="${item.ruoloCod}">${item.ruoloDes}</option>
	    		</c:forEach>
			</select>
			</div>
	<!-- 		<button id="hide" value="<fmt:message key="hide" bundle="${lang}" />" ></button>
			<button id="show" value="<fmt:message key="show" bundle="${lang}" />" ></button>-->
			<br/>
			<input type="submit" name="hide" id="hide" value="<fmt:message key="hide" bundle="${lang}" />" />
			<input type="submit" name="show" id="show" value="<fmt:message key="show" bundle="${lang}" />" />
			<br/>

		<br/>
		<input type="submit" name="submit" id="submit" value="<fmt:message key="submit" bundle="${lang}" />" />
		<input type="submit" name="check" id="check" value="<fmt:message key="check" bundle="${lang}" />" />
		<br/>
		
		

<!-- 		<input type="button" onclick="location.href='<%=request.getContextPath() %>/CalendarioAppController?action=check&id=${item.doceMatricola}';" value="<fmt:message key="check" bundle="${lang}"/>" /> -->
		
		</form>

</body>
</html>