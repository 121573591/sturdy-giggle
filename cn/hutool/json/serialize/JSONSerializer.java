package cn.hutool.json.serialize;

@FunctionalInterface
public interface JSONSerializer<T extends cn.hutool.json.JSON, V> {
  void serialize(T paramT, V paramV);
}
