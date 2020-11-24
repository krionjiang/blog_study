package tk.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/24 9:44
 */

public interface MyMapper<T> extends Mapper<T> , MySqlMapper<T> {
}
