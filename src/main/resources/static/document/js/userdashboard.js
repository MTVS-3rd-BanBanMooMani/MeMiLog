new Chartist.Bar('#ct-chart1', {
    labels: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
    series: [
        [800000, 1500000, 1400000, 1300000, 800000, 300000]
    ]
}, {
    stackBars: true,
    axisY: {
        labelInterpolationFnc: function(value) {
            return (value / 1000) + 'k';
        }
    }
}).on('draw', function(data) {
    if(data.type === 'bar') {
        data.element.attr({
            style: 'stroke-width: 30px'
        });
    }
});

new Chartist.Bar('#ct-chart2', {
    labels: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
    series: [
        [800000, 1200000, 1400000, 1300000, 700000, 300000],

    ]
}, {
    stackBars: true,
    axisY: {
        labelInterpolationFnc: function(value) {
            return (value / 1000) + 'k';
        }
    }
}).on('draw', function(data) {
    if(data.type === 'bar') {
        data.element.attr({
            style: 'stroke-width: 30px'
        });
    }
});

new Chartist.Bar('#ct-chart3', {
    labels: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
    series: [
        [300, 200, 500, 200, 150, 300],

    ]
}, {
    stackBars: true,
    axisY: {
        labelInterpolationFnc: function(value) {
            return (value);
        }
    }
}).on('draw', function(data) {
    if(data.type === 'bar') {
        data.element.attr({
            style: 'stroke-width: 30px'
        });
    }
});

$(document).ready(function() {
    function createPieChart(elementId, data) {
        var ctx = document.getElementById(elementId).getContext('2d');
        return new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ["😁", "😎", "😮", "😐", "🥲"],
                datasets: [{
                    backgroundColor: [
                        "#5969ff",
                        "#ff407b",
                        "#25d5f2",
                        "#ffc750",
                        "#2ec551"
                    ],
                    data: data
                }]
            },
            options: {
                legend: {
                    display: true,
                    position: 'bottom',
                    labels: {
                        fontColor: '#71748d',
                        fontFamily: 'Circular Std Book',
                        fontSize: 14,
                    }
                }
            }
        });
    }

    createPieChart('chartjs_pie_teen', [28, 12, 10, 27, 23]);
    createPieChart('chartjs_pie_twenty', [12, 33, 10, 25, 20]);
    createPieChart('chartjs_pie_thirty', [18, 23, 5, 17, 37]);
    createPieChart('chartjs_pie_forty', [15, 22, 8, 30, 25]);
    createPieChart('chartjs_pie_fifty', [20, 20, 15, 25, 20]);
    createPieChart('chartjs_pie_sixty', [25, 20, 10, 25, 20]);
});
