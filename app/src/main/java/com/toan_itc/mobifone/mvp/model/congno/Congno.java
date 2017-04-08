package com.toan_itc.mobifone.mvp.model.congno;

/**
 * Created by Toan.IT on 4/5/17.
 * Email: huynhvantoan.itc@gmail.com
 */

public class Congno {
  /**
   * sdt : 01676542546
   * TenLoai : Hoà mạng trả sau cá nhân
   * date : 2017-04-08 04:34:27
   * thanhtoan : 0
   * images : ["01676542546_cmnd_mt2017-02-26-b2652e-test.jpg","01676542546_cmnd_ms2017-02-26-b2652e-test.jpg","01676542546_phieu2017-02-26-b2652e-test.jpg"]
   * ghichu : <p>0</p>

   */

  private String sdt;
  private String TenLoai;
  private String date;
  private String thanhtoan;
  private String images;
  private String ghichu;

  public String getSdt() {
    return sdt;
  }

  public void setSdt(String sdt) {
    this.sdt = sdt;
  }

  public String getTenLoai() {
    return TenLoai;
  }

  public void setTenLoai(String TenLoai) {
    this.TenLoai = TenLoai;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getThanhtoan() {
    return thanhtoan;
  }

  public void setThanhtoan(String thanhtoan) {
    this.thanhtoan = thanhtoan;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public String getGhichu() {
    return ghichu;
  }

  public void setGhichu(String ghichu) {
    this.ghichu = ghichu;
  }
}
