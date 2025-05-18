let convertPtoE = function (pertxt) {
    let newStrimg = "";
    let per = ['۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'];
    for (let i = 0; i < pertxt.length; i++) {
        for (let ch = 0; ch < per.length; ch++) {
            if (pertxt[i] == per[ch]) {
                newStrimg += ch;
                break;
            }
        }
    }
    if (newStrimg != "") {
        return newStrimg;
    } else {
        return pertxt;
    }
};
/*
let ParsconvertPtoE = function (pertxt) {
    let newStrimg = "";
    let per = ['۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'];
    for (let i = 0; i < pertxt.length; i++) {
        for (let ch = 0; ch < per.length; ch++) {
            if (pertxt[i] == per[ch]) {
                newStrimg += ch;
                break;
            }
        }
    }
    if (newStrimg != "") {
        return newStrimg;
    } else {
        return parseInt(pertxt);
    }
};*/
