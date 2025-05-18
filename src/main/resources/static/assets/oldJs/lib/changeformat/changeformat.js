function formatDollar(num) {
    //
    let p = num.toFixed(2).split(".");

    return p[0].split("").reverse().reduce(function (acc, num, i, orig) {

        return num == "-" ? acc : num + (i && !(i % 3) ? "," : "") + acc;

    }, " تومان ");
}

$.fn.digits = function(){
    return this.each(function(){
        $(this).text( $(this).text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") );
    })
}
