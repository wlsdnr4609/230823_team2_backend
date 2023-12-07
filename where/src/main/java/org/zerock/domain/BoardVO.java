package org.zerock.domain;

import java.util.Date;
import lombok.Data;

@Data
public class BoardVO {
  private int bid;
  private int mid;
  private String niname;
  private String title;
  private char btype;
  private String cont;
  private int likes;
  private int counts;
  private int replycnt;
  private Date regdate;
  
  private String[] files;
}
