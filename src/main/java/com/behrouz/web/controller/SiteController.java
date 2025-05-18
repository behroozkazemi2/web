package com.behrouz.web.controller;

import com.behrouz.web.rest.SiteMapData;
import com.behrouz.web.util.LocalDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description: مربوط به صفحات باز سایت
 * Created by Hapi on 8/21/2023.
 */

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/site")
public class SiteController {



    @RequestMapping(value={"/robots.txt", "/robot.txt"}, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String robots() {
        return "User-agent: *\n" +
                "Disallow: /api/user/\n" +
                "Disallow: /user/\n" +
                "Disallow: /api/address/\n" +
                "Disallow: /productCategory/\n" +
                "Disallow: /product/\n" +
                "Disallow: /api/shop/\n" +
                "Disallow: /shop/\n" +
                "Disallow: /checkOut/\n" +
                "Disallow: /cart/\n" +
                "Disallow: /api/cart/\n" +
                "Sitemap: https://shop.behtatahvie.com/sitemap.xml\n" ;
    }


    @RequestMapping(value = {"/sitemap.xml"}, produces = MediaType.TEXT_XML_VALUE)
    public String getSiteConfigurations(Model model) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        String date = simpleDateFormat.format(LocalDateTimeUtil.localDateTimeToDate(LocalDateTime.now().minusHours((4 + new Random().nextInt(3))).minusMinutes((10 + new Random().nextInt(40)))));

        List<SiteMapData> list = new ArrayList<>();
        list.add(new SiteMapData("https://shop.behtatahvie.com/", date));
        list.add(new SiteMapData("https://shop.behtatahvie.com/login", date));
        list.add(new SiteMapData("https://shop.behtatahvie.com/info/aboutUs", date));
        list.add(new SiteMapData("https://shop.behtatahvie.com/info/termsOfUse", date));
        list.add(new SiteMapData("https://shop.behtatahvie.com/info/contactUs", date));
        list.add(new SiteMapData("https://shop.behtatahvie.com/info/faq", date));
        list.add(new SiteMapData("https://shop.behtatahvie.com/info/privacyPolicy", date));
        list.add(new SiteMapData("https://shop.behtatahvie.com/info/termsOfUse", date));
        list.add(new SiteMapData("https://shop.behtatahvie.com/blog/get-unique-modian-code", date));

        model.addAttribute("siteMap", list);
        return "sitemap.xml";
    }



}
