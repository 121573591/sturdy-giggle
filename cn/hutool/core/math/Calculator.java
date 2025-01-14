package cn.hutool.core.math;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Stack;

public class Calculator {
  private final Stack<String> postfixStack = new Stack<>();
  
  private final int[] operatPriority = new int[] { 0, 3, 2, 1, -1, 1, 0, 2 };
  
  public static double conversion(String expression) {
    return (new Calculator()).calculate(expression);
  }
  
  public double calculate(String expression) {
    prepare(transform(expression));
    Stack<String> resultStack = new Stack<>();
    Collections.reverse(this.postfixStack);
    while (false == this.postfixStack.isEmpty()) {
      String currentOp = this.postfixStack.pop();
      if (false == isOperator(currentOp.charAt(0))) {
        currentOp = currentOp.replace("~", "-");
        resultStack.push(currentOp);
        continue;
      } 
      String secondValue = resultStack.pop();
      String firstValue = resultStack.pop();
      firstValue = firstValue.replace("~", "-");
      secondValue = secondValue.replace("~", "-");
      BigDecimal tempResult = calculate(firstValue, secondValue, currentOp.charAt(0));
      resultStack.push(tempResult.toString());
    } 
    return NumberUtil.mul((String[])resultStack.toArray((Object[])new String[0])).doubleValue();
  }
  
  private void prepare(String expression) {
    Stack<Character> opStack = new Stack<>();
    opStack.push(Character.valueOf(','));
    char[] arr = expression.toCharArray();
    int currentIndex = 0;
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      char currentOp = arr[i];
      if (isOperator(currentOp)) {
        if (count > 0)
          this.postfixStack.push(new String(arr, currentIndex, count)); 
        char peekOp = ((Character)opStack.peek()).charValue();
        if (currentOp == ')') {
          while (((Character)opStack.peek()).charValue() != '(')
            this.postfixStack.push(String.valueOf(opStack.pop())); 
          opStack.pop();
        } else {
          while (currentOp != '(' && peekOp != ',' && compare(currentOp, peekOp)) {
            this.postfixStack.push(String.valueOf(opStack.pop()));
            peekOp = ((Character)opStack.peek()).charValue();
          } 
          opStack.push(Character.valueOf(currentOp));
        } 
        count = 0;
        currentIndex = i + 1;
      } else {
        count++;
      } 
    } 
    if (count > 1 || (count == 1 && !isOperator(arr[currentIndex])))
      this.postfixStack.push(new String(arr, currentIndex, count)); 
    while (((Character)opStack.peek()).charValue() != ',')
      this.postfixStack.push(String.valueOf(opStack.pop())); 
  }
  
  private boolean isOperator(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '%');
  }
  
  private boolean compare(char cur, char peek) {
    int offset = 40;
    if (cur == '%')
      cur = '/'; 
    if (peek == '%')
      peek = '/'; 
    return (this.operatPriority[peek - 40] >= this.operatPriority[cur - 40]);
  }
  
  private BigDecimal calculate(String firstValue, String secondValue, char currentOp) {
    BigDecimal result;
    switch (currentOp) {
      case '+':
        result = NumberUtil.add(new String[] { firstValue, secondValue });
        return result;
      case '-':
        result = NumberUtil.sub(new String[] { firstValue, secondValue });
        return result;
      case '*':
        result = NumberUtil.mul(firstValue, secondValue);
        return result;
      case '/':
        result = NumberUtil.div(firstValue, secondValue);
        return result;
      case '%':
        result = NumberUtil.toBigDecimal(firstValue).remainder(NumberUtil.toBigDecimal(secondValue));
        return result;
    } 
    throw new IllegalStateException("Unexpected value: " + currentOp);
  }
  
  private static String transform(String expression) {
    expression = StrUtil.cleanBlank(expression);
    expression = StrUtil.removeSuffix(expression, "=");
    char[] arr = expression.toCharArray();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == '-')
        if (i == 0) {
          arr[i] = '~';
        } else {
          char c = arr[i - 1];
          if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == 'E' || c == 'e')
            arr[i] = '~'; 
        }  
    } 
    if (arr[0] == '~' && arr.length > 1 && arr[1] == '(') {
      arr[0] = '-';
      return "0" + new String(arr);
    } 
    return new String(arr);
  }
}
