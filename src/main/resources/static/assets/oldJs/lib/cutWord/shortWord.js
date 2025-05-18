let moreFormat = function( str, len){
        let text;
        if (str!=null && str.length > len) {
            text = str.substring(0, len)+ " ...";
            return text;
        }
        return str;
};
let moreFormat1 = function( str, len){
        let text;
        if (str!=null && str.length > len) {
            text = "... "+str.substring(0, len) ;
            return text;
        }
        return str;
};