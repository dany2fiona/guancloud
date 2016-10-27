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
     * total : 3
     * results : [{"id":"1","roomid":"1263","mcuser":null,"active":true},{"id":"2","roomid":"1262","mcuser":null,"active":true},{"id":"3","roomid":"0","mcuser":null,"active":false}]
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
     * roomid : 1263
     * mcuser : null
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
        private String roomid;
        private Object mcuser;
        private boolean active;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public Object getMcuser() {
            return mcuser;
        }

        public void setMcuser(Object mcuser) {
            this.mcuser = mcuser;
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
