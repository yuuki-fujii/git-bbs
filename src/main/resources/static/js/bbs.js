$(function(){
	
	$(".iineButton").on("click", function(){
		
			var hostUrl = "http://localhost:8080/iine-count"
			var articleId =$(this).val();

			$.ajax({
				url: hostUrl,
				type: "POST",
				dataType: "json",
				data:{
					articleId: articleId
				},
				async: true
			}).done(function(data){
				console.log(data.iineCount);
				$("#"+articleId).text(data.iineCount);
			})
		
	});
	
});