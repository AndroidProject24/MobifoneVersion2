package com.toan_itc.mobifone.mvp.model.congno;

import java.util.List;

/**
 * Created by Toan.IT on 4/11/17.
 * Email: huynhvantoan.itc@gmail.com
 */

public class ListCongno {

  /**
   * error : 0
   * reason : success
   * data : [{"sdt":"01676542546","TenLoai":"Hoà mạng trả sau cá nhân","date":"2017-04-08 04:34:27","thanhtoan":"0","images":"01676542546_cmnd_mt2017-02-26-b2652e-test.jpg,01676542546_cmnd_ms2017-02-26-b2652e-test.jpg,01676542546_phieu2017-02-26-b2652e-test.jpg","ghichu":"<p>0<\/p>\r\n"},{"sdt":"0901887889","TenLoai":"Đăng ký thông tin","date":"2017-02-27 09:28:06","thanhtoan":"1","images":"8fc024-47187.png,8fc024-60158.jpg,8fc024-20150802-nam-sinh-da-nang-duoc-dac-cach-tot-nghiep-khi-vua-qua-doi-1.jpg","ghichu":""},{"sdt":"01676542546","TenLoai":"Hoà mạng trả sau cá nhân","date":"2017-04-08 04:30:10","thanhtoan":"0","images":"01676542546_cmnd_mt2017-03-04-21edbd.jpg,01676542546_phieu2017-03-04-ef9834.jpg","ghichu":"<p>0<\/p>\r\n"},{"sdt":"01676542546","TenLoai":"Hoà mạng trả sau cá nhân","date":"2017-04-08 04:35:12","thanhtoan":"0","images":"01676542546_cmnd_mt2017-03-04-ccc1c4.jpg,01676542546_cmnd_ms2017-03-04-aa3958.jpg,01676542546_phieu2017-03-04-eab075.jpg","ghichu":"<p>0<\/p>\r\n"}]
   */

  private String error;
  private String reason;
  private List<Congno> data;

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

  public List<Congno> getData() {
    return data;
  }

  public void setData(List<Congno> data) {
    this.data = data;
  }
}
