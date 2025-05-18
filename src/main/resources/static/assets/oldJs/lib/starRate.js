let starRatingV2 = function (starrate) {
    // not working fake sag
    let str = "<div rating=\"true\" dir='rtl' style=\"color: rgb(252, 215, 3); \" >";
    if (starrate != 0) {
        let mot = Math.ceil(starrate);
        mot = 5 - starrate;
        for (let j = 0; j < mot; j++) {
            str += "<i class='starterRating fa fa-star rate-color'></i>";
        }
        if (Math.floor(starrate) !== Math.ceil(starrate)) {
            str += "<i class=\"starterRating fa fa-star-half-o rate-color\" aria-hidden=\"true\"></i>";
        }
        for (let i = 0; i < Math.floor(starrate); i++) {
            str += "<i class='starterRating fa fa-star rate-color'></i>";

        }

    } else {
        for (let j = 0; j < 5; j++) {
            str += "<i class=\"starterRating fa fa-star rate-color\" aria-hidden=\"true\"></i>";
        }
    }
    str += "</div>";

    return str;
};

let starRatingJustLi = function (starrate) {
    // not working fake sag
    let str = '';
    if (starrate != 0) {
        let mot = Math.ceil(starrate);
        mot = 5 - starrate;
        for (let j = 0; j < mot; j++) {
            str += '<li><i class="fa fa-star-o"></i></li>';
        }
        if (Math.floor(starrate) !== Math.ceil(starrate)) {
            str += '<li><i class="fa fa-star-half-o" ></i></li>';
        }
        for (let i = 0; i < Math.floor(starrate); i++) {
            str += '<li><i class="fa fa-star"></i></li>';

        }

    } else {
        for (let j = 0; j < 5; j++) {
            str += '<li><i class="fa fa-star-o" ></i></li>';
        }
    }

    return str;
};

let starRatingJustDiv = function (starrate) {

    let str = "";
    if (starrate != 0) {
        let mot = Math.ceil(starrate);
        mot = 5 - starrate;
        for (let j = 0; j < mot; j++) {
            str += "<div class='fa fa-star-o'></div>";
        }
        if (Math.floor(starrate) !== Math.ceil(starrate)) {
            str += "<div class='fa fa-star-half-o'></div>";
        }
        for (let i = 0; i < Math.floor(starrate); i++) {
            str += "<div class='fa fa-star'></div>";

        }


    } else {
        for (let j = 0; j < 5; j++) {
            str += "<div class='fa fa-star-o'></div>";
        }
    }
    str += "";

    return str;
};

let starRating = function (starrate) {
    let str = "<div class='mt-2'>";
    if (starrate != 0) {
        let mot = Math.ceil(starrate);
        mot = 5 - starrate;
        for (let j = 0; j < mot; j++) {
            str += "<div class='starterRating fa fa-star rate-color'></div>";
        }
        for (let i = 0; i < Math.floor(starrate); i++) {
            str += "<div class='starRating fa fa-star  '></div>";

        }
        if (Math.floor(starrate) !== Math.ceil(starrate)) {
            str += "<div class='starterRating fa fa-star-half-empty rate-color'></div>";
        }

    } else {
        for (let j = 0; j < 5; j++) {
            str += "<div class='starterRating fa fa-star rate-color'></div>";
        }
    }
    str += "</div>";

    return str;
};
