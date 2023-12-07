package org.zerock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

  @Autowired
  private ReplyMapper replyMapper;

  @Autowired
  private BoardMapper boardMapper;

  @Transactional
  @Override
  public void addReply(ReplyVO vo) throws Exception {

    replyMapper.create(vo);
	boardMapper.updateReplyCnt(vo.getBid(), 1); 
  }

  @Transactional
  @Override
  public void removeReply(Integer rid) throws Exception {

    int bid = replyMapper.getBid(rid);
    replyMapper.delete(rid);
    boardMapper.updateReplyCnt(bid, -1); 
  }



  @Override
  public List<ReplyVO> listReply(Integer bid) throws Exception {

    return replyMapper.list(bid);
  }

  @Override
  public void modifyReply(ReplyVO vo) throws Exception {

    replyMapper.update(vo);
  }



  @Override
  public List<ReplyVO> listReplyPage(Integer bid, Criteria cri) throws Exception {

    return replyMapper.listPage(bid, cri);
  }

  @Override
  public int count(Integer bid) throws Exception {

    return replyMapper.count(bid);
  }

  

  public List<ReplyVO> listPage(Integer bid, Criteria cri) throws Exception {
      try {
          return replyMapper.listPage(bid, cri);
      } catch (Exception e) {
          throw new Exception("댓글 목록 조회 중 오류 발생", e);
      }
  }
}
