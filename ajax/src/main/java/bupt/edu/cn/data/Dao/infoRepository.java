package bupt.edu.cn.data.Dao;

import bupt.edu.cn.data.entity.info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface infoRepository extends JpaRepository<info,Long> {
    List<info> findByTel(String tel);
}
