package com.jlq.mapper;

import com.jlq.pojo.TbComment;
import tk.mybatis.MyMapper;

import java.util.List;

public interface TbCommentMapper extends MyMapper<TbComment> {

    List<TbComment> findByBlogIdAndParentCommentNull(Long id);
}