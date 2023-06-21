package com.example.dhlotteryapi.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum DHLotteryRequestHeaderType {
    DH_HEADER(ignored -> {
        return makeLoginHeaders();
    }),
    ;

    private final Function<String, HttpHeaders> httpHeadersFunction;

    private static HttpHeaders makeLoginHeaders() {
        final HttpHeaders headers = new HttpHeaders();

        List<MediaType> accept = new ArrayList<>();
        accept.add(MediaType.valueOf("text/html"));
        accept.add(MediaType.valueOf("application/xhtml+xml;q=0.9"));
        accept.add(MediaType.valueOf("image/avif"));
        accept.add(MediaType.valueOf("image/webp"));
        accept.add(MediaType.valueOf("image/apng"));
        accept.add(MediaType.valueOf("*/*;q=0.8"));
        accept.add(MediaType.valueOf("application/signed-exchange;v=b3;q=0.9"));

        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36");
        headers.setConnection("keep-alive");
        headers.setCacheControl("max-age=0");
        headers.set("sec-ch-ua", " Not;A Brand';v='99', 'Google Chrome';v='91', 'Chromium';v='91'");
        headers.set("sec-ch-ua-mobile", "?0");
        headers.setUpgrade("1");
        headers.setOrigin("https://dhlottery.co.kr");
        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));
        headers.setAccept(accept);
        headers.set("Referer", "https://dhlottery.co.kr/");
        headers.set("Sec-Fetch-Site", "same-site");
        headers.set("Sec-Fetch-Mode", "navigate");
        headers.set("Sec-Fetch-User", "?1");
        headers.set("Sec-Fetch-Dest", "document");
        headers.set("Accept-Language", "ko,en-US;q=0.9,en;q=0.8,ko-KR;q=0.7");
        return headers;
    }

    public HttpHeaders getHeaders() {
        return getHttpHeadersFunction().apply(null);
    }
}
