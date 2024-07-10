package com.ladmakhi.projecttracker.core.resolvers;

import com.ladmakhi.projecttracker.core.annotations.GetCurrentUser;
import com.ladmakhi.projecttracker.features.users.User;
import com.ladmakhi.projecttracker.features.users.UserRepository;
import com.ladmakhi.projecttracker.features.users.dtos.GetUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class GetCurrentUserResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(GetCurrentUser.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("Email Not Found");
        }
        return user;
    }
}
