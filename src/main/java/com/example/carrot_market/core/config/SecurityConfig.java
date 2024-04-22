//package com.example.carrot_market.core.config;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtTokenFilter jwtTokenFilter; // 필터 주입
//    private static final String[] PERMIT_URL_ARRAY = {
//            "/api/v1/auth/**",
//            "/error"
//    };
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(request -> {
//                    CorsConfiguration configuration = new CorsConfiguration();
//                    configuration.setAllowedOrigins(List.of("http://localhost:3000")); // 추후 배포 URL 로 변경
//                    configuration.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE"));
//                    configuration.setAllowedHeaders(List.of("Content-Type", "Authorization"));
//                    return configuration;
//                }))
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement((sessionManagement) ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .exceptionHandling(exceptionHandlingConfigurer -> {
//                    exceptionHandlingConfigurer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
//                })
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                        authorizationManagerRequestMatcherRegistry
//                                .requestMatchers(PERMIT_URL_ARRAY).permitAll()
//                                .anyRequest().authenticated()
//                )
//                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//}