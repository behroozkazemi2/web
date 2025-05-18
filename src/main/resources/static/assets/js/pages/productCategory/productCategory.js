let productCategoryPage = function () {


    let page = 1;
    let filtrationClicked = false;

    let successResponse = false;

    let defaultBrand = [];
    let firstTimeGetData = true;
    let appendBrands = function (defaultBrands, selectedBrands) {

        $('.filter-checkbox').empty();
        defaultBrands.forEach(item => {

            let checked = false;
            selectedBrands.forEach(index => {
                (parseInt(index) == parseInt(item.id)) ? checked = true : '';
            });
            let outPut = '' +
                '<div class="filter-checkbox-item">\n' +
                '                                    <input data-text ="' + item.name + '" ' + (checked ? "checked" : "") + ' id="brand_' + item.id + '" type="checkbox" class="brand_filter brand_' + item.id + '"  name="item1" value="' + item.id + '">\n' +
                '                                    <label for="item1"> ' + item.name + '  </label>\n' +
                '                                </div>';
            $('.filter-checkbox').append(outPut);
        });


        $('.filter-search').on('keyup', function () {
            let search = $(this).val();
            let flagIsAllDNone = true;

            $($('.filter-checkbox').find('.brand-not-fount')).remove();

            $('.filter-checkbox-item').each(function () {
                $(this).addClass('d-none');
                let brName = $($(this).find('input')).data('text');
                if (brName.includes(search)){
                    flagIsAllDNone = false;
                    $(this).removeClass('d-none');
                }
            })
            if (flagIsAllDNone){
                $('.filter-checkbox').append(
                    '<div class="filter-checkbox-item brand-not-fount">\n' +
                    '                                    <label for="item1"> برندی یافت نشد.  </label>\n' +
                    '                                </div>'
                )
            }
        })
    };

    let submitFilters = function () {
        $('.submit-filters').on('click', function () {
            page = 1;
            successResponse = false;
            filtrationClicked = true;
            appendCartToTable();
        });


    };

    let pageChange = function () {
        $('#pagination').on('click', '.page_link_filter', function () {
            page = $(this).data('page');
            $(this).addClass('active');
            appendCartToTable();
        });
        $('#pagination').on('click', '.page_back', function () {
            let elemnt = $('.pagination').find('.active_page_filter');
            if ($(elemnt).data('page') != 1) {
                page = $(elemnt).data('page') - 1;
                $(elemnt).addClass('active');
                appendCartToTable();
            }
        });
        $('#pagination').on('click', '.page_next', function () {
            let elemnt = $('.pagination').find('.active_page_filter');
            let pageCount = $('.pagination').find('.page_link_filter').length;
            if ($(elemnt).data('page') != pageCount) {
                page = $(elemnt).data('page') + 1;
                $(elemnt).addClass('active');
                appendCartToTable();
            }
        });
    };

    let orderAction = function () {
        $('.sort-btn').on('click', function () {
            $('.sort-btn').each(function () {
                $(this).removeClass('active');
            });
            $(this).addClass('active');
            appendCartToTable();
        });
    };

    let appendCartToTable = function () {

        let listBrands = [];
        let availableProduct = false;
        let minPrice = 0;
        let maxPrice = 90_000_000;

        if (filtrationClicked) {
            listBrands = getSelectedBrands();

            let existenceProductCheck = false;
            $('.available-product').each(function () {
                if ($(this).prop('checked')) {
                    existenceProductCheck = true;
                }
            })
            availableProduct = existenceProductCheck;
            minPrice = $('#first-price').val();
            maxPrice = $('#last-price').val();
            minPrice = (Number(minPrice.replace(/[^0-9.-]+/g, "")));
            maxPrice = (Number(maxPrice.replace(/[^0-9.-]+/g, "")));
        }


        let orderShow = $($('.sort-content').find('.active')).data('value');

        let tagList = [];
        tagList.push(Number($('#tagId').val()));
        listBrands.push(Number($('#brandId').val()));
        let data = {
            pvdId: 0,
            maxP: Number(maxPrice),
            minP: Number(minPrice),
            rg: 0,
            pdtCat: getCategoryList(),
            order: orderShow,
            tags: tagList,
            srch: $('#searchString').val() ?? "",
            draw: 9,
            start: page - 1,
            length: 9,
            existence: availableProduct ? availableProduct : false,
            brands: listBrands ? listBrands : []
        };

        $.ajax({
            "url": "/api/product/search",
            "data": data,
            "success": function (res) {
                if (res.result) {
                    let resultJson = JSON.parse(res.payload);

                    if (resultJson.data.length != 0) {
                        $('.product-box').empty();
                        let brands = [];
                        let categories = [];

                        resultJson.data.forEach(item => {
                            let imageSrc = "";
                            if (item.images.length == 0) {
                                imageSrc = '/assets/behta_logo/BehtaTahvie.png';
                            } else {
                                imageSrc = '/thumbnail/files/0/' + item.images[0];
                            }

                            let flagAdded = false;
                            for (let i = 0; i < brands.length; i++) {
                                if (item.brands.id == brands[i].id) {
                                    flagAdded = true;
                                }
                            }
                            if (!flagAdded) {
                                brands.push(item.brands);
                            }

                            let flagAddedCategory = false;
                            for (let i = 0; i < categories.length; i++) {
                                if (item.category.id == categories[i].id) {
                                    flagAddedCategory = true;
                                }
                            }
                            if (!flagAddedCategory) {
                                categories.push(item.category);
                            }
                            let productName = item.name;
                            productName = removeHalfSpace(productName);
                            let outPut = '' +
                                '                  <div style="opacity: ' + (item.existence ? "1" : "0.5") + ' "  class="product-item">\n' +
                                '                                <a href="/product/' + item.id + '/' + productName + '" style="min-height: 200px; max-height: 200px;align-items: center; align-content: center"><img class="rounded" src="' + imageSrc + '" alt="' + item.name + '"></a>\n' +
                                '                                <div class="product-content">\n' +
                                '                                    <span><a href="/product/' + item.id + '/' + productName + '" >' + item.name + '</a></span>\n' +
                                '                                    <div class="product-score">\n' +
                                '                                        <span class="star"><i class="fa fa-star"></i></span>\n' +
                                '                                        <span class="vote">' + item.rate + ' </span>\n' +
                                // '                                        <span class="count">( ' + item.countRate + ' )</span>\n' +
                                '                                    </div>\n' +
                                '                                    <div class="product-footer">\n' +
                                '                                        <div class="product-cart">\n' +
                                (!item.existence ? ' ' :
                                        '                                            <a  data-id="' + item.id + '" href="javascript:;"  style="cursor: pointer" class="add-to-cart increaseProductProviderCount" >\n' +
                                        '                                                <i class="fa fa-cart-plus"></i>\n' +
                                        '\n' +
                                        '                                            </a>\n'
                                ) +
                                '                                        </div>\n' +
                                '                                        <div class="product-cost">\n' +
                                (item.offPercent == 0 ? "" :
                                        '                                            <div class="product-del" >\n' +
                                        '                                                <del>' + number_format(item.primitiveAmount) + ' تومان</del>\n' +
                                        '                                                <span class="percent">' + item.offPercent + '%</span>\n' +
                                        '                                            </div>\n'
                                ) +
                                '                                            <div class="product-price">\n' +
                                '                                                <span class="price">' + number_format(item.finalAmount) + ' تومان</span>\n' +
                                '                                            </div>\n' +
                                '                                        </div>\n' +
                                '                                    </div>\n' +
                                '                                </div>\n' +
                                '<div class="product-main">\n' +
                                '                                    <div class="product-content">\n' +
                                '                                        <span><a href="/product/' + item.id + '/' + productName + '">' + item.name + '</a></span>\n' +
                                '                                        <div class="product-score">\n' +
                                '                                            <span class="star"><i class="fa fa-star"></i></span>\n' +
                                '                                            <span class="vote">' + item.rate + ' </span>\n' +
                                // '                                            <span class="count">(22)</span>\n' +
                                '                                        </div>\n' +
                                '                                    </div>\n' +
                                '                                    <div class="product-footer">\n' +
                                '                                        <div class="product-cart">\n' +

                                (!item.existence ? ' ' :
                                        '                                            <a  data-id="' + item.id + '" href="javascript:;"  style="cursor: pointer" class="add-to-cart increaseProductProviderCount" >\n' +
                                        '                                                <i class="fa fa-cart-plus"></i>\n' +
                                        '\n' +
                                        '                                            </a>\n'
                                ) +
                                '                                        </div>\n' +
                                '                                        <div class="product-cost">\n' +
                                '                                            <div class="product-del">\n' +
                                '                                                <del>' + number_format(item.primitiveAmount) + ' تومان</del>\n' +
                                '                                                <span class="percent">' + item.offPercent + '%</span>\n' +
                                '                                            </div>\n' +
                                '                                            <div class="product-price">\n' +
                                '                                                <span class="price">' + number_format(item.finalAmount) + ' تومان</span>\n' +
                                '                                            </div>\n' +
                                '                                        </div>\n' +
                                '                                    </div>\n' +
                                '                                </div>\n' +
                                '                            </div> </a>\n';

                            $('.product-box').append(outPut);
                        });

                        $('.pagination').empty();

                        let totalPage = Math.ceil(resultJson.recordsTotal / 9);

                        index.pagination(page, totalPage, "pagination");


                        successResponse = true;

                        if (($('#brandId').val() != 0 || $('#tagId').val() != 0)) {
                            appendCategoriesForSearchedBrandOrTag(categories);
                        }
                        if (firstTimeGetData) {
                            // firstTimeGetData = !firstTimeGetData;
                            defaultBrand = brands;
                            appendBrands(defaultBrand, listBrands);
                        }


                        $('.product-items').empty();
                        $('#not-found').addClass('d-none');


                        window.location = '#pic_carosul'
                    }else {
                        $('.product-box').empty();

                        let outPut = '                        <div class="empty-content box-shadow ">\n' +
                            '                            <div class="ctg-empty d-flex flex-column justify-content-center align-items-center text-center">\n' +
                            '                                <img src="/assets/images/icons/48.svg" alt=" بهتاتهویه">\n' +
                            '                                <span>متأسفانه در این قسمت کالایی برای سفارش وجود ندارد.</span>\n' +
                            '                                <a href="/" target="_parent" id="back-to-home" class="btn btn-info">بازگشت به صفحه اصلی</a>\n' +
                            '                            </div>\n' +
                            '                        </div>\n'
                        $('.product-box').append(outPut);

                    }
                } else {
                }
            },
            beforeSend: function () {
                $('.loading-box').removeClass('d-none');
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
                if ($('.checkIsAnyProductInArea').val() != undefined && $('.checkIsAnyProductInArea').val() != null && !$('.checkIsAnyProductInArea').val()) {
                    removeAllAddToCartBtnInPages();
                }

                // append filter to Location
                $('.loading-box').addClass('d-none');

                if (successResponse) {
                    // append brand
                    $('.filters_holder').empty();
                    $('.brand_filter').each(function () {
                        if ($(this).is(':checked')) {

                            console.log("asdsaad");
                            let name = $(this).data('text');
                            let id = $(this).val();
                            let outPut = '' +
                                '                <button data-type="brands" data-value="' + id + '" class="applied-filter close searched_filters" type="alert" data-dismiss="alert"><span>\n' +
                                '                           <!-- <svg xmlns="http://www.w3.org/2000/svg" width="10.062" height="10.062" viewBox="0 0 10.062 10.062">\n' +
                                '      <path id="Path_2079" data-name="Path 2079" d="M10.062-12.994,9.055-14,5.031-9.975,1.006-14,0-12.994,4.025-8.969,0-4.945,1.006-3.938,5.031-7.963,9.055-3.938l1.006-1.006L6.037-8.969Z" transform="translate(0 14)" fill="#457b9d" fill-rule="evenodd"/>\n' +
                                '    </svg>-->\n' +
                                '                        ' + name + '</span>&times\n' +
                                '                        </button>';


                            let appendBeforeFromWebSize = false
                            $('.filters_holder').find('.searched_filters').each(function () {
                                if ($(this).data('value') == id) {
                                    appendBeforeFromWebSize = true;
                                }
                            });
                            if (!appendBeforeFromWebSize) {
                                $('.filters_holder').append(outPut);
                            }

                        }
                    });

                    // append category
                    $('.hasCheckBox').empty();
                    $('.hasCheckBox').each(function () {
                        if ($(this).is(':checked')) {
                            console.log($(this).data('name'));

                            let name = $(this).data('name');
                            let id = $(this).data('id');
                            let outPut = '' +
                                '                <button data-type="brands" data-value="' + id + '" class="applied-filter close searched_filters" type="alert" data-dismiss="alert"><span>\n' +
                                '                        ' + name + '</span>&times\n' +
                                '                        </button>';

                            let appendBeforeFromWebSize = false
                            $('.filters_holder').find('.searched_filters').each(function () {
                                if ($(this).data('value') == id) {
                                    appendBeforeFromWebSize = true;
                                }
                            });
                            if (!appendBeforeFromWebSize) {
                                $('.filters_holder').append(outPut);
                            }

                        }
                    });



                    // append available Product
                    let existenceProductCheck = false;
                    $('.available-product').each(function () {
                        if ($(this).prop('checked')) {
                            existenceProductCheck = true;
                        }
                    })
                    if (existenceProductCheck) {
                        let outPut = '' +
                            '                <button data-type="available"  class="applied-filter close" type="alert" data-dismiss="alert"><span>\n' +
                            '                           <!-- <svg xmlns="http://www.w3.org/2000/svg" width="10.062" height="10.062" viewBox="0 0 10.062 10.062">\n' +
                            '      <path id="Path_2079" data-name="Path 2079" d="M10.062-12.994,9.055-14,5.031-9.975,1.006-14,0-12.994,4.025-8.969,0-4.945,1.006-3.938,5.031-7.963,9.055-3.938l1.006-1.006L6.037-8.969Z" transform="translate(0 14)" fill="#457b9d" fill-rule="evenodd"/>\n' +
                            '    </svg>-->\n' +
                            '                        فقط کالا های موجود</span>&times\n' +
                            '                        </button>';

                        $('.filters_holder').append(outPut);
                    };


                    appendUrlParametersToSearchedFilters();
                };
                removeSelectedFilter();
                refreshByCategoryId();

            }
        });


    };

    let appendCategoriesForSearchedBrandOrTag = function (categories) {

        let outPut = '';

        $('.category_list').empty();

        categories.forEach(item => {
            outPut += '' +
                '<div>\n' +
                '      <a style="cursor: pointer" data-id="' + item.id + '" data-name="' + item.name + '" class="product-breadcrumb-lastitem category_filter_' + item.id + '">' + item.name + '</a>\n' +
                '</div>'
        });
        $('.category_list').append(outPut);
    }

    let appendUrlParametersToSearchedFilters = function () {

        let categoryName = $('#categoryName').val() != null && $('#categoryName').val() != '' ? $('#categoryName').val() : 'all';
        let categoryId = $('#categoryId').val() != 0 ? $('#categoryId').val() : '0';

        let brandName = $('#brandName').val() != null && $('#brandName').val() != '' ? $('#brandName').val() : 'all';
        let brandId = $('#brandId').val() != 0 ? $('#brandId').val() : '0';

        let tagName = $('#tagName').val() != null && $('#tagName').val() != '' ? ($('#tagName').val()) : 'all';
        let tagId = $('#tagId').val() != 0 ? $('#tagId').val() : '0';

        let stringSearch = $('#searchString').val() != null && $('#searchString').val() != '' ? $('#searchString').val() : '';

        if ($('#categoryName').val() != null && $('#categoryName').val() != '' && $('#categoryName').val() != 'all' && $('#categoryId').val() != 0) {
            let outPut = '' +
                '                <button data-type="category-searched" data-value="' + categoryId + '" class="applied-filter close searched_filters" type="alert" data-dismiss="alert"><span>\n' +
                '                           <!-- <svg xmlns="http://www.w3.org/2000/svg" width="10.062" height="10.062" viewBox="0 0 10.062 10.062">\n' +
                '      <path id="Path_2079" data-name="Path 2079" d="M10.062-12.994,9.055-14,5.031-9.975,1.006-14,0-12.994,4.025-8.969,0-4.945,1.006-3.938,5.031-7.963,9.055-3.938l1.006-1.006L6.037-8.969Z" transform="translate(0 14)" fill="#457b9d" fill-rule="evenodd"/>\n' +
                '    </svg>-->\n' +
                '                        ' + categoryName + '</span>&times\n' +
                '                        </button>';

            $('.filters_holder').append(outPut);
        }
        if ($('#brandName').val() != null && $('#brandName').val() != '' && $('#brandId').val() != 0) {
            let outPut = '' +
                '                <button data-type="brand-searched" data-value="' + brandId + '" class="applied-filter close searched_filters" type="alert" data-dismiss="alert"><span>\n' +
                '                           <!-- <svg xmlns="http://www.w3.org/2000/svg" width="10.062" height="10.062" viewBox="0 0 10.062 10.062">\n' +
                '      <path id="Path_2079" data-name="Path 2079" d="M10.062-12.994,9.055-14,5.031-9.975,1.006-14,0-12.994,4.025-8.969,0-4.945,1.006-3.938,5.031-7.963,9.055-3.938l1.006-1.006L6.037-8.969Z" transform="translate(0 14)" fill="#457b9d" fill-rule="evenodd"/>\n' +
                '    </svg>-->\n' +
                '                        ' + brandName + '</span>&times\n' +
                '                        </button>';

            $('.filters_holder').append(outPut);
        }

        if ($('#tagName').val() != null && $('#tagName').val() != '' && $('#tagId').val() != 0) {
            let outPut = '' +
                '                <button data-type="tag-searched" data-value="' + tagId + '" class="applied-filter close searched_filters" type="alert" data-dismiss="alert"><span>\n' +
                '                           <!-- <svg xmlns="http://www.w3.org/2000/svg" width="10.062" height="10.062" viewBox="0 0 10.062 10.062">\n' +
                '      <path id="Path_2079" data-name="Path 2079" d="M10.062-12.994,9.055-14,5.031-9.975,1.006-14,0-12.994,4.025-8.969,0-4.945,1.006-3.938,5.031-7.963,9.055-3.938l1.006-1.006L6.037-8.969Z" transform="translate(0 14)" fill="#457b9d" fill-rule="evenodd"/>\n' +
                '    </svg>-->\n' +
                '                        ' + tagName + '</span>&times\n' +
                '                        </button>';

            $('.filters_holder').append(outPut);
        }

        if ($('#searchString').val() != null && $('#searchString').val() != '') {
            let outPut = '' +
                '                <button data-type="string-searched" data-value="' + tagId + '" class="applied-filter close searched_filters" type="alert" data-dismiss="alert"><span>\n' +
                '                           <!-- <svg xmlns="http://www.w3.org/2000/svg" width="10.062" height="10.062" viewBox="0 0 10.062 10.062">\n' +
                '      <path id="Path_2079" data-name="Path 2079" d="M10.062-12.994,9.055-14,5.031-9.975,1.006-14,0-12.994,4.025-8.969,0-4.945,1.006-3.938,5.031-7.963,9.055-3.938l1.006-1.006L6.037-8.969Z" transform="translate(0 14)" fill="#457b9d" fill-rule="evenodd"/>\n' +
                '    </svg>-->\n' +
                '                        ' + stringSearch + '</span>&times\n' +
                '                        </button>';

            $('.filters_holder').append(outPut);
        }


    }
    let getCategoryList = function () {
        let list = [];
        $('.ctg-section').find('.hasCheckBox').each(function () {
            var elem = $(this);
            if (elem.prop('checked')){
                if (!list.includes($(this).data('id'))) {
                    list.push($(this).data('id'));
                }
            }

        });

        if ($('#categoryId').val() !== 0)
            list.push($('#categoryId').val())
        return list;
    };

    let refreshByCategoryId = function () {
        $('.product-breadcrumb-item').on('click', function () {
            let catName = $(this).data('name');
            catName = removeHalfSpace(catName);

            let brandName = $('#brandName').val() != null && $('#brandName').val() != '' ? removeHalfSpace($('#brandName').val()) : 'all';
            let brandId = $('#brandId').val() != 0 ? $('#brandId').val() : '0';

            let tagName = $('#tagName').val() != null && $('#tagName').val() != '' ? removeHalfSpace($('#tagName').val()) : 'all';
            let tagId = $('#tagId').val() != 0 ? $('#tagId').val() : '0';

            let stringSearch = $('#searchString').val() != null && $('#searchString').val() != '' ? '&searchString=' + $('#searchString').val() : '';

            let url = '/productCategory/' + brandName + '/' + tagName + '/' + catName + '?categoryId=' + $(this).data('id') + '&brandId=' + brandId + '&tagId=' + tagId + stringSearch;
            window.location = url;

        });
        $('.product-breadcrumb-lastitem').on('click', function () {
            let catName = $(this).data('name');
            catName = removeHalfSpace(catName);

            let brandName = $('#brandName').val() != null && $('#brandName').val() != '' ? removeHalfSpace($('#brandName').val()) : 'all';
            let brandId = $('#brandId').val() != 0 ? $('#brandId').val() : '0';

            let tagName = $('#tagName').val() != null && $('#tagName').val() != '' ? removeHalfSpace($('#tagName').val()) : 'all';
            let tagId = $('#tagId').val() != 0 ? $('#tagId').val() : '0';


            let stringSearch = $('#searchString').val() != null && $('#searchString').val() != '' ? '&searchString=' + $('#searchString').val() : '';

            let url = '/productCategory/' + brandName + '/' + tagName + '/' + catName + '?categoryId=' + $(this).data('id') + '&brandId=' + brandId + '&tagId=' + tagId + stringSearch;
            window.location = url;


            // $('.product-breadcrumb').find('.active_category').removeClass('active_category');
            // $(this).addClass('active_category');
            // let imageSrc = '/thumbnail/files/0/' + $(this).data('image-id')
            //
            // $('.ctg-name').text($(this).text());
            // $('.category_description').text($(this).data('desc'));
            // $('.category_image_id').attr('src', imageSrc);
            // page = 1;
            // firstTimeGetData = true;
            // successResponse = false;
            // appendCartToTable();
        });
    };
    let removeSelectedFilter = function () {

        $('.applied-filter').on('click', function (e) {
            e.preventDefault();
            let type = $(this).data('type');

            let flagIsParentCategory = false;
            switch (type) {
                case "brands":
                    let id = $(this).data('value');

                    let BrandId = "brand_" + parseInt(id);
                    $('.' + BrandId + '').each(function () {
                        $(this).prop('checked', false);
                    });
                    break;
                case "available":
                    $('.available-product').each(function () {
                        $(this).prop('checked', false);
                    })
                    break;
                case "brand-searched":
                    $('#brandId').val(0);
                    $('#brandName').val('');
                    break;
                case "category-searched":
                    let catId = $(this).data('value');
                    let catElementId = 'category_filter_' + catId;
                    $('.' + catElementId + '').each(function () {
                        flagIsParentCategory = $(this).hasClass('parent_category_filter');
                    })
                    if (!flagIsParentCategory) {
                        $('#categoryId').val(0);
                        $('#categoryName').val('');
                        $('.' + catElementId + '').closest('div').each(function () {
                            $(this).find('.active_category').each(function () {
                                $(this).removeClass('active_category')
                            });
                        });
                        $('.' + catElementId + '').removeClass('active_category');

                        $('.' + catElementId + '').each(function () {
                            let parentEl = $($(this).parents()[1]);
                            if ($(parentEl)) {
                                $(parentEl).find('.category_filter').each(function () {
                                    if ($(this).hasClass('parent_category_filter')) {
                                        $('#categoryId').val($(this).data('id'));
                                        $('#categoryName').val($(this).data('name'));
                                    }
                                    $(this).addClass('active_category')
                                })
                                // $(parentEl).addClass('active_category');
                            }
                        })

                    }

                    break;
                case "tag-searched":
                    $('#tagName').val('');
                    $('#tagId').val(0);
                    break;
                case "string-searched":
                    $('#searchString').val('');
                    break;
                default :
            }
            if (!flagIsParentCategory) {
                $(this).remove();
            }
        });
    };

    let getSelectedBrands = function () {

        let brands = [];
        $('.brand_filter').each(function () {
            if ($(this).is(':checked')) {
                if (!brands.includes(this.value)) {
                    brands.push(this.value);
                }
            }

        });

        return brands
    };


    let removeAllFilters = function () {
        $('#remove-all').on('click', function () {
            // brands
            $('.brand_filter').each(function () {
                $(this).prop('checked', false);
            });


          // category
            $('.hasCheckBox').each(function () {
                $(this).prop('checked', false);
            });

            $('#categoryId').val(0)
            $('#categoryId').val(0)

            //available-product
            $('.available-product').prop('checked', false);

            // price
            $('#first-price').val(number_format(10));
            $('#last-price').val(number_format(90000000));

            appendCartToTable();
        });

    };


    /*---slider-range here---*/

    let initSlider = function () {
        $("#slider, .custom-range").slider({
            range: true,
            min: 0,
            step: 5000,
            max: 90000000,
            direction: "ltr",
            values: [0, 90000000],
            slide: function (event, ui) {
                $('.firstPrice').each(function () {
                    $(this).val(number_format(ui.values[0]));
                    $(this).text(number_format(ui.values[0]));
                });
                $('.lastPrice').each(function () {
                    $(this).val(number_format(ui.values[1]));
                    $(this).text(number_format(ui.values[1]));
                });
            }
        });

    };
    let number_format = function (total) {
        return total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };


    let unSelectBrand = function () {
        $('.content').on('change', '.brand_filter', function () {
            let id = "brand_" + $(this).val();
            $('#' + id + '').prop('checked', false);
        });
    }

    let checkTheInputForClickOnLi = function (){
        $('.hasNochild').on('click',function(){
            var elem = $(this).find('.hasCheckBox');
            elem.prop('checked', !elem.is(':checked'));
        })
    }
    return {
        // public functions
        init: function () {
            index.init();
            initSlider();
            orderAction();
            submitFilters();
            checkTheInputForClickOnLi();
            appendCartToTable();
            pageChange();
            removeAllFilters();
            unSelectBrand();

        }
    };
}();

jQuery(document).ready(function () {
    productCategoryPage.init();
});