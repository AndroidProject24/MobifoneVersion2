package com.toan_itc.mobifone.mvp.model.khoso;

import java.util.List;

public class Khoso {

  /**
   * idkm : 784,782
   * error : 0
   * reason : success
   * data : [{"sdtview":"0901.304.254","goicuoc":"","gia":"60000"},{"sdtview":"0901.308.132","goicuoc":"","gia":"60000"},{"sdtview":"0901.308.346","goicuoc":"","gia":"60000"},{"sdtview":"0901.318.190","goicuoc":"","gia":"60000"},{"sdtview":"0901.319.471","goicuoc":"","gia":"60000"},{"sdtview":"0901.329.762","goicuoc":"","gia":"60000"},{"sdtview":"0901.341.076","goicuoc":"","gia":"60000"},{"sdtview":"0901.345.025","goicuoc":"","gia":"60000"},{"sdtview":"0901.347.512","goicuoc":"","gia":"60000"},{"sdtview":"0901.353.174","goicuoc":"","gia":"60000"},{"sdtview":"0901.353.861","goicuoc":"","gia":"60000"},{"sdtview":"0901.354.621","goicuoc":"","gia":"60000"},{"sdtview":"0901.362.147","goicuoc":"","gia":"60000"},{"sdtview":"0901.365.041","goicuoc":"","gia":"60000"},{"sdtview":"0901.372.483","goicuoc":"","gia":"60000"},{"sdtview":"0901.373.843","goicuoc":"","gia":"60000"},{"sdtview":"0901.375.106","goicuoc":"","gia":"60000"},{"sdtview":"0901.375.148","goicuoc":"","gia":"60000"},{"sdtview":"0901.377.413","goicuoc":"","gia":"60000"},{"sdtview":"0901.381.273","goicuoc":"","gia":"60000"},{"sdtview":"0901.381.564","goicuoc":"","gia":"60000"},{"sdtview":"0901.384.876","goicuoc":"","gia":"60000"},{"sdtview":"0901.389.752","goicuoc":"","gia":"60000"},{"sdtview":"0901.391.305","goicuoc":"","gia":"60000"},{"sdtview":"0901.392.807","goicuoc":"","gia":"60000"},{"sdtview":"0901.394.537","goicuoc":"","gia":"60000"},{"sdtview":"0901.399.823","goicuoc":"","gia":"60000"},{"sdtview":"0901.401.921","goicuoc":"","gia":"60000"},{"sdtview":"0901.402.381","goicuoc":"","gia":"60000"},{"sdtview":"0901.403.654","goicuoc":"","gia":"60000"},{"sdtview":"0901.404.783","goicuoc":"","gia":"60000"},{"sdtview":"0901.405.067","goicuoc":"","gia":"60000"},{"sdtview":"0901.406.182","goicuoc":"","gia":"60000"},{"sdtview":"0901.406.361","goicuoc":"","gia":"60000"},{"sdtview":"0901.406.581","goicuoc":"","gia":"60000"},{"sdtview":"0901.407.602","goicuoc":"","gia":"60000"},{"sdtview":"0901.408.143","goicuoc":"","gia":"60000"},{"sdtview":"0901.408.260","goicuoc":"","gia":"60000"},{"sdtview":"0901.409.654","goicuoc":"","gia":"60000"},{"sdtview":"0901.409.806","goicuoc":"","gia":"60000"},{"sdtview":"0901.409.924","goicuoc":"","gia":"60000"},{"sdtview":"0901.411.265","goicuoc":"","gia":"60000"},{"sdtview":"0901.411.306","goicuoc":"","gia":"60000"},{"sdtview":"0901.411.505","goicuoc":"","gia":"60000"},{"sdtview":"0901.411.875","goicuoc":"","gia":"60000"},{"sdtview":"0901.413.290","goicuoc":"","gia":"60000"},{"sdtview":"0901.413.405","goicuoc":"","gia":"60000"},{"sdtview":"0901.413.904","goicuoc":"","gia":"60000"},{"sdtview":"0901.413.934","goicuoc":"","gia":"60000"},{"sdtview":"0901.413.976","goicuoc":"","gia":"60000"}]
   * totalrows : 391749
   * page : {"nextLink":"http://n3t.top/test/api/timsim?search=&kho=trasau&dau=090&page=2"}
   */

  private String idkm;
  private int error;
  private String reason;
  private String totalrows;
  private PageBean page;
  private List<DataBean> data;

  public String getIdkm() {
    return idkm;
  }

  public void setIdkm(String idkm) {
    this.idkm = idkm;
  }

  public int getError() {
    return error;
  }

  public void setError(int error) {
    this.error = error;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getTotalrows() {
    return totalrows;
  }

  public void setTotalrows(String totalrows) {
    this.totalrows = totalrows;
  }

  public PageBean getPage() {
    return page;
  }

  public void setPage(PageBean page) {
    this.page = page;
  }

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class PageBean {
    /**
     * nextLink : http://n3t.top/test/api/timsim?search=&kho=trasau&dau=090&page=2
     */

    private String nextLink;

    public String getNextLink() {
      return nextLink;
    }

    public void setNextLink(String nextLink) {
      this.nextLink = nextLink;
    }
  }

  public static class DataBean {
    /**
     * sdtview : 0901.304.254
     * goicuoc :
     * gia : 60000
     */

    private String sdtview;
    private String goicuoc;
    private String gia;

    public String getSdtview() {
      return sdtview;
    }

    public void setSdtview(String sdtview) {
      this.sdtview = sdtview;
    }

    public String getGoicuoc() {
      return goicuoc;
    }

    public void setGoicuoc(String goicuoc) {
      this.goicuoc = goicuoc;
    }

    public String getGia() {
      return gia;
    }

    public void setGia(String gia) {
      this.gia = gia;
    }
  }
}
