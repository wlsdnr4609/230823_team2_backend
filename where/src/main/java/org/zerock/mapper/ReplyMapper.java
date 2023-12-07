package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
  public List<ReplyVO> list(@Param("bid")Integer bid) throws Exception;

  public void create(ReplyVO vo) throws Exception;

  public void update(ReplyVO vo) throws Exception;

  public void delete(Integer rid) throws Exception;

  public List<ReplyVO> listPage(@Param("bid")Integer bid,@Param("cri") Criteria cri) throws Exception;

  public int count(Integer bid) throws Exception;

  public int getBid(@Param("rid")Integer rid) throws Exception;

}
