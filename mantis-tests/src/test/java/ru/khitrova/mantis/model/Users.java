package ru.khitrova.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UsersData>{

    private Set<UsersData> delegate;

    public Users(Users groups) {
        this.delegate = new HashSet<UsersData>(groups.delegate);
    }

    public Users() {
        this.delegate = new HashSet<UsersData>();
    }

    public Users(Collection<UsersData> groups) {
        this.delegate = new HashSet<UsersData>(groups);
    }

    @Override
    protected Set<UsersData> delegate() {
        return delegate;
    }

    public Users withAdded(UsersData user){
        Users groups = new Users(this);
        groups.add(user);
        return groups;
    }

    public Users without(UsersData user){
        Users groups = new Users(this);
        groups.remove(user);
        return groups;
    }
}
