package com.wy.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author WY
 * @Description:
 * @date 2019/7/23
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Accessors(chain = true)
@TableName(value = "user")
public class User implements Serializable{

    private static final long serialVersionUID = 2074660838134142877L;
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;

    private String name;

    private Integer age;

    @TableField(value = "birth_day")
    private LocalDate birthDay;

    private String nameDesc;


}
