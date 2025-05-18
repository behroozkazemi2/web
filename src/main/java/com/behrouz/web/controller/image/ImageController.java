package com.behrouz.web.controller.image;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.ImageTypeRequest;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.behrouz.web.okhttp.model.request.ImageRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.controller
 * Project xima-webapplication
 * 20 February 2019 10:28 AM
 **/

@RestController

public class ImageController {

    @RequestMapping(
            value = "/thumbnail/files/{type}/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public void images (@PathVariable(name = "type") String type,
                          @PathVariable("id") int id,
                          HttpServletResponse servletResponse){

        if(!"original".equalsIgnoreCase(type) && !"thumbnail".equalsIgnoreCase(type)){
            type = "thumbnail";
        }

        ApiResponseBody<ImageRequest> response = id == 0 ? null :
                OkHttpHelper.getImage(
                        new ImageTypeRequest(
                                id,
                                "original".equalsIgnoreCase(type) ? 4 : 3
                        )
                );


        if(response == null || !response.successful()){

                System.err.println("We Found a Bug , J8 o_O");
                servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }else{
            try {
                servletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
                StreamUtils.copy(response.getData().getImage(), servletResponse.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }


    }

//    public byte[] extractBytes () throws IOException {
//
//
//        URL url = new URL("http://localhost:2223/assets/img/not_fount.png");
//        BufferedImage img = ImageIO.read(url);
//        File imgPath = new File("downloaded.jpg");
//        ImageIO.write(img, "png", imgPath);
//
//        // open image
//        BufferedImage bufferedImage = ImageIO.read(imgPath);
//
//        // get DataBufferBytes from Raster
//        WritableRaster raster = bufferedImage .getRaster();
//        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
//
//        return ( data.getData() );
//    }

}
