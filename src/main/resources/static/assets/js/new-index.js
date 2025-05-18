$(document).ready(function() {

    $(window).scroll(function() {

        if ($(document).scrollTop() > 60) {
            $(".upper-header").addClass("box-shadow");
        } else {
            $(".upper-header").removeClass("box-shadow");
        }

    });

});



$(document).ready(function(){
    $(".hover-zoom").blowup({
        width : 150, // طول لنز
        height : 150, // ارتفاع لنز
        // scale : 10,  //مقیاس بزرگنمایی
        background : '#00000000', // رنگ پس زمینه در صورتی که از تصویر بیرون رفت
        border : '1px solid white', // حاشیه لنز
        round : true, // اینکه لنز گرد باشد یا خیر
        shadow : 'none' // ویژگی های سایه
    });
})


var style = document.createElement('style');
style.innerText = 'body::after {\n' +
    '        content: "";\n' +
    '        display: block;\n' +
    '        position: fixed;\n' +
    '        width: 100%;\n' +
    '        height: 100%;\n' +
    '        background: rgba(127,127,127,.3);\n' +
    '        top: 0;\n' +
    '    }';


function openNav() {
    document.getElementById("mobile-menu").style.width = "75%";
    document.getElementById("categoryPage").appendChild(style);
}

function closeNav() {
    document.getElementById("mobile-menu").style.width = "0";
    document.getElementById("categoryPage").removeChild(style);
}

function openFilter() {
    document.getElementById("ctg-filter").style.width = "100%";
}

function closeFilter() {
    document.getElementById("ctg-filter").style.width = "0";
}

function openSort() {
    document.getElementById("ctg-sort").style.width = "100%";
}

function closeSort() {
    document.getElementById("ctg-sort").style.width = "0";
}

function openComment() {
    document.getElementById("comment-confirm").style.width = "100%";
}

function closeComment() {
    document.getElementById("comment-confirm").style.width = "0";
}



function openNewAddress() {
}


function closeNewAddress() {
    document.getElementById("new-address-mobile").style.width = "0";
}

// function searchBoxOpen() {
//     document.getElementById("search-input").appendChild(style);
// }
//
// function searchBoxClose() {
//     document.getElementById("search-input").removeChild(style);
// }

function newCode() {
    document.getElementById("code-again").style.display = "none";
    document.getElementById("new-code").style.display = "block";
}

//
// document.getElementById("toastbtn").onclick = function() {
//     var myAlert =document.getElementById('toastNotice');//select id of toast
//     var bsAlert = new bootstrap.Toast(myAlert);//inizialize it
//     bsAlert.show();//show it
// }






