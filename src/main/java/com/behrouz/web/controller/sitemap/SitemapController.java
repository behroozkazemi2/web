package com.behrouz.web.controller.sitemap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Logger;

/**
 * Created by: hapi
 * 02 November 2020
 **/

@Controller
public class SitemapController {

    private static final Logger LOGGER = Logger.getLogger(SitemapController.class.getSimpleName());

    @RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody public FileSystemResource getFile(HttpServletResponse response) {
        response.setContentType("application/xml");
        try {
            return new FileSystemResource(new ClassPathResource("static//assets/raw/sitemap.xml").getFile()); //Or path to your file
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
    @ResponseBody public FileSystemResource getRobotTxtFile(HttpServletResponse response) {
        response.setContentType("text/plain");
        try {
            return new FileSystemResource(new ClassPathResource("static//assets/raw/robots.txt").getFile()); //Or path to your file
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
