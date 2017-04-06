package com.toan_itc.mobifone.intdef;

import com.toan_itc.mobifone.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by vantoan on 2/25/17.
 * Email: huynhvantoan.itc@gmail.com
 */
@android.support.annotation.IntDef({ResDef.MOBILE, ResDef.TABLET, ResDef.WEB})
@Retention(RetentionPolicy.SOURCE)
public @interface ResDef {
  int MOBILE= R.drawable.ic_upload;
  int TABLET= R.drawable.ic_arrow_white_24dp;
  int WEB= R.drawable.ic_banvas;
}
