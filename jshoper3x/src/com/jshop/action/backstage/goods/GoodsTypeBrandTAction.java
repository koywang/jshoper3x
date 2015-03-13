package com.jshop.action.backstage.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jshop.action.backstage.base.BaseTAction;
import com.jshop.action.backstage.utils.Validate;
import com.jshop.action.backstage.utils.statickey.StaticKey;
import com.jshop.entity.GoodsTypeBrandT;
import com.jshop.service.GoodsTypeBrandTService;
import com.jshop.service.impl.Serial;
@Namespace("")
@ParentPackage("jshop")
public class GoodsTypeBrandTAction extends BaseTAction {
	private static final long serialVersionUID = 1L;
	@Resource
	private GoodsTypeBrandTService goodsTypeBrandTService;
	private String goodsTypeBrandTid;
	private String goodsTypeId;
	private String name;
	private String brandname;
	private String brandid;
	private String query;
	private String qtype;
	private List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
	private int rp;
	private int page = 1;
	private int total = 0;
	private boolean sucflag;

	public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public List<Map<String,Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String,Object>> rows) {
		this.rows = rows;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isSucflag() {
		return sucflag;
	}

	public void setSucflag(boolean sucflag) {
		this.sucflag = sucflag;
	}

	public String getGoodsTypeBrandTid() {
		return goodsTypeBrandTid;
	}

	public void setGoodsTypeBrandTid(String goodsTypeBrandTid) {
		this.goodsTypeBrandTid = goodsTypeBrandTid;
	}


	/**
	 * 设置日期格式
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages();

	}

	/**
	 * 增加品牌商品
	 * 
	 * @return
	 */
	@Action(value = "addGoodsTypeBrand", results = { @Result(name = "json", type = "json") })
	public String addGoodsTypeBrand() {
		String brandidarray[] = StringUtils.split(this.getBrandid(), StaticKey.SPLITDOT);
		String brandnamearray[] =StringUtils.split(this.getBrandname(), StaticKey.SPLITDOT);
		GoodsTypeBrandT gtbt = new GoodsTypeBrandT();
		for (int j = 0; j < brandidarray.length; j++) {
			gtbt.setBrandid(brandidarray[j]);
			gtbt.setBrandname(brandnamearray[j]);
			gtbt.setGoodsTypeId(this.getGoodsTypeId());
			gtbt.setName(this.getName());
			Criterion criterion=Restrictions.and(Restrictions.eq("goodsTypeId", this.getGoodsTypeId())).add(Restrictions.eq("brandid", brandidarray[j]));
			GoodsTypeBrandT list = this.goodsTypeBrandTService.findOneByCriteria(GoodsTypeBrandT.class, criterion);
			if (list != null) {
				this.setSucflag(false);
			} else {
				gtbt.setGoodsTypeBrandTid(this.getSerial().Serialid(Serial.GOODSTYPEBRAND));
				this.goodsTypeBrandTService.save(gtbt);
			}

		}
		this.setSucflag(true);
		return JSON;
	}

	/**
	 *获取所有品牌商品
	 * 
	 * @return
	 */
	@Action(value = "findAllGoodsTypeBrand", results = { @Result(name = "json", type = "json") })
	public String findAllGoodsTypeBrand() {
		if (this.getQtype().equals(StaticKey.SC)) {
			findDefaultAllGoodsTypeBrand();
		} else {
			if (StringUtils.isBlank(this.getQtype())) {
				return JSON;
			} else {
				return JSON;
			}
		}
		return JSON;

	}

	public void findDefaultAllGoodsTypeBrand() {
		int currentPage = page;
		int lineSize = rp;
		total = this.goodsTypeBrandTService.count(GoodsTypeBrandT.class).intValue();
		if(StringUtils.isNotBlank(this.getSortname())&&StringUtils.isNotBlank(this.getSortorder())){
			Order order=null;
			if(StringUtils.equals(this.getSortorder(), StaticKey.ASC)){
				order=Order.asc(this.getSortname());
			}else{
				order=Order.desc(this.getSortname());
			}
			List<GoodsTypeBrandT> list = this.goodsTypeBrandTService.findByCriteriaByPage(GoodsTypeBrandT.class, order, currentPage, lineSize);
			this.processGoodsTypeBrandTList(list);
		}
	}

	public void processGoodsTypeBrandTList(List<GoodsTypeBrandT> list) {
		for (Iterator<GoodsTypeBrandT> it = list.iterator(); it.hasNext();) {
			GoodsTypeBrandT gtbt = (GoodsTypeBrandT) it.next();
			Map<String, Object> cellMap = new HashMap<String, Object>();
			cellMap.put("id", gtbt.getGoodsTypeBrandTid());
			cellMap.put("cell", new Object[] {gtbt.getBrandname(), gtbt.getName() });
			rows.add(cellMap);
		}

	}

	/**
	 * 删除品牌商品
	 * 
	 * @return
	 */
	@Action(value = "DelGoodsTypeBrand", results = { @Result(name = "json", type = "json") })
	public String DelGoodsTypeBrand() {
		if(StringUtils.isNotBlank(this.getGoodsTypeBrandTid())){
			String []strs=StringUtils.split(this.getGoodsTypeBrandTid(), StaticKey.SPLITDOT);
			for(String s:strs){
				GoodsTypeBrandT gbt=this.goodsTypeBrandTService.findByPK(GoodsTypeBrandT.class, s);
				if(gbt!=null){
					this.goodsTypeBrandTService.delete(gbt);
				}
			}
			this.setSucflag(true);
		}
		return JSON;
	}
}
