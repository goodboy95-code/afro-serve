package ink.afro.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentInfo {
    Long id;
    String label;
    Long value;
    Boolean leaf;
    //子路由
    private List<DepartmentInfo> children;
}
