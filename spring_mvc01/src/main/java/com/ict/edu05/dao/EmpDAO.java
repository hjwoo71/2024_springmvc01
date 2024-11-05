package com.ict.edu05.dao;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ict.edu05.db.EmpVO;

public interface EmpDAO {
	List<EmpVO> getList() throws Exception;
	List<EmpVO> getSearch(String deptno) throws Exception;
	List<EmpVO> getSearch(EmpVO empvo) throws Exception;
	List<EmpVO> getSearch(String idx, String keyword) throws Exception;
}
