package fi.ipss.isteer.dialog.authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author akupias
 * used in Spring Security filter chain for configuring ip based access
 */
public class IpFilter extends OncePerRequestFilter {

        List<String> authorizedIPs;

        public void setAuthorizedIPs(List<String> authorizedIPs) {
                this.authorizedIPs = authorizedIPs;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                        HttpServletResponse response, FilterChain filterChain)
                        throws ServletException, IOException {
                if (this.authorizedIPs.contains(request.getRemoteAddr()))
                        filterChain.doFilter(request, response);
                else
                        throw new AccessDeniedException("no access from your ip: "+request.getRemoteAddr());   
        }

}

