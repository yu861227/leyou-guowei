package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author guo
 */
@Table(name = "tb_spec_param")
@Data
public class SpecParam {
    @Id
    @KeySql(useGeneratedKeys=true)
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    /**
     *字段名和列名不一致 需要指定
     */
    @Column(name = "`numeric`")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
    
    // getter和setter ...
}