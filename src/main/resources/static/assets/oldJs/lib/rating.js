!function (t) {
    var e = {};

    function r(a) {
        if (e[a]) return e[a].exports;
        var s = e[a] = {i: a, l: !1, exports: {}};
        return t[a].call(s.exports, s, s.exports, r), s.l = !0, s.exports
    }

    r.m = t, r.c = e, r.d = function (t, e, a) {
        r.o(t, e) || Object.defineProperty(t, e, {enumerable: !0, get: a})
    }, r.r = function (t) {
        "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(t, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(t, "__esModule", {value: !0})
    }, r.t = function (t, e) {
        if (1 & e && (t = r(t)), 8 & e) return t;
        if (4 & e && "object" == typeof t && t && t.__esModule) return t;
        var a = Object.create(null);
        if (r.r(a), Object.defineProperty(a, "default", {
            enumerable: !0,
            value: t
        }), 2 & e && "string" != typeof t) for (var s in t) r.d(a, s, function (e) {
            return t[e]
        }.bind(null, s));
        return a
    }, r.n = function (t) {
        var e = t && t.__esModule ? function () {
            return t.default
        } : function () {
            return t
        };
        return r.d(e, "a", e), e
    }, r.o = function (t, e) {
        return Object.prototype.hasOwnProperty.call(t, e)
    }, r.p = "", r(r.s = 0)
}([function (t, e) {
    const r = {
        value: 0,
        stars: 5,
        half: !1,
        emptyStar: "fa fa-star-o",
        halfStar: "fa fa-star-half-o",
        filledStar: "fa fa-star",
        color: "#fcd703",
        readonly: !1,
        click: function (t) {
            console.error("No click callback provided!")
        }
    };
    jQuery.fn.extend({
        rating: function (t = {}) {
            return this.each((function () {
                $(this).attr("rating") && $(this).empty(), this.stars = t.value ? t.value : r.value, this.readonly = t.readonly ? t.readonly : r.readonly, this.getStars = function () {
                    return $(this).find($("i"))
                }, $(this).css({color: t.color ? t.color : r.color}).attr("rating", !0), this.readonly || ($(this).off("mousemove").on("mousemove", (function (e) {
                    let a = t.half ? t.half : r.half;
                    if (this.getStars().index(e.target) >= 0) if (a) {
                        $(this).find("i").attr("class", t.emptyStar ? t.emptyStar : r.emptyStar);
                        let a = .5;
                        $(this).find("i").css({width: $(this).find("i").outerWidth()}), e.offsetX > $(e.target).outerWidth() / 2 && (a = 1);
                        let s = this.getStars().index(e.target) + a;
                        for (let e = 0; e < this.getStars().length; e++) e + .5 < s ? $(this.getStars()[e]).attr("class", t.filledStar ? t.filledStar : r.filledStar) : e < s && $(this.getStars()[e]).attr("class", t.halfStar ? t.halfStar : r.halfStar)
                    } else {
                        $(this).find("i").attr("class", t.emptyStar ? t.emptyStar : r.emptyStar);
                        let a = this.getStars().index(e.target) + 1;
                        for (let e = 0; e < this.getStars().length; e++) e < a && $(this.getStars()[e]).attr("class", t.filledStar ? t.filledStar : r.filledStar)
                    }
                })), $(this).off("mouseout").on("mouseout", (function (t) {
                    this.printStars()
                })), $(this).off("click").on("click", (function (e) {
                    if (t.half ? t.half : r.half) {
                        let t = .5;
                        e.offsetX > $(e.target).outerWidth() / 2 && (t = 1), this.stars = this.getStars().index(e.target) + t
                    } else this.stars = this.getStars().index(e.target) + 1;
                    (t.click ? t.click : r.click)({stars: this.stars, event: e})
                })));
                const e = t.stars ? t.stars : r.stars;
                for (let a = 0; a < e; a++) {
                    let e = $("<i></i>").addClass(t.emptyStar ? t.emptyStar : r.emptyStar).appendTo($(this));
                    if (this.readonly || e.css({cursor: "pointer"}), a > 1e3) return
                }
                if (this.printStars = function () {
                    if (t.half ? t.half : r.half) {
                        $(this).find("i").attr("class", t.emptyStar ? t.emptyStar : r.emptyStar);
                        for (let e = 0; e < this.stars; e++) e < this.stars - .5 ? $(this.getStars()[e]).attr("class", t.filledStar ? t.filledStar : r.filledStar) : $(this.getStars()[e]).attr("class", t.halfStar ? t.halfStar : r.halfStar)
                    } else {
                        $(this).find("i").attr("class", t.emptyStar ? t.emptyStar : r.emptyStar);
                        for (let e = 0; e < this.stars; e++) $(this.getStars()[e]).attr("class", t.filledStar ? t.filledStar : r.filledStar)
                    }
                }, this.stars > 0) {
                    this.printStars();
                    (t.click ? t.click : r.click)({stars: this.stars})
                }
            }))
        }
    }), $((function () {
        $("[data-rating-stars]").each((function () {
            let t = {}, e = /^data-rating\-(.+)$/;
            $.each($(this).get(0).attributes, (function (r, a) {
                if (e.test(a.nodeName)) {
                    let r = a.nodeName.match(e)[1];
                    t[r] = a.nodeValue
                }
            })), null != t.input && (t.click = function (e) {
                $(t.input).val(e.stars)
            }), $(this).rating(t)
        }))
    }))
}]);