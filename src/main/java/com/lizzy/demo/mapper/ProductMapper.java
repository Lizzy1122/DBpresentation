package com.lizzy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizzy.demo.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<ProductEntity> {

}
