package com.utils.work.mongo.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PersonEntity {
    @Id
    private String id;
    @ApiModelProperty("姓名")
    private String name;
    private String idcard;
    private String sex;
    private String phone;
    private String address;
    private String birthday;
}
