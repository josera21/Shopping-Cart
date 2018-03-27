$(document).ready(function() {

	$('#buylink').click(function() {
		var quantity = $('#quantity').val

		$(this).attr("href", 
			'${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}&quantity='+quantity);
	});	
});