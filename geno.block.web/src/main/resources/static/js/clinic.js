function register(){
	
	const idInfor = $("#id_infor").serialize()

	$.ajax({
		
		type : "POST",
		url : getContextPath() + "/clinic/register",
		data : idInfor,
		dataType : "json",
		async : false,
		success : function(result){
			
			if(result){
				alert("Regist Complete")
			}else{
				console.log("Regist Failed")
			}
		}
	})
}

$(function(){
});
