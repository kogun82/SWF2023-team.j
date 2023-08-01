function request(){
	
	const genoId = document.getElementById("genoId").value
	
	$.ajax({
		
		url : getContextPath() + "/main/request",
		data : {genoId : genoId},
		type : "POST",
		success : function(result){
			console.log(result)
			if(result){
				alert("Request Complete")
			}else{
				console.log("Regist Failed")
			}
		},
		error : function(){
			console.log("error")
		}
	})
}

$(function(){
});
