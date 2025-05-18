package com.behrouz.web.controller.bank;


import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.rest.response.bank.BehtaSamanRedirectUrlRestResponse;
import com.behrouz.web.rest.response.bank.SamanRedirectUrlRestResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;


@RestController
@RequestMapping("/payment/saman")
public class BankSamanController {

    @RequestMapping({ "/verifyBankTransaction/{token}"})
    public String samanCallback(
            @PathVariable(name = "token", required = false) String token,
            @RequestParam( defaultValue = "0", required = false,name = "MID") long MID,
            @RequestParam( defaultValue = "0", required = false,name = "TerminalId") long TerminalId,
            @RequestParam( defaultValue = "", required = false,name = "State") String State,
            @RequestParam( defaultValue = "0", required = false,name = "Status") long Status,
            @RequestParam( defaultValue = "", required = false,name = "RRN") String RRN,
            @RequestParam( defaultValue = "", required = false,name = "RefNum") String RefNum,
            @RequestParam( defaultValue = "", required = false,name = "ResNum") String ResNum,
            @RequestParam( defaultValue = "", required = false,name = "TraceNo") String TraceNo,
            @RequestParam( defaultValue = "0", required = false,name = "Amount") long Amount,
            @RequestParam( defaultValue = "", required = false,name = "Wage") Long Wage,
            @RequestParam( defaultValue = "", required = false,name = "SecurePan") String SecurePan,
            @RequestParam(defaultValue = "", required = false, name = "HashedCardNumber") String HashedCardNumber
    ) throws HttpServerErrorException {

        SamanRedirectUrlRestResponse request = new SamanRedirectUrlRestResponse();
        request.setMID(MID);
        request.setTerminalId(TerminalId);
        request.setState(State);
        request.setStatus(Status);
        request.setRRN(RRN);
        request.setRefNum(RefNum);
        request.setResNum(ResNum);
        request.setTraceNo(TraceNo);
        request.setAmount(Amount);
        request.setWage(Wage);
        request.setSecurePan(SecurePan);
        request.setHashedCardNumber(HashedCardNumber);
        System.out.println( "---------------- BankSamanController ---------------- " );
        System.out.println("BankSamanController : " + request.toString());

        BehtaSamanRedirectUrlRestResponse bankResponse = new BehtaSamanRedirectUrlRestResponse(
                token,
                request
        );

        ApiResponseBody<String> result =
                OkHttpHelper.customerPaymentConfirm(bankResponse);

        System.out.println("BankSamanController : " + result.getData());
        if (result.successful()) {
            return result.getData();
        } else {
            return "redirect:https://shop.behtatahvie.com/pay/error";
        }

    }

}
