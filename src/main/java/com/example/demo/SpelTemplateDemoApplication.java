package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpelTemplateDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpelTemplateDemoApplication.class, args);
  }

  @Bean
  ApplicationRunner run(SpelTemplateEngine templateEngine) {
    return args -> {
      // テンプレートファイルの読み込み
      String template = StreamUtils.copyToString(new ClassPathResource("welcome-template.txt").getInputStream(), StandardCharsets.UTF_8);
      // 変数の生成
      Map<String, Object> variables = new HashMap<>();
      User user = new User();
      user.setName("Kazuki  ");
      Project project = new Project();
      project.setName("MyBatis");
      variables.put("user", user);
      variables.put("project", project);
      // テンプレート処理の実装
      String text = templateEngine.process(template, variables);
      // テンプレート処理後のテキストを標準出力
      System.out.println(text);
    };
  }

  static class User {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  static class Project {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

}
