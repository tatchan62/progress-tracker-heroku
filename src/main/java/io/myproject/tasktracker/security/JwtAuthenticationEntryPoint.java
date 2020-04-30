package io.myproject.tasktracker.security;

import com.google.gson.Gson;
import io.myproject.tasktracker.exceptions.InvalidLoginResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// call when user authentication exception is thrown
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        // create a invalid login response
        InvalidLoginResponse loginResponse = new InvalidLoginResponse();
        // return JSON object
        String jsonLoginResponse = new Gson().toJson(loginResponse);

        // set up a response from server to user
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().print(jsonLoginResponse);

    }
}
