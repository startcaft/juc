package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceDaoTest {

    @Autowired
    private IResourceDao resourceDao;

    @Test
    public void selectByPrimaryKey() throws Exception {

        Resource resource = resourceDao.selectByPrimaryKey(1l);
        System.out.print(resource);
    }

}