package org.zmf.assistant.java;


public class Main {

	public static String print(int n){
		StringBuilder sb = new StringBuilder() ;
		for(int i=1; i<=n; i++){
			sb.append(repeat(' ',n-i)) ;
			sb.append(repeat((char)('A'+i-1),2*i-1)) ;
			sb.append(repeat(' ',n-i)).append("\n") ;
		}
		
		return sb.toString() ;
	}
	public static String repeat(char ch,int n){
		StringBuilder sb = new StringBuilder() ;
		while(n-->0){
			sb.append(ch) ;
		}
		return sb.toString(); 
	}
	
	public static int findGreatestCommonFactor(int a, int b){
		int p=0,r=0 ;
		a=(a>b)?a:b ;
		r=a%b ;
		p=b ;
		if(p==0)return r;
		if(r==0)return p ;
		return (findGreatestCommonFactor(p, r)) ;		
	}
	
	
	public static void main(String[] args) {
		System.out.println(print(26));
		System.out.println(findGreatestCommonFactor(20, 8));
	}
}
