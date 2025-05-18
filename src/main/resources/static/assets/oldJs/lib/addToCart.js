let chageCount=function (proProId, val,description,sub) {
    let data = {
        pId: proProId,
        v: val,
        d: description,
        sF:sub,
    };
    $.ajax({
        "data": data,
        "url": "/api/product/addToCart",
        "method": "post",
        "success": function (res) {
            $('#show-error').text('');
            $('#show-error').css('color','white');
            $('#show-error').css('display','inline-block');

            if (res == null || res.result + '' === 'false') {
                $('#show-error').css('color','red');
                $('#show-error').text( res.payload !== '' ?  res.payload:  'محصول به سبد خرید اضافه نشد.');
                $('#show-error').delay(2000).hide(0);


            } else {
                swal.fire({
                    "title": '',
                    "text": 'محصول با موفقیت به سبد خرید اضافه شد.',
                    "type": 'success',
                    "confirmButtonClass": "btn btn-secondary",
                    "confirmButtonText": "تایید"
                })
                $('#show-error').css('color','#40A944');
                $('#show-error').css('font-size','20px');
                $('#show-error').text('محصول به سبد خرید اضافه شد.');
                $('#show-error').delay(2000).hide(0);

                reloadCartList();
            }
        }


    })
}