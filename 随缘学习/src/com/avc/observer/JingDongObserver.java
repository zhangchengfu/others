package com.avc.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017年10月30日
 */
public class JingDongObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		String product = (String)arg;
		System.out.println("发送新产品【"+product+"】到京东");
	}

}
