(function ($) {
    "use strict";

    $("#slider").slider({
        range: true,
        min: 0,
        step: 10000,
        max: 1000000,
        values: [0, 1000000],
        slide: function (event, ui) {

            $('#first-price').val(ui.values[0])
            $('#last-price').val(ui.values[1])

        }
    });

    new WOW().init();

    /*---background image---*/
    function dataBackgroundImage() {
        $('[data-bgimg]').each(function () {
            var bgImgUrl = $(this).data('bgimg');
            $(this).css({
                'background-image': 'url(' + bgImgUrl + ')', // + meaning concat
            });
        });
    }

    $(window).on('load', function () {
        dataBackgroundImage();
    });

    /*---stickey menu---*/
    $(window).on('scroll', function () {
        var scroll = $(window).scrollTop();
        if (scroll < 100) {
            $(".sticky-header").removeClass("sticky");
        } else {
            $(".sticky-header").addClass("sticky");
        }
    });


    /*---slider activation---*/
    var $slider = $('.slider_area');
    if ($slider.length > 0) {
        $slider.owlCarousel({
            animateOut: 'fadeOut',
            autoplay: true,
            loop: true,
            nav: false,
            autoplayTimeout: 8000,
            items: 1,
            dots: true,
        });
    }

    /*---provider column4 activation---*/

    /*---productpage column4 activation---*/

    /*---provider column3 activation---*/

    /*---product3 column3 activation---*/
    var $product3Column3 = $('.product3_column3');
    if ($product3Column3.length > 0) {
        $product3Column3.on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(event.target).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last')
        }).owlCarousel({
            autoplay: true,
            loop: true,
            nav: true,
            autoplayTimeout: 8000,
            items: 3,
            dots: false,
            margin: 20,
            navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                },
                576: {
                    items: 2,
                },
                768: {
                    items: 2,
                },
                992: {
                    items: 3,
                },
            }
        });
    }


    let $smallpColumn2 = $('.smallp_column2');
    if ($smallpColumn2.length > 0) {
        $('.smallp_column2').on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(event.target).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last')
        }).owlCarousel({
            autoplay: true,
            loop: false,
            nav: true,
            autoplayTimeout: 8000,
            items: 2,
            dots: false,
            margin: 20,
            navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                },
                768: {
                    items: 1,
                },
                992: {
                    items: 2,
                },
            }
        });
    }
    /*---provider column2 activation---*/
    var $productColumn2 = $('.product_column2');
    if ($productColumn2.length > 0) {
        $productColumn2.on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(event.target).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last')
        }).owlCarousel({
            autoplay: true,
            loop: true,
            nav: false,
            autoplayTimeout: 8000,
            items: 2,
            dots: false,
            margin: 20,
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                },
                768: {
                    items: 1,
                },
                992: {
                    items: 2,
                },
            }
        });
    }

    var $productColumn1 = $('.product_column1');
    if ($productColumn1.length > 0) {
        $('.product_column1').on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(event.target).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last')
        }).owlCarousel({
            autoplay: true,
            loop: true,
            nav: true,
            autoplayTimeout: 8000,
            items: 2,
            dots: false,
            margin: 20,
            navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                },
                768: {
                    items: 2,
                },
                992: {
                    items: 1,
                },
            }
        });
    }
    /*---deals3 column1 activation---*/
    var $deals3Column1 = $('.deals3_column1');
    if ($deals3Column1.length > 0) {
        $('.deals3_column1').on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(event.target).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last')
        }).owlCarousel({
            autoplay: true,
            loop: true,
            nav: true,
            autoplayTimeout: 8000,
            items: 1,
            dots: false,
            margin: 20,
            navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                },
                576: {
                    items: 2,
                },
                768: {
                    items: 1,
                },
                992: {
                    items: 1,
                },
            }
        });
    }
    /*---smallp4 left column1 activation---*/
    var $smallp4LeftColumn1 = $('.smallp4_left_column1');
    if ($smallp4LeftColumn1.length > 0) {
        $('.smallp4_left_column1').on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(event.target).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last')
        }).owlCarousel({
            autoplay: true,
            loop: true,
            nav: true,
            autoplayTimeout: 8000,
            items: 1,
            dots: false,
            margin: 20,
            navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                },
                768: {
                    items: 1,
                },
                992: {
                    items: 1,
                },
            }
        });
    }

    let porductColumn5 = $('.product_column5');
    if (porductColumn5.length > 0) {
        porductColumn5.on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(event.target).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last')
        }).owlCarousel({
            loop: false,
            nav: true,
            autoplay: true,
            center: false,
            autoplayTimeout: 4000,
            margin: 20,
            dots: true,
            navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                },
                576: {
                    items: 2,
                },
                768: {
                    items: 3,
                },
                992: {
                    items: 4,
                },
                1200: {
                    items: 5,
                },

            }
        });
    }
    /*---blog column3 activation---*/
    /*---brand container activation---*/
    var $brandContainer = $('.brand_container');
    if ($brandContainer.length > 0) {
        $('.brand_container').on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(event.target).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last')
        }).owlCarousel({
            autoplay: true,
            loop: true,
            nav: false,
            autoplayTimeout: 8000,
            items: 5,
            margin: 20,
            dots: false,
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                },
                300: {
                    items: 2,
                    margin: 15,
                },
                480: {
                    items: 3,
                },
                768: {
                    items: 4,
                },
                992: {
                    items: 5,
                },

            }
        });
    }

    /*---testimonial column1 activation---*/
    var $testimonialColumn1 = $('.testimonial_column1');
    if ($testimonialColumn1.length > 0) {
        $('.testimonial_column1').owlCarousel({
            autoplay: true,
            loop: true,
            nav: false,
            autoplayTimeout: 8000,
            items: 1,
            dots: true,
        });
    }
    /*---testimonial active activation---*/
    var $testimonialTwo = $('.testimonial-two');
    if ($testimonialTwo.length > 0) {
        $('.testimonial-two').owlCarousel({
            autoplay: true,
            loop: true,
            nav: false,
            autoplayTimeout: 8000,
            items: 1,
            dots: true,
        })
    }

    /*---blog thumb activation---*/
    var $blogThumbActive = $('.blog_thumb_active');
    if ($blogThumbActive.length > 0) {
        $('.blog_thumb_active').owlCarousel({
            autoplay: true,
            loop: true,
            nav: true,
            autoplayTimeout: 8000,
            items: 1,
            navText: ['<i class="fa fa-angle-right"></i>', '<i class="fa fa-angle-left"></i>'],
        });
    }


    /*---single provider activation---*/
    var $singleProductActive = $('.single-provider-active');
    if ($singleProductActive.length > 0) {
        $('.single-provider-active').owlCarousel({
            autoplay: true,
            loop: true,
            nav: true,
            autoplayTimeout: 8000,
            items: 4,
            margin: 15,
            dots: false,
            navText: ['<i class="fa fa-angle-right"></i>', '<i class="fa fa-angle-left"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                },
                320: {
                    items: 2,
                },
                400: {
                    items: 3,
                },
                992: {
                    items: 3, lo
                },
                1200: {
                    items: 4,
                },


            }
        });
    }

    /*---provider navactive activation---*/
    /*--- video Popup---*/
    $('.video_popup').magnificPopup({
        type: 'iframe',
        removalDelay: 300,
        mainClass: 'mfp-fade'
    });

    /*--- Magnific Popup Video---*/
    $('.port_popup').magnificPopup({
        type: 'image',
        gallery: {
            enabled: true
        }
    });

    /*--- Tooltip Active---*/
    $('.action_links ul li a,.add_to_cart a,.footer_social_link ul li a').tooltip({
        animated: 'fade',
        placement: 'top',
        container: 'body'
    });


    /*---  Accordion---*/
    $(".faequently-accordion").collapse({
        accordion: true,
        open: function () {
            this.slideDown(300);
        },
        close: function () {
            this.slideUp(300);
        }
    });


    /*---  ScrollUp Active ---*/
    $.scrollUp({
        scrollText: '<i class="fa fa-angle-double-up"></i>',
        easingType: 'linear',
        scrollSpeed: 900,
        animation: 'fade'
    });

    /*---countdown activation---*/

    $('[data-countdown]').each(function () {
        var $this = $(this), finalDate = $(this).data('countdown');
        $this.countdown(finalDate, function (event) {
            $this.html(event.strftime('<div class="countdown_area"><div class="single_countdown"><div class="countdown_number">%D</div><div class="countdown_title">روز</div></div><div class="single_countdown"><div class="countdown_number">%H</div><div class="countdown_title">ساعت</div></div><div class="single_countdown"><div class="countdown_number">%M</div><div class="countdown_title">دقیقه</div></div><div class="single_countdown"><div class="countdown_number">%S</div><div class="countdown_title">ثانیه</div></div></div>'));

        });
    });

    /*---slider-range here---*/
    $("#slider-range").slider({
        range: true,
        min: 0,
        step: 10000,
        max: 1000000,
        values: [0, 1000000],
        slide: function (event, ui) {
            $("#amount").val(formatDollar(ui.values[1]) + ' - ' + formatDollar(ui.values[0]));
        }
    });
    $("#amount").val($("#slider-range").slider("values", 1) + ' - ' +
        $("#slider-range").slider("values", 0));

    /*---elevateZoom---*/

    /*---portfolio Isotope activation---*/
    $('.portfolio_gallery').imagesLoaded(function () {

        var $grid = $('.portfolio_gallery').isotope({
            itemSelector: '.gird_item',
            percentPosition: true,
            masonry: {
                columnWidth: '.gird_item'
            }
        });

        /*---ilter items on button click---*/
        $('.portfolio_button').on('click', 'button', function () {
            var filterValue = $(this).attr('data-filter');
            $grid.isotope({filter: filterValue});

            $(this).siblings('.active').removeClass('active');
            $(this).addClass('active');
        });

    });


    /*---categories slideToggle---*/
    $(".categories_title").on("click", function () {
        $(this).toggleClass('active');
        $('.categories_menu_toggle').slideToggle('medium');
    });
    window.addEventListener("click", function(event) {
        if (!event.target.matches('.categori_toggle') && $('.categories_title').hasClass('active')) {
            $('.categories_title').removeClass('active');
            $('.categories_menu_toggle').slideToggle('medium');
        }
    });
    /*---widget sub categories---*/
    $(".sub_categories1 > a").on("click", function () {
        $(this).toggleClass('active');
        $('.dropdown_categories1').slideToggle('medium');
    });

    /*---shop grid activation---*/
    $('.shop_toolbar_btn > button').on('click', function (e) {

        e.preventDefault();

        $('.shop_toolbar_btn > button').removeClass('active');
        $(this).addClass('active');

        var parentsDiv = $('.shop_wrapper');
        var viewMode = $(this).data('role');


        parentsDiv.removeClass('grid_3 grid_4 grid_5 grid_list').addClass(viewMode);

        if(viewMode == 'grid_3'){
            parentsDiv.children().addClass('col-lg-4 col-md-4 col-sm-6').removeClass('col-lg-3 col-cust-5 col-12');

        }

        if(viewMode == 'grid_4'){
            parentsDiv.children().addClass('col-lg-3 col-md-4 col-sm-6').removeClass('col-lg-4 col-cust-5 col-12');
        }

        if(viewMode == 'grid_list'){
            parentsDiv.children().addClass('col-12').removeClass('col-lg-3 col-lg-4 col-md-4 col-sm-6 col-cust-5');
        }

    });

    /*---widget sub categories---*/
    $(".sub_categories2 > a").on("click", function () {
        $(this).toggleClass('active');
        $('.dropdown_categories2').slideToggle('medium');
    });

    /*---widget sub categories---*/
    $(".sub_categories3 > a").on("click", function () {
        $(this).toggleClass('active');
        $('.dropdown_categories3').slideToggle('medium');
    });

    /*---addClass/removeClass categories---*/
    $("#cat_toggle.has-sub > a").on("click", function () {
        $(this).removeAttr('href');
        $(this).toggleClass('open').next('.categorie_sub').toggleClass('open');
        $(this).parents().siblings().find('#cat_toggle.has-sub > a').removeClass('open');
    });


    /*---MailChimp---*/
    $('#mc-form').ajaxChimp({
        language: 'en',
        callback: mailChimpResponse,
        // ADD YOUR MAILCHIMP URL BELOW HERE!
        url: '#'

    });

    function mailChimpResponse(resp) {

        if (resp.result === 'success') {
            $('.mailchimp-success').addClass('active')
            $('.mailchimp-success').html('' + resp.msg).fadeIn(900);
            $('.mailchimp-error').fadeOut(400);

        } else if (resp.result === 'templates.view.error') {
            $('.mailchimp-error').html('' + resp.msg).fadeIn(900);
        }
    }

    /*---Category menu---*/
    function categorySubMenuToggle() {
        $('.categories_menu_toggle li.menu_item_children > a').on('click', function () {
            if ($(window).width() < 991) {
                $(this).removeAttr('href');
                var element = $(this).parent('li');
                if (element.hasClass('open')) {
                    element.removeClass('open');
                    element.find('li').removeClass('open');
                    element.find('ul').slideUp();
                } else {
                    element.addClass('open');
                    element.children('ul').slideDown();
                    element.siblings('li').children('ul').slideUp();
                    element.siblings('li').removeClass('open');
                    element.siblings('li').find('li').removeClass('open');
                    element.siblings('li').find('ul').slideUp();
                }
            }
        });
        $('.categories_menu_toggle li.menu_item_children > a').append('<span class="expand"></span>');
    }

    categorySubMenuToggle();


    /*---shop grid activation---*/


    /*---Newsletter Popup activation---*/

    setTimeout(function () {
        if ($.cookie('shownewsletter') == 1) $('.newletter-popup').hide();
        $('#subscribe_pemail').keypress(function (e) {
            if (e.which == 13) {
                e.preventDefault();
                email_subscribepopup();
            }
            var name = $(this).val();
            $('#subscribe_pname').val(name);
        });
        $('#subscribe_pemail').change(function () {
            var name = $(this).val();
            $('#subscribe_pname').val(name);
        });
        //transition effect
        if ($.cookie("shownewsletter") != 1) {
            $('.newletter-popup').bPopup();
        }
        $('#newsletter_popup_dont_show_again').on('change', function () {
            if ($.cookie("shownewsletter") != 1) {
                $.cookie("shownewsletter", '1')
            } else {
                $.cookie("shownewsletter", '0')
            }
        });
    }, 2500);


    /*---search box slideToggle---*/
    // $(".search_box > a").on("click", function() {
    //     $(this).toggleClass('active');
    //     $('.search_widget').slideToggle('medium');
    // });


    /*---header account slideToggle---*/
    $(".header_account > a").on("click", function () {
        $(this).toggleClass('active');
        $('.dropdown_account').slideToggle('medium');
    });

    /*---slide toggle activation---*/


    /*---mini cart activation---*/
    $('.mini_cart_wrapper > a').on('click', function () {
        $('.mini_cart/*,.off_canvars_overlay*/').addClass('active')
    });

    $('.mini_cart_close/*.off_canvars_overlay*/').on('click', function () {
        $('.mini_cart,.off_canvars_overlay').removeClass('active')
    });


    /*---canvas menu activation---*/
    $('.canvas_open,.off_canvars_overlay').on('click', function () {
        $('.offcanvas_menu_wrapper,.off_canvars_overlay').addClass('active')
    });

    $('.canvas_close,.off_canvars_overlay').on('click', function () {
        $('.offcanvas_menu_wrapper,.off_canvars_overlay').removeClass('active')
    });


    /*---Off Canvas Menu---*/
    var $offcanvasNav = $('.offcanvas_main_menu'),
        $offcanvasNavSubMenu = $offcanvasNav.find('.sub-menu');
    $offcanvasNavSubMenu.parent().prepend('<span class="menu-expand"><i class="fa fa-angle-down"></i></span>');

    $offcanvasNavSubMenu.slideUp();

    $offcanvasNav.on('click', 'li a, li .menu-expand', function (e) {
        var $this = $(this);
        if (($this.parent().attr('class').match(/\b(menu-item-has-children|has-children|has-sub-menu)\b/)) && ($this.attr('href') === '#' || $this.hasClass('menu-expand'))) {
            e.preventDefault();
            if ($this.siblings('ul:visible').length) {
                $this.siblings('ul').slideUp('slow');
            } else {
                $this.closest('li').siblings('li').find('ul:visible').slideUp('slow');
                $this.siblings('ul').slideDown('slow');
            }
        }
        if ($this.is('a') || $this.is('span') || $this.attr('clas').match(/\b(menu-expand)\b/)) {
            $this.parent().toggleClass('menu-open');
        } else if ($this.is('li') && $this.attr('class').match(/\b('menu-item-has-children')\b/)) {
            $this.toggleClass('menu-open');
        }
    });


})(jQuery);	
