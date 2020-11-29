package com.jlq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_tag")
public class TbTag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name")
    private String tagName;

    private List<TbBlog> blogs;

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
//     * @return tag_name
//     */
//    public String getTagName() {
//        return tagName;
//    }
//
//    /**
//     * @param tagName
//     */
//    public void setTagName(String tagName) {
//        this.tagName = tagName == null ? null : tagName.trim();
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", tagName=").append(tagName);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}