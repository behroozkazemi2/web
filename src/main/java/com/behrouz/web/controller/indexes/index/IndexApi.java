package com.behrouz.web.controller.indexes.index;


import com.behrouz.web.controller.shop.DataTable;
import com.behrouz.web.data.indexItem.IndexItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.behrouz.web.rest.AjaxResponse;

import java.util.List;

@RestController
@RequestMapping(value = "/index/api")
public class IndexApi {

    @RequestMapping(value = "/specialProduct")
    public AjaxResponse specialProduct(
            @RequestParam(value = "draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("length") int length) {

        DataTable<SpecialList> table = new DataTable();
        List<SpecialList> specialProductDetail = IndexItem.special();
        table.setData(specialProductDetail);
        table.setDraw(draw);
        table.setStart(start);
        if (1==1) {
            return new AjaxResponse(true, table);

        } else {
            return new AjaxResponse(false, "پر شه!!");
        }

    }


}