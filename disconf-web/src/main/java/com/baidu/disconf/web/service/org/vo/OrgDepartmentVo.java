package com.baidu.disconf.web.service.org.vo;

import com.baidu.disconf.web.service.org.mybatis.OrgDepartment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class OrgDepartmentVo extends OrgDepartment {
    private List<OrgDepartmentVo> children = new ArrayList<>();
}
