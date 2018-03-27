package com.mycompany.gestioncompra.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.gestioncompra.dao.CategoriaDAO;
import com.mycompany.gestioncompra.dao.OrdenDAO;
import com.mycompany.gestioncompra.dao.ProductoDAO;
import com.mycompany.gestioncompra.dto.Categoria;
import com.mycompany.gestioncompra.dto.Producto;
import com.mycompany.gestioncompra.modelo.CarInfo;
import com.mycompany.gestioncompra.modelo.CategoryInfo;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.gestioncompra.modelo.ClienteInfo;
import com.mycompany.gestioncompra.modelo.PaginationResult;
import com.mycompany.gestioncompra.modelo.ProductoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.mycompany.gestioncompra.util.Utils;
import com.mycompany.gestioncompra.validator.ClienteInfoValidator;

/**
 *
 * @author josera
 */
@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class MainController {
    @Autowired
    private OrdenDAO orderDAO;
 
    @Autowired
    private ProductoDAO productDAO;
 
    @Autowired
    private CategoriaDAO categoryDAO;
    
    @Autowired
    private ClienteInfoValidator customerInfoValidator;
 
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        // For Cart Form.
        // (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
        if (target.getClass() == CarInfo.class) {
 
        }
        // For Customer Form.
        // (@ModelAttribute("customerForm") @Validated CustomerInfo
        // customerForm)
        else if (target.getClass() == ClienteInfo.class) {
            dataBinder.setValidator(customerInfoValidator);
        }
 
    }
 
    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }
 
    @RequestMapping("/")
    public String home() {
        return "index";
    }
 
    // Category List
    @RequestMapping({ "/categoryList" })
    public String listCategoryHandler(Model model, //
            @RequestParam(value = "nombre", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
        
        PaginationResult<CategoryInfo> result = categoryDAO.queryCategories(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationCategories", result);
        
        return "categoryList";
    }
    
    // Products by Category
    @RequestMapping({ "/productListCategory" })
    public String productsByCategory(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") int code_cate,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 6;
        final int maxNavigationPage = 10;
        
        PaginationResult<ProductoInfo> result = productDAO.queryProductsByCate(page, //
                maxResult, maxNavigationPage, code_cate);
        
        model.addAttribute("paginationProductsByCate", result);
        return "productListCategory";
    }
    
    // Product List page.
    // Danh sách sản phẩm.
    @RequestMapping({ "/productList" })
    public String listProductHandler(Model model, //
            @RequestParam(value = "nombre", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 6;
        final int maxNavigationPage = 10;
 
        PaginationResult<ProductoInfo> result = productDAO.queryProducts(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationProducts", result);
        return "productList";
    }
 
    @RequestMapping({ "/buyProduct" })
    public String listProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code, //
            @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
 
        Producto product = null;
        if (code != null && code.length() > 0) {
            product = productDAO.findProduct(code);
        }
        if (product != null) {
 
            // Cart info stored in Session.
            CarInfo cartInfo = Utils.getCartInSession(request);
 
            ProductoInfo productInfo = new ProductoInfo(product);
 
            cartInfo.addProduct(productInfo, quantity);
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
        Producto product = null;
        if (code != null && code.length() > 0) {
            product = productDAO.findProduct(code);
        }
        if (product != null) {
 
            // Cart Info stored in Session.
            CarInfo cartInfo = Utils.getCartInSession(request);
 
            ProductoInfo productInfo = new ProductoInfo(product);
 
            cartInfo.removeProduct(productInfo);
 
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    // POST: Update quantity of products in cart.
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("cartForm") CarInfo cartForm) {
 
        CarInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
 
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    // GET: Show Cart
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CarInfo myCart = Utils.getCartInSession(request);
 
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }
 
    // GET: Enter customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
 
        CarInfo cartInfo = Utils.getCartInSession(request);
      
        // Cart is empty.
        if (cartInfo.isEmpty()) {
             
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        }
 
        ClienteInfo customerInfo = cartInfo.getClienteInfo();
        if (customerInfo == null) {
            customerInfo = new ClienteInfo();
        }
 
        model.addAttribute("customerForm", customerInfo);
 
        return "shoppingCartCustomer";
    }
 
    // POST: Save customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("customerForm") @Validated ClienteInfo customerForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
  
        // If has Errors.
        if (result.hasErrors()) {
            customerForm.setValid(false);
            // Forward to reenter customer info.
            return "shoppingCartCustomer";
        }
        
        System.out.println("NO ENTRE EN EL IF");
        
        customerForm.setValid(true);
        CarInfo cartInfo = Utils.getCartInSession(request);
 
        cartInfo.setClienteInfo(customerForm);
 
        // Redirect to Confirmation page.
        return "redirect:/shoppingCartConfirmation";
    }
 
    // GET: Review Cart to confirm.
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CarInfo cartInfo = Utils.getCartInSession(request);
 
        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
 
        return "shoppingCartConfirmation";
    }
 
    // POST: Send Cart (Save).
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CarInfo cartInfo = Utils.getCartInSession(request);
 
        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
        try {
            orderDAO.saveOrder(cartInfo);
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            return "shoppingCartConfirmation";
        }
        // Remove Cart In Session.
        Utils.removeCartInSession(request);
         
        // Store Last ordered cart to Session.
        Utils.storeLastOrderedCartInSession(request, cartInfo);
 
        // Redirect to successful page.
        return "redirect:/shoppingCartFinalize";
    }
 
    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {
 
        CarInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
 
        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
 
        return "shoppingCartFinalize";
    }
 
    @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("code") String code) throws IOException {
        Producto product = null;
        if (code != null) {
            product = this.productDAO.findProduct(code);
        }
        if (product != null && product.getImagen() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(product.getImagen());
        }
        response.getOutputStream().close();
    }
    
    @RequestMapping(value = { "/categoryImage" }, method = RequestMethod.GET)
    public void categoryImage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("code") int code) throws IOException {
        Categoria categoria = null;
        if (code != 0) {
            categoria = this.categoryDAO.findCategory(code);
        }
        if (categoria != null && categoria.getImagen() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(categoria.getImagen());
        }
        response.getOutputStream().close();
    }
}
