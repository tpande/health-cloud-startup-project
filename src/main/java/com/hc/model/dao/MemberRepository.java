package com.hc.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.hc.model.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {

}
