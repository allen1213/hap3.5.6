package hbi.core.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.item.mapper.ShopItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.item.dto.ShopItem;
import hbi.core.item.service.IShopItemService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopItemServiceImpl extends BaseServiceImpl<ShopItem> implements IShopItemService{

    @Autowired
    private ShopItemMapper itemMapper;

    @Override
    public List<ShopItem> selectItemByShopId(IRequest requestContext, ShopItem dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return itemMapper.selectItemByShopId(dto);
    }

    @Override
    public Boolean deleteItemByShopId(Long shopId) {
        return itemMapper.deleteItemByShopId(shopId);
    }

}