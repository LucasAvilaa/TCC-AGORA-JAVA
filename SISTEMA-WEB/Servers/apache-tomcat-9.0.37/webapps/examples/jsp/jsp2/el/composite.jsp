<%--
 Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@ taglib prefix="my" uri="http://tomcat.apache.org/example-taglib" %>

<html>
  <head>
    <title>JSP 2.0 Expression Language - Composite Expressions</title>
  </head>
  <body>
    <h1>JSP 2.0 Expression Language - Composite Expressions</h1>
    <hr>
    This example illustrates EL composite expressions. Composite expressions
    are formed by grouping together multiple EL expressions. Each of them is
    evaluated from left to right, coerced to String, all those strings are
    concatenated, and the result is coerced to the expected type.

    <jsp:useBean id="values" class="jsp2.examples.ValuesBean" />

    <blockquote>
      <code>
        <table border="1">
          <thead>
        <th><b>EL Expression</b></th>
        <th><b>Type</b></th>
        <th><b>Result</b></th>
      </thead>
      <tr>
        <th>\${'hello'} wo\${'rld'}</th>
        <th>String</th>
        <th><jsp:setProperty name="values" property="stringValue" value="${'hello'} wo${'rld'}"/>${values.stringValue}</th>
      </tr>
      <tr>
        <th>\${'hello'} wo\${'rld'}</th>
        <th>String</th>
        <th><my:values string="${'hello'} wo${'rld'}"/></th>
      </tr>
      <tr>
        <th>\${1+2}.\${220}</th>
        <th>Double</th>
        <th><jsp:setProperty name="values" property="doubleValue" value="${1+2}.${220}"/>${values.doubleValue}</th>
      </tr>
      <tr>
        <th>\${1+2}.\${220}</th>
        <th>Double</th>
        <th><my:values double="${1+2}.${220}"/></th>
      </tr>
      <tr>
        <th>000\${1}\${7}</th>
        <th>Long</th>
        <th><jsp:setProperty name="values" property="longValue" value="000${1}${7}"/>${values.longValue}</th>
      </tr>
      <tr>
        <th>000\${1}\${7}</th>
        <th>Long</th>
        <th><my:values long="000${1}${7}"/></th>
      </tr>
      <!--
         Undefined values are to be coerced to String, to be "",
         https://bz.apache.org/bugzilla/show_bug.cgi?id=47413
       -->
      <tr>
        <th>\${undefinedFoo}hello world\${undefinedBar}</th>
        <th>String</th>
        <th><jsp:setProperty name="values" property="stringValue" value="${undefinedFoo}hello world${undefinedBar}"/>${values.stringValue}</th>
      </tr>
      <tr>
        <th>\${undefinedFoo}hello world\${undefinedBar}</th>
        <th>String</th>
        <th><my:values string="${undefinedFoo}hello world${undefinedBar}"/></th>
      </tr>
      <tr>
        <th>\${undefinedFoo}\${undefinedBar}</th>
        <th>Double</th>
        <th><jsp:setProperty name="values" property="doubleValue" value="${undefinedFoo}${undefinedBar}"/>${values.doubleValue}</th>
      </tr>
      <tr>
        <th>\${undefinedFoo}\${undefinedBar}</th>
        <th>Double</th>
        <th><my:values double="${undefinedFoo}${undefinedBar}"/></th>
      </tr>
      <tr>
        <th>\${undefinedFoo}\${undefinedBar}</th>
        <th>Long</th>
        <th><jsp:setProperty name="values" property="longValue" value="${undefinedFoo}${undefinedBar}"/>${values.longValue}</th>
      </tr>
      <tr>
        <th>\${undefinedFoo}\${undefinedBar}</th>
        <th>Long</th>
        <th><my:values long="${undefinedFoo}${undefinedBar}"/></th>
      </tr>
    </table>
      </code>
    </blockquote>
  </body>
</html>

