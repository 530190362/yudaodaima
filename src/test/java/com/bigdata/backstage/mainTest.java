package com.bigdata.backstage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigdata.backstage.modules.ums.model.UmsAdminRoleRelation;
import com.bigdata.backstage.modules.ums.model.UmsRole;
import com.bigdata.backstage.modules.ums.service.UmsAdminRoleRelationService;
import com.bigdata.backstage.modules.ums.service.UmsAdminService;
import com.bigdata.backstage.modules.ums.service.UmsRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class mainTest {

    @Autowired
    private UmsRoleService umsRoleService;

    @Autowired
    private UmsAdminRoleRelationService umsAdminRoleRelationService;

    @Test
    public void test1() {
        QueryWrapper<UmsAdminRoleRelation> wrapperRoleRelation = new QueryWrapper<>();
        wrapperRoleRelation.eq("admin_id", 1);
        long roleId = umsAdminRoleRelationService.getOne(wrapperRoleRelation).getRoleId();
        QueryWrapper<UmsAdminRoleRelation> wrapperRoleCount = new QueryWrapper<>();
        wrapperRoleCount.eq("role_id", roleId);
        long roleCount = umsAdminRoleRelationService.count(wrapperRoleCount);

        UmsRole umsRole  = umsRoleService.getById(roleId);
        umsRole.setAdminCount((int) roleCount);
        System.out.println(umsRole);
        umsRoleService.updateById(umsRole);

//        long count = umsAdminService.count();



    }
}
