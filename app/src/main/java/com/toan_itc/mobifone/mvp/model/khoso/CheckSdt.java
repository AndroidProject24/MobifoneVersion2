package com.toan_itc.mobifone.mvp.model.khoso;

/**
 * Created by Toan.IT on 4/23/17.
 * Email: huynhvantoan.itc@gmail.com
 */

public class CheckSdt {

  /**
   * error : 0
   * reason : đã gửi thông tin vui lòng chờ hệ thống phản hồi
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
