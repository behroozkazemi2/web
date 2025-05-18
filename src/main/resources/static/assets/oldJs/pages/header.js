let isDecries = false;
let flagForFirstTime = 1;

let reloadCartList = function () {
    $('.in_cart_header').empty();
    $.ajax({
        "url": "/api/cart/list/get",
        "method": "post",
        "success": function (res) {
            $('.in_cart_header').empty();
            let total = 0;
            let total_item_count = 0;
            if (res.length != 0) {
                res.forEach(item => {
                    let imageSrc = "";
                    if (item.image == 0) {
                        imageSrc = '/assets/behta_logo/BehtaTahvie.png';
                    } else {
                        imageSrc = '/thumbnail/files/0/' + item.image;
                    }
                    let productName = item.name;
                    productName = removeHalfSpace(productName);
                    $('.in_cart_header').append("" +
                        '    <div class="cart-menu-item">\n' +
                        '                                <figure><a href="/product/' + item.id + '/' + productName + '"><img src="' + imageSrc + '" alt="' + item.name + '"></a></figure>\n' +
                        '                                <div class="cart-menu-content">\n' +
                        '                                     <a href="/product/' + item.id + '/' + productName + '"> <div class="product-header">\n' +
                        '                                        <h6 class="product-title">' + item.name + '</h6></a>\n' +
                        '                                        <button class="product-remove-item" id="product-remove-item" data-product="' + item.id + '" type="button" ><i class="fa fa-trash"></i></button>\n' +
                        '                                    </div> \n' +


                        '                                    <div class="product-footer">\n' +
                        '                                        <div class="add-to-card-number">\n' +
                        '                                            <span id="inc-button"' +
                        '                                            data-price="' + ((100 - item.offPercent) / 100) * item.primitiveAmount + '"   ' +
                        '                                            data-min = "0" data-id = "' + item.id + '" data-max = "' + (item.productCount < item.maxAllow ? item.productCount : item.maxAllow )+ '" data-step = "1" ' +
                        '                                            class="changeCount inc-button spinner-button"><i\n' +
                        '                                                    class="fa fa-plus"></i></span>\n' +
                        '                                            <input' +
                        '                                                  data-min = "0" data-id = "' + item.id + '" data-max = "' + (item.productCount < item.maxAllow ? item.productCount : item.maxAllow) + '" data-step = "1" ' +
                        '                                                   class="in_cart_product_count" data-price="' + ((100 - item.offPercent) / 100) * item.primitiveAmount + '" type="number" name="product-number" id="product-number" value="' + item.count + '"\n' +
                        '                                                   min="0" max="100"/>\n' +
                        '                                            <span' +
                        '                                                    data-price="' + ((100 - item.offPercent) / 100) * item.primitiveAmount + '" ' +
                        '                                                    data-min = "0" data-id = "' + item.id + '" data-max = "' + (item.productCount < item.maxAllow ? item.productCount : item.maxAllow) + '" data-step = "1" ' +
                        '                                                    id="dec-button" class="changeCount dec-button spinner-button"><i\n' +
                        '                                                    class="fa fa-minus"></i></span>\n' +
                        '                                        </div>\n' +
                        '                                        <div class="product-price">\n' +
                        '                                            <span class="price singel_product_total_price" >' + formatDollar(item.totalAmount) + '</span>\n' +
                        '                                        </div>\n' +
                        '                                    </div>\n' +
                        '                                </div>\n' +
                        '                </div>');

                    // "<div  class=\"cart_item\">" +
                    // "     <div  class=\"cart_img\">\n" +
                    // "         <a href='/p/" + item.id + "'>" +
                    // "<img src=" + (item.image ? '/thumbnail/files/0/' + item.image + '' : '/assets/img/logo.ico') + " alt=\"\">" + "     </div>\n" +
                    // "     <div class=\"cart_info\">\n" +
                    // "         <a href='/p/" + item.id + "'>" + item.name + "</a>\n" +
                    // "         <a href='/p/" + item.id + "'>" + item.count + ' ' + item.unit.name + " </a>\n" +
                    // "         <p style='cursor:pointer' href='/p/\" + item.id + \"'><span class='price-cart-header'>قیمت  " + formatDollar(item.totalAmount) + "  </span></p>" +
                    // "     </div>" +
                    // "     <div  id='product-remove-item' data-product='" + item.id + "' style='cursor:pointer;padding: 10px;width: 30px;height: 30px'  class=\"cart-remove\">\n" +
                    // "         <div   class='fa fa-close'></div>\n" +
                    // "     </div>\n" +
                    // "</div>" +
                    // ""
                    total_item_count+= item.count;
                    if (item.OffPercent != 0) {
                        $('.des' + item.id + '').removeClass('d-none');
                        $('.des' + item.id + '').addClass('line-through');
                    } else {
                        $('.des' + item.id + '').addClass('d-none');

                    }
                    total += item.totalAmount;
                    $('.go_to_cart_page').attr('href', '/cart');

                });
            }else{
                $('.go_to_cart_page').attr('href', '#');
            }
            $("#total_in_cart_price").text(formatDollar(total));
            $(".in_cart_product_count").text(total_item_count);
        },
        complete: function(){
            if (flagForFirstTime) {
                flagForFirstTime = 0;
                increaseOrDecriesFunction();
            }
        },
        error: function (res) {
            // showError(res);
        }
    });

};

