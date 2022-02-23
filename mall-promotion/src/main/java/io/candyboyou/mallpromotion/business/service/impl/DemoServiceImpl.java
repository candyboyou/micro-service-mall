package io.candyboyou.mallpromotion.business.service.impl;

import io.candyboyou.mallpromotion.business.mapper.DemoMapper;
import io.candyboyou.mallpromotion.business.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public void demo() {
        demoMapper.addEntity();
    }
}
