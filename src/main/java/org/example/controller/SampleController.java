package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.SampleException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class SampleController {

  @GetMapping( "/" )
  public String showIndex(HttpServletRequest request) {
    return "index";
  }

  @GetMapping( "/throwFromController" )
  public String throwException() {
    throw new SampleException( "Hello from SampleController" );
  }

}
