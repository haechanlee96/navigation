package com.example.navigation.apinavi.controller;

import com.example.navigation.apinavi.dto.KakaoRouteAllResponseDto;
import com.example.navigation.apinavi.service.KakaoRouteSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class RouteController {

    private final KakaoRouteSearchService kakaoRouteSearchService;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    @GetMapping("/map")
    public String showMap(Model model) {
        model.addAttribute("kakaoRestApiKey", kakaoRestApiKey);
        return "index";
    }

    @GetMapping("/route")
    public ResponseEntity<KakaoRouteAllResponseDto> getRoute(@RequestParam String origin, @RequestParam String destination) {
        KakaoRouteAllResponseDto response = kakaoRouteSearchService.requestRouteSearch(origin, destination);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
