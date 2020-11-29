package com.jlq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_comment")
public class TbComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_comment")
    private Boolean adminComment;

    private String avatar;

    private String content;

    @Column(name = "create_time")
    private Date createTime;

    private String email;

    private String nickname;

    @Column(name = "blog_id")
    private Long blogId;

    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    private TbBlog blog;

    private List<TbComment> replyComments = new ArrayList<>();

    private static final long serialVersionUID = 1L;
    
}