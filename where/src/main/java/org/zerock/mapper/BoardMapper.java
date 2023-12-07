package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.domain.SearchCriteria;

public interface BoardMapper {
  List<BoardVO> selectBoardList(@Param("btype")char btype) throws Exception;

  // List<BoardVO> qList(@Param("niname")String niname) throws Exception;
  
  public List<BoardVO> qList(@Param("niname")String niname) throws Exception;
  
  BoardVO read(int bid) throws Exception;

  public void create(BoardVO vo) throws Exception;

  public void update(BoardVO vo) throws Exception;
  
  public void likes(BoardVO vo) throws Exception;
  
  public void delete(BoardVO board) throws Exception;

  public void updateCounts(Integer bid) throws Exception;

  public List<BoardVO> listAll() throws Exception;

  public List<BoardVO> listPage(int page) throws Exception;

  public List<BoardVO> listCriteria(Criteria cri) throws Exception;

  public int countPaging(Criteria cri) throws Exception;

  public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;

  public int listSearchCount(SearchCriteria cri) throws Exception;

	
  public void updateReplyCnt(@Param("bid")Integer bid, @Param("amount")int
	  amount) throws Exception;
	 

  public void addAttach(String fullName)throws Exception;
  
  public List<String> getAttach(@Param("bid")Integer bid)throws Exception;  
   
  public void deleteAttach(@Param("bid")Integer bid)throws Exception;
  
  public void replaceAttach(String fullName, Integer bid)throws Exception;
}
