package com.avc.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author AmVilCres
 * @desc 描述
 * @date 2017年10月30日
 */
public class ProductList extends Observable {
	
	private List<String> productList = null; //产品列表
	
	private static ProductList instance; //类唯一实例
	
	private ProductList() {}  //构造方法私有化
	
	/**
	 * 获取唯一的实例
	 * @return 产品列表唯一实例
	 * */
	public static ProductList getInstance() {
		if(null==instance) {
			instance = new ProductList();
			instance.productList = new ArrayList<String>();
		}
		
		return instance;
	}
	
	/**
	 * 增加观察者(电商接口)
	 * @param observer 观察者
	 * */
	public void addProductObserver(Observer observer) {
		this.addObserver(observer);
	}
	
	/**
	 * 新增产品
	 * */
	public void addProduct(String newProduct) {
		productList.add(newProduct);
		System.out.println("产品列表发生了变化");
		this.setChanged(); //告知观察者被观察者发生了变化
		this.notifyObservers(newProduct);
	}
}
