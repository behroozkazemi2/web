let caroselItem = function (res) {
    let i = 0;
    res.forEach(item => {
        let newDiv = '' +
            '  <article  class="single_product go-detail" data-pro="' + item.id + '">' +
            '                                        <figure>' +
            '                                             <div id="text-not' + i + '" class="setText" style="display: none">  اتمام موجودی </div>' +
            '                                            <div id="cart-list-all' + i + '" class="single_product setTextOnImage">' +
            '                                            <div class="product_thumb" href="/p/' + item.id + '">' +
            '                                                <a class="primary_img"  href="/p/' + item.id + '"><img alt=" بهتاتهویه" class="nullImage' + item.id + '" src=' + returnImageUrl(item.images) + ' ></a>' +
            '                                                <a class="secondary_img"  href="/p/' + item.id + '"><img alt=" بهتاتهویه" src=' + returnImageUrl(item.images) + '></a>' +
            '                                                <div class="label_product">' +
            '                                                   <span '+(item.offPercent>0?'':'hidden')+'class=" label_sale old_price dis-' + item.id + '" style=" margin-right: 200px;background-color: #FC8A35">%' + item.offPercent + '</span>' +
            '                                                   <span class="label_sale" style="width: auto;padding-left: 5px;padding-right: 5px;">' + item.category.name + '</span>' +
            '                                                </div>' +
            '                                                <div class="action_links">' +
            '                                                    <ul>' +
            '                                                     <li   data-index="' + i + '" data-available="' + item.existence + '" data-detail="' + item.id + '"   id="cart ' + item.id + ' " class="quick_button cart-open-modal"><a title="مشاهده سریع"> <span class="lnr lnr-magnifier moshahede"></span></a></li>' +
            '                                                </div>' +
            '                                            </div>' +
            '                                            <figcaption class="product_content">' +
            '                                                <h4 class="product_name"><a href="/p/' + item.id + '">' + item.name + '</a></h4>' +
            '                                                <h4 class="provider_name"><a href="/p/' + item.id + '">' + item.provider.name + '</a></h4>' +
            '                                                <p><a href="/p/' + item.id + '">' + moreFormat1(item.shortDescription, 25) + '</a></p>' +
            '                                                 <div class="price_box rtl">' +
            '                                                    <span class="current_price">' +
            '                                                           <span class=" smfont-size text-dark" > هر ' + item.unit.name + '</span>' +
            '                                                           <span style="font-size: 16px ; color: #000;"> ' + formatDollar((item.primitiveAmount * (100 - item.offPercent)) / 100) + ' </span> ' +
            '                                                       <span '+(item.offPrice >0 ?'':'hidden')+' class="old_price dis-' + item.id + '">' +
            '                                                           <span class="black-color" style="font-size: 15px"></span>' +
            '                                                           <span class="black-color"style="font-size: 15px"></span>' +
            '                                                           <span style="font-size: 15px"> ' + formatDollar(item.primitiveAmount) + '</span>' +
            '                                                       </span>' +

            '                                                    </span>' +
            '                                                   <span  '+(item.offPrice >0 ?'':'hidden')+' class="black-color" style="font-size: 15px ;"> ' + 'تومان' + ' </span> ' +
            '                                                 </div>' +
            '                                            </figcaption>' +
            '                                           </div>' +
            '                                   </figure>' +
            '  </article>';
        let disId = 'dis-' + item.id;
        if (item.offPercent == 0) {
            $('.' + disId + '').addClass('d-none')
        } else {
            $('.' + disId + '').removeClass('d-none')

        }

        if (item.existence == false) {
            // KAMRANG KARDAN
            $('#cart-list-all' + i + '').addClass('notForSell');
            $('#cart-list-all' + i + '').css("cursor", "defult");
            // ETMAM MOJODI TEXT
            $('#text-not' + i + '').removeClass('notForSell');
            $('#text-not' + i + '').css("display", "inline");
            // action Links
            $('#action' + i + '').addClass('d-none')
        }
        i++;
        $('#hassan').append(newDiv);
        let nullImage = 'nullImage' + item.id;


    });
};

let returnImageUrl = function (images) {
    if (images != null) {
        return '/thumbnail/files/0/' + images[0] + '';
    }
    return '/assets/img/logo.png';
};

let goDetailPage = function () {
    $(document).on('click', '.go-detail', function () {
        let url = "";
        url = url + "/p/" + $(this).data('pro');
        window.location = url;
    })

}
