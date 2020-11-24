package com.jlq.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

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

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return admin_comment
     */
    public Boolean getAdminComment() {
        return adminComment;
    }

    /**
     * @param adminComment
     */
    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }

    /**
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * @return blog_id
     */
    public Long getBlogId() {
        return blogId;
    }

    /**
     * @param blogId
     */
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    /**
     * @return parent_comment_id
     */
    public Long getParentCommentId() {
        return parentCommentId;
    }

    /**
     * @param parentCommentId
     */
    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adminComment=").append(adminComment);
        sb.append(", avatar=").append(avatar);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", email=").append(email);
        sb.append(", nickname=").append(nickname);
        sb.append(", blogId=").append(blogId);
        sb.append(", parentCommentId=").append(parentCommentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}