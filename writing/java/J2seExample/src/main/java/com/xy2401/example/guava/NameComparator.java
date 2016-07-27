package com.xy2401.example.guava;

import java.util.Comparator;

public class NameComparator implements Comparator<UserGuava> {
    public int compare(UserGuava user, UserGuava user1) {
        return user.getName().compareTo(user1.getName());
    }
}