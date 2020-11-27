package com.jlq.mapper;

import com.jlq.pojo.TbBlog;
import tk.mybatis.MyMapper;

import java.util.List;

public interface TbBlogMapper extends MyMapper<TbBlog> {
    
    int saveAndFlush (TbBlog blog);

    /**
     * 查询所有博客信息，（并且查询出Type信息，tags信息，user信息）
     * @return
     */
    List<TbBlog> selectAllNewTbBlog();

    /**
     * 查询所有博客信息，（并且查询出Type信息，tags信息，user信息）
     * @return
     */
    TbBlog selectAllNewTbBlogById(Long id);
    
    int saveAndFlushBlogTags(TbBlog blog);

    List<TbBlog> selectAllNewTbBlogInId(List<Long> list);
    
    void deleteBlogTags(Long id);
    
}