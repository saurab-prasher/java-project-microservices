package status200.adminservice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "yourpassword";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("{bcrypt}" + encodedPassword);
    }
}
