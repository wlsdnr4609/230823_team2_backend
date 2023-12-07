package org.zerock.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ReplyVO {

  private Integer rid;
  private Integer bid;
  private String replytext;
  private String replyer;

  private Date regdate;
  private Date updateregdate;


}
