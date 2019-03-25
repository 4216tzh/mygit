package com.java.design.single;
/**
 * ����ģʽ
 * @author tzh
 *
 */
public final class SingleModels {
	
	private static class Singleton{
		//��̬��ʼ��������JVM����֤�̰߳�ȫ 
		private static SingleModels singleModels=new SingleModels();
	}
	
	private SingleModels(){};
	
	//��getInstance������һ�α����õ�ʱ������һ�ζ�ȡSingletonHolder.instance������SingletonHolder��õ���ʼ����  
	//���������װ�ز�����ʼ����ʱ�򣬻��ʼ�����ľ�̬�򣬴Ӷ�����Singleton��ʵ���������Ǿ�̬����  
	//���ֻ�ᱻ�������װ�����ʱ���ʼ��һ�Σ��������������֤�����̰߳�ȫ�ԡ�  
	//���ģʽ���������ڣ�getInstance������û�б�ͬ��������ֻ��ִ��һ����ķ��ʣ�����ӳٳ�ʼ����û�������κη��ʳɱ���
	public static SingleModels getInstance(){
		return Singleton.singleModels;
	}

}
