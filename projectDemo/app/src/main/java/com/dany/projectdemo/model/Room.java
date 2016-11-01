package com.dany.projectdemo.model;

import java.util.List;

/**
 * Created by dan.y on 2016/10/26.
 */
public class Room {


    /**
     * status : SUCCESS
     * code : 200
     * msg : 获取成功
     * pageSize : 30
     * currentPage : 1
     * totalPage : 1
     * total : 1
     * results : [{"pk":"5","room_alias":"8683","roomid":"8683","image":"http://222.73.0.213:8300/static/mcuser/img/0.18%E2%84%83.png","username":"0.18℃","active":true}]
     */

    private String status;
    private String code;
    private String msg;
    private String pageSize;
    private String currentPage;
    private String totalPage;
    private String total;
    /**
     * pk : 5
     * room_alias : 8683
     * roomid : 8683
     * image : http://222.73.0.213:8300/static/mcuser/img/0.18%E2%84%83.png
     * username : 0.18℃
     * active : true
     */

    private List<ResultsBean> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String pk;
        private String room_alias;
        private String roomid;
        private String image;
        private String username;
        private boolean active;

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }

        public String getRoom_alias() {
            return room_alias;
        }

        public void setRoom_alias(String room_alias) {
            this.room_alias = room_alias;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @Override
        public String toString() {
            return "[pk:"+pk+",roomId:"+roomid+",active:"+active+"]";
        }
    }
}