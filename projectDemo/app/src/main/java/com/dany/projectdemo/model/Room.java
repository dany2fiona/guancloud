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
     * total : 25
     * results : [{"id":"1","room_alias":"8689","roomid":"8689","active":true},{"id":"3","room_alias":"8686","roomid":"8687","active":true},{"id":"4","room_alias":"8685","roomid":"8685","active":true},{"id":"5","room_alias":"8683","roomid":"8683","active":true},{"id":"6","room_alias":"8681","roomid":"8681","active":true},{"id":"7","room_alias":"8678","roomid":"8679","active":true},{"id":"8","room_alias":"8677","roomid":"8677","active":true},{"id":"9","room_alias":"8675","roomid":"8675","active":true},{"id":"10","room_alias":"8672","roomid":"8673","active":true},{"id":"11","room_alias":"8670","roomid":"8671","active":true},{"id":"12","room_alias":"8669","roomid":"8669","active":true},{"id":"13","room_alias":"8667","roomid":"8667","active":true},{"id":"14","room_alias":"8664","roomid":"8665","active":true},{"id":"15","room_alias":"8663","roomid":"8663","active":true},{"id":"16","room_alias":"8660","roomid":"8661","active":true},{"id":"17","room_alias":"8659","roomid":"8659","active":true},{"id":"18","room_alias":"8656","roomid":"8657","active":true},{"id":"19","room_alias":"8655","roomid":"8655","active":true},{"id":"20","room_alias":"8652","roomid":"8653","active":true},{"id":"21","room_alias":"8650","roomid":"8651","active":true},{"id":"22","room_alias":"8649","roomid":"8649","active":true},{"id":"23","room_alias":"8647","roomid":"8648","active":true},{"id":"24","room_alias":"8645","roomid":"8646","active":true},{"id":"25","room_alias":"8643","roomid":"8642","active":true},{"id":"26","room_alias":"8642","roomid":"8641","active":true}]
     */

    private String status;
    private String code;
    private String msg;
    private String pageSize;
    private String currentPage;
    private String totalPage;
    private String total;
    /**
     * id : 1
     * room_alias : 8689
     * roomid : 8689
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
        private String id;
        private String room_alias;
        private String roomid;
        private boolean active;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @Override
        public String toString() {
            return "[id:"+id+",roomId:"+roomid+",active:"+active+"]";
        }
    }
}
