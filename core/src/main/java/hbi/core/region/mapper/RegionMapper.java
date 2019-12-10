package hbi.core.region.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.region.dto.Region;

import java.util.List;

public interface RegionMapper extends Mapper<Region>{

    List<Region> selectProvince(Region region);

    List<Region> selectRegion(Region region);

}