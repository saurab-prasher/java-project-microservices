package status200.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import status200.adminservice.model.JwtRequest;
import status200.adminservice.model.JwtResponse;
import status200.adminservice.security.JwtTokenUtil;
import status200.adminservice.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Endpoint to authenticate user credentials and generate JWT token
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        // Authenticate user credentials
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Load user details by username
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        
        // Generate JWT token
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        // Return the JWT token as response
        return ResponseEntity.ok(new JwtResponse(token));
    }

    // Method to authenticate user credentials
    private void authenticate(String username, String password) throws Exception {
        try {
            // Perform authentication using Spring Security AuthenticationManager
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            // Throw exception if user account is disabled
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            // Throw exception if credentials are invalid
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
