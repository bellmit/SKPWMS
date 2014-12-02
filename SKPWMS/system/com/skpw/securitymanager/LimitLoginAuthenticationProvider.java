//package com.skpw.securitymanager;
//import java.util.Date;
//
//import javax.annotation.Resource;
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
//import com.skpw.bean.UserAttempts;
//import com.skpw.dao.UserDetailsDao;
//
//
//
//public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {
//    
//	@Resource
//	private UserDetailsDao userDetailsDao;
//	
//	@Resource
//	private UserDetailsService userDetailsService;
//    @Override
//    public Authentication authenticate(Authentication authentication)
//            throws AuthenticationException {
//
//        try {
//
//            Authentication auth = super.authenticate(authentication);
//
//            // if reach here, means login success, else exception will be thrown
//            // reset the user_attempts
//            userDetailsDao.resetFailAttempts(authentication.getName());
//
//            return auth;
//
//        } catch (BadCredentialsException e) {
//
//            userDetailsDao.updateFailAttempts(authentication.getName());
//            throw e;
//
//        } catch (LockedException e) {
//
//            String error = "";
//            UserAttempts userAttempts = userDetailsDao
//                    .getUserAttempts(authentication.getName());
//            if (userAttempts != null) {
//                Date lastAttempts = userAttempts.getLastModified();
//                error = "User account is locked! <br><br>Username : "
//                        + authentication.getName() + "<br>Last Attempts : "
//                        + lastAttempts;
//            } else {
//                error = e.getMessage();
//            }
//
//            throw new LockedException(error);
//        }
//
//    }
//    
//    
//
//}
