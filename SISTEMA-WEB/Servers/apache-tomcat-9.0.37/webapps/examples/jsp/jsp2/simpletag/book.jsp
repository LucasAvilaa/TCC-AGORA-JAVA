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
<%@ taglib prefix="my" uri="/WEB-INF/jsp2/jsp2-example-taglib.tld" %>
<html>
  <head>
    <title>JSP 2.0 Examples - Book SimpleTag Handler</title>
  </head>
  <body>
    <h1>JSP 2.0 Examples - Book SimpleTag Handler</h1>
    <hr>
    <p>Illustrates a semi-realistic use of SimpleTag and the Expression
    Language.  First, a &lt;my:findBook&gt; tag is invoked to populate
    the page context with a BookBean.  Then, the books fields are printed
    in all caps.</p>
    <br>
    <b><u>Result:</u></b><br>
    <my:findBook var="book"/>
    <table border="1">
        <thead>
        <th><b>Field</b></th>
        <th><b>Value</b></th>
        <th><b>Capitalized</b></th>
    </thead>
    <tr>
        <th>Title</th>
        <th>${book.title}</th>
        <th>${my:caps(book.title)}</th>
    </tr>
    <tr>
        <th>Author</th>
        <th>${book.author}</th>
        <th>${my:caps(book.author)}</th>
    </tr>
    <tr>
        <th>ISBN</th>
        <th>${book.isbn}</th>
        <th>${my:caps(book.isbn)}</th>
    </tr>
    </table>
  </body>
</html>
