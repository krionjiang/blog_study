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
@Table(name = "tb_type")
public class TbType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name")
    private String typeName;
    
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
//     * @return type_name
//     */
//    public String getTypeName() {
//        return typeName;
//    }
//
//    /**
//     * @param typeName
//     */
//    public void setTypeName(String typeName) {
//        this.typeName = typeName == null ? null : typeName.trim();
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", typeName=").append(typeName);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}