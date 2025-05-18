let productDetailPage = function () {

    let page = 1;

    let coomentPage = 0; // initial page
    let commentEnds = false; // initial page
    let isLoading = false; // flag to prevent multiple requests

    let initSimilarProduct = function () {


        let pName = "";
        let listCat = [];
        listCat.push($('.product_parent_cat').val(), $('.product_cat').val());
        let data = {
            pvdId: 0,
            maxP: 2000000000,
            minP: 0,
            rg: 0,
            pdtCat: listCat,
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
                $('.custom1').owlCarousel({
                    autoplay: false,
                    rtl: true,
                    loop: false,
                    nav: true,
                    autoWidth: true,
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
                    $('#related-product-carousel').empty();

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
                            '                                    <figure><a href="/product/' + item.id + '/' + productName + '" ><img src="'+imageSrc+'" class="rounded" alt="' + item.name + '"></a></figure>\n' +
                            '                                    <div class="product-content">\n' +
                            '                                        <span class="font-12"><a href="/product/' + item.id + '/' + productName + '" >' + item.name + '</a></span>\n' +
                            '                                        <div class="product-score">\n' +
                            '                                            <span class="star"><i class="fa fa-star"></i></span>\n' +
                            '                                            <span class="vote">' + item.rate + '</span>\n' +
                            // '                                            <span class="count">(22)</span>\n' +
                            '                                        </div>\n' +
                            '                                        <div class="product-footer">\n' +
                            '                                            <div  class="product-cart">\n' +
                            (!item.existence ? ' ' :
                                '                                                <a data-id="' + item.id + '" href="javascript:;"  style="cursor: pointer" class="add-to-cart increaseProductProviderCount"  >\n' +
                                '                                                    <i class="fa fa-cart-plus"></i>\n' +
                                '\n' +
                                '                                                </a>\n'
                            ) +
                            '                                            </div>\n' +
                            '                                            <div class="product-cost">\n' +
                            (  item.offPercent ==0 ? "" :

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
                    $('#related-product-carousel').append(outPut);

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
    let getCommentDetail = function () {
        $.ajax({
            url: '/api/comment/userComments/detail/' + $('#productId').val(),
            method: 'post',
            processData: false,
            contentType: false,
            success: function (res) {

                let outPut = '' +
                    '                <section class="product-score-box col-12">\n' +
                    '                    <div class="score-header">\n' +
                    '                        <span class="overall-score">' + res.totalRatePercent + '</span>\n' +
                    '                        <span class="score-result">\n' +
                    '                            <span> از  ۵</span>\n' +
                    '                        </span>\n' +
                    '                    </div> \n' +
                    '                    <div class="score-main row">\n' +
                    '                        <div class="score-star col-4">\n' +
                    '                            <span style="display: inline-flex">\n' +
                    '                                <i class="icon icon-star-1"></i><br>\n' +
                    '                                <i class="icon icon-star-1"></i><br>\n' +
                    '                                <i class="icon icon-star-1"></i><br>\n' +
                    '                                <i class="icon icon-star-1"></i><br>\n' +
                    '                                <i class="icon icon-star-1"></i><br>\n' +
                    '                            </span>\n' +
                    '                        </div>\n' +
                    '                        <div class="col-8  text-muted-2">\n' +
                    '                            <span>  از مجموع  ' + res.totalRate  + ' نظر </span><br>\n' +
                    '                        </div>' +
                    '                   </div> ' +
                    '<button id="register_comment" type="button" class="btn btn btn-outline-primary add_comment btn-sm register_comment">افزودن دیدگاه\n' +
                    '<i class="button-loading spinner-border loading-btn d-none"></i>\n' +
                    '</button>' +
                    '</section>';


                $('#rate_detail').empty();
                $('#rate_detail').append(outPut);
            },
            complete: function (){
                openCommentModal()
            }
        })
    };

    let commentLazyLoading = function (){
        $('.comment-loading').removeClass('d-none')
        initCommentTable();
        // Check if we've reached the end of the section
        $(window).scroll(function() {
            if ($(window).scrollTop() + $(window).height() >= $('#comment-box').offset().top + $('#comment-box').height()) {
                if (!commentEnds && !requestInProgress) {
                    requestInProgress = true;
                    $('.comment-loading').removeClass('d-none')
                    setTimeout(function () {
                        coomentPage++;
                        initCommentTable();
                    }, 1000);
                }
            }
        });
    }
    let requestInProgress = false;

    let initCommentTable = function () {
        let data = {
            currentPage: page,
            productId: $('#product_id').val()
        }
        console.log(commentEnds);
        if (!commentEnds) {
            $.ajax({
                url: '/api/comment/product/list/' + $('#productId').val() + '/' + coomentPage + '/' + 10,
                method: 'post',
                data: data,
                processData: false,
                contentType: false,
                beforeSend: function (){
                    requestInProgress = true;
                },
                success: function (res) {
                    let outPut = appendDateToTable(res);
                    // $('#comment-box').empty();
                    $('#comment-box').append(outPut);
                    $('.pagination').empty();
                    let totalPage = Math.floor(res.recordsTotal / 10) + 1;
                    index.pagination(page, totalPage, 'pagination');
                },
                complete: function (){
                    requestInProgress = false;
                    $('.comment-loading').addClass('d-none')
                }
            })
        }else{
            $('.comment-loading').addClass('d-none')
        }
    }
    let appendDateToTable = function (data) {
        let outPut = '';
        if ((data == null || data.length == 0) && coomentPage == 0)
            return  '' +
                '                        <div class="comment-item">\n' +
                '                            <div class="comment-header">\n' +
                '                                <span class="comment-name"></span>\n' +
                '                                <span class="comment-date"></span>\n' +
                '                                <span class="score-star">\n' +
                '                            </span>\n' +
                '                            </div>\n' +
                '                            <div class="comment-main">\n' +
                '                                <p>کامنتی ثبت نشده است</p>\n' +
                '                            </div>\n' +
                '                        </div>\n';


        if (coomentPage != 0 && (data == null || data.length == 0)) {
            commentEnds = true;
        }

        data.forEach(item => {
            outPut += '' +
                '                        <div class="comment-item">\n' +
                '                            <div class="comment-header">\n' +
                '                                <span class="comment-name">' + item.name + '</span>\n' +
                '                                <span class="comment-date">' + item.date + '</span>\n' +
                '                                <span class="score-star">\n';
            for (let i = 0; i < item.rate; i++) {
                outPut += '' +
                    '<i class="icon icon-star-1"></i>';
            }
            for (let i = 0; i < 5 - item.rate; i++) {
                outPut += '' +
                    '<i class="icon icon-star2"></i>';
            }

            outPut += '                            </span>\n' +
                '                            </div>\n' +
                '                            <div class="comment-main">\n' +
                '                                <p>' + item.text + '</p>\n' +
                '                            </div>\n' +
                '                        </div>\n'
        });
        return outPut;
    }

    let increaseOrDecriesFunction = function () {
        $('.changeCount').on('click', function () {
            let value = $($('.product_count')[0]).val();
            let maxAllow = $(this).data('max');
            let minAllow = $(this).data('min');
            let step = $(this).data('step');
            step = ($(this).hasClass('changeCount-plus') ? step : -step);

            if (Number(value) + Number(step) > maxAllow || Number(value) + Number(step) < minAllow){
                return ;
            }

            if (Number(value) + Number(step) > maxAllow)
                value = Number(maxAllow);
            else if (Number(value) + Number(step) < minAllow)
                value = Number(minAllow);
            else
                value = Number(value) + Number(step)
            if (value == 0) {
                return;
            }
            $('.product_count').val(value);
        });
    }
    let addToCart = function () {
        $('.submit_add').on('click', function () {
            let data = {
                v: $('#product_count').val(), // value
                pId: $(this).data('id') // productProviderId
            }
            $('.loading-btn').removeClass('d-none');
            $.ajax({
                url: '/api/product/addToCart',
                data: data,
                type: "post",
                success: function (res) {
                    // swal.fire({
                    //     "title": " ",
                    //     "text": "محصول به سبد خرید اضافه شد",
                    //     "type": "success",
                    //     "confirmButtonClass": "btn btn-warning",
                    //     "confirmButtonText": "تایید"
                    // });

                    if (res.result) {

                        Command: toastr["success"]("محصول به سبد خرید اضافه گردید")

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
                        }
                        reloadCartList(); // in header.js
                    }else{
                        swal.fire({
                            "title": " خطا",
                            "text": res.payload,
                            "type": "error",
                            "confirmButtonClass": "btn btn-warning",
                            "confirmButtonText": "تایید"
                        });
                    }
                    $('.loading-btn').addClass('d-none');

                    // $('.loading-btn').addClass('d-none');

                },

                complete: function () {
                    var myAlert = $('#toastNotice');//select id of toast
                    var bsAlert = new bootstrap.Toast(myAlert);//inizialize it
                    bsAlert.show();//show it
                    $('.loading-btn').addClass('d-none');

                },
                error: function () {
                }
            })

        })
    };

    let openImageInModal = function () {
        $('.open_in_modal').on('click', function () {
            let imageId = $(this).data('image-id');
            $('#image_modal').modal('show');
            $('#image_modal').find('.modal-content').empty();
            $('#image_modal').find('.modal-content').append(
                '<img style=" min-width:500px; min-height: 700px" src="/thumbnail/files/0/' + imageId + '.jpg" alt= "'+pName+'"\n' +
                '\n' +
                '\n>\n'
            );
        })
    }

    let openCommentModal = function () {
        $('#register_comment').on('click', function () {
            $('#comment_modal').modal('show');
            $('#comment_modal').on('shown.bs.modal', function () {
                initSelectStarForComment();
                submitComment();
            });
        })
    };

    let initSelectStarForComment = function (){
        $('.star_modal').on('click', function(){
        });

        $('.star_modal').mouseenter(function(){
            console.log($(this));
        });
        $('.star_modal').mouseleave(function(){
        });
    }
    let submitComment = function () {
        $('#submit_comment_btn').on('click', function () {

            let rateEl = $('.star-rating :checked ~ label.star').last();
            let data = {
                rate: ( rateEl == '' || rateEl == null || rateEl == undefined ? 0 : $(rateEl.prevObject[0]).data('val') ),
                commenter: $('#commentor_name').val(),
                email: $('#commentor_email').val(),
                text: $('#commentor_comment').val()
            }
            $.ajax({
                    url: '/api/comment/product/send/' + $('#productId').val(),
                    data: data,
                    method: "post",
                    success: function (res) {
                        if (res.result) {
                            swal.fire({
                                "title": "",
                                "text": "نظر شما با موفقیت ثبت گردید",
                                "type": "success",
                                "confirmButtonClass": "btn btn-warning",
                                "confirmButtonText": "تایید"
                            });
                            $('#comment_modal').modal('hide');
                            $('#commentor_name').val('')
                            $('#commentor_email').val('')
                            $('#commentor_comment').val('')
                        }
                        else
                            swal.fire({
                                "title": "خطا",
                                "text": res.payload,
                                "type": "error",
                                "confirmButtonClass": "btn btn-warning",
                                "confirmButtonText": "تایید"
                            });
                    },
                    error: function () {

                    },
                    complete: function () {
                        page = 1;
                        // initCommentTable();
                    }
                }
            )
        })
    }

    let number_format = function (total) {
        return total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };

    let initDefaultItems = function () {

        $('.primitive_price').each(function () {
            $(this).text(number_format($(this).text()));
        });
        $('.final_price').each(function () {
            $(this).text(number_format($(this).text()));
        });

        $("#zoom1").elevateZoom({
            gallery: 'gallery_01',
            responsive: true,
            cursor: 'crosshair',
            zoomType: 'inner'

        });
        var $singleProductActive = $('.single-product-active');
        if ($singleProductActive.length > 0) {
            $('.single-product-active').owlCarousel({
                loop: false,
                rtl: true,
                nav: true,
                autoplay: false,
                autoplayTimeout: 8000,
                items: 3,
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
                        items: 3,
                    },
                    1200: {
                        items: 3,
                    },
                }
            });
        }
    }

    let changeProductImages = function () {
        $('.small_pic').on('click' , function () {
            $('#zoom1').attr('src' ,$(this).attr('src'));
            $('#zoom1').attr('data-image-id' ,$(this).data('image-id'));
            $('#zoom1').data('image-id' ,$(this).data('image-id'));
            $('#zoom1').attr('style' ,'max-height: 300px!important; min-height: 300px!important;');

            $("#zoom1").elevateZoom({
                gallery: 'gallery_01',
                responsive: true,
                cursor: 'crosshair',
                zoomType: 'inner'

            });
        })
    }
    let getInformation = function () {
        $.ajax({
            "url": "/api/product/information/" + $('#productId').val(),
            "method": "post",
            "beforeSend": function () {
                $('.loading-box').removeClass('d-none');
            },
            "complete": function () {
                $('.loading-box').addClass('d-none');
            },

            "success": function (res) {
                $('.info_cat_table').empty()
                $('.info_cat_table').append(res);
            },
            error: function () {
            }
        });

    }

    return {
        // public functions
        init: function () {
            getInformation();

            pName = $('#productProviderName').val()

            getCommentDetail();
            increaseOrDecriesFunction();
            $('#zoom1').attr('style' ,'max-height: 300px!important; min-height: 300px!important; ');
            changeProductImages();
            initSimilarProduct();
            addToCart();
            openImageInModal();

            initDefaultItems();
            commentLazyLoading();

            //hideFooter();
            // hideFooter();\
            // if (!$('.checkIsAnyProductInArea').val()){
            //
            //     removeAllAddToCartBtnInPages();
            // }
        }
    };
}();

jQuery(document).ready(function () {
    productDetailPage.init();
});