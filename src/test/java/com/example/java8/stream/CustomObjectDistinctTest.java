package com.example.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomObjectDistinctTest {

    private static final String LOG_FORMAT_ORIG_LIST = "Orig List: {}";
    private static final String LOG_FORMAT_UNIQ_LIST = "Unique List: {}";

    private static final String ASSERT_MSG_NOT_BE_EXIST = "The item must not be exist";
    private static final String ASSERT_MSG_ITEM_NOT_MATCHED = "The item is not matched";

    private List<HashAndEqualsObject> hashAndEqualsObjectList;
    private List<NoHashAndEqualsObject> noHashAndEqualsObjectList;

    private HashAndEqualsObject hashAndEqualsObject1;
    private HashAndEqualsObject hashAndEqualsObject2;
    private HashAndEqualsObject hashAndEqualsObject3;

    private NoHashAndEqualsObject noHashAndEqualsObject1;
    private NoHashAndEqualsObject noHashAndEqualsObject2;
    private NoHashAndEqualsObject noHashAndEqualsObject3;

    @Before
    public void setUp() {

        hashAndEqualsObject1 = new HashAndEqualsObject("a", "b");
        hashAndEqualsObject2 = new HashAndEqualsObject("a", "b");
        hashAndEqualsObject3 = new HashAndEqualsObject("b", "c");

        noHashAndEqualsObject1 = new NoHashAndEqualsObject("a", "b");
        noHashAndEqualsObject2 = new NoHashAndEqualsObject("a", "b");
        noHashAndEqualsObject3 = new NoHashAndEqualsObject("b", "c");

        hashAndEqualsObjectList = Lists.newArrayList(
                hashAndEqualsObject1,
                hashAndEqualsObject2,
                hashAndEqualsObject3);

        noHashAndEqualsObjectList = Lists.newArrayList(
                noHashAndEqualsObject1,
                noHashAndEqualsObject2,
                noHashAndEqualsObject3);
    }

    @Test
    public void testDistinctObjectWithHashAndEquals() {
        List<HashAndEqualsObject> list = new ArrayList<>(hashAndEqualsObjectList);
        log.info(LOG_FORMAT_ORIG_LIST, list);

        List<HashAndEqualsObject> uniques = list.stream()
                .distinct()
                .collect(Collectors.toList());

        log.info(LOG_FORMAT_UNIQ_LIST, uniques);

        Assert.assertFalse(ASSERT_MSG_NOT_BE_EXIST, uniques.contains(hashAndEqualsObject2));
        Assert.assertEquals(ASSERT_MSG_ITEM_NOT_MATCHED, uniques.get(0), hashAndEqualsObject1);
        Assert.assertEquals(ASSERT_MSG_ITEM_NOT_MATCHED, uniques.get(1), hashAndEqualsObject3);
    }

    @Test
    public void testDistinctObjectWithoutHashAndEqualsViaSet() {
        List<NoHashAndEqualsObject> list = new ArrayList<>(noHashAndEqualsObjectList);
        log.info(LOG_FORMAT_ORIG_LIST, list);

        final Set<List<String>> seenList = new HashSet<>();
        list.removeIf(t -> !seenList.add(Arrays.asList(t.id, t.name)));

        log.info(LOG_FORMAT_UNIQ_LIST, list);

        Assert.assertFalse(ASSERT_MSG_NOT_BE_EXIST, list.contains(noHashAndEqualsObject1));
        Assert.assertEquals(ASSERT_MSG_ITEM_NOT_MATCHED, list.get(0), noHashAndEqualsObject1);
        Assert.assertEquals(ASSERT_MSG_ITEM_NOT_MATCHED, list.get(1), noHashAndEqualsObject3);
    }

    @Test
    public void testDistinctObjectWithoutHashAndEqualsViaFunctinalInterface() {
        List<NoHashAndEqualsObject> list = new ArrayList<>(noHashAndEqualsObjectList);
        log.info(LOG_FORMAT_ORIG_LIST, list);

        List<NoHashAndEqualsObject> uniques = list.stream()
                .filter(distinctByKey(o -> Arrays.asList(o.id, o.name)))
                .collect(Collectors.toList());

        log.info(LOG_FORMAT_UNIQ_LIST, uniques);

        Assert.assertFalse(ASSERT_MSG_NOT_BE_EXIST, uniques.contains(noHashAndEqualsObject2));
        Assert.assertEquals(ASSERT_MSG_ITEM_NOT_MATCHED, uniques.get(0), noHashAndEqualsObject1);
        Assert.assertEquals(ASSERT_MSG_ITEM_NOT_MATCHED, uniques.get(1), noHashAndEqualsObject3);
    }


    //Utility function
    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private class NoHashAndEqualsObject {
        String name;
        String id;

        NoHashAndEqualsObject(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    @EqualsAndHashCode
    private class HashAndEqualsObject {
        String name;
        String id;

        HashAndEqualsObject(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }
}
