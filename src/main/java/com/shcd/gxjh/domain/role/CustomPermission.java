package com.shcd.gxjh.domain.role;

import com.shcd.Permission;
import javafx.util.Builder;

/**
 * @ClassName CustomPermission
 * @Description TODO
 * @Author hh
 * @Data
 * @Version 1.0
 **/

public class CustomPermission implements Permission {

    private int id;
    private String desc;



    public static Builder create() {
        return new Builder();
    }
    public static class Builder{
        private Integer id;
        private String desc;

        public Builder id(Integer val) {
            this.id = val;
            return this;
        }

        public Builder desc(String val) {
            this.desc = val;
            return this;
        }

        public CustomPermission build() {
            return new CustomPermission(this);
        }
    }

    public CustomPermission (Builder builder) {
        this.id = builder.id;
        this.desc = builder.desc;
    }

    public CustomPermission() {

    }

    public CustomPermission(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
