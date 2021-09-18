package com.example.demo;

import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class SpelTemplateEngine {

  private final SpelExpressionParser parser = new SpelExpressionParser();
  private final ParserContext parserContext = new TemplateParserContext("${", "}");
  private final Method[] functions = StringUtils.class.getMethods();

  public String process(String template, Map<String, Object> variables) {
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setVariables(variables); // 変数の追加
    Stream.of(functions).forEach(x -> context.registerFunction(x.getName(), x)); // カスタム関数の追加

    Expression expression = parser.parseExpression(template, parserContext); // テンプレート(SpEL)の評価
    return expression.getValue(context, String.class); // テンプレート処理
  }

}
