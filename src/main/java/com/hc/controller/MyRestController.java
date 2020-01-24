package com.hc.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hc.exception.MemberNotFoundException;
import com.hc.kafka.producer.configuration.Sender;
import com.hc.model.Member;
import com.hc.model.dao.MemberRepository;
import com.hc.service.MemberService;


@RestController
public class MyRestController {
	
    @Autowired
    MemberService memberService;
    
    @Autowired
    Sender sender;

    @GetMapping("/members")
    private List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/members/{id}")
    private Member getPerson(@PathVariable("id") int id) {
    	
    	Member member = memberService.getMemberById(id);
    	
    	if(member==null) {
			throw new MemberNotFoundException("id not found ::::" + id);
		}
        return member;
    }

    @DeleteMapping("/members/{id}")
    private void deletePerson(@PathVariable("id") int id) {
    	
    	memberService.delete(id);
    	
    }

    @PostMapping("/members")
    private ResponseEntity<Object> savePerson(@Valid @RequestBody Member member) {
    	
    	Member savedMember = memberService.saveOrUpdate(member);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(savedMember.getId())
				.toUri();
		
		sender.sendMessage(member);
		
		return ResponseEntity.created(location).build();
		
    	
    	
        //return member.getId();
        
    }
}
