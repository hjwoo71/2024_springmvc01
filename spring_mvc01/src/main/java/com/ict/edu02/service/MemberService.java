package com.ict.edu02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ict.edu02.vo.MembersVO;
import com.ict.edu02.dao.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	

	
	public List<MembersVO> getMemberList(){
		List<MembersVO> list = null;
		list = memberDAO.getMemberList();
		return list;
	}
}
