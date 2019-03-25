package com.java.design.single;
/**
 * 单例模式(多线程下互斥检查，保证线程安全)
 * @author tzh
 *
 */
public class SingleModel {
	
	private static volatile SingleModel singleModel=null;
	
	private String flag;
	
	private SingleModel(){}
	
	public static SingleModel getInstance(){
		if(singleModel==null){
			synchronized (SingleModel.class) {
				if(singleModel==null){
					singleModel=new SingleModel();
				}
			}
		}
		return singleModel;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
