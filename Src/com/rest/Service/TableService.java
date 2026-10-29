package com.rest.Service;

import com.rest.Models.Table;
import com.rest.Repository.TableRepository;
import java.util.List;

public class TableService {
    private final TableRepository repository;
    public TableService(){
        repository= new TableRepository();
    }
    public List<Table> getAllTables(){
        return repository.show();
    }
}
