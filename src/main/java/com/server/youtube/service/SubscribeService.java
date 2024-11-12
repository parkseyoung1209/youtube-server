package com.server.youtube.service;

import com.server.youtube.domain.Member;
import com.server.youtube.domain.Subscribe;
import com.server.youtube.repo.SubscribeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscribeService {

    @Autowired
    private SubscribeDAO dao;

    public String getId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null && auth.isAuthenticated()) {
            Member member = (Member) auth.getPrincipal();
            return member.getId();
        }
        return null;
    }

    public void create(Subscribe vo) {
        vo.setId(getId());
        dao.save(vo);
    }

    public void remove(int subCode) {
        dao.deleteById(subCode);
    }

    public int count(int channelCode) {
        return dao.count(channelCode);
    }
    public Subscribe check(int channelCode) {
        return dao.check(channelCode, getId());
    }
}
