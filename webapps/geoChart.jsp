<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    	<script type="text/javascript"
		src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript">
      var aryData = ${jsonAry}; // EL, 객체형식
      console.log(aryData);
      
      google.charts.load('current', {
        'packages':['geochart'],
        // Note: you will need to get a mapsApiKey for your project.
        // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
        'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
      });
      google.charts.setOnLoadCallback(drawRegionsMap);

      ary = new Array();
      ary.push(['Country', 'Popularity']);
      
      $.each(aryData, function(idx, obj) {
    	 ary.push([obj.country, obj.popularity]); 
      });
      
      function drawRegionsMap() {
        var data = google.visualization.arrayToDataTable(ary);

        var options = {};

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="regions_div" style="width: 900px; height: 500px;"></div>
  </body>
</html>