package com.huijava.superiorjavablogs.mapper;

import com.google.common.collect.Lists;
import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.entity.Tags;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TagsMapperTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagsMapperTest.class);
    @Autowired
    private TagsMapper tagsMapper;

    @Test
    public void insertList() {
        List<Tags> tagsList = Lists.newArrayList();
        Tags tags = new Tags();
        tags.setName("chen");
        tagsList.add(tags);

        Tags tags2 = new Tags();
        tags2.setName("c");
        tagsList.add(tags2);
        LOGGER.info("tagsList={}", tagsList);
        tagsMapper.insertList(tagsList);
        LOGGER.info("tagsList2={}", tagsList);
    }
}