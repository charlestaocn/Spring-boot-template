package com.charles.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charles.template.entity.dos.CommonConfig;
import com.charles.template.service.CommonConfigService;
import com.charles.template.dao.CommonConfigMapper;
import org.springframework.stereotype.Service;

/**
 * @author charlestao
 */
@Service
public class CommonConfigServiceImpl extends ServiceImpl<CommonConfigMapper, CommonConfig>
        implements CommonConfigService {

}




