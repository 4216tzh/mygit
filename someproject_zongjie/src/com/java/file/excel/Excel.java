package com.java.file.excel;

import java.util.List;

public interface Excel<T> {

	public void downLoad(List<T> list, String fileName);

	public List<T> upLoad(String file);

}
