package com.study.library.controller;


import com.study.library.aop.annotation.ParamsPrintAspect;
import com.study.library.aop.annotation.ValidAspect;
import com.study.library.dto.SignupReqDto;
import com.study.library.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // 회원가입 요청 "/Signup" 라고 요청 주소를 해주면 공통주소
    // post요청은 JSON 데이터를 받아와서
    // @valid + Binding 세트

//    @ParamsPrintAspect
    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult) {

        // true면 중복이라는 것
        if(authService.isDuplicatedByUsername((signupReqDto.getUsername()))) {

            ObjectError objectError = new FieldError("username","username" ,"이미 존재하는 사용자이름 입니다.");
            bindingResult.addError(objectError);
//            Map<String, String> errorMap = Map.of("username", "이미 존재하는 사용자이름 입니다.");
//            return ResponseEntity.badRequest().body(errorMap);
        }

        authService.signup(signupReqDto);

        return ResponseEntity.created(null).body(true);
    }
}
