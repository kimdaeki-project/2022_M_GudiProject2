package com.iu.home.studyCafe;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class cafeDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.iu.home.studyCafe.cafeDAO.";
	
	public List<cafeDTO> getList() throws Exception{
		return sqlSession.selectList(NAMESPACE+"getList");
	}
	
	public cafeDetailDTO getDetail(cafeDTO cafeDTO) throws Exception{
		cafeDetailDTO cafeDetailDTO = new cafeDetailDTO();
		return sqlSession.selectOne(NAMESPACE+"getDetail", cafeDetailDTO);
	}
}