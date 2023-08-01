function getData(){
	
	$.ajax({
		
		type : "POST",
		url : getContextPath() + "/user/get_data",
		data : {user : "teamj"},
		dataType : "json",
		async : false,
		success : function(result){
			
			console.log(Object.keys(result))
			console.log(result)
			console.log(result["uniquList"])
			
			let listStr = ""
			
			JSON.parse(result["res"]).forEach(function(element){
				listStr += "<li id='" + element['Key'] + "'>"
	            listStr +=      "<a href='#'>"
	            listStr +=          "<div class='dataL_item'>"
	            listStr +=              "<div class='data_info'>"
	            listStr +=                  "<span>VCF ID</span>"
	            listStr +=                  "<strong>" + element['Key'] + "</strong>"
	            listStr +=                  "<ul class='myData_info'>"
	            listStr +=                      "<li>patient name : " + element['Record']['name'] + "</li>"
	            listStr +=                      "<li>chromosome : " + element['Record']['chr'] + "</li>"
	            listStr +=                      "<li>vcf : " + element['Record']['vcf'] + "</li>"
	            listStr +=                      "<li>genes : " + element['Record']['gene_ids'] + "</li>"
	            listStr +=                      "<li>regist date : " + element['Record']['regist_date'] + "</li>"
	            listStr +=                  "</ul>"
	            listStr +=              "</div>"
	            listStr +=          "</div>"
	            listStr +=      "</a>"
	            if(result["uniquList"].includes(element['Key'])){
					
	            listStr +=      "<button type='button' class='signBtn2' onclick='certification(\"" + element['Key'] + "\")'><span class='sr-only'>Report</span></button>"
				}else{
	            listStr +=      "<button type='button' class='signBtn'><span class='sr-only'>Report</span></button>"
					
				}
	            if(element['Record']['report_url'] == "none"){
					
	            listStr +=      "<button type='button' class='linkBtn' ><span class='sr-only'>Remove</span></button>"
				}else{
					
	            listStr +=      "<button type='button' class='linkBtn2' onclick='report(\"" + element['Record']['report_url'] + "\")'><span class='sr-only'>Remove</span></button>"
				}
	            listStr +=  "</li>"
					
			})
			
			document.querySelector(".data_list").innerHTML = listStr
		}
	})
}

function report(report_url){
	if(report_url && report_url != "none"){
		
		window.open(report_url, '_blank', 'noopener, noreferrer');
	}
}

function certification(geno_key){
	
	$.ajax({
		
		url : getContextPath() + "/user/certification",
		data : {genoKey : geno_key},
		type : "POST",
		success : function(result){
			console.log(result)
			if(result){
				alert("Certification Complete")
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
	getData()
});
