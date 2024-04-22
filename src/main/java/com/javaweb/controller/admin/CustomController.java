package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
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
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionService transactionService;

    private void setResponse(CustomerSearchResponse customerSearchResponse, CustomerSearchRequest customerSearchRequest){
        List<CustomerSearchResponse>findAll = customerService.findAll(customerSearchRequest , PageRequest.of(1, 10));
        customerSearchResponse.setListResult(findAll);
        customerSearchResponse.setTotalItems((int)customerService.countTotalItems(customerSearchRequest));
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request, Exception ex) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            Long staffid = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffid);
            setResponse(customerSearchResponse, customerSearchRequest);
            mav.addObject("customerList", customerSearchResponse);
        }
        else {
            setResponse(customerSearchResponse, customerSearchRequest);
            mav.addObject("customerList", customerSearchResponse);
        }
        mav.addObject("modelSearchCustomer", customerSearchRequest);
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
    public ModelAndView customerEdit(@PathVariable("id") Long Customerid, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        if(Customerid != null){
            CustomerDTO customerDTO = customerService.getOneCustomerEdit(Customerid);
            List<TransactionDTO> transactionDTOCSKH = transactionService.getTransactionEditCSKH(Customerid);
            List<TransactionDTO> transactionDTODDX = transactionService.getTransactionEditDDX(Customerid);
            mav.addObject("customerEdit", customerDTO);
            mav.addObject("transactionEdit1", transactionDTOCSKH);
            mav.addObject("transactionEdit2", transactionDTODDX);
        }
        mav.addObject("transactionType", TransactionType.transactionType());
        return mav;
    }
}