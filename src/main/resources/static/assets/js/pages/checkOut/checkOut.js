let productDetail = function () {
    let addAddressFlag = 0;
    let paymentMethod = 0;

    let selectPaymentMethod = function (){
      $('.payment_method').on('click',function (){
          paymentMethod=0;
          $('.payment_method').addClass('deSelected_payment')
          $('.payment_method').removeClass('selected_payment')
          $(this).removeClass('deSelected_payment')
          $(this).addClass('selected_payment')
          paymentMethod = $(this).data('type-id');
          if (paymentMethod == 2){
              $('.nonCashMethod').removeClass('d-none');
          }else{
              $('.nonCashMethod').addClass('d-none');
          }
      });
    };
    // navAddress
    let addersList = function () {
        $.ajax({
            url: '/api/address/getList/changeable',
            method: 'post',
            beforeSend: function () {
            },
            success: function (res) {
                $('.address-box').empty();
                $('.address-box').append(res);
            },
            complete: function () {
                let selectedId = 'radio_' + $('.session_address_id').data('id');
                $('.' + selectedId + '').prop('checked', true);
                // deleteAddressButton();
            },
            error: function () {
            }
        });

    };


    let changeAddressRadio = function (){
        $('.address-box').on('click', '.user_address_id', function (){

            $('.selected_border').removeClass('selected_border');
            $($(this).parent()[0]).addClass('selected_border');
            let newAddress = 0;
            if ($(this).is('checked') || $(this).prop('checked')) {
                newAddress = $(this).data('id');
            }

            requestForChangAddress(newAddress, false);
        })
    };

    let submitOffCode = function () {
        $('.off-btn').on('click', function () {
            if ($('#off-code').val() === '') {
                $('#discount-error').empty();
                $('#discount-error').text("لطفا کد تخفیف را وارد نمایید.");
                $('#discount-error').css('color', 'red');
            } else {
                $('.loading-btn').removeClass('d-none');
                $('#discount-error').empty();
                showFactor();
            }
        });
    };
    let showFactor = function () {
        // TODO FIX DATE AND TIME
        let addressId = 0

        $('.address-box').find('.user_address_id').each(function () {
            if ($(this).is('checked') || $(this).prop('checked')) {
                addressId = $(this).data('id');
            }
        });
        let data = {
            address: addressId,
            discount: $('#off-code').val(),
            date: $('.send-day').val(),
            time: $('.send-time').val()
        };
        $.ajax({
            "data": data,
            "url": "/api/cart/factor",
            "method": "post",
            "beforsend": function () {
                $('.loading-box').removeClass('d-none');
                $('.loading-btn-submit').removeClass('d-none');
                $('.order-main').addClass('d-none');
            },
            "complete": function () {
                $('.loading-box').addClass('d-none');
                $('.order-main').removeClass('d-none');
                $('.loading-btn').addClass('d-none');
                $('.loading-btn-submit').addClass('d-none');

                submitPayment();
            },

            "success": function (res) {
                if (res.result) {
                    let resultJson = JSON.parse(res.payload);
                    if (resultJson.offCodeAmount == 0) {
                        $('.off-order-item4').addClass('d-none')
                        $('#discount-error').text(resultJson.offCodeMessage)
                        $('#discount-error').css('color', 'red');

                    } else {
                        $('.off-order-item4').removeClass('d-none')
                        $('#discount-error').text(resultJson.offCodeMessage)
                        $('#discount-error').css('color', 'green')
                    }
                    // مبلغ اولیه
                    $('.order-price').text(formatDollar(resultJson.primitiveAmount));

                    //   تعداد محصولات
                    $('.order_count').text("(" + resultJson.count + ")");

                    $('.min-price-in-cart').text(formatDollar(resultJson.primitiveAmount - resultJson.offPriceAmount) );
                    $('.prg-percent-complete').css('width',  (resultJson.primitiveAmount - resultJson.offPriceAmount ) * 100 / 50000 + '%' )
                    // مبلغ تخفیف محصولات
                    $('.order-off').text(formatDollar(resultJson.offPriceAmount));

                    // درصد تخفیف محصولات
                    // $('.order-percent').text(resultJson.finalAmount * 100 / resultJson.primitiveAmount);


                    //  مبلغ کد تخفیف
                    $('.offCode').text(formatDollar(resultJson.offCodeAmount));

                    // هزینه مالیات
                    $('#tax-amount').text(formatDollar(resultJson.taxAmount));

                    // موجودی حساب
                    $('.in_cart_amount').text(formatDollar(resultJson.availableInBalance));

                    // هزینه ارسال
                    $('.transmit-price').text(formatDollar(resultJson.deliveryAmount));

                    // هزینه ارسال
                    if ( resultJson.primitiveAmount - resultJson.offPriceAmount  < 50000)
                    {
                        $('.prg-percent-complete').addClass('not-allow-for-min-price')
                        $('.min-factor-price-progress').removeClass('d-none');
                    }
                    // if (resultJson.finalAmount - resultJson.availableInBalance > 0) {
                        //  مبلف قابل پرداخت
                        $('.last_price-to_pay').text(formatDollar(resultJson.finalAmount ));
                    // } else {
                    //     $('.last_price-to_pay').text('پرداخت از کیف پول ')
                    // }
                    //  مبلف  فاکتور
                    $('.order-overall').text(formatDollar(resultJson.finalAmount));


                    if (resultJson.offCodeAmount != 0) {
                        $('.off-tab').removeClass('d-none');
                    } else {
                        $('.off-tab').addClass('d-none');
                    }
                    if (resultJson.taxAmount != 0) {
                        $('.tax-tab').removeClass('d-none');
                    } else {
                        $('.tax-tab').addClass('d-none');
                    }


                    if (resultJson.count == 0) {
                        $('.order-confirm').addClass('disabled')
                        $('.order-confirm').css('opacity', '0.5')
                        $('.order-confirm').removeClass('order-confirm')
                    }
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

    let submitPayment = function () {
        $(document).on('click', '.order-confirm', function () {
            sweetalert();
        })
    };

    let globalTime = [];
    let setTimeAndDate = function () {
        $.ajax({
            "url": "/api/cart/getTime",
            "beforeSend": function () {
                $('.loading').removeClass('hidden')

            },
            "success": function (res) {

                // DATES

                $('.dates').niceSelect()
                $('.times').niceSelect()
                globalTime = res.data.times;


                let times = filterTimesAndShowAvailableTimes(res.data.times,  new Date().getHours() + 1);
                if (times.length == 0) {
                    times.push({name: "زمانی در دسترس نیست ", id: 1})
                }


                appendOptions('send-day', res.data.dates);
                appendOptions('send-time', times);


                $('.send-day').append($("<option></option>")
                    .attr("value", 0).text('انتخاب زمان ارسال')
                    .addClass('active'));


                $('.send-time').append($("<option></option>")
                    .attr("value", 0).text('انتخاب زمان ارسال')
                    .addClass('active'));

                $('.send-time').val(0).trigger('change');
                $('.send-day').val(0).trigger('change');

                // res.data.dates.forEach(item => {
                //     let newD = new persianDate(item).format("D  MMMM YYYY");
                //     var newOption = new Option(newD, item, false, false);
                //     $('#dates').append(newOption).trigger('change');
                //
                // });
                //
                // // TIMES
                // res.data.times.forEach(item => {
                //     let newD = item
                //     var newOption = new Option(newD.name, newD.id, false, false);
                //     $('#time').append(newOption).trigger('change');
                //
                // })
            },
            complete: function () {
                $('.loading').addClass('hidden')
                showFactor();
                changeTimeSelect2();
            }
        })
    };

    let filterTimesAndShowAvailableTimes = function (times, time) {
        $('.send-time').empty();
        let d = time;
        let list = [];
        let listId = [];
        for (let i = 0; i < times.length; i++) {
            if (times[i].name.includes(d + '-')) {
                for (let j = i; j < times.length; j++) {
                    if (!listId.includes(times[j].id)) {
                        list.push({id: times[j].id, name: times[j].name})
                    }
                    listId.push(times[j].id)
                }
            }
        }
        // return list;

        return times;
    };
    let appendOptions = function (filterClass, list) {

        if (list.length != 0) {
            if (list != null && list[0].id) {

                for (let i = 0; i < list.length; i++) {
                    $('.' + filterClass).append($("<option></option>")
                        .attr("value", list[i].id).text(list[i].name));
                }
            } else {
                for (let i = 0; i < list.length; i++) {
                    $('.' + filterClass).append($("<option data-count='" + i + "'></option>")
                        .attr("value", list[i]).text(new persianDate(list[i]).format("D  MMMM YYYY")))
                }
            }
        } else {
            $('.' + filterClass).append($("<option></option>")
                .attr("value", 0).text('زمانی در دسترس نیست'))
        }
    }


    let changeTimeSelect2 = function () {
        $('.send-day').on('change', function () {
            if ($(this).find(':selected').data('count')){
                let times = filterTimesAndShowAvailableTimes(globalTime, 9);
                appendOptions('send-time', times);
            }else {
                let times = filterTimesAndShowAvailableTimes(globalTime,  new Date().getHours() + 1);
                appendOptions('send-time', times);
            }
        })
    }
    let sweetalert = function () {
        // validation
        // minPrice , time

        // if ($('.prg-percent-complete').hasClass('not-allow-for-min-price') ){
        //     // Error
        //     swal.fire({
        //         "title": " خطا",
        //         "text": 'حداقل سفارش از این تامیین‌کننده ۵۰,۰۰۰ تومان است',
        //         "type": "error",
        //         "confirmButtonClass": "",
        //         "confirmButtonText": '<a class="text-white" href="/">بازگشت به سایت و خرید بیشتر</a>'
        //     });
        // }
        //
        // else if ($('.send-time').val() == 0 || $('.send-day').val() == 0){
        //
        //     swal.fire({
        //         "title": " خطا",
        //         "text": 'زمان‌ و تاریخ ارسال را انتخاب‌ نمایید.',
        //         "type": "error",
        //         "confirmButtonClass": "btn btn-warning",
        //         "confirmButtonText": "تایید"
        //     });
        //
        // } else if (paymentMethod == 0){
        //
        //     swal.fire({
        //         "title": " خطا",
        //         "text": 'لطفا روش پرداخت را انتخاب‌ نمایید.',
        //         "type": "error",
        //         "confirmButtonClass": "btn btn-warning",
        //         "confirmButtonText": "تایید"
        //     });
        //
        // }
        if(true) {
            $('.order-confirm').append(
                function sweetalert() {
                    swal.fire({
                        title: '    تایید پرداخت ',
                        text: "آیا نسبت به پرداخت به روش " +
                            (paymentMethod == 1 ? 'اینترنتی' : 'غیر نقدی' )  +
                            " مطمئن هستید ؟",
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: 'primary',
                        confirmButtonText: 'تایید',
                        cancelButtonText: 'بازگشت',
                        reverseButtons: true
                    }).then((result) => {
                        if (result.value == true) {
                            sendDataForShowFactor();
                        } else if (
                            /* Read more about handling dismissals below */
                            result.dismiss === Swal.DismissReason.cancel
                        ) {
                        }
                    })
                }
            );
        }
    };

    function showIfMethod(link,payload) {
        if (link != null) {
            window.location = link;
            reloadCartList();

        } else {
            reloadCartList();
            window.location = res.payload;
        }
    }

    let sendDataForShowFactor = function () {

        // TODO FIX DATE AND TIME
        let addressId = 0
        $('.address-box').find('.user_address_id').each(function () {
            if ($(this).is('checked') || $(this).prop('checked')) {
                addressId = $(this).data('id');
            }
        });
        let data = {
            userDescription: $('#user-desc').val(),
            address: addressId,
            paymentMethod: paymentMethod,
            discount: $('#off-code').val(),
            date: $('.send-day').val(),
            time: $('.send-time').val()
        }

        $.ajax({
            'data': data,
            'url': "/api/cart/finalPayment",
            'method': "post",
            "beforsend": function () {
                $('.loading-btn-submit').removeClass('d-none');

                $("#confirm-code").prop('disabled', true);
                $(".btnResend").prop('disabled', true);
                $('.loading').removeClass('hidden');
            },
            "complete": function () {
                $('.loading-btn-submit').addClass('d-none');
                $('.loading-btn').addClass('d-none');

                $("#confirm-code").prop('disabled', false);
                $(".btnResend").prop('disabled', false);
                $('.loading').addClass('hidden');
            },
            "success": function (res) {
                if (res.result) {
                    let resultJson = JSON.parse(res.payload);
                    if (paymentMethod == 2){
                        swal.fire({
                            title: 'سفارش شما تکیمل شد.',
                            text: "" +
                                " کارشناسان ما طی ۴۸ ساعت آینده نسبت به تکمیل فرآیند خرید و نحوه پرداخت با شما ارتباط برقرار خواهند کرد." +
                                " توجه داشته باشید که اجناس فاکتور به قیمت روز محاسبه می‌گردد.",
                            icon: 'warning',
                            showCancelButton: false,
                            confirmButtonColor: 'primary',
                            confirmButtonText: 'تایید',
                            reverseButtons: true
                        }).then((result) => {
                            if (result.value == true) {
                                showIfMethod(resultJson.link,res.payload)
                            }
                        })
                    }else{showIfMethod(resultJson.link,res.payload)}

                } else {
                    swal.fire({
                        "title": " خطا",
                        "text": res.payload,
                        "type": "error",
                        "confirmButtonClass": "btn btn-warning",
                        "confirmButtonText": "تایید"
                    });

                }
                $('.loading-btn').addClass('d-none');


            },
            error: function () {
                $('.toast').removeClass('toast-success');
                $('.toast').addClass('toast-failed');
                $('.toast').removeClass('hidden');
                $('.toast-body').text("خطا در برقراری ارتباط");
                $('.toast').toast('show');
            }
        })
    };


    return {
        // public functions
        init: function () {
            submitOffCode();
            changeAddressRadio();
            selectPaymentMethod();
            addersList();
            setTimeAndDate();
        }
    };
}();

jQuery(document).ready(function () {
    productDetail.init();
});