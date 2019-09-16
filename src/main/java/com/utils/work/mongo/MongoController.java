package com.utils.work.mongo;

import com.utils.work.mongo.Dto.PersonEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utils/mongo")
public class MongoController {

    @Autowired
    MongoService mongoService;

    @ApiOperation(value="增")
    @PutMapping(value="/add")
   public String addMongo(@RequestBody PersonEntity personEntity) {
       return mongoService.addMongo(personEntity);
   }

    @ApiOperation(value="删")
    @DeleteMapping(value="/delete")
    public String deleteMongo(@RequestParam String id) {
        return mongoService.deleteMongo(id);
    }

    @ApiOperation(value="改")
    @PostMapping(value="/fix")
    public String fixMongo(@RequestBody PersonEntity personEntity) {
        return mongoService.fixMongo(personEntity);
    }

    @ApiOperation(value="查")
    @GetMapping(value = "/check")
    public String checkMongo(@RequestParam String ss) {
        return mongoService.checkMongo(ss);
    }
}
