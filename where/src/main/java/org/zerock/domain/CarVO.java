package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CarVO {


	private int Cid;
	private String carNo;
	private String carBrand;
	private String carModel;
	private String charType;
	private Date regdate;
	private int evcapacity;
	private int mid;
}
