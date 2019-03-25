package com.java.study;

public class TzhController {
	
	@Inject
	private TzhDao tzhDao;
	
	public void save() {
		tzhDao.save();
		System.out.println("TzhController test");
	}

}
