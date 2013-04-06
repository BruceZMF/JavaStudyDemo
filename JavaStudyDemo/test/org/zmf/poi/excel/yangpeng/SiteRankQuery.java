package org.zmf.poi.excel.yangpeng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/**
 * 
 * @author 朱名发
 * @date 2013-3-31
 * @version 2.0
 * @email zhumingfa@zju.edu.cn
 * 
 */
public class SiteRankQuery implements Serializable {
	private static final long serialVersionUID = 1L;

	static final String baseUrl = "http://www.chinarank.org.cn/overview/Info.do";

	static HashMap<String, RankingContent> map = new HashMap<String, RankingContent>();
	static List<String> list = new ArrayList<String>();

	public static void main(String args[]) throws Exception {
		deal() ;
	}

	private static void deal() throws Exception{
		int startLine = 0, endLine = 0;
		System.out.print("输入查询范围（即site.txt文件中第几行到第几行，例如第1行到20行：1 20）：");
		Scanner scanner0 = new Scanner(System.in);

		startLine = scanner0.nextInt();
		endLine = scanner0.nextInt();

		StringBuilder sb = new StringBuilder();
		sb.append("网站名称\t").append("网址\t")
				.append("排名：分别为当前排名、一周平均排名和三个月平均排名\t")
				.append("独立访问者(人/百万人):分别为当前排名、一周平均排名和三个月平均排名\t")
				.append("人均页面浏览量(页/人):" + "分别为当前排名、一周平均排名和三个月平均排名\n");

		File file = new File("D:\\zmf\\sites.txt");

		Scanner scanner = new Scanner(new FileInputStream(file), "GBK");
		String strLine = null;
		int i = 1;
		while (scanner.hasNextLine()) {
			if (i >= startLine && i <= endLine) {
				RankingContent rc = new RankingContent();
				strLine = scanner.nextLine();
				// System.out.println(strLine);

				if (dealWithStrLine(strLine, rc)) {
					if (init(rc)) {
						map.put(rc.getSiteUrl(), rc);
						Thread.sleep(1000 * 20);
					}
				}
			} else {
				scanner.nextLine();
			}
			i++;

		}

		Set<String> keySet = map.keySet();

		for (Iterator<String> iter = keySet.iterator(); iter.hasNext();) {
			String key = iter.next();
			RankingContent rc = map.get(key);
			// FIXME
			// System.out.println("rc="+rc);
			sb.append(rc.getSiteName()).append("\t\t").append(rc.getSiteUrl())
					.append("\t\t[");
			sb.append(rc.getRanking()[0]).append(" ")
					.append(rc.getRanking()[1]).append(" ")
					.append(rc.getRanking()[2]).append("]\t\t[");
			sb.append(rc.getUniqueVisitors()[0]).append(" ")
					.append(rc.getUniqueVisitors()[1]).append(" ")
					.append(rc.getUniqueVisitors()[2]).append("]\t\t[");
			sb.append(rc.getAveragePageViews()[0]).append(" ")
					.append(rc.getAveragePageViews()[1]).append(" ")
					.append(rc.getAveragePageViews()[2]).append("]\n");

		}

		// System.out.println(sb);
		PrintStream ps = new PrintStream(new File("D:\\zmf\\sites_" + startLine
				+ "_to_" + endLine + ".txt"));
		ps.write(sb.toString().getBytes());
		ps.close();

		ObjectOutputStream oops = new ObjectOutputStream(new FileOutputStream(
				"D:\\zmf\\map.obj"));
		oops.writeObject(map);
		oops.close();
	}
	private static boolean dealWithStrLine(String strLine, RankingContent rc) {
		boolean flag = false;
		String siteUrl = null;
		String siteName = null;
		if (strLine != null && strLine.length() > 0) {
			if (strLine.contains("www")) {
				String rst = strLine.substring(0, strLine.indexOf("www"));
				siteName = rst.trim();
				rst = strLine.substring(strLine.indexOf("www"));
				siteUrl = rst.trim();

				rc.setSiteName(siteName);
				rc.setSiteUrl(siteUrl);
				flag = true;
			} else {
				list.add("网站[" + strLine + "]属于子站，不参与综合排名！");
			}
		}
		return flag;
	}

	private static boolean init(RankingContent rc) {
		String siteUrl = rc.getSiteUrl();
		String siteName = rc.getSiteName();
		// System.out.println(siteUrl+"===="+siteName);
		
		try {
			URL url = new URL(baseUrl);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "GBK");
			out.write("url=" + siteUrl); // post的关键所在！
			out.flush();
			out.close();
			String sCurrentLine;
			String sTotalString;
			sCurrentLine = "";
			sTotalString = "";
			InputStream l_urlStream;
			l_urlStream = connection.getInputStream();
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(
					l_urlStream, "GBK"));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine + "\r\n";

			}

			String key1 = "&nbsp;排名";
			String key2 = "&nbsp;独立访问者(人/百万人)";
			String key3 = "&nbsp;人均页面浏览量(页/人)";
			if (sTotalString.indexOf(key1) == -1
					|| sTotalString.indexOf(key2) == -1
					|| sTotalString.indexOf(key3) == -1)
				return false;

			rc.setRanking(getSiteRanking(sTotalString, key1));
			rc.setUniqueVisitors(getSiteRanking(sTotalString, key2));
			rc.setAveragePageViews(getSiteRanking(sTotalString, key3));
			rc.setSiteName(siteName);
			rc.setSiteUrl(siteUrl);

			map.put(siteUrl, rc);
			String tmp = null ;
			tmp = rc.getSiteName() +"\t"+ rc.getSiteUrl()+"\t"
					+ rc.getRanking()[0] + "\t" + rc.getRanking()[1]+"\t"
					+ rc.getRanking()[2] + "\t" + rc.getUniqueVisitors()[0]+"\t"
					+ rc.getUniqueVisitors()[1] + "\t" + rc.getUniqueVisitors()[2] + "\t"
					+ rc.getAveragePageViews()[0] + "\t" + rc.getAveragePageViews()[1] + "\t"
					+ rc.getAveragePageViews()[2];
			System.out.println(tmp);
			return true;

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("暂时没有查到相关网站的信息。");
			return false;
		}

	}

	private static String[] getSiteRanking(String sTotalString, String key) {

		String targetStr = sTotalString.substring(sTotalString.indexOf(key));
		// System.out.println(targetStr);
		int first = targetStr.indexOf('>');

		String[] result = new String[3];
		// 排名：tp1、tp2、tp3分别表示当前排名、一周平均排名和三个月平均排名
		String tp1 = targetStr.substring(first + 54);
		int tp1_last = tp1.indexOf('<');
		// System.out.println("当前排名:\t"+tp1.substring(0,tp1_last));
		result[0] = tp1.substring(0, tp1_last);
		String tp2 = tp1.substring(tp1_last + 58);
		int tp2_last = tp2.indexOf('<');
		// System.out.println("一周平均排名:\t"+tp2.substring(0,tp2_last));
		result[1] = tp2.substring(0, tp2_last);
		String tp3 = tp2.substring(tp2_last + 58);
		int tp3_last = tp3.indexOf('<');
		// System.out.println("三个月平均排名：\t"+tp3.substring(0,tp3_last));
		result[2] = tp3.substring(0, tp3_last);

		return result;
	}
}

