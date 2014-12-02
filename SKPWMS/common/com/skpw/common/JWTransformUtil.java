package com.skpw.common;

public class JWTransformUtil {

	//经纬度转换
	public static double transformLongitude(int degree, int minute, double second){
		
		return (second/60.0+minute)/60.0+degree;
	}
}
