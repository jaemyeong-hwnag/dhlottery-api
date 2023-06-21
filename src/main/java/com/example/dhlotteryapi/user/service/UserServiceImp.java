package com.example.dhlotteryapi.user.service;

import com.example.dhlotteryapi.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.example.dhlotteryapi.user.DHLotteryRequestHeaderType.DH_HEADER;
import static com.example.dhlotteryapi.user.DHLotteryURI.DEFAULT_SESSION_URL;
import static com.example.dhlotteryapi.user.DHLotteryURI.LOGIN_REQUEST_URL;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

/**
 * @todo http 요청 클라이언트로 분리
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {
    /**
     * @todo 익셉션 핸들로 추가
     */
    @ResponseBody
    public void checkDHServer() throws Exception {
        // RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        try {
            // 요청할 URL 만들기
            URI uri = UriComponentsBuilder
                    .fromUriString(DEFAULT_SESSION_URL.toString())
                    .build()
                    .toUri();

            // HTTP GET, 응답을 ResponseEntity 로 받기.
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

            // HttpStatus 상태코드가 OK(200)이면 응답 본문을 출력하고, 그렇지 않으면 오류 메시지 출력
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new Exception("fail DH API request");
            }
        } catch (Exception e) {
            log.info("fail");
            // log.error("Error occurred while processing login request for user {}: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void login(UserDto user) throws Exception {
        log.info("User {} is attempting to log in at {}", user, LocalDateTime.now());

        // RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders DHHeader = DH_HEADER.getHeaders();

        try {
            DHHeader.set(SET_COOKIE, "JSESSIONID=" + getJSESSIONID());
            HttpEntity<String> request = new HttpEntity<>("", DHHeader);

            // HTTP GET, 응답을 ResponseEntity 로 받기.
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    LOGIN_REQUEST_URL.getUrl(),
                    HttpMethod.POST,
                    request,
                    String.class
            );

            // HttpStatus 상태코드가 OK(200)이면 응답 본문을 출력하고, 그렇지 않으면 오류 메시지 출력
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new Exception("fail DH API request");
            }

            log.info("User {} has successfully logged in at {} - {}", user, LocalDateTime.now(), responseEntity);
        } catch (Exception e) {
            log.error("Error occurred while processing login request for user {}: {}", user, e.getMessage());
            throw e;
        }
    }

    @Override
    public void logout() {

    }

    private String getJSESSIONID() throws Exception {
        // RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> request = new HttpEntity<>("", DH_HEADER.getHeaders());

        // HTTP GET, 응답을 ResponseEntity 로 받기.
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                DEFAULT_SESSION_URL.getUrl(),
                HttpMethod.GET,
                request,
                String.class
        );

        // HttpStatus 상태코드가 OK(200)이면 응답 본문을 출력하고, 그렇지 않으면 오류 메시지 출력
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new Exception("fail DH API request");
        }

        if (Objects.equals(responseEntity.getHeaders().getFirst("JSESSIONID"), "")) {
            throw new Exception("Dose not have JSESSIONID in response header");
        }

        return responseEntity.getHeaders().getFirst("JSESSIONID");
    }
}
