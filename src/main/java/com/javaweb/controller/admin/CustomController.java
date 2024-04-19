package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller(value="customerControllerOfAdmin")
@ControllerAdvice
public class CustomController {
    @Autowired
    private MessageUtils messageUtil;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;

    @ExceptionHandler(Exception.class)
    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request, Exception ex) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        List<CustomerSearchResponse> res = customerService.findAll(customerSearchRequest , PageRequest.of((customerSearchRequest.getPage() - 1), customerSearchRequest.getMaxPageItems()));
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        customerSearchResponse.setListResult(res);
        customerSearchResponse.setTotalItems((int)customerService.countTotalItems(customerSearchRequest));
        mav.addObject("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
        mav.addObject("modelSearchCustomer", customerSearchRequest);
        mav.addObject("customerList", customerSearchResponse);
        mav.addObject("listStaffs", userService.getStaffs());
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView customerEdit(@ModelAttribute CustomerDTO customerDTO,  HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerEdit", customerDTO);
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView customerEdit(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO = customerService.getOneCustomerEdit(id);
        mav.addObject("customerEdit", customerDTO);
        return mav;
    }
}