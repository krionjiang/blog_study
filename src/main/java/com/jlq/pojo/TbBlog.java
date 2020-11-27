package com.jlq.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_blog")
public class TbBlog implements Serializable {

    private TbUser user;

    private TbType type;

    private List<TbTag> tags;
    
    @Transient
    private Long tempTagId;
    
    @Transient
    private Integer count;

    @Transient
    private String tagIds;

    public void init(){
        //获取该博客的多个标签，将多个标签的id赋值给tagIds
        Object[] tagIds = this.getTags().stream().map(TbTag::getId).toArray();
        this.setTagIds(StringUtils.join(tagIds,","));
    }

//    public List<TbTag> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<TbTag> tags) {
//        this.tags = tags;
//    }
//
//    public TbType getType() {
//        return type;
//    }
//
//    public void setType(TbType type) {
//        this.type = type;
//    }
//
//    public TbUser getUser() {
//        return user;
//    }
//
//    public void setUser(TbUser user) {
//        this.user = user;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean appreciation;

    private boolean commentabled;
    

    @Column(name = "create_time")
    private Date createTime;

    private String description;

    @Column(name = "first_picture")
    private String firstPicture;

    private String flag;

    private boolean published;

    private Boolean recommend;

    private boolean share;

    private String title;
    
    
    @Column(name = "update_time")
    private Date updateTime;

    private Integer views;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "user_id")
    private Long userId;

    private String content;

    private static final long serialVersionUID = 1L;

//    /**
//     * @return id
//     */
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * @param id
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    /**
//     * @return appreciation
//     */
//    public Boolean getAppreciation() {
//        return appreciation;
//    }
//
//    /**
//     * @param appreciation
//     */
//    public void setAppreciation(Boolean appreciation) {
//        this.appreciation = appreciation;
//    }
//
//    /**
//     * @return commentabled
//     */
//    public Boolean getCommentabled() {
//        return commentabled;
//    }
//
//    /**
//     * @param commentabled
//     */
//    public void setCommentabled(Boolean commentabled) {
//        this.commentabled = commentabled;
//    }
//
//    /**
//     * @return create_time
//     */
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    /**
//     * @param createTime
//     */
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    /**
//     * @return description
//     */
//    public String getDescription() {
//        return description;
//    }
//
//    /**
//     * @param description
//     */
//    public void setDescription(String description) {
//        this.description = description == null ? null : description.trim();
//    }
//
//    /**
//     * @return first_picture
//     */
//    public String getFirstPicture() {
//        return firstPicture;
//    }
//
//    /**
//     * @param firstPicture
//     */
//    public void setFirstPicture(String firstPicture) {
//        this.firstPicture = firstPicture == null ? null : firstPicture.trim();
//    }
//
//    /**
//     * @return flag
//     */
//    public String getFlag() {
//        return flag;
//    }
//
//    /**
//     * @param flag
//     */
//    public void setFlag(String flag) {
//        this.flag = flag == null ? null : flag.trim();
//    }
//
//    /**
//     * @return published
//     */
//    public Boolean getPublished() {
//        return published;
//    }
//
//    /**
//     * @param published
//     */
//    public void setPublished(Boolean published) {
//        this.published = published;
//    }
//
//    /**
//     * @return recommend
//     */
//    public Boolean getRecommend() {
//        return recommend;
//    }
//
//    /**
//     * @param recommend
//     */
//    public void setRecommend(Boolean recommend) {
//        this.recommend = recommend;
//    }
//
//    /**
//     * @return share
//     */
//    public Boolean getShare() {
//        return share;
//    }
//
//    /**
//     * @param share
//     */
//    public void setShare(Boolean share) {
//        this.share = share;
//    }
//
//    /**
//     * @return title
//     */
//    public String getTitle() {
//        return title;
//    }
//
//    /**
//     * @param title
//     */
//    public void setTitle(String title) {
//        this.title = title == null ? null : title.trim();
//    }
//
//    /**
//     * @return update_time
//     */
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    /**
//     * @param updateTime
//     */
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    /**
//     * @return views
//     */
//    public Integer getViews() {
//        return views;
//    }
//
//    /**
//     * @param views
//     */
//    public void setViews(Integer views) {
//        this.views = views;
//    }
//
//    /**
//     * @return type_id
//     */
//    public Long getTypeId() {
//        return typeId;
//    }
//
//    /**
//     * @param typeId
//     */
//    public void setTypeId(Long typeId) {
//        this.typeId = typeId;
//    }
//
//    /**
//     * @return user_id
//     */
//    public Long getUserId() {
//        return userId;
//    }
//
//    /**
//     * @param userId
//     */
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    /**
//     * @return content
//     */
//    public String getContent() {
//        return content;
//    }
//
//    /**
//     * @param content
//     */
//    public void setContent(String content) {
//        this.content = content == null ? null : content.trim();
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", appreciation=").append(appreciation);
//        sb.append(", commentabled=").append(commentabled);
//        sb.append(", createTime=").append(createTime);
//        sb.append(", description=").append(description);
//        sb.append(", firstPicture=").append(firstPicture);
//        sb.append(", flag=").append(flag);
//        sb.append(", published=").append(published);
//        sb.append(", recommend=").append(recommend);
//        sb.append(", share=").append(share);
//        sb.append(", title=").append(title);
//        sb.append(", updateTime=").append(updateTime);
//        sb.append(", views=").append(views);
//        sb.append(", typeId=").append(typeId);
//        sb.append(", userId=").append(userId);
//        sb.append(", content=").append(content);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}