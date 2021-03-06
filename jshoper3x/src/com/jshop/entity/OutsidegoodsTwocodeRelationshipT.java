package com.jshop.entity;

// Generated 2014-10-9 18:51:28 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OutsidegoodsTwocodeRelationshipT generated by hbm2java
 */
@Entity
@Table(name = "outsidegoods_twocode_relationship_t", catalog = "jshoper3")
public class OutsidegoodsTwocodeRelationshipT implements java.io.Serializable {

	private String id;
	private String goodsid;
	private String goodsname;
	private String twocodepath;
	private String state;

	public OutsidegoodsTwocodeRelationshipT() {
	}

	public OutsidegoodsTwocodeRelationshipT(String id, String goodsid,
			String goodsname, String twocodepath, String state) {
		this.id = id;
		this.goodsid = goodsid;
		this.goodsname = goodsname;
		this.twocodepath = twocodepath;
		this.state = state;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 20)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "GOODSID", nullable = false, length = 20)
	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	@Column(name = "GOODSNAME", nullable = false, length = 100)
	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	@Column(name = "TWOCODEPATH", nullable = false)
	public String getTwocodepath() {
		return this.twocodepath;
	}

	public void setTwocodepath(String twocodepath) {
		this.twocodepath = twocodepath;
	}

	@Column(name = "STATE", nullable = false, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
