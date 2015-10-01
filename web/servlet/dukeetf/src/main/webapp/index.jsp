<?xml version='1.0' encoding='UTF-8' ?>
<!--

    Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.

    You may not modify, use, reproduce, or distribute this software except in
    compliance with  the terms of the License at:
    http://java.net/projects/javaeetutorial/pages/BerkeleyLicense

-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en"
      xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Duke's HTTP ETF</title>
  <link rel="stylesheet" type="text/css" href="resources/css/default.css" />
  <script type="text/javascript">
      var ajaxRequest;
      function updatePage() {
          if (ajaxRequest.readyState === 4) {
              var arraypv = ajaxRequest.responseText.split("/");
              document.getElementById("price").innerHTML = arraypv[0];
              document.getElementById("volume").innerHTML = arraypv[1];
              makeAjaxRequest();
          }
      }
      function makeAjaxRequest() {
          ajaxRequest = new XMLHttpRequest();
          ajaxRequest.onreadystatechange = updatePage;
          ajaxRequest.open("GET", "http://localhost:8080/dukeetf/dukeetf", true);
          ajaxRequest.send(null);
      }
  </script>
</head>
<body onload="makeAjaxRequest();">
    <h1>Duke's HTTP ETF</h1>
    <table>
        <tr>
            <td width="100px">Ticker</td>
            <td align="center">Price</td>
            <td id="price" style="font-size:24pt;font-weight:bold;">--.--</td>
        </tr>
        <tr>
            <td style="font-size:18pt;font-weight:bold;" width="100px">DKEJ</td>
            <td align="center">Volume</td>
            <td id="volume" align="right">--</td>
        </tr>
    </table>
</body>
</html>
