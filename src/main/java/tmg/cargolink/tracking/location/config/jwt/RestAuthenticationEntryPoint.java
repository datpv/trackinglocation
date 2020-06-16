package tmg.cargolink.tracking.location.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	JwtService jwtService;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
		if(!StringUtils.isEmpty(response.getHeader("expired"))){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Expired");
			return;
		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("Unauthorized");
	}
}
