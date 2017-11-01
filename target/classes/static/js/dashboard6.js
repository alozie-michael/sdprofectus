/*
Template Name: Material Pro Admin
Author: Themedesigner
Email: niravjoshi87@gmail.com
File: js
*/
$(function () {
    "use strict";
    // ==============================================================
    // Sales overview
    // ==============================================================
    var chart2 = new Chartist.Bar('.amp-pxl', {
          labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
          series: [
            [9, 5, 3, 7, 5, 10, 3],
            [6, 3, 9, 5, 4, 6, 4]
          ]
        }, {
          axisX: {
            // On the x-axis start means top and end means bottom
            position: 'end',
            showGrid: false
          },
          axisY: {
            // On the y-axis start means left and end means right
            position: 'start'
          },
        high:'12',
        low: '0',
        plugins: [
            Chartist.plugins.tooltip()
        ]
    });


    // ==============================================================
    // Our visitor
    // ==============================================================

    var chart = c3.generate({
        bindto: '#visitor',
        data: {
            columns: [
                ['Other', 30],
                ['Desktop', 10],
                ['Tablet', 40],
                ['Mobile', 50],
            ],

            type : 'donut',
            onclick: function (d, i) { console.log("onclick", d, i); },
            onmouseover: function (d, i) { console.log("onmouseover", d, i); },
            onmouseout: function (d, i) { console.log("onmouseout", d, i); }
        },
        donut: {
            label: {
                show: false
              },
            title: "Yearly Sales",
            width:20,

        },

        legend: {
          hide: true
          //or hide: 'data1'
          //or hide: ['data1', 'data2']
        },
        color: {
              pattern: ['#eceff1', '#745af2', '#26c6da', '#1e88e5']
        }
    });

  // ==============================================================
  // Newsletter
  // ==============================================================

  var chart = new Chartist.Line('.campaign', {
    labels: [1, 2, 3, 4, 5, 6, 7, 8],
    series: [
      [0, 5, 6, 8, 25, 9, 8, 24]
      , [0, 3, 1, 2, 8, 1, 5, 1]
    ]}, {
    low: 0,
    high: 28,
    showArea: true,
    fullWidth: true,
    plugins: [
      Chartist.plugins.tooltip()
    ],
    axisY: {
      onlyInteger: true
      , scaleMinSpace: 40
      , offset: 20
      , labelInterpolationFnc: function (value) {
        return (value / 1) + 'k';
      }
    },
  });




  // ==============================================================
    // sparkline charts
    // ==============================================================
    var sparklineLogin = function() {


        $("#spark1").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
            type: 'line',
            width: '100%',
            height: '50',
            lineColor: '#26c6da',
            fillColor: '#26c6da',
            maxSpotColor: '#26c6da',
            highlightLineColor: 'rgba(0, 0, 0, 0.2)',
            highlightSpotColor: '#26c6da'
        });
        $("#spark2").sparkline([0,2,8,6,8,5,6,4,8,6,6,2 ], {
            type: 'line',
            width: '100%',
            height: '50',
            lineColor: '#f16425',
            fillColor: '#f16425',
            minSpotColor:'#f16425',
            maxSpotColor: '#f16425',
            highlightLineColor: 'rgba(0, 0, 0, 0.2)',
            highlightSpotColor: '#f16425'
        });
        $("#spark3").sparkline([2,4,4,6,8,5,6,4,8,6,6,2], {
            type: 'line',
            width: '100%',
            height: '50',
            lineColor: '#239f8f',
            fillColor: '#239f8f',
            maxSpotColor: '#239f8f',
            highlightLineColor: 'rgba(0, 0, 0, 0.2)',
            highlightSpotColor: '#239f8f'
        });

      $("#spark1b").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
        type: 'line',
        width: '100%',
        height: '50',
        lineColor: '#0c5e54',
        fillColor: '#0c5e54',
        maxSpotColor: '#0c5e54',
        highlightLineColor: 'rgba(0, 0, 0, 0.2)',
        highlightSpotColor: '#0c5e54'
      });
      $("#spark2b").sparkline([0,2,8,6,8,5,6,4,8,6,6,2 ], {
        type: 'line',
        width: '100%',
        height: '50',
        lineColor: '#cd5529',
        fillColor: '#cd5529',
        minSpotColor:'#cd5529',
        maxSpotColor: '#cd5529',
        highlightLineColor: 'rgba(0, 0, 0, 0.2)',
        highlightSpotColor: '#cd5529'
      });
      $("#spark3b").sparkline([2,4,4,6,8,5,6,4,8,6,6,2], {
        type: 'line',
        width: '100%',
        height: '50',
        lineColor: '#239f8f',
        fillColor: '#239f8f',
        maxSpotColor: '#239f8f',
        highlightLineColor: 'rgba(0, 0, 0, 0.2)',
        highlightSpotColor: '#239f8f'
      });

        $('.spark-count').sparkline([4, 5, 0, 10, 9, 12, 4, 9, 4, 5, 3, 10, 9, 12, 10, 9, 12, 4, 9], {
            type: 'bar'
            , width: '100%'
            , height: '70'
            , barWidth: '2'
            , resize: true
            , barSpacing: '6'
            , barColor: 'rgba(255, 255, 255, 0.3)'
        });


   }
    var sparkResize;

        $(window).resize(function(e) {
            clearTimeout(sparkResize);
            sparkResize = setTimeout(sparklineLogin, 500);
        });
        sparklineLogin();
});
