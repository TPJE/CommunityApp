package ca.bytetube.communityApp.web.frontend;

import ca.bytetube.communityApp.entity.ShopCategory;
import ca.bytetube.communityApp.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
    @Autowired
    private ShopCategoryService shopCategoryService;
}
