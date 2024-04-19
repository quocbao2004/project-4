package com.javaweb.repository.custom.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.service.BuildingService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
        // TODO Auto-generated method stub

        // JPQL : JPA QUERY L
//		String sql = "FROM BuildingEntity b";
//		Query query = entityManager.createQuery(sql, BuildingEntity.class);

        String sql = createSql(buildingSearchBuilder, pageable);
        // SQL Native

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return  query.getResultList();
    }

    @Override
    public long countTotalItems(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT b.id) "
                + "FROM building b ");
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        queryNomal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString());
        Number result = (Number) query.getSingleResult();
        return result.longValue();
    }

    public static String createSql(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable){
        StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* "
                + "FROM building b ");
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        queryNomal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        sql.append(where);
//        sql.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
//                .append(" OFFSET ").append(pageable.getOffset());
        return sql.toString();
    }

    public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId") && !fieldName.equals("type") &&
                        !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingSearchBuilder);
                    if(value != null) {
                        if(item.getType().getName().equals("java.lang.Long")) {
                            where.append(" AND b." + fieldName + " = " + value);
                        }
                        else if(item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%'");
                        }
                    }
                }
                if(buildingSearchBuilder.getType() != null){
                    List<String>type = buildingSearchBuilder.getType();
                    if(type != null && type.size() != 0) {
                        where.append(" AND ( ");
                        String sql = type.stream().map(it -> " b.type like " + "'%" + it + "%'").collect(Collectors.joining(" or "));
                        where.append(sql + " ) ");
                    }
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if(staffId != null) {
            where.append(" AND assignmentbuilding.staffid = " + staffId);
        }
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        if(rentAreaTo != null || rentAreaFrom != null) {
            where.append(" AND EXISTS (SELECT * FROM rentarea WHERE b.id = rentarea.buildingid ");
            if( rentAreaFrom != null && NumberUtils.isNumber(rentAreaFrom.toString()) == true) {
                where.append(" AND rentarea.value >= " + rentAreaFrom);
            }
            if(rentAreaTo != null && NumberUtils.isNumber(rentAreaTo.toString()) == true ) {
                where.append(" AND rentarea.value <= " + rentAreaTo);
            }
            where.append(") ");
        }
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
            if(rentPriceTo != null || rentPriceTo != null) {
            if(rentPriceFrom != null && NumberUtils.isNumber(rentPriceFrom.toString()) == true) {
                where.append(" AND b.rentprice >= " + rentPriceFrom);
            }
            if(rentPriceTo != null && NumberUtils.isNumber(rentPriceTo.toString()) == true) {
                where.append(" AND b.rentprice <= " + rentPriceTo);
            }
        }
    }

    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if(staffId != null) {
            sql.append(" JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
    }
}