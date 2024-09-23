package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.SampleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class SampleControllerExceptionHandler {

  @ExceptionHandler( {SampleException.class} )
  public ModelAndView handleSampleException(HttpServletRequest req, Exception e) throws Exception {
    log.debug( "SampleException: " + e.getLocalizedMessage() );
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("error");
    modelAndView.addObject( "message", e.getLocalizedMessage() );
    return modelAndView;
  }

}