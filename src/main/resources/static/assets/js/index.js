$(document).ready(function () {
    index.init();
    //////نمایش سبد خرید به صورت ساید بار
    $("#cartBtn").click(function () {
        ///show
        $(".cart-slide-overlay").addClass("show");
        $(".cart-slide").css("left", '0');
        ///hide
        $("#cartSlideColse").click(function () {
            $(".cart-slide-overlay").removeClass("show");
            $(".cart-slide").css("left", '-100%');
        })
        ///hide with overlay
        $(".cart-slide-overlay").click(function () {
            $(this).removeClass("show");
            $(".cart-slide").css("left", '-100%');
        })
    })

    //////نمایش سبد خرید به صورت ساید بار end

    /// نمایش منو رسپانسیو ////////////
    $(".showSubMenu").click(function () {
        $(this).nextAll("ul").toggleClass("show");
        $(this).toggleClass('open');
    })

    ///show
    $("#showResponsiveMenu").click(function () {
        ///show
        console.log('clicked showResponsiveMenu')
        $(".rm-items").addClass("open");
        $(".rm-overlay").addClass("open");

        ///hide
        $(".rm-overlay").click(function () {
            $(this).removeClass('open');
            $(".rm-items").removeClass('open');
        })
        $(".rm-item-close").click(function () {
            $(".rm-overlay").removeClass('open');
            $(".rm-items").removeClass('open');
        })
    })


    ///انتخاب گر رنگ
    $(".color-box-item").click(function () {
        $(".color-box-item").removeClass("active");
        $(this).addClass('active');
    })


    $(document).on("input", ".number_input", function (event) {
        var inputValue = $(this).val();
        var englishValue = convertPersianToEnglishNumbers(inputValue);
        $(this).val(englishValue);
    });


    function convertPersianToEnglishNumbers(str) {


        var persianNumbers = ['۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'];
        var englishNumbers = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];

        for (var i = 0; i < persianNumbers.length; i++) {
            str = str.replace(new RegExp(persianNumbers[i], 'g'), englishNumbers[i]);
        }

        return str;
    }
});

let index = function () {

    let pagination = function (c, m, target) {
        var current = c,
            last = m,
            delta = 2,
            left = current - delta,
            right = current + delta + 1,
            range = [],
            rangeWithDots = [],
            l;

        for (let i = 1; i <= last; i++) {
            if (i == 1 || i == last || i >= left && i < right) {
                range.push(i);
            }
        }

        for (let i of range) {
            if (l) {
                if (i - l === 2) {

                    let outPut = '' +
                        '<li class="page-item"><a style="cursor: pointer !important;" data-page ="' + l + 1 + '" class="page-link page_link_filter ' + (i == current ? "active active_page_filter" : '') + ' "> ' + l + 1 + ' </a></li>\n';
                    rangeWithDots.push(outPut);
                } else if (i - l !== 1) {
                    let outPut = '' +
                        '<li class="page-item"><a disabled class="page-link page_link_filter ">...</a></li>\n';
                    rangeWithDots.push(outPut);
                }
            }
            let outPut = '' +
                '<li class="page-item"><a style="cursor: pointer !important;" data-page ="' + i + '" class="page-link page_link_filter ' + (i == current ? "active active_page_filter" : '') + ' "> ' + i + ' </a></li>\n';
            rangeWithDots.push(outPut);
            l = i;
        }
        rangeWithDots.unshift('<li class="page-item"><a ' + (c == 1 ? 'disabled' : '') + '  class="page-link  page_back"><i class="fa fa-angle-right"></i></a></li>\n');
        rangeWithDots.push('<li class="page-item"><a ' + (c == l ? 'disabled' : '') + '  class="page-link  page_next"><i class="fa fa-angle-left"></i></a></li>\n');

        $('.' + target + '').append(rangeWithDots);
    };
    let hover = function () {

        $("#hover-zoom").blowup({
            width: 150, // طول لنز
            height: 150, // ارتفاع لنز
            scale : 10,  //مقیاس بزرگنمایی
            background: '#00000000', // رنگ پس زمینه در صورتی که از تصویر بیرون رفت
            border: '1px solid white', // حاشیه لنز
            round: true, // اینکه لنز گرد باشد یا خیر
            shadow: 'none' // ویژگی های سایه
        });
    };
    return {
        // public functions
        init: function () {
            hover();
        },
        pagination: function (c, m, target) {
            pagination(c, m, target);
        }
    };
}();


//
// ///نمایش مگامنو آپدیدت جدید
// $(".main-menu-head").hover(function(){
//     $(this).children().find(".main-menu-sub").first().addClass('main-menu-sub-active');
//     $(this).children().addClass('active');
// })
// $(".main-menu-head").mouseleave(function(){
//     $(this).children().find(".main-menu-sub").first().removeClass('main-menu-sub-active');
//     $(this).children().removeClass('active');
// })
// $(".main-menu > li").mouseover(function () {
//
//     $(".main-menu > li").removeClass("main-menu-sub-active-li");
//     $(this).addClass("main-menu-sub-active-li");
//     $(".main-menu-sub").removeClass('main-menu-sub-active');
//     $(this).children('ul').removeClass('main-menu-sub-active');
//     $(this).children('ul').addClass('main-menu-sub-active');
// });
// $(".main-menu-sub-active").mouseleave(function(){
//     $(".main-menu-sub-active").removeClass("main-menu-sub-active");
// })