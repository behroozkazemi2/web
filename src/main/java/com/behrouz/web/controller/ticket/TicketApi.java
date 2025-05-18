package com.behrouz.web.controller.ticket;

import com.behrouz.web.controller.shop.DataTable;
import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.IdRequest;
import com.behrouz.web.option.TicketImportanceOption;
import com.behrouz.web.rest.AjaxResponse;
import com.behrouz.web.rest.request.SaveTicketMessageRestRequest;
import com.behrouz.web.rest.request.TicketMessageRequest;
import com.behrouz.web.rest.request.SaveTicketRestRequest;
import com.behrouz.web.rest.response.TicketDetailRestResponse;
import com.behrouz.web.rest.response.TicketMessageRestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ticket")
public class TicketApi {
    @RequestMapping(value = {"/modal/add"})
    public ModelAndView addTicketModal(Model model, HttpServletRequest request,
                                       @RequestParam(name = "search", defaultValue = "", required = false) String search) {
        ModelAndView modelAndView = new ModelAndView();
        List<TicketImportanceOption> data =
                TicketImportanceOption.getAll();
        modelAndView.addObject("imp" , data);
        modelAndView.setViewName("fragment/modal/addTicketModal.html::modal");
        return modelAndView;
    }



    @RequestMapping(value = {"/message/{ticketId}"})
    public ModelAndView getMessages(Model model, HttpServletRequest request,
                                    @PathVariable(required = true, name = "ticketId") long id) {
        ModelAndView modelAndView = new ModelAndView();
        TicketMessageRequest requestTicket = new TicketMessageRequest(id, false);
        ApiResponseBody<List<TicketMessageRestResponse>> msg = OkHttpHelper.getTicketMessages(requestTicket);
        if (msg.successful() )
            model.addAttribute("msg", msg.getData());

        modelAndView.setViewName("fragment/messages.html::text");
        return modelAndView;
    }


    @RequestMapping(value = {"/last/message/{id}"})
    public ModelAndView getLastMassage(Model model, HttpServletRequest request,
                                       @PathVariable(required = true, name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        TicketMessageRequest requestTicket =
                new TicketMessageRequest(id, true);

        ApiResponseBody<List<TicketMessageRestResponse>> msg =
                OkHttpHelper.getTicketMessages(requestTicket);
        if (msg.successful() )
            model.addAttribute("msg", msg.getData());
        modelAndView.setViewName("fragment/messages.html::text");
        return modelAndView;
    }


    @RequestMapping(value = {"/modal/add/message/"})
    public ModelAndView addTicketMessageModal(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragment/modal/addTicketMessageModal.html::modal");
        return modelAndView;
    }

    @RequestMapping(value = {"/importance/list"})
    public ResponseEntity getTicketImportanceList(Model model, HttpServletRequest request,
                                                  @RequestParam(name = "search", defaultValue = "", required = false) String search
    ) {
        List<TicketImportanceOption> data =
                TicketImportanceOption.getAll();
        return new ResponseEntity(new AjaxResponse(true, data), HttpStatus.OK);
    }
    @RequestMapping(value = "/list")
    public DataTable<TicketDetailRestResponse> getUserList() {
        ApiResponseBody<List<TicketDetailRestResponse>> response =
                OkHttpHelper.getUserTicket("");

        if (!response.successful()){
            return new DataTable<>();
        }

        DataTable<TicketDetailRestResponse> item = new DataTable<>();
        item.setData(response.getData());
        item.setRecordsTotal(response.getData().size());
        return item;
    }

    @RequestMapping(value = {"/save"})
    public ResponseEntity saveTicket(Model model, HttpServletRequest request,
                                     @ModelAttribute SaveTicketRestRequest rest) {
        try {
            ApiResponseBody<String> response =
                    OkHttpHelper.saveOrEditTicket(rest);
            if (!response.successful())
                return new ResponseEntity(new AjaxResponse(false, response.getDescription()), HttpStatus.OK);

            return new ResponseEntity(new AjaxResponse(true, response.getData()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new AjaxResponse(false, e.getMessage()), HttpStatus.OK);
        }
    }


    @RequestMapping(value = {"/close/{id}"})
    public ResponseEntity closeTicket(Model model, HttpServletRequest request,
                                      @PathVariable(required = true, name = "id") long id) {
        try {

            ApiResponseBody<Void> res =
                    OkHttpHelper.closeTicket(new IdRequest(id));

            if (!res.successful())
                return new ResponseEntity(new AjaxResponse(false, res.getDescription()), HttpStatus.OK);

            return new ResponseEntity(new AjaxResponse(true, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new AjaxResponse(false, e.getMessage()), HttpStatus.OK);
        }
    }


    @RequestMapping(value = {"/save/message"})
    public ResponseEntity saveTicketMessage(Model model,
                                            HttpServletRequest request,
                                            @ModelAttribute SaveTicketMessageRestRequest restRequest) {
        try {
            ApiResponseBody<Void> res =
                    OkHttpHelper.addNewTicketMessage(restRequest);

            if (!res.successful())
                return new ResponseEntity(new AjaxResponse(false, res.getMessage()), HttpStatus.OK);

            return new ResponseEntity(new AjaxResponse(true, ""), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new AjaxResponse(false, e.getMessage()), HttpStatus.OK);
        }

    }
}
