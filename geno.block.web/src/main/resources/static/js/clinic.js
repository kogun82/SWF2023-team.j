function register(){
	
	const idInfor = $("#id_infor").serialize()

	$.ajax({
		
		type : "POST",
		url : getContextPath() + "/clinic/register",
		data : idInfor,
		dataType : "json",
		async : false,
		success : function(result){
			
			console.log(result)
			if(result){
				console.log("!1")
			}else{
				console.log("@2")
			}
		}
	})
}

$(function(){
});