package com.hzqiyunkeji.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by 傅令杰 on 2017/4/22
 */
@Entity(nameInDb = "admin_profile")
public class AdminProfile {
    @Id
    private long id = 0;
    private String name;
    private String token ;
    @Generated(hash = 1703894241)
    public AdminProfile(long id, String name, String token) {
        this.id = id;
        this.name = name;
        this.token = token;
    }
    @Generated(hash = 1294787661)
    public AdminProfile() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
   
}
