package org.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.SampleException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class FilterChainExceptionHandlerFilter extends OncePerRequestFilter {

  private final SpringTemplateEngine templateEngine;

  public FilterChainExceptionHandlerFilter( SpringTemplateEngine templateEngine ) {
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      log.error("caught exception ", e);

      if ( e instanceof SampleException ) {
        Context context = new Context();
        context.setVariable("statusCode", "123");
        context.setVariable("message", e.getLocalizedMessage() );
        String htmlTemplate = this.templateEngine.process("error", context);
        PrintWriter printWriter = response.getWriter();
        printWriter.println( htmlTemplate );
        printWriter.close();
      }

    }
  }
}
