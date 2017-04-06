package com.toan_itc.mobifone.mvp.model.vas;

/**
 * Created by Toan.IT on 4/5/17.
 * Email: huynhvantoan.itc@gmail.com
 */

public class Vas {

  /**
   * error : 0
   * reason : gửi tn thành công sdt 0903669889 gói 12M120
   */

  private int error;
  private String reason;

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
}
