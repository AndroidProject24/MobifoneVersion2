package com.toan_itc.mobifone.mvp.model.congno;

import java.util.List;

/**
 * Created by Toan.IT on 4/5/17.
 * Email: huynhvantoan.itc@gmail.com
 */

public class Congno {

  /**
   * error : 0
   * reason : success
   * data : [{"sdt":"01676542546","TenLoai":"Hoà mạng trả sau cá nhân","date":"2017-02-26 08:20:42","thanhtoan":"0","images":"{\"cmnd_mt\":\"01676542546_cmnd_mt2017-02-26-b2652e-test.jpg\",\"cmnd_ms\":\"01676542546_cmnd_ms2017-02-26-b2652e-test.jpg\",\"phieu\":\"01676542546_phieu2017-02-26-b2652e-test.jpg\"}","ghichu":"0"},{"sdt":"0901887889","TenLoai":"Đăng ký thông tin","date":"2017-02-27 09:28:06","thanhtoan":"1","images":"[\"8fc024-47187.png\",\"8fc024-60158.jpg\",\"8fc024-20150802-nam-sinh-da-nang-duoc-dac-cach-tot-nghiep-khi-vua-qua-doi-1.jpg\"]","ghichu":""},{"sdt":"01676542546","TenLoai":"Hoà mạng trả sau cá nhân","date":"2017-03-04 06:01:47","thanhtoan":"0","images":"{\"cmnd_mt\":\"01676542546_cmnd_mt2017-03-04-21edbd.jpg\",\"cmnd_ms\":\"01676542546_cmnd_ms2017-03-04-21edbd.jpg\",\"phieu\":\"01676542546_phieu2017-03-04-ef9834.jpg\"}","ghichu":"0"},{"sdt":"01676542546","TenLoai":"Hoà mạng trả sau cá nhân","date":"2017-03-04 06:37:08","thanhtoan":"0","images":"{\"cmnd_mt\":\"01676542546_cmnd_mt2017-03-04-ccc1c4.jpg\",\"cmnd_ms\":\"01676542546_cmnd_ms2017-03-04-aa3958.jpg\",\"phieu\":\"01676542546_phieu2017-03-04-eab075.jpg\"}","ghichu":"0"}]
   */

  private String error;
  private String reason;
  private List<DataBean> data;

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * sdt : 01676542546
     * TenLoai : Hoà mạng trả sau cá nhân
     * date : 2017-02-26 08:20:42
     * thanhtoan : 0
     * images : {"cmnd_mt":"01676542546_cmnd_mt2017-02-26-b2652e-test.jpg","cmnd_ms":"01676542546_cmnd_ms2017-02-26-b2652e-test.jpg","phieu":"01676542546_phieu2017-02-26-b2652e-test.jpg"}
     * ghichu : 0
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
}
