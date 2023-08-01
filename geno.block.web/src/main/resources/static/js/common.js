function loadClass(){
    $(window).on("load",function(){
        $("#wrap").addClass("on");
    });
}

//로그인 페이지 텍스트 효과
function textEffect(){
    $('#introTxt p .row.r1').textillate({
        initialDelay:1000,
        minDisplayTime:1000,
        in: {
            delay: 15,
            effect: "fadeInRight"
        }
    });
    $('#introTxt p .row.r2').textillate({
        initialDelay:1600,
        minDisplayTime:1000,
        in: {
            delay: 15,
            effect: "fadeInRight"
        }
    });
}

//데이터 분석 결과 페이지 이미지 슬라이드
function resultSlide(){
    $('.img_slider_for').slick({
        slidesToShow:1,
        slidesToScroll:1,
        arrows:false,
        fade:true,
        asNavFor:'.img_slider_nav'
    });
    $('.img_slider_nav').each(function(e){
        $(this).slick({
            slidesToShow:4,
            slidesToScroll:1,
            asNavFor:$(this).parent(".imgSlider").find(".img_slider_for"),
            centerMode:false,
            focusOnSelect:true,
            vertical:true,
            prevArrow:'<button type="button" class="slick-prev"><span class="blind">Previous</span></button>',
            nextArrow:'<button type="button" class="slick-next"><span class="blind">Next</span></button>'
        });
    });
}

// 컨텍스트 페스
function getContextPath(){
	
	const hostIndex = location.href.indexOf(location.host) + location.host.length
	
	return location.href.substring(hostIndex, location.href.indexOf("/", hostIndex + 1))
}

$(function(){
    loadClass();    //로딩 후 클래스 추가
});