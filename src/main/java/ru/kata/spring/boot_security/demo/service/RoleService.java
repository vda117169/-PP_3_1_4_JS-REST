package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    public Role getRoleByName(String name);
    public List<Role> listRoles();

    void add(Role roleAdmin);
}
