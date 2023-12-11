package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.domain.SearchCriteria;
import org.zerock.mapper.BoardMapper;

@Service
// @Configuration
// @MapperScan("org.zerock.mapper")
public class BoardServiceImpl implements BoardService {
  @Autowired
  private BoardMapper boardMapper;

  @Override
  @Transactional
  public List<BoardVO> selectBoardList(char btype) throws Exception {
    return boardMapper.selectBoardList(btype);
  }

	
  @Override
  public List<BoardVO> qListReply(String niname) throws Exception {

    return boardMapper.qList(niname);
  }

  @Override
  public BoardVO read(Integer num) throws Exception {
    boardMapper.updateCounts(num);
    return boardMapper.read(num);
  }

  @Override
  public void regist(BoardVO board) throws Exception {
    boardMapper.create(board);
   String[] files = board.getFiles();
    
    if(files == null) { return; } 
    
    for (String fileName : files) {
      boardMapper.addAttach(fileName);
    }
  }

  @Override
  public void modify(BoardVO board) throws Exception {
    boardMapper.update(board);
    Integer bid = board.getBid();
    
    boardMapper.deleteAttach(bid);
    
    String[] files = board.getFiles();
    
    if(files == null) { return; } 
    
    for (String fileName : files) {
      boardMapper.replaceAttach(fileName, bid);
    }
  }
  
  @Override
  public void likes(BoardVO board) throws Exception {
    boardMapper.likes(board);
    Integer bid = board.getBid();
    
    boardMapper.deleteAttach(bid);
    
  
    }
  
  @Transactional
  @Override
  public void delete(BoardVO board) throws Exception {

    boardMapper.delete(board);
  }
  
  @Override
  public List<BoardVO> listAll() throws Exception {
    return boardMapper.listAll();
  }

  @Override
  public List<BoardVO> listCriteria(Criteria cri) throws Exception {

    return boardMapper.listCriteria(cri);
  }

  @Override
  public int listCountCriteria(Criteria cri) throws Exception {

    return boardMapper.countPaging(cri);
  }

  @Override
  public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {

    return boardMapper.listSearch(cri);
  }

  @Override
  public int listSearchCount(SearchCriteria cri) throws Exception {

    return boardMapper.listSearchCount(cri);
  }

  @Override
  public List<BoardVO> listPage(int page) throws Exception {

    if (page <= 0) {
      page = 1;
    }

    page = (page - 1) * 10;

    return boardMapper.listPage(page);
  }

	
  @Override public void updateReplyCnt(Integer bid, int amount) throws
  Exception { try { boardMapper.updateReplyCnt(bid, amount); } catch (Exception
	  e) { throw new Exception("댓글 수 업데이트 중 오류 발생", e); } }
	 
  @Override
  public List<String> getAttach(Integer bid) throws Exception {
    
    return boardMapper.getAttach(bid);
  }  
  
  @Override
  public void replaceAttach(String fullName, Integer bid) throws Exception {
      try {
          boardMapper.replaceAttach(fullName, bid);
      } catch (Exception e) {
          throw new Exception("첨부 파일 교체 중 오류 발생", e);
      }
  } 
  @Override
  public void create(BoardVO vo) throws Exception {
     boardMapper.create(vo);
  } 
 
  @Override
  public List<BoardVO> qList(char btype,String niname) throws Exception {

    return boardMapper.qList(btype,niname);
  }

  }
