package com.utils.work.mongo;

import com.utils.work.http.GlobalException;
import com.utils.work.mongo.Dto.NumStatistics;
import com.utils.work.mongo.Dto.PersonEntity;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    String addMongo(PersonEntity personEntity) {
        // insert List
        List<PersonEntity> personEntityList = new ArrayList<>();
        personEntityList.add(personEntity);
        for (int i=0;i<9;i++) {
            PersonEntity p = new PersonEntity();
            p.setName("何"+i);
            p.setBirthday("19950"+i+"01");
            p.setSex(i%2==0?"male":"female");
            p.setCompany(i%2==0?"A":"B");
            List<NumStatistics> numStatisticsList = new ArrayList<>();
            for (int j=0;j<3;j++){
                NumStatistics n = new NumStatistics();
                n.setId(String.valueOf(j));
                n.setTotalNum(j*i);
                numStatisticsList.add(n);
            }
            p.setNumStatistics(numStatisticsList);
            personEntityList.add(p);
        }
            mongoTemplate.insert(personEntityList, "utils_person");

        // insert Entity
        PersonEntity p = new PersonEntity();
        p.setId(UUID.randomUUID().toString().replaceAll("_", ""));
        p.setName("单个插入");
        mongoTemplate.insert(p, "utils_person");
        return "success";
    }

    String deleteMongo(String id) {
        return "success";
    }

    String fixMongo(PersonEntity personEntity) {
        return "success";
    }

    String checkMongo(String ss) {
        // 查询单个数据
//        Pattern pattern = Pattern.compile("^.*8$",Pattern.CASE_INSENSITIVE);
//        Criteria c = Criteria.where("phone").regex(pattern);    // 封装所有语句，以方式的形式进行查询
//        Query q = new Query(c);  // 将语句进行封装或者添加排序之类的操作

        // 聚合查询
        aggregationCheck();
        return "success";
    }
    private void aggregationCheck() {
        // 第一阶段按照 某 字段过滤文档；第二阶段按照 某 字段对文档进行分组
         /* 聚合管道概念，
         最基本的管道提供过滤器，操作类似于查询和文档转换，可以修改输出文档的形式，
        其他管道提供了按特定字段等对文档进行分组和排序的工具，以及用户聚合数组内容的工具 */
        Criteria c = Criteria.where("birthday").gte("19950501").lt("19950801");
        Aggregation a = Aggregation.newAggregation(
                Aggregation.match(c), // 条件
                Aggregation.group("sex"), // 分组字段
//                Aggregation.sort("birthday"), // 排序
//                Aggregation.skip(page.getFirstResult()), // 过滤
                Aggregation.limit(40) // 返回记录数
        );
        AggregationResults<PersonEntity> personEntities = mongoTemplate.aggregate(a, "utils_person", PersonEntity.class);
        System.out.println("聚合查询："+personEntities);
//        可用map-reduce操作来执行聚合  query => map => reduce
//        单用途聚合操作：db.collection.estimatedDocumentCount()， db.collection.count() 和 db.collection.distinct("属性")


        /*
            1、统计各个公司的人数
        */
        Aggregation aggregation1 = Aggregation.newAggregation(Aggregation.group("company").count().as("totalNum"));
        AggregationResults<PersonEntity> outputTypeCount1 =
                mongoTemplate.aggregate(aggregation1, "utils_person", PersonEntity.class);
        System.out.println("各公司人数统计");
        ArrayList<Document> totalStatis = (ArrayList<Document>) outputTypeCount1.getRawResults().get("results");
        for (Document d:totalStatis) {
            System.out.println(d.get("_id")+"："+d.get("totalNum"));
        }

        /*
            2、统计各公司某项目 (“2” 项目) 中的指标在某范围的人数
         */
        Aggregation aggregation2 =
                Aggregation.newAggregation(
                        Aggregation.unwind("numStatistics"), // 分解一个文档中的数组字段
                        Aggregation.match(Criteria.where("numStatistics._id").is("2").and("numStatistics.totalNum").gt(3).lt(13)), // 并列条件用and连接
                        Aggregation.group("company").count().as("num")); // 按公司统计数量，并命名为num
        AggregationResults<PersonEntity> outputTypeCount2 =
                mongoTemplate.aggregate(aggregation2, "utils_person", PersonEntity.class);
        System.out.println("各公司某项目 (“2” 项目) 中的指标在某范围的人数");
        ArrayList<Document> totalStatis2 = (ArrayList<Document>) outputTypeCount2.getRawResults().get("results");
        for (Document d:totalStatis2) {
            System.out.println(d.get("_id")+"："+d.get("num"));
        }

        /*
            3、统计某公司（"A"）某项目（“2”）中的指标不在某范围的人数
        */
        Aggregation aggregation3 =
                Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("company").is("A")),
                        Aggregation.unwind("numStatistics"),
                        Aggregation.match(Criteria.where("numStatistics._id").is("2").orOperator( // “或”运算符拼接多个条件
                                Criteria.where("numStatistics.totalNum").lte(3),
                                Criteria.where("numStatistics.totalNum").gte(13))),
                        Aggregation.group("company").count().as("notNum"));
        AggregationResults<PersonEntity> outputTypeCount3 =
                mongoTemplate.aggregate(aggregation3, "utils_person", PersonEntity.class);
        System.out.println("A公司某项目 (“2” 项目) 中的指标不在某范围的人数");
        ArrayList<Document> totalStatis3 = (ArrayList<Document>) outputTypeCount3.getRawResults().get("results");
        for (Document d:totalStatis3) {
            System.out.println(d.get("_id")+"："+d.get("notNum"));
        }

        /*
            4、统计某公司各个项目指标的最大值、最小值、平均值
        */
        Aggregation aggregation4 =
                Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("company").is("A")),
                        Aggregation.unwind("numStatistics"),
                        Aggregation.group("$numStatistics._id").avg("$numStatistics.totalNum").as("avg").min("$numStatistics.totalNum").as
                                ("min").max("$numStatistics.totalNum").as("max"));
        AggregationResults<PersonEntity> outputTypeCount4 =
                mongoTemplate.aggregate(aggregation4, "utils_person", PersonEntity.class);
        System.out.println("某公司各个项目指标的最大值、最小值、平均值");
        ArrayList<Document> totalStatis4 = (ArrayList<Document>) outputTypeCount4.getRawResults().get("results");
        for (Document d:totalStatis4) {
            System.out.println(d.get("_id")+"项目：平均值："+d.get("avg")+"；最小值："+d.get("min")+"；最大值："+d.get("max"));
        }

    }
}
