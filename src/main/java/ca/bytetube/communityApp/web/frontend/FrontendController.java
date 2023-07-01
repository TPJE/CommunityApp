package ca.bytetube.communityApp.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontendController {
    /**
     * Home page router
     */
    @RequestMapping(value="/index", method = RequestMethod.GET)
    private String index() {
        return "frontend/index";
    }

    /**
     * Product list page router
     */
    @RequestMapping(value="/shoplist", method = RequestMethod.GET)
    private String showShopDetail() {
        return "frontend/shopdetail";
    }

    /**
     * Product detail router
     */
    @RequestMapping(value="/productdeatil", method = RequestMethod.GET)
    private String showProductDetail() {
        return "frontend/productdetail";
    }
}
