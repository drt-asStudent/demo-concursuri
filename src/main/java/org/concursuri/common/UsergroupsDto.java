package org.concursuri.common;

public class UsergroupsDto {
    Integer id;
    String username;
    String usergroup;

    public UsergroupsDto(Integer id, String username, String usergroup) {
        this.id = id;
        this.username = username;
        this.usergroup = usergroup;
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public String getUsergroup() {
        return usergroup;
    }
}
