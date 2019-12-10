package hbi.core.item.controllers;

import com.hand.hap.excel.annotation.ExcelExport;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.item.dto.ShopItem;
import hbi.core.item.service.IShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;

import java.util.Enumeration;
import java.util.List;

    @Controller
    public class ShopItemController extends BaseController{

    @Autowired
    private IShopItemService service;



    @ResponseBody
    @ExcelExport(table = ShopItem.class)
    @RequestMapping(value = "/test/shop/item/query")
    public ResponseData query(ShopItem dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request, HttpServletResponse response) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectItemByShopId(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/test/shop/item/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<ShopItem> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/test/shop/item/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<ShopItem> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }