let cartPage = function () {

    let similarBrand = [];
    let similarCategory = []
    let getInCartProduct = function () {
        $.ajax({
            url: '/api/cart/cartTable',
            method: 'post',
            beforeSend: function(){
                $('.loading-box').removeClass('d-none');
                $('.prices').addClass('d-none');

            },
            success: function (res) {
                let outPut = '';
                if(res.result) {

                    let result = JSON.parse(res.payload);
                    if (result.data.length == 0 ) {
                        // $('.shopping').prop('disabled' , true);
                        $('.shopping').attr('href','#');
                        $('.shopping').addClass('btn disabled');
                        $('.shopping').removeClass('shopping');
                        $('.shopping').css('opacity' , '0.5');
                        // $('.shopping').css('cursor' , 'not-allowed');
                        // $('.shopping').attr('href' , '#');
                    };
                    result.data.forEach(item => {
                        // similarBrand.push(item.brand.id);
                        // similarCategory.push(item.category.id);
                        let imageSrc = "";
                        if (item.images.length == 0) {
                            imageSrc = '/assets/behta_logo/BehtaTahvie.png';
                        } else {
                            imageSrc = '/thumbnail/files/0/' + item.images[0];
                        }

                        let productName = item.name;
                        productName = removeHalfSpace(productName);
                        outPut += '' +
                            '    <div class="product-item box-shadow col-12 col-md-6">\n' +
                            '        <figure><a href="/product/' + item.id + '/' + productName + '"><img src="' + imageSrc + '" alt=" بهتاتهویه"></a></figure>\n' +
                            '        <div class="product-content">\n' +
                            '            <span class="product-title">' + item.name + '</span>\n' +
                            '         <div class="add-to-card-number">\n' +
                            '                        ' +
                            '                                <span id="inc-button" class="changeCount-plus changeCount spinner-button" ' +
                            '                                         data-min="'+item.minAllow+'" data-max="' + (item.productCount < item.maxAllow ? item.productCount : item.maxAllow) + '"  data-step="'+item.unitStep+'"' +
                            '                                         data-product-id="'+item.id+'" ><i class="fa fa-plus"></i></span>\n' +
                            '                                <input class="product_count" id="product_count" type="number" name="number" ' +
                            '                                   min="'+item.minAllow+'" ' +
                            '                                   max="'+item.minAllow+'" ' +
                            '                                   value="'+item.inCartCount+'"' +
                            '                                   step="'+item.unitStep+'"' +
                            '                                   data-product-id="'+item.id+'"' +
                            '                                   data-primitiveamount="'+item.primitiveAmount+'" ' +
                            '                                   data-finalprice="'+item.totalAmount+'" ' +
                            '                                   data-discount="'+item.offPercent+'" ' +

                            '                                             >\n' +
                            '                                <span id="dec-button" class="changeCount-minus changeCount spinner-button" ' +
                            '                                      data-min="'+item.minAllow+'" data-max="'+item.maxAllow+'" data-step="'+item.unitStep+'" ' +
                            '                                      data-product-id="'+item.id+'"  ><i class="fa fa-minus"></i></span>\n' +
                            '                            ' +
                            '</div>' +
                            '        </div>\n' +
                            '        <div class="product-left">\n' +
                            '            <button  class="product-remove-item" data-id="' + item.id + '" type="button" id="product-delete"><i class="fa fa-trash"></i></button>\n' +
                            '            <div class="product-cost">\n' +
                            (  item.offPercent == 0 ? "" :
                            '                <div class="product-del">\n' +
                            '                    <del>' + number_format(item.primitiveAmount) + ' تومان</del>\n' +
                            '                    <span class="percent">' + item.offPercent + '%</span>\n' +
                            '                </div>\n'
                            ) +
                            '                <div class="product-price">\n' +
                            '                    <span class="price">' + number_format(item.totalAmount) + ' تومان</span>\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '        </div>\n' +
                            '    </div>\n'
                    });
                    $('.cart-products').empty();
                    $('.cart-products').append(outPut);


                    if (result.data.length == 0){

                        $('.cart-products').append( '                <div class="empty-content box-shadow ">\n' +
                        '                    <div class="cart-empty d-flex flex-column justify-content-center align-items-center text-center">\n' +
                        '                        <img src="assets/images/icons/45.svg" alt=" بهتاتهویه">\n' +
                        '                        <span>هنوز کالایی را به سبد خرید خود اضافه نکرده اید!</span>\n' +
                        '                        <span>\n' +
                        '                            لطفا با کلیک بر روی علامت\n' +
                        '                            <i class="fa fa-cart-plus" style="font-size: 24px; color: var(--main-color);"></i>\n' +
                        '                            و یا گزینه "افزودن به سبد خرید"\n' +
                        '                            در قسمتی که محصول موردنظر شما قرار دارد، سبد خرید خود را تکمیل نمایید.\n' +
                        '                        </span>\n' +
                        '                        <a href="/" target="_parent" id="back-to-home" class="btn btn-info">بازگشت به صفحه اصلی و خرید کالاها</a>\n' +
                        '                    </div>\n' +
                        '                </div>');
                    }
                    changes();
                }
            },
            complete: function () {
                $('.loading-box').addClass('d-none');
                $('.prices').removeClass('d-none');
                removeItem();
                CalculatePriceAndSet();
            },
            error: function () {

            }
        })
    };

    let CalculatePriceAndSet = function () {
        let totalDelPrice = 0;
        let totalPrice = 0;
        let totalCount = 0;
        let totalDiscountPercent = 0;
        $('.product_count').each(function () {
            totalDelPrice += $(this).val() * $(this).data('primitiveamount');
            totalPrice += $(this).data('finalprice');
            totalCount += Number($(this).val());
            totalDiscountPercent += $(this).data('discount') * $(this).val();
        });

        console.log(totalDiscountPercent);
        if (totalCount != 0) {
            totalDiscountPercent = Math.round(totalDiscountPercent / totalCount);
        }
        $('.order-count').empty();
        $('.order-price').empty();
        $('.order-off').empty();
        $('.order-percent').empty();
        $('.order-overall').empty();


        $('.order-count').text('(' + totalCount + ')');
        $('.order-price').text(number_format(totalDelPrice) + ' تومان ');
        $('.order-off').text(number_format(totalDelPrice - totalPrice) + ' تومان ');
        $('.order-percent').text('(' + totalDiscountPercent + '%)');
        $('.order-overall').text(number_format(totalPrice) + ' تومان ');
    };
    let changes = function () {
        // Change InputVal;
        $('.product_count').on('change', function () {
            let data = {
                v: $(this).val, // value
                pId: $(this).data('id') // productProviderId
            }
            changeProductCount(data);
        });
    }

    let initSimilarProduct = function () {
        let data = {
            pvdId: 0,
            maxP: 2000000000,
            minP: 0,
            rg: 0,
            pdtCat: similarCategory,
            order: 5,
            tags: [],
            srch: "",
            draw: 12,
            start: 0,
            length: 12,
            existence: false,
            brands: similarBrand
        };
        $.ajax({
            "data": data,
            "url": "/api/product/search",
            "method": "post",
            "beforeSend": function () {

            },
            "complete": function () {
                $('.custom1').owlCarousel({
                    autoplay: false,
                    rtl: true,
                    loop: true,
                    nav: true,
                    autoWidth: true,
                    autoplayTimeout: 8000,
                    items: 10,
                    dots: false,
                    margin: 0,
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
                if ($('.checkIsAnyProductInArea').val() != undefined && $('.checkIsAnyProductInArea').val() != null  && !$('.checkIsAnyProductInArea').val()){
                    removeAllAddToCartBtnInPages();
                }
            },

            "success": function (res) {
                if (res.result) {

                    let result = JSON.parse(res.payload);
                    $('#suggested').empty();

                    let outPut = '';
                    result.data.forEach(item => {
                        let imageSrc ="";
                        if (item.images.length == 0) {
                            imageSrc = '/assets/behta_logo/BehtaTahvie.png';
                        }else {
                            imageSrc = '/thumbnail/files/0/' +item.images[0];
                        }

                        let productName = item.name;
                        productName = removeHalfSpace(productName);
                        outPut +=
                            '                                <div class="product-item" style="opacity: ' + (item.existence ? "1" : "0.5") + '">\n' +
                            '                                    <figure><a href="/product/' + item.id + '/' + productName + '" ><img src="'+imageSrc+'" class="rounded"  alt=" بهتاتهویه"></a></figure>\n' +
                            '                                    <div class="product-content">\n' +
                            '                                        <span class="font-12"><a href="/product/' + item.id + '/' + productName + '" >' + item.name + '</a></span>\n' +
                            '                                        <div class="product-footer">\n' +
                            '                                            <div  class="product-cart">\n' +
                            (!item.existence ? ' ' :
                                    '                                                <a data-id="' + item.id + '" href="javascript:;" style="cursor: pointer" class="add-to-cart increaseProductProviderCount"  >\n' +
                                    '                                                    <i class="fa fa-cart-plus"></i>\n' +
                                    '\n' +
                                    '                                                </a>\n'
                            ) +
                            '                                            </div>\n' +
                            '                                            <div class="product-cost">\n' +
                            (  item.offPercent == 0 ? "" :

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
                    $('#suggested').append(outPut);

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

    let increaseOrDecriesFunction = function(){
        $('.cart-products').on('click','.changeCount' , function () {
            let value = $(this).closest('.add-to-card-number').find('#product_count').val();
            let maxAllow = $(this).data('max');
            let minAllow = $(this).data('min');
            let step = $(this).data('step');

            isDecries = false;
            if (!$(this).hasClass('changeCount-plus')){
                isDecries = true;
                step = -step;
            }
            if (Number(value) + Number(step) > maxAllow || Number(value) + Number(step) < minAllow){
                return ;
            }
            if ( Number(value) + Number(step) > maxAllow )
                value = Number(maxAllow);
            else if ( Number(value) + Number(step) < minAllow )
                value = Number(minAllow);
            else
                value = Number(value) + Number(step)

            if (value == 0) {
                return;
            }
                $(this).closest('.add-to-card-number').find('#product_count').val(value);

            let data = {
                v: value, // value
                pId: $(this).data('product-id') // productProviderId
            }
            changeProductCount(data);

        });
    }
    let changeProductCount = function(data){
        $.ajax({
            url:'/api/product/addToCart',
            data: data,
            type:"post",
            success:function (res) {
                if (!isDecries){
                    if (res == null || res.result + '' === 'false') {
                        Command: toastr["error"](res.payload !== '' ? res.payload : 'محصول به سبد خرید اضافه نشد.');

                    } else {
                        Command: toastr["success"]("محصول به سبد خرید اضافه گردید");
                    }
                    toastr.options = {
                        "closeButton": true,
                        "debug": false,
                        "newestOnTop": false,
                        "progressBar": true,
                        "positionClass": "toast-bottom-left",
                        "preventDuplicates": false,
                        "onclick": null,
                        "showDuration": "300",
                        "hideDuration": "1000",
                        "timeOut": "5000",
                        "extendedTimeOut": "1000",
                        "showEasing": "swing",
                        "hideEasing": "linear",
                        "showMethod": "fadeIn",
                        "hideMethod": "fadeOut"
                    };
                }else {
                    Command: toastr["error"]("محصول از سبد خرید کم گردید");
                    toastr.options = {
                        "closeButton": true,
                        "debug": false,
                        "newestOnTop": false,
                        "progressBar": true,
                        "positionClass": "toast-bottom-left",
                        "preventDuplicates": false,
                        "onclick": null,
                        "showDuration": "300",
                        "hideDuration": "1000",
                        "timeOut": "5000",
                        "extendedTimeOut": "1000",
                        "showEasing": "swing",
                        "hideEasing": "linear",
                        "showMethod": "fadeIn",
                        "hideMethod": "fadeOut"
                    };
                }
                reloadCartList(); // in header.js
                getInCartProduct();
            },
            error:function () {
            }
        })
    }

    let removeItem = function () {
        $('.cart-products').on('click', '.product-remove-item', function () {

            let count = 0;
            deleteItem($(this).data('id'), count);
            $('#delete-modal').modal('hide');
        });
    }

    let deleteItem = function (productId, count) {
        let data = {
            pId: productId,
            v: count,
            d: "",
            sF: []
        };
        $.ajax({
            url: "/api/product/addToCart",
            type: 'POST',
            data: data,
            beforeSend: function () {
                $(".loading1").show();
            },
            success: function () {
                reloadCartList();
                getInCartProduct();
                $(".loading1").hide();
                $("#checksection").load("/checkoutsection");
                $(".shopcart").load("/headerCartCount");
            },
            error: function (request, error) {
                $('.toast').addClass('toast-failed');
                $('.toast-header').text(request);
                $('.toast').removeClass('hidden');
                $('.toast-body').text(error);
                $('.toast').toast('show');
            }
        });

    };
    let number_format = function (total) {
        return total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };
    return {
        // public functions
        init: function () {
            getInCartProduct();
            initSimilarProduct();
            increaseOrDecriesFunction();
        }
    };
}();

jQuery(document).ready(function () {
    cartPage.init();
});