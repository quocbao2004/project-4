package com.javaweb.repository.custom.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity>findAll (CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        String sql = createSql(customerSearchRequest, pageable);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    public String createSql(CustomerSearchRequest customerSearchRequest, Pageable pageable){
        StringBuilder sql = new StringBuilder("SELECT DISTINCT c.* "
                + "FROM customer c ");
        joinTable(customerSearchRequest, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1  AND is_active = 1 ");
        NewQuery(customerSearchRequest, where);
        sql.append(where);
        return sql.toString();
    }

    public void NewQuery(CustomerSearchRequest customerSearchRequest, StringBuilder where ) {
        try {
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId")){
                    Object value = item.get(customerSearchRequest);
                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long")) {
                            where.append(" AND c." + fieldName + " = " + value);
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND c." + fieldName + " LIKE '%" + value + "%'");
                        }
                    }
                }
                else if(fieldName.equals("staffId")){
                    Long staffId =  customerSearchRequest.getStaffId();
                    if(staffId != null) {
                        where.append(" AND assignmentcustomer.staffid = " + staffId);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void joinTable(CustomerSearchRequest customerSearchRequest, StringBuilder sql) {
        Long staffId = customerSearchRequest.getStaffId();
        if(staffId != null) {
            sql.append(" JOIN assignmentcustomer ON c.id = assignmentcustomer.customerid ");
        }
    }

    @Override
    public long countTotalItems(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT c.id) "
                + "FROM customer c ");
        joinTable(customerSearchRequest, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1   AND is_active = 1 ");
        NewQuery(customerSearchRequest, where);
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString());
        Number result = (Number) query.getSingleResult();
        return result.longValue();
    }
}
