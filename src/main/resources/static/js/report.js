function option(dto = [], model, name) {
    let A2 = dto.find(v => v.type === 'A2')
    let B1 = dto.find(v => v.type === 'B1')
    return {
        type: 'doughnut',
        data: {
            labels: ['A2', 'B1'],
            datasets: [{
                label: name,
                data: [A2[model], B1[model]],
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)'
                ],
                hoverOffset: 4
            }]
        },
        options: {
            plugins: {
                title: {
                    display: true,
                    text: name
                }
            }
        }
    }
}

$(document).ready(function () {

    console.log(reportDto)
    new Chart(document.getElementById('reportChartExam'), option(reportDto, 'numExam', 'Exam'));
    new Chart(document.getElementById('reportChartRoom'), option(reportDto, 'numRoom', 'Room'));
    new Chart(document.getElementById('reportChartCandidate'), option(reportDto, 'numCandidate', 'Candidate'));

})