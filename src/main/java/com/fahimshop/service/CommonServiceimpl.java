package com.fahimshop.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Request;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
public class CommonServiceimpl implements CommonService {
    @Override
    public void removeSessionMessage() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
                .getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("succMsg");
        session.removeAttribute("errorMsg");
    }
}
