var ticketJs = function () {

    let isAdmin;
    let searchBtn = $(".search_btn");
    let modal = $(".kt_modal_remote");
    let validate;

    let ticketTable;


    let initSelect2 = function () {

        $('.has-select2').select2({
            placeholder: "انتخاب کنید",
            autoComplete: true,
            width: '100%'
        });

        $('.response-select2').select2({
            placeholder: "وضعیت پاسخ",
            autoComplete: false,
            allowClear: true,
            width: '100%'
        });

        $('.ticket_close').select2({
            placeholder: "وضعیت تیکت",
            autoComplete: false,
            allowClear: true,
            width: '100%'
        });


    };
    let initDatePicker = function () {
        $('.submit_date_show').MdPersianDateTimePicker({
            targetTextSelector: '.submit_date_show',
            targetDateSelector: '.submit_date',
            enableTimePicker: false
        });
        $('.submit_to_date_show').MdPersianDateTimePicker({
            targetTextSelector: '.submit_to_date_show',
            targetDateSelector: '.submit_to_date',
            enableTimePicker: false
        });

        $('.last_msg_date_show').MdPersianDateTimePicker({
            targetTextSelector: '.last_msg_date_show',
            targetDateSelector: '.last_msg_date',
            enableTimePicker: false
        });
        $('.last_msg_to_date_show').MdPersianDateTimePicker({
            targetTextSelector: '.last_msg_to_date_show',
            targetDateSelector: '.last_msg_to_date',
            enableTimePicker: false
        });

    }
    let initProjectSearch = function (element) {

        $(element).select2({
            placeholder: "پروژه",
            allowClear: true,
            width: '100%',
            ajax: {
                url: "/user/projectList",
                type: 'post',
                delay: 250,
                data: function (params) {
                    return {
                        search: params.term, // search term
                        page: params.page
                    };
                },
                processResults: function (response, params) {
                    params.page = params.page || 1;
                    return {
                        results: response,
                        pagination: 15
                    };
                },
                cache: true
            },
            escapeMarkup: function (markup) {
                return markup;
            },
            templateResult: formatRepo, // omitted for brevity, see the source of this page
            templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
        });

        function formatRepo(repo) {
            if (repo.loading) return repo.text;
            let data = getImportanceClass(repo.id);
            return repo.name;
        };

        function formatRepoSelection(repo) {
            return repo.name || repo.text;
        };

    };

    let initTicketDataTable = function () {

        ticketTable = new DataTable('#ticket-table', {
            ajax: {
                responsive: true,
                rowReorder: {
                    selector: 'td:nth-child(2)'
                },
                url: '/api/ticket/list',
                type: "post",
                data: function (d) {
                    d.myKey = 'myValue';
                    // d.custom = $('#myInput').val();
                    // etc
                }
            },
            // processing: true,
            // serverSide: true,
            columns: [
                {
                    data: 'trackingCode'
                },
                {
                    data: 'ticketSubject'
                },
                {
                    data: 'insertDateStr'
                },
                {
                    data: 'status'
                }, {
                    data: 'operation'
                }
            ],
            columnDefs: [
                {"className": "dt-center", "targets": "_all"},
                {

                    data: 'status',
                    render: function(data, type, full, meta) {
                        console.log(full);
                        let divClass = full.closed ? 'kt-badge--info' : 'kt-badge--unified-info' ;
                        let text = full.closed ? 'بسته شده' : (full.read ? 'باز' : 'باز (پاسخ داده شده)' )  ;
                        let div = ' <span   class="kt-inbox__label  rounded-lg kt-badge  kt-badge--md kt-badge--bold kt-badge--inline '+ divClass +' ">' +
                            text +
                            '</span>' ;
                        return div
                    },
                    targets: -2
                },{
                    data: 'operation',
                    render: function(data, type, full, meta) {
                        let div = ' <a href="/ticket/'+full.trackingCode+'"> ' +
                            'نمایش' +
                            '</a>' ;
                        return div
                    },                    targets: -1
                }
            ],
            "initComplete": function (settings, json) {
                $('.loading-box').addClass('d-none');
            }
        });
        ticketTable.on('click', '.show_btn', function () {
            window.open("/ticket/" + $(this).attr('data-id') , "_blank");
        });

        $('#myInput').on('keyup', function () {
            ticketTable.search(this.value).draw();
        });

    };

    let getTicketList = function (){

    }
    let searchHandle = function () {
        searchBtn.on("click", function (evt) {
            // search();
            evt.preventDefault();
        });
        $(document).on('keypress', function (e) {
            if (e.which == 13) {
                // search();
            }
        });
    };
    let search = function () {
        ticketTable.setDataSourceParam("ticketImportance", $('.importance_search').val());
        ticketTable.setDataSourceParam("project", $('.project_search').val());
        ticketTable.setDataSourceParam("closed",  !isZero($('.closed_search').val()) ?  $('.closed_search').val() :0 );
        ticketTable.setDataSourceParam("responseType", !isZero($('.response_type_search').val()) ?  $('.response_type_search').val() :0);
        ticketTable.setDataSourceParam("lastMsgDate", !isZero($('.last_msg_date').val()) ?  new Date($('.last_msg_date').val()).getTime() :0);
        ticketTable.setDataSourceParam("lastMsgToDate", !isZero($('.last_msg_to_date').val()) ?  new Date($('.last_msg_to_date').val()).getTime() :0);
        ticketTable.setDataSourceParam("search", $('.general_search').val());
        ticketTable.reload();
        KTApp.progress(searchBtn);
        searchBtn.prop('disabled', true);
    };


    let initPersianDatePicker = function () {
        $('#date').MdPersianDateTimePicker({
            targetTextSelector: '#date',
            targetDateSelector: '#date_hidden',
            enableTimePicker: false
        });
    };


    let saveTicketAjax = function () {

        let btn = $('#save_ticket_btn');
        btn.on('click', function (e) {
            // if (validate.form()) {
                e.preventDefault();
                let data = new FormData();
                data.set('project', 1)
                data.set('text', $('.modal_text').val());
                data.set('importance', $('.modal_importance').val());
                data.set('subject', $('.modal_subject').val());
                // let docs = [];
                // $('.ticket_doc').each(function () {
                //     if (!isNull($(this).attr('data-id'))) {
                //         docs.push($(this).attr('data-id'))
                //     }
                // });
                // data.set('documents', docs);

                $.ajax({
                    url: '/api/ticket/save',
                    type: 'post',
                    data: data,
                    contentType: false,
                    processData: false,
                    beforeSend: function () {
                        $('#save_ticket_btn').addClass('disabled');
                    },
                    success: function (response) {
                        if (response.result) {
                            modal.modal('hide');
                            swal.fire({
                                "title": "",
                                'text': "ثبت با موفقیت انجام شد.",
                                "type": "success",
                                "confirmButtonClass": "",
                                "showCancelButton": true,
                                "cancelButtonColor": '#26a2dd',
                                "confirmButtonText": 'مشاهده',
                                "cancelButtonText": 'تایید',
                            }).then((result) => {
                                if (result.value) {
                                    window.open('/ticket/' + response.payload)
                                } else {

                                    ticketTable.reload();

                                }
                            });
                        } else {
                            swal.fire({
                                "title": "خطا",
                                'text': response.payload,
                                "type": "error",
                                "confirmButtonClass": "btn btn-warning"
                            });
                        }
                    },
                    error: function (jqXHR) {
                        $('#save_ticket_btn').removeClass('disabled');

                        if (jqXHR.status == 403) {
                            swal.fire({
                                title: 'خطا ',
                                text: "شما اجازه دسترسی برای انجام این عملیات را ندارید.",
                                // text: "سطح دسترسی شما برای انجام این عملیات پایین تر از سطح دسترسی مجاز می‌باشد.",
                                type: 'error',
                                confirmButtonText: 'تایید'
                            });
                        } else {
                            swal.fire({
                                title: 'خطا ',
                                text: "خطا در برقراری ارتباط",
                                type: 'error',
                                confirmButtonText: 'تایید'
                            });
                        }
                    },
                    complete: function () {
                        ticketTable.ajax.reload();

                        $('#save_ticket_btn').removeClass('disabled');
                    }
                });
            // }

        });
    };

    let saveTicketValidation = function () {
        validate = $('#save-ticket-form').validate({
            rules: {
                subject: {
                    required: true,
                },
                text: {
                    required: true,
                },
                importance: {
                    required: true,
                },
                project: {
                    required: true,
                },

            },
            messages: {
                project: {
                    required: 'لطفا پروژه را انتخاب کنید',
                },
                subject: {
                    required: "لطفا موضوع پروژه را وارد نمایید",
                },
                text: {
                    required: "لطفا متن تیکت را وارد نمایید",
                },
                importance: {
                    required: "لطفا درجه اهمیت تیکت را انتخاب کنید",
                },
            },
            invalidHandler: function (event, validator) {
                event.preventDefault();
            },
            submitHandler: function (form) {
            }
        });
    };

    var initAttachments = function (elemId) {

        var id = "#" + elemId;
        var previewNode = $(id + " .dropzone-item");
        previewNode.id = "";
        var previewTemplate = previewNode.parent('.dropzone-items').html();
        previewNode.remove();

        var myDropzone = new Dropzone(id, { // Make the whole body a dropzone
            url: "/file/upload", // Set the url for your upload script location
            parallelUploads: 20,
            timeout: (1000 * 60 * 3), // 3 minutes
            // acceptedFiles: "image/jpeg,image/png,image/gif,application/pdf,image/jpg",
            // maxFilesize: 2, // Max filesize in MB
            previewTemplate: previewTemplate,
            previewsContainer: id + " .dropzone-items", // Define the container to display the previews
            clickable: id + "_select" // Define the element that should be used as click trigger to select files.
        });

        myDropzone.on("addedfile", function (file) {
            // Hookup the start button
            $(document).find(id + ' .dropzone-item').css('display', '');
        });

        myDropzone.on("success", function (file, response) {

            if (response.result) {
                var data = JSON.parse(response.payload);
                $(file.previewElement).find('.dropzone-filename').addClass('ticket_doc').attr('data-id', data.id);
            }
            // Hookup the start button
        });

        // Update the total progress bar
        myDropzone.on("totaluploadprogress", function (progress) {
            document.querySelector(id + " .progress-bar").style.width = progress + "%";
        });

        myDropzone.on("sending", function (file) {
            // Show the total progress bar when upload starts
            document.querySelector(id + " .progress-bar").style.opacity = "1";
        });

        // Hide the total progress bar when nothing's uploading anymore
        myDropzone.on("complete", function (progress) {
            var thisProgressBar = id + " .dz-complete";
            setTimeout(function () {
                $(thisProgressBar + " .progress-bar, " + thisProgressBar + " .progress").css('opacity', '0');
            }, 300)
        });
    }

    let openTicketModal = function () {

        $(".add-new-ticket").on('click', function () {
            $.ajax({
                url: '/api/ticket/modal/add',
                dataType: "html",
                success: function (response) {
                    $($(document).find('.kt_modal_remote')).find('.modal-content').empty();
                    console.log("asdasdas");
                    console.log($($(document).find('.kt_modal_remote')));
                    $($(document).find('.kt_modal_remote')).find('.modal-content').append(response);
                    $($(document).find('.kt_modal_remote')).modal('show');
                    // saveTicketValidation();
                    // initAttachments('kt_inbox_compose_attachments');
                    saveTicketAjax();

                },
                error: function () {
                },
                complete: function () {

                }
            });
        })
    };

    let downloadErrorExcel = function (){
        $('#downloadExcel').on( "click", function(evt) {
            const queryParams = {
                ticketImportance:$('.importance_search').val(),
                project: $('.project_search').val(),
                closed: !isZero($('.closed_search').val()) ?  $('.closed_search').val() :0,
                responseType: !isZero($('.response_type_search').val()) ?  $('.response_type_search').val() :0,
                // submitDate: !isZero($('.submit_date').val()) ?  new Date().getTime($('.submit_date').val())  :0,
                // submitToDate: !isZero($('.submit_to_date').val()) ?  new Date().getTime($('.submit_to_date').val())  :0,
                lastMsgDate: !isZero($('.last_msg_date').val()) ?  new Date($('.last_msg_date').val()).getTime() :0,
                lastMsgToDate: !isZero($('.last_msg_to_date').val()) ?  new Date($('.last_msg_to_date').val()).getTime() :0,
                search: $('.general_search').val(),
            };
            const searchParams = new URLSearchParams(queryParams).toString();
            const href = `/${searchParams}`;
            window.open(href, '_blank');
        });
    }

    return {
        init: function () {
            $('.ticket_close').val('').trigger('change');
            $('.response-select2').val('').trigger('change');
            // initDatePicker();
            isAdmin = false;
            initSelect2();
            openTicketModal();
            initSelect2();
            initTicketDataTable();
            // initPersianDatePicker();
            // downloadErrorExcel();
            // setInterval(function() {
            //     initTicketDataTable();
            // }, 60000);
        },
    };
}
();

// On document ready
$(document).ready(function () {

});
