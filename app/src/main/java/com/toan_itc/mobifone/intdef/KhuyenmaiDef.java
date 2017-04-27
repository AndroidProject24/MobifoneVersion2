package com.toan_itc.mobifone.intdef;

import com.toan_itc.mobifone.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by vantoan on 2/25/17.
 * Email: huynhvantoan.itc@gmail.com
 */
@android.support.annotation.IntDef({KhuyenmaiDef.KHUYEN_MAI_TRA_SAU_CA_NHAN,KhuyenmaiDef.KHUYEN_MAI_TRA_SAU_DOANH_NGHIEP, KhuyenmaiDef.KHUYEN_MAI_SO_DEP, KhuyenmaiDef.KHUYEN_MAI_TRONG_NGAY})
@Retention(RetentionPolicy.SOURCE)
public @interface KhuyenmaiDef {
  int KHUYEN_MAI_TRA_SAU_CA_NHAN= R.string.km_trasau_cn;
  int KHUYEN_MAI_TRA_SAU_DOANH_NGHIEP= R.string.km_trasau_dn;
  int KHUYEN_MAI_SO_DEP=R.string.km_sodep;
  int KHUYEN_MAI_TRONG_NGAY=R.string.km_trongngay;
}