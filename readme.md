# 基于SAX解析xml的封装
使用示例:
```java
      XMLParser parser = new XMLParser(ParserTest.class.getResourceAsStream("/contact.xml"));
      XMLObject result = parser.getResult();
      System.out.println(result.getTagName());
      System.out.println(result.getSingleTag("contact"));
      for (XMLObject item : result.getChildren()) {
          System.out.println(item);
      }

```