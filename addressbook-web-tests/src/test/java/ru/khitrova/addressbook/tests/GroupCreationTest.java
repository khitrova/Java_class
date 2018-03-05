package ru.khitrova.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.GroupData;
import ru.khitrova.addressbook.model.Groups;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);

        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()+1));



        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g)->(g.getId())).max().getAsInt()))));
    }

}
