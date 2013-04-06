package org.zmf.poi.excel.yangpeng;

import java.io.Serializable;

public class RankingContent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String siteUrl = null;

	private String siteName = null;

	/**
	 * 网站排名:当前、一周平均、三月平均
	 */
	private String[] ranking = null;

	/**
	 * 独立访问者(人/百万人):当前、一周平均、三月平均
	 */
	private String[] uniqueVisitors = null;

	/**
	 * 人均页面浏览量(页/人):当前、一周平均、三月平均
	 */
	private String[] averagePageViews = null;

	public String[] getRanking() {
		return ranking;
	}

	public void setRanking(String[] ranking) {
		this.ranking = ranking;
	}

	public String[] getUniqueVisitors() {
		return uniqueVisitors;
	}

	public void setUniqueVisitors(String[] uniqueVisitors) {
		this.uniqueVisitors = uniqueVisitors;
	}

	public String[] getAveragePageViews() {
		return averagePageViews;
	}

	public void setAveragePageViews(String[] averagePageViews) {
		this.averagePageViews = averagePageViews;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
