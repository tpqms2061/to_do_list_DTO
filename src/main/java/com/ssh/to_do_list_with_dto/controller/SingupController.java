package com.ssh.to_do_list_with_dto.controller;


import com.ssh.to_do_list_with_dto.dto.SignupDTO;
import com.ssh.to_do_list_with_dto.model.User;
import com.ssh.to_do_list_with_dto.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SingupController {
    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("signupDto", new SignupDTO());

        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(
            @Valid @ModelAttribute("signupDto") SignupDTO signupDTO,
//    @Valid 를 붙여줌으로써 너 검증절차를 수행하라고 지시할수있는것
            BindingResult bindingResult, //검증결과및 연산결과를 bindingResult 에 담아 출력
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        // 중복 가입 여부 체크 -id 값이 유니크여도 사용자에게 중복이라고 알려줘야됨
        if (userRepository.findByUsername(signupDTO.getUsername()) != null) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다");

            return "signup";

        }
        User user = User.builder()
                .username(signupDTO.getUsername())
                .password(signupDTO.getPassword())
                .build();
        userRepository.save(user);

        return "redirect:/login?registered";


    }


}
