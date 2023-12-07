package org.zerock.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService {

  public void addReply(ReplyVO vo) throws Exception;

  public List<ReplyVO> listReply(@Param("bid")Integer bid) throws Exception;

  public void modifyReply(ReplyVO vo) throws Exception;

  public void removeReply(Integer rid) throws Exception;

  public List<ReplyVO> listReplyPage(@Param("bid")Integer bid, @Param("cri")Criteria cri) throws Exception;

  public int count(Integer bid) throws Exception;

  public List<ReplyVO> listPage(@Param("bid")Integer bid,@Param("cri") Criteria cri) throws Exception;
}
