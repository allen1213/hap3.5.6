package hbi.core.region.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.region.dto.Region;
import hbi.core.region.service.IRegionService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RegionServiceImpl extends BaseServiceImpl<Region> implements IRegionService{

}