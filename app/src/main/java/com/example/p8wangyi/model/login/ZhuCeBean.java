package com.example.p8wangyi.model.login;

public class ZhuCeBean {

    /**
     * errno : 0
     * errmsg :
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYWYzZjA5MTAtZDhlNC00OGM5LWE4MzYtM2Q0MTYwYzA0YTljIiwiaWF0IjoxNjA4NjkzODQ5fQ.QTJmrw4K09vMB_SaNP_peGsd1Tk9IhgpTlZ_R4kIc1U","userInfo":{"uid":"af3f0910-d8e4-48c9-a836-3d4160c04a9c","username":"ijkij165","nickname":null,"gender":0,"avatar":"","birthday":0}}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYWYzZjA5MTAtZDhlNC00OGM5LWE4MzYtM2Q0MTYwYzA0YTljIiwiaWF0IjoxNjA4NjkzODQ5fQ.QTJmrw4K09vMB_SaNP_peGsd1Tk9IhgpTlZ_R4kIc1U
         * userInfo : {"uid":"af3f0910-d8e4-48c9-a836-3d4160c04a9c","username":"ijkij165","nickname":null,"gender":0,"avatar":"","birthday":0}
         */

        private String token;
        private UserInfoBean userInfo;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * uid : af3f0910-d8e4-48c9-a836-3d4160c04a9c
             * username : ijkij165
             * nickname : null
             * gender : 0
             * avatar :
             * birthday : 0
             */

            private String uid;
            private String username;
            private Object nickname;
            private int gender;
            private String avatar;
            private int birthday;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }
        }
    }
}
