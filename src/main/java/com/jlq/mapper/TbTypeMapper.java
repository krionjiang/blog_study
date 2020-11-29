package com.jlq.mapper;

import com.jlq.pojo.TbTag;
import com.jlq.pojo.TbType;
import tk.mybatis.MyMapper;

import java.util.List;

public interface TbTypeMapper extends MyMapper<TbType> {
    /**
     * 热门排序
     * @param size
     * @return
     */
    List<TbType> listTop(Integer size);

    /**
     * 存在则更新不存在则新增
     * @param type
     * @return
     */
    int saveAndFlush(TbType type);

    /**
     * 增强版查询所有Types
     * @return
     */
    List<TbType> selectTypesAllPro();

    /**
     * 增强版根据Id查询Types
     * @param id
     * @return
     */
    TbType findByIDPro(Long id);
}