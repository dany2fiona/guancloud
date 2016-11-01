package com.dany.projectdemo.model;

/**
 * Created by dan.y on 2016/11/1.
 */
public class ModifyRoom {

    /**
     * pk : 1
     * room_alias : 8689
     * roomid : 8689
     * image : http://222.73.0.213:8300/static/mcuser/img/%E4%BF%A1%E4%BB%B0%E7%9A%84%E7%96%AF%E5%AD%90.png
     * username : 信仰的疯子
     * active : false
     * status : SUCCESS
     * msg : 获取成功
     * code : 200
     */

    private String pk;
    private String room_alias;
    private String roomid;
    private String image;
    private String username;
    private boolean active;
    private String status;
    private String msg;
    private String code;

    public ModifyRoom(boolean active){
        this.active = active;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
