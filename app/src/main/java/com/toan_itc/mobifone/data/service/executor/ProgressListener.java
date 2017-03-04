package com.toan_itc.mobifone.data.service.executor;

/**
 * Toan.IT
 * Created by vantoan on 3/1/17.
 * Email: huynhvantoan.itc@gmail.com
 */

public interface ProgressListener {
  void update(long bytesRead, long contentLength, boolean done);
}
