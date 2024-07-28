package com.banbanmoomani.memilog.config;

import com.banbanmoomani.memilog.config.error.NotFoundException;
import com.banbanmoomani.memilog.config.error.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
    public String handleBadRequest(Exception ex, Model model) {
        model.addAttribute("error", "400");
        model.addAttribute("message", ex.getMessage());
        return "error/400";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorized(Exception ex, Model model) {
        model.addAttribute("error", "401");
        model.addAttribute("message", ex.getMessage());
        return "error/401";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String handleForbidden(Exception ex, Model model) {
        model.addAttribute("error", "403");
        model.addAttribute("message", ex.getMessage());
        return "error/403";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Exception ex, Model model) {
        model.addAttribute("error", "404");
        model.addAttribute("message", ex.getMessage());
        return "error/404";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IOException.class, SQLException.class, NullPointerException.class, Exception.class})
    public String handleInternalServerError(Exception ex, Model model) {
        model.addAttribute("error", "500");
        model.addAttribute("message", ex.getMessage());
        return "error/500";
    }
}