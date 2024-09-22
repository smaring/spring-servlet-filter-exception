package org.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.controller.SampleException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class SampleFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    log.debug( "entered SampleFilter" );
    if ( request.getRequestURI().endsWith("does-not-exist") ) {
      log.debug( "throwing exception" );
      throw new SampleException("does-not-exist does not exist!  Big surprise!");
    }
    filterChain.doFilter(request, response);
  }

}
