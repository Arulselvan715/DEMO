package com.rest.Repository;

import com.rest.Models.Table;
import java.util.ArrayList;
import java.util.List;

public class TableRepository {
    private final List<Table> tables=new ArrayList<>();

    public TableRepository(){
        tables.add(new Table(1,5));
        tables.add(new Table(2,2));
        tables.add(new Table(3,7));

    }

        public List<Table> show(){
            return tables;
        }
    }