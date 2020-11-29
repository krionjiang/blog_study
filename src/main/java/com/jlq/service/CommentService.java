package com.jlq.service;

import com.jlq.mapper.TbCommentMapper;
import com.jlq.pojo.TbComment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/28 15:01
 */

@Service
public class CommentService {

    @Autowired
    private TbCommentMapper tbCommentMapper;

    //存放迭代找出的所有子代的集合
    private List<TbComment> tempReplys = new ArrayList<>();


    public List<TbComment> findByBlogID (Long id){
        List<TbComment> list = tbCommentMapper.findByBlogIdAndParentCommentNull(id);
        return eachTbComment(list);
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments
     * @return
     */
    private List<TbComment> eachTbComment(List<TbComment> comments) {
        List<TbComment> commentsView = new ArrayList<>();
        for (TbComment comment : comments) {
            TbComment c = new TbComment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<TbComment> comments) {

        for (TbComment comment : comments) {
            List<TbComment> replys1 = comment.getReplyComments();
            for (TbComment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }
    

    /**
     * 递归迭代，剥洋葱
     *
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(TbComment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size() > 0) {
            List<TbComment> replys = comment.getReplyComments();
            for (TbComment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }
}


