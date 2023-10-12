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

    @Value("${kakao.javascript.api.key}")
    private String kakaoJavascriptApiKey;

    @GetMapping("/map")
    public String showMap(Model model) {
        model.addAttribute("kakaoJavascriptApiKey", kakaoJavascriptApiKey);
        return "index";
    }

    @GetMapping("/route")
    public ResponseEntity<KakaoRouteAllResponseDto> getRoute(@RequestParam String originAddress, @RequestParam String destinationAddress) {
        KakaoRouteAllResponseDto response = kakaoRouteSearchService.requestRouteSearch(originAddress, destinationAddress);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 적절한 HTTP 상태 코드로 응답
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
