package com.toan_itc.mobifone.mvp.model.login;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("0")
    private _$0Bean _$0;
    private int error;
    private String reason;

    public _$0Bean get_$0() {
        return _$0;
    }

    public void set_$0(_$0Bean _$0) {
        this._$0 = _$0;
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

    public static class _$0Bean {
        /**
         * id : 2
         * username : test
         * email : test@localhost.com
         * auth_code : bd8de88b4a82e84eefa114979018ccc4
         */

        private String id;
        private String username;
        private String email;
        private String auth_code;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAuth_code() {
            return auth_code;
        }

        public void setAuth_code(String auth_code) {
            this.auth_code = auth_code;
        }
    }
}
