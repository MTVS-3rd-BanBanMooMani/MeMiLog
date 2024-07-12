var ctx2 = document.querySelectorAll(".chart-line");

new Chart(ctx2[ctx2.length-1], {
    type: "line",
    data: {
        labels: ["1주차", "2주차", "3주차", "4주차", "5주차"],
        datasets: [{
            label: "Weekly Reports",
            tension: 0.4,
            borderWidth: 0,
            pointRadius: 0,
            borderColor: "#00ab55",
            borderWidth: 3,
            backgroundColor: "transparent",
            data: [20, 60, 20, 50, 60],
            maxBarThickness: 6
        }],
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
            display: false,
        },
        tooltips: {
            enabled: true,
            mode: "index",
            intersect: false,
            backgroundColor: '#ffffff',
            titleFontColor: '#000000',
            bodyFontColor: '#000000',
            footerFontColor: '#000000',
            fontSize: '20px',
            callbacks: {
                label: function(tooltipItem, data) {
                    var label = data.datasets[tooltipItem.datasetIndex].label || '';
                    if (label) {
                        label += ': ';
                    }
                    label += tooltipItem.yLabel;
                    return label;
                }
            }
        },
        scales: {
            yAxes: [{
                gridLines: {
                    borderDash: [2],
                    borderDashOffset: [2],
                    color: '#dee2e6',
                    zeroLineColor: '#dee2e6',
                    zeroLineWidth: 1,
                    zeroLineBorderDash: [2],
                    drawBorder: false,
                },
                ticks: {
                    suggestedMin: 0,
                    suggestedMax: 100,
                    beginAtZero: true,
                    padding: 10,
                    fontSize: 20,
                    fontColor: '#000',
                    lineHeight: 3,
                    fontStyle: 'normal',
                    fontFamily: "Public Sans",
                },
            }],
            xAxes: [{
                gridLines: {
                    zeroLineColor: 'rgba(0,0,0,0)',
                    display: false,
                },
                ticks: {
                    padding: 10,
                    fontSize: 20,
                    fontColor: '#adb5bd',
                    lineHeight: 3,
                    fontStyle: 'normal',
                    fontFamily: "Public Sans",
                },
            }],
        },
    },
});

const reportedPosts = [
    {id: 13, 작성자: 'dddafsa', 내용: '♨︎고수익♦︎투자♛종목♤추천☏', 신고건수: 10},
    {id: 22, 작성자: '영이', 내용: '집에서 쉽게 하는 부업', 신고건수: 8},
    {id: 32, 작성자: 'ㅁㅎㄹ', 내용: '블로그로 월수익 3000만원 만들기💖', 신고건수: 6},
    {id: 24, 작성자: 'ㅇㅇ', 내용: '[Web발신] 전회차 L0TT0 2등나왔내요 인증 꼭 약속 해주시면 무료입니다', 신고건수: 4},
    {id: 35, 작성자: 'sadfwg', 내용: '안녕하세요, 저 기억나요? 저는 한국에 온 지 얼마 되지 않았지만, 당신과 연락이 되지 않습니다. 당신은 나의 라인을 추가하여 답장할 수 있습니다.나의 라인은:2222222', 신고건수: 3}
];

function renderReportedPosts(){
    const reportList = document.getElementById('report-list');
    reportedPosts.forEach(post => {
        const listItem = document.createElement('li');
        listItem.className = 'list-group-item';
        listItem.textContent = `[작성자 : ${post.작성자}] ${post.내용} ------- ${post.신고건수} reports`;
        reportList.appendChild(listItem);
    });
}

document.addEventListener('DOMContentLoaded', renderReportedPosts);

$(document).ready(function() {
    $('#sparkline-1').sparkline([3, 4, 6, 7, 9, 4, 7], {
        type: 'line',
        width: '100%',
        height: '120',
        lineColor: '#1e88e5',
        fillColor: 'rgba(30, 136, 229, 0.2)',
        spotColor: '#1e88e5',
        minSpotColor: '#1e88e5',
        maxSpotColor: '#1e88e5',
        highlightLineColor: '#1e88e5',
        spotRadius: 4,
        highlightSpotColor: '#1e88e5'
    });

    const days = ['일', '월', '화', '수', '목', '금', '토'];
    const today = new Date();
    const todayIndex = today.getDay();

    let labelsHtml = '';
    for (let i=0; i<7; i++){
        const dayIndex = (todayIndex - i + 7) % 7;
        labelsHtml = `<span>${days[dayIndex]}</span>` + labelsHtml;
    }

    $('#day-labels').html(labelsHtml);
});

var ctx = document.getElementById('reportTypeChart').getContext('2d');
        var data = {
            labels: ['스팸 및 광고', '선정적인 게시물', '개인정보노출', '영리목적/홍보성', '개인정보 노출'],
            datasets: [{
                data: [2.50, 16.69, 6.34, 23.03, 51.44],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#BE81F7']
            }]
        };

        var reportTypeChart = new Chart(ctx, {
            type: 'pie',
            data: data,
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: '신고 유형 분포'
                    }
                }
            },
        });