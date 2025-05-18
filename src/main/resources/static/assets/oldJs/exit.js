function exit() {
    $('#u-sure-about-this').css("display", "inline");
    $('#u-sure-about-this').empty();
    $('#u-sure-about-this').append(
        function exite() {

            swal.fire({
                title: 'خروج از حساب کاربری',
                text: "مایل به خروج از حساب کاربری خود هستید ؟",
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: 'primary',
                confirmButtonText: 'تایید',
                cancelButtonText: 'بازگشت',
                reverseButtons: true
            }).then((result) => {
                if (result.value == true) {
                    $.ajax({
                        "data": null,
                        "url": "/api/auth/logout",
                        "method": "post",
                        success: function () {
                            let url = '/';
                            window.location = url;
                        },
                        error: function () {
                            swal.fire({
                                "title": "خطا در برقراری ارتباط!!",
                                "text": 'عملیات ناموفت بود. لطفا دوباره تلاش کنید.',
                                "type": "error",
                                "confirmButtonClass": "btn btn-warning",
                                "confirmButtonText": "تایید"
                            });
                        }
                    });
                }
})
}
    )
}
