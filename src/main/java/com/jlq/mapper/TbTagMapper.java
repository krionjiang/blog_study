package com.jlq.mapper;

import com.jlq.pojo.TbTag;
import tk.mybatis.MyMapper;

import java.util.List;

public interface TbTagMapper extends MyMapper<TbTag> {

    /**
     * 热门排序
     * @param size
     * @return
     */
    List<TbTag> listTop(Integer size);

    /**
     * 存在则更新不存在则新增
     * @param tag
     * @return
     */
    int saveAndFlush(TbTag tag);

    /**
     * 根据博客ID查询所含有的Tag标签
     * @param id
     * @return
     */
    List<TbTag> selectByBlogId(Integer id);
    
}