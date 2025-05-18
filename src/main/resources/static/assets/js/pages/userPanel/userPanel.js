let userPanel = function () {


    let addAddressFlag = 0;


    const navITem = {
        DASHBOARD: {value: 0, name: "dashboard", class: "dashboard_class"},
        ADDRESSES: {value: 1, name: "addresses", class: "addresses_class"},
        INTERESTS: {value: 2, name: "interests", class: "interests_class"},
        OFFCODE: {value: 3, name: "offCode", class: "offCode_class"},
        ORDERDETAILS: {value: 4, name: "orderDetail", class: "orderDetails_class"},
        ORDERS: {value: 5, name: "orders", class: "orders_class"},
        USERINFO: {value: 6, name: "userInfo", class: "userInfo_class"},
        CHARGEWALLET: {value: 8, name: "walletCharge", class: "chargeWallet_class"},
        TICKET: {value: 7, name: "ticket", class: "userTicket_class"},
        LOGOUT: {value: 9, name: "logOut", class: "logOut_class"},
    }
    let changeNav = function () {
        $('.nav_items').on('click', function () {
            let navId = 0;
            let url = '/UserPanel/';
            let activeClassNavbar = '';
            $('.nav_items').removeClass('menu-active');
            switch ($(this).data('nav-id')) {
                case navITem.DASHBOARD.value :
                    url += navITem.DASHBOARD.name;
                    navId = navITem.DASHBOARD.value;

                    activeClassNavbar = navITem.DASHBOARD.class
                    $('.' + activeClassNavbar + '').addClass('menu-active');
                    break;
                case navITem.ADDRESSES.value :
                    url += navITem.ADDRESSES.name;
                    navId = navITem.ADDRESSES.value;

                    activeClassNavbar = navITem.ADDRESSES.class
                    $('.' + activeClassNavbar + '').addClass('menu-active');
                    break;
                case navITem.INTERESTS.value :
                    url += navITem.INTERESTS.name;
                    navId = navITem.INTERESTS.value;

                    activeClassNavbar = navITem.INTERESTS.class
                    $('.' + activeClassNavbar + '').addClass('menu-active');
                    break;
                case navITem.OFFCODE.value :
                    url += navITem.OFFCODE.name;
                    navId = navITem.OFFCODE.value;

                    activeClassNavbar = navITem.OFFCODE.class
                    $('.' + activeClassNavbar + '').addClass('menu-active');
                    break;
                case navITem.ORDERS.value :
                    url += navITem.ORDERS.name;
                    navId = navITem.ORDERS.value;

                    activeClassNavbar = navITem.ORDERS.class
                    $('.' + activeClassNavbar + '').addClass('menu-active');
                    break;
                case navITem.USERINFO.value :
                    url += navITem.USERINFO.name;
                    navId = navITem.USERINFO.value;

                    activeClassNavbar = navITem.USERINFO.class
                    $('.' + activeClassNavbar + '').addClass('menu-active');
                    break;

                case navITem.TICKET.value :
                    url += navITem.TICKET.name;
                    navId = navITem.TICKET.value;

                    activeClassNavbar = navITem.TICKET.class
                    $('.' + activeClassNavbar + '').addClass('menu-active')
                    break;
                case navITem.LOGOUT.value :
                    url += navITem.LOGOUT.name;
                    navId = navITem.LOGOUT.value;
                    break;
                case navITem.CHARGEWALLET.value :
                    url += navITem.CHARGEWALLET.name;
                    navId = navITem.CHARGEWALLET.value;
                    break;

            }

            appendNavBody(url, navId);
        });
    }


    // DashboardNav

    let dashboardButtonClickAction = function () {
        $('#order-list').on('click', function () {
            $('.orders_class').click();
        });
        $('#user-edit').on('click', function () {
            $('.userInfo_class').click();
        });


    }


    // ORDER NAv
    let orderFunction = function (factorType) {
        let data = {
            draw: 100,
            start: 0,
            length: 100,
            factorType: factorType,
        };
        $.ajax({
            "data": data,
            "url": "/api/user/order",
            "method": "post",
            "beforeSend": function () {
                $('.loading-box').removeClass('d-none');

            },
            "complete": function () {
                $('.loading-box').addClass('d-none');

            },
            "success": function (res) {

                $('.order-box').empty();
                $('.order-box').append(res);
                showOrderDetail();
            },
            error: function () {
            }
        });
    };

    let changeFactorType = function(){
        $('#nav_body').on('click', '.factor_type', function () {
            $('#nav_body').find('.factor_type').removeClass('active');
            $(this).addClass('active');
            orderFunction($(this).data('val'));
        });
    }

    let showOrderDetail = function () {
        $('.go-to-order-details').on('click', function () {
            let lodingBtn = $(this).find('.loading-btn')
            let angel = $(this).find('.fa-angle-left')
            orderInfo($(this).data('id'), lodingBtn, angel);
        })
    }
    let orderInfo = function (id, lodingBtn, angel) {
        let data = {
            id: id
        };
        $.ajax({
            "data": data,
            "url": "/api/user/orderInfo",
            "method": "post",
            "beforeSend": function () {
                $(lodingBtn).removeClass('d-none');
                $(angel).addClass('d-none');
            },
            "complete": function () {
                $(lodingBtn).addClass('d-none');
                $(angel).removeClass('d-none');

                orderAgainFunction();
            },
            "success": function (res) {
                $('#nav_body').empty();
                $('#nav_body').append(res);
            },
            error: function () {
                swal.fire({
                    "title": " خطا",
                    "text": "خطا در بر قراری ارتباط با سرور",
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                    "confirmButtonText": "تایید"
                });
                $('.toast').removeClass('toast-success');
                $('.toast').addClass('toast-failed');
                $('.toast').removeClass('hidden');
                $('.toast-body').text("خطا در برقراری ارتباط");
                $('.toast').toast('show');
            }
        });
    }
    let orderAgainFunction = function () {
        $('.orderAgain').on('click', function () {
            let id = $(this).data('pp-id');
            let name = $(this).data('name');
            let productName = removeHalfSpace(name);
            window.location = '/product/' + id + '/' + productName;
        });
    }

    // offCodeNav
    let discountList = function () {
        let data = {
            draw: 12,
            start: 0,
            length: 100
        };
        $.ajax({
            "data": data,
            "url": "/api/user/discount",
            "method": "post",
            "beforeSend": function () {
                $('.loading-box').removeClass('d-none');

                $('.loading').removeClass('hidden')
            },
            "complete": function () {
                $('.loading-box').addClass('d-none');

                $('.loading').addClass('hidden');
                copyToClipboard();
            },
            "success": function (res) {
                $('.off-box').empty();
                $('.off-box').append(res);
            },
            error: function () {
            }
        });
    };

    let copyToClipboard = function () {
        $('.copy_value_to_clip_board').on('click', function () {
            let copyText = $($(this).closest('div').find('.copy_value_to_clip_board_value')).text()
            navigator.clipboard.writeText(copyText);
            Command: toastr["success"]("کد‌تخفیف کپی گردید. ")
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
        });
    }
    // navAddress
    let addersList = function () {

        $.ajax({
            url: '/api/address/getList',
            method: 'post',
            beforeSend: function () {
                $('.loading-box').removeClass('d-none');

            },
            success: function (res) {
                $('.address-box').empty();
                $('.address-box').append(res);
            },
            complete: function () {
                addOrEditAddress();
                deleteAddressButton();
                $('.loading-box').addClass('d-none');

                $('.open_new_address_form_mobile').on('click', function () {

                    console.log("Cliked");
                    document.getElementById("new-address-mobile").style.width = "100%";

                    setTimeout(() => {
                        // oMap.updateSize();
                        initAddressMap('kt_location_mobile')
                    }, 250);

                })

            },
            error: function () {
            }
        });

    };


    let addOrEditAddress = function () {
        $('#new-address').on('click', function () {
            getAddressData(0)
        });
        $('.edit-address').on('click', function () {
            let id = $(this).data('id');
            getAddressData(id)
        })
    };
    let deleteAddressButton = function () {
        $('.delete-address').on('click', function () {
            let id = $(this).data('id');
            deleteAddress(id);
        })
    };
    let deleteAddress = function (id) {
        let data = {
            id: id,
        };
        swal.fire({
            text: "آیا نسبت به حذف آدرس اطمینان دارید؟",
            type: 'question',
            showCancelButton: true,
            confirmButtonColor: 'primary',
            confirmButtonText: 'بله',
            cancelButtonText: 'خیر',
            cancelButtonColor: 'btn-warning',

            // reverseButtons: true
        }).then(function (result) {
            if (result.value) {
                $.ajax({
                    "data": data,
                    "url": "/api/user/address/delete",
                    "method": "post",
                    "beforeSend": function () {
                        $('.loading').removeClass('hidden')
                    },
                    "complete": function () {
                        $('.loading').addClass('hidden')
                    },
                    "success": function () {
                        addersList();
                        getAddressFromSession();
                    },
                    "error": function (request) {
                        //todo error
                        showErrorAnotherModel(request);
                    }
                })
            } else if (result.dismiss === 'cancel') {
            }
        })
    };
    let getAddressData = function (id) {
        $.ajax({
            url: '/api/address/getModalAddress/' + id,
            method: 'post',
            success: function (res) {
                $('#kt_modal_remote').find('.modal-content').empty();
                $('#kt_modal_remote').find('.modal-content').append(res);
                $('#kt_modal_remote').modal('show');
                addressModalShownOrHidden();


            },
            complete: function () {
            }
        })
    }
    let addressModalShownOrHidden = function () {
        $('#kt_modal_remote').on('shown.bs.modal', function () {
            addAddress();
            addAddressFlag = 0;
            $('#kt_location').empty();
            let locInput = $('#kt_modal_remote').find('.latlon');
            initAddressMap('kt_location', $(locInput).data('lat'), $(locInput).data('lon'));
            addressMarkerLat = $(locInput).data('lat');
            addressMarkerLon = $(locInput).data('lon');
        });
        $('#kt_modal_remote').on('hidden.bs.modal', function () {
            $(this).data('bs.modal', null);
            $(this).data('bs.modal', null);
            $('#kt_modal_remote').find('#ratio-edit').val('');
            $('#kt_modal_remote').data('modal', null);
        });


    };
    let addAddressFromMobileResponse = function () {
        $('.new-address-mobile').on('click', '.address-confirm-mobile', function () {
            if ($('.address-field_mobile').val() == "" && $('.address-field_mobile').val() == null) {
                swal.fire({
                    "title": " خطا",
                    "text": "آدرس خود را وارد نمایید ",
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                    "confirmButtonText": "تایید"
                });
            } else {
                let address = $('.address-field_mobile').val();
                // $('#address-field').text("");
                // $('#address-field').val("");
                if (addressMarkerLat == null || addressMarkerLon == null || addressMarkerLat == "" || addressMarkerLon == "") {
                    swal.fire({
                        "text": 'لطفا موقعیت خود را از روی نقشه انتخاب نمایید.',
                        "type": "error",
                        "confirmButtonClass": "btn btn-warning",
                        "confirmButtonText": "تایید"
                    });
                }
                if ($('.address_title_mobile').val() == null || $('.address_title_mobile').val() == "") {
                    swal.fire({
                        "text": 'لطفا عنوان آدرس را وارد نمایید.',
                        "type": "error",
                        "confirmButtonClass": "btn btn-warning",
                        "confirmButtonText": "تایید"
                    });
                }


                let data = {
                    id: 0,
                    address: address,
                    province: $($('.new-address-mobile').find('#state-field')).val(),
                    city: $($('.new-address-mobile').find('#city-field')).val(),
                    title: $('.address_title_mobile').val(),
                    postalCode: $($('#kt_modal_remote').find('#postalCode')).val(),
                    // productRecipientPhone: productRecipientPhone,
                    lat: addressMarkerLat,
                    lng: addressMarkerLon
                };

                addAddressAction(data, true);

            }
        })
    }
    let addAddress = function () {
        $('#confirm-location').on('click', function () {
            if ($('#kt_modal_remote').find('.address-field').val() == "" || $('#kt_modal_remote').find('.address-field').val() == null) {
                swal.fire({
                    "title": " خطا",
                    "text": "آدرس خود را وارد نمایید ",
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                    "confirmButtonText": "تایید"
                });

            }else if ($('#kt_modal_remote').find('#postalCode').val() == "" || $('#kt_modal_remote').find('#postalCode').val() == null) {
                swal.fire({
                    "title": " خطا",
                    "text": "کدپستی خود را وارد نمایید ",
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                    "confirmButtonText": "تایید"
                });

            }else if($('#kt_modal_remote').find('#city-field').val() == "" || $('#kt_modal_remote').find('#city-field').val() == null) {
                swal.fire({
                    "title": " خطا",
                    "text": "نام شهر خود را وارد نمایید ",
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                    "confirmButtonText": "تایید"
                });
            }else if (addressMarkerLat == null || addressMarkerLon == null || addressMarkerLat == "" || addressMarkerLon == "") {
                swal.fire({
                    "text": 'لطفا موقعیت خود را از روی نقشه انتخاب نمایید.',
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                    "confirmButtonText": "تایید"
                });
            }else if($('#kt_modal_remote').find('#state-field').val() == null || $('#kt_modal_remote').find('#state-field').val() == "") {
                swal.fire({
                    "text": 'لطفا استان آدرس را وارد نمایید.',
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                    "confirmButtonText": "تایید"
                });
            }else if($('#kt_modal_remote').find('#address_title').val() == null || $('#kt_modal_remote').find('#address_title').val() == "") {
                swal.fire({
                    "text": 'لطفا عنوان آدرس را وارد نمایید.',
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                    "confirmButtonText": "تایید"
                });
            }
            else {
                $('.empty-address').addClass('d-none');
                let address = $('.address-field').val();
                // $('#address-field').text("");
                // $('#address-field').val("");



                let data = {
                    id: Number($('#kt_modal_remote').find('#address_id').val() ?? 0),
                    address: address,
                    title: $('#kt_modal_remote').find('.address_title').val(),
                    province: $($('#kt_modal_remote').find('#state-field')).val(),
                    city: $($('#kt_modal_remote').find('#city-field')).val(),
                    postalCode: $($('#kt_modal_remote').find('#postalCode')).val(),
                    // productRecipientPhone: productRecipientPhone,
                    lat: addressMarkerLat,
                    lng: addressMarkerLon
                };

                if (addAddressFlag == 0) {
                    addAddressFlag++;
                    addAddressAction(data);
                }

            }
        })
    };
    let addAddressAction = function (data, isFromMobileResponse) {
        $.ajax({
            "data": data,
            "url": "/api/address/addOrEdit",
            "method": "post",
            "beforeSend": function () {
            },
            "complete": function () {
                addAddressFlag = 0;
            },
            "success": function (res) {
                if (res.result) {

                    if (isFromMobileResponse) {
                        window.location = "/UserPanel?type=1"
                    }
                    $('#kt_modal_remote').modal('toggle');
                    $('#locationModal').modal('toggle');
                    addersList();
                    headerAddersList();
                    getAddressFromSession(JSON.parse(res.payload).id);
                } else {
                    swal.fire({
                        "text": res.payload,
                        "type": "error",
                        "confirmButtonClass": "btn btn-warning",
                        "confirmButtonText": "تایید"
                    });
                }
            },
            error: function () {
                addAddressFlag = 0;
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


    let requestForChangAddress = function (addressId) {

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
                }
                getAddressFromSession(addressId);
            },
            error: function () {
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


    // UserInfoEdit
    let editUserInfo = function () {
        $("#user-submit-info").click(function () {
            let firstname = $("#first-name").val();
            let lastname = $("#last-name").val();
            // let mobile = $("#user-mobile").val();
            if (firstname == "" || lastname == "") {
                swal.fire({
                    "title": "خطا",
                    "text": (firstname ? 'لطفا نام خانوادگی خود را وارد کنید' : 'لطفا نام  خود را وارد کنید'),
                    "type": "error",
                    "confirmButtonClass": "btn btn-warning",
                });
            } else {

                let data = {
                    firstname: firstname,
                    lastname: lastname,
                };

                $.ajax({
                    "data": data,
                    "url": "/api/user/edit",
                    "method": "post",
                    "beforeSend": function () {
                        $('.loading').removeClass('hidden')
                    },
                    "complete": function () {
                        $('.loading').addClass('hidden')
                    },
                    "success": function (request) {
                        if (request.result) {
                            $('.costumer_name_last').text(request.payload);
                            Command: toastr["success"]("اطلاعات‌‌کاربری با موفقیت ثبت گردید")
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
                        } else {
                            Command: toastr["error"]("خطا در تغییر اطلاعات‌‌کاربری")
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
                        }
                    },
                    "error": function (request) {
                        //todo error
                        showError(request);
                    }

                })
            }
        });
    }


    // CHARGE WALLET
    let changeChargeAmount = function () {
        $("#nav_body").on('keyup', '#amount', function () {

            $(this).val(number_format(Number($(this).val().replace(/[^0-9.-]+/g, ""))));
        });
    };
    let walletCharge = function () {
        $("#nav_body").on('click', '#charge_wallet', function () {
            let amount = (Number($("#nav_body").find("#amount").val().replace(/[^0-9.-]+/g, "")));
            let data =
                {
                    amount: amount
                };
            $.ajax({
                "data": data,
                "method": "post",
                "url": "/api/user/walletCharge",
                "beforeSend": function () {
                    $('.loading').removeClass('hidden')
                },
                "complete": function () {
                    $('.loading').addClass('hidden')
                },
                "success": function (res) {
                    $('.cal-error').empty();
                    window.location.replace(res);
                },
                "error": function (request) {
                    //todo error
                    swal.fire({
                        "title": " خطا",
                        "text": "خطا در بر قراری ارتباط با سرور",
                        "type": "error",
                        "confirmButtonClass": "btn btn-warning",
                        "confirmButtonText": "تایید"
                    });
                }
            })
        });
    };

    let number_format = function (total) {
        return total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };


    let appendNavBody = function (url, navId) {
        $.ajax({
            url: url,
            method: 'post',
            beforeSend: function () {
            },
            success: function (res) {
                $('#nav_body').empty();
                $('#nav_body').append(res);
            },
            complete: function () {

                switch (navId) {
                    case navITem.DASHBOARD.value :
                        showOrderDetail();
                        dashboardButtonClickAction();
                        break;
                    case navITem.ADDRESSES.value :
                        addersList();
                        // changeAddress();
                        addAddressFromMobileResponse();
                        break;
                    case navITem.INTERESTS.value :

                        break;
                    case navITem.OFFCODE.value :
                        discountList();
                        break;
                    case navITem.ORDERS.value :
                        orderFunction(0);
                        changeFactorType();
                        break;
                    case navITem.USERINFO.value :
                        editUserInfo();
                        break;
                    case navITem.LOGOUT.value :
                        break;
                    case navITem.CHARGEWALLET.value :
                        walletCharge();
                        changeChargeAmount()
                        break;
                    case navITem.TICKET.value :
                        ticketJs.init();
                        break;

                }


            },
            error: function () {
            }
        });
    }
    let firstTimeInitNavs = function () {
        switch (Number($('.nav_type').val())) {
            case 0 :
                appendNavBody('/UserPanel/dashboard', 0);
                break
            case 1:
                appendNavBody('/UserPanel/addresses', 1);
                break
            case 3:
                appendNavBody('/UserPanel/offCode', 1);
                break
            case 6:
                appendNavBody('/UserPanel/userInfo', 1);
                break
            case 5:
                appendNavBody('/UserPanel/orders', 5);
                break
            case 7:
                appendNavBody('/UserPanel/ticket', 7);
                break
            case 8:
                appendNavBody('/UserPanel/walletCharge', 8);
                break


        }
        // appendNavBody('/UserPanel/dashboard',0);
    }


    let isChargingWalletSuccessful = function () {
        if ($('.isFromWalletCharge').val() === "true") {
            if ($('.isFromWalletCharge').data('type') === "true") {
                Command: toastr["error"]("خطا در شارژ حساب‌‌کاربری")
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
            } else {
                Command: toastr["success"]("حساب‌کاربری با موفقیت شارژ گردید")
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
            }
        }
    }
    return {
        // public functions
        init: function () {
            changeNav();
            firstTimeInitNavs();
            isChargingWalletSuccessful();

        }
    };
}();

jQuery(document).ready(function () {
    userPanel.init();
});