package io.myproject.tasktracker.security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/users/**";
    public static final String H2_URL = "h2-console/**";


    /* ------------ Below are the the token --------------- */
    public static final String SECRET = "SecretKeyToGenJWTs";

    // Space after Bearer is extremely important
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 120_000; // 60 seconds
}
