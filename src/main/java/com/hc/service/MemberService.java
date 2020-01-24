package com.hc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.model.Member;
import com.hc.model.dao.MemberRepository;

@Service
public class MemberService {
	
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<Member>();
        memberRepository.findAll().forEach(member -> members.add(member));
        return members;
    }

    public Member getMemberById(int id) {
        return memberRepository.findById(id).get();
    }

    public Member saveOrUpdate(Member member) {
    	
    		member.setDob(new Date());
    		
    	
    	return memberRepository.save(member);
    }

    public void delete(int id) {
    	 memberRepository.deleteById(id);
    }

}
