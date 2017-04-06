package com.toan_itc.mobifone.mvp.model.khoso;

public class Dangsim{
	@Override
	public String toString() {
		return "Dangsim{" +
						"tenkey='" + tenkey + '\'' +
						", tends='" + tends + '\'' +
						'}';
	}

	private String tenkey;
	private String tends;

	public void setTenkey(String tenkey){
		this.tenkey = tenkey;
	}

	public String getTenkey(){
		return tenkey;
	}

	public void setTends(String tends){
		this.tends = tends;
	}

	public String getTends(){
		return tends;
	}
}