package com.toan_itc.mobifone.libs.takephoto.library.permission;

import com.toan_itc.mobifone.libs.takephoto.library.model.InvokeParam;

/**
 * 授权管理回调
 */
public interface InvokeListener {
    PermissionManager.TPermissionType invoke(InvokeParam invokeParam);
}
