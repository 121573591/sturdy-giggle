package cn.hutool.core.date.chinese;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.TableMap;
import java.util.List;

public class LunarFestival {
  private static final TableMap<Pair<Integer, Integer>, String> L_FTV = new TableMap(16);
  
  static {
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(1)), "春节");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(2)), "犬日");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(3)), "猪日");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(4)), "羊日");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(5)), "牛日 破五日");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(6)), "马日 送穷日");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(7)), "人日 人胜节");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(8)), "谷日 八仙日");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(9)), "天日 九皇会");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(10)), "地日 石头生日");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(12)), "火日 老鼠娶媳妇日");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(13)), "上（试）灯日 关公升天日");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(15)), "元宵节 上元节");
    L_FTV.put(new Pair(Integer.valueOf(1), Integer.valueOf(18)), "落灯日");
    L_FTV.put(new Pair(Integer.valueOf(2), Integer.valueOf(1)), "中和节 太阳生日");
    L_FTV.put(new Pair(Integer.valueOf(2), Integer.valueOf(2)), "龙抬头");
    L_FTV.put(new Pair(Integer.valueOf(2), Integer.valueOf(12)), "花朝节");
    L_FTV.put(new Pair(Integer.valueOf(2), Integer.valueOf(19)), "观世音圣诞");
    L_FTV.put(new Pair(Integer.valueOf(3), Integer.valueOf(3)), "上巳节");
    L_FTV.put(new Pair(Integer.valueOf(4), Integer.valueOf(1)), "祭雹神");
    L_FTV.put(new Pair(Integer.valueOf(4), Integer.valueOf(4)), "文殊菩萨诞辰");
    L_FTV.put(new Pair(Integer.valueOf(4), Integer.valueOf(8)), "佛诞节");
    L_FTV.put(new Pair(Integer.valueOf(5), Integer.valueOf(5)), "端午节 端阳节");
    L_FTV.put(new Pair(Integer.valueOf(6), Integer.valueOf(6)), "晒衣节 姑姑节");
    L_FTV.put(new Pair(Integer.valueOf(6), Integer.valueOf(6)), "天贶节");
    L_FTV.put(new Pair(Integer.valueOf(6), Integer.valueOf(24)), "彝族火把节");
    L_FTV.put(new Pair(Integer.valueOf(7), Integer.valueOf(7)), "七夕");
    L_FTV.put(new Pair(Integer.valueOf(7), Integer.valueOf(14)), "鬼节(南方)");
    L_FTV.put(new Pair(Integer.valueOf(7), Integer.valueOf(15)), "中元节");
    L_FTV.put(new Pair(Integer.valueOf(7), Integer.valueOf(15)), "盂兰盆节 中元节");
    L_FTV.put(new Pair(Integer.valueOf(7), Integer.valueOf(30)), "地藏节");
    L_FTV.put(new Pair(Integer.valueOf(8), Integer.valueOf(15)), "中秋节");
    L_FTV.put(new Pair(Integer.valueOf(9), Integer.valueOf(9)), "重阳节");
    L_FTV.put(new Pair(Integer.valueOf(10), Integer.valueOf(1)), "祭祖节");
    L_FTV.put(new Pair(Integer.valueOf(10), Integer.valueOf(15)), "下元节");
    L_FTV.put(new Pair(Integer.valueOf(11), Integer.valueOf(17)), "阿弥陀佛圣诞");
    L_FTV.put(new Pair(Integer.valueOf(12), Integer.valueOf(8)), "腊八节");
    L_FTV.put(new Pair(Integer.valueOf(12), Integer.valueOf(16)), "尾牙");
    L_FTV.put(new Pair(Integer.valueOf(12), Integer.valueOf(23)), "小年");
    L_FTV.put(new Pair(Integer.valueOf(12), Integer.valueOf(30)), "除夕");
  }
  
  public static List<String> getFestivals(int year, int month, int day) {
    if (12 == month && 29 == day && 
      29 == LunarInfo.monthDays(year, month))
      day++; 
    return getFestivals(month, day);
  }
  
  public static List<String> getFestivals(int month, int day) {
    return L_FTV.getValues(new Pair(Integer.valueOf(month), Integer.valueOf(day)));
  }
}
