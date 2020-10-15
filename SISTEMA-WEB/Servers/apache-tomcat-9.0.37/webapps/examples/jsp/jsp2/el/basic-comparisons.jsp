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
<html>
  <head>
    <title>JSP 2.0 Expression Language - Basic Comparisons</title>
  </head>
  <body>
    <h1>JSP 2.0 Expression Language - Basic Comparisons</h1>
    <hr>
    This example illustrates basic Expression Language comparisons.
    The following comparison operators are supported:
    <ul>
      <li>Less-than (&lt; or lt)</li>
      <li>Greater-than (&gt; or gt)</li>
      <li>Less-than-or-equal (&lt;= or le)</li>
      <li>Greater-than-or-equal (&gt;= or ge)</li>
      <li>Equal (== or eq)</li>
      <li>Not Equal (!= or ne)</li>
    </ul>
    <blockquote>
      <u><b>Numeric</b></u>
      <code>
        <table border="1">
          <thead>
        <th><b>EL Expression</b></th>
        <th><b>Result</b></th>
      </thead>
      <tr>
        <th>\${1 &lt; 2}</th>
        <th>${1 < 2}</th>
      </tr>
      <tr>
        <th>\${1 lt 2}</th>
        <th>${1 lt 2}</th>
      </tr>
      <tr>
        <th>\${1 &gt; (4/2)}</th>
        <th>${1 > (4/2)}</th>
      </tr>
      <tr>
        <th>\${1 gt (4/2)}</th>
        <th>${1 gt (4/2)}</th>
      </tr>
      <tr>
        <th>\${4.0 &gt;= 3}</th>
        <th>${4.0 >= 3}</th>
      </tr>
      <tr>
        <th>\${4.0 ge 3}</th>
        <th>${4.0 ge 3}</th>
      </tr>
      <tr>
        <th>\${4 &lt;= 3}</th>
        <th>${4 <= 3}</th>
      </tr>
      <tr>
        <th>\${4 le 3}</th>
        <th>${4 le 3}</th>
      </tr>
      <tr>
        <th>\${100.0 == 100}</th>
        <th>${100.0 == 100}</th>
      </tr>
      <tr>
        <th>\${100.0 eq 100}</th>
        <th>${100.0 eq 100}</th>
      </tr>
      <tr>
        <th>\${(10*10) != 100}</th>
        <th>${(10*10) != 100}</th>
      </tr>
      <tr>
        <th>\${(10*10) ne 100}</th>
        <th>${(10*10) ne 100}</th>
      </tr>
    </table>
      </code>
      <br>
      <u><b>Alphabetic</b></u>
      <code>
        <table border="1">
          <thead>
            <th><b>EL Expression</b></th>
            <th><b>Result</b></th>
          </thead>
          <tr>
            <th>\${'a' &lt; 'b'}</th>
            <th>${'a' < 'b'}</th>
          </tr>
          <tr>
            <th>\${'hip' &gt; 'hit'}</th>
            <th>${'hip' > 'hit'}</th>
          </tr>
          <tr>
            <th>\${'4' &gt; 3}</th>
            <th>${'4' > 3}</th>
          </tr>
        </table>
      </code>
    </blockquote>
  </body>
</html>
