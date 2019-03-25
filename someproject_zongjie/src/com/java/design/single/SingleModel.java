package com.java.design.single;
/**
 * ����ģʽ(���߳��»����飬��֤�̰߳�ȫ)
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
