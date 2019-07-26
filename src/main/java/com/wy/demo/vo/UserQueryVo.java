package com.wy.demo.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.demo.entity.User;
import lombok.*;
import lombok.experimental.Accessors;

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
public class UserQueryVo extends Page<User> {

    private static final long serialVersionUID = 2074660838134142877L;
    private Long id;

    private String name;

    private Integer age;

    private LocalDate birthDay;

    private String nameDesc;


}
