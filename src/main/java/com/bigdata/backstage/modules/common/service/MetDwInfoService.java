package com.bigdata.backstage.modules.common.service;

import com.bigdata.backstage.modules.common.model.MetDwInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.backstage.modules.common.model.MetDwTableMapInfo;

/**
 * <p>
 * 数仓主表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-02-08
 */
public interface MetDwInfoService extends IService<MetDwInfo> {

    MetDwInfo getDwInfo(String dwNameEn);

    MetDwTableMapInfo getDwMapInfo(int dwId);
}
