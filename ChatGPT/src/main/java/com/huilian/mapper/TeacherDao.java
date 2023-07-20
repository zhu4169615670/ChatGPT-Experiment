package com.huilian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huilian.entity.Teacher;
import com.huilian.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author 大飞
 */
public interface TeacherDao extends BaseMapper<Teacher> {

    List<Integer> selectDaFenCount(@Param("id") Integer id);
}
