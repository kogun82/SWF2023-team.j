( function( $ ) {

    "use strict";

    function isTouchEnabled() {
    return (('ontouchstart' in window)
        || (navigator.MaxTouchPoints > 0)
        || (navigator.msMaxTouchPoints > 0));
    }

    $(document).ready(function () {
        $("path[id^=\"haio_\"]").each(function (i, e) {
            addEvent($(e).attr('id'));
        });
    });

    function addEvent(id, relationId) {
        var _obj = $('#' + id);
        $('#haio-wrapper').css({'opacity': '1'});

        _obj.attr({'fill': 'rgba(255, 0, 0, 0)', 'stroke': 'rgba(255, 102, 102, 1)'});
        _obj.attr({'cursor': 'default'});

        if (haio_config[id]['active'] === true) {
            if (isTouchEnabled()) {
                var touchmoved;
                _obj.on('touchend', function (e) {
                    if (touchmoved !== true) {
                        _obj.on('touchstart', function (e) {
                            let touch = e.originalEvent.touches[0];
                            let x = touch.pageX - 10, y = touch.pageY + (-15);

                            let $haioatip = $('#tip-haio');
                            let haioanatomytipw = $haioatip.outerWidth(),
                                haioanatomytiph = $haioatip.outerHeight();

                            x = (x + haioanatomytipw > $(document).scrollLeft() + $(window).width()) ? x - haioanatomytipw - (20 * 2) : x
                            y = (y + haioanatomytiph > $(document).scrollTop() + $(window).height()) ? $(document).scrollTop() + $(window).height() - haioanatomytiph - 10 : y

                            if (haio_config[id]['target'] !== 'none') {
                                _obj.css({'fill': 'rgba(255, 0, 0, 0.7)'});
                            }
                            $haioatip.show().html(haio_config[id]['hover']);
                            $haioatip.css({left: x, top: y})
                        })
                        _obj.on('touchend', function () {
                            _obj.css({'fill': 'rgba(255, 0, 0, 0)'});
                            if (haio_config[id]['target'] === '_blank') {
                                window.open(haio_config[id]['url']);
                            } else if (haio_config[id]['target'] === '_self') {
                                window.parent.location.href = haio_config[id]['url'];
                            }
                            $('#tip-haio').hide();
                        })
                    }
                }).on('touchmove', function (e) {
                    touchmoved = true;
                }).on('touchstart', function () {
                    touchmoved = false;
                });
            }
            _obj.attr({'cursor': 'pointer'});

            _obj.on('mouseenter', function () {
                $('#tip-haio').show().html(haio_config[id]['hover']);
                _obj.css({'fill': 'rgba(255, 0, 0, 0.3)'})
            }).on('mouseleave', function () {
                $('#tip-haio').hide();
                _obj.css({'fill': 'rgba(255, 0, 0, 0)'});
            })
            if (haio_config[id]['target'] !== 'none') {
                _obj.on('mousedown', function () {
                    _obj.css({'fill': 'rgba(255, 0, 0, 0.7)'});
                })
            }
            _obj.on('mouseup', function () {
                _obj.css({'fill': 'rgba(255, 0, 0, 0.3)'});
                if (haio_config[id]['target'] === '_blank') {
                    window.open(haio_config[id]['url']);
                } else if (haio_config[id]['target'] === '_self') {
                    window.parent.location.href = haio_config[id]['url'];
                }
            })
            _obj.on('mousemove', function (e) {
                let x = e.pageX + 10, y = e.pageY + 15;

                let $ahaio = $('#tip-haio');
                let haioanatomytipw = $ahaio.outerWidth(), haioanatomytiph = $ahaio.outerHeight();

                x = (x + haioanatomytipw > $(document).scrollLeft() +
                    $(window).width()) ? x - haioanatomytipw - (20 * 2) : x
                y = (y + haioanatomytiph > $(document).scrollTop() + $(window).height()) ?
                    $(document).scrollTop() + $(window).height() - haioanatomytiph - 10 : y

                $ahaio.css({left: x, top: y})
            })
        } else {
            _obj.hide();
        }
    }
})(jQuery);
