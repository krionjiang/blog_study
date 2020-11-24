package com.jlq.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_blog_tags")
public class TbBlogTags implements Serializable {
    @Column(name = "blogs_id")
    private Long blogsId;

    @Column(name = "tags_id")
    private Long tagsId;

    private static final long serialVersionUID = 1L;

    /**
     * @return blogs_id
     */
    public Long getBlogsId() {
        return blogsId;
    }

    /**
     * @param blogsId
     */
    public void setBlogsId(Long blogsId) {
        this.blogsId = blogsId;
    }

    /**
     * @return tags_id
     */
    public Long getTagsId() {
        return tagsId;
    }

    /**
     * @param tagsId
     */
    public void setTagsId(Long tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", blogsId=").append(blogsId);
        sb.append(", tagsId=").append(tagsId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}