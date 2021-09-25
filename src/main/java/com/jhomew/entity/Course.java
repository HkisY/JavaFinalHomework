package com.jhomew.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author izuna
 * @since 2021-09-25
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Course implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "Id", type = IdType.AUTO)
      private Integer Id;

    private Integer courseNum;

    private String courseName;

    private String teacher;

    private String description;

    private Integer score;


}
