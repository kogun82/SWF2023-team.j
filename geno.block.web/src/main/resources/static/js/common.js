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

//모달 레이어 팝업
function modal(){
    var modal = $(".modal_layer");
    var btn = $(".modalBtn");

    btn.on("click",function(){
        var t = $(this);
        if($("#modal_layer").is(":hidden")){
            $("html").css("overflow-y","hidden");
            $("#modal_layer").fadeIn("fast");
        }else{
            $("html").removeAttr("style");
            $("#modal_layer").fadeOut("fast");
        }
    });

    //모달창을 제외한 나머지 영역 클릭시 모달창 감추기
    modal.on("click",function(e){
        if(!$(e.target).parents().hasClass("modal_layerWrap")){
            $("html").removeAttr("style");
            btn.removeClass("on");
            $(".modal_layer").fadeOut("fast");
        }
    });

    //esc key - modal close
    $(document).on("keyup", function(e) {
        if(e.keyCode == 27){
            btn.removeClass("on");
            $(".modal_layer").fadeOut("fast");
        }
    });

    //clear, apply 클릭시 닫힘
    $(document).on("click", ".modalBox .applyBtn",function(){
        var t = $(this);

        $(".modalBtn").removeClass("on");
        t.closest(".modal_layer").fadeOut("fast");
    });

    //모달 닫기 버튼
    $(document).on("click", ".modal_close",function(){
        $(".modalBtn").removeClass("on");
        $(this).parents(".modal_layer").fadeOut("fast");
    });
}

// 스크롤 체크
function scrollChk(){
    $("#container").scroll(function(){
        var scroll = $(this).scrollTop();
        if(scroll > 10){
            $("#userTop").addClass("on");
        }else{
            $("#userTop").removeClass("on");
        }
    })
}

$(function(){
    loadClass();    //로딩 후 클래스 추가
    modal()
    scrollChk();    //스크롤체크
});