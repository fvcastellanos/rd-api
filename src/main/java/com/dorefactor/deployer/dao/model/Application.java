package com.dorefactor.deployer.dao.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document("applications")
public class Application {

    @BsonId
    private ObjectId id;
    private String name;

    @BsonProperty(useDiscriminator = true)
    private ApplicationSetup applicationSetup;
}