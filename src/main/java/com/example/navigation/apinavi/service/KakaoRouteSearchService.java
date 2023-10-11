package com.example.navigation.apinavi.service;

import com.example.navigation.api.service.KakaoUriBuilderService;
import com.example.navigation.apinavi.dto.KakaoRouteAllResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoRouteSearchService {

    private final RestTemplate restTemplate;
    private final KakaoUriBuilderService kakaoUriBuilderService;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;


    public KakaoRouteAllResponseDto requestRouteSearch(String origin, String destination) {
        if (ObjectUtils.isEmpty(origin) || ObjectUtils.isEmpty(destination)) return null;



        URI uri = kakaoUriBuilderService.buildUriByRouteSearch(origin, destination);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoRouteAllResponseDto.class).getBody();
    }
}
