package com.avc.observer;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017年10月30日
 */
public class TestObserver {
	public static void main(String[] args) {
		ProductList productList = ProductList.getInstance();
		TaoBaoObserver baoObserver = new TaoBaoObserver();
		JingDongObserver dongObserver = new JingDongObserver();
		productList.addProductObserver(baoObserver);
		productList.addProductObserver(dongObserver);
		productList.addProduct("蛇皮新货");
	}
}