function getCategoryChild(child) {

    let children_html = '';
    child.forEach(ch => {
        console.log("child name",ch)
        console.log("child name",ch.children)
        if (ch.children ==  null || ch.children.length == 0){
            children_html +=  '<li><a href="/child.current.name">'+ch.current.name+'</a></li>'

        }else{
            children_html += '<li><a class="title my-flex-baseline" href="#">'+ch.current.name+'<i' +
                ' class=""></i></a></li>';

            children_html += getCategoryChild(ch.children)
        }
    });
    return children_html;
}

let initCategory = function () {
    $.ajax({
        "data": null,
        "url": "/api/product/model/type/0",
        "method": "post",
        beforeSend: function () {
            $('.header_menu').empty();
            // $('.top-parent-category').empty();

        },
        "success": function (res) {
            let children_html = '';
            if (res.result) {

                let resultJson = JSON.parse(res.payload);
                console.log('resultJson == ' , resultJson)
                $('.header_menu').empty();
                // $('.top-parent-category').empty();
                let outPut = '';
                resultJson.forEach(item => {
                    let src ="/assets/images/icons/3.svg";

                    let catName = item.current.name;
                    catName = removeHalfSpace(catName);
                    children_html = '';
                    let final_child = '';

                    if (item.children != null){
                        final_child = getCategoryChild(item.children)
                    }

                    outPut += '' +
                        '                                <li><a  href="#"><i class="bi bi-tablet"></i>'+item.current.name+'</a>' +
                        '                                    <ul class="main-menu-sub back-menu ul_menu"' +
                        '                                         ">' +
                                                                final_child +

                        '                                    </ul>' +
                        '                                </li>';
                });

                $('.top-parent-category').append(outPut);

///نمایش مگامنو آپدیدت جدید
                $(".main-menu-head").hover(function(){
                    $(this).children().find(".main-menu-sub").first().addClass('main-menu-sub-active');
                    $(this).children().addClass('active');
                })
                $(".main-menu-head").mouseleave(function(){
                    $(this).children().find(".main-menu-sub").first().removeClass('main-menu-sub-active');
                    $(this).children().removeClass('active');
                })
                $(".main-menu > li").mouseover(function () {

                    $(".main-menu > li").removeClass("main-menu-sub-active-li");
                    $(this).addClass("main-menu-sub-active-li");
                    $(".main-menu-sub").removeClass('main-menu-sub-active');
                    $(this).children('ul').removeClass('main-menu-sub-active');
                    $(this).children('ul').addClass('main-menu-sub-active');
                });
                $(".main-menu-sub-active").mouseleave(function(){
                    $(".main-menu-sub-active").removeClass("main-menu-sub-active");
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

    });
};

let removeAllAddToCartBtnInPages = function(){

    $('.submit_add').remove();

    //inCreaseDecrice
    $('.increaseProductProviderCount').remove();

    //inCreaseDecrice
    $('.add-to-cart').remove();
    $('.increaseProductProviderCount').remove();

}
let clearInCartProduct = function(){
    $.ajax({
        url:'/api/cart/order/cart/clear',
        method:'post',
        success:function () {
            reloadCartList();
        }
    })
}
let increaseProductProviderCount = function(){

    $(document).on('click', '.increaseProductProviderCount', function () {
        let id = $(this).data('id');
        $.ajax({
            "data": null,
            "url": "/product/increase/" + id,
            "method": "post",
            beforeSend: function () {
            },
            "success": function (res) {
                addToCart({pId: id, v: res + 1}, true);
            },
            error: function () {
            }

        });
    });
}

let getAddressFromSession = function(addressId){
    let data = {
        addressId:addressId != null ? addressId : 0
    }
    $.ajax({
        "data": null,
        "url": "/api/address/getSessionAddress" ,
        data: data,
        "method": "post",
        beforeSend: function () {
            $('.header_menu').empty();
            // $('.top-parent-category').empty();

        },
        "success": function (res) {
            $('#session_address').empty();
            let output ='' +
                 '<h6>موقعیت شما:</h6> ';
            if (res == null || res == "" || res.id == 0){
                output +=
                    ' <span class="session_address_id" data-id="-1" >مشهد بلوار طبرسی شمالی</span>'
                $('#session_address').append(output);

            }else {
                output += '<span class="session_address_id" data-id="'+res.id+'" > '+res.title+'</span>'
                $('#session_address').append(output);
            }
        },
        error: function () {
            //todo error
            $('.toast').removeClass('toast-success');
            $('.toast').addClass('toast-failed');
            $('.toast').removeClass('hidden');
            $('.toast-body').text("خطا در برقراری ارتباط");
            $('.toast').toast('show');
        },
        complete: function () {
            // initCategory();
            $(".main-menu-head").hover(function(){
                $(this).children().find(".main-menu-sub").first().addClass('main-menu-sub-active');
                $(this).children().addClass('active');
            })
            $(".main-menu-head").mouseleave(function(){
                $(this).children().find(".main-menu-sub").first().removeClass('main-menu-sub-active');
                $(this).children().removeClass('active');
            })
            $(".main-menu > li").mouseover(function () {

                $(".main-menu > li").removeClass("main-menu-sub-active-li");
                $(this).addClass("main-menu-sub-active-li");
                $(".main-menu-sub").removeClass('main-menu-sub-active');
                $(this).children('ul').removeClass('main-menu-sub-active');
                $(this).children('ul').addClass('main-menu-sub-active');
            });
            $(".main-menu-sub-active").mouseleave(function(){
                $(".main-menu-sub-active").removeClass("main-menu-sub-active");
            })
            checkIsAnyProductInArea();
        }

    });
}
let increaseOrDecriesFunction = function(){
    $('.in_cart_header').on('click' ,'.changeCount', function () {
        let value =  $(this).closest('.add-to-card-number').find('input').val();
        let id = $(this).data('id');
        let maxAllow = $(this).data('max');
        let minAllow = $(this).data('min');
        let step = $(this).data('step');
        isDecries = false;
        if (!$(this).hasClass('inc-button')){
            isDecries = true;
            step = -step;
        }

        if (Number(value) + Number(step) > maxAllow || value == minAllow){
            return ;
        }
        if ( Number(value) + Number(step) > maxAllow )
            value = Number(maxAllow);
        else if ( Number(value) + Number(step) < minAllow )
            value = Number(minAllow);
        else
            value = Number(value) + Number(step)

        if (value == 0){
            return;
        }
        // INPUT AMOUNT VALUE
        $(this).closest('.add-to-card-number').find('input').val(value);

        // TOTOAL SINGLE PRICE
        $(this).closest('.product-footer').find('.singel_product_total_price').text(formatDollar($(this).data('price') * value));

        addToCart({pId: id, v: value}, false);

    });
}
let addToCart = function(data, isFromIncrese){
        $.ajax({
            url:'/api/product/addToCart',
            data: data,
            type:"post",
            success:function (res) {
                // swal.fire({
                //     "title": " خطا",
                //     "text": "محصول به سبد خرید اضافه شد",
                //     "type": "success",
                //     "confirmButtonClass": "btn btn-warning",
                //     "confirmButtonText": "تایید"
                // });

                if (!isDecries){
                    if (res == null || res.result + '' === 'false') {
                        Command: toastr["error"](res.payload !== '' ? res.payload : 'محصول به سبد خرید اضافه نشد.');

                    } else {
                        Command: toastr["success"]("محصول به سبد خرید اضافه گردید");
                    }                    toastr.options = {
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
            },

            complete: function(){
                // var myAlert = $('#toastNotice');//select id of toast
                // var bsAlert = new bootstrap.Toast(myAlert);//inizialize it
                // bsAlert.show();//show it

                let sum = 0 ;
                $('.in_cart_header').find('.in_cart_product_count').each( function () {
                    sum += $(this).data('price') * $(this).val()
                });

                //  TOTAL ALL PRICE
                if (sum != 0) {
                    $('#total_in_cart_price').text(formatDollar(sum));
                }
            },
            error:function () {
            }
        })

};
let dropDownCartMenu = function (){
    $( ".cart-toggle" )
        .mouseenter(function() {
            if (Number($('.in_cart_product_count').text()[0]) != 0){
            $('.cart-menu').css('display','flex')
            }
        })
        .mouseleave(function() {
            $('.cart-menu').css('display','none')
        });
}


let showToastForIsNotLogin = function(){
    $('.show_toast-not_login').on('click', function () {
        Command: toastr["warning"]("برای افزودن آدرس ابتدا باید وارد شوید.");
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
    });
}

let headerAddersList = function () {
    $.ajax({
        url: '/api/address/list',
        method: 'post',
        beforeSend: function () {
        },
        success: function (res) {
            if (res.result){
                $('.address_drop_down_list').empty();
                let result = JSON.parse(res.payload);

                if (result.length == 0){
                    result.push({
                        id: 0,
                        title: "پیش فرض"
                    });
                }
                result.forEach( item => {
                    let outPut = '' +
                        '<li  class="dropdown-item ">\n' +
                        '<button type="button" class="btn dropdown_address_change col-12"  data-id ="' +item.id+ '"  >\n ' +
                        '                        <img src="/assets/images/icons/9.svg" alt=" بهتاتهویه">\n' +
                        '                        <span class="address-content" id="session_address">\n' +
                        '                            <label for="address-item1">' + item.title + '</label>\n' +
                        '                    </span>\n' +
                        '</button> \n' +
                        '                    </li>\n';
                    $('.address_drop_down_list').append(outPut);
                });


            }
        },
        complete: function () {

        },
        error: function () {
        }
    });

};
let checkIsAnyProductInArea = function () {
    $.ajax({
        url: '/api/product/checkIsAnyProductInArea',
        method: 'post',
        beforeSend: function () {
        },
        success: function (res) {
            if (res.result){
                if(res.payload == "false"){

                    // TODO SET TOAST HERRE
                    $('.checkIsAnyProductInArea').val(res.payload)
                    // productDetailPage
                    removeAllAddToCartBtnInPages();

                    Command: toastr["warning"]("در محدوده آدرس انتخابی شما هیچ تامین‌کننده‌ای وجود ندارد");
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

                    clearInCartProduct();
                }

            }else {

            }
        },
        complete: function () {
        },
        error: function () {
        }
    });

};
let changeAddress = function(){
    $('.address_drop_down_list').on('click', '.dropdown_address_change', function () {
        let newAddress = $(this).data('id');

        console.log(newAddress);
        $.ajax({
            url: '/api/address/checkAddressLocationArea/'+ newAddress,
            method: 'post',
            beforeSend: function () {
            },
            success: function (res) {
                if (res.result) {
                    // new Location (not Same with old Address location)
                    if (res.payload == "false") {
                        Swal.fire({
                            "title": "تغییر آدرس",
                            "text": "در صورت تغییر آدرس محتویات سبد خرید شما خالی خواهد گردید ",
                            "type": "question",
                            "showCancelButton": true,
                            "confirmButtonClass": "btn btn-primary",
                            "confirmButtonText": "تایید",
                            "cancelButtonText": "بازگشت"
                        }).then(function (result) {
                            if (result.value == true) {
                                $.ajax({
                                    url: '/api/address/order/cart/clear/' + newAddress,
                                    method: 'post',
                                    success: function (res) {
                                        if (res.result) {
                                            requestForChangAddress(newAddress, true);
                                        } else {
                                            swal.fire({
                                                "title": "خطا ",
                                                "text": res.payload,
                                                "type": "error",
                                                "confirmButtonClass": "btn btn-warning",
                                                "confirmButtonText": "تایید"
                                            });
                                        }
                                    },
                                    error: function () {
                                        swal.fire({
                                            "title": "خطا ",
                                            "text": "خطا در برقراری ارتباط با سرور",
                                            "type": "error",
                                            "confirmButtonClass": "btn btn-warning",
                                            "confirmButtonText": "تایید"
                                        });
                                    },
                                    complete: function () {
                                        reloadCartList();
                                    }
                                })
                            }else{
                            }
                        });
                    }
                    // old Location (Same with old Address location)
                    else{

                        console.log("WhyGo Here");
                        requestForChangAddress(newAddress, false);
                    }

                }
                else{
                    swal.fire({
                        "title": " خطا",
                        "text": res.payload,
                        "type": "error",
                        "confirmButtonClass": "btn btn-warning",
                        "confirmButtonText": "تایید"
                    });
                    // ERROR
                }
            },
            complete: function () {
                checkIsAnyProductInArea();
            },
            error: function () {
                swal.fire({
                    "title": " خطا",
                    "text": "خطا در بر قراری ارتباط با سرور5",
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                    "confirmButtonText": "تایید"
                });
            }
        });
    });
};
let requestForChangAddress = function(addressId, needToReload ){
    $.ajax({
        url: '/api/address/changeSelected/' + addressId,
        method: 'post',
        beforeSend: function () {
        },
        success: function () {
            Command: toastr["success"]("آدرس تغییر یافت")
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
            if (addressId != 0) {
                let addClass = 'radio_' + addressId
                $('.' + addClass + '').prop('checked', true);
                if (needToReload) {
                    location.reload()
                }
            }
            getAddressFromSession(addressId);
        },
        complete: function(){

        },
        error: function(){
            swal.fire({
                "title": " خطا",
                "text": "خطا در بر قراری ارتباط با سرور",
                "type": "error",
                "confirmButtonClass": "btn btn-warning",
                "confirmButtonText": "تایید"
            });
        }
    });
}

// let addOrEditAddress = function () {
//     $('#new-address').on('click',function () {
//         getAddressData(0)
//     });
//     $('.edit-address').on('click',function () {
//         let id = $(this).data('id');
//         getAddressData(id)
//     })
// };
// let deleteAddressButton = function () {
//     $('#delete-address').on('click',function () {
//         let id = $(this).data('id');
//         deleteAddress(id);
//     })
// };
// let deleteAddress = function (id) {
//     let data = {
//         id: id,
//     };
//     swal.fire({
//         text: "آیا نسبت به حذف آدرس اطمینان دارید؟",
//         type: 'question',
//         showCancelButton: true,
//         confirmButtonColor: 'primary',
//         confirmButtonText: 'بله',
//         cancelButtonText: 'خیر',
//         cancelButtonColor: 'btn-warning',
//
//         // reverseButtons: true
//     }).then(function (result) {
//         if (result.value) {
//             $.ajax({
//                 "data": data,
//                 "url": "/api/user/address/delete",
//                 "method": "post",
//                 "beforeSend": function () {
//                     $('.loading').removeClass('hidden')
//                 },
//                 "complete": function () {
//                     $('.loading').addClass('hidden')
//                 },
//                 "success": function () {
//                     addersList();
//                     getAddressFromSession();
//                 },
//                 "error": function (request) {
//                     //todo error
//                     showErrorAnotherModel(request);
//                 }
//             })
//         } else if (result.dismiss === 'cancel') {
//         }
//     })
// };
// let getAddressData = function (id) {
//     $.ajax({
//         url:'/api/address/getModalAddress/' + id,
//         method:'post',
//         success:function (res) {
//             $('#kt_modal_remote').find('.modal-content').empty();
//             $('#kt_modal_remote').find('.modal-content').append(res);
//             $('#kt_modal_remote').modal('show');
//             addressModalShownOrHidden();
//
//
//         },
//         complete:function () {
//         }
//     })
// }
// let addressModalShownOrHidden = function(){
//     $('#kt_modal_remote').on('shown.bs.modal', function () {
//         addAddress();
//         addAddressFlag = 0;
//         $('#kt_location').empty();
//         let locInput = $('#kt_modal_remote').find('.latlon');
//         initAddressMap('kt_location' , $(locInput).data('lat'), $(locInput).data('lon') );
//         addressMarkerLat = $(locInput).data('lat');
//         addressMarkerLon = $(locInput).data('lon');
//     });
//     $('#kt_modal_remote').on('hidden.bs.modal', function () {
//         $(this).data('bs.modal', null);
//         $(this).data('bs.modal', null);
//         $('#kt_modal_remote').find('#ratio-edit').val('');
//         $('#kt_modal_remote').data('modal', null);
//     });
// };
// let addAddress = function () {
//     $('#confirm-location').on('click', function () {
//         if ($('#address-field').val() == "" && $('#address-field').val() ==  null) {
//             swal.fire({
//                 "title": " خطا",
//                 "text": "آدرس خود را وارد نمایید ",
//                 "type": "error",
//                 "confirmButtonClass": "btn btn-warning",
//                 "confirmButtonText": "تایید"
//             });
//         } else {
//             $('.empty-address').addClass('d-none');
//             let address = $('#address-field').val();
//             $('#address-field').text("");
//             $('#address-field').val("");
//             if (addressMarkerLat == null || addressMarkerLon == null || addressMarkerLat == "" || addressMarkerLon == "") {
//                 swal.fire({
//                     "text": 'لططفا موقعیت خود را از روی نقشه انتخاب نمایید.',
//                     "type": "error",
//                     "confirmButtonClass": "btn btn-warning",
//                     "confirmButtonText": "تایید"
//                 });
//             }
//
//
//             let data = {
//                 id: Number($('#kt_modal_remote').find('#address_id').val() ?? 0),
//                 address: address,
//                 // productRecipientPhone: productRecipientPhone,
//                 lat: addressMarkerLat,
//                 lng: addressMarkerLon
//             };
//
//             if (addAddressFlag == 0 ) {
//                 addAddressFlag ++;
//                 $.ajax({
//                     "data": data,
//                     "url": "/api/address/addOrEdit",
//                     "method": "post",
//                     "beforeSend": function () {
//                     },
//                     "complete": function () {
//                     },
//                     "success": function (res) {
//                         $('#kt_modal_remote').modal('toggle');
//                         $('#locationModal').modal('toggle');
//
//                         addersList();
//                         getAddressFromSession(JSON.parse(res.payload).id)
//                     },
//                     error: function () {
//                         //todo error
//                     }
//                 });
//             }
//
//         }
//     })
// };

let removeHalfSpace = function(value) {
    const str = value.split('');
    str.forEach((item, index) => {
        const charCode = item.charCodeAt(0);
        if (charCode === 32 || charCode === 8204) {
            str[index] = '-';
        }
    });
    value = str.join('');
    return value;
}





let headerJS = function () {

    function delay(callback, ms) {
        var timer = 0;
        return function() {
            var context = this, args = arguments;
            clearTimeout(timer);
            timer = setTimeout(function () {
                callback.apply(context, args);
            }, ms || 0);
        };
    }
    let search = function () {
        $('#search-input').on('keyup',delay(function (event) {
            let data = {
                pvdId: 0,
                maxP: 10000000,
                minP: 0,
                rg: 0,
                pdtCat: [],
                order: 0,
                tags: [],
                srch: this.value,
                draw: 12,
                start: 0,
                length: 20,
                existence: true,
                brands: []
            };
            $('.search-suggest-box').empty();
            $.ajax({
                "url": "/api/product/search",
                "data": data,
                "success": function (res) {
                    if (res.result) {
                        let resultJson = JSON.parse(res.payload);
                        $('.search-suggest-box').empty();
                        $('.search-suggest-box').append('<ul>');

                        resultJson.data.forEach(item => {
                            let productName = item.name;
                            productName = removeHalfSpace(productName);
                            $('.search-suggest-box').append("" +
                                '    <li class="search-item">\n' +
                                '         <i class="fa fa-search"></i>\n' +
                                '         <a href="/product/'+item.id+ '/' + productName + '" class="product-title">'+item.name+'</a>\n' +
                                '    </li>\n')
                        });
                        $('.search-suggest-box').append('</ul>');
                    } else {
                    }
                },
                error: function () {
                    //todo error
                    $('.toast').removeClass('toast-success');
                    $('.toast').addClass('toast-failed');
                    $('.toast').removeClass('hidden');
                    $('.toast-body').text("خطا در برقراری ارتباط");
                    $('.toast').toast('show');
                },
                complete: function () {
                }
            });


        },1000));

        $('#search-btn').click(function (event) {
            event.preventDefault();
            let search = $('#search-input').val();
            let url = '/productCategory/all/all/search?searchString=' + search;
            // url = encodeURI(url)
            window.location = url
        })
    };

    let logOut = function () {
        $("#exit").click(function () {
            exit();
        });
    };
    let removeItem = function () {
        $('.in_cart_header').on('click', '.product-remove-item', function () {

                let count = 0;
                deleteItem($(this).data('product'), count);
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
    let dropdownclick = function () {
        $('.dropbtn').on('click', function () {
            document.getElementById("myDropdown").classList.toggle("show");

        });
        // Close the dropdown if the user clicks outside of it
        window.onclick = function (event) {
            if (!event.target.matches('.dropbtn')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                var i;
                for (i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }
    };



    let number_format = function (total) {
        return total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };
    return {
        // public functions
        init: function () {
            // initCategory();
            headerAddersList();
            getAddressFromSession();
            changeAddress();
            search();
            dropdownclick();
            increaseProductProviderCount();
            removeItem();
            logOut();
            reloadCartList();
            dropDownCartMenu();
            showToastForIsNotLogin();

        },

    };
}();
jQuery(document).ready(function () {
    headerJS.init();
});

