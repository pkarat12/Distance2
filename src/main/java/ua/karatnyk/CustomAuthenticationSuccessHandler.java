package ua.karatnyk;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter @Setter
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private final String ADMIN_PAGE = "/admin";
	private final String DIRECTOR_PAGE = "/director";
	private final String TEACHER_PAGE = "/teacher";
	private final String STUDENT_PAGE = "/student";
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determainateTargetUrl(authentication);

		if (response.isCommitted()) {
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	protected String determainateTargetUrl(Authentication authentication) {
		boolean isAdmin = false;
		boolean isDirector = false;
		boolean isTeacher = false;
		boolean isStudent = false;
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {

			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_DIRECTOR")) {
				isDirector = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_TEACHER")) {
				isTeacher = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_STUDENT")) {
				isStudent = true;
				break;
			}
		}

		if (isAdmin) {
			return ADMIN_PAGE;
		} else if (isDirector) {
			return DIRECTOR_PAGE;
		}else if (isTeacher) {
			return TEACHER_PAGE;
		}else if (isStudent) {
			return STUDENT_PAGE;
		}
		
		return null;
	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
