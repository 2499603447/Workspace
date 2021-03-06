/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/25 23:39
 */
public class GetImportance {
    static class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    };

    public static void main(String[] args) {
        GetImportance gi = new GetImportance();
        Employee e1 = new Employee();
        e1.id = 1;
        e1.importance = 2;
        e1.subordinates = new ArrayList<Integer>(){{add(2);}};
        Employee e2 = new Employee();
        e2.id = 2;
        e2.importance = 3;
        e2.subordinates = new ArrayList<>();
        List<Employee> employees = new ArrayList<Employee>(){{
            add(e1);
            add(e2);
        }};
        gi.getImportance1(employees, 1);
    }

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Employee em : employees) {
            map.put(em.id, em.importance);
        }

        int sum = 0;
        for (Employee em : employees) {
            if (em.id == id) {
                sum += em.importance;
                for (Integer val : em.subordinates) {
                    sum += map.getOrDefault(val, 0);
                }
            }
        }

        return sum;
    }

    Map<Integer, Employee> emap;
    public int getImportance1(List<Employee> employees, int queryid) {
        emap = new HashMap();
        for (Employee e: employees) emap.put(e.id, e);
        return dfs(queryid);
    }
    public int dfs(int eid) {
        Employee employee = emap.get(eid);
        int ans = employee.importance;
        for (Integer subid: employee.subordinates)
            ans += dfs(subid);
        return ans;
    }
}
