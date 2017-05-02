package eus.arriegi.cyclingacb.web.apiv1;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import eus.arriegi.cyclingacb.web.apiv1.MyAuthenticationDetailsSource.MyAuthenticationDetails;

public class MyAuthenticationProvider implements AuthenticationProvider {

	@Override
	public	Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// cast as it pass the support method
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		String principal = (String) auth.getPrincipal();
		String credential = (String) auth.getCredentials();

		Object obj = auth.getDetails();
		if (obj instanceof MyAuthenticationDetails) {
			MyAuthenticationDetails details = (MyAuthenticationDetails) obj;
			// Doing the extra check such as referer
			if ("http://example.com/referer".equalsIgnoreCase(details.getReferer())) {
				// do the further check ...
				// return the result if passed validation
				UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal,
						credential);
				return result;
			}
		}

		throw new BadCredentialsException("Bad Authentication");

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);

	}
}