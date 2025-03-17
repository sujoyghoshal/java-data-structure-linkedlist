import java.util.*;

class FriendNode {
    int friendId;
    FriendNode next;

    public FriendNode(int friendId) {
        this.friendId = friendId;
        this.next = null;
    }
}

class UserNode {
    int userId;
    String name;
    int age;
    FriendNode friendList;
    UserNode next;

    public UserNode(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendList = null;
        this.next = null;
    }
}

class SocialMediaFriendConnections {
    private UserNode head;

    public void addUser(int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            UserNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
    }

    private UserNode findUser(int userId) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    public void addFriendConnection(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("User not found.");
            return;
        }
        addFriend(user1, userId2);
        addFriend(user2, userId1);
    }

    private void addFriend(UserNode user, int friendId) {
        FriendNode newFriend = new FriendNode(friendId);
        if (user.friendList == null) {
            user.friendList = newFriend;
        } else {
            FriendNode temp = user.friendList;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newFriend;
        }
    }

    public void removeFriendConnection(int userId1, int userId2) {
        removeFriend(findUser(userId1), userId2);
        removeFriend(findUser(userId2), userId1);
    }

    private void removeFriend(UserNode user, int friendId) {
        if (user == null || user.friendList == null) return;
        if (user.friendList.friendId == friendId) {
            user.friendList = user.friendList.next;
            return;
        }
        FriendNode temp = user.friendList;
        while (temp.next != null && temp.next.friendId != friendId) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public void displayFriends(int userId) {
        UserNode user = findUser(userId);
        if (user == null || user.friendList == null) {
            System.out.println("No friends found.");
            return;
        }
        System.out.print("Friends of " + user.name + ": ");
        FriendNode temp = user.friendList;
        while (temp != null) {
            System.out.print(temp.friendId + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void findMutualFriends(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);
        if (user1 == null || user2 == null || user1.friendList == null || user2.friendList == null) {
            System.out.println("No mutual friends found.");
            return;
        }
        Set<Integer> friendsOfUser1 = new HashSet<>();
        FriendNode temp = user1.friendList;
        while (temp != null) {
            friendsOfUser1.add(temp.friendId);
            temp = temp.next;
        }
        System.out.print("Mutual Friends: ");
        temp = user2.friendList;
        boolean found = false;
        while (temp != null) {
            if (friendsOfUser1.contains(temp.friendId)) {
                System.out.print(temp.friendId + " ");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.print("None");
        System.out.println();
    }

    public void searchUserById(int userId) {
        UserNode user = findUser(userId);
        if (user != null) {
            System.out.println("User Found: " + user.name + " (Age: " + user.age + ")");
        } else {
            System.out.println("User not found.");
        }
    }

    public void searchUserByName(String name) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("User Found: " + temp.name + " (ID: " + temp.userId + ", Age: " + temp.age + ")");
                return;
            }
            temp = temp.next;
        }
        System.out.println("User not found.");
    }
}

public class SocialMediaFriendConnectionss {
    public static void main(String[] args) {
        SocialMediaFriendConnections network = new SocialMediaFriendConnections();

        network.addUser(1, "Alice", 25);
        network.addUser(2, "Bob", 27);
        network.addUser(3, "Charlie", 22);
        
        network.addFriendConnection(1, 2);
        network.addFriendConnection(1, 3);
        
        System.out.println("Friends List:");
        network.displayFriends(1);
        
        System.out.println("\nMutual Friends between 1 and 2:");
        network.findMutualFriends(1, 2);
        
        System.out.println("\nSearching for User with ID 3:");
        network.searchUserById(3);
    }
}