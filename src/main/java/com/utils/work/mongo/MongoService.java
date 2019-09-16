package com.utils.work.mongo;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.utils.work.mongo.Dto.PersonEntity;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String addMongo(PersonEntity personEntity) {
        // insert List
        List<PersonEntity> personEntityList = new ArrayList<>();
        personEntityList.add(personEntity);
        for (int i=0;i<9;i++) {
            PersonEntity p = new PersonEntity();
            p.setName("何"+i);
            p.setBirthday("19950"+i+"01");
            p.setSex(i%2==0?"male":"female");
            personEntityList.add(p);
        }
        mongoTemplate.insert(personEntityList, "utils_person");
        // insert Entity
        PersonEntity p = new PersonEntity();
        p.setId(UUID.randomUUID().toString().replaceAll("_", ""));
        p.setName("单个插入");
        try {
            mongoTemplate.insert(p, "utils_person");
        }catch (Exception e) {
            throw e;
        }
        return "success";
    }

    public String deleteMongo (String id) {
        return "success";
    }

    public String fixMongo(PersonEntity personEntity) {
        return "success";
    }

    public String checkMongo(String ss) {
        // 查询单个数据
//        Pattern pattern = Pattern.compile("^.*8$",Pattern.CASE_INSENSITIVE);
//        Criteria c = Criteria.where("phone").regex(pattern);    // 封装所有语句，以方式的形式进行查询
//        Query q = new Query(c);  // 将语句进行封装或者添加排序之类的操作

        // 聚合查询
        String aggregationString = aggregationCheck();
        return "success";
    }
    private String aggregationCheck() {
        // 第一阶段按照 某 字段过滤文档；第二阶段按照 某 字段对文档进行分组
         /* 聚合管道概念，
         最基本的管道提供过滤器，操作类似于查询和文档转换，可以修改输出文档的形式，
        其他管道提供了按特定字段等对文档进行分组和排序的工具，以及用户聚合数组内容的工具 */
        Criteria c = Criteria.where("birthday").gte("19950501").lt("19950801");
        Aggregation a = Aggregation.newAggregation(
                Aggregation.match(c),
                Aggregation.group("sex")
        );
        AggregationResults<PersonEntity> personEntities = mongoTemplate.aggregate(a, "utils_person", PersonEntity.class);
        System.out.println("聚合查询："+personEntities);
//        可用map-reduce操作来执行聚合  query => map => reduce
//        单用途聚合操作：db.collection.estimatedDocumentCount()， db.collection.count() 和 db.collection.distinct("属性")
        return "success";
    }
}
