package com.goldencis.osa;

import com.goldencis.osa.core.entity.Permission;
import com.goldencis.osa.core.entity.Resource;
import com.goldencis.osa.core.service.IPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by limingchao on 2018/10/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AutoWireTest {

    @Autowired
    private IPermissionService permissionService;

    @Test
    public void autoWireTest() {
        System.out.println(permissionService);
        Resource resourceByResourceTypeAndId = permissionService.findResourceByResourceTypeAndId(1, 1);
        System.out.println(resourceByResourceTypeAndId);
    }

    @Test
    public void permissionServiceTest() {
        Permission permission = permissionService.findPermissionById(15);
        System.out.println(permission);
    }

}
