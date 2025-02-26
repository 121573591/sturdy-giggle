package cn.hutool.db.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.db.DbRuntimeException;
import cn.hutool.db.Entity;
import cn.hutool.db.Page;
import java.util.Collection;
import java.util.Set;

public class Query {
  Collection<String> fields;
  
  String[] tableNames;
  
  Condition[] where;
  
  Page page;
  
  public static Query of(Entity where) {
    Query query = new Query(SqlUtil.buildConditions(where), new String[] { where.getTableName() });
    Set<String> fieldNames = where.getFieldNames();
    if (CollUtil.isNotEmpty(fieldNames))
      query.setFields(fieldNames); 
    return query;
  }
  
  public Query(String... tableNames) {
    this(null, tableNames);
    this.tableNames = tableNames;
  }
  
  public Query(Condition[] where, String... tableNames) {
    this(where, null, tableNames);
  }
  
  public Query(Condition[] where, Page page, String... tableNames) {
    this(null, tableNames, where, page);
  }
  
  public Query(Collection<String> fields, String[] tableNames, Condition[] where, Page page) {
    this.fields = fields;
    this.tableNames = tableNames;
    this.where = where;
    this.page = page;
  }
  
  public Collection<String> getFields() {
    return this.fields;
  }
  
  public Query setFields(Collection<String> fields) {
    this.fields = fields;
    return this;
  }
  
  public Query setFields(String... fields) {
    this.fields = CollectionUtil.newArrayList((Object[])fields);
    return this;
  }
  
  public String[] getTableNames() {
    return this.tableNames;
  }
  
  public Query setTableNames(String... tableNames) {
    this.tableNames = tableNames;
    return this;
  }
  
  public Condition[] getWhere() {
    return this.where;
  }
  
  public Query setWhere(Condition... where) {
    this.where = where;
    return this;
  }
  
  public Page getPage() {
    return this.page;
  }
  
  public Query setPage(Page page) {
    this.page = page;
    return this;
  }
  
  public String getFirstTableName() throws DbRuntimeException {
    if (ArrayUtil.isEmpty((Object[])this.tableNames))
      throw new DbRuntimeException("No tableName!"); 
    return this.tableNames[0];
  }
}
