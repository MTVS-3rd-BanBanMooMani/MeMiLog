package com.banbanmoomani.memilog.config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        System.out.println("basicErrorController = ");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        System.out.println("status = " + status.toString());
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                System.out.println("statusCode = " + statusCode);
                model.addAttribute("status", HttpStatus.NOT_FOUND);
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
                return "error/500";
            }
        }

        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error/error";
    }
}
