let shopPage = function () {
    //NAV BAR
    let region = [parseInt($('#searched-region').val())];
    let providerCategory = [($('#param').data('procategory'))];
    let providerId = [$('#param').data('proid')];
    let search = $('#param').data('search');
    let orderBarFliter = 3;

    let maxPriceFliter = 1000000;          //set default max
    let minPriceFliter = 0;          //set default min
    let page = 1;
    let tag = [];  //set tag
    let globalItem;
    let setForPage = 1;
    let conditionInpage = 5;
    let pageMin = 1;
    let pageMax;

    let commentPage = 0;
    let commentPageSize = 10;
    let commentPageLoading = false;
    let commentRateStar = 5;


    let lastRgPosition = $('#region' + region + '');


    let categories;
    let providers;
    let selectedRegionCategory;
    let primitiveAmount;
    let requestTimeOut = null;
    let finalAmount;
//FILTERS
    // NAV BAR
    let filters = function () {

        //document http://davidstutz.github.io/bootstrap-multiselect/#configuration-options-dropRight

        $("#example-enableCollapsibleOptGroups-enableClickableOptGroups").multiselect({
            allSelectedText: 'همه شهرها',
            nonSelectedText: 'شهر را انتخاب کنید',
            selectAllText: 'همه شهرها',
            selectAllValue: 0,
            numberDisplayed: 100,
            buttonWidth: '100%',
            includeSelectAllOption: true,
            enableClickableOptGroups: true,
            enableCollapsibleOptGroups: true,
            selectAllJustVisible: true,
            onSelectAll: function () {
                setForPage = 1;
                page = 1;

                region = $('#example-enableCollapsibleOptGroups-enableClickableOptGroups').val();
                getProDetail(providerId);
                tagFilter();
                changeRegionFunction();
            },
            onChange: function (option, checked, select) {
                setForPage = 1;
                page = 1;

                region = $('#example-enableCollapsibleOptGroups-enableClickableOptGroups').val();
                getProDetail(providerId);
                tagFilter();
                changeRegionFunction();


            },

            onDropdownHide: function (event) {
                // alert('Dropdown closed.');
            },

            // optionLabel: function(element) {
            //     return $(element).html() + '(' + $(element).val() + ')';
            // }

        });

        $('#category-list').on('click', 'input', function () {
            setForPage = 1;
            page = 1;
            providerCategory = [];
            $('#category-list').find('input').each(function () {
                if ($(this).prop('checked')) {
                    providerCategory.push($(this).val());
                }
            });
            let providerId1 = [];
            $('#providers_list').find('input').each(function () {
                if ($(this).prop('checked')) {
                    providerId1.push($(this).val());
                }
            });
            providerId = providerId1;
            setProvidersName(region, providerCategory, providers);
            tagFilter();
            getProDetail(providerId);

            itemCard();
        });
        $('#providers_list').on('click', 'input', function () {
            setForPage = 1;
            page = 1;
            let providerId1 = [];
            $('#providers_list').find('input').each(function () {
                if ($(this).prop('checked')) {
                    providerId1.push($(this).val());
                }
            });
            providerId = providerId1;
            getProDetail(providerId)
            tagFilter();
            itemCard();


        });

        $('#tagList').on('click', ".tag-clicked", function () {

            if (!$(this).hasClass("selected-item-bg")) {
                $(this).addClass('selected-item-bg');
                $(this).val(1);
            } else {
                $(this).val(0);
                $(this).removeClass('selected-item-bg');
                for (let i = 0; i < tag.length; i++) {
                    if (tag[i] == $(this).data('id')) {
                        tag.splice(i, 1);
                    }

                }
            }
            setForPage = 1;
            page = 1;


            itemCard();

        });
        $("#slider-range").on("slidestop touch", function () {
            setForPage = 1;
            page = 1;
            minPriceFliter = $("#slider-range").slider("values", 0);
            maxPriceFliter = $("#slider-range").slider("values", 1);
            itemCard();
        });


        $(".nav-bar-filter").on('change', function () {
            $(this).addClass('selected-item-bg');
            orderBarFliter = $('#short').val();
            itemCard();

        });


    };

    let changeRegionFunction = function () {
        providerId = null;
        providerCategory = null;
        selectedRegionCategory = findCategories(region, categories);
        setCategoriesType(selectedRegionCategory);
        setProvidersName(region, [], providers);
        tagFilter();

        itemCard();
    };
    let expandRegions = function () {
        $('.collapsible').on("click", function () {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        })
        $('#providers_list').on("click", '.collapsible', function () {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        })
        // if region was child open the
        $($(lastRgPosition).closest('div').removeClass('content'))
    }
    // APPEND MODELS AND SET DATA
    let setCategoriesType = function (parent) {
        $('#category-list').empty();
        let outPut = '';
        parent.forEach(item => {
            outPut +=
                '    <div class="checkbox">' +
                '      <label><input ' + ((providerCategory && item.id == providerCategory[0]) ? 'checked' : '') + ' type="checkbox" value=' + item.id + '>  ' + item.name + ' </label>' +
                '    </div>';
        });
        $('#category-list').append(outPut)
    };

    let setProvidersName = function (rg, proCatId, list) {
        data = {
            rg: rg,
            search: "",
            pId: (proCatId == 0 ? null : proCatId),
            // pId: (proCatId),
            draw: 12,
            start: page - 1,
            length: 12
        };


        $.ajax({
            "data": data,
            "url": "/api/provider/providerList",
            "method": "post",
            "beforeSend": function () {
                $('.loading').removeClass('hidden')

            },
            "complete": function () {
                $('.loading').addClass('hidden')
            },

            "success": function (res) {

                if (res.result) {
                    let outPut = '';
                    let resultJson = JSON.parse(res.payload);
                    resultJson.data.forEach(item => {
                        outPut +=
                            '    <div class="checkbox">' +
                            '      <label><input ' + ((providerId && item.id == providerId[0]) ? "checked" : "") + ' type="checkbox" value=' + item.id + '>  ' + item.name + ' </label>' +
                            '    </div>';

                    })
                    $('#providers_list').empty();
                    $('#providers_list').append(outPut);

                }
            }

        });
    };
//MODAL DATA


    let submitNavBar = function () {

    };

    let tagFilter = function () {

        console.log("appendTags");
        data = {
            pCat: providerCategory
        }
        $.ajax({
            'data': data,
            "url": "/api/product/tagList",
            "method": "get",
            "success": function (res) {
                if (res.result) {
                    let resultJson = JSON.parse(res.payload);
                    $('#tagList').empty();


                    resultJson.forEach(item => {

                        console.log(item.id);
                        console.log($('#yalda_offer').val());
                        $('#tagList').append("" +
                            "<a  value='" + (item.id == parseInt($('#yalda_offer').val()) ? 1 : 0) + "' class='" + (item.id == parseInt($('#yalda_offer').val()) ? 'selected-item-bg' : '') + " tag-clicked ' data-id=" + item.id + " >" + item.name + "</a>" +
                            "");
                        if (item.id == parseInt($('#yalda_offer').val())) {
                            tag.push(parseInt($('#yalda_offer').val()))
                        }
                    });

                } else {
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

        // maxPriceFliter = 1000000;                      //set default max
        // minPriceFliter = 0;                        //set default min
        // if ($('.provideId').text() != "0") {
        //     providerId = $('.provideId').text();
        // }


        // barFliter = 3;


        // page = 1;
        // search = null;
        // setForPage = 1;
        //
        // if (tag.length == 0) {
        //     providerId = $('.provideId').text();
        // }
    }
    //PRICE
    let submitPrice = function () {

    };
    //MODEL

    //PAGE SELECTED
    let initPageFooterClick = function () {
        $(document).on('click', ".first-page", function () {
            $('.product-items').empty();
            $('#page-number').empty();
            page = 1;
            $('.first-page ').addClass('d-none')
            let back = 1
            let next = page + 3;
            changPage(back, next);
        });
        $(document).on('click', ".last-page", function () {
            $('.product-items').empty();
            $('#page-number').empty();
            $('.last-page ').addClass('d-none')
            page = pageMax;
            let back = pageMax - 3
            let next = page;
            changPage(back, next);
        });
        $(document).on('click', ".page-footer-item-next", function () {

            $('.product-items').empty();
            $('#page-number').empty();
            page = page + 1;
            if (page > pageMax) {
                page = pageMax
            }
            let back = page - 3;
            let next = page + 3;
            changPage(back, next);
        });
        $(document).on('click', ".page-footer-item-back", function () {
            $('.product-items').empty();
            page = page - 1;
            let back = page - 3;
            let next = page + 3;
            changPage(back, next);

        });
        $(document).on('click', ".page-footer-item", function () {
            $('.product-items').empty();
            $('#page-number').empty();
            let pageNum = $(this).data('page');
            page = pageNum;
            let back = page - 3;
            let next = page + 3;
            let maxPa = pageMax
            if (back <= 1) {
                back = 1;
            }
            if (next >= pageMax) {
                next = maxPa;
            }
            changPage(back, next);
        });
    };


    //APPEND MODAL
    let setModalData = function () {
        $('.shop_wrapper').on('click', ".cart-open-modal", function () {
            let pDetailId = $(this);
            let pDetail;
            let pInCartDetail;
            let data = {
                proId: pDetailId.data('id')
            };
            $.ajax({
                url: "/api/shop/p/detail/" + pDetailId.data('id'),
                data: data,
                "success": function (res) {
                    if (res.result) {
                        let resultJson = JSON.parse(res.payload);
                        pDetail = resultJson.data;
                        if (resultJson.productInCart) {
                            pInCartDetail = resultJson.productInCart;
                            pDetail = resultJson.productDetail;
                        }
                        (pInCartDetail ? pInCartDetail = pInCartDetail[0] : pInCartDetail = null);
                        appendingModal(pDetail, pInCartDetail);
                        if (pDetail.offPercent == 0) {
                            $('.old_price').addClass('d-none');
                        }
                        if (pDetail.existence) {
                            $('#myModal-' + pDetail.id + '').modal();
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


        })
    };
    //function for appendin
    let appendingModal = function (pDetailResponse, pDetailInCResponse) {
        $('#modal-append').empty()
        primitiveAmount = pDetailResponse.primitiveAmount;
        finalAmount = pDetailResponse.finalAmount;
        let subFeatureInnerHtml = '';

        let modalInnerHtml = '<div   id="myModal-' + pDetailResponse.id + '"  class="modal fade"  tabindex="-1" role="dialog" style="z-index500;padding-right: 15px; display: block;" aria-modal="true">\n' +
            '    <div class="modal-dialog modal-dialog-centered" role="document">\n' +
            '        <div class="modal-content">\n' +
            '            <button type="button" class="close" data-dismiss="modal" aria-label="نزدیک">\n' +
            '                <span aria-hidden="true"><i class="icon-x"></i></span>\n' +
            '            </button>\n' +
            '            <div class="modal_body">\n' +
            '                <div class="container color_three">\n' +
            '                    <div class="row">\n' +
            '                        <div class="col-lg-5 col-md-5 col-sm-12 ">\n' +
            '                            <div class="modal_tab">\n' +
            '                                <div class="tab-content product-details-large append-img-modal ">\n' +
            '                                </div>\n' +
            '                                <div class="modal_tab_button">\n' +
            '                                    <ul class="nav product_navactive owl-carousel append-small-img " role="tablist">\n' +
            '                                     </ul>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <span class="col-lg-7 col-md-7 col-sm-12">\n' +
            '                            <div class="modal_right">\n' +
            '                                <div class="modal_title mb-10">\n' +
            '                                    <h2>' + pDetailResponse.name + '</h2>\n' +
            '                                       <h3 style="font-size: 15px">نام تأمین‌کننده : ' + pDetailResponse.provider.name + '</h3>' +
            '                                </div></div>\n' +
            '                                <div class="modal_description mb-14">\n' +
            '                                    <p>' + pDetailResponse.shortDescription + '</p>\n' +
            '                                </div>\n' +
            '                                <div class="modal_description mb-15">\n' +
            '                                    <p>' + pDetailResponse.fullDescription + '</p>\n' +
            '                                </div>\n' +
            '                                <hr>' +
            '                                <div class="mb-15">\n' +
            '                                      <div class="tag_cloud all-tag" style="max-width: 350px"></div>' +
            '                                </div>\n' +
            //changeableAmount
            '                                <div class=" mb -10 "> \n' +
            // finalAmount
            '                                    <span class=" current_price" id="unit' + pDetailResponse.id + '"><span class="changeable-step countSelected text-dark" style="font-weight: 400;font-size: 14px;">  ' + (pDetailInCResponse ? pDetailInCResponse.count : pDetailResponse.minAllow) + '</span><span class="text-dark" style="font-weight: 400;font-size: 14px;">   ' + pDetailResponse.unit.name + ' : </span> \n' +
            '                                    <span style="font-size: 16px;"  class="changeable-current_price cal-fp"> ' + (pDetailInCResponse ? formatDollar(pDetailResponse.primitiveAmount * (100 - pDetailResponse.offPercent) / 100 * pDetailInCResponse.count) : formatDollar(pDetailResponse.primitiveAmount * (100 - pDetailResponse.offPercent) / 100 * pDetailResponse.minAllow)) + ' </span></span> ' +

            // primitiveAmount
            '<span>&nbsp;&nbsp;</span> ' +
            '                                   <span class="old_price">' +
            '                                   <span style="font-size: 16px" class="changeable-old-price"> ' + (pDetailInCResponse ? formatDollar(pDetailResponse.primitiveAmount * pDetailInCResponse.count) : formatDollar(pDetailResponse.primitiveAmount * pDetailResponse.minAllow)) + '</span></span>' +
            '                                </div>' +

            // defual price
            '                                <div class="  price_box product_desc text-secondary mb-10 "> \n' +
            // finalAmount
            '                                   <span  id="unit' + pDetailResponse.id + '"><span> ( هر </span><span>' + pDetailResponse.unit.name + ' </span>\n' +
            '                                    <span style="margin-top: 10px;font-size: 16px;"> ' + formatDollar(pDetailResponse.primitiveAmount * (100 - pDetailResponse.offPercent) / 100) + ')</span></span>\n' +

            // primitiveAmount
            '                                   <span class="old_price"><span> ( </span>\n' +
            '                                    <span style="font-size: 16px"> ' + formatDollar(pDetailResponse.primitiveAmount) + ' )</span> </span>\n' +
            '                                </div>' +
            '                                <hr>' +
            '                                <div class="variants_selects">\n' +
            '                                 <div class="product_variant quantity">' +
            '                                     <label> تعداد&nbsp </label>' +
            '                                     <div class="pagination">' +
            '                                         <a class="text-center mtSquareButton" id="increase"  data-step="' + pDetailResponse.unitStep + '" >' +
            '                                             <i class="fa fa-plus text-white" style="margin-top: 12px"></i>' +
            '                                         </a>' +
            '                                         <input  class="product-count" min="' + pDetailResponse.minAllow + '" max="' + pDetailResponse.maxAllow + '"' +
            '                                                value="' + (pDetailInCResponse ? pDetailInCResponse.count : pDetailResponse.minAllow) + '" step="' + pDetailResponse.unitStep + '"' +
            '                                                data-step="' + pDetailResponse.unitStep + '" data-min="' + pDetailResponse.minAllow + '" data-max="' + (pDetailResponse.productCount < pDetailResponse.maxAllow ? pDetailResponse.productCount : pDetailResponse.maxAllow) + '" ' +
            '                                                type="text" disabled="false" style="margin-right: 5px!important;margin-left: 5px!important;">' +
            '                                         <a class="text-center mtSquareButton " id="decrease"   " data-step="' + pDetailResponse.unitStep + '" >' +
            '                                             <i class="fa fa-minus text-white" style="margin-top: 12px"></i>' +
            '                                         </a>' +

            '                                     </div>' +
            '                                     <label class="m-2">' + pDetailResponse.unit.name + '</label>' +
            '                                     <span class="mt-text-danger "> قابل سفارش ' + pDetailResponse.minAllow + ' تا ' + pDetailResponse.maxAllow + ' ' + '' + pDetailResponse.unit.name + '</span>' +
            '                                 </div>' +
            '                                     <br>';
        (pDetailResponse.subFeatureResponse ? pDetailResponse.subFeatureResponse.forEach(item => {
            subFeatureInnerHtml += '<div class="sub-f-par product_variant size col-md-12 ">'
            subFeatureInnerHtml += ' <label class="col-2" >' + item.name + '</label>' +
                '<div class="niceselect_option  filter-provider-div col-10" ><select class="select_option_f select_option  ">';
            item.subFeature.forEach(op => {
                let opAmount = formatDollar(op.amount) + ' +';
                if (op.amount == 0) {
                    opAmount = ""
                }
                subFeatureInnerHtml += '' +
                    '                                    <option class="sub_f"\n' +
                    '                                            value=' + op.id + '\n' +
                    '                                            data-id=' + op.id + '\n' +
                    '                                            data-amount=' + op.amount + '\n' +
                    '                                            data-unitDepended=' + op.unitDepended + '\n' +
                    '                                            ' + (pDetailInCResponse != null ? (pDetailInCResponse.selectedSubFeature.includes(op.id) ? "selected" : "") : "") + '\n' +
                    '                                            >\n' +
                    '                                        ' + op.name + '\n' +
                    '                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n' +
                    '                                        ' + opAmount + '\n' +
                    '                                    </option>\n'


            });
            $('.select_option_f').niceSelect();
            subFeatureInnerHtml += '                                </select></div>\n' +
                '                            </div>\n'
        }) : '')
        modalInnerHtml += subFeatureInnerHtml;
        modalInnerHtml += '                                     <div class="minday">' +
            '                                     <div class="col-12 row">' +
            '                                           <div class=" text-secondary">' +
            '' +
            '                                               <div  id="minday"> آماده ارسال از <span style="color: #222222!important;">' + pDetailResponse.prepareHour + '</span>  ساعت آینده  </div>' +
            '                                           </div>' +
            '                                       </div><br>' +
            '                                    <div class="contact_message form"> ' +
            '                                        <div class="contact_textarea"> ' +
            '                                            <textarea ' +
            '                                               id="userDescription" ' +
            '                                               name="userDescription" ' +
            '                                               data-userdesc="' + pDetailResponse.userDescription + '" ' +
            '                                               placeholder=" توضیحات سفارش " ' +
            '                                               class="form-control2">' + (pDetailInCResponse ? pDetailInCResponse.userDescription : "") + '</textarea> ' +
            '                                        </div> ' +
            '                                    </div>' +
            '                               <div class="price-tolerance">\n' +
            (pDetailResponse.category.id == 13 ? '<p class="mt-text-danger ">در صورت تغییر وزن بیشتر از ۱۵۰ گرم، اختلاف هزینه\n' +
                '                                در مبلغ نهایی محاسبه خواهد شد.</p>\n' : '') +
            '                                    </div>\n' +
            '                                    </div>\n' +

            '<div class="mt-2">' +
            '<button data-id="' + pDetailResponse.id + '" data-uName="' + pDetailResponse.unit.name + '" data-name="' + pDetailResponse.name + ' " data-discount="' + pDetailResponse.offPercent + ' " data-price=" ' + pDetailResponse.primitiveAmount + ' "  data-image="' + pDetailResponse.images[0] + '" data-des=" ' + pDetailResponse.shortDescription + '" id="btn-add-to-cart"  class="mtPrimaryButton" data-dismiss="modal" type="submit" >افزودن به سبد خرید' +
            '</button>' +
            '   <a href="/user/cart/" class="mtOutlineButton text-center justify-content-center"   data-id="' + pDetailResponse.id + '"><i class="lnr lnr-cart" ></i>سبد خرید\n' +
            '</a>' +
            '</div>' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>'


        $('#modal-append').append(modalInnerHtml);
        calculateTotalPrice();
        if (pDetailResponse.prepareHour != 0) {
            $('.prepareHour').removeClass('d-none')
        } else {
            $('.prepareHour').addClass('d-none')
        }

        appendMoreImage(pDetailResponse);
        appendModalTages(pDetailResponse)

        if (pDetailResponse.offPercent == 0) {

        } else {

        }

    };

    // IMAGE MODAL (ONCLICK IMAGE ZOOM IN IMAGE)
    // MODAL CHANGING VALUE

    // let actionForModalValue = function () {
    //     $(document).on('click', ".do-action", function () {
    //         let setPro = $(this).data('id');
    //         let id = '?????????' + setPro;
    //         let value = $('#' + id + '').text();
    //         let price = $(this).data('price');
    //         let offPrice = $(this).data('discount');
    //         price = value * price;
    //         offPrice = price - (value * offPrice);
    //         // set total price with off(0||>0)
    //         let total = 'total-' + setPro;
    //         $('#' + total + '').val(offPrice);
    //         $('#' + total + '').text(offPrice);
    //         //set static price(without off)
    //         let offTotal = 'off-total-' + setPro;
    //         $('#' + offTotal + '').val(price);
    //         $('#' + offTotal + '').text(price);
    //
    //
    //     })
    // }


    // MODAL SET RATE
    // MODAL BUY ITEM
    let calculateTotalPrice = function () {
        let calTotalPrice = 0;
        $('#modal-append').find('.select_option_f')
            .each(function () {
                if (!($(this).val() == undefined || $(this).val() == null || $(this).val() == "")) {
                    let selecte2vlaue = ($(this).val());
                    ($(this).find('option')).each(function () {
                        if (selecte2vlaue == $(this).val()) {
                            ($(this).data('unitdepended') ? calTotalPrice += $(this).data('amount') * parseFloat($('.countSelected').text()) : calTotalPrice += $(this).data('amount'));
                        }
                    });

                }
            });

        calTotalPrice += parseFloat($('.countSelected').text()) * finalAmount;

        $('.cal-fp').val(calTotalPrice);
        $('.cal-fp').text(formatDollar(calTotalPrice));
    }

    function decreaseValueIncreaseValue() {
        $('#modal-append').on('click', '#decrease', function () {
            let minStep = $('.product-count').data('min');
            let step = $('.product-count').data('step');
            let vale = $('.product-count').val();
            vale -= step;
            if (vale < minStep) {
                vale = minStep;
            }
            $('.product-count').val(vale);
            $('.product-count').text(vale)


            // new price
            $('.changeable-current_price').text(formatDollar(vale * finalAmount));
            $('.changeable-current_price').text(formatDollar(vale * finalAmount));

            // old price
            $('.changeable-old-price').text(formatDollar(vale * primitiveAmount));
            $('.changeable-old-price').text(formatDollar(vale * primitiveAmount));

            $('.changeable-step').text((vale));
            $('.changeable-step').val((vale));


            calculateTotalPrice();

        });
        $('#modal-append').on('click', '#increase', function () {
            let step = $('.product-count').data('step');
            let maxStep = $('.product-count').data('max');
            let vale = $('.product-count').val();
            vale = Number(vale) + Number(step);
            if (vale > maxStep) {
                vale = maxStep;
            }
            $('.product-count').val(vale);
            $('.product-count').text(vale)

            // new price
            $('.changeable-current_price').text(formatDollar(vale * finalAmount));
            $('.changeable-current_price').text(formatDollar(vale * finalAmount));

            // old price
            $('.changeable-old-price').text(formatDollar(vale * primitiveAmount));
            $('.changeable-old-price').text(formatDollar(vale * primitiveAmount));

            $('.changeable-step').text((vale));
            $('.changeable-step').val((vale));
            calculateTotalPrice();
        });
        $('#modal-append').on('change', '.select_option_f', function () {
            calculateTotalPrice();
        })
    }

    let appendModalTages = function (index) {
        $('.all-tag').empty();
        let tag = '';
        index.tags.forEach(item => {

            tag += '<a class=" after-tag-click mr-2" style="cursor: default">' + item.name + '</a>'
        })
        $('.all-tag').append(tag)
    }
    let appendMoreImage = function (index) {
        let i = 0;
        index.images.forEach(item => {
            let active = "";
            if (i == 0) {
                active = " active"
            } else {
                active = ""
            }
            $('.append-img-modal').append('' +
                '<div class="tab-pane fade show' + active + '" id="tab' + item + '" role="tabpanel">\n' +
                '    <div class="modal_tab_img">' +
                '       <div style="min-width: 100px" class="img-seted"> <img style="width: 100%;\n' +
                '           max-width: 400px;\n' +
                '           height: auto;" data-img="' + item + '" class=" open-image-modal col-lg-6 col-md-12 primary_img img-fluid " src="/thumbnail/files/original/' + item + '"></div>\n' +
                '     </div>\n' +
                '</div>')
            $('.append-small-img').append('' +
                ' <li>\n' +
                '  <a class="nav-link active"  data-img="' + item + '" data-toggle="tab" href="#tab' + item + '" role="tab" aria-controls="tab1" aria-selected="false"><img src="/thumbnail/files/original/' + item + '" alt=" بهتاتهویه"></a>\n' +
                '</li>')
            i++;
        })
        var $productNavactive = $('.product_navactive');
        if ($productNavactive.length > 0) {
            $('.product_navactive').owlCarousel({
                autoplay: true,
                loop: false,
                nav: true,
                autoplayTimeout: 8000,
                items: 4,
                dots: false,
                navText: ['<i class="fa fa-angle-right"></i>', '<i class="fa fa-angle-left"></i>'],
                responsiveClass: true,
                responsive: {
                    0: {
                        items: 1,
                    },
                    250: {
                        items: 2,
                    },
                    480: {
                        items: 3,
                    },
                    768: {
                        items: 4,
                    },

                }
            });
        }
        $('.modal').on('shown.bs.modal', function (e) {
            $('.product_navactive').resize();
        })

        $(document).on('click', '.product_navactive', function (e) {
            e.preventDefault();
            var $href = $(this).attr('href');
            $('.product_navactive a').removeClass('active');
            $(this).addClass('active');

            $('.provider-details-large .tab-pane').removeClass('active show');
            $('.provider-details-large ' + $href).addClass('active show');

        })
    }
    let addToCart = function () {
        $('#modal-append').on('click', ".btn-add-to-cart", function () {
            let proProId = $(this).data('id');
            // let proName = $(this).data('name');
            // let proPrice = $(this).data('price');
            // let proDesc = $(this).data('des');
            // let proImage = $(this).data('image');
            // let discount = $(this).data('discount');
            // let unitName = $(this).data('uName');
            let userDescription = $('#userDescription').val();
            let val = parseFloat($('.product-count').val());
            let sub = [];
            $('#modal-append').find('.select_option_f')
                .each(function () {
                    if (!($(this).val() == undefined || $(this).val() == null || $(this).val() == "")) {
                        sub.push(($(this).val()))
                    }
                });
            chageCount(
                proProId,
                val,
                userDescription,
                sub
            );

        })
    };


    //PRODUCT DETAILS PAGE
    let productDetail = function () {
        $(document).on('click', '.product-detail-page', function () {
            let index = $(this).data('index');
            // let tagsStr = '';
            // tag.forEach(e => {
            //     if(tagsStr != ''){
            //         tagsStr += '&';
            //     }
            //     tagsStr += 'tags=' + e;
            // })
            let url = "";
            url = url + "/p/" + index;
            // url = url + "/&proName=" + encodeURI($('#pro-name').val());
            // url = url + "minPrice=" + minPriceFliter + "&";
            // url = url + "maxPrice=" + maxPriceFliter + "&";
            // url = url + "barFilter=" + barFliter + "&";
            // url = url + "modelFilter=" + category + "&";
            // url = url + "search=" + encodeURI(search) + "&";
            // url = url + "start=" + page + "&";
            // url = url +(tagsStr == null || tagsStr =='' ? 'tag=' : tagsStr + '&' );
            window.location = url;
        })
    }
    // TABLE

    let doItemCard = function () {
        $("#slider-range").slider('values', 0, minPriceFliter);
        $("#slider-range").slider('values', 1, maxPriceFliter);
        $("#amount").val(formatDollar(maxPriceFliter) + ' - ' + formatDollar(minPriceFliter));
        let data = {
            pvdId: providerId,
            maxP: maxPriceFliter,
            minP: minPriceFliter,
            rg: region,
            pdtCat: providerCategory,
            order: orderBarFliter,
            tags: selectedTags(),
            srch: search,
            draw: 12,
            start: page - 1,
            length: 12
        };

        console.log(data, " v");
        $.ajax({
            "url": "/api/product/search",
            "data": data,
            "success": function (res) {
                if (res.result) {
                    let resultJson = JSON.parse(res.payload);
                    setRecords(resultJson.recordsTotal);
                    $('.product-items').empty();
                    $('#not-found').addClass('d-none');


                    if (resultJson.recordsTotal == 0) {
                        notFound(resultJson.recordsTotal);


                    } else {
                        pagination(resultJson.recordsTotal);
                        globalItem = resultJson.data;
                        let i = 0;
                        $('.shop_wrapper').empty();
                        resultJson.data.forEach(item => {
                            appendCards(i, item);
                            i++;
                        });

                        // initGrids();

                    }
                } else {
                    $('.shop_wrapper').empty();
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
            },
            complete: function () {
            }
        });

    };

    let selectedTags = function () {
        tag = [];
        $('#tagList').find('.tag-clicked ').each(function () {

            console.log($(this));
            console.log($(this).hasClass("selected-item-bg"));
            if ($(this).hasClass("selected-item-bg")) {
                tag.push($(this).data('id'));

            }
        })
        return tag;
    }

    let itemCard = function () {
        $(".shop_toolbar_btn .active").each(function () {
            $(this).removeClass('active');

            $('.btn-grid-3').click();
        });
        if (requestTimeOut != null) {
            clearTimeout(requestTimeOut);
        }

        requestTimeOut = setTimeout(doItemCard, 700);
    };

    let appendCards = function (i, item) {

        if (item.images[1] == null) {
            $('#img-2').css('display', 'none');
        }

        let id = "myModal-" + item.id;
        let disId = 'dis-' + item.id;

        $('.shop_wrapper').append(
            ' <div class="col-lg-4 col-md-4 col-sm-6 color_three ">' +
            '<div class="justify-content-center row align-content-center">' +
            '   <div id="text-not' + i + '" class="setText  mt-text-danger  " style="display: none">  اتمام موجودی </div> ' +
            '</div>' +
            '                        <div id="cart-list-all' + i + '" class="single_product setTextOnImage">' +
            '                            <div class="product_thumb">' +
            '                                <a class="primary_img"  href="/p/' + item.id + '" ><img   data-index="' + item.id + ' " class="product-detail-page setTextOnImage nullImage' + item.id + '"  src=' + "/thumbnail/files/original/" + item.images[0] + '></a>' +
            '                                <a class="secondary_img"  href="/p/' + item.id + '"><img   data-index="' + item.id + '" class="product-detail-page setTextOnImage nullImage' + item.id + '"  src=' + "/thumbnail/files/original/" + item.images[0] + '></a>' +
            '                                <div class="label_product">' +
            '                                    <span  class="  ' + (item.offPercent != 0 ? 'label_new' : 'none') + ' " >%' + item.offPercent + '</span>' +
            '                                    <span  class="label_sale" style="width: auto;padding-left: 5px;padding-right: 5px;">' + item.category.name + '</span>' +
            '                                </div>' +
            '                              <div>' +
            '                                <div  id="action' + i + '" class="action_links"  >' +
            '                                    <ul>' +
            '                                        <li   data-id="' + item.id + '" data-index="' + i + '" class="quick_button cart-open-modal"><a title="مشاهده سریع"> <span class="lnr lnr-cart moshahede"></span></a></li>' +
            '                                    </ul>' +
            '                                </div>' +
            '                               </div>' +
            '                            </div>' +
            '                            <div class=" product-detail-page product_content grid_content" data-index="' + item.id + '">' +
            '                                <h4 class="product_name"><a></a>' + item.name + '</h4>' +
            '                                <p><a href="/p/' + item.id + '" >' + moreFormat(item.shortDescription, 40) + '</a></p>' +
            '                                <div class="price_box rtl">' +
            '                                    <span class="smfont-size">هر' + item.unit.name + '</span>' +
            '                                    <span style="font-size: 16px; color:#000"> ' + formatDollar((item.primitiveAmount * (100 - item.offPercent)) / 100) + ' </span> ' +
            '                                    <span  class="old_price dis-' + item.id + '" style="margin-top: 10px"><span style="font-size: 16px"> ' + formatDollar(item.primitiveAmount) + '</span></span> ' +
            '                                </div>' +
            '                            </div>' +
            '                            <div class="product_content list_content">' +
            '                                <h4 class="product_name"><a></a>' + item.name + '</h4>' +
            '                                <span><a href="/p/' + item.id + '" style="font-size: 14px" >' + item.shortDescription + '</a></span>' +
            '                                <div class="price_box rtl">' +
            '                                    <span class="black-color smfont-size"  > هر' + item.unit.name + '</span>' +
            '                                    <span style="font-size: 16px ;color:#000!important;"> ' + formatDollar((item.primitiveAmount * (100 - item.offPercent)) / 100) + ' </span> ' +
            '                                    <span  class="old_price dis-' + item.id + '" style="margin-top: 10px"><span style="font-size: 16px ;"> ' + formatDollar(item.primitiveAmount) + '</span></span> ' +
            '                                </div>' +
            // '                                <div class="product_desc">' +
            // '                                    <p style="width: 400px">' + item.fullDescription + '</p>' +
            // '                                </div>' +
            '                                <div class="action_links list_action_right magnifiercolor">' +

            '                                <ul>' +
            // '                                        <li class="add_to_cart"><div  title="افزودن به سبد خرید"><span class="lnr lnr-cart"></span></div></li>' +
            '                                        <li  data-id="' + item.id + '" data-index="' + i + '" class="quick_button cart-open-modal"><a title="مشاهده سریع"> <span class="lnr lnr-cart"></span></a></li>' +
            '                                    </ul>' +
            '                                </div>' +
            '                            </div>' +
            '                        </div>' +
            '                    </div>'
        );
        let nullImage = 'nullImage' + item.id;

        if (item.images.length == 0) {

            $('.' + nullImage + '').attr('src', '/assets/img/logo.png' +
                '');
        }
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

    }
    //paging
    let pagination = function (data) {
        if (setForPage == 1) {

            console.log("Page === 1");
            setForPage++;
            $('#page-number').empty();
            $('.product-items').empty();
            pageMax = Math.ceil(data / 12);
            $('#page-number').append('<li style="display: none;" class="first-page"  data-page=' + page + '><a> &lt;&lt;</a></li><li style="display: none!important" class=" next page-footer-item-back"   id="back-btn"  data-page="' + page + '"><a>قبل </a></li>\n');
            if (conditionInpage >= Math.ceil(data / 12)) {
                conditionInpage = Math.ceil(data / 12);
            }
            if (conditionInpage < Math.ceil(data / 12)) {
                conditionInpage = 5;
            }
            for (let i = 1; i <= conditionInpage; i++) {
                $('#page-number').append('<li  style="cursor:pointer " class="page-footer-item" value="' + i + '" id ="page_' + i + '"  data-page="' + i + '"  >' + i + ' </li>\n')
            }
            if (conditionInpage < Math.ceil(data / 12)) {
                $('#page-number').append('<li style="display: none!important" id="next-btn" class="  hide-next next page-footer-item-next" " data-page="' + page + '"><a>...</a></li></li>');

            }
            $('#page-number').append('<li  data-page-max="' + pageMax + '" style="display: none" id="next-btn" class=" next page-footer-item-next" " data-page="' + page + '"><a>بعد</a></li><li style="display: none!important" class=" next page-footer-item-next last-page" data-page=' + page + ' ><a>&gt;&gt;</a></li>');
        }

        for (i = Math.ceil(data / 12); i >= 1; i--) {
            let otherPages = "page_" + i;
            otherPages = '#' + otherPages + '';
            $(otherPages).removeClass("current");
        }
        let currentPage = "page_" + page;
        currentPage = '#' + currentPage + '';
        $(currentPage).addClass("current");

        // FOR MIDDLE PAGE SHOW BACK AND NEXT
        $('.page-footer-item-next').css('display', ' inline-block');
        $('.page-footer-item-back').css('display', ' inline-block');
        // HID BACK IN PAGE ONE
        if (page == 1) {
            $('.page-footer-item-back').css('display', 'none');
            $('.page-footer-item-next').css('display', ' inline-block');
        }
        if (page == pageMax) {
            $('.page-footer-item-back').css('display', ' inline-block');
            $('.page-footer-item-next').css('display', 'none');
        }
        if (data == 0 || data <= 12) {
            $('.page-footer-item-back').css('display', 'none');
            $('.page-footer-item-next').css('display', 'none');
        }
        if (page <= 4) {
            $('.hide-back').css('display', 'none');
        }
        if (page >= pageMax - 3) {
            $('.hide-next').css('display', 'none');

        }

    };
    let changPage = function (back, next) {
        if (back <= pageMin) {
            back = 1
        }
        if (next >= pageMax) {
            next = pageMax;
        }
        if (page == pageMax) {
            back = pageMax - 3;
            if (back <= 1) {
                back = 1;
            }
        }
        $('#page-number').empty()
        $('#page-number').append('<li  class=" d-none first-page"  data-page=' + page + '><a> &lt;&lt;</a></li><li style="display: none!important" class=" next page-footer-item-back"  id="back-btn"  data-page="' + page + '"><a>قبل </a></li>\n');
        $('#page-number').append('<li style="display: none!important" id="next-btn" class="  hide-back next page-footer-item-back" " data-page="' + page + '"><a>...</a></li></li>');
        for (let i = back; i <= next; i++) {
            $('#page-number').append('<li  style="cursor:pointer " class="page-footer-item" value="' + i + '" id ="page_' + i + '"  data-page="' + i + '"  >' + i + ' </li>\n')
        }
        $('#page-number').append('<li style="display: none!important" id="next-btn" class=" hide-next next page-footer-item-next" " data-page="' + page + '"><a>...</a></li></li>');
        $('#page-number').append('<li  id="next-btn" class=" next page-footer-item-next" " data-page="' + page + '"><a>بعد</a></li><li class=" d-none last-page " data-page=' + page + ' ><a>&gt;&gt;</a></li>');
        let newPage = "page_" + page;
        $('#' + newPage + '').addClass("current");
        if (page != 1) {
            $('.first-page ').removeClass('d-none')
        }
        if (page != pageMax) {
            $('.last-page ').removeClass('d-none')
        }
        $("html, body").animate({scrollTop: window.innerHeight / 2}, 600);
        itemCard();

    };


    let filterScroll = function(){
        $('#filter-btn').on('click',function () {

            var delayInMilliseconds = 1000; //1 second

            setTimeout(function() {
                $('html,body').animate({
                        scrollTop: $(".sidebar_widget").offset().top},
                    2300);      //your code to be executed after 1 second
            }, delayInMilliseconds);

        })
    };

    // page change tags removed
    let selectTagsFromTagList = function () {
        tag.forEach(item => {

            $('#tagList').find('.tag-clicked').each(function () {
                console.log("$(this).data('id')", $(this).data('id'));
                if (parseInt($(this).data('id')) === item) {
                    console.log("same in itemmmmmm");

                    console.log("(Tage item)", item);
                    $(this).addClass('selected-item-bg')
                }
            })
        })


    };
    //RECORDS
    let setRecords = function (data) {
        $('#page_amount').empty();
        let min = (page - 1) * 12 + 1;
        let max = page * 12;
        if (max >= data) {
            max = data;
        }
        let string = min + " - " + max + "  از " + data;
        $('#page_amount').text(string);
    }
    //404
    let notFound = function (data) {

        $('#page-number').empty();
        $('.shop_wrapper').empty();
        // $('.shop-table').empty();
        setForPage = 1;
        page = 1;
        $('.shop_wrapper').append('<p class="no-result  text-center text-dark text-lg-center"> محصولی  یافت نشد! </p>')

    };

    let initGrids = function () {
        $('.shop_toolbar_btn > button').on('click', function (e) {

            e.preventDefault();
            var parentsDiv = $('.shop_wrapper');
            var viewMode = $(this).data('role');
            var mood = $(this).hasClass('active');
            $('.shop_toolbar_btn > button').removeClass('active');
            parentsDiv.removeClass('grid_3 grid_4 grid_5 grid_list').addClass(viewMode);
            if (viewMode == 'grid_3') {
                $(this).addClass('active');
                parentsDiv.children().addClass('col-lg-4 col-md-4 col-sm-6').removeClass('col-lg-3 col-cust-5 col-12');
                $('.row.justify-content-center.col-lg-4.col-md-4.col-sm-6').remove();
                $('.row.justify-content-center.col-lg-3.col-md-4.col-sm-6').remove();
            }
            if (viewMode == 'grid_4') {
                $(this).addClass('active');
                parentsDiv.children().addClass('col-lg-3 col-md-4 col-sm-6').removeClass('col-lg-4 col-cust-5 col-12');
                $('.row.justify-content-center.col-lg-4.col-md-4.col-sm-6').remove();
                $('.row.justify-content-center.col-lg-3.col-md-4.col-sm-6').remove();
            }
            if (viewMode == 'grid_list' && (mood == false)) {
                $(this).addClass('active');
                parentsDiv.children().addClass('col-lg-3 col-md-4 col-sm-6').removeClass('col-lg-3 col-lg-4 col-md-4 col-sm-6 col-cust-5');
                $('.row.justify-content-center.col-lg-4.col-md-4.col-sm-6').remove();
                $('.row.justify-content-center.col-lg-3.col-md-4.col-sm-6').remove();
            }

        });

    };


    // init comment
    let initComment = function () {
        $('#comment-btn').on('click', function () {
            appendingCommentModal();
        });

    };

    // add comment modal div to page
    let appendingCommentModal = function () {
        $('#modal-comment').empty();
        let rateNumber = providerDetail.rate;
        let providerName = providerDetail.name;
        let commentTotalNumber = providerDetail.commentNumber;
        let modalDiv = '' +
            '    <div  class="modal fade show"  tabindex="-1" role="dialog" style="padding-right: 12px; display: block;" aria-modal="true">\n' +
            '        <div class="modal-dialog modal-dialog-centered" role="document">\n' +
            '            <div class="modal-content">\n' +
            '                <button type="button" id="close-modal-comment" class="close" data-dismiss="modal" aria-label="نزدیک">\n' +
            '                    <span aria-hidden="true"><i class="icon-x"></i></span>\n' +
            '                </button>\n' +
            '                <div class="modal_body">\n' +
            '                    <div class="container color_three">\n' +
            '                        <div class="container color_three">\n' +
            '                            <div class="row" style="display: block ruby;" >\n' +
            (providerDetail.imageId != 0 ? '<img class="br-8px" src="/thumbnail/files/original/' + providerDetail.imageId + '" alt=" بهتاتهویه" style="height: auto; width: 52px; padding: 2px 2px 2px 2px">' : '<img src="/assets/img/logo/logo-c.png" alt=" بهتاتهویه" style="height: auto; width: 52px; padding: 2px 2px 2px 2px">n'
            ) +
            '                                <h5 class="ml-2"> ' + providerName + ' </h5>\n' +
            '                            </div>\n' +
            '                            <h4 class="text-center">نظرات</h4>\n' +
            '                        </div>\n' +
            '                        <div class="line-top ">\n' +
            '\n' +
            '                           <div class="row text-center d-block ">\n' +
            '                               &nbsp;\n' +
            '                               <div class=" product_ratting row justify-content-center" rating="true" >\n' +
            '                                   <ul style="display: block ruby; " class="row mr-2">\n' +
            starRatingV2(rateNumber) +
            '                                       <li class="color_three text-center ml-2 ">' + rateNumber + '</li>\n' +
            '                                   </ul>\n' +
            '\n' +
            '\n' +
            '                                   <ul class="mb-3">\n' +
            '\n' +
            '                                       (از مجموع ' + commentTotalNumber + ' رای)\n' +
            '                                   </ul>\n' +
            '                               </div>\n' +
            '                           </div>\n' +
            '<div id="accordion" class="card__accordion mt-3 mb-3">\n' +
            '    <div class="card card_dipult">\n' +
            '        <div class="card-header card_accor" id="headingOne">\n' +
            '            <button id="row-comment" class="btn btn-link collapsed text-white" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne" style="background-color: #FC8A35;">\n' +
            '                نظر خود را ثبت کنید\n' +
            '\n' +
            '                <i class="fa fa-plus "></i>\n' +
            '                <i class="fa fa-minus"></i>\n' +
            '\n' +
            '            </button>\n' +
            '\n' +
            '        </div>\n' +
            '\n' +
            '        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion" style="">\n' +
            '            <div class="card-body">\n' +
            '                <div class="panel-body">\n' +
            '\n' +
            '                    <div>\n' +
            '                        ' +
            '                       <div class="comment_title">\n' +
            '                            <h5>نظر خود را ثبت کنید</h5>\n' +
            '                            <div class="right" id="review" dir="ltr"></div>\n' +
            '                       </div>' +
            '                        <div class="product_review_form">\n' +
            '                            <form >\n' +
            '                                <div class="row">\n' +
            '                                    <div class="col-12">\n' +
            '                                        <textarea name="comment" id="review_comment-1" maxlength="250" ></textarea>\n' +
            '                                    </div>\n' +
            '                                </div>\n' +
            '                                <button type="button" id="comment-btn-send">ارسال</button>\n' +
            '                            </form>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '\n' +
            '                </div>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>' +
            '                            <div id="review_comment-body-parent" style="overflow: auto; position: relative; height: 300px;" ><div id="review_comment-body"  > \n' +
            '\n' +
            '                            </div></div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n';


        $('#modal-comment').append(modalDiv);
        $('#modal-comment').modal('show');

        // addCommentResponse();

        $('#close-modal-comment').on('click', function () {
            $('#modal-comment').modal('hide');
        });

        addPagebleComment();

        requestForComment();

        //init send btn
        $('#comment-btn-send').on('click', function () {
            sendComment();
        });

        $("#review").rating({
            "value": 0,
            "click": function (e) {
                commentRateStar = e.stars;
            }
        });

        if (login != true) {
            $("#accordion").remove();
        }
    };

    // request for get comment data (pagele)
    let showCommentLoading = function (vis) {
        if (vis) {

            let loadDiv = '<div class=" text-center " style="display: block ruby" id="comment-page-loading"> <div class="loader-bar" ></div></div>';
            $("#review_comment-body").append(loadDiv);
        } else {
            $('#comment-page-loading').remove();
        }
    };

    // request for get btn comment data (pagele)
    let showCommentBtnLoading = function (vis) {
        if (vis) {
            $('#review_comment-1').attr('readonly', true);
            $('#comment-btn-send').addClass("disabled");
        } else {
            $('#review_comment-1').attr('readonly', false);
            $('#comment-btn-send').removeClass("disabled");
        }
    };

    // request for get comment data (pagele)
    let requestForComment = function () {
        // //show loading
        showCommentLoading(true);
        $.ajax({
            url: "/api/comment/provider/list/" + providerDetail.id + "/" + commentPage + "/" + commentPageSize,
            method: "GET",
            data: null,
            success: function (response) {
                commentPage = commentPage + 1;
                commentPageLoading = false;
                addCommentResponse(response);
                showCommentLoading(false);
            },
            error: function () {
                commentPageLoading = false;
                showCommentLoading(false);
            }
        });

    };

    // add scroll pagination to comment div
    let addPagebleComment = function () {

        $("#review_comment-body-parent").scroll(function (e) {
            var elem = $(e.currentTarget);
            if (elem[0].scrollHeight - elem.scrollTop() == elem.outerHeight()) {
                //check is not loading
                if (commentPageLoading == false) {
                    commentPageLoading = true;
                    requestForComment();

                }
            }

        });
    };


    // just for test adding comment
    let addCommentResponse = function (data) {
        for (let i = 0; i < data.length; i++) {
            var x = data[i];
            appendComment(x.name, x.text, x.rate, x.date);
        }
        ;

    };

    //append comment to comment div
    let appendComment = function (username, commentText, stars, date) {
        var commentDiv = "" +
            " <div class=\"reviews_comment_box\">\n" +
            "     <div class=\"letter-name \">\n" +
            "     <div class=\"text-center d-block \">" +
            username.substring(0, 2) +
            "     </div>\n" +
            "     </div>\n" +
            "     <div class=\"comment_text\">\n" +
            "         <div class=\"reviews_meta\">\n" +
            "             <div class=\"star_rating\"  style=\"color: rgb(252, 215, 3);\">\n" +
            starRatingJustDiv(stars) +
            "             </div>\n" +
            "             <p>\n" +
            "                 <strong>" + username + "</strong>\n" +
            "                 &nbsp;\n" +
            "                 <strong class=\"text-secondary\">" + date + "</strong>\n" +
            "             </p>\n" +
            "             <div class=\"text-secondary\">" +
            commentText +
            "             </div>\n" +
            "         </div>\n" +
            "     </div>\n" +
            " </div>";

        $("#review_comment-body").append(commentDiv);
    };

    //append comment to comment div
    let getProDetail = function (provider) {
        if (provider && provider != 0 && provider.length == 1) {
            $('#comment-btn').removeClass('d-none');
            let data = {
                proId: provider[0],

            }
            $.ajax({
                url: " /api/shop/pro/detail",
                method: "GET",
                contentType: false,
                data: data,
                success: function (res) {

                    if (res.result) {
                        $('#provider_detail').addClass('col-lg-9');
                        $('#provider_detail').removeClass('col-lg-12');
                        let resultJson = JSON.parse(res.payload);
                        providerDetail = resultJson;
                        commentPage = 0;
                        showProviderDetail(resultJson);
                    }
                },
                error: function () {
                }
            });
        } else {
            $("#pro-rate-div").empty();
            $('#provider_detail').empty();
            $('#provider_detail').addClass('justify-content-center');
            $('#provider_detail').removeClass('col-lg-9');
            $('#provider_detail').addClass('col-lg-12');
            $('#provider_detail').append("                    <div class='breadcrumb_content'>" +
                "                        <h3> محصولات </h3>" +
                "                        <ul>" +
                "<li><a href='/'>خانه </a></li>" +
                "<li><a href='/category/" + providerCategory + "'>تأمین‌کنندگان</a></li>" +
                "<li>محصولات</li>" +
                "                        </ul>\n" +
                "                    </div>\n");
            $('#comment-btn').addClass('d-none');
        }

    };
    let showProviderDetail = function (provider) {
        let providerDetails = ' <div class="col-lg-3 col-md-3 col-sm-12 ">\n' +
            '                        <img src="' + (provider.imageId == 0 ? "/assets/img/logo/logo-c.png" : "/thumbnail/files/original/" + provider.imageId + "") + '" alt=" بهتاتهویه" style="height: 110px; width: 110px; border-radius: 10px;">\n' +
            '                    </div>\n' +
            '                    <div class="col-lg-9  col-md-9 col-sm-12 mt-custom ">\n' +
            '                        <h4>' + provider.name + '</h4>\n' +
            '                        <span >' + provider.fullDescription + '</span>\n' +
            '                    </div>\n' +
            '                </div>'

        $('#provider_detail').empty();
        $('#provider_detail').append(providerDetails);
        let rateDiv = '' +
            '                           <div class="row text-center justify-content-center " style="display: block ruby;" >\n' +
            '                               <div class=" product_ratting row justify-content-center mb-3" rating="true" >\n' +
            '                                   <ul style="display: block ruby; " class="row mr-3">\n' +
            starRatingV2(provider.rate) +
            '                                       <li class=" text-center text-white ml-3" style="background-color:#FC8A35;width: 40px;height: auto; border-radius: 7px;">' + provider.rate + '</li>\n' +
            '                                   </ul>\n' +
            '\n' +
            '\n' +
            '                                   <ul class="" style="font-size: 12px">\n' +
            '\n' +
            '                                       (از مجموع ' + provider.commentNumber + ' رای)\n' +
            '                                   </ul>\n' +
            '                               </div>\n' +
            '                               </div>\n';
        $("#pro-rate-div").empty();
        $("#pro-rate-div").append(rateDiv);

    }

    //append comment to comment div
    let sendComment = function () {

        showCommentBtnLoading(true);

        let commentText = $('#review_comment-1').val();

        $.ajax({
            url: "/api/comment/provider/send/" + providerDetail.id,
            method: "GET",
            contentType: false,
            data: {
                text: "" + commentText,
                rate: commentRateStar
            },
            success: function (response) {
                $('#modal-comment').modal('hide');

                swal.fire({
                    "title": 'عملیات موفق',
                    "text": 'نظر شما با موفقیت ثبت شد.',
                    "type": 'success',
                    confirmButtonColor: 'primary',
                    "confirmButtonText": "تایید"
                }).then(function () {
                    $('#review_comment-1');
                    $('#review_comment-1').val('');
                    $('#row-comment').attr('aria-expanded', false);
                    $('#row-comment').addClass("collapsed");
                    $('#collapseOne').removeClass("show");
                    showCommentBtnLoading(false);
                });
            },
            error: function () {
                showCommentBtnLoading(false);
            }
        });

    };

    let sendToSpecialPage = function () {
        $('#special-btn').on('click', function () {

            let url = '/special?';
            url += ((region.length == 1) ? '&ci=' + region[0] : '');
            url += ((providerCategory.length == 1 && providerCategory[0] != 0) ? '&ca=' + providerCategory[0] : '');
            url += ((providerId.length == 1) ? '&pr=' + providerId[0] : '');
            window.location = url;
        })
    }
    let getSelect2Options = function () {
        $.ajax({
            url: "/api/shop/special/filter",
            method: "GET",
            contentType: false,
            data: null,
            success: function (response) {
                categories = response.categories;
                providers = response.providers;
                let selectedRegionCategory = findCategories(region, categories);
                setCategoriesType(selectedRegionCategory);
                setProvidersName(region, providerCategory, providers);
            },
            error: function () {
            }
        });
    };
    let findCategories = function (parent, list) {
        let categorySet = new Set();
        let child = [];

        // if (parent[0] == 0) {
        //     for(var key in list) {
        //          list[key].forEach(item =>{
        //              if (!categorySet.has(item.id)) {
        //                  child.push(item);
        //                  categorySet.add(item.id);
        //              }
        //          });
        //     }
        // } else {
            parent.forEach(index => {
                if (list[index]) {
                    list[index].forEach(item => {
                        if (!categorySet.has(item.id)) {
                            child.push(item);
                            categorySet.add(item.id);
                        }
                    });
                }

            });
        // }
        console.log('child', child)
        return child;
    };

    return {
        // public functions
        // actionForModalValue();
        init: function () {
            filterScroll();

            sendToSpecialPage();
            itemCard();
            expandRegions();
            filters();
            decreaseValueIncreaseValue();
            tagFilter();
            addToCart();
            submitNavBar();
            submitPrice();
            setModalData();

            // searching();
            // changeUrl();

            initPageFooterClick();
            productDetail();

            $('.mt-nice-select').niceSelect();
            let getFirst = [providerDetail.id];
            getProDetail(getFirst);


            initComment();
            getSelect2Options();
            $('#region_category').val(region);
            $('input[name="search"]').text($('#param').data('search'));
            $('input[name="search"]').val($('#param').data('search'));
        }
    };
}
();
jQuery(document).ready(function () {
    shopPage.init();
});
