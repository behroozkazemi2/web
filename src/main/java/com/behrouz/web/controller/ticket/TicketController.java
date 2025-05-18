package com.behrouz.web.controller.ticket;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.rest.request.SaveTicketMessageRestRequest;
import com.behrouz.web.rest.response.TicketDetailRestResponse;
import com.behrouz.web.util.GetCategoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {

    @RequestMapping(value = {"/", ""})
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("view", "view/ticket/ticket.html");
        return "index.html";
    }


    //   -----------------------    TICKET PAGE  ------------------------



//    @RequestMapping(value = {"/list/download"})
//    public ResponseEntity getListExcel(
//            Model model,
//            @RequestParam(required = false, name = "project", defaultValue = "0") int project,
//            @RequestParam(required = false, name = "ticketImportance", defaultValue = "0") long ticketImportance,
//            @RequestParam(required = false, name = "search") String search,
//            @RequestParam(required = false, name = "responseType", defaultValue = "0") long responseType,
//            @RequestParam(required = false, name = "closed", defaultValue = "0") long closed,
////                                      @RequestParam(required = false, name = "submitDate", defaultValue = "0") long submitDate,
////                                      @RequestParam(required = false, name = "submitToDate", defaultValue = "0") long submitToDate,
//            @RequestParam(required = false, name = "lastMsgDate", defaultValue = "0") long lastMsgDate,
//            @RequestParam(required = false, name = "lastMsgToDate", defaultValue = "0") long lastMsgToDate,
//            HttpServletRequest request
//    ) {
//
//
//        byte[] excelData =
//                ticketComponent.createTicketListExcel(
//                        project,
//                        ticketImportance,
//                        search,
//                        responseType,
//                        closed,
//                        lastMsgDate,
//                        lastMsgToDate
//                );
//
//
//        try (FileOutputStream outputStream = new FileOutputStream("ticketList_" +new Date().getTime()+ ".xlsx")) {
//            outputStream.write(excelData);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/xlsx"));
//
//        String filename = "ticketList_" +new Date().getTime()+ ".xlsx" ;
//        headers.setContentDispositionFormData(filename, filename);
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
//
//    }



    //   -----------------------    TICKET MESSAGE PAGE  ------------------------

    @RequestMapping(value = {"/{trackingCode}"})
    public String getTicketMessagesPage(Model model, HttpServletRequest request,
                                        @PathVariable(required = true, name = "trackingCode") String trackingCode,
                                        SaveTicketMessageRestRequest restRequest) {

        ModelAndView modelAndView = new ModelAndView();

        ApiResponseBody<TicketDetailRestResponse> ticketDetailRes =
                OkHttpHelper.getUserTicketDetail(trackingCode);

        if (ticketDetailRes == null || !ticketDetailRes.successful() || ticketDetailRes.getData() == null)
            return "";

        TicketDetailRestResponse ticketDetail =
                ticketDetailRes.getData();

        model.addAttribute("category", GetCategoryUtils.getAllCategory());
        model.addAttribute("data", ticketDetail);
        model.addAttribute("closed", ticketDetail.isClosed());
        model.addAttribute("view", "view/ticket/ticketMessage.html");
        return "index.html";
    }


}