let validator;

let logInPage = function () {


    let mb = 0;

    let toDataURL = function (url, callback) {
        var xhr = new XMLHttpRequest();
        xhr.onload = function () {
            var reader = new FileReader();
            reader.onloadend = function () {
                callback(reader.result);
            }
            reader.readAsDataURL(xhr.response);
        };
        xhr.open('GET', url);
        xhr.responseType = 'blob';
        xhr.send();
    };

    // init Auth
    let initAuth = function () {
        $('#captcha-image-signIn').append("<img style='height: 40px;!important;'  class='captcha captcha-login' >");
        $('#captcha-image-signUp').append("<img style='height: 40px;!important;'  class='captcha captcha-login' >");

        toDataURL('/api/auth/captcha', function (dataUrl) {
            $(".captcha-login").attr('src', dataUrl);
        });


        validator = $("#validation-log-in").validate(
            {
                ignore: ":hidden",
                rules: {
                    mobile: {
                        required: true,
                        regex: /^[\u0660|\u06F0][\u0660-\u0669\u06F0-\u06F9]{10}$|0[0-9]{10}$/,

                    },
                    captcha: {
                        required: true
                    }
                },
                message: {
                    mobile: {
                        required: "شماره همراه را انتخاب نمایید",
                        regex: "شماره همراه را درست وارد کنید."

                    },
                    captcha: {
                        required: "کد امنیتی را وارد نمایید"
                    }
                },
                // Display error
                invalidHandler: function (event, validator) {
                },

                // Submit valid form
                submitHandler: function (form) {

                    let dataAjax = {
                        mobile: convertPtoE($('#user-phone').val()),
                        captcha: convertPtoE($('#captcha').val())
                    };
                    mb = $('#user-phone').val();
                    $.ajax({
                        data: dataAjax,
                        url: "/api/auth/singIn",
                        type: "post",
                        "beforeSend": function () {
                            $("#submit-log-in").addClass('disable');
                            $('.loading').removeClass('hidden')
                        },
                        "complete": function () {
                            $("#submit-log-in").removeClass('disable');
                            $('.loading').addClass('hidden')
                        },
                        "success": function (res) {

                            if (res.result) {
                                // setTime();
                                $.ajax({
                                    url: '/getVerify/Content/true',
                                    type: 'post',
                                    success: function (res) {
                                        $('.log_in_content').empty();
                                        $('.log_in_content').append(res);
                                        initVerify($('#user-phone').val(), true);
                                        resendVCode();

                                    },
                                    complete: function () {
                                        goBackToLoginOrSingInPage();
                                    },
                                })

                            } else {
                                // swal.fire({
                                //     "title": "عملیات ناموفق!",
                                //     "text": res.payload,
                                //     "type": "error",
                                //     confirmButtonColor: 'primary',
                                //     "confirmButtonText": "تایید"
                                // });
                                reloadCaptcha();
                            }
                        },
                        error: function () {
                            swal.fire({
                                "title": "خطا در برقراری ارتباط!",
                                "text": 'عملیات ناموفق بود. لطفا دوباره تلاش کنید.',
                                "type": "error",
                                confirmButtonColor: 'primary',
                                "confirmButtonText": "تایید"
                            });
                            reloadCaptcha();
                        }
                    });
                }


            });
        $.validator.addMethod("regex", function (value, element, regexpr) {
            return regexpr.test(value);
        }, "");

        $.validator.addMethod("nationalCodeCheck", function (value, element) {
            if (value == '' || value == null) {
                return true;
            } else {
                var codeMelli = value.toString();
                switch (codeMelli) {
                    case '0000000000':
                    case '1111111111':
                    case '2222222222':
                    case '3333333333':
                    case '4444444444':
                    case '5555555555':
                    case '6666666666':
                    case '7777777777':
                    case '8888888888':
                    case '9999999999':
                        return false;
                }
                var c = parseInt(codeMelli.charAt(9));
                var sum = parseInt(codeMelli.charAt(0)) * 10 +
                    parseInt(codeMelli.charAt(1)) * 9 +
                    parseInt(codeMelli.charAt(2)) * 8 +
                    parseInt(codeMelli.charAt(3)) * 7 +
                    parseInt(codeMelli.charAt(4)) * 6 +
                    parseInt(codeMelli.charAt(5)) * 5 +
                    parseInt(codeMelli.charAt(6)) * 4 +
                    parseInt(codeMelli.charAt(7)) * 3 +
                    parseInt(codeMelli.charAt(8)) * 2;
                var r = sum - parseInt(sum / 11) * 11;
                return (r == 0 && r == c) || (r == 1 && c == 1) || (r > 1 && c == 11 - r);
            }
        }, "");


        validator = $("#validation-sign-up").validate(
            {
                ignore: ":hidden",
                rules: {
                    first_name: {
                        required: true,

                    },
                    nationalCode: {
                        required: true,
                        nationalCodeCheck: true,
                    },
                    last_name: {
                        required: true,

                    },
                    mobile_sign_up: {
                        required: true,
                        regex: /^[\u0660|\u06F0][\u0660-\u0669\u06F0-\u06F9]{10}$|0[0-9]{10}$/,

                    },
                    birth: {
                        required: false,
                    },
                    captcha: {
                        required: true
                    }
                },
                message: {
                    first_name: {
                        required: "نام را انتخاب نمایید",

                    },
                    nationalCode: {
                        required: "کد ملی نامعتبر",
                        nationalCodeCheck: "کد ملی نامعتبر",

                    },
                    last_name: {
                        required: "نام‌خانوادگی را انتخاب نمایید",

                    },
                    mobile_sign_up: {
                        required: "شماره همراه را انتخاب نمایید",
                        regex: "شماره همراه را درست وارد کنید.",

                    },
                    birth: {
                        required: "تاریخ تولد را وارد کنید را انتخاب نمایید",

                    },
                    captcha: {
                        required: "کد امنیتی را انتخاب نمایید"
                    }
                },
                // Display error
                invalidHandler: function (event, validator) {

                },

                // Submit valid form
                submitHandler: function (form) {

                    // if (!validationDate()) {
                    //     swal.fire({
                    //         "title": "تاریخ نا معتبر",
                    //         "text": "تاریخ تولد نادرست است.\n(مثال:1399/01/01)",
                    //         "type": "error",
                    //         confirmButtonColor: 'primary',
                    //         "confirmButtonText": "تایید"
                    //     });
                    //     return;
                    // }


                    let dataSignUp = {
                        first_name: $('#first_name').val(),
                        last_name: $('#last_name').val(),
                        gender: false,
                        captcha: '',
                        birth: '',
                        nationalCode: $('#nationalCode').val(),
                        mobile: convertPtoE($('#mobile_sign_up').val()),
                    };

                    $.ajax({
                        data: dataSignUp,
                        url: "/api/auth/singUp",
                        type: "post",
                        "beforeSend": function () {
                            $("#submit-log-up").addClass('disable');
                            $('.loading').removeClass('hidden')
                        },
                        "complete": function () {
                            $("#submit-log-up").removeClass('disable');
                            $('.loading').addClass('hidden');
                            $(document).on('keypress','.verifyCode', function (event) {
                                if (event.which === '13') {
                                    event.preventDefault();
                                }
                            });
                        },
                        "success": function (res) {
                            if (res.result) {
                                window.location = "/"
                            } else {
                                swal.fire({
                                    "title": "عملیات ناموفق!",
                                    "text": res.payload,
                                    "type": "error",
                                    confirmButtonColor: 'primary',
                                    "confirmButtonText": "تایید"
                                });
                                reloadCaptcha();
                            }

                        },
                        "error": function (res) {
                            swal.fire({
                                "title": "خطا در برقراری ارتباط!!",
                                "text": 'عملیات ناموفت بود. لطفا دوباره تلاش کنید.',
                                "type": "error",
                                confirmButtonColor: 'primary',
                                "confirmButtonText": "تایید"
                            });
                            reloadCaptcha();
                        }
                    });


                }


            });


        // validator = $("#validation-verify").validate(
        //     {
        //         ignore: ":hidden",
        //         rules: {
        //             verify_code: {
        //                 required: true,
        //             }
        //         },
        //         message: {
        //             'verifyCode': {
        //                 required: "کد تایید را انتخاب نمایید",
        //             }
        //         },
        //         // Display error
        //         invalidHandler: function (event, validator) {
        //         },
        //
        //         // Submit valid form
        //         submitHandler: function (form) {
        //             let dataAjax = {
        //                 mobile: convertPtoE($('#verify_mobile').val()),
        //                 verify_code: convertPtoE($('#verify_code').val())
        //             };
        //             console.log(dataAjax);
        //         }
        //
        //
        //     });
        $(document).on('keypress','.verifyCode', function (event) {
            if (event.which === '13') {
                event.preventDefault();
            }
        });

        $('.log_in_content').on('click', '#code-submit', function () {
            let dataAjax = {
                mobile: convertPtoE(mb),
                verify_code: convertPtoE($('#verifyCode').val())
            };
            $.ajax({
                data: dataAjax,
                url: "/api/auth/verify",
                type: "post",
                "beforeSend": function () {
                },
                "complete": function () {
                },
                "success": function (res) {
                    console.log(res);
                    if (res.value != null) {
                        console.log(res);
                        window.location.replace(res.value + '?mobile=' + res.name);
                    }
                    else {
                        window.location.replace(res)
                    }
                }, error: function (res) {
                    swal.fire({
                        "title": "ناموفق!",
                        "text": ' کد تایید نامعتبر است. ',
                        "type": "error",
                        confirmButtonColor: 'primary',
                        "confirmButtonText": "تایید"
                    });
                }
            });

        })
    };

    // init reload captcha bottom
    let initReloadBtnCaptcha = function () {

        $('.btn-reloadCaptcha').on('click', function () {

            reloadCaptcha();

        })
    };

    let reloadCaptcha = function () {
        toDataURL('/api/auth/captcha', function (dataUrl) {
            $(".captcha-login").attr('src', dataUrl);
        });
    };

    function resendVCode() {
        $(".btnResend, .resendCodeAgain").click(function () {
            let data = {
                mobile: mb
            }
            console.log("data" , data)
            $.ajax({
                data: data,
                url: "/api/auth/resendCode",
                type: 'POST',
                "beforeSend": function () {
                    $("#confirm-code").prop('disabled', true);
                    $("#resend").prop('disabled', true);
                    $('.loading').removeClass('hidden');
                    $('.resendCodeAgain').addClass('d-none');
                    $('#new-code').removeClass('d-none');
                    $('.resendCodeAgain').attr('disabled', true);
                    $(".counter_time").text('02:00');
                    // startTimerFunction();
                },
                "complete": function () {
                    $("#confirm-code").prop('disabled', false);
                    $("#resend").prop('disabled', false);
                    $('.loading').addClass('hidden')

                },
                "success": function () {
                    $("#confirm-code").prop('disabled', false);
                    $(".btnResend").css("display", "none");

                    $(".counter_time").text('02:00');
                    startTimerFunction();
                },
                error: function (request) {
                    //todo error
                    $('.btn-reloadCaptcha').click();
                    $(".counter_time").text('02:00');
                    startTimerFunction();
                    swal.fire({
                        "title": "ناموفق!",
                        "text": 'خطا در برقراری ارتباط!',
                        "type": "error",
                        confirmButtonColor: 'primary',
                        "confirmButtonText": "تایید"
                    });
                }
            });
        });
    }

    let changeMobileNumber = function () {
        $("#back").on('click', function () {
        });

    };

    function startTimer(duration, display) {
        var timer = duration, minutes, seconds;
        let timerr = setInterval(function () {
            minutes = parseInt(timer / 60, 10)
            seconds = parseInt(timer % 60, 10);

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            display.textContent = minutes + ":" + seconds;

            if (--timer < 0) {
                timer = duration;
                clearInterval(timerr);
                $('.resendCodeAgain').removeClass('d-none');
                $('.resendCodeAgain').attr('disabled', false);
                $('#new-code').addClass('d-none');

            }
        }, 1000);
    }

    let startTimerFunction = function () {

        var fiveMinutes = 60 * 2,
            display = document.querySelector('#time');
        startTimer(fiveMinutes, display);
    };

    /*
    let checkNumber = function() {
        $(document).on("keydown", '.numberControl', function (e) {
            let textNum = changeTextToPersian($(this).val());
            $(this).val(textNum);
        })
    };*/
    function changeTextToPersian(text) {
        let count = 0;
        let values = '';
        let ch = 0;
        let perKey = ['۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
        let perVal = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
        for (let i = 0; i < text.length; i++) {
            for (ch = 0; ch < perKey.length; ch++) {
                if (text[i] == perKey[ch]) {
                    count++;
                    values += perVal[ch];
                    break;
                }
            }
            if (perKey[ch] != text[i]) {
                return values;
            }
        }
        return values;
    }

    // init verify div and set visibility block
    let initVerify = function (mobile, isLogin) {

        $('#verify_mobile').val(mobile);
        $('#verify_code').val('');

    };

    // init verify div and set visibility block
    let refill = function () {
        // $(isLogin === true ? '#sign-up-div' : '#sign-in-div').addClass('hidden');

    };

    let validationDate = function () {
        let dateVal = $('#birth').val();
        if (dateVal == 'undefine' || dateVal == "" || dateVal == null || dateVal.length == 0) {
            return true;
        } else if (dateVal != 'undefine' && dateVal != null && dateVal.length > 0 && dateVal.length < 14) {
            let dateSplited = dateVal.split('/');
            if (dateSplited.length == 3) {
                let y = dateSplited[0];
                let m = dateSplited[1];
                let d = dateSplited[2];

                if (isNumeric(y) && isNumeric(m) && isNumeric(d)) {
                    y = parseInt(y);
                    m = parseInt(m);
                    d = parseInt(d);

                    if (y < 1300 || y > 1403 || m < 1 || m > 12 || d < 1 || d > 31) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }

    };

    function isNumeric(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

    function inputEnterHandler() {

        var captchaLoginInput = document.getElementById("captcha");
        var captchaSigningInput = document.getElementById("captcha_sign_up");
        var verifyCodeInput = document.getElementById("verifyCode");

        if (captchaLoginInput != undefined && captchaLoginInput != null) {
            captchaLoginInput.addEventListener("keyup", function (event) {
                if (event.keyCode === 13) {
                    // Cancel the default action, if needed
                    event.preventDefault();
                    // Trigger the button element with a click
                    document.getElementById("login-submit").click();
                }
            });
        }

        if (captchaSigningInput != undefined && captchaSigningInput != null) {
            captchaSigningInput.addEventListener("keyup", function (event) {
                if (event.keyCode === 13) {
                    // Cancel the default action, if needed
                    event.preventDefault();
                    // Trigger the button element with a click
                    document.getElementById("submit-log-up").click();
                }
            });
        }


        if (verifyCodeInput != undefined && verifyCodeInput != null) {
            verifyCodeInput.addEventListener("keyup", function (event) {
                // Number 13 is the "Enter" key on the keyboard
                if (event.keyCode === 13) {
                    // Cancel the default action, if needed
                    event.preventDefault();
                    // Trigger the button element with a click
                    document.getElementById("submit-verify").click();
                }
            });
        }

    }



    let goBackToLoginOrSingInPage = function(){

        console.log("inited");
        $('.log_in_content').on('click','.go_back_to', function () {

            console.log("go_back_to");
            if ($(this).data('isfromlogin')){
                isLogInOrSignUpFirst(1);
            }else {
                isLogInOrSignUpFirst(0);

            }
        })
    }

    let isLogInOrSignUpFirst = function (isLogin , mobile) {
        console.log(isLogin);
        if (isLogin == 1) {

            $.ajax({
                url: '/getLogIn/Content',
                type: 'post',
                success: function (res) {
                    $('.log_in_content').empty();
                    $('.log_in_content').append(res);
                    initAuth();
                    changeMobileNumber();
                    initReloadBtnCaptcha();
                    inputEnterHandler();
                }
            })
        } else {
            $.ajax({
                url: '/getSignUp/Content?mobile=' + mobile,
                type: 'post',
                success: function (res) {
                    $('.log_in_content').empty();
                    $('.log_in_content').append(res);
                    initAuth();
                    changeMobileNumber();
                    initReloadBtnCaptcha();
                    inputEnterHandler();
                }
            })
        }
    }

    let changeLogInOrSignUpFirstContent = function () {
        $('.log_in_content').on('click', '.change_log_in_content', function () {
            isLogInOrSignUpFirst($(this).data('is-login'));
        })
    }
    return {
        // public functions
        init: function () {
            changeLogInOrSignUpFirstContent();

            console.log($('#isLogIn').val());
            isLogInOrSignUpFirst($('#isLogIn').val() !== "false" ? 1 : 0, $('.mobile_content').val());
            // reloadCaptcha();

            // refill();
            //function por kardan vorod (remember)
        }
    };
}
();
jQuery(document).ready(function () {
    logInPage.init()
});
