/**
 * 最大公约数和最小公倍数的求解算法
 * 		|-GCD(最大公约数,greatest common divisor):辗转相除法
 * 		|-LCM（最小公倍数，least common multiple ）：两数之积除以最大公约数
 */
package org.zmf.assistant.java;

/**
 * 
 * @author 朱名发
 * @date 2013-4-11
 * @version 2.0
 * @email zhumingfa@zju.edu.cn
 * 
 */
public class GcdAndLcm {

	/**
	 * 辗转相除法求最大公约数：辗转相除法是古希腊求两个正整数的最大公约数的，也叫欧几里德算法，其方法是用较大的数除以较小的数，
	 * 上面较小的除数和得出的余数构成新的一对数，继续做上面的除法，直到出现能够整除的两个数，其中较小的数（即除数）就是最大公约数。
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a, int b) {
		int p, r;
		// 若a<b，则a与b互换
		if (a < b) {
			a = a + b;
			b = a - b;
			a = a - b;
		}
		r = a % b;
		p = b;
		if (r == 0)
			return p;
		if (p == 0)
			return r;
		return gcd(p, r);
	}

	/**
	 * 最小公倍数：两个数相乘一定是它们的公倍数.但不是最小公倍数.因为,它们相同的公因数乘了两次.而这些相同公因数都是它们的约数.其乘积就是最大因约数.
	 * 也就是说,最大公约数乘了两次.所以要除去一次.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	public static void main(String[] args) {
		System.out.println(gcd(172, 28));
	}

}
/**
 * 辗转相除法求最大公约数的证明 
 * （一）基本思想 
 * 设两数为a、b(a>b)，求a和b最大公约数gcd（a,b）步骤如下：
 * 		1)a/b=q1...r1，若r1=0,则gcd(a,b)=b;
 * 		2)若r1!=0,得b/r1=q2...r2。若r2=0,则gcd(a,b)=r1,否则重复2）直到rn=0为止，此时gcd(a,b)=pn。
 * （二）证明过程 
 * 设a/b=k...r,目标是证明：gcd(a,b)=gcd(b,r). 
 * 		1)令c=gcd(a,b),则设a=nc,b=mc;
 * 		2)则r=a-kb=nc-kmc=c(n-km); 
 * 		3)由2)知c也是r的因数;
 * 		4)可以判定m与n-km互素。（证明：若m与n-km有公约数，设m=th,n-km=tg得出n=km+tg=kth+tg=t(kh+g),
 * 		则a=nc=ct(kh+g),b=mc=cth，可知此时ct才是a和b的最大公约数，矛盾。）
 */
