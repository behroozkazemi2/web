let homePage = function () {

    let specialId;
    let initTopSellsProduct = function () {
        let data = {
            start: 0,
            length: 20,
            draw: 20,
            rg: [0]
        }
        $.ajax({
            "data": data,
            "url": "/api/product/popularProduct",
            "method": "post",
            "beforeSend": function () {

                $('#best-sell-product-loading').removeClass('d-none');
            },
            "complete": function () {
                $('#best-sell-product-loading').addClass('d-none');
                $('.top-product-carousel').owlCarousel({
                    autoplay: false,
                    rtl: true,
                    loop: false,
                    autoWidth: true,
                    nav: true,
                    autoplayTimeout: 8000,
                    items: 10,
                    dots: false,
                    margin: 20,
                    navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
                    responsiveClass: true,
                    responsive: {
                        0: {
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
            },
            "success": function (res) {
                if (res.result) {
                    let result = JSON.parse(res.payload);
                    $('#top_product').empty();
                    result.data.forEach(item => {

                        let imageSrc = "";
                        if (item.images.length == 0) {
                            imageSrc = '/assets/behta_logo/BehtaTahvie.png';
                        } else {
                            imageSrc = '/thumbnail/files/0/' + item.images[0];
                        }
                        let productName = item.name;
                        productName = removeHalfSpace(productName);

                        $('#top_product').append('' +
                            '                            <div style="opacity: ' + (item.existence ? "1" : "0.5") + '" class="product-box">\n' +
                            '                                <div class="product-item">\n' +
                            '                                    <figure><a  href="/product/' + item.id + '/' + productName + '"><img class="rounded" src="' + imageSrc + '" alt="' + item.name + '"></a></figure>\n' +
                            '                                    <div class="product-content">\n' +
                            '                                        <span><a href="/product/' + item.id + '/' + productName + '">' + item.name + '</a></span>\n' +
                            '                                        <div class="product-score">\n' +
                            '                                            <span class="star"><i class="fa fa-star"></i></span>\n' +
                            '                                            <span class="vote">' + item.rate + '</span>\n' +
                            // '                                            <span class="count">(22)</span>\n' +
                            '                                        </div>\n' +
                            '                                        <div class="product-footer">\n' +
                            '                                            <div class="product-cart">\n' +
                            (!item.existence ? ' ' :
                                    '                                                <a  data-id="' + item.id + '" href="javascript:;"  style="cursor: pointer" class="add-to-cart increaseProductProviderCount" >\n' +
                                    '                                                    <i class="fa fa-cart-plus"></i>\n' +
                                    '\n' +
                                    '                                                </a>\n'
                            ) +
                            '                                            </div>\n' +
                            '                                            <div class="product-cost">\n' +
                            (item.offPercent == 0 ? "" :
                                    '                                                <div class="product-del">\n' +
                                    '                                                    <del>' + number_format(item.primitiveAmount) + ' تومان</del>\n' +
                                    '                                                    <span class="percent">' + item.offPercent + '%</span>\n' +
                                    '                                                </div>\n'
                            ) +
                            '                                                <div class="product-price">\n' +
                            '                                                    <span class="price">' + number_format(item.finalAmount) + ' تومان</span>\n' +
                            '                                                </div>\n' +
                            '                                            </div>\n' +
                            '                                        </div>\n' +
                            '                                    </div>\n' +
                            '                                </div>\n' +
                            '                            </div>\n')
                    })

                } else {
                    $('.toast').removeClass('toast-success');
                    $('.toast').addClass('toast-failed');
                    $('.toast').removeClass('hidden');
                    $('.toast-body').text(res.payload);
                    $('.toast').toast('show');

                }
            },
            error: function () {
                //todo error
                $('.toast').removeClass('toast-success');
                $('.toast').addClass('toast-failed');
                $('.toast').removeClass('hidden');
                $('.toast-body').text("خطا در برقراری ارتباط");
                $('.toast').toast('show');
            }
        })

    };
    let initSpecialProduct = function () {
        $.ajax({
            "url": "/api/product/promoteProduct/special",
            "method": "post",
            "beforeSend": function () {

            },
            "complete": function () {
                $('.special_product_carousel').owlCarousel({
                    autoplay: false,
                    rtl: true,
                    loop: false,
                    autoWidth: true,
                    nav: true,
                    autoplayTimeout: 8000,
                    items: 10,
                    dots: false,
                    margin: 20,
                    navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
                    responsiveClass: true,
                    responsive: {
                        0: {
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
                initPromoteProductEvent();

            },

            "success": function (res) {
                if (res.result) {
                    let result = JSON.parse(res.payload);
                    $('#special_product').empty();
                    let outPut = '';

                    if (result.products.length == 0 || new Date(result.startDate) >= new Date() ){
                        $('#special-offer').remove();
                    }else {
                        if (new Date() > new Date(result.endDate)) {
                            $('#special-offer').remove();
                        }
                        let promoteDuration = new Date(result.endDate).getTime() - new Date(result.startDate).getTime();
                        let promoteCountedTime = new Date(result.startDate);
                        let nowTime = new Date();
                        var countedTime = nowTime.getTime() - promoteCountedTime.getTime();

                        let promoteSecondRemainTime = ((promoteDuration - countedTime) / 1000);
                        specialId = result.promoteId;
                        initCagouleTimmerSpecialEvent('count-down-promote-special', promoteSecondRemainTime)

                        result.products.forEach(item => {
                            let imageSrc = "";
                            if (item.images.length == 0) {
                                imageSrc = '/assets/behta_logo/BehtaTahvie.png';
                            } else {
                                imageSrc = '/thumbnail/files/0/' + item.images[0];
                            }

                            let productName = item.name;
                            productName = removeHalfSpace(productName);
                            outPut +=
                                '                            <div style="opacity: ' + (item.existence ? "1" : "0.5") + '" class="product-box">\n' +
                                '                                <div class="product-item">\n' +
                                '                                    <figure><a href="/product/' + item.id + '/' + productName + '"><img class="rounded" src="' + imageSrc + '" alt="' + item.name + '"></a></figure>\n' +
                                '                                    <div class="product-content">\n' +
                                '                                        <span><a href="/product/' + item.id + '/' + productName + '">' + item.name + '</a></span>\n' +
                                '                                        <div class="product-score">\n' +
                                '                                            <span class="star"><i class="fa fa-star"></i></span>\n' +
                                '                                            <span class="vote">' + item.rate + '</span>\n' +
                                // '                                            <span class="count">(22)</span>\n' +
                                '                                        </div>\n' +
                                '                                        <div class="product-footer">\n' +
                                '                                            <div class="product-cart">\n' +
                                (!item.existence ? ' ' :
                                        '                                                <a  data-id="' + item.id + '" href="javascript:;"  style="cursor: pointer" class="add-to-cart increaseProductProviderCount" >\n' +
                                        '                                                    <i class="fa fa-cart-plus"></i>\n' +
                                        '\n' +
                                        '                                                </a>\n'
                                ) +
                                '                                            </div>\n' +
                                '                                            <div class="product-cost">\n' +
                                (item.offPercent == 0 ? "" :

                                        '                                                <div class="product-del">\n' +
                                        '                                                    <del>' + number_format(item.primitiveAmount) + ' تومان</del>\n' +
                                        '                                                    <span class="percent">' + item.offPercent + '%</span>\n' +
                                        '                                                </div>\n'
                                ) +
                                '                                                <div class="product-price">\n' +
                                '                                                    <span class="price">' + number_format(item.finalAmount) + ' تومان</span>\n' +
                                '                                                </div>\n' +
                                '                                            </div>\n' +
                                '                                        </div>\n' +
                                '                                    </div>\n' +
                                '                                </div>\n' +
                                '                            </div>\n';
                        });

                        $('#special_product').append(outPut);

                    }


                } else {
                    $('#special-offer').remove();
                    $('.toast').removeClass('toast-success');
                    $('.toast').addClass('toast-failed');
                    $('.toast').removeClass('hidden');
                    $('.toast-body').text(res.payload);
                    $('.toast').toast('show');

                }

            },
            error: function () {
                $('#special-offer').remove();
                //todo error
                $('.toast').removeClass('toast-success');
                $('.toast').addClass('toast-failed');
                $('.toast').removeClass('hidden');
                $('.toast-body').text("خطا در برقراری ارتباط");
                $('.toast').toast('show');


            }
        })
    };

    function startTimer(duration, display) {
        var timer = duration, minutes, seconds;
        setInterval(function () {
            minutes = parseInt(timer / 60, 10);
            seconds = parseInt(timer % 60, 10);

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            display.textContent = minutes + ":" + seconds;

            if (--timer < 0) {
                timer = duration;
            }
        }, 1000);
    };

    let initPromoteProductEvent = function () {
        $.ajax({
            "url": "/api/product/promoteProduct/last",
            "method": "post",
            "beforeSend": function () {

            },
            "complete": function () {
                $('#cheaper_product').owlCarousel({
                    autoplay: false,
                    rtl: true,
                    loop: false,
                    autoWidth: true,
                    nav: true,
                    autoplayTimeout: 8000,
                    items: 10,
                    dots: false,
                    margin: 20,
                    navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
                    responsiveClass: true,
                    responsive: {
                        0: {
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

            },

            "success": function (res) {

                if (res.result) {
                    let result = JSON.parse(res.payload);
                    $('#cheaper_product').empty();
                    let outPut = '';
                    if (result.products.length == 0 || specialId == result.promoteId || new Date(result.startDate) >= new Date() ){
                        $('#cheaper-campaign').remove();

                    }else {
                        result.products.forEach(item => {
                            let imageSrc = "";
                            if (item.images.length == 0) {
                                imageSrc = '/assets/behta_logo/BehtaTahvie.png';
                            } else {
                                imageSrc = '/thumbnail/files/0/' + item.images[0];
                            }
                            let productName = item.name;
                            productName = removeHalfSpace(productName);
                            outPut +=
                                '                            <div style="opacity: ' + (item.existence ? "1" : "0.5") + '" class="product-box">\n' +
                                '                                <div class="product-item">\n' +
                                '                                    <figure><a href="/product/' + item.id + '/' + productName + '"><img class="rounded" src="' + imageSrc + '" alt="' + item.name + '"></a></figure>\n' +
                                '                                    <div class="product-content">\n' +
                                '                                        <span><a href="/product/' + item.id + '/' + productName + '">' + item.name + '</a></span>\n' +
                                '                                        <div class="product-score">\n' +
                                '                                            <span class="star"><i class="fa fa-star"></i></span>\n' +
                                '                                            <span class="vote">' + item.rate + '</span>\n' +
                                // '                                            <span class="count">(22)</span>\n' +
                                '                                        </div>\n' +
                                '                                        <div class="product-footer">\n' +
                                '                                            <div class="product-cart">\n' +
                                (!item.existence ? ' ' :
                                        '                                                <a  data-id="' + item.id + '" href="javascript:;"  style="cursor: pointer" class="add-to-cart increaseProductProviderCount" >\n' +
                                        '                                                    <i class="fa fa-cart-plus"></i>\n' +
                                        '\n' +
                                        '                                                </a>\n'
                                ) +
                                '                                            </div>\n' +
                                '                                            <div class="product-cost">\n' +
                                (item.offPercent == 0 ? "" :

                                        '                                                <div class="product-del">\n' +
                                        '                                                    <del>' + number_format(item.primitiveAmount) + ' تومان</del>\n' +
                                        '                                                    <span class="percent">' + item.offPercent + '%</span>\n' +
                                        '                                                </div>\n'
                                ) +
                                '                                                <div class="product-price">\n' +
                                '                                                    <span class="price">' + number_format(item.finalAmount) + ' تومان</span>\n' +
                                '                                                </div>\n' +
                                '                                            </div>\n' +
                                '                                        </div>\n' +
                                '                                    </div>\n' +
                                '                                </div>\n' +
                                '                            </div>\n';
                        });

                        if (new Date() > new Date(result.endDate)) {
                            $('#special-offer').remove();
                        }
                        let promoteDuration = new Date(result.endDate).getTime() - new Date(result.startDate).getTime();
                        let promoteCountedTime = new Date(result.startDate);
                        let nowTime = new Date();
                        var countedTime = nowTime.getTime() - promoteCountedTime.getTime();

                        let promoteSecondRemainTime = ((promoteDuration - countedTime) / 1000);

                        initCagouleTimmerPromoteEvent('count-down-promote-event', promoteSecondRemainTime)

                        $('#cheaper_product').append(outPut);

                    }
                } else {

                    $('.toast').removeClass('toast-success');
                    $('.toast').addClass('toast-failed');
                    $('.toast').removeClass('hidden');
                    $('.toast-body').text(res.payload);
                    $('.toast').toast('show');
                    $('#cheaper-campaign').remove();

                }

            },
            error: function () {
                //todo error
                $('#cheaper-campaign').empty();
                $('.toast').removeClass('toast-success');
                $('.toast').addClass('toast-failed');
                $('.toast').removeClass('hidden');
                $('.toast-body').text("خطا در برقراری ارتباط");
                $('.toast').toast('show');


            }
        })
    };
    let initNewProduct = function () {


        let data = {
            pvdId: 0,
            maxP: 2000000000,
            minP: 0,
            rg: 0,
            pdtCat: [],
            order: 5,
            tags: [],
            srch: "",
            draw: 12,
            start: 0,
            length: 12,
            existence: false,
            brands: []
        };
        $.ajax({
            "data": data,
            "url": "/api/product/search",
            "method": "post",
            "beforeSend": function () {

            },
            "complete": function () {
                $('.new-product-carousel').owlCarousel({
                    autoplay: false,
                    rtl: true,
                    loop: false,
                    autoWidth: true,
                    nav: true,
                    autoplayTimeout: 8000,
                    items: 10,
                    dots: false,
                    margin: 20,
                    navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
                    responsiveClass: true,
                    responsive: {
                        0: {
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

                if ($('.checkIsAnyProductInArea').val() != undefined && $('.checkIsAnyProductInArea').val() != null && !$('.checkIsAnyProductInArea').val()) {
                    removeAllAddToCartBtnInPages();
                }

            },

            "success": function (res) {
                if (res.result) {
                    let result = JSON.parse(res.payload);
                    $('#new_product').empty();

                    let outPut = '';
                    result.data.forEach(item => {

                        let imageSrc = "";
                        if (item.images.length == 0) {
                            imageSrc = '/assets/behta_logo/BehtaTahvie.png';
                        } else {
                            imageSrc = '/thumbnail/files/0/' + item.images[0];
                        }
                        let productName = item.name;
                        productName = removeHalfSpace(productName);

                        outPut +=
                            '                            <div  style="opacity: ' + (item.existence ? "1" : "0.5") + '"  class="product-box">\n' +
                            '                                <div class="product-item">\n' +
                            '                                    <figure><a href="/product/' + item.id + '/' + productName + '" ><img class="rounded" src="' + imageSrc + '" alt="' + item.name + '"></a></figure>\n' +
                            '                                    <div class="product-content">\n' +
                            '                                        <span><a href="/product/' + item.id + '/' + productName + '" >' + item.name + '</a></span>\n' +
                            '                                        <div class="product-score">\n' +
                            '                                            <span class="star"><i class="fa fa-star"></i></span>\n' +
                            '                                            <span class="vote">' + item.rate + '</span>\n' +
                            // '                                            <span class="count">(22)</span>\n' +
                            '                                        </div>\n' +
                            '                                        <div class="product-footer">\n' +
                            '                                            <div class="product-cart">\n' +
                            (!item.existence ? ' ' :
                                    '                                                <a  data-id="' + item.id + '" href="javascript:;"  style="cursor: pointer" class="add-to-cart increaseProductProviderCount">\n' +
                                    '                                                    <i class="fa fa-cart-plus"></i>\n' +
                                    '\n' +
                                    '                                                </a>\n'
                            ) +
                            '                                            </div>\n' +
                            '                                            <div class="product-cost">\n' +
                            (item.offPercent == 0 ? "" :

                                    '                                                <div class="product-del">\n' +
                                    '                                                    <del>' + number_format(item.primitiveAmount) + ' تومان</del>\n' +
                                    '                                                    <span class="percent">' + item.offPercent + '%</span>\n' +
                                    '                                                </div>\n'
                            ) +
                            '                                                <div class="product-price">\n' +
                            '                                                    <span class="price">' + number_format(item.finalAmount) + ' تومان</span>\n' +
                            '                                                </div>\n' +
                            '                                            </div>\n' +
                            '                                        </div>\n' +
                            '                                    </div>\n' +
                            '                                </div>\n' +
                            '                            </div>\n';
                    })
                    $('#new_product').append(outPut);

                } else {
                    $('.toast').removeClass('toast-success');
                    $('.toast').addClass('toast-failed');
                    $('.toast').removeClass('hidden');
                    $('.toast-body').text(res.payload);
                    $('.toast').toast('show');

                }

            },
            error: function () {
                //todo error
                $('.toast').removeClass('toast-success');
                $('.toast').addClass('toast-failed');
                $('.toast').removeClass('hidden');
                $('.toast-body').text("خطا در برقراری ارتباط");
                $('.toast').toast('show');


            }
        })
    };
    let initLastSeenProduct = function () {
        for (let i = 0; i < 10; i++) {
            let productName = item.name;
            productName = removeHalfSpace(productName);
            $('#last-seen').append('' +
                '                            <div  style="opacity: ' + (item.existence ? "1" : "0.5") + '"  class="product-box">\n' +
                '                                <div class="product-item">\n' +
                '                                    <figure><a href="/product/' + item.id + '/' + productName + '"><img src="/assets/behta_logo/BehtaTahvie.png" alt="' + item.name + '"></a></figure>\n' +
                '                                    <div class="product-content">\n' +
                '                                        <span><a  href="/product/href="/product/' + item.id + '/' + productName + '"> item.name</a></span>\n' +
                '                                        <div class="product-score">\n' +
                '                                            <span class="star"><i class="fa fa-star"></i></span>\n' +
                '                                            <span class="vote">4.8</span>\n' +
                '                                            <span class="count">(22)</span>\n' +
                '                                        </div>\n' +
                '                                        <div class="product-footer">\n' +
                '                                            <div class="product-cart">\n' +
                (!item.existence ? ' ' :
                        '                                                <a  data-id="' + item.id + '" href="javascript:;"  style="cursor: pointer" class="add-to-cart increaseProductProviderCount" >\n' +
                        '                                                    <i class="fa fa-cart-plus"></i>\n' +
                        '\n' +
                        '                                                </a>\n'
                ) +
                '                                            </div>\n' +
                '                                            <div class="product-cost">\n' +
                (item.offPercent == 0 ? "" :
                        '                                                <div class="product-del">\n' +
                        '                                                    <del>76,000 تومان</del>\n' +
                        '                                                    <span class="percent">22%</span>\n' +
                        '                                                </div>\n'
                ) +
                '                                                <div class="product-price">\n' +
                '                                                    <span class="price">54,000 تومان</span>\n' +
                '                                                </div>\n' +
                '                                            </div>\n' +
                '                                        </div>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </div>\n')
        }
        $('.last-seen-product-carousel').owlCarousel({
            autoplay: false,
            rtl: true,
            loop: false,
            autoWidth: true,
            nav: true,
            autoplayTimeout: 8000,
            items: 10,
            dots: false,
            margin: 20,
            navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
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
    let number_format = function (total) {
        return total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };


    var myTimerSpecialEvent;
    var myTimerPromoteEvent;
    let initCagouleTimmerSpecialEvent = function (display,  hours) {
        myTimerSpecialEvent = setInterval(myClock, 1000);
       let c = hours;
        function myClock() {
            --c
            var seconds = c % 60; // Seconds that cannot be written in minutes
            var secondsInMinutes = (c - seconds) / 60; // Gives the seconds that COULD be given in minutes
            var minutes = secondsInMinutes % 60; // Minutes that cannot be written in hours
            var hours = (secondsInMinutes - minutes) / 60;
            minutes = parseInt(minutes) <= 9 ?  "0" + minutes : minutes;
            hours = parseInt(hours) <= 9 ?  "0" + hours : hours;
            seconds = parseInt(seconds) <= 9 ?  "0" + Math.round(seconds) : Math.round(seconds);
            $('.'+display+'').text( seconds + ' : ' + minutes + ' : ' + hours);

            if (c < 0) {
                $($('.' + display + '').parents()[3]).remove();
                clearInterval(myTimerSpecialEvent);
            }
        }
    }
    let initCagouleTimmerPromoteEvent = function (display,  hours) {
        myTimerPromoteEvent = setInterval(myClock, 1000);
       let c = hours;
        function myClock() {
            --c
            var seconds = c % 60; // Seconds that cannot be written in minutes
            var secondsInMinutes = (c - seconds) / 60; // Gives the seconds that COULD be given in minutes
            var minutes = secondsInMinutes % 60; // Minutes that cannot be written in hours
            var hours = (secondsInMinutes - minutes) / 60;
            minutes = parseInt(minutes) <= 9 ?  "0" + minutes : minutes;
            hours = parseInt(hours) <= 9 ?  "0" + hours : hours;
            seconds = parseInt(seconds) <= 9 ?  "0" + Math.round(seconds) : Math.round(seconds);
            $('.'+display+'').text( seconds + ' : ' + minutes + ' : ' + hours);

            if (c < 0) {
                $($('.' + display + '').parents()[3]).remove();
                clearInterval(myTimerPromoteEvent);
            }
        }
    }


    let initBanners = function () {
        $.ajax({
            url:'/api/product/banner',
            beforeSend: function(){
            },
            success: function (res) {
                if (res.result) {
                    let result = JSON.parse(res.payload);


                    console.log(result);
                    // ALL Banner Type
                    result.forEach( item => {
                        switch (item.type) {
                            // MainPage Top Slider
                            case 1 :{
                                $('#home-slider1').empty();
                                item.images.forEach( index => {
                                    $('#home-slider1').append(
                                        ' <div class="home-slider">' +
                                        '                <figure><a href="'+ (index.name === '' || index.name == null ? "#" : index.name ) + '">\n' +
                                        '                                <img src="/thumbnail/files/0/'+index.id+'" alt=" بهتاتهویه">\n' +
                                        '                            </a></figure>' +
                                        '</div>'
                                    );
                                })
                                break;
                            }



                            // MainPage Near Top Slider Pics
                            case 2 :{
                                $('.near-top-slider-pics').empty();
                                item.images.forEach( index => {
                                    $('.near-top-slider-pics').append(
                                   '  <figure><a  href="'+ (index.name === '' || index.name == null ? "#" : index.name ) + '"><img src="/thumbnail/files/0/'+index.id+'" alt=" بهتاتهویه"></a></figure>'
                                    );
                                })
                                break;
                            }




                            // MainPage Down Pics
                            case 3 :{
                                $('.main-page-down-pic').empty();
                                $('.main-page-down-pic-mobile').empty();

                                item.images.forEach( index => {
                                    $('.main-page-down-pic').append(
                                        '            <div class="banner-item col-3">\n' +
                                        '                <figure>\n' +
                                        '                    <a  href="'+ (index.name === '' || index.name == null ? "#" : index.name ) + '"><img src="/thumbnail/files/0/'+index.id+'"  alt=" بهتاتهویه"></a>\n' +
                                        '                </figure>\n' +
                                        '            </div>\n'
                                    );


                                    $('.main-page-down-pic-mobile').append(
                                        '            <div class="banner-item col-6 ">\n' +
                                        '                <figure>\n' +
                                        '                    <a  href="'+ (index.name === '' || index.name == null ? "#" : index.name ) + '" ><img src="/thumbnail/files/0/'+index.id+'" alt=" بهتاتهویه"></a>\n' +
                                        '                </figure>\n' +
                                        '            </div>'
                                    );
                                }) ;
                                break;
                            }

                        }



                    })
                }
            },
            complete: function () {
                $('#home-slider1').owlCarousel({
                    autoplay: true,
                    animateIn: 'fadeIn',
                    animateOut: 'fadeOut',
                    rtl: true,
                    loop: true,
                    nav: false,
                    autoplayTimeout: 6000,
                    items: 1,
                    dots: true,
                    margin: 20,
                    lazyLoad: true,
                    navText: ['<i class="fa fa-arrow-right" style="color: #265CC7"></i>', '<i class="fa fa-arrow-left" style="color: #265CC7"></i>'],
                    responsiveClass: true,
                    responsive: {
                        0: {
                            items: 1,
                        },
                        768: {
                            items: 1
                        },
                        992: {
                            items: 1
                        },
                    }
                })
            }
        })
    }
    return {
        // public functions
        init: function () {
            initSpecialProduct();
            initBanners();
            initTopSellsProduct();
            initNewProduct();

            // initPromoteProductEvent();
            // initLastSeenProduct();
        }
    };


}();


jQuery(document).ready(function () {
    homePage.init();
});