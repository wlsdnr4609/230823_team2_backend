package org.zerock.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.domain.SearchCriteria;

public interface BoardService {
  List<BoardVO> selectBoardList(@Param("btype")char btype) throws Exception;

  public BoardVO read(Integer num) throws Exception;

  public void regist(BoardVO board) throws Exception;

  public void modify(BoardVO board) throws Exception;
  
  public void likes(BoardVO board) throws Exception;
  
  public void delete(BoardVO board) throws Exception;

  public List<BoardVO> listAll() throws Exception;

  public List<BoardVO> listCriteria(Criteria cri) throws Exception;

  public int listCountCriteria(Criteria cri) throws Exception;

  public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;

  public int listSearchCount(SearchCriteria cri) throws Exception;

  List<BoardVO> listPage(int page) throws Exception;

  void updateReplyCnt(Integer bid, int amount) throws Exception; 
  
  public List<String> getAttach(@Param("bid")Integer bid)throws Exception;

  void replaceAttach(String fullName, Integer bid) throws Exception;

  void create(BoardVO vo) throws Exception;

 // List<BoardVO> qList(@Param("niname")String niname) throws Exception;

  public List<BoardVO> qListReply(@Param("niname")String niname) throws Exception;
}
