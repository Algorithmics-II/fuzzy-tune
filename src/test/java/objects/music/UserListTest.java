package objects.music;

import usb.finalproject.utils.DataReader;
import usb.finalproject.core.objects.User;

import usb.finalproject.core.objects.UserList;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class UserListTest {
    @Test
    void testNonEmptyUserList() {
        UserList userList = DataReader.readUsersFromJson("src/main/resources/users.json");
        List<User> users = userList.getUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    void testCompleteUserInfo() {
        UserList userList = DataReader.readUsersFromJson("src/main/resources/users.json");
        List<User> users = userList.getUsers();

        for (User user : users) {
            assertNotNull(user.getName());
            assertNotNull(user.getAge());
            assertNotNull(user.getFavoriteGenres());
            assertNotNull(user.getFavoriteArtists());
            assertNotNull(user.getFavoriteSongs());
            assertNotNull(user.getRecentlyPlayed());
        }
    }

    @Test
    void testNoDuplicateUsers() {
        UserList userList = DataReader.readUsersFromJson("src/main/resources/users.json");
        List<User> users = userList.getUsers();
        Set<User> uniqueUsers = new HashSet<>(users);
        assertEquals(users.size(), uniqueUsers.size());
    }
}
