function getData(){
	
	$.ajax({
		
		type : "POST",
		url : getContextPath() + "/user/get_data",
		data : {user : "teamj"},
		dataType : "json",
		async : false,
		success : function(result){
			
			let listStr = ""
			
			result.forEach(function(element){
				listStr += "<li id='" + element['Key'] + "'>"
	            listStr +=      "<a href='#'>"
	            listStr +=          "<div class='dataL_item'>"
	            listStr +=              "<div class='data_info'>"
	            listStr +=                  "<span>VCF ID</span>"
	            listStr +=                  "<strong>" + element['Record']['uid'] + "</strong>"
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
	            listStr +=      "<button type='button' class='signBtn'><span class='sr-only'>Reposrt</span></button>"
	            listStr +=      "<button type='button' class='linkBtn' onclick='report(\"" + element['Record']['report_url'] + "\")'><span class='sr-only'>Remove</span></button>"
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

$(function(){
	getData()
});
