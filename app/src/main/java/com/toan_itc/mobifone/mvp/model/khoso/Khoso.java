package com.toan_itc.mobifone.mvp.model.khoso;

import java.util.List;

public class Khoso {

  /**
   * error : 0
   * reason : success
   * data : [{"sdt":"903852409","sdtview":"0903.852.409","loai":"tudo","gia":"60000"},{"sdt":"903157209","sdtview":"0903.157.209","loai":"tudo","gia":"60000"},{"sdt":"902604409","sdtview":"0902.604.409","loai":"tudo","gia":"60000"},{"sdt":"902652409","sdtview":"0902.652.409","loai":"tudo","gia":"60000"},{"sdt":"902406809","sdtview":"0902.406.809","loai":"tudo","gia":"60000"},{"sdt":"902538509","sdtview":"0902.538.509","loai":"tudo","gia":"60000"},{"sdt":"906773809","sdtview":"0906.773.809","loai":"tudo","gia":"60000"},{"sdt":"906797309","sdtview":"0906.797.309","loai":"tudo","gia":"60000"},{"sdt":"906325509","sdtview":"0906.325.509","loai":"tudo","gia":"60000"},{"sdt":"906924109","sdtview":"0906.924.109","loai":"tudo","gia":"60000"},{"sdt":"909978309","sdtview":"0909.978.309","loai":"tudo","gia":"60000"},{"sdt":"901341809","sdtview":"0901.341.809","loai":"tudo","gia":"60000"},{"sdt":"901445209","sdtview":"0901.445.209","loai":"tudo","gia":"60000"},{"sdt":"903739809","sdtview":"0903.739.809","loai":"tudo","gia":"60000"},{"sdt":"903137109","sdtview":"0903.137.109","loai":"tudo","gia":"60000"},{"sdt":"909320109","sdtview":"0909.320.109","loai":"tudo","gia":"60000"},{"sdt":"909760409","sdtview":"0909.760.409","loai":"tudo","gia":"60000"},{"sdt":"909054309","sdtview":"0909.054.309","loai":"tudo","gia":"60000"},{"sdt":"909861709","sdtview":"0909.861.709","loai":"tudo","gia":"60000"},{"sdt":"902882109","sdtview":"0902.882.109","loai":"tudo","gia":"60000"},{"sdt":"906773509","sdtview":"0906.773.509","loai":"tudo","gia":"60000"},{"sdt":"901378909","sdtview":"0901.378.909","loai":"tudo","gia":"60000"},{"sdt":"901478809","sdtview":"0901.478.809","loai":"tudo","gia":"60000"},{"sdt":"903758009","sdtview":"0903.758.009","loai":"tudo","gia":"60000"},{"sdt":"903639709","sdtview":"0903.639.709","loai":"tudo","gia":"60000"},{"sdt":"903163109","sdtview":"0903.163.109","loai":"tudo","gia":"60000"},{"sdt":"909267209","sdtview":"0909.267.209","loai":"tudo","gia":"60000"},{"sdt":"909296809","sdtview":"0909.296.809","loai":"tudo","gia":"60000"},{"sdt":"909126209","sdtview":"0909.126.209","loai":"tudo","gia":"60000"},{"sdt":"902588909","sdtview":"0902.588.909","loai":"tudo","gia":"60000"},{"sdt":"906855009","sdtview":"0906.855.009","loai":"tudo","gia":"60000"},{"sdt":"901301609","sdtview":"0901.301.609","loai":"tudo","gia":"60000"},{"sdt":"903380209","sdtview":"0903.380.209","loai":"tudo","gia":"60000"},{"sdt":"901182809","sdtview":"0901.182.809","loai":"tudo","gia":"60000"},{"sdt":"909282509","sdtview":"0909.282.509","loai":"tudo","gia":"60000"},{"sdt":"909398409","sdtview":"0909.398.409","loai":"tudo","gia":"60000"},{"sdt":"909568409","sdtview":"0909.568.409","loai":"tudo","gia":"60000"},{"sdt":"909758309","sdtview":"0909.758.309","loai":"tudo","gia":"60000"},{"sdt":"909948509","sdtview":"0909.948.509","loai":"tudo","gia":"60000"},{"sdt":"906860409","sdtview":"0906.860.409","loai":"tudo","gia":"60000"},{"sdt":"901313809","sdtview":"0901.313.809","loai":"tudo","gia":"60000"},{"sdt":"902637909","sdtview":"0902.637.909","loai":"tudo","gia":"60000"},{"sdt":"902960309","sdtview":"0902.960.309","loai":"tudo","gia":"60000"},{"sdt":"909291409","sdtview":"0909.291.409","loai":"tudo","gia":"60000"},{"sdt":"902472209","sdtview":"0902.472.209","loai":"tudo","gia":"60000"},{"sdt":"906975509","sdtview":"0906.975.509","loai":"tudo","gia":"60000"},{"sdt":"906994709","sdtview":"0906.994.709","loai":"tudo","gia":"60000"},{"sdt":"903979309","sdtview":"0903.979.309","loai":"tudo","gia":"60000"},{"sdt":"903383509","sdtview":"0903.383.509","loai":"tudo","gia":"60000"},{"sdt":"903066509","sdtview":"0903.066.509","loai":"tudo","gia":"60000"}]
   * totalrows : 378
   * page : {"firstLink":"http://n3t.top/test/api/timsim?search=09&kho=trasau&dau=090","prevLink":"","nextLink":"http://n3t.top/test/api/timsim?search=09&kho=trasau&dau=090&page=2","lastLink":"http://n3t.top/test/api/timsim?search=09&kho=trasau&dau=090&page=8"}
   * pagelist : {"1":"","2":"http://n3t.top/test/api/timsim?search=09&kho=trasau&dau=090&page=2","3":"http://n3t.top/test/api/timsim?search=09&kho=trasau&dau=090&page=3","4":"http://n3t.top/test/api/timsim?search=09&kho=trasau&dau=090&page=4"}
   */

  private int error;
  private String reason;
  private String totalrows;
  private PageBean page;
  private List<Data> data;

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
  public List<Data> getData() {
    return data;
  }

  public void setData(List<Data> data) {
    this.data = data;
  }
  public class Data{
    private String sdt;
    private String sdtview;
    private String loai;
    private String gia;
    public void setSdt(String sdt){
      this.sdt = sdt;
    }

    public String getSdt(){
      return sdt;
    }

    public void setLoai(String loai){
      this.loai = loai;
    }

    public String getLoai(){
      return loai;
    }

    public void setGia(String gia){
      this.gia = gia;
    }

    public String getGia(){
      return gia;
    }

    public String getSdtview() {
      return sdtview;
    }

    public void setSdtview(String sdtview) {
      this.sdtview = sdtview;
    }

  }
  public class PageBean {
    private String nextLink;

    public String getNextLink() {
      return nextLink;
    }

    public void setNextLink(String nextLink) {
      this.nextLink = nextLink;
    }
  }
}
