package hbi.core.region.controllers;

import com.hand.hap.excel.annotation.ExcelExport;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.region.dto.Region;
import hbi.core.region.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class RegionController extends BaseController{

    @Autowired
    private IRegionService service;



    @ResponseBody
    @ExcelExport(table = Region.class)
    @RequestMapping(value = "/region/query")
    public ResponseData query(Region dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request, HttpServletResponse response) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,10));
    }

    @RequestMapping(value = "/region/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<Region> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/region/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Region> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }